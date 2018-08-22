package com.qfedu.shop.controller;

import com.google.gson.Gson;
import com.qfedu.shop.pojo.Order;
import com.qfedu.shop.pojo.OrderItem;
import com.qfedu.shop.service.OrderService;
import com.wx.pay.utils.PayCommonUtil;
import com.wx.pay.utils.PayConfigUtil;
import com.wx.pay.utils.XMLUtil;
import com.wx.pay.utils.ZxingUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class PayController {

    @Autowired
    private OrderService orderService;
    @RequestMapping("/getorderstate")
    public void getorderstate(HttpServletRequest req, HttpServletResponse resp){
        String orderid=req.getParameter("orderid");
        System.out.println(orderid);
        try {
            //请求微信服务器，获取orderid对应的订单的支付信息。
            String str=PayCommonUtil.weixin_query(orderid);
            resp.setContentType("text/html;charset=utf-8");
            //String str= (String) req.getSession().getAttribute("state");
            System.out.println(str);
            if("支付成功".equals(str)){
                orderService.payOrder(orderid);
            }
            resp.getWriter().print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @RequestMapping("/pay")
    public void test(HttpServletRequest req, HttpServletResponse resp,@RequestParam("orderId") String orderId) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        Order order=orderService.findOrderByOrderid(orderId);
        //String price = order.getPrice()*100+"";//此处默认是1分,次数需要项目开发中实际根据用户购买的商品获取
        String price="1";
        //String body = req.getParameter("body");//商品描述,获取用户前台输入的想要购买的商品,此处需要参考项目实际开发中获取

        String body="";
        List<OrderItem> items=order.getItems();
        for (int i = 0; i < items.size(); i++) {
            body=body+items.get(i).getName()+" ";
        }


        if (req.getMethod().equalsIgnoreCase("get")) {
            body = new String(body.getBytes("ISO8859-1"), "UTF-8");
        }
       // String orderId = random.nextInt(100000000) + "";//此处随机生成伪订单,实际开发中请参考项目需求生成
        try {
            String url = PayCommonUtil.weixin_pay(price, body, orderId);//获取微信返回的二维码对应的短地址
            //System.out.println(JSONObject.fromObject(jsonObject).getString("code_url"));



            //更新数据库，订单表中，这个订单号对应的订单的支付状态。

            //req.getSession().setAttribute("url", url);//将图片放到 session 中
            resp.sendRedirect("/shop/payment.jsp?order_id="+orderId+"&url="+URLEncoder.encode(url, "utf-8"));//跳转到支付页面,显示二维码
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping("/image")
    public void image(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //url代表了手机微信访问的地址
        String url=req.getParameter("url");//二维码的文本信息
        BufferedImage image=ZxingUtil.createImage(url,300,300);
        if(image!=null){
            ImageIO.write(image,"JPEG",resp.getOutputStream());
        }
    }

    @RequestMapping("/result")
    public void result(HttpServletRequest req, HttpServletResponse resp){
        try {
            weixin_notify(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("完成了");
        String writeContent = "默认支付失败";// 因为没有重定向,所以测试时无法知道支付结果,因此将支付结果写入文件,开发时访问文件查看,实际开发中删除
        String path = request.getSession().getServletContext().getRealPath("file");// 保存结果文件的位置
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(path + "/result.txt", true);// 创建输出流,写入结果用,实际开发中删除由此到上面的内容
        // 读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        // 解析xml成map
        Map<String, String> m = new HashMap<String, String>();
        m = XMLUtil.doXMLParse(sb.toString());

        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        // 账号信息
        String key = PayConfigUtil.API_KEY; // key

        System.err.println(packageParams);
        String out_trade_no = (String) packageParams.get("out_trade_no");// 订单号,实际开发中应该在下面的 IF 中,除非需要对每个订单的每次支付结果做记录
        // 判断签名是否正确
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            String resXml = "";
            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                // 这里是支付成功
                ////////// 执行自己的业务逻辑////////////////
                String mch_id = (String) packageParams.get("mch_id");
                String openid = (String) packageParams.get("openid");
                String is_subscribe = (String) packageParams.get("is_subscribe");
                // String out_trade_no = (String)packageParams.get("out_trade_no");

                String total_fee = (String) packageParams.get("total_fee");

                System.err.println("mch_id:" + mch_id);
                System.err.println("openid:" + openid);
                System.err.println("is_subscribe:" + is_subscribe);
                System.err.println("out_trade_no:" + out_trade_no);
                System.err.println("total_fee:" + total_fee);

                ////////// 执行自己的业务逻辑////////////////

                System.err.println("支付成功");
                writeContent = "订单:" + out_trade_no + "支付成功";// 拼接支付结果信息,写入文件,实际开发中删除
                // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

            } else {
                writeContent = "订单" + out_trade_no + "支付失败,错误信息：" + packageParams.get("err_code");// 拼接支付结果信息,写入文件,实际开发中删除
                System.err.println("订单" + out_trade_no + "支付失败,错误信息：" + packageParams.get("err_code"));
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            writeContent = "订单" + out_trade_no + "通知签名验证失败,支付失败";// 拼接支付结果信息,写入文件,实际开发中删除
            System.err.println("通知签名验证失败");
        }
        fileOutputStream.write(writeContent.getBytes());// 将支付结果写入文件,实际开发中删除
        fileOutputStream.close();// 将支付结果写入文件,实际开发中删除
    }
}
