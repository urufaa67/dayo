<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
</head>
<body> 
		 <form action="/admin/Search" method="post">
				メンバーID :    <input type="number"   			name="memberId"   />  <br>
				名前 :          <input type="text"   			name="name"      />  <br>
				かな :          <input type="text"   			name="kana"     />  <br><br>
								<input type="submit"   		value="検索"/><br>
		</form><br>
		<a href="/admin/topadmin.jsp"><button class="memberinfo" type="button">トップ</button></a>
</body>
</html>