
import { InvalidValueException } from './exceptions.js';

function validateIsNull(values) {
    if (values === null) {
        throw new InvalidValueException("Wrong X");
    }
}

function validateRValue(rValue){
    if (rValue === null){
        throw new InvalidValueException("Choose R");
    }
}

export {validateIsNull, validateRValue};