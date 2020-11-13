<%@page pageEncoding="UTF-8"%>

<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
	<script src ="${pageContext.request.contextPath}/view/script/jquery-3.2.1.min.js" ></script >
	<script src="${pageContext.request.contextPath}/view/script/echarts.js"></script>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">Mes_sys</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>${loginUser.username} <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${APP_PATH}/logout.page"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 客服管理 </span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="/wechatdemo/UserListServlet" style="color:red;"><i class="glyphicon glyphicon-user"></i> 信息维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed" >
						<a href=""><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div id="main1" style="width: 900px; height: 350px;"></div>  
		    <input type="hidden" id="uservalue" value="${list}">
		    <input type="hidden" id="uservalue1" value="${list1}">
        </div>
      </div>
    </div>
    <script type="text/javascript">  
	var uservalue = $("#uservalue").val();
	var showvalue1 = uservalue.split(",");
	console.log(showvalue1);
	var uservalue1 = $("#uservalue1").val();
	var showvalue2 = uservalue1.split(",");
	console.log(showvalue2);
    var zChart = echarts.init(document.getElementById("main1"));// 柱形图模板一  
  
    var option = {  
        color : [ '#3398DB' ],  
        title : {  
            text : 'ECharts 示例', //主标题文本，支持使用 \n 换行。  
            link : '', //主标题文本超链接  
            textStyle : { //该属性用来定义主题文字的颜色、形状等  
                color : '#3398DB',  
            }  
        },  
        tooltip : {  
            trigger : 'axis',  
            axisPointer : { // 坐标轴指示器，坐标轴触发有效  
                type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'  
            }  
        },  
        grid : {  
            left : '6%',  
            right : '20%',  
            bottom : '20%',  
            containLabel : true  
        },  
        xAxis : [ { //x轴列表展示  
            type : 'category',  
            data : showvalue2 
        } ],  
        yAxis : [ { //定义y轴刻度  
            type : 'value',  
            scale : true,  
            name : '每日增长量',  
            max : 20,  
            min : 0,  
            splitNumber : 10,  
            boundaryGap : [ 0.2, 0.2 ]  
        } ],  
        series : [ {  
            name : '当天人数',  
            type : 'bar', //bar 柱状图     line 折线图 等  
            barWidth : '40%', //柱的宽度  
            data : showvalue1  
        } ]  
    };  
    zChart.setOption(option);  
	</script>  
  </body>
</html>