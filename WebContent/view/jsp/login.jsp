<%@page pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/css/login.css">
<script type="text/javascript">
	//用户名密码判断
	function cheeklog() {
		if (document.main.loginacct.value.length == 0) {
			alert("用户名不能为空，请重试！");
			document.main.id.focus;
			return false;
		} else if (document.main.userpasswd.value.length == 0) {
			alert("密码不能为空，请重试！");
			document.main.password.focus;
			return false;
		}
		document.main.submit();
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="#" style="font-size: 32px;">微信后台管理系统</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">
		<%-- <h1 style="color:red;">${param.errorMsg}</h1> --%>
		<form id="loginForm" name="main" action="${pageContext.request.contextPath}/LoginServlet" method="post"
			class="form-signin" role="form">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-user"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="loginacct"
					name="loginacct" placeholder="请输入登录账号" autofocus> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" class="form-control" id="userpasswd"
					name="userpasswd" placeholder="请输入登录密码" style="margin-top: 10px;">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select class="form-control" name="select">
					<option value="member">会员</option>
					<option value="user">管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label> <br> <label> 忘记密码 </label> <label style="float: right">
					<a href="reg.html">我要注册</a>
				</label>
			</div>
			<input class="btn btn-lg btn-success btn-block" type="submit"
				value="登录" onClick="cheeklog()" />
		</form>
	</div>
	<script
		src="${pageContext.request.contextPath}/view/jquery/jquery-2.1.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/view/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/view/ui/login.js"></script>
</body>
</html>