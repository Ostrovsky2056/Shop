package com.qfedu.shop.controller;

import com.qfedu.shop.pojo.User;
import com.qfedu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value="/login",method ={RequestMethod.POST})
    public String login(
            @RequestParam("loginInfo") String loginInfo,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        //登录逻辑
        //返回"user"  "admin"  "error"
        User user=userService.loginUser(loginInfo,password);

        if(user==null){
            return "error";
        }else{
            request.getSession().setAttribute("user",user);
            System.out.println(user);
            return user.getRole();
        }
    }

    @RequestMapping(value = "/register",method ={RequestMethod.POST})
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("gender") String gender,
            @RequestParam("telephone") String telephone,
            @RequestParam("introduce") String introduce,
                    HttpServletRequest request

    ) {
        User user = new User(username, password, gender, email, telephone, introduce);
        user=userService.regUser(user);
        //user是我们登录的结果
        //user要放入session里
        if(user!=null) {
            request.getSession().setAttribute("user", user);
            return "registersuccess";
        }else{
            request.setAttribute("error","注册失败了，请重试。");
            return "register";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/checkuserinfo",method ={RequestMethod.GET})
    public String checkuserinfo(
            @RequestParam("info") String info,
            @RequestParam("type") String type
    ){
        boolean result=userService.checkInfo(info,type);
        return result+"";
    }

    ///图片验证码///
    //保存所有的成语
    private List<String> words=new ArrayList<String>();

    private void initWords(HttpServletRequest request) {
        if(words.size()==0){
            String path=request.getSession().getServletContext().getRealPath("/WEB-INF/new_words.txt");
            BufferedReader reader=null;

            try {
                reader=new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
                String line;
                for(;(line=reader.readLine())!=null;){
                    words.add(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
    @RequestMapping("/textcode")
    public void textcode(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

       String checkcode= (String) request.getSession().getAttribute("checkcode");
       response.setContentType("text/html;charset=utf-8");
       response.getWriter().write(checkcode);
    }


    @RequestMapping("/imagecode")
    public void imagecode(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        initWords(request);
        //设置http响应内容的mime类型
        response.setContentType("image/jpeg");
        //随机对象
        Random random=new Random();
        int index=random.nextInt(words.size());
        //随机的那一个成语
        String word=words.get(index);
        request.getSession().setAttribute("checkcode",word);
        //定义验证码
        int width=120;
        int height=30;


        //用java生成一个图片
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取图片的绘制对象
        Graphics graphics=bufferedImage.getGraphics();
        //验证码分为三个部分
        //底色、验证码的文字颜色、上方的噪点颜色

        //首先画底色
        //底色比较浅
        Color color=getRandomColor(200,249);
        //设置底色
        graphics.setColor(color);
        //fill是填充绘制
        graphics.fillRect(0,0,width,height);

        //设置白色
        graphics.setColor(Color.WHITE);
        //描边绘制
        graphics.drawRect(0,0,width,height);

        //绘制随机的验证码
        Graphics2D graphics2D= (Graphics2D) graphics;

        //设置即将绘制出来的文字的样式
        graphics2D.setFont(new Font("宋体",Font.BOLD,18));
        int x=10;
        for (int i = 0; i < word.length(); i++) {
            //设置颜色比较深
            graphics2D.setColor(getRandomColor(20,110));
            //设置一个旋转角度
            int angle=random.nextInt(60)-30;
            //转化成弧度制
            double theta=Math.PI/180*angle;

            //获取到某个单个字
            char c=word.charAt(i);
            //把字画到图片上
            //graphics2D.rotate();
            graphics2D.rotate(theta,x,20);
            graphics2D.drawString(c+"",x,20);
            graphics2D.rotate(-theta,x,20);
            x+=30;
        }


        //噪点
        graphics.setColor(getRandomColor(160,200));
        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < 30; i++) {
            //每一个线的长度要有限制
            //每一条线的长度不会超过sqrt(288)
            x1=random.nextInt(width);
            y1=random.nextInt(height);
            x2=random.nextInt(12)+x1;
            y2=random.nextInt(12)+y1;
            graphics.drawLine(x1,y1,x2,y2);
        }
        //释放资源
        graphics.dispose();
        //输出到网页上
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
    }
    private Color getRandomColor(int fc,int ec){
        Random random=new Random();
        int r=fc+random.nextInt(ec-fc);
        int g=fc+random.nextInt(ec-fc);
        int b=fc+random.nextInt(ec-fc);
        return new Color(r,g,b);
    }



    @RequestMapping("/modifyUserInfo")
    public String modifyUserInfo(
            @RequestParam("uid") Long uid,
            @RequestParam("password") String password,
            @RequestParam("gender") String gender,
            @RequestParam("telephone") String telephone,
            HttpServletRequest request
    ){
        User user= (User) request.getSession().getAttribute("user");
        String oldPassword=user.getPassword();

        user.setPassword(password);
        user.setGender(gender);
        user.setTelephone(telephone);

        user=userService.editUser(user,oldPassword);

        if(user!=null) {
            return "modifyUserInfoSuccess";
        }else{
            return "modifyUserInfo";
        }
    }


    @RequestMapping("signout")
    public String signOut(

            HttpServletRequest request
    ){
        request.getSession().removeAttribute("user");
        return "index";
    }

}
