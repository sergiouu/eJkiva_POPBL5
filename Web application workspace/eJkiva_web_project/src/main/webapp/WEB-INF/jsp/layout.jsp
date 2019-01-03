<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">    
<html>    
<head>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
<link rel="stylesheet" href="css/style.css">
<!-- Bootstrap CSS -->
 <link
 rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
 integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
 crossorigin="anonymous">
<title><tiles:insertAttribute name="title" ignore="true" /></title>    
</head>    
<body class="h-100">    
        <header class="page-header">
        	<tiles:insertAttribute name="header" />
        </header>     	  
        <div class = "main">
	        <div class="aside" style="float:left;padding:10px;width:15%;"><tiles:insertAttribute name="menu" /></div>    
	        <div class="center" style="float:left;padding:10px;width:80%;">    
	        <tiles:insertAttribute name="body" /></div>  
        </div> 
        <footer class="footer">
        	<tiles:insertAttribute name="footer" />    
    	</footer>
    	
    	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>    
</html>    