
document.addEventListener("DOMContentLoaded", function () {

    class ValidY {
        validate(value) {
            const decimalPart = String(value).trim().split('.')[1];
            if (isNaN(value)) {
                alert("Y неверен");
                return false;
            }
            if (decimalPart && decimalPart.length > 15) {
                alert("Много знаков после запятой");
                return false;
            }
            if (value < -3 || value > 5) {
                alert("Y не в пределах от -3 до 5");
                return false;
            }
            return true;
        }
    }

    class ValidX {
        validate(value) {
            if (isNaN(value)) {
                alert("Х неверен");
                return false;
            }
            if (value < -2 || value > 1) {
                alert("Х не в пределах от -2 до 1");
                return false;
            }
            return true;
        }
    }

    class ValidR {
        validate(value) {
            if (!value) {
                alert("Забыл выбрать R:)");
                return false;
            }
            return true;
        }
    }



    const validators = {
        x: new ValidX(),
        y: new ValidY(),
        r: new ValidR(),
    };

    function validateFormInput(values) {
        for (let key in values) {
            validators[key].validate(values[key]);
        }
    }



    function handleClick(event) {
        const svg = document.getElementById("plate");
        const point = svg.createSVGPoint();
        point.x = event.clientX;
        point.y = event.clientY;
        const coords = point.matrixTransform(svg.getScreenCTM().inverse());

        const r = document.querySelector('input[name="data-form:rSelect"]:checked')?.value;
        let x = (coords.x - 250) / 33;
        let y = (250 - coords.y) / 33;

        try {
            validateFormInput({ x: x, y: y.toFixed(2), r });

            const validXValues = [-2, -1.5, -1, -0.5, 0, 0.5, 1];
            x = validXValues.reduce((prev, curr) =>
                Math.abs(curr - x) < Math.abs(prev - x) ? curr : prev
            );
            console.log("x: " + x);

            document.querySelector('select[id$=":x"]').value = x;
            document.querySelector('input[id$=":y"]').value = y.toFixed(2);
            document.querySelector('input[name="data-form:rSelect"][value="' + r + '"]').checked = true;

            document.getElementById("data-form:submit").click();
        } catch (e) {
            alert(e.message);
        }
    }

    let allowAjaxRequest = false;

    window.validateAndSubmitForm = function () {
        const form = document.getElementById("data-form");
        const formData = new FormData(form);
        const values = {
            x: formData.get('data-form:x'),
            y: formData.get('data-form:y'),
            r: formData.get('data-form:rSelect')
        };

        const errorDiv = document.getElementById("error");

        try {
            validateFormInput(values);
            errorDiv.hidden = true;
            allowAjaxRequest = true;
            return true;
        } catch (e) {
            errorDiv.hidden = false;
            errorDiv.textContent = e.message;
            allowAjaxRequest = false;
            return false;
        }
    }

    document.getElementById("plate").addEventListener("click", handleClick);

    const radioButtons = document.querySelectorAll('input[name="data-form\\:rSelect"]');
    radioButtons.forEach(radio => {
        radio.addEventListener('change', function () {
            updateGraph(this.value);
        });
    });

    function updateGraph(r) {
        const scaleFactor = r / 3;

        const elementsToUpdate = [
            {id: "rect", attrs: {width: 100 * scaleFactor, height: 50 * scaleFactor, x: 250, y: 250 - 50 * scaleFactor}},
            {
                id: "arc", attrs: {d: `M ${250 -  50 * scaleFactor} 250 A ${50 * scaleFactor} ${50 * scaleFactor} 0 0 1 250 ${250 - 50 * scaleFactor} L 250 250 Z`}
            },
            {id: "triangle", attrs: {points: `250,250 250,${250 + 100 * scaleFactor} ${250 + 100 * scaleFactor},250`}},
            {id: "mark-neg-rx", attrs: {x1: 250 - 100 * scaleFactor, x2: 250 - 100 * scaleFactor}},
            {id: "mark-rx", attrs: {x1: 250 + 100 * scaleFactor, x2: 250 + 100 * scaleFactor}},
            {id: "mark-ry", attrs: {y1: 250 - 100 * scaleFactor, y2: 250 - 100 * scaleFactor}},
            {id: "mark-neg-ry", attrs: {y1: 250 + 100 * scaleFactor, y2: 250 + 100 * scaleFactor}},
            {id: "label-neg-rx", attrs: {x: 250 - 120 * scaleFactor}},
            {id: "label-rx", attrs: {x: 250 + 103 * scaleFactor}},
            {id: "label-neg-ry", attrs: {y: 250 + 110 * scaleFactor}},
            {id: "label-ry", attrs: {y: 250 - 96 * scaleFactor}},
        ];

        elementsToUpdate.forEach(({id, attrs}) => {
            const element = document.getElementById(id);
            if (element) {
                Object.entries(attrs).forEach(([attr, value]) => {
                    element.setAttribute(attr, value);
                });
            }
        });
    }

    function drawPoints() {
        const svg = document.getElementById("plate");
        svg.querySelectorAll(".data-point").forEach(point => point.remove());

        const points = document.querySelectorAll("#points-data .point");
        points.forEach(point => {
            const x = parseFloat(point.getAttribute("data-x"));
            const y = parseFloat(point.getAttribute("data-y"));
            const result = point.getAttribute("data-result") === "true";

            const svgX = 250 + x * 33;
            const svgY = 250 - y * 33;

            const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
            circle.setAttribute("cx", svgX);
            circle.setAttribute("cy", svgY);
            circle.setAttribute("r", 2);
            circle.setAttribute("fill", result ? "green" : "red");
            circle.classList.add("data-point");

            svg.appendChild(circle);
        });
    }

    const resultElement = document.getElementById("result");
    if (resultElement) {
        const observer = new MutationObserver(drawPoints);
        observer.observe(resultElement, { childList: true, subtree: true });
    }

    drawPoints();

});
