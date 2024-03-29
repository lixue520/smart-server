package cn.edu.guet.controller;

import cn.edu.guet.entity.Order;
import cn.edu.guet.entity.UserOrder;
import cn.edu.guet.mqtt.IMqttSender;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author liwei
 * @Date 2021-08-04 11:28
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/wxApi")
public class TestPayController {

    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TestPayController.class);

    //这个是外面配置文件里面的设置的接收主题之一
    private final static String SEND_TOPIC_PREFIX = "smartwater/";

    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;


    @RequestMapping("/getPayState")
    public Map<String, String> GetPayState(@RequestBody Map<String, String> ResultMap) {
        System.out.println("前端查询当前订单下的支付状态");
        Map<String, String> map = new HashMap<>();
        // map.put("order_solve",orderMapperService.Select_Current_PayState(ResultMap.get("out_trade_no")));
        return map;
    }

    @GetMapping("/auth")
    public String auth(@RequestParam("code") String code) {
        log.info("获取code");
        log.info("code : " + code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd9a46e74fc279fcc&secret=7deb521448e11ddbc163fca849648198&code=" + code + "&grant_type=authorization_code";
        String response = restTemplate.getForObject(url, String.class);
        log.info("response = {}", response);
        return response;
    }

    @PostMapping("/result")
    public String result(HttpServletRequest request, HttpServletResponse res) { //回调是安全性数据最好接收post请求
        String ip = request.getRemoteAddr();
        System.out.println("IP地址:" + ip);
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        StringBuffer inputString = new StringBuffer();

        try {
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            request.getReader().close();
            String response = inputString.toString();//接收的回调结果（支付成功后，腾讯调用我们的接口，并发数据给我们）
            String pattern =
                    ".+<attach><!\\[CDATA\\[(.+);.+" +
                            "<mch_id><!\\[CDATA\\[(\\d{10}).+" +
                            "<out_trade_no><!\\[CDATA\\[(.{32}).+" +
                            "<time_end><!\\[CDATA\\[(\\d{14}).+" +
                            "<transaction_id><!\\[CDATA\\[(\\d{28})";
            Pattern r = Pattern.compile(pattern);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(response);
            if (m.find()) {

                String itemInfo = m.group(1);
                String item[] = itemInfo.split(";");

                String item_id = item[0].split(",")[1];
                String item_price = item[1].split(",")[1];
                String item_amount = item[2].split(",")[1];
                String mch_id = m.group(2);
                String out_trade_no = m.group(3);
                String orderTime = m.group(4);
                String transactionId = m.group(5);
                System.out.println("交易号：" + transactionId);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                Timestamp timestamp = new Timestamp(sdf.parse(orderTime).getTime());

//                HistoryOrder historyOrder = new HistoryOrder();
//                String id = UUID.randomUUID().toString().replace("-", "");
//                historyOrder.setUuid(id);
//                historyOrder.setMch_id(Integer.valueOf(mch_id));
//                historyOrder.setOut_trade_no(out_trade_no);
//                historyOrder.setOrder_time(timestamp);
//                historyOrder.setTransaction_id(transactionId);
//
//                //将信息插入数据库
//                System.out.println("---------服务器部署成功接收回调消息---------");
//                orderMapperService.Add_HistoryOrder(historyOrder);
//                System.out.println("当前交易ID:"+historyOrder.getOut_trade_no());

                /*
                发信息给微信，告知微信官方，已收到通知
                 */
                String result = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" +
                        "<return_msg><![CDATA[OK]]></return_msg>" +
                        "</xml>";
                System.out.println("sssssssssssssssssssssssss");
                //这里是完成支付的消息，将消息发送走，让单片机察觉到订单完成
                // 前缀 + topic
                String topics = "sub";
                UserOrder order = new UserOrder();

                order.setPay_url("ok");
                order.setTarget("pay_url");
                order.setMoney(0);

                String topic = SEND_TOPIC_PREFIX + topics;
                String payload = gson.toJson(order);
                mqttSender.sendToMqtt(topic, payload);
                logger.info("Send_ok=>" + " Topic:" + topic + "  payload:" + payload);

                BufferedOutputStream out = new BufferedOutputStream(
                        res.getOutputStream());
                out.write(result.getBytes());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "服务器接收回调地址";
    }
}
