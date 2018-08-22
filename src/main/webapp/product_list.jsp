<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<script type="text/javascript" src="js/my.js"></script>

<script type="text/javascript">
	var curr_pageno=-1;
	function loadData(pageNo){
	    //获取对象
		var xmlhttp=getXMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
		    if(xmlhttp.readyState==4&&xmlhttp.status==200){
					var text=xmlhttp.responseText;
					var jsonObject=JSON.parse(text);
                curr_pageno=jsonObject.pageNo;
                //html1就是一个table的innerHTML
                var html1="";
                for (var i = 0; i < jsonObject.pageSize; i++) {
					if(i%4==0){
					    //一行开始了
                        html1=html1+"<tr>";
					}

					//中间有td，来表示书
					if(i<jsonObject.items.length){
					html1=html1+"<td><div class=\"divbookpic\">";
                    html1=html1+"<p><a href=\"showone?id="+jsonObject.items[i].id+"\">";
                    html1=html1+"<img src=\""+jsonObject.items[i].img_url+"\" width=\"115\" height=\"129\" border=\"0\" /></a>";
                    html1=html1+"</p></div><div class=\"divlisttitle\">";
                    html1=html1+"<a href=\"product_info.jsp\">书名:"+jsonObject.items[i].name+"<br />售价:"+jsonObject.items[i].price+" </a></div></td>";
                    }
					if(i%4==3){
					    //一行结束了
						html1=html1+"</tr>"
					}

                }
                //使得table中的innerHTML是html1
				document.getElementById("booklist").innerHTML=html1;
				//按钮逻辑
				var html2="";
				//上一页
				if(jsonObject.pageNo==1){
				    html2=html2+"<li class=\"disablepage\">上一页 &lt;&lt;</li>";
				}else{
				    html2=html2+"<li class=\"nextpage\"><a href=\"#\" onclick='return gopage(-1)'>上一页&lt;&lt;</a></li>"
				}
				//页码按钮
                for (var i = 0; i < jsonObject.pageCount; i++) {
				    if(jsonObject.pageNo==(i+1)){
				        //当前页不可点击
                        html2=html2+"<li class=\"currentpage\">"+(i+1)+"</li>";
					}else{
				        //其他页可以点击
                        html2=html2+"<li><a href=\"#\" onclick='return loadData("+(i+1)+")'>"+(i+1)+"</a></li>";
					}
                }
				

				//下一页
				if(jsonObject.pageNo==jsonObject.pageCount){
                    html2=html2+"<li class=\"disablepage\">下一页 &gt;&gt;</li>";
				}else{
                    html2=html2+"<li class=\"nextpage\"><a href=\"#\" onclick='return gopage(1)'>下一页&gt;&gt;</a></li>";
				}


				document.getElementById("buttons").innerHTML=html2;

			}
		};
		var url="showProductPage?pageNo="+pageNo+"&pageSize=8";
		xmlhttp.open("get",url);
		xmlhttp.send();
		return false;
	}

	function gopage(n){
	    loadData(curr_pageno+n);
	    return false;
	}
    loadData(1);

</script>
<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;计算机&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共100种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist" id="booklist">
									<%--<tr>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/101.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/102.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/103.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/104.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>


									</tr>--%>
								</table>

								<%--<table cellspacing="0" class="booklist">
									<tr>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/101.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/102.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/103.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>
										<td>

											<div class="divbookpic">
												<p>
													<a href="product_info.jsp"><img src="bookcover/104.jpg" width="115"
														height="129" border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>
											</div></td>


									</tr>
								</table>--%>















								<div class="pagination">
									<ul id="buttons"><%--<li class="disablepage">上一页 &lt;&lt;</li>
										<li class="currentpage">1</li>
										<li><a href="product_info.jsp">2</a>
										</li>
										<li><a href="product_info.jsp">3</a>
										</li>
										<li><a href="product_info.jsp">4</a>
										</li>
										<li class="nextpage"><a href="product_info.jsp">下一页&gt;&gt;</a>
										</li>--%>
									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
