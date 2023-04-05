package cn.edu.guet.mqtt;

import cn.edu.guet.entity.*;
import cn.edu.guet.service.*;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import static cn.edu.guet.pay.WXPayT.unifiedOrder;
/**
 * MessageHeaders:
 * public static final String PREFIX = "mqtt_";
 * public static final String QOS = "mqtt_qos";
 * public static final String ID = "mqtt_id";
 * public static final String RECEIVED_QOS = "mqtt_receivedQos";
 * public static final String DUPLICATE = "mqtt_duplicate";
 * public static final String RETAINED = "mqtt_retained";
 * public static final String RECEIVED_RETAINED = "mqtt_receivedRetained";
 * public static final String TOPIC = "mqtt_topic";
 * public static final String RECEIVED_TOPIC = "mqtt_receivedTopic";
 * public static final String MESSAGE_EXPIRY_INTERVAL = "mqtt_messageExpiryInterval";
 * public static final String TOPIC_ALIAS = "mqtt_topicAlias";
 * public static final String RESPONSE_TOPIC = "mqtt_responseTopic";
 * public static final String CORRELATION_DATA = "mqtt_correlationData";
 *
 * @version 1.0
 * @Author qin
 * @Date 2023/3/12 0:56
 */

/**
 * MQTT接收消息
 * {
 * "BEEP": 1,
 * "time": 0,
 * "DZF": 0,
 * "DZF1": 0,
 * "types": "Esp8266",
 * "status": 1,
 * "user_id": 2,
 * "user_name": "David" ,
 * "user_money": "123" ,
 * "user_password": "ssss" ,
 * "ic_states":"pay",
 * "devicd_id": "qzw10086",
 * "Bottle": 0,
 * "Pump": 0,
 * "Height": 1986.0,
 * "Height1": 20.0,
 * "Flow": 2969.0,
 * "Flow1": 0.0,
 * "Temp": 4.0,
 * "amount":1,
 * "address":"桂电",
 * "money":12,
 * "ph": 202.0,
 * "tds": 201.0,
 * "Pump1": 1,
 * "ZW_LED": 1,
 * "robot_id":1
 * }
 */
@Service
public class MqttCaseServiceImpl implements MessageHandler {
    private static final Logger logger = LoggerFactory.getLogger(MqttCaseServiceImpl.class);


    @Autowired
    Gson gson;

    User user = null;
    UserOrder order = null;
    Collect collect = null;
    Account account = null;
    //这个是外面配置文件里面的设置的接收主题之一
    private final static String SEND_TOPIC_PREFIX = "smartwater/";

    @Autowired
    IMqttSender mqttSender;

    @Autowired
    UserOrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CollectService collectService;
    @Autowired
    AccountService accountService;


    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
        String payload = (String) message.getPayload();
        logger.info(String.valueOf(message));
        //System.out.println();
        logger.info("Headers:" + topic + " Accept:" + payload);
        //System.out.println("Headers:" + topic + " Accept:" + payload);
        if (topic.contains("smartwater")) {
            logger.info("The theme of hardware information");
            //System.out.println("The theme of hardware information");
            IotData entity = gson.fromJson(payload, IotData.class);

            if (entity != null) {
                //不是心跳数据
                if (!entity.getTypes().equals("heartbeat")) {
                    //判断硬件来源
                    if (entity.getTypes().equals("esp32")) {
                        logger.info("This is esp32's data");
                        //System.out.println("This is esp32's data");
                        //写入数据库 ...
                    } else if (entity.getTypes().equals("Esp8266")) {
                        logger.info("This is esp8266's data");
                        //写入数据库 ...,当数据认定了是人为操作时，存入数据库中.即user_name!=null时
                        if (!(entity.getUser_name()).equals("")) {
                            logger.info("Legally write to database!");
                            user = JSONUtil.toBean(payload, User.class);
                            System.out.println(user);
                            collect = JSONUtil.toBean(payload.toLowerCase(), Collect.class);
                            System.out.println(collect);
                            account = JSONUtil.toBean(payload.toLowerCase(), Account.class);
                            //System.out.println(account);
                            collect.setId(0);
                            order = JSONUtil.toBean(payload.toLowerCase(), UserOrder.class);

                            System.out.println(account);
                            order.setAmount(1);
                            order.setMoney(Integer.valueOf(account.getUser_money()));//虚拟账户，即是临时容量金额
                            order.setId(1);
                            collectService.insert(collect);
                            System.out.println(order);
                            String url = "";
                            paydata paydata = new paydata();
                            if (account.getIc_states().equals("pay")) {
                                logger.info("pay stats start...");
                                orderService.insert(order);//将订单装入

                                url = unifiedOrder(order.getAmount() * order.getMoney());//生成二维码支付。
                                url = url.substring(url.lastIndexOf("=") + 1);

                                String topicsx = "sub";
                                order.setPay_url(url);
                                order.setTarget("pay_url");
                                String topicx = SEND_TOPIC_PREFIX + topicsx;
                                //{"money":5,"pay_url":"weixin://wxpay/bizpayurl?pr\u003dU5TLiauzz","target":"pay_url"}

                                paydata.setTarget(order.getTarget());
                                paydata.setPay_url(url);
                                paydata.setMoney(order.getMoney());
                                String payloadx = gson.toJson(paydata);
                                mqttSender.sendToMqtt(topicx, payloadx);
                                logger.info("Send_ok=>" + " Topic:" + topicx + "  payload:" + payloadx);
                            }
                        }
                    } else {
                        logger.info("Refuse to write to Database");
                    }
                }
            } else {
                logger.info("Serialization Failed");
                //System.out.println("Serialization Failed");
            }

        }
        if (topic.contains("receive_chat_topic")) {
            logger.info("The theme of the conversation");
            //System.out.println("The theme of the conversation");
            //...构建聊天室.....
            //...相互订阅发送消息就可以了....
            //...逻辑代码...

        }
    }
}



