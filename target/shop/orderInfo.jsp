<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.qfedu.shop.pojo.Order" %>
<%@ page import="com.qfedu.shop.pojo.OrderItem" %>
<%@ page import="java.math.BigDecimal" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>


<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;;订单详细信息
					</div>


					<%
						Order order= (Order) request.getAttribute("order");


					%>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:<%out.print(order.getId());%></p></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="40%">商品名称</td>
													<td width="10%">价格</td>
													<td width="10%">数量</td>
													<td width="10%">小计</td>

												</tr>
											</table>
											<table width="100%" border="0" cellspacing="0">

												<%
													List<OrderItem> items=order.getItems();
													for (int i = 0; i < items.size(); i++) {
														OrderItem item=items.get(i);
														out.print("<tr><td width=\"10%\">"+(i+1)+"</td>");
														out.print("<td width=\"40%\">"+item.getName()+"</td>");
														out.print("<td width=\"10%\">"+item.getPrice()+"</td>");
														out.print("<td width=\"10%\">"+item.getCount()+"</td>");
														System.out.println(item);
														System.out.println(item.getPrice());
														System.out.println(item.getPrice().multiply(new BigDecimal(1)));
														System.out.println(item.getPrice().multiply(new BigDecimal(item.getCount())));

														out.print("<td width=\"10%\">"+item.getPrice().multiply(new BigDecimal(item.getCount()))+"</td></tr>");
													}

												%>






											</table>


											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">合计：&nbsp;&nbsp;<%out.print(order.getPrice());%></font></td>
												</tr>
											</table>

											<p>
												收货地址：<%out.print(order.getReceiverAddress());%><br />
												收货人：<%out.print(order.getReceiverName());%><br />
												联系方式：<%out.print(order.getReceiverPhone());%>

											</p>
											<hr>
											<p style="text-align:right">
												<a href="pay?orderId=<%out.print(order.getId());%>"><img src="images/gif53_029.gif" width="204"
													height="51" border="0" /> </a>
											</p>
										</td>
									</tr>
								</table>
							</td>


						</tr>


					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
