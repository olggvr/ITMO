function sendDataToServlet(values) {
    const params = new URLSearchParams(values);

    fetch(`./controller?${params.toString()}`, {
        method: 'GET',
    })
        .then(response => {
            if(response.redirected){
                window.location.href = response.url;
            }
        });
}

export {sendDataToServlet}