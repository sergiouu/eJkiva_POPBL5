<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"  style="height: 100%">
  <head>
	   <meta charset="utf-8">
	   <title>D3 praktika12</title>
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
	   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body  style="height: 50%; margin: 0">
  	<header>
  	  <h1  class="text-center">ITEMS THAT HAVE BEEN TAKEN OUT FROM THE WAREHOUSE</h1><br/>
	  <form action="LoginP" method="post">
	  <div class="container mt-3">
      	<div class="form-group" class="d-flex justify-content-center bg-secondary mb-3">
	      	<div class="p-2">
	      		<label for="sel1">Select month:</label>
	      	</div>
	      	<div class="p-2" class="text-center">
		      <select class="form-control" name="month" id="month" >
		        <option value="1">January</option>
				<option value="2">February</option>
				<option value="3">March</option>
				<option value="4">April</option>
				<option value="5">May</option>
				<option value="6">June</option>
				<option value="7">July</option>
				<option value="8">August</option>
				<option value="9">September</option>
				<option value="10">October</option>
				<option value="11">November</option>
				<option value="12">December</option>
		      </select>
		      <button  class="form-control" type="Submit" name="action" value="ok" class="btn btn-secondary btn-lg active" role="button" aria-pressed="true">ok</button>   
		    </div>   
	    </div>
	  </div>		
 	 </form>
	</header>
	<div id="container" style="height: 100%"></div>
       
     <script type="text/javascript">     
		var dom = document.getElementById("container");
		
		list = null;
		list ="${mylistF}";
		monthN ="${monthName}";
		console.log(list);
		valueList =${mylistD}; 
		console.log(valueList);
		
		
		var fecha;
		fecha = list.split('[');
		fecha = fecha[1].split(']');
		fecha = fecha[0].split(',');
		
		console.log(fecha);
		
		var myChart = echarts.init(dom);
		var app = {};
		option = null;
		option = {
			tooltip: {
			       trigger: 'item',
				   formatter: 'Date: {b} <br> Items: {c}'
			},
			title: {
		        left: 'center',
		        text: monthN,
		    },
			xAxis: [{
			       type: 'category',
			       data: fecha,			       
			  }],
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        type: 'line',
		        data: valueList
		    }]
		};
		;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
       </script>
  </body>
</html>