/**
 * @author Podleśny Jakub S20540
 */

package zad1;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    private String[] equation;
    private Map<String, Method> operationsMap;

    public Calc() {
        operationsMap = new HashMap<>();

    }

    public String doCalc(String givenEquation) {
        try {
            Method addM = this.getClass().getDeclaredMethod("add", BigDecimal.class, BigDecimal.class);
            Method substractM = this.getClass().getDeclaredMethod("substract", BigDecimal.class, BigDecimal.class);
            Method divideM = this.getClass().getDeclaredMethod("divide", BigDecimal.class, BigDecimal.class);
            Method multiplyM = this.getClass().getDeclaredMethod("multiply", BigDecimal.class, BigDecimal.class);
            /////
            operationsMap.put("+", addM);
            operationsMap.put("-", substractM);
            operationsMap.put("/", divideM);
            operationsMap.put("*", multiplyM);
            ////
            equation = givenEquation.split("\\s+");
            BigDecimal number1 = new BigDecimal(equation[0]);
            BigDecimal number2 = new BigDecimal(equation[2]);
            Method m = operationsMap.get(equation[1]);
            return "" + m.invoke(this, number1, number2);
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }

    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2).stripTrailingZeros();
    }

    public BigDecimal substract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2).stripTrailingZeros();
    }

    public BigDecimal divide(BigDecimal number1, BigDecimal number2) {
        return number1.divide(number2,7,BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
    }

    public BigDecimal multiply(BigDecimal number1, BigDecimal number2) {
        return number1.multiply(number2).stripTrailingZeros();
    }
}
