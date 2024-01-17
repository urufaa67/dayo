<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
        window.onload = function() {
            fetchData();
        };

        function fetchData() {
            fetch('/cinema/History') // サーブレットのURLを指定
                .then(response => response.json()) // JSON形式でレスポンスを解析
                .then(data => {
                    displayData(data); // データを表示する関数に渡す
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }

        function displayData(data) {
            const template = document.getElementById('dataRowTemplate').content;
            const container = document.getElementById('dataContainer');
            container.innerHTML = "";

            data.reserveHistory.forEach(item => {
                const clone = document.importNode(template, true);
                clone.querySelector('.reserveId').textContent = item.reserveId;
                clone.querySelector('.seat').textContent = item.seat;
                clone.querySelector('.date').textContent = item.date;
            	const id = "" + item.reserveId;
                clone.querySelector('.deleteHistory').onclick = function(){
                    deleteHistory(id);
                    }
                container.appendChild(clone);
            });
        }

        function deleteHistory(deleteReserveId){
            console.log("deleteReserveId:" + deleteReserveId);
            fetch('/cinema/History?id=' + deleteReserveId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    console.log('削除に成功しました');
                    // 削除成功時の処理
                    fetchDataAndDisplay(); 
                } else {
                    console.error('削除に失敗しました');
                    // 削除失敗時の処理
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }

        function fetchDataAndDisplay() {
            fetch('/cinema/History') // サーブレットのURLを指定
                .then(response => response.json()) // JSON形式でレスポンスを解析
                .then(data => {
                    displayData(data); // データを表示する関数に渡す
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
            
    </script>
</head>
<body>
	<h2>Server Data:</h2>
	<table border="1">
		<thead>
			<tr>
				<th>Reserve ID</th>
				<th>Seat</th>
				<th>Date</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="dataContainer">
			<!-- JavaScriptによってデータ行がここに挿入されます -->
		</tbody>
	</table>
	<!-- テンプレートの定義 -->
	<template id="dataRowTemplate">
		<tr>
			<td class="reserveId"></td>
			<td class="seat"></td>
			<td class="date"></td>
			<td><button class="deleteHistory" type="button">削除</button></td>
		</tr>
	</template>
</body>
</html>