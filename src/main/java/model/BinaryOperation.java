package model;

/**
 * Enum represents binary operations and symbol for their showing
 */
public enum BinaryOperation implements Operation{
    PLUS(" + "), MINUS(" - "), MULTIPLY(" × "), DIVIDE(" ÷ ");
    /**
     * Symbol of operation
     */
    public String operationSign;

    BinaryOperation(String operationSign){
        this.operationSign = operationSign;
    }
}
