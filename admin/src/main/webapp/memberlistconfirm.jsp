<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報一覧</title>
</head>
<body>
		<form action="/admin/Memberlist"  method="post">
		メンバーID :	<input type="number"   		required	name="memberId"  value="${session_memberId}"  readonly />  <br>
		ユーザーID :	<input type="text"   		required	name="userId"  value="${session_userId}"    />  <br>
		パスワード :	<input type="password"   	required	name="pass"  value="${session_pass}"    />  <br>
		名前 :          <input type="text"   		required	name="name"  value="${session_name}"    />  <br>
		名前（かな） :  <input type="text"   		required	name="kana"  value="${session_kana}" />  <br> 
		電話番号 :		<input type="tel"    		required	name="tel"  pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" value="${session_tel}" />  <br>
		メールアドレス:	<input type="email"  		required	name="mail"  value="${session_mail}" />  <br>
		郵便番号 :		<input type="text"   		required	name="postNum"  value="${session_postNum}" />  <br>
		住所 :			<input type="text"   		required	name="address"  value="${session_address}" />  <br>
		性別 :			<input type="text"   		required	name="gender"  value="${session_gender}" />  <br>
		
						<input type="submit"   value="変更を確定する"/>
		</form>
</body>
</html>