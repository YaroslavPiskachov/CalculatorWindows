package model;

/**
 * Enum represents binary operations and symbols for their showing to user
 */
public enum BinaryOperation implements Operation{
    /**
     * Plus operation
     */
    PLUS("+"),
    /**
     * Minus operation
     */
    MINUS("-"),
    /**
     * Multiply operation
     */
    MULTIPLY("×"),
    /**
     * Divide operation
     */
    DIVIDE("÷");
    /**
     * Symbol of operation
     */
    public String operationSign;

    BinaryOperation(String operationSign){
        this.operationSign = operationSign;
    }
}