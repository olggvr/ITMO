
import { validateFormInput } from './validate.js';
import { fetchData } from './sendData.js';
import {dataLoader, populateTableRow, saveTableData} from './table.js';

async function onSubmit() {
    const selectedX = document.querySelectorAll('input[name="x"]:checked');
    const yValue = document.querySelector('input[name="y"]').value;
    const rValue = document.querySelector('input[name="r"]:checked').value;

    for (const checkbox of selectedX) {
        const xValue = checkbox.value;
        const values = { x: xValue, y: yValue, r: rValue };

        try {
            validateFormInput(values);
            errorDiv.hidden = true;
        } catch (e) {
            errorDiv.hidden = false;
            errorDiv.textContent = e.message;
            return;
        }

        const result = await fetchData(values);
        if (result) {
            populateTableRow(result);
        }
    }

    saveTableData();
}

const but = document.getElementById("sub_button");
but.addEventListener('click', onSubmit);
document.addEventListener('DOMContentLoaded', dataLoader);
