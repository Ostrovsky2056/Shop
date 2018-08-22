<%@ page import="com.qfedu.shop.pojo.Product" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/check.js"></script>

</HEAD>
<body>
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath}/admin/doedit" method="post"
		>
		<input type="hidden" name="id" value="${product.id}"/>
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>编辑商品</STRONG> </strong></td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="name" class="bg" value="${product.name}" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="price" class="bg" value="${product.price}" /></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品数量：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pnum" class="bg" value="${product.pnum}" /></td>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品类别：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="category"
					id="category">
					<%
						Product product= (Product) request.getAttribute("product");
						String category=product.getCategory();
						String [] categories={"文学","生活","计算机","外语","经营","励志","社科",
								"学术","少儿","艺术","原版","科技","考试","生活百科"};
						for (int i = 0; i < categories.length; i++) {
							out.print("<option value=\""+categories[i]+"\"");
							//selected="selected"
							if(category.equals(categories[i])){
							    out.print("selected=\"selected\"");
							}
							out.print(">"+categories[i]+"</option>");
						}
					%>
						

				</select></td>
			</tr>


			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品图片：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3"><input
					type="hidden" name="img_url" id="img_url" size="30" value="${product.img_url}" /></td>
			</tr>
			<TR>
				<TD class="ta_01" align="center" bgColor="#f5fafe">商品描述：</TD>
				<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
						name="description" cols="30" rows="3" style="WIDTH: 96%">${product.description}</textarea>
				</TD>
			</TR>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4"><input type="submit"
					class="button_ok" value="确定"> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>



					<input type="reset" value="重置" class="button_cancel"> <FONT
					face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span></td>
			</tr>
		</table>
	</form>
	<script>
        function getXMLHttpRequest(){
            var xmlhttp;
            if(window.XMLHttpRequest){
                xmlhttp=new XMLHttpRequest();
            }else{
                //IE6以前
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            return xmlhttp;
        }

        function onfileChanged(){
            //form,showphoto,photo
            if(document.getElementById("photo").value==""){
                //什么也没选，就什么也不做
                return ;
            }
            var fileForm=document.getElementById("file");

            //用ajax把图片上传到服务器
            var xmlhttp=getXMLHttpRequest();
            xmlhttp.onreadystatechange=function(){
                if(xmlhttp.readyState==4&&xmlhttp.status==200){
                    var imgPath=xmlhttp.responseText;
                    if("error"==imgPath){
                        imgPath="../images/error.png";
                    }
                    document.getElementById("img_url").value=imgPath;
                    //document.getElementById("showphoto").src=imgPath;
                }
            };
            xmlhttp.open("post",fileForm.action);
            xmlhttp.send(new FormData(fileForm));

        }

	</script>
	<div  style="position: absolute;top: 160px;left: 200px;">
		<form id="file" action="${pageContext.request.contextPath}/fileupload" method="post" enctype="multipart/form-data">



			<input type="file" id="photo" name="icon" onchange="onfileChanged();"/>


		</form>
	</div>



</body>
</HTML>