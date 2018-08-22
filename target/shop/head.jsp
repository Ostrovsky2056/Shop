<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.qfedu.shop.pojo.User" %>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td><a href="index.jsp"><img src="images/logo.png"
					width="95" height="30" border="0" /> </a></td>
			<td style="text-align:right"><img src="images/cart.gif"
				width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a
				href="showcar">购物车</a> | <a href="#">帮助中心</a> |
				<%--  如果没登录，就跳转到login.jsp
				 如果是一个普通用户登录，就跳转到myAccount.jsp
				 如果是一个管理员登录，就跳转到admin/login/home.jsp
				 --%>
				<%
				User user  = (User) session.getAttribute("user");
				String url=null;
				if(user==null){
					url="login.jsp";
				}else if("admin".equals(user.getRole())){
					url="admin/login/home.jsp";
				}else if("user".equals(user.getRole())){
				    url="myAccount.jsp";
				}
				%>
				<a href="<%out.print(url);%>">我的帐户</a>
				| <a href="register.jsp">新用户注册</a></td>
		</tr>
	</table>
</div>