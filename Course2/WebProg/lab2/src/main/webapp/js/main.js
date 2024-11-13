import {clickHandler} from "./clickCheck.js";
import {validateIsNull} from "./validation";


let coordinates = {};
const svg = document.querySelector('.graph');
svg.addEventListener('click', (event) => function (){
    coordinates = clickHandler(event, svg)
    validateIsNull(coordinates);
});



