#include "Calculator.h"
#include <stdexcept>

double Calculator::add(double a, double b) {
    return a+b;
}

double Calculator::subtract(double a, double b) {
    return a-b;
}

double Calculator::multiply(double a, double b) {
    return a*b;
}

double Calculator::divide(double a, double b) {
    if (b != 0){
        return a/b;
    } else {
        throw std::invalid_argument("Division by zero");
    }
}

double Calculator::calculateResult(double value1, double value2, char op) {
    switch (op) {
        case '+':
            return add(value1, value2);
        case '-':
            return subtract(value1, value2);
        case '*':
            return multiply(value1, value2);
        case '/':
            return divide(value1, value2);
        default:
            throw std::invalid_argument("Invalid operator");

    }
}
