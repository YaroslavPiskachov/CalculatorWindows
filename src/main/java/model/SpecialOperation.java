package model;

/**
 * Enum represents special operations functions for their showing
 */
public enum SpecialOperation implements Operation{
    /**
     * Sqr operation
     */
    SQR {
        private String operationSign;

        public String getOperationSign() {
            return operationSign;
        }

        @Override
        public String makeOperationSign(String val) {
            return operationSign = "sqr( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Sqr operation
     */
    SQRT {
        private String operationSign;

        public String getOperationSign() {
            return operationSign;
        }

        @Override
        public String makeOperationSign(String val) {
            return operationSign = "√( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * One divided operation
     */
    ONE_DIVIDED {
        private String operationSign;

        public String getOperationSign() {
            return operationSign;
        }

        @Override
        public String makeOperationSign(String val) {
            return operationSign = "1/( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Negate operation
     */
    NEGATE {
        private String operationSign;

        public String getOperationSign() {
            return operationSign;
        }

        @Override
        public String makeOperationSign(String val) {
            return operationSign = "negate( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Percent operation
     */
    PERCENT {
        private String operationSign;


        public String getOperationSign() {
            return operationSign;
        }

        @Override
        public String makeOperationSign(String val) {
           return operationSign = "%( " + val.replace(".", ",") + " )";
        }

    };

    //private String operationSign;

    /**
     * Function for getting operation appearance
     * @param val number or string operation should be displayed with
     * @return String with operation symbol and {@code val}
     */
    public abstract String makeOperationSign(String val);
}