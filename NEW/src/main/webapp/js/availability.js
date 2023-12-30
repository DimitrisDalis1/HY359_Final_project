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
