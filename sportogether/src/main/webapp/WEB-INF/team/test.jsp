<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>RivalMatching</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function teamPortfolioShow() {
	if (form.value == "") {
		alert("TeamID를 입력하십시오.");
		form.teamID.focus();
		return false;
	} 	
	form.submit();
}
function totalLanking(targetUri) {
	form.action = targetUri;
	form.method="GET";		
	form.submit();
}
</script>
</head>
<body>
<br>
<form name="form" method="POST" action="<c:url value='/team/portfolio' />">
  <table style="width:100%">
	<tr>
	  <td width="20"></td>
	  <td>
	   	<table>
	   	  <tr>
		    <td class="title">&nbsp;&nbsp;팀 포트폴리오 - TestPage&nbsp;&nbsp;</td>
		  </tr>
	    </table> 
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">teamID</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="teamID">
			</td>
		  </tr>
		  <tr>
		  	<td colspan = '2'><input type = "button" value="팀 포트폴리오 보기" onClick = "teamPortfolioShow()"></td>
		  </tr>
	    </table>
	    <br>	 
	  </td>	  
	</tr>
  </table>  
</form>
</body>
</html>