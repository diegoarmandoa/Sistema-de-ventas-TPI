
function sendData(url, data ) {
    return fetch(url, {
        method: "POST",
        body: data
    });

}

function getData(url, data ) {
    return fetch(url, {
        method: "GET",
        body: data
    });

}
