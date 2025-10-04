#include <string>

class Calculator {
public:
    double calculateResult(double value1, double value2, char op);
private:
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);

};