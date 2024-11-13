

function getSelectedR() {
    const rRadio = document.querySelector("input[name='r']:checked");
    return rRadio ? parseFloat(rRadio.value) : null;
}

const svg = document.querySelector('.graph');
let values = {};

svg.addEventListener('click', (event) => {
    const rect = svg.getBoundingClientRect();
    const clickedX = event.clientX - rect.left;
    const clickedY = event.clientY - rect.top;

    const centerX = 200;
    const centerY = 200;
    const r = getSelectedR();
    const rStep = 150 / r;

    let x = Math.round((clickedX - centerX) / rStep);
    let y = (- (clickedY - centerY) / rStep).toFixed(2);

    values = {x:x, y:y, r:r};
    console.log("x = %d, y = %s, r = %f", values.x, values.y, values.r);
});