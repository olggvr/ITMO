
const table = document.getElementById("result-table");
const errorDiv = document.getElementById("error");

function populateTableRow(data) {
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

    if (data.result === "true") {
        rowResult.style.color = "green";
    } else if (data.result === "false") {
        rowResult.style.color = "orange";
    } else {
        rowResult.style.color = "red";
    }
}

function dataLoader() {
    const tableData = JSON.parse(sessionStorage.getItem('tableData')) || [];
    tableData.forEach(populateTableRow);
}

function saveTableData() {
    const rows = Array.from(table.rows).slice(1);
    const tableData = rows.map(row => ({
        x: row.cells[0].textContent,
        y: row.cells[1].textContent,
        r: row.cells[2].textContent,
        time: row.cells[3].textContent,
        now: row.cells[4].textContent,
        result: row.cells[5].textContent,
    }));

    sessionStorage.setItem('tableData', JSON.stringify(tableData));
}

export { dataLoader, saveTableData, populateTableRow };
