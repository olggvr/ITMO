
import { InvalidValueException } from './exceptions.js';

function validateFormInput(values) {

    if (values.x === null) {
        throw new InvalidValueException("Choose X");
    }

    if (isNaN(values.y)) {
        throw new InvalidValueException("Wrong Y");
    }

    const y = parseInt(values.y);
    if (y < -3 || y > 5) {
        throw new InvalidValueException("Wrong Y")
    }

    if (isNaN(values.r)) {
        throw new InvalidValueException("Wrong R value")
    }
}

export {validateFormInput};