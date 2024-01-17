<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Page</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<script>
function fetchData() {
    $.ajax({
        type: "GET",
        url: "/admin/Edit",
        dataType: "json",
        success: function (data) {
            console.log("Data received from Servlet:", data);
            displayData(data);
        },
        error: function (xhr, status, error) {
            console.error("AJAX request failed:", status, error);
        }
    });
}

function fetchDataAndDisplay() {
    fetch('/admin/Edit')
        .then(response => response.json())
        .then(data => {
            displayData(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function displayData(data) {
    console.log("Received data:", data);

    if (data && data.editer) { // Update property name to 'editer'
        const template = document.getElementById('dataRowTemplate').content;
        const container = document.getElementById('dataContainer');
        container.innerHTML = "";
        

        if (Array.isArray(data.editer)) {
            data.editer.forEach(item => {
                const clone = document.importNode(template, true);
                
                <%--clone.querySelector('.titleId').textContent = item.titleId;
                clone.querySelector('.title').textContent = item.title;
                clone.querySelector('.imgPath').textContent = item.imgPath;
                clone.querySelector('.moviePath').textContent = item.moviePath;
                clone.querySelector('.date').textContent = item.date;
                clone.querySelector('.screenId').textContent = item.screenId;
                clone.querySelector('.time1').textContent = item.time1;
                clone.querySelector('.time2').textContent = item.time2;
                clone.querySelector('.time3').textContent = item.time3;
                clone.querySelector('.time4').textContent = item.time4;
                clone.querySelector('.time5').textContent = item.time5;
                clone.querySelector('.time6').textContent = item.time6;
                clone.querySelector('.description').textContent = item.description; --%>
                clone.querySelector('.titleId').textContent = item.titleId;
                clone.querySelector('.edit-title').value = item.title;
                clone.querySelector('.edit-imgpath').value = item.imgpath;
                clone.querySelector('.edit-moviepath').value = item.moviepath;
                clone.querySelector('.edit-date').value = item.date;
                clone.querySelector('.edit-screenId').value = item.screenId;
                clone.querySelector('.edit-time1').value = item.time1;
                clone.querySelector('.edit-time2').value = item.time2;
                clone.querySelector('.edit-time3').value = item.time3;
                clone.querySelector('.edit-time4').value = item.time4;
                clone.querySelector('.edit-time5').value = item.time5;
                clone.querySelector('.edit-time6').value = item.time6;
                clone.querySelector('.edit-description').value = item.description;

                // Set the src attribute for the imgPath
                //const imgElement = clone.querySelector('.imgPath');
                //imgElement.src = item.imgPath;
                //imgElement.alt = item.title; // Add alt attribute for accessibility
                //imgElement.date = item.date; // Add alt attribute for accessibility

                container.appendChild(clone);
                
            });

            // テーブルの後ろに追加ボタンを生成
            const addButton = document.createElement('button');
            addButton.textContent = '新しいレコード追加';
            addButton.addEventListener('click', addNewRecord);

            // 既存のテーブルの親要素を取得
            const tableContainer = document.getElementById('dataContainer');

            // テーブルの後ろに追加ボタンを挿入
            tableContainer.parentNode.insertBefore(addButton, tableContainer.nextSibling);
            
        } else {
            console.log("Data.editer is not an array. Data:", data);
        }
    } else {
        console.log("Data or data.editer is undefined. Data:", data);
    }
}

function addNewRecord() {
    const dummyData = {
        //titleId: "",
        title: 'タイトル',
        imgpath: '画像パス',
        moviepath: '動画パス',
        date: '日付',
        screenId: 0,
        time1: '時間1',
        time2: '時間2',
        time3: '時間3',
        time4: '時間4',
        time5: '時間5',
        time6: '時間6',
        description: '映画情報',
        // ... (他のフィールドも同様に設定)
    };

    // 新しいデータをサーバーに送信
    fetch('/admin/InsertRecord', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: Object.keys(dummyData).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(dummyData[key])).join('&'),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        if (data.status === 'success') {
            console.log('New record inserted successfully');
        } else {
            console.error('Failed to insert new record:', data.message);
        }
    })
    .catch(error => {
    	console.error('Fetch error:', error);
    	console.log('Error message:', error.message);
    	console.log('Server response:', error.response);
    });
}
function updateRow(button) {
    // Implement the logic to send updated data to the server and update the database
    // You can use AJAX (similar to fetchData) to send data to the server
    // The server should have a servlet to handle the update

    // Example:
    const row = button.closest('tr');
    const updatedData = {
        titleId: parseInt(row.querySelector('.titleId').textContent),
        title: row.querySelector('.edit-title').value,
        imgpath: row.querySelector('.edit-imgpath').value,
        moviepath: row.querySelector('.edit-moviepath').value,
        date: row.querySelector('.edit-date').value,
        screenId: parseInt(row.querySelector('.edit-screenId').value),
        time1: row.querySelector('.edit-time1').value,
        time2: row.querySelector('.edit-time2').value,
        time3: row.querySelector('.edit-time3').value,
        time4: row.querySelector('.edit-time4').value,
        time5: row.querySelector('.edit-time5').value,
        time6: row.querySelector('.edit-time6').value,
        description: row.querySelector('.edit-description').value,
    };

    // Send the updated data to the server for processing
    // Use AJAX to send the data to the server (similar to fetchData)
    // The server should have a servlet to handle the update
    fetch('/admin/Update', {
        method: 'POST',
        headers: {
            //'Content-Type': 'application/json',
        	'Content-Type': 'application/x-www-form-urlencoded',
        },
        //body: JSON.stringify(updatedData),
        body: Object.keys(updatedData).map(key => encodeURIComponent(key) + '=' + encodeURIComponent(updatedData[key])).join('&'),
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            // Handle success, if needed
            console.log('Update successful');
        } else {
            // Handle error, if needed
            console.error('Update failed:', data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

$(document).ready(function () {
    // Use either fetchData or fetchDataAndDisplay, not both
    // fetchData();
    fetchDataAndDisplay();
    
});

</script>
<body>

<h1>Edit Page</h1>

<table border="1">
    <thead>
        <tr>
            <th>Title ID</th>
            <th>Title</th>
            <th>ImgPath</th>
            <th>MoviesPath</th>
            <th>Date</th>
            <th>Screen ID</th>
            <th>Time1</th>
            <th>Time2</th>
            <th>Time3</th>
            <th>Time4</th>
            <th>Time5</th>
            <th>Time6</th>
            <th>Description</th>
            <th>Actions</th>
            <th></th>
        </tr>
    </thead>
    <tbody id="dataContainer">
        <!-- JavaScriptによってデータ行がここに挿入されます -->
    </tbody>
</table>

<template id="dataRowTemplate">
    <tr>
        <td class="titleId"><input type="text" class="edit-titleId" /></td>
        <td class="title"><input type="text" class="edit-title" /></td>
        <td class="imgpath"><input type="text" class="edit-imgpath" /></td>
        <td class="moviepath"><input type="text" class="edit-moviepath" /></td>
        <td class="date"><input type="text" class="edit-date" /></td>
        <td class="screenId"><input type="text" class="edit-screenId" /></td>
        <td class="time1"><input type="text" class="edit-time1" /></td>
        <td class="time2"><input type="text" class="edit-time2" /></td>
        <td class="time3"><input type="text" class="edit-time3" /></td>
        <td class="time4"><input type="text" class="edit-time4" /></td>
        <td class="time5"><input type="text" class="edit-time5" /></td>
        <td class="time6"><input type="text" class="edit-time6" /></td>
        <td class="description"><input type="text" class="edit-description" /></td>
         <td>
            <button onclick="updateRow(this)">Update</button>
        </td>
    </tr>
</template>
</body>
</html>
