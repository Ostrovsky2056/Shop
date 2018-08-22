<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/pattern.js"></script>
<script type="text/javascript">
	function changeImage() {

		document.getElementById("img").src = "${pageContext.request.contextPath}/imagecode?time="
				+ new Date().getTime();
	}
	//校验数据分为前端后端
	//为了方便，我们使用jquery

	//校验email
	var tempemail=false;
	function onemailchange(){
	    //jquery中取值，value，innerHTML，text
		//val()   html()   text()
	    var c_email=$("#email").val();
		if(!ePattern.test(c_email)){
		    //邮箱格式不正确
			$("#checkemailfont").html("邮箱格式不正确");
			$("#checkemailfont").css("color","red");
			return false;
		}
		//格式正确
		var url="checkuserinfo?type=email&info="+c_email;
		$.get(url,function (data,status){
			if(status=="success"){
			    if("true"==data){
			        //邮箱可用
                    $("#checkemailfont").html("邮箱可用");
                    $("#checkemailfont").css("color","green");
                    tempemail=true;
				}else{
			        //邮箱已经存在
                    $("#checkemailfont").html("邮箱已经被注册");
                    $("#checkemailfont").css("color","red");
                    tempemail=false;
				}
			}
		});
		//前端校验通过
		return true;
	}

	//校验用户名
    var tempusername=false;
    function onusernamechange(){
        //jquery中取值，value，innerHTML，text
        //val()   html()   text()
        var c_username=$("#username").val();
        if(!uPattern.test(c_username)){
            //邮箱格式不正确
            $("#checkusernamefont").html("用户名格式不正确");
            $("#checkusernamefont").css("color","red");
            return false;
        }
        //格式正确
        var url="checkuserinfo?type=username&info="+c_username;
        $.get(url,function (data,status){
            if(status=="success"){
                if("true"==data){
                    //邮箱可用
                    $("#checkusernamefont").html("用户名可用");
                    $("#checkusernamefont").css("color","green");
                    tempusername=true;
                }else{
                    //邮箱已经存在
                    $("#checkusernamefont").html("用户名已经被注册");
                    $("#checkusernamefont").css("color","red");
                    tempusername=false;
                }
            }
        });
        //前端校验通过
        return true;
    }

	//校验电话号
    var temptelephone=false;
    function ontelephonechange(){
        //jquery中取值，value，innerHTML，text
        //val()   html()   text()
        var c_telephone=$("#telephone").val();
        if(!mPattern.test(c_telephone)){
            //邮箱格式不正确
            $("#checktelephonefont").html("手机号格式不正确");
            $("#checktelephonefont").css("color","red");
            return false;
        }
        //格式正确
        var url="checkuserinfo?type=telephone&info="+c_telephone;
        $.get(url,function (data,status){
            if(status=="success"){
                if("true"==data){
                    //邮箱可用
                    $("#checktelephonefont").html("手机号可用");
                    $("#checktelephonefont").css("color","green");
                    temptelephone=true;
                }else{
                    //邮箱已经存在
                    $("#checktelephonefont").html("手机号已经被注册");
                    $("#checktelephonefont").css("color","red");
                    temptelephone=false;
                }
            }
        });
        //前端校验通过
        return true;
    }

    function onpasswordchange(){
        var c_password=$("#password").val();
        if(!pPattern.test(c_password)){
            //邮箱格式不正确
            $("#checkpasswordfont").html("密码至少6位， 必须包含大写、小写、数字、特殊字符（!@#$%^&*?）");
            $("#checkpasswordfont").css("color","red");
            return false;
        }else{
            $("#checkpasswordfont").html("密码格式正确");
            $("#checkpasswordfont").css("color","green");
		}
	}
    function onrepasswordchange(){
        var c_repassword=$("#repassword").val();
        var c_password=$("#password").val();
        if(!(c_password==c_repassword)){
            //邮箱格式不正确
            $("#checkrepasswordfont").html("两次密码输入不一致");
            $("#checkrepasswordfont").css("color","red");
            return false;
        }else{
            $("#checkrepasswordfont").html("两次密码输入一致");
            $("#checkrepasswordfont").css("color","green");
            return true;
		}
    }

    //校验
	var tempcheckcode=false;
	function oncheckcodechange(){
        //验证码输入校验
		//jquery中取值，value，innerHTML，text
        //val()   html()   text()
        var c_checkcode=$("#checkcode").val();
        if(!(c_checkcode.length==4)){
            //邮箱格式不正确
            $("#checkcheckcodefont").html("验证码不正确");
            $("#checkcheckcodefont").css("color","red");
            return false;
        }
        //格式正确
        var url="textcode";
        $.get(url,function (data,status){
            if(status=="success"){

                if(c_checkcode==data){
                    //邮箱可用
                    $("#checkcheckcodefont").html("验证码正确");
                    $("#checkcheckcodefont").css("color","green");
                    tempcheckcode=true;
                }else{
                    //邮箱已经存在
                    $("#checkcheckcodefont").html("验证码不正确");
                    $("#checkcheckcodefont").css("color","red");
                    tempcheckcode=false;
                }
            }
        });
        //前端校验通过
        return true;
	}

	//点击注册时，统一校验所有的内容
	function onsubmitclick(){
	    var bool=onemailchange()&&onusernamechange()&&ontelephonechange()
		&&oncheckcodechange()&&onpasswordchange()&&onrepasswordchange();

		bool=bool&&tempcheckcode&&tempemail&&temptelephone&&tempusername;
		return bool;

	}

