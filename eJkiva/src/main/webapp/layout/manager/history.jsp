<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>

#styled {
	margin: 0;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

#styled2 {
	margin: 0;
	display: flex;
	flex-direction: row;
	justify-content: center;
}
#text {
	margin: 0;
	display: flex;
	align-self:center;
}
</style>

<div style="height: 100%; width: 100%;">
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
	<script type="text/javascript"
		src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
	<script type="text/javascript"
		src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<p class="text-center display-4">ITEMS THAT HAVE BEEN TAKEN OUT
		FROM THE WAREHOUSE</p>
	<br />
	<form method="post" action="history.html" >
		<div class="col-lg-12">
			<div class="form-group"
				class="d-flex justify-content-center bg-secondary mb-3">
				<div class="row" id="styled2">
					<div class="col col-lg-6" id="styled">
						<select class="form-control" name="month" id="month">
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
					</div>

					<div class="col col-lg-2 center-block" class="text-center">

						<button class="form-control" type="Submit" name="action"
							value="ok" class="btn btn-secondary btn-lg active" role="button"
							aria-pressed="true">ok</button>
					</div>
				</div>


			</div>
		</div>

	</form>
	<div id="container" style="height: 500px; width: 100%"></div>

	<script type="text/javascript">     
		var dom = document.getElementById("container");
		
		list = null;
		list ="${mylistF}";
		monthN ="${monthName}";
		console.log(monthN);
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
</div>