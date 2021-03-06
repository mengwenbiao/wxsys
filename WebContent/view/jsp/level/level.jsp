<%@page pageEncoding="UTF-8"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
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
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">微信后台管理系统</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				<!-- 用户名 -->
				<%String name=(String)session.getAttribute("username");%>
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><%=name %><span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="jsp/login.jsp"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
						<a href="/wechatdemo/view/jsp/main.jsp"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理 <span class="badge" style="float:right">5</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath}/UserListServlet"><i class="glyphicon glyphicon-user"></i> 信息维护</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/ImageListServlet"><i class="glyphicon glyphicon-king"></i> 图片管理</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/FlagServlet"><i class="glyphicon glyphicon-lock"></i> 用户标签</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/LevelServlet" style="color:red;"><i class="glyphicon glyphicon-lock"></i> 层次关系</a> 
							</li>
							<li style="height:30px;">
								<a href="#"><i class="glyphicon glyphicon-lock"></i> 活动分析</a> 
							</li>
						</ul>
					</li>										
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
			  
<%request.getParameter("querySelect"); %>			  
<form class="form-inline" action="${pageContext.request.contextPath}/LevelServlet" role="form" style="float:left;" method="post" >
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" name="querySelect" class="form-control has-success" type="text" value="${querySelect}" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>



<!-- 新增 -->
<!-- <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<a href="view/jsp/level/add.jsp"><button type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button></a> -->
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <form id="userForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allSelBox"></th>
                  <th>用户昵称</th>
                  <th>上级昵称</th>
                  <th>等级</th>
                  <!-- <th width="100">操作</th> -->
                </tr>
              </thead>
	              <tbody id="userData">
	              		  <c:forEach items="${levels}" var="level" varStatus="st">
	                	  <tr>
	                	  	<!-- id -->
	                	  	<td>${level.id}</td>
	                	  	<td><input type='checkbox' name='levelid' value='"+user.id+"'></td>
	                	 	<td>${level.nickname}</td>
	                	 	<td>${level.superNickname}</td>
	                	 	<td>${level.ranking}</td>
	                	 	<%-- <td>
    							<a href="getLevel?id=${level.id}"><button type='button' class='btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>
    							</a>
    							<a href="view/jsp/level/add.jsp"><button type='button'  class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>
    							</a>
    							<a href="deleteLevel?id=${level.id}"><button type='button'  class='btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>
    							</a>
    						</td> --%>
	                	  </tr>
	               	      </c:forEach>         	 
	              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							<li><a href="LevelServlet?start=0">首  页</a></li>
							<li><a href="LevelServlet?start=${pre}">上一页</a></li>
							<li  class=''><a href="#">${page}/${sum}</a></li>
							<!-- <li  class='active'><a href="UserServlet?start=5">2</a></li>
							<li  class=''><a href="UserServlet?start=10">3</a></li> -->
							<li><a href="LevelServlet?start=${next}">下一页</a></li>
							<li><a href="LevelServlet?start=${last}">末页</a></li>
						</ul>
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

    <script src="${pageContext.request.contextPath }/view/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/view/bootstrap/js/bootstrap.min.js"></script>
 	<script src="${pageContext.request.contextPath }/view/script/docs.min.js"></script>
 	<script src="${pageContext.request.contextPath }/view/layer/layer.js"></script>
  </body>
</html>
