package cn.edu.guet.pay;


import cn.edu.guet.controller.TestPayController;
import cn.edu.guet.entity.Order;
import cn.edu.guet.mqtt.IMqttSender;
import cn.edu.guet.sdk.WXPayUtil;
import cn.edu.guet.util.DateUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class WXPayT {

    private static final Logger logger = LoggerFactory.getLogger(TestPayController.class);

    //这个是外面配置文件里面的设置的接收主题之一
    private final static String SEND_TOPIC_PREFIX = "smartwater/";

    public String er_url;

    @Autowired
    IMqttSender mqttSender;

    @Autowired
    Gson gson;

    private static Log log = LogFactory.getLog(WXPayT.class);

    private static final String PAY_SUCCESS = "SUCCESS";
    private static final String PAY_USERPAYING = "USERPAYING";

    //二维码支付方法
    public String TwoCodePay(int price) throws Exception {
        //执行支付，并返还交易号
        return unifiedOrder(price);
    }

    //一维码支付方法
    public void OneCodePay(String auth_code, int price) throws Exception {
        scanCodeToPay(auth_code, price);
    }

    public static void main(String[] args) throws Exception {

        // 生成二维码，完成支付
        // unifiedOrder();
        // 商家扫用户手机的条形码
        //scanCodeToPay("133819286798799636",1);  一维码支付
        unifiedOrder(1);

    }

    /**
     * 扫码支付
     *
     * @throws Exception
     */
    public static String scanCodeToPay(String auth_code, int price) throws Exception {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();

        MyConfig config = new MyConfig();
        cn.edu.guet.sdk.WXPay wxpay = new cn.edu.guet.sdk.WXPay(config);
        String out_trade_no = DateUtil.getCurrentTime();
        Map<String, String> map = new HashMap<>(16);
        map.put("attach", "订单额外描述");
        map.put("auth_code", auth_code);
        map.put("body", "购物");
        map.put("device_info", "矿泉水自动贩卖机");
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("out_trade_no", out_trade_no);
        map.put("spbill_create_ip", spbill_create_ip);
        map.put("total_fee", String.valueOf(price));   //可通过依赖包的数据传入并生成
        //生成签名
        String sign = WXPayUtil.generateSignature(map, config.getKey());
        map.put("sign", sign);
        String mapToXml = null;
        try {
            //调用微信的扫码支付接口
            Map<String, String> resp = wxpay.microPay(map);
            System.out.println("扫码支付结果：" + resp);
            mapToXml = WXPayUtil.mapToXml(resp);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信支付失败" + e);
        }

        return "";
    }

    /*
    下单：生成二维码
     */
    public static String unifiedOrder(int price) {
        Map<String, String> resultMap = new HashMap();
        String openid = "ouR0E1oP5UGTEBce8jZ_sChfH26g";
        MyConfig config = null;
        cn.edu.guet.sdk.WXPay wxpay = null;
        try {
            config = new MyConfig();
            wxpay = new cn.edu.guet.sdk.WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成的随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
        //获取客户端的ip地址
        //获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();
        //支付金额，需要转成字符串类型，否则后面的签名会失败
        int total_fee = price;//100分：1块钱
        //商品描述
        String body = "矿泉水智能贩卖机";
        //商户订单号
        String out_trade_no = WXPayUtil.generateNonceStr();
        //统一下单接口参数
        SortedMap<String, String> data = new TreeMap<String, String>();
        data.put("appid", "wx1cf40e3ddcb87ac8");
        data.put("body", body);
        data.put("mch_id", "1623889015");
        // 回调接口，必须是一个域名，不能使用IP
        // 腾讯会自动调用你（程序自己提供的接口）的接口，给你发送支付结果的数据，数据格式：xml格式
        data.put("notify_url", "http://qzwxixi.e3.luyouxia.net:12795/wxApi/result");
        data.put("out_trade_no", out_trade_no);//交易号
        data.put("spbill_create_ip", spbill_create_ip);//下单的电脑IP地址
        data.put("trade_type", "NATIVE");//支付类型
        data.put("total_fee", String.valueOf(total_fee));
        //data.put("openid", openid);
        data.put("attach", "id,11111;price,18.00;amount,1;");//交付给数据库处理，这个暂时不用处理

        try {
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);

            return createQRCode(rMap);//生成二维码;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String createQRCode(Map<String, String> map) throws Exception {

        File directory = new File("");//设定为当前文件夹
        System.out.println(directory.getAbsolutePath());//获取绝对路径;
        File outputFile = new File(directory.getAbsolutePath() + File.separator + "pay.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        String url = map.get("code_url");
        log.info("Generate QR code URL:" + url);

        /**
         * Mqtt:将二维码发出去
         */
        // System.out.println("Generate QR code URL:" + url);

        try {
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);

            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", fileOutputStream);
            return url;
        } catch (Exception e) {
            throw new Exception("生成二维码失败！");
        } finally {
            fileOutputStream.close();
        }
    }
}