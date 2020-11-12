<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${pageContext.request.contextPath }/view/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/view/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/view/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>
  <% String username=(String)session.getAttribute("username"); %>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">MES-System</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><%=username %><span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="../login.jsp"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
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
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="${pageContext.request.contextPath}/view/jsp/main.jsp"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="#" style="color:red;"><i class="glyphicon glyphicon-user"></i> 信息维护</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath}/ImageListServlet"><i class="glyphicon glyphicon-king"></i> 图片管理</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/FlagServlet"><i class="glyphicon glyphicon-lock"></i> 用户标签</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/LevelServlet"><i class="glyphicon glyphicon-lock"></i> 层级关系</a> 
							</li>
							<li style="height:30px;">
								<a href=permission.html"><i class="glyphicon glyphicon-lock"></i> 活动分析</a> 
							</li>
						</ul>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" name="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <form id="userForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allSelBox"></th>
                  <th width="180">openID</th>
                  <th width="180">昵称</th>
                  <th width="100">性别</th>
                  <th width="100">城市</th>
                  <th width="100">国家</th>
                  <th width="100">省份</th>
                  <th width="300">关注时间</th>
                </tr>
              </thead>
	              <tbody id="">
	             	<c:forEach items="${users}" var="user" varStatus="st">
	                	  <tr>
	                	  	<td>${user.id}</td>
	                	  	<td><input type='checkbox' name='userid' value='"+user.id+"'></td>
	                	 	<td>${user.openid}</td>
	                	 	<td>${user.nickname}</td>
	                	 	<td>${user.sex}</td>
	                	 	<td>${user.city}</td>
	                	 	<td>${user.country}</td>
	                	 	<td>${user.province}</td>
	                	 	<td>${user.subscribe_time}</td>
	                	  </tr>	
	               </c:forEach>                	 
	              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="9" align="center">
						
						<a href="/wechatdemo/UserListServlet?start=0">首  页</a>
							<a href='/wechatdemo/UserListServlet?start=${pre}'>上一页</a>
							<a href='#'> ${page}/${pages}</a>
							<a href='/wechatdemo/UserListServlet?start=${next}'>下一页</a>
							<a href='/wechatdemo/UserListServlet?start=${last}'>末页</a>
					
					 </td>
				 </tr>
			  </tfoot>
            </table>
           </form>  
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="../../jquery/jquery-2.1.1.min.js"></script>
    <script src="../../bootstrap/js/bootstrap.min.js"></script>
 	<script src="../../script/docs.min.js"></script>
 	<script src="../../layer/layer.js"></script>
  </body>
</html>
