import {validateRValue} from "./validation"

function getSelectedR() {
    const rRadio = document.querySelector("input[name='r']:checked");
    return rRadio ? parseFloat(rRadio.value) : null;
}

function clickHandler(event, svg){
    const rect = svg.getBoundingClientRect();
    const clickedX = event.clientX - rect.left;
    const clickedY = event.clientY - rect.top;

    const centerX = 200;
    const centerY = 200;
    const rValue = getSelectedR();
    validateRValue(rValue);

    const rStep = 150 / rValue;

    let x = Math.round((clickedX - centerX) / rStep);
    let y = (- (clickedY - centerY) / rStep).toFixed(2);

    if (x > 3) return null;
    return {x, y, rValue}
}

export {clickHandler}

