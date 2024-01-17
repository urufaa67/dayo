<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報一覧</title>
</head>
<body>
		<form action="/admin/memberlistconfirm.jsp"  method="post">
		メンバーID :	<input type="number"   		required	name="memberId"  value="${session_memberId}"  readonly />  <br>
		ユーザーID :	<input type="text"   		required	name="userId"  value="${session_userId}"  readonly  />  <br>
		パスワード :	<input type="password"   	required	name="pass"  value="${session_pass}"   readonly />  <br>
		名前 :          <input type="text"   		required	name="name"  value="${session_name}"   readonly />  <br>
		名前（かな） :  <input type="text"   		required	name="kana"  value="${session_kana}" readonly/>  <br> 
		電話番号 :		<input type="tel"    		required	name="tel"  pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" value="${session_tel}" readonly/>  <br>
		メールアドレス:	<input type="email"  		required	name="mail"  value="${session_mail}" readonly/>  <br>
		郵便番号 :		<input type="text"   		required	name="postNum"  value="${session_postNum}" readonly/>  <br>
		住所 :			<input type="text"   		required	name="address"  value="${session_address}" readonly/>  <br>
		性別 :			<input type="text"   		required	name="gender"  value="${session_gender}" readonly/>  <br>
		
						<input type="submit"   value="変更"/>
						<h2>購入履歴</h2>
						<a href="/admin/historyadmin.jsp"><button class="purchasehistory" type="button">確認</button></a>
						
		</form>
		<a href="/admin/topadmin.jsp" class="btn">トップ</a>
</body>
</html>