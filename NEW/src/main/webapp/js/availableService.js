function showAvailablePetKeepers() {
    var xhr = new XMLHttpRequest();
    var servletUrl = 'AvailableService';

    xhr.open('GET', servletUrl, true);
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            var responseArray = JSON.parse(xhr.responseText);
            if (responseArray.length === 0) {
                document.getElementById('ajaxContent').innerHTML = 'No available Pet Keeper at the moment.';
            } else {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            }
        } else {
            console.error('Request failed with status:', xhr.status, xhr.statusText);
        }
    };

    xhr.send();
}
