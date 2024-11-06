
async function fetchData(values) {
    const response = await fetch('/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
            'Custom-Header':'value'
        },
        body: JSON.stringify(values)
    });

    if (!response.ok) {
        const result = await response.json();
        console.error(result);
        return null;
    }

    if (response.redirected){
        window.location.href = response.url;
        return;
    }

    const customHeaderValue = response.headers.get('Custom-Header');
    console.log("Updated custom header:", customHeaderValue);

    return await response.json();
}

export { fetchData };
