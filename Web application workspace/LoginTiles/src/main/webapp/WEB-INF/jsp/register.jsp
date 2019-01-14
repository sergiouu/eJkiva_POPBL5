<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<html>    
<head>    
    <title>Register</title>    
</head>    
<body>    
<h2>Enter information</h2>    
<form:form method="post" action="register.html">    
    
    <table>    
    <tr>    
        <td><form:label path="uname">Username</form:label></td>    
        <td><form:input path="uname" /></td>     
    </tr> 
    <tr>    
        <td><form:label path="password">Password</form:label></td>    
        <td><form:input path="password" /></td>     
    </tr> 
    <tr>    
        <td><form:label path="rname">First Name</form:label></td>    
        <td><form:input path="rname" /></td>     
    </tr>    
    <tr>    
        <td><form:label path="surname">Last Name</form:label></td>    
        <td><form:input path="surname" /></td>    
    </tr>    
    <tr>    
        <td><form:label path="mail">Email</form:label></td>    
        <td><form:input path="mail" /></td>    
    </tr>    
    <tr>    
        <td><form:label path="bornDat">Date of birth</form:label></td>    
        <td><form:input path="bornDat" /></td>    
    </tr>    
    <tr>    
        <td colspan="2">    
            <input type="submit" value="Add User"/>    
        </td>    
    </tr>    
</table>      
        
</form:form>    
</body>    
</html> 