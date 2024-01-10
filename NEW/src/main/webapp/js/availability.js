function formatUsers(data, category, value) {
    let resultHtml = '<div>';
    resultHtml += `<h2>${category}</h2>`;
    resultHtml += '<ul>';

    for (let i = 0; i < data.length; i++) {
        resultHtml += `<li>${value}: ${data[i]}</li>`;
    }

    resultHtml += '</ul>';
    resultHtml += '</div>';

    return resultHtml;
}

function showAvailability() {   
    console.log('Button pressed');

    $.ajax({
        url: 'AvailablePetKeepers?type=catKeepers',
        type: 'GET',
        dataType: 'json',
        success: function (responseData) {
            if (responseData && responseData.length > 0) {
                $('#ShowKeepers').html("<h1>Available Pet Keepers</h1>");
                $('#ShowKeepers').append(createTableFromJSON(responseData));
            } else {
                $('#ShowKeepers').html("<p>No available pet keepers found.</p>");
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

function showUsers() {
    console.log('Button pressed');

    $.ajax({
        url: 'AvailableUsers',
        type: 'GET',
        dataType: 'json',
        success: function (responseData) {
            console.log('Response Data:', responseData);

            if (responseData && responseData.owner && responseData.keeper) {
                $('#outerContent').html("<h1>List of users:</h1>");

                $('#outerContent').append(formatUsers(responseData.owner, 'Pet Owners', 'Username'));
                $('#outerContent').append(formatUsers(responseData.keeper, 'Pet Keepers', 'Username'));
            } else {
                $('#outerContent').html("<p>No user found.</p>");
            }
        },
        error: function (xhr, status, error) {
            console.error('AJAX Request Error:', status, error);

            console.log('Response Text:', xhr.responseText);

            alert('AJAX Request Error. Check console for details.');
        }
    });
}
