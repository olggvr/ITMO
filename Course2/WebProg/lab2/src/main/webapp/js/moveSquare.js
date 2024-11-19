
const svg = document.querySelector('.graph');
const square = document.getElementById('square');

let isDragging = false;
let offsetX = 0;
let offsetY = 0;

function getMousePosition(event) {
    const CTM = svg.getScreenCTM();
    return [
        (event.clientX - CTM.e) / CTM.a,
        (event.clientY - CTM.f) / CTM.d
    ];
}

square.addEventListener('mousedown', (event) => {
    isDragging = true;

    const [x, y] = getMousePosition(event);
    const bbox = square.getBBox();

    offsetX = x - bbox.x;
    offsetY = y - bbox.y;
});

svg.addEventListener('mousemove', (event) => {
    if (!isDragging) return;

    const [x, y] = getMousePosition(event);

    const bbox = square.getBBox();
    const newX = Math.min(Math.max(x - offsetX, 0), svg.clientWidth - bbox.width);
    const newY = Math.min(Math.max(y - offsetY, 0), svg.clientHeight - bbox.height);

    const width = bbox.width;
    const height = bbox.height;
    const points = `${newX},${newY + height} ${newX},${newY} ${newX + width},${newY} ${newX + width},${newY + height}`;
    square.setAttribute('points', points);
});

svg.addEventListener('mouseup', () => {
    isDragging = false;
});
svg.addEventListener('mouseleave', () => {
    isDragging = false;
});