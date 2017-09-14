package modelTest;

import model.Calculator;
import model.DivisionByZeroException;
import model.NegativeValueForSqrtException;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static model.BinaryOperation.PLUS;
import static model.SpecialOperation.SQR;
import static model.SpecialOperation.SQRT;

/**
 * Created by Yaroslav on 29.08.2017.
 */

public class usualTest {

    //A+B   M sqrt(C) + MR
    public BigDecimal calculate(BigDecimal a, BigDecimal b, BigDecimal c) throws DivisionByZeroException, NegativeValueForSqrtException {
        Calculator calculator = new Calculator();

        BigDecimal answer = calculator.executeOperation(PLUS, a, b);

        calculator.setMemoryValue(answer);
        BigDecimal answer2 = calculator.executeOperation(SQRT, c);
        BigDecimal answer3 = calculator.executeOperation(PLUS, answer2, calculator.getMemoryValue());

        return answer3;

    }

    @Test
    public void test3Plus5Memory4SqrtPlusMemory() {
        //3+5 M sqrt(4) + MR =


        Calculator calculator = new Calculator();
        BigDecimal answer = null;
        try {
            answer = calculator.executeOperation(PLUS, new BigDecimal("3"), new BigDecimal("5")); // 8
        } catch (DivisionByZeroException e) {
            System.out.println("division by zero");
            return;
        }

        calculator.setMemoryValue(answer);
        BigDecimal answer3 = null;

        try {
            BigDecimal answer2 = calculator.executeOperation(SQR, new BigDecimal("4")); // 16
            answer3 = calculator.executeOperation(PLUS, answer2, calculator.getMemoryValue()); // 24
        } catch (DivisionByZeroException | NegativeValueForSqrtException e) {
            if (e instanceof DivisionByZeroException) {
                System.out.println("division by zero");
            } else if (e instanceof NegativeValueForSqrtException) {
                System.out.println("value is negative");
            }
            return;
        }

        //answer is 'answer3'

    }

    @Test
    public void test3Plus5Memory4SqrtPlusMemory1() {
        //3+5 M M+ sqrt(4) + MR =
        Calculator calculator = new Calculator();
        //BigDecimal answer = calculator.executeOperation(PLUS,new BigDecimal("3"), new BigDecimal("5")); // 8

        //calculator.setMemoryValue(answer);
        calculator.memoryPlus(new BigDecimal("5"));

        // BigDecimal answer2 = calculator.executeOperation(SQR,new BigDecimal("4")); // 16
        //BigDecimal answer3 = calculator.executeOperation(PLUS,answer2, calculator.getMemoryValue()); // 29

        //assertEquals(new BigDecimal("29"),answer3);
    }
}
