
// listen form
document.querySelector('form').addEventListener('submit', async function (event){

    // validate input values
    //

    let errors = [];

    // x validate
    const checkboxesX = document.querySelectorAll('input[name="x"]:checked')
    if (checkboxesX.length !== 1) errors.push("ERROR: Выберите ровно одно значнеие X");

    // y validate
    const inputY = document.querySelector('input[name="y"]');
    const yValue = parseFloat(inputY.value);

    if (isNaN(yValue) || yValue < -3 || yValue > 5) {
        errors.push('ERROR: Введите корректное значение Y в диапазоне от -3 до 5.');
    }

    // errors output
    if (errors.length > 0) {
        event.preventDefault();
        alert(errors.join('\n'));
    }

    // send data
    //

    event.preventDefault();
    const formData = new FormData(this);

    try{
        const response = await fetch('/fcgi-bin/lab1.jar', {
            method: 'POST',
            body: formData
        })

        const result = await response.json();

        const currentTime = new Date().toLocaleTimeString();
        const execTime = "";

        const table = document.getElementById('result-table');
        const newRow = table.insertRow(); // Создаем новую строку

        newRow.insertCell(0).textContent = result.x;
        newRow.insertCell(1).textContent = result.y;
        newRow.insertCell(2).textContent = result.r;
        newRow.insertCell(3).textContent = currentTime;
        newRow.insertCell(4).textContent = executionTime;
        newRow.insertCell(5).textContent = result.res ? "True" : "False";
    }catch (error) {
        console.error('Error: ', error);
    }
})