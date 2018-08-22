<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.qfedu.shop.bean.ShoppingCar" %>
<%@ page import="com.qfedu.shop.pojo.Car" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />

<script type="text/javascript">
	function onbuttonclick(a,b){
	    var countInput=document.getElementById("count"+a);

	    var n=new Number(countInput.value)+b;
	    if(n<0){
	        return ;
		}
		//数据更新
		//1、count
        countInput.value=n;
	    //2、price*count
		var priceA=document.getElementById("price"+a).innerHTML;
		var priceCount=document.getElementById("priceCount"+a);
        priceCount.innerHTML=new Number(priceCount.innerHTML)+priceA*b;
		//3、sum
		var sumFont=document.getElementById("sum");
        sumFont.innerHTML=new Number(sumFont.innerHTML)+priceA*b;
	}

	function removeitem(n){
		var trn=document.getElementById("tr"+n);

		//更新sum
        var sumFont=document.getElementById("sum");
        var priceCount=document.getElementById("priceCount"+n);
        sumFont.innerHTML=new Number(sumFont.innerHTML)-new Number(priceCount.innerHTML);
        //一定是最后算完再没。
        trn.parentNode.removeChild(trn);
	    return false;
	}

	function onjxclick(){
	    var myForm=document.getElementById("form");
        myForm.action="goshopping"
		myForm.submit();
	    return false;
	}
    function onbuyclick(){
        var myForm=document.getElementById("form");
        myForm.action="goorder"
        myForm.submit();
        return false;
    }
</script>
</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="30%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">取消</td>
												</tr>
											</table> 
												<table id="tableparent" width="100%" border="0" cellspacing="0">
													<form id="form" method="post" action="#">
													<%
														ShoppingCar shoppingCar= (ShoppingCar) request.getAttribute("car");
														List<Car> items=shoppingCar.getItems();
														BigDecimal sum=new BigDecimal(0);
														for (int i = 0; i < items.size(); i++) {
														    Car car=items.get(i);
															out.print("<tr id='tr"+(i+1)+"'><td width=\"10%\">"+(i+1)+"</td>");
															out.print("<td width=\"30%\">"+car.getName()+"</td><td width=\"10%\" id='price"+(i+1)+"'>"+car.getPrice()+"</td><td width=\"20%\">");
															out.print("<input type=\"button\" value='-' onclick='onbuttonclick("+(i+1)+",-1)' style=\"width:20px\">");
															out.print("<input name=\"count\" type=\"text\" id='count"+(i+1)+"'  value=\""+car.getCount()+"\" style=\"width:40px;text-align:center\" />");
															out.print("<input type='hidden' name='pid' value='"+car.getPid()+"' />");
															out.print("<input type=\"button\" value='+' onclick='onbuttonclick("+(i+1)+",1)' style=\"width:20px\"></td>");
															out.print("<td width=\"10%\">"+car.getPnum()+"</td>");
															out.print("<td width=\"10%\" id='priceCount"+(i+1)+"'>"+car.getPrice().multiply(new BigDecimal(car.getCount()))+"</td>");
															out.print("<td width=\"10%\"><a href=\"#\" onclick='return removeitem("+(i+1)+")' style=\"color:#FF0000; font-weight:bold\">X</a></td></tr>");
															sum=sum.add(car.getPrice().multiply(new BigDecimal(car.getCount())));
														}

													%>

													</form>








												</table>
												


											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;<font id="sum"><%out.print(sum);%></font>元</font>
													</td>
												</tr>
											</table>
											<div style="text-align:right; margin-top:10px">
												<a
													href="product_list.jsp"><img
													src="images/gwc_jx.gif" onclick="return onjxclick();" border="0" /> </a>

												&nbsp;&nbsp;&nbsp;&nbsp;<a
													href="order.jsp"><img
													src="images/gwc_buy.gif" onclick="return onbuyclick();" border="0" /> </a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
