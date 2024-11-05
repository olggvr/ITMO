
async function fetchData(values) {
    const response = await fetch('/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(values)
    });

    if (!response.ok) {
        const result = await response.json();
        console.error(result);
        return null;
    }

    return await response.json();
}

export { fetchData };
