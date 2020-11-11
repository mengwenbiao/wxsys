<%@page pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/main.css">
<link re·l="stylesheet" href="${pageContext.request.contextPath}/view/css/doc.min.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>

<body>
	<%
		String username = (String) session.getAttribute("username");
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="user.html">后台管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<div class="btn-group">
							<button type="button"
								class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i><%=username%>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#"><i class="glyphicon glyphicon-cog"></i>
										个人设置</a></li>
								<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
										消息</a></li>
								<li class="divider"></li>
								<li><a href="../login.jsp"><i
										class="glyphicon glyphicon-off"></i> 退出系统</a></li>
							</ul>
						</div>
					</li>
					<li style="margin-left: 10px; padding-top: 8px;">
						<button type="button" class="btn btn-default btn-danger">
							<span class="glyphicon glyphicon-question-sign"></span> 帮助
						</button>
					</li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<ul style="padding-left: 0px;" class="list-group">
						<li class="list-group-item tree-closed"><a href="${pageContext.request.contextPath}/view/jsp/main.jsp"><i
								class="glyphicon glyphicon-dashboard"></i>控制面板</a></li>
						<li class="list-group-item"><span><i
								class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理 <span
								class="badge" style="float: right">5</span></span>
							<ul style="margin-top: 10px;">
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath}/view/jsp/user.jsp" ><i class="glyphicon glyphicon-user"></i>信息维护</a> 
							</li>
							<li style="height:30px;">
								<a href="#"><i class="glyphicon glyphicon-king"></i>图片管理</a> 
							</li>
							<li style="height: 30px;">
								<a href="/wechatdemo/FlagServlet" style="color: red;"><i class="glyphicon glyphicon-user"></i>用户标签</a>
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/LevelServlet"><i class="glyphicon glyphicon-lock"></i>层级关系</a> 
							</li>
							<li style="height:30px;">
								<a href="#"><i class="glyphicon glyphicon-lock"></i>活动分析</a> 
							</li>
								
							</ul></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">数据列表</a></li>
					<li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form role="form" action="/Jspexamdemo/UpdateFServlet" method="post">
							<!-- <input type="hidden" name="id" value="${users.id}" /> -->
							<div class="form-group">
								 <label for="exampleInputPassword1">ID</label> 
								 <input type="text" class="form-control" id="id" name="id" value="${flags.id}">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">用户名称</label> <input
									type="text" class="form-control" id="username" name="username"
									value="${flags.username}" placeholder="请输入用户名称">
							</div>
							<div class="form-group">
								 <label
									for="exampleInputPassword1">促销</label> <input type="text"
									class="form-control" id="sale" name="sale" value="${flags.sale}"
									placeholder="促销">
							</div>
							
							<div class="form-group">
								<label for="exampleInputEmail1">赠送</label> <input type="text"
									class="form-control" id="free" name="free" value="${flags.free}"
									placeholder="赠送">
							</div>
							<div class="form-group">
								 <label
									for="exampleInputPassword1">团购</label> <input type="text"
									class="form-control" id="teamsale" name="teamsale" value="${flags.teamsale}"
									placeholder="团购">
							</div>
							<input type="submit" value="修改" class="btn btn-success"/>
							<%-- <a href="${pageContext.request.contextPath}/UpdateServlet">
								<button type="button" id="insertBtn" class="btn btn-success">
									<i class="glyphicon glyphicon-plus"></i> 新增
								</button>
							</a> --%>
							<!-- <button type="button" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button> -->
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="bs-callout bs-callout-info">
						<h4>测试标题1</h4>
						<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
					</div>
					<div class="bs-callout bs-callout-info">
						<h4>测试标题2</h4>
						<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
					</div>
				</div>
				<!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/view/jquery/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/script/docs.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/layer/layer.js"></script>
</body>
</html>