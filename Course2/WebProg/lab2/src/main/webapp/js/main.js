import {sendDataToServlet} from "./sendData.js"

function getSelectedR() {
    const rRadio = document.querySelector("input[name='r']:checked");
    return rRadio ? parseFloat(rRadio.value) : null;
}

const svg = document.querySelector('.graph');
let values = {};
const button = document.getElementById('sub_button');

svg.addEventListener('click', (event) => {
    const rect = svg.getBoundingClientRect();
    const clickedX = event.clientX - rect.left;
    const clickedY = event.clientY - rect.top;
    const errorMessage = document.getElementById("error");

    const centerX = 200;
    const centerY = 200;
    const r = getSelectedR();
    if (r === null) {
        errorMessage.style.display = "block";
        return;
    } else {
        errorMessage.style.display = "none";
    }

    const rStep = 150 / r;

    let x = Math.round((clickedX - centerX) / rStep);
    let y = (- (clickedY - centerY) / rStep).toFixed(2);

    values = {x:x, y:y, r:r};
    sendDataToServlet(values);
});

button.addEventListener('click', (event) => {
    const xValue = document.querySelector('input[name="x"]:checked').value;
    const yValue = document.querySelector('input[name="y"]').value;
    const rValue = document.querySelector('input[name="r"]:checked').value;

    const values = { x: xValue, y: yValue, r: rValue };
    sendDataToServlet(values);
})