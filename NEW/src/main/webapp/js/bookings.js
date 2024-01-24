/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function showAvailableKeepers(){
    $("#ajaxContent").load("showBookings.html"); 

}

function AvailableKeepers() {  
    $.ajax({
        url: 'AvailablePetKeepers?type=all',
        type: 'GET',
        dataType: 'json',
        success: function (responseData) {
            if (responseData && responseData.length > 0) {
                $('#ajaxContent').html("<h1>Available Pet Keepers</h1>");
                $('#ajaxContent').append('<input type="text" id="deleteUserInput" placeholder="Username">');





                $('#ajaxContent').append(createTableFromJSON(responseData));
            } else {
                $('#ajaxContent').html("<p>No available pet keepers found.</p>");
            }
        },
        error: function (xhr, status, error) {
            console.error('AJAX Request Error:', status, error);

            // Log the response text for more details
            console.log('Response Text:', xhr.responseText);

            alert('AJAX Request Error. Check console for details.');
        }
    });
    
}

function sendRequest(){
    let myForm = document.getElementById('myForm');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData=JSON.stringify(data);
    
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           document.getElementById('ajaxContent').innerHTML=JSON.stringify(xhr.responseText);
                      //document.getElementById('ajaxContent').innerHTML="<br>HEY</br>";

            
        } else if (xhr.status !== 200) {
            document.getElementById('msg')
                    .innerHTML = 'Request failed. Returned status of ' + xhr.status + "<br>"+
					JSON.stringify(xhr.responseText);
 
        }
    };
    xhr.open('POST', 'AddToBookings');
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(jsonData);
    
   /*
   var name = $("#username").val();
    
    $.ajax({
        url: 'AddToBookings',
        data: {
            jsonData,
            username : name
        },
        type: 'POST',
        dataType: 'json',
        success: function (responseData) {
            if (responseData && responseData.length > 0) {
                $('#ajaxContent').html("<h1>Available Pet Keepers</h1>");

            } else {
                $('#ajaxContent').html("<p>No available pet keepers found.</p>");
            }
        },
        error: function (xhr, status, error) {
            console.error('AJAX Request Error:', status, error);

            // Log the response text for more details
            console.log('Response Text:', xhr.responseText);

            alert('AJAX Request Error. Check console for details.');
        }
    });*/
}

