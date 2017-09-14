package model;

/**
 * Enum represents special operations functions for their showing
 */
public enum SpecialOperation implements Operation{
    SQR {
        @Override
        public String getOperationSign(String val) {
            return "sqr( " + val.replace(".", ",") + " )";
        }

    }, SQRT {
        @Override
        public String getOperationSign(String val) {
            return "√( " + val.replace(".", ",") + " )";
        }

    }, ONE_DIVIDED {
        @Override
        public String getOperationSign(String val) {
            return "1/( " + val.replace(".", ",") + " )";
        }

    }, NEGATE {
        @Override
        public String getOperationSign(String val) {
            return "negate( " + val.replace(".", ",") + " )";
        }

    }, PERCENT {
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