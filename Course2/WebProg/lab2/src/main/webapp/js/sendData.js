async function sendDataToServlet(values) {
    const params = new URLSearchParams(values);

    const response = await fetch(`./controller?${params.toString()}`, {
        method: 'GET',
    })
        .then();

    if(response.redirected){
        window.location.href = response.url;
    }
}

export {sendDataToServlet}