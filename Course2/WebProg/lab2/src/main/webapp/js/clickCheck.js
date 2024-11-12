
const svg = document.querySelector('.graph');
const xCheckboxes = document.querySelectorAll("input[name='x']");
const yInput = document.querySelector("input[name='y']");

svg.addEventListener('click', (event) => {
    const rect = svg.getBoundingClientRect();
    const offsetX = event.clientX - rect.left;
    const offsetY = event.clientY - rect.top;

    const centerX = 200;
    const centerY = 200;
    const rValue = getSelectedR();

    const xCoord = ((offsetX - centerX) / 150 * rValue).toFixed(2);
    const yCoord = ((centerY - offsetY) / 150 * rValue).toFixed(2);

    selectXCheckbox(xCoord);
    yInput.value = yCoord;
});

function getSelectedR() {
    const rRadio = document.querySelector("input[name='r']:checked");
    return rRadio ? parseFloat(rRadio.value) : null;
}

function selectXCheckbox(xValue) {
    xCheckboxes.forEach(checkbox => {
        checkbox.checked = (checkbox.value === xValue);
    });
}
