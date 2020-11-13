<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="zh-CN">
  <head>
 <!--   equest.getSchema()可以返回当前页面使用的协议，就是上面例子中的“http” 
request.getServerName()可以返回当前页面所在的服务器的名字，就是上面例子中的“localhost" 
request.getServerPort()可以返回当前页面所在的服务器使用的端口,就是80， 
request.getContextPath()可以返回当前页面所在的应用的名字，就是上面例子中的项目名jspexam  -->
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>     
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-idth, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="<%=basePath%>/view/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/view/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath%>/view/css/main.css">
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
				<%String name=(String)session.getAttribute("username");%>
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><%=name %><span class="caret"></span>
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
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理<span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="/wechatdemo/UserListServlet"><i class="glyphicon glyphicon-user"></i> 信息维护</a> 
							</li>
							<li style="height:30px;">
								<a href="${pageContext.request.contextPath}/ImageListServlet" style="color:red;"><i class="glyphicon glyphicon-lock"></i> 图片管理</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/FlagServlet"><i class="glyphicon glyphicon-king"></i> 用户标签</a> 
							</li>
							<li style="height:30px;">
								<a href="/wechatdemo/LevelServlet"><i class="glyphicon glyphicon-lock"></i> 层级关系</a> 
							</li>
							
							<li style="height:30px;">
								<a href="/wechatdemo/LevelServlet"><i class="glyphicon glyphicon-lock"></i> 活动分析</a> 
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
			  
			<!--模糊查询模块  -->
			<form class="form-inline" role="form" style="float:left;">
			  <div class="form-group has-feedback">
			    <div class="input-group">
			      <div class="input-group-addon">查询条件</div>
			      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件" name="query">
			    </div>
			  </div>
			  <button id="queryBtn" type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
			</form>
			<!-- <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button> -->
			<!-- <a href="jsp/poster/addposter.jsp"><button type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 新增</button></a> -->
			<br>
			
			<!--数据显示区域  -->
			<hr style="clear:both;">
			 <div class="table-responsive">
			  	<form id="userForm">
			   <table class="table  table-bordered">
				   <thead>
				     <tr >
				       <th width="30">id</th>
				       <th>主题名</th>
				       <th>素材图片</th>
				       <th>操作</th>
				     </tr>
				   </thead>
			<tbody id="userData">
	          <c:forEach items="${images}" var="image" varStatus="st">
			      <tr>
			          <td>${image.id}</td>
			          <td>${image.theme}</td>
			         <%--  <td><img height="40px" src="img/Templateimg/${image.id}.jpg"></td> --%><!--  -->
			         <td><img height="40px" src="img/Templateimg/${image.id}.jpg"></td>
			          <!-- 这里向类传值 -->
			          <td>
			         	 <a href="UpdateImageServlet?id=${image.id }"><i class='glyphicon glyphicon-check'></i></a>
			             <%-- <a href="EditUserServlet?id=${poster.id}"><i class='glyphicon glyphicon-pencil'></i></a> --%>
			          	 <a href="DeleteImageServlet?id=${image.id}"><i class='glyphicon glyphicon-remove'></i></a>
			          </td>
			      </tr>
			  </c:forEach>
			  
			  <!--页码显示  -->
			  <tr>
			      <td colspan="6" align="center">
			      <!-- 这里向PersonListServlet 传递值start -->
			          <a href="ImageListServlet?start=0">[首  页]</a>
			          <a href="ImageListServlet?start=${pre}">[上一页]</a>
			 <%--     <form>
			         	 <input type="text" value="${currpage}" name="currpage" tyle="width:100px;"/>
			        	 <input type="submit" value="跳转" >
			          </form>  --%>
			          <a>${currpage }/${totalPage }</a>
			          <a href="ImageListServlet?start=${next}">[下一页]</a>
			          <a href="ImageListServlet?start=${last}">[末  页]</a>
			      </td>
			  </tr>
			</tfoot>
            </table>
           </form>  
          </div>
          
      <div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增主题</div>
	  <div class="panel-body" style="margin:0 auto;">
	    	<form method="post" id="addForm" action="AddServlet" enctype="multipart/form-data">
	    		<table class="addTable"  >
	    			<tr>
	    				<td>主题名称    </td>
	    				<td><input  id="name" name="name" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    			<!--存放图片的地方  -->
	    				<td>素材图片    </td>
	    				<td>
	    					<input id=" imageid" accept="img/Templateimg" type="file" name="filepath" />
	    				</td>
	    			</tr>
	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
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