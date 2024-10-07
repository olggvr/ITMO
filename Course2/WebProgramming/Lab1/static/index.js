class InvalidValueException extends Error {
    constructor(message) {
        super(message);
        this.name = "InvalidValueException";
    }
}

function validateFormInput(values) {

    if (values.x === null) {
        throw new InvalidValueException("Choose X");
    }

    if (isNaN(values.y)) {
        throw new InvalidValueException("Wrong Y");
    }

    const y = parseInt(values.y);
    if (y < -3 || y > 5) {
        throw new InvalidValueException("Wrong Y")
    }

    if (isNaN(values.r)) {
        throw new InvalidValueException("Wrong R value")
    }
}


/** @type HTMLTableElement */
const table = document.getElementById("result-table");

/** @type HTMLDivElement */
const errorDiv = document.getElementById("error");

async function onSubmit(ev) {
    ev.preventDefault();

    const formData = new FormData(document.getElementById("data-form"));

    const values = {
        x: formData.get('x'),
        y: formData.get('y'),
        r: formData.get('r')
    };

    try {
        validateFormInput(values);
        errorDiv.hidden = true;
    } catch (e) {
        errorDiv.hidden = false;
        errorDiv.textContent = e.message;
        return
    }


    const response = await fetch('/fcgi-bin/lab1.jar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(values)
    });

    if (response.ok) {

        const newRow = table.insertRow(-1);
        const rowX = newRow.insertCell(0);
        const rowY = newRow.insertCell(1);
        const rowR = newRow.insertCell(2);
        const rowTime = newRow.insertCell(3);
        const rowNow = newRow.insertCell(4);
        const rowResult = newRow.insertCell(5);

        const result = await response.json();
        rowX.textContent = result.x;
        rowY.textContent = result.y;
        rowR.textContent = result.z;
        rowTime.textContent = result.time;
        rowNow.textContent = result.now;
        const res =rowResult.textContent = result.result.toString();
        if (res==="true"){
            rowResult.style.color="green"
        }else{
            rowResult.style.color="orange"
        }


    } else {
        const result = await response.json();
        rowResult.style.color = "red";
        rowResult.textContent = "error";
        rowNow.textContent = result.now;
        console.error(result);
    }


    saveTableData();

}


const dataForm = document.getElementById("data-form");
dataForm.addEventListener('submit', onSubmit);
document.addEventListener('DOMContentLoaded', dataLoader);


function dataLoader() {

    const tableData = JSON.parse(sessionStorage.getItem('tableData')) || [];

    tableData.forEach(data => {
        const newRow = table.insertRow(-1);
        const rowX = newRow.insertCell(0);
        const rowY = newRow.insertCell(1);
        const rowR = newRow.insertCell(2);
        const rowTime = newRow.insertCell(3);
        const rowNow = newRow.insertCell(4);
        const rowResult = newRow.insertCell(5);

        rowX.textContent = data.x;
        rowY.textContent = data.y;
        rowR.textContent = data.r;
        rowTime.textContent = data.time;
        rowNow.textContent = data.now;
        rowResult.textContent = data.result;

        if (data.result === "true"){
            rowResult.style.color="green";
        }else if ( data.result === "false"){
            rowResult.style.color="orange"
        } else{
            rowResult.style.color="red"
        }
    });

}


function saveTableData() {
    const rows = Array.from(table.rows).slice(1);
    const tableData = rows.map(row => {
        return {
            x: row.cells[0].textContent,
            y: row.cells[1].textContent,
            r: row.cells[2].textContent,
            time: row.cells[3].textContent,
            now: row.cells[4].textContent,
            result: row.cells[5].textContent,
        };


    });

    sessionStorage.setItem('tableData', JSON.stringify(tableData));

}