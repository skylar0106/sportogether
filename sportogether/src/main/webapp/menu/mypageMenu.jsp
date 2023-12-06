<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>mypageMenu</title>
	<style>
	</style>
	<script>
		function menu_choice(num){
			switch(num){
			case 1:parent.rigth.documnet.location.replace("/team/lanking.jsp'");break;
			case 1:parent.rigth.documnet.location.replace("/team/portfolio.jsp");break;
			case 1:parent.rigth.documnet.location.replace("/user/portfolio.jsp");break;
			case 1:parent.rigth.documnet.location.replace("/user/profile.jsp");break;
			}
		}
	</script>
</head>
<body>
	<div class = "d1">
		<center><input type = "button" value = "TEAM LANKING" onClick="menu_choice(1)"></center>
	</div>
	<div class = "d2">
		<center><input type = "button" value = "TEAM PORTFOLIO" onClick="menu_choice(2)"></center>
	</div>
	<div class = "d3">
		<center><input type = "button" value = "MY PORTFOLIO" onClick="menu_choice(3)"></center>
	</div>
	<div class = "d4">
		<center><input type = "button" value = "내 정보 수정" onClick="menu_choice(4)"></center>
	</div>
</body>
</html>