</script>
</head>


<body class="main" onload="changeImage()">
	<%@include file="head.jsp"%>
	<%--导入头 --%>
	<%@include file="menu_search.jsp"%><%--导入导航条与搜索 --%>

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>新会员注册</h1>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">会员邮箱：</td>
								<td style="width:40%">
								<input type="text" class="textinput" id="email"onkeyup="onemailchange()"
									name="email"  /></td>
								<td><font color="#999999" id="checkemailfont">请输入有效的邮箱地址</font></td>
							</tr>
							<tr>
								<td style="text-align:right">会员名：</td>
								<td>
									<input type="text" class="textinput" name="username"
										   id="username"onkeyup="onusernamechange()"

									/>
								</td>
								<td><font color="#999999" id="checkusernamefont">用户名设置至少6位</font></td>
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td><input type="password" class="textinput"
									name="password" id="password" onkeyup="onpasswordchange()" /></td>
								<td><font color="#999999" id="checkpasswordfont">密码设置至少6位</font></td>
							</tr>
							<tr>
								<td style="text-align:right">重复密码：</td>
								<td><input type="password" class="textinput"
									name="repassword" id="repassword" onkeyup="onrepasswordchange()" /></td>
								<td><font color="#999999" id="checkrepasswordfont">密码设置至少6位</font></td>
							</tr>
							<tr>
								<td style="text-align:right">性别：</td>
								<td colspan="2">&nbsp;&nbsp;<input type="radio"
									name="gender" value="男" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
									name="gender" value="女" /> 女</td>
							</tr>
							<tr>
								<td style="text-align:right">联系电话：</td>
								<td colspan="1"><input type="text" class="textinput"
													   id="telephone"onkeyup="ontelephonechange()"
									 name="telephone" /></td>
								<td>
									<font color="#999999" id="checktelephonefont">请输入手机号</font>

								</td>
							</tr>
							<tr>
								<td style="text-align:right">个人介绍：</td>
								<td colspan="2"><textarea class="textarea" name="introduce"></textarea>
								</td>
							</tr>

						</table>



						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">输入校验码：</td>
								<td style="width:50%"><input type="text" class="textinput"
								id="checkcode" onkeyup="oncheckcodechange()"
								/>
								</td>
								<td><font color="#999999" id="checkcheckcodefont">请输入验证码</font></td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="2" style="width:50%"><img
									src="${pageContext.request.contextPath}/imageCode" width="180"
									height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
								</td>
							</tr>
						</table>



						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center"><input
									type="image" src="images/signup.gif" name="submit" onclick="return onsubmitclick();" border="0">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" /></td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a></td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2015 &copy; eShop All Rights RESERVED.</b> </font></td>
			</tr>
		</table>
	</div>


</body>
</html>
