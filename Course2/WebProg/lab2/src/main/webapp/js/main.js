import {clickHandler} from "./clickCheck.js";

const svg = document.querySelector('.graph');
svg.addEventListener('click', (event) => clickHandler(event, svg));