import {clickHandler} from "./clickCheck.js";


let coordinates = {};
const svg = document.querySelector('.graph');
svg.addEventListener('click', (event) => function (){
    coordinates = clickHandler(event, svg)
});