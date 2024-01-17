<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<form action="/admin/Login" method="post">
	ユーザーID : <input type="text" name="userId"/> <br><br>
	パスワード : <input type="password" name="pass"/> <br><br>
	<input type="submit" value="Login"/> <br><br>
	
</form>
</body>
</html>