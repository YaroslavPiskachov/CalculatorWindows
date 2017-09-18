package model;

/**
 * Enum represents special operations functions for their showing
 */
public enum SpecialOperation implements Operation{
    /**
     * Sqr operation
     */
    SQR {
        @Override
        public String getOperationSign(String val) {
            return "sqr( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Sqr operation
     */
    SQRT {
        @Override
        public String getOperationSign(String val) {
            return "√( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * One divided operation
     */
    ONE_DIVIDED {
        @Override
        public String getOperationSign(String val) {
            return "1/( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Negate operation
     */
    NEGATE {
        @Override
        public String getOperationSign(String val) {
            return "negate( " + val.replace(".", ",") + " )";
        }

    },
    /**
     * Percent operation
     */
    PERCENT {
        @Override
        public String getOperationSign(String val) {
            return "%( " + val.replace(".", ",") + " )";
        }

    };

    /**
     * Function for getting operation appearance
     * @param val number or string operation should be displayed with
     * @return String with operation symbol and {@code val}
     */
    public abstract String getOperationSign(String val);
}