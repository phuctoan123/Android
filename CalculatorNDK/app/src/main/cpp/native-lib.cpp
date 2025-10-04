#include "jni.h"
#include <string>
#include <sstream>
#include <iomanip>
#include <stdexcept>
#include "Calculator.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_calculatorndk_data_source_ndk_CalculatorJNI_calculateResult(
        JNIEnv* env,
        jobject/* this */,
        jdouble value1,
        jdouble value2,
        jchar op
        ) {

    Calculator calculator;

    double result = calculator.calculateResult(value1, value2, op);
    std::ostringstream resultStream;
    resultStream << std::fixed << std::setprecision(2) << result;

    std::string resultStr = resultStream.str();

    return env->NewStringUTF(resultStr.c_str());

}