package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static model.BinaryOperation.*;
import static model.SpecialOperation.*;

/**
 * Class represents objects for calculator work
 */
public class Calculator {

    /**
     * List consist of objects which should be in history
     */
    private List<Object> history = new ArrayList<>();

    /**
     * Variable for keeping value in memory of calculator
     */
    private BigDecimal memoryValue;

    /**
     * Value equals to one hundred. Needed for calculating percent
     */
    private BigDecimal oneHundred = new BigDecimal("100");

    /**
     * Function for adding element to history
     *
     * @param obj
     */
    public void addToHistory(Object obj){
        history.add(obj);
    }

    /**
     * Function for deleting last element from history
     */
    public void deleteLastInHistory(){
        history.remove(history.size() - 1);
    }

    /**
     * Function for getting element from history
     */
    public Object getFromHistory(int i){
        return history.get(i);
    }

    /**
     * Function for getting history size
     */
    public int getHistorySize(){
        return history.size();
    }

    /**
     * Function for getting last element from history
     */
    public Object getLastFromHistory(){
        Object res = null;
        if(history.size() != 0){
            res = history.get(history.size() - 1);
        }
        return res;
    }

    /**
     * Function for clearing history
     */
    public void makeHistoryEmpty(){
        history.clear();
    }

    /**
     * Function for executing sqrt operation
     *
     * @param currentValue operation will be executed for
     *
     * @return {@code BigDecimal} result of operation
     */
    private   BigDecimal sqrt(BigDecimal currentValue) throws NegativeValueForSqrtException {
        if (currentValue.doubleValue() < 0) {
            throw new NegativeValueForSqrtException();
        }

        if (currentValue.doubleValue() == 0.0) {
            return BigDecimal.ZERO;
        }

        BigDecimal x = new BigDecimal(Math.sqrt(currentValue.doubleValue()));
        currentValue = x.add(new BigDecimal(currentValue.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)
        )).round(MathContext.DECIMAL128);
        return currentValue;
    }

    /**
     * Function for executing one divided operation
     *
     * @param currentValue operation will be executed for
     *
     * @return {@code BigDecimal} result of operation
     */
    private BigDecimal oneDivided(BigDecimal currentValue) throws DivisionByZeroException {
        return divide(currentValue, BigDecimal.ONE);
    }

    /**
     * Function for executing sqr operation
     *
     * @param currentValue operation will be executed for
     *
     * @return {@code BigDecimal} result of operation
     */
    public  BigDecimal sqr(BigDecimal currentValue) {
        return currentValue.pow(2, MathContext.DECIMAL128);
    }


    /**
     * Function for executing negate operation
     *
     * @param currentValue operation will be executed for
     *
     * @return {@code BigDecimal} result of operation
     */
    private  BigDecimal negate(BigDecimal currentValue) {
        return currentValue.negate();
    }

    /**
     * Function executing divide operation
     *
     * @param currentValue divisor of operation
     * @param lastValue dividend of operation
     *
     * @return {@code BigDecimal} result of operation
     *
     * @throws ArithmeticException
     *      if divisor of operation is zero
     */
    private  BigDecimal divide(BigDecimal currentValue, BigDecimal lastValue) throws DivisionByZeroException {
        if (currentValue.doubleValue() == 0.0) {
            throw new DivisionByZeroException();
        }
        return lastValue.divide(currentValue, MathContext.DECIMAL128);
    }

    /**
     * Function executing plus operation
     *
     * @param currentValue first value
     * @param lastValue second value
     *
     * @return {@code BigDecimal} result of operation
     */
    private  BigDecimal plus(BigDecimal currentValue, BigDecimal lastValue) throws ArithmeticException {
        return currentValue.add(lastValue, MathContext.DECIMAL128);
    }

    /**
     * Function executing plus operation
     *
     * @param currentValue first value will be subtracted from second value
     * @param lastValue second value
     *
     * @return {@code BigDecimal} result of operation
     */
    private BigDecimal minus(BigDecimal currentValue, BigDecimal lastValue) throws ArithmeticException {
        return lastValue.subtract(currentValue, MathContext.DECIMAL128);
    }

    /**
     * Function executing multiply operation
     *
     * @param currentValue first value
     * @param lastValue second value
     *
     * @return {@code BigDecimal} result of operation
     */
    private  BigDecimal multiply(BigDecimal currentValue, BigDecimal lastValue) throws ArithmeticException {
        return currentValue.multiply(lastValue, MathContext.DECIMAL128);
    }

    /**
     * Function for getting percent operation
     *
     * @param currentValue percent which will be executed
     * @param percentFrom value percent which will be executed from
     *
     * @return {@code BigDecimal} result of operation
     */
    private  BigDecimal percent(BigDecimal currentValue, BigDecimal percentFrom) throws DivisionByZeroException {
        if (currentValue == null || percentFrom == null) {
            throw new NullPointerException("param is null");
        }

        currentValue = divide(oneHundred, percentFrom).multiply(currentValue).round(MathContext.DECIMAL128);
        return currentValue;
    }

    /**
     * Function for getting result of executing some operation which needs two values
     *
     * @param operation operation which will be executed
     * @param firstValue first value for calculating
     * @param secondValue second value for calculating
     *
     * @return {@code BigDecimal} result of operation
     */
    public BigDecimal executeOperation(Operation operation, BigDecimal firstValue, BigDecimal secondValue) throws DivisionByZeroException, WrongOperationException {
        BigDecimal answer;
        if(operation == PLUS){
            answer = plus(firstValue,secondValue);
        } else if(operation == MINUS){
            answer = minus(firstValue,secondValue);
        } else if(operation == MULTIPLY){
            answer = multiply(firstValue,secondValue);
        } else if(operation == DIVIDE){
            answer = divide(firstValue,secondValue);
        } else if(operation == PERCENT){
            answer = percent(firstValue,secondValue);
        } else {
            throw new WrongOperationException();
        }

        return answer;
    }

    /**
     * Function for getting result of executing some special operation which needs only one value
     *
     * @param specialOperation operation which will be executed
     * @param firstValue value for calculating
     *
     * @return {@code BigDecimal} result of operation
     */
    public BigDecimal executeOperation(SpecialOperation specialOperation, BigDecimal firstValue) throws DivisionByZeroException, NegativeValueForSqrtException, WrongOperationException {
        BigDecimal answer;
        if(specialOperation == SQR){
            answer = sqr(firstValue);
        } else if(specialOperation == SQRT){
            answer = sqrt(firstValue);
        } else if (specialOperation == NEGATE){
            answer = negate(firstValue);
        } else if(specialOperation == ONE_DIVIDED){
            answer = oneDivided(firstValue);
        } else {
            throw new WrongOperationException();
        }

        return answer;
    }

    /**
     * Function for removing value from memory
     */
    public void clearMemoryValue() {
        memoryValue = null;
    }

    /**
     * Function for increasing value in memory on value that is current at this moment
     */
    public void memoryPlus(BigDecimal currentValue) throws NullPointerException {
        memoryValue = memoryValue.add(currentValue);
    }

    /**
     * Function for reducing value in memory on value that is current at this moment
     */
    public void memoryMinus(BigDecimal currentValue) throws NullPointerException {
        memoryValue = memoryValue.subtract(currentValue);
    }

    public BigDecimal getMemoryValue() {
        return memoryValue;
    }

    public void setMemoryValue(BigDecimal currentValue) {
        this.memoryValue = currentValue;
    }

}

