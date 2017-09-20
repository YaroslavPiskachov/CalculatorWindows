package controller;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import static javafx.scene.input.KeyCode.*;
import static model.SpecialOperation.*;


/**
 * Class represents controller for window of Application
 * Class consist of methods which are event listeners for every button in Application
 * and some methods for their support.
 * Also there are methods for correct displaying data
 */

public class Controller {

    /**
     * Previous inputted value for calculations
     */
    private BigDecimal lastValue;


    /**
     * Collection for mapping operations and id's
     */
    private Map<Button, Operation> operationMap = new HashMap<>();

    /**
     * Model of calculator
     */
    private static Calculator calculator = new Calculator();

    /**
     * Max count of zeros that can be at the beginning of number
     */
    private final int MAX_COUNT_OF_FIRST_ZEROS = 4;

    /**
     * Max number in normal system for doing calculations
     */
    private final BigDecimal MAX_NUMBER_IN_NORMAL_SYSTEM = new BigDecimal("9999999999999999.4999999999999999");

    /**
     * Max count of digits in number calculator can display
     */
    private final int MAX_DIGITS_IN_NUMBER = 17;

    /**
     * Symbol which defines number as in exponential system
     */
    private final String SYMBOL_EXP = "e";

    /**
     * Symbol which separates parts of float number
     */
    private final String FLOAT_POINT = ",";

    /**
     * Symbol which separates parts of big number by three digits
     */
    private final String BIG_NUMBER_SEPARATOR = " ";

    /**
     * Value which is defined as initial for calculator display
     */
    private final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

    /**
     * The greatest value calculator can work with
     */
    private final BigDecimal MAX_VALUE = new BigDecimal("1.000000000000000E+10000");

    /**
     * The nearest positive value to zero
     */
    private final BigDecimal MIN_POSITIVE_VALUE = new BigDecimal("1E-9999");

    /**
     * The nearest negative value to zero
     */
    private final BigDecimal MAX_NEGATIVE_VALUE = new BigDecimal("-1E-9999");

    /**
     * Minimal value calculator can work with
     */
    private final BigDecimal MIN_VALUE = new BigDecimal("-9.999999999999999E+9999");

    /**
     * Current value contains number from main label of calculator
     */
    private BigDecimal currentValue = DEFAULT_VALUE;

    /**
     * Answer of calculation last and current values
     */
    private BigDecimal answer;

    /**
     * Stage of application
     */
    private Stage stage;

    /**
     * Define font of numbers in main label of window
     */
    private final Font fontForMainLabel = new Font("Microsoft JhengHei UI Bold", 45);


    /**
     * Css style of font of buttons with text after increasing window size
     */
    private final String extendedFontSize = "-fx-font-size: 26px";

    /**
     * Css style of background size for buttons with image after increasing window size
     */
    private final String extendedBackgroundSize = "-fx-background-size: 85 60";

    /**
     * Define type of binary operation which is selected
     */
    private BinaryOperation binaryOperation;

    /**
     * Define type of special operation which is selected
     */
    private SpecialOperation specialOperation;

    /**
     * Is replacing value needed
     */
    private boolean replaceValue;

    /**
     * Is last executed operation special
     */
    private boolean isOperationSpecial;

    /**
     * Define result of last special operation added to history
     * if "null" means last operation is not special
     */
    private SpecialOperation lastSpecialAddedToHistory;

    /**
     * Is last pressed button for adding digit
     */
    private boolean isDigitAddedLast = true;

    /**
     * Is last pressed button calculate
     */
    private boolean isCalculateButtonLast;

    /**
     * List of buttons with image on a background
     */
    private List<Button> buttonsWithImage;

    /**
     * List of buttons with text on a background
     */
    private List<Button> buttonsWithText;

    /**
     * Coordinates of window location before maximazing
     */
    private double x, y;

    /**
     * Massage will be shown to user when overflow exception was thrown
     */
    private String overflowExceptionMsg = "Переполнение";

    /**
     * Massage will be shown to user when division by zero exception was thrown
     */
    private String divisionByZeroMsg = "Деление на 0";

    /**
     * Massage will be shown to user when negative value for sqrt exception was thrown
     */
    private String negativeValueForSqrtExceptionMsg = "Введены неверные данные";

    /**
     * Parent pane for calculator
     */
    @FXML
    AnchorPane anchorPane;

    /**
     * Label for displaying inputted digits and answers
     */
    @FXML
    Label mainLabel;

    /**
     * Label for displaying history of operations
     */
    @FXML
    Label historyLabel;

    /**
     * Button for clearing memory
     */
    @FXML
    Button mClear;

    /**
     * Button for displaying number from memory to main label
     */
    @FXML
    Button mRead;

    /**
     * Button for saving number to memory from main label
     */
    @FXML
    Button mSave;

    /**
     * Button for subtracting value in main label from memory value
     */
    @FXML
    Button mMinus;

    /**
     * Button for adding value in main label to memory value
     */
    @FXML
    Button mPlus;

    /**
     * Button for inputting digit 0
     */
    @FXML
    Button zeroButton;

    /**
     * Button for inputting digit 1
     */
    @FXML
    Button oneButton;

    /**
     * Button for inputting digit 2
     */
    @FXML
    Button twoButton;

    /**
     * Button for inputting digit 3
     */
    @FXML
    Button threeButton;

    /**
     * Button for inputting digit 4
     */
    @FXML
    Button fourButton;

    /**
     * Button for inputting digit 5
     */
    @FXML
    Button fiveButton;

    /**
     * Button for inputting digit 6
     */
    @FXML
    Button sixButton;

    /**
     * Button for inputting digit 7
     */
    @FXML
    Button sevenButton;

    /**
     * Button for inputting digit 8
     */
    @FXML
    Button eightButton;

    /**
     * Button for inputting digit 9
     */
    @FXML
    Button nineButton;

    /**
     * Button for plus operation
     */
    @FXML
    Button plusButton;

    /**
     * Button for minus operation
     */
    @FXML
    Button minusButton;

    /**
     * Button for multiply operation
     */
    @FXML
    Button multiplyButton;

    /**
     * Button for divide operation
     */
    @FXML
    Button divideButton;

    /**
     * Button for calculate answer
     */
    @FXML
    Button calculateButton;

    /**
     * Button for inputting comma
     */
    @FXML
    Button commaButton;

    /**
     * Button for removing last inputted symbol
     */
    @FXML
    Button backSpaceButton;

    /**
     * Button for sqr operation
     */
    @FXML
    Button sqrButton;

    /**
     * Button for sqrt operation
     */
    @FXML
    Button sqrtButton;

    /**
     * Button for percent operation
     */
    @FXML
    Button percentButton;

    /**
     * Button for one divided operation
     */
    @FXML
    Button oneDividedButton;

    /**
     * Button for changing sign of value
     */
    @FXML
    Button signButton;

    /**
     * Button for clearing all elements of calculation
     */
    @FXML
    Button clearButton;

    /**
     * Button for clearing only value in main label
     */
    @FXML
    Button clearCurrentButton;

    /**
     * Formatter object for displaying values on label
     */
    private DecimalFormat formatter = new DecimalFormat();

    /**
     * Symbols for correct formatting and displaying values on label
     */
    private DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    /**
     * Function represents listener on pressing action of moving window
     */
    @FXML
    public void movementPressed(MouseEvent mouseEvent) {
        x = stage.getX() - mouseEvent.getScreenX();
        y = stage.getY() - mouseEvent.getScreenY();
    }

    /**
     * Function represents listener on dragging action of moving window
     */
    @FXML
    public void movementDragged(MouseEvent mouseEvent) {
        if (stage.getScene().getCursor() != Cursor.N_RESIZE) {
            stage.setX(mouseEvent.getScreenX() + x);
            stage.setY(mouseEvent.getScreenY() + y);
        }
    }

    /**
     * Function represents listener on button for closing window
     */
    @FXML
    public void closeWindowButton() {
        stage.close();
    }

    /**
     * Function represents listener on button for extending window on full screen
     */
    @FXML
    public void extendWindowButton() {
        stage.setMaximized(!stage.isMaximized());
        resizeFontMainLabel();
    }

    /**
     * Function represents listener for buttons with digits for input
     */
    @FXML
    public void digitButton(ActionEvent actionEvent) throws DivisionByZeroException, OverflowException {
        Button pressed = (Button) actionEvent.getSource();
        int digit = buttonsWithText.indexOf(pressed);
        addToMainLabel(digit);
    }

    /**
     * Function represents listener on button for hiding window on task panel
     */
    @FXML
    public void hideWindowButton() {
        stage.setIconified(true);
    }

    /**
     * Function represents listener on button for inputting comma
     */
    @FXML
    public void commaButton() throws OverflowException {
        if (!mainLabel.getText().contains(FLOAT_POINT)) {
            isValueFloat = true;
            inputting = true;
            displayValue(currentValue);
            inputting = false;
            replaceValue = false;
        }
    }

    private boolean isValueFloat = false;

    /**
     * Function represents listener on button for clearing only value in main label
     */
    @FXML
    public void clearCurrentButton() throws OverflowException {
        deleteLastSpecialInHistory();
        isValueFloat = false;
        displayValue(DEFAULT_VALUE);
    }

    /**
     * Function represents listener on button for clearing all elements
     */
    @FXML
    public void clearAllButton() throws OverflowException {
        setDisableButtons(false);
        binaryOperation = null;
        currentValue = DEFAULT_VALUE;
        lastValue = null;
        displayValue(DEFAULT_VALUE);
        historyLabel.setText(" ");
        calculator.makeHistoryEmpty();
        isOperationSpecial = false;
        isCalculateButtonLast = false;
        lastSpecialAddedToHistory = null;
        specialOperation = null;
        answer = null;
        isValueFloat = false;
        resizeFontMainLabel();
    }

    /**
     * Function represents listener on backspace button
     */
    @FXML
    public void backSpaceButton() throws DivisionByZeroException, OverflowException {
        inputting = true;
        if (isValueFloat && currentValue.scale() == 0) {
            displayValue(currentValue);
        } else if (!replaceValue) {
            int floatDigits = 0;
            BigDecimal value = currentValue;
            while (value.scale() != 0) {
                floatDigits++;
                value = value.movePointRight(1);
            }

            value = calculator.executeOperation(BinaryOperation.DIVIDE, BigDecimal.TEN, value);
            value = new BigDecimal(value.toBigInteger());

            if (floatDigits != 0) {
                value = value.movePointLeft(floatDigits - 1);
            }

            displayValue(value);
            replaceValue = false;
        }
        inputting = true;
    }

    /**
     * Function represents listener on button for calculating answer
     */
    @FXML
    public void calculateButton() {
        specialOperation = null;
        try {
            if (binaryOperation != null) {
                if (isCalculateButtonLast) {
                    currentValue = calculator.executeOperation(binaryOperation, lastValue, currentValue);
                    displayValue(currentValue);
                    replaceValue = true;
                } else {
                    calculateAnswer();
                }
            }
            answer = null;
            historyLabel.setText(" ");
            calculator.makeHistoryEmpty();
            isDigitAddedLast = true;
            lastSpecialAddedToHistory = null;
            specialOperation = null;
            isCalculateButtonLast = true;
            isValueFloat = false;
        } catch (OverflowException | DivisionByZeroException e) {
            printException(e);
        }
    }

    /**
     * Function represents listener on button for reading value from memory
     */
    @FXML
    public void mReadButton() {

        deleteLastSpecialInHistory();
        try {
            displayValue(calculator.getMemoryValue());
            replaceValue = true;
        } catch (OverflowException e) {
            printException(e);
        }
    }

    /**
     * Function represents listener on button for saving value for memory
     */
    @FXML
    public void mSaveButton() {
        disableMemoryWork(false);
        calculator.setMemoryValue(currentValue);
        replaceValue = true;
    }

    /**
     * Function represents listener on button for saving value for memory
     */
    @FXML
    public void mClearButton() {
        disableMemoryWork(true);
        calculator.clearMemoryValue();
    }

    /**
     * Function represents listener on button for increasing value in memory
     */
    @FXML
    public void mPlusButton() {
        if (calculator.getMemoryValue() == null) {
            mSaveButton();
        } else {
            calculator.memoryPlus(currentValue);
            replaceValue = true;
        }
    }

    /**
     * Function represents listener on button for reducing value in memory
     */
    @FXML
    public void mMinusButton() {
        if (calculator.getMemoryValue() == null) {
            mSaveButton();
        } else {
            calculator.memoryMinus(currentValue);
            replaceValue = true;
        }
    }

    /**
     * Function for disabling or unabling buttons which make work with memory not allowed
     *
     * @param isDisable make buttons disable or unable
     */
    private void disableMemoryWork(boolean isDisable) {
        mRead.disableProperty().setValue(isDisable);
        mClear.disableProperty().setValue(isDisable);
    }

    /**
     * Function for executing special operation
     *
     * @param actionEvent event which was fired
     */
    @FXML
    private void executeSpecialOperation(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        SpecialOperation specialOperation = (SpecialOperation) operationMap.get(button);

        isOperationSpecial = true;

        if (!(specialOperation == NEGATE && lastSpecialAddedToHistory == null)) {
            addToHistoryLabel(specialOperation);
        }
        this.specialOperation = specialOperation;

        try {
            BigDecimal result;
            if (specialOperation == PERCENT) {
                result = calculator.executeOperation(specialOperation, currentValue, lastValue);
            } else {
                result = calculator.executeOperation(specialOperation, currentValue);
            }
            displayValue(result);
        } catch (OverflowException | DivisionByZeroException | NegativeValueForSqrtException e) {
            printException(e);
        }

        if (specialOperation != NEGATE) {
            replaceValue = true;
        }
        this.specialOperation = null;
    }

    /**
     * Function disables buttons
     * Needed when exception was thrown
     *
     * @param val defines disable buttons or not
     */
    private void setDisableButtons(boolean val) {
        percentButton.setDisable(val);
        sqrButton.setDisable(val);
        sqrtButton.setDisable(val);
        oneDividedButton.setDisable(val);
        divideButton.setDisable(val);
        multiplyButton.setDisable(val);
        minusButton.setDisable(val);
        plusButton.setDisable(val);
        signButton.setDisable(val);
        mMinus.setDisable(val);
        mSave.setDisable(val);
        mPlus.setDisable(val);
        if (!mRead.isDisable() && !mClear.isDisable()) {
            mRead.setDisable(val);
            mClear.setDisable(val);
        }

    }

    /**
     * Function for display exception in a proper way
     *
     * @param e exception with message will be displayed
     */
    private void printException(Exception e) {
        String toMainLabel;
        if (e instanceof DivisionByZeroException) {
            toMainLabel = divisionByZeroMsg;
        } else if (e instanceof NegativeValueForSqrtException) {
            toMainLabel = negativeValueForSqrtExceptionMsg;
        } else if (e instanceof OverflowException) {
            toMainLabel = overflowExceptionMsg;
        } else {
            toMainLabel = "Ошибка";
        }

        String history = historyLabel.getText();
        try {
            clearAllButton();
        } catch (OverflowException r) {
            r.printStackTrace();
        }

        mainLabel.setText(toMainLabel);
        replaceValue = true;
        resizeFontMainLabel();
        setDisableButtons(true);
        historyLabel.setText(history);
    }

    /**
     * Function for executing binary operation
     *
     * @param actionEvent event which was fired
     */
    @FXML
    private void executeBinaryOperation(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        BinaryOperation binaryOperation = (BinaryOperation) operationMap.get(button);

        specialOperation = null;
        isOperationSpecial = false;
        addToHistoryLabel(binaryOperation);
        if (!isDigitAddedLast) {
            this.binaryOperation = binaryOperation;
            return;
        }
        if (lastValue == null || isCalculateButtonLast) {
            replaceValue = true;
            lastValue = currentValue;
        } else {
            try {
                calculateAnswer();
            } catch (OverflowException | DivisionByZeroException e) {
                printException(e);
            }
        }

        this.binaryOperation = binaryOperation;
        lastSpecialAddedToHistory = null;
        isDigitAddedLast = false;
        isCalculateButtonLast = false;
    }

    /**
     * Function for calculating answer of binary operation
     */
    private void calculateAnswer() throws OverflowException, DivisionByZeroException {
        if (answer == null) {
            answer = lastValue;
        }
        answer = calculator.executeOperation(binaryOperation, currentValue, answer);
        lastValue = currentValue;
        displayValue(answer);
        replaceValue = true;
    }

    /**
     * Function removes result of last special operation added to history
     * Needed for replacing value in history
     */
    private void deleteLastSpecialInHistory() {
        int historySize = calculator.getHistorySize();
        boolean inLoop = false;
        for (int i = historySize - 1; i >= 0 && calculator.getFromHistory(i) instanceof SpecialOperation; i--) {
            inLoop = true;
            calculator.deleteLastInHistory();
        }
        if (inLoop) {
            calculator.deleteLastInHistory();
        }
        showHistory();
    }

    /**
     * Function displays answer of calculations on main label of application
     *
     * @param val result of calculation will be displayed
     */
    private void displayValue(BigDecimal val) throws OverflowException {
        if ((val.compareTo(MAX_VALUE) == 1 || val.compareTo(MAX_VALUE) == 0 || val.compareTo(MIN_VALUE) == -1)
                || (val.compareTo(MIN_POSITIVE_VALUE) == -1 && val.compareTo(BigDecimal.ZERO) == 1)
                || (val.compareTo(MAX_NEGATIVE_VALUE) == 1 && val.compareTo(BigDecimal.ZERO) == -1)) {
            throw new OverflowException();
        }

        String toMainLabel;
        String inMainLabel = mainLabel.getText();
        if (specialOperation == NEGATE) {
            String text = mainLabel.getText();
            toMainLabel = text.startsWith("-") ? text.substring(1) : "-" + text;
        } else if (isValueFloat && inputting) {
            toMainLabel = makeString(val);

            if (inMainLabel.contains(FLOAT_POINT)) {
                if (currentValue.scale() == 0 && val.scale() == 0) {
                    toMainLabel = inMainLabel.replace(FLOAT_POINT, "");
                    isValueFloat = false;
                } else if (currentValue.scale() == 1 && val.scale() == 0) {
                    toMainLabel = inMainLabel.substring(0, inMainLabel.length() - 1);
                }
            } else {
                toMainLabel = inMainLabel + FLOAT_POINT;
            }
        } else {
            if (isInteger(val) && val.scale() != 0) {
                val = new BigDecimal(val.toBigInteger());
            }
            toMainLabel = makeString(val);
        }

        currentValue = val;
        mainLabel.setText(toMainLabel);
        isDigitAddedLast = true;
        resizeFontMainLabel();
    }

    private boolean inputting = false;

    /**
     * Function adds digits to number which is in main label
     *
     * @param digit digit will be added to number on display
     */
    private void addToMainLabel(int digit) throws DivisionByZeroException, OverflowException {
        if (replaceValue) {
            isValueFloat = false;
            currentValue = DEFAULT_VALUE;
        }
        inputting = true;
        if ((currentValue.toBigInteger().equals(BigInteger.ZERO) && currentValue.precision() == 17) ||
                !currentValue.toBigInteger().equals(BigInteger.ZERO) && currentValue.precision() == 16) {
            inputting = false;
            return;            // number too long
        }
        setDisableButtons(false);
        if (mainLabel.getText().equals(overflowExceptionMsg)) {
            clearAllButton();
        }

        if (lastSpecialAddedToHistory != null) {
            deleteLastSpecialInHistory();
        }

        BigDecimal newCurrentValue;
        BinaryOperation operation;

        if (currentValue.compareTo(BigDecimal.ZERO) == -1) {
            operation = BinaryOperation.MINUS;
        } else {
            operation = BinaryOperation.PLUS;
        }
        if (!isValueFloat) {
            newCurrentValue = calculator.executeOperation(BinaryOperation.MULTIPLY, currentValue, BigDecimal.TEN);
            newCurrentValue = calculator.executeOperation(operation, new BigDecimal(digit), newCurrentValue);
        } else {
            BigDecimal newDigit = new BigDecimal(digit);
            newCurrentValue = currentValue;

            if (!isInteger(newCurrentValue)) {
                newDigit = newDigit.movePointLeft(1);
            }

            newDigit = newDigit.movePointLeft(newCurrentValue.scale());

            if (digit == 0) {
                int newScale = newCurrentValue.scale() + 1;
                newCurrentValue = newCurrentValue.setScale(newScale, BigDecimal.ROUND_DOWN);
            } else {
                if (isInteger(newCurrentValue) || newCurrentValue.compareTo(BigDecimal.ZERO) == 0) {
                    newDigit = newDigit.movePointLeft(1);
                }

                newCurrentValue = calculator.executeOperation(operation, currentValue, newDigit);
                if (operation == BinaryOperation.MINUS) {
                    newCurrentValue = newCurrentValue.negate();
                }
            }
        }
        displayValue(newCurrentValue);
        inputting = false;
        replaceValue = false;
        isDigitAddedLast = true;
    }

    /**
     * Function check is value has integer type
     *
     * @param bd value will be checked
     * @return is it integer or not
     */
    private boolean isInteger(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

    /**
     * Function adds numbers and operation to history
     *
     * @param operation expression with operation and numbers
     */
    private void addToHistoryLabel(Operation operation) {

        if (operation instanceof BinaryOperation) {
            if (!isDigitAddedLast && lastValue != null) {
                calculator.deleteLastInHistory();
            } else {
                if (!(calculator.getLastFromHistory() instanceof SpecialOperation)) {
                    calculator.addToHistory(currentValue);
                }
                lastSpecialAddedToHistory = null;
            }
        } else {
            if (!(calculator.getLastFromHistory() instanceof SpecialOperation)) {
                calculator.addToHistory(currentValue);
            }
            lastSpecialAddedToHistory = (SpecialOperation) operation;
        }
        calculator.addToHistory(operation);
        showHistory();
    }

    private void showHistory() {
        StringBuilder history = new StringBuilder("");
        int historySize = calculator.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            Object obj = calculator.getFromHistory(i);
            String toHistory = null;
            if (obj instanceof BigDecimal) {
                toHistory = makeString((BigDecimal) obj).replace(BIG_NUMBER_SEPARATOR, "");

                while (i + 1 < historySize && calculator.getFromHistory(i + 1) instanceof SpecialOperation) { // for nested operations
                    toHistory = ((SpecialOperation) calculator.getFromHistory(i + 1)).makeOperationSign(toHistory);
                    i++;
                }
            } else if (obj instanceof BinaryOperation) {
                toHistory = ((BinaryOperation) obj).operationSign;
            }

            if (toHistory != null) {
                toHistory += " ";
                history.append(toHistory);
            }
        }
        historyLabel.setText(history.toString());
    }

    /**
     * Function resizes font in main label after resizing window or adding new digits to number
     */
    private void resizeFontMainLabel() {
        mainLabel.setFont(fontForMainLabel);
        double textWidth = computeTextWidth(mainLabel.getFont(), mainLabel.getText());
        double labelWidth = mainLabel.getWidth();
        double additional = 0.05;
        if (labelWidth < textWidth) {
            double scale = textWidth / labelWidth + additional;
            mainLabel.setFont(new Font(fontForMainLabel.getName(), mainLabel.getFont().getSize() / scale));
        }
    }

    /**
     * Function initializes stage and prepares objects for correct working of application
     *
     * @param primaryStage main stage of application
     */
    public void setStageAndInitialize(Stage primaryStage) {
        // fill map with special operations
        operationMap.put(sqrButton, SQR);
        operationMap.put(sqrtButton, SQRT);
        operationMap.put(oneDividedButton, ONE_DIVIDED);
        operationMap.put(signButton, NEGATE);
        operationMap.put(percentButton, PERCENT);

        // fill map with binary operations
        operationMap.put(plusButton, BinaryOperation.PLUS);
        operationMap.put(minusButton, BinaryOperation.MINUS);
        operationMap.put(multiplyButton, BinaryOperation.MULTIPLY);
        operationMap.put(divideButton, BinaryOperation.DIVIDE);


        // initializing lists with buttons
        buttonsWithImage = Arrays.asList(multiplyButton, divideButton, backSpaceButton, signButton,
                sqrButton, sqrtButton, percentButton, oneDividedButton);

        buttonsWithText = Arrays.asList(zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton,
                sevenButton, eightButton, nineButton, commaButton, calculateButton, clearButton, clearCurrentButton,
                plusButton, minusButton);

        // resize window listeners
        ResizeListener r = new ResizeListener(primaryStage);
        Scene scene = primaryStage.getScene();
        scene.setOnMouseMoved(r);
        scene.setOnMousePressed(r);
        scene.setOnMouseDragged(r);

        // keyboard buttons listeners
        stage = primaryStage;
        stage.getScene().setOnKeyPressed(event -> {
            Button button = null;
            KeyCode keyCode = event.getCode();
            if (keyCode == NUMPAD0 || keyCode == DIGIT0) {
                button = zeroButton;
            } else if (keyCode == NUMPAD1 || keyCode == DIGIT1) {
                button = oneButton;
            } else if (keyCode == NUMPAD2 || keyCode == DIGIT2) {
                button = twoButton;
            } else if (keyCode == NUMPAD3 || keyCode == DIGIT3) {
                button = threeButton;
            } else if (keyCode == NUMPAD4 || keyCode == DIGIT4) {
                button = fourButton;
            } else if (keyCode == NUMPAD5 || keyCode == DIGIT5) {
                button = fiveButton;
            } else if (keyCode == NUMPAD6 || keyCode == DIGIT6) {
                button = sixButton;
            } else if (keyCode == NUMPAD7 || keyCode == DIGIT7) {
                button = sevenButton;
            } else if (keyCode == NUMPAD8 || keyCode == DIGIT8) {
                button = eightButton;
            } else if (keyCode == NUMPAD9 || keyCode == DIGIT9) {
                button = nineButton;
            } else if (keyCode == SUBTRACT || keyCode == KeyCode.MINUS) {
                button = minusButton;
            } else if (keyCode == ADD || keyCode == KeyCode.PLUS) {
                button = plusButton;
            } else if (keyCode == KeyCode.MULTIPLY) {
                button = multiplyButton;
            } else if (keyCode == KeyCode.DIVIDE) {
                button = divideButton;
            } else if (keyCode == EQUALS) {
                button = calculateButton;
            } else if (keyCode == BACK_SPACE) {
                button = backSpaceButton;
            } else if (keyCode == COMMA) {
                button = commaButton;
            }
            if (button != null) {
                button.fire();
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
            }
        });

        stage.getScene().setOnKeyReleased(event -> {
            List<Button> buttons = new ArrayList<>(buttonsWithImage);
            buttons.addAll(buttonsWithText);
            for (Button button : buttons) {
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
            }
        });

        // resize content of buttons listener
        stage.widthProperty().addListener((
                (observable, oldValue, newValue) -> {
                    resizeFontButtons();
                    resizeFontMainLabel();
                }));
        stage.heightProperty().addListener((
                (observable, oldValue, newValue) -> {
                    resizeFontButtons();
                    resizeFontMainLabel();
                }));
    }

    /**
     * Function increases or reduces content of buttons after resizing window
     */
    private void resizeFontButtons() {
        List<Button> buttons = new ArrayList<>(buttonsWithImage);
        buttons.addAll(buttonsWithText);
        String imageStyle = "";
        String textStyle = "";
        if (stage.getHeight() > 645.0 && stage.getWidth() > 375.0) {
            imageStyle = extendedBackgroundSize;
            textStyle = extendedFontSize;
        }
        for (Button button : buttonsWithImage) {
            button.setStyle(imageStyle);
        }
        for (Button button : buttonsWithText) {
            button.setStyle(textStyle);
        }

    }

    /**
     * Function for getting wight of String some special font
     *
     * @param font Font of text
     * @param text wight function calculates for
     * @return wight
     */
    private static double computeTextWidth(Font font, String text) {
        Text helper = new Text();
        helper.setText(text);
        helper.setFont(font);
        return helper.getLayoutBounds().getWidth();
    }

    /**
     * Function for converting {@code BigDecimal} value to a {@code String} in exponential system for displaying to user
     *
     * @param bigDecimal number which have to be displayed
     * @return String which represents number for user
     */
    private String convertIntoExp(BigDecimal bigDecimal) {
        String exponentialSymbolSign = SYMBOL_EXP;
        if (!(bigDecimal.compareTo(BigDecimal.ONE.negate()) == 1 && bigDecimal.compareTo(BigDecimal.ONE) == -1)) {
            exponentialSymbolSign += "+";
        }
        symbols.setExponentSeparator(exponentialSymbolSign);

        DecimalFormat expFormatter = new DecimalFormat("0.######E0");
        expFormatter.setDecimalFormatSymbols(symbols);
        expFormatter.setMaximumFractionDigits(MAX_DIGITS_IN_NUMBER - 2);

        String result = expFormatter.format(bigDecimal);
        if (!result.contains(FLOAT_POINT)) {
            int expIndex = result.indexOf(SYMBOL_EXP);
            result = result.substring(0, expIndex) + FLOAT_POINT + result.substring(expIndex);
        }

        return result;
    }

    /**
     * Minimal value which can be displayed not in exponential system
     */
    private final BigDecimal MIN_NUMBER_IN_NORMAL_SYSTEM = new BigDecimal("0.0000000000000001");

    /**
     * Function counts digits in BigInteger value
     *
     * @param number value count of digits will be counted for
     * @return count of digits in {@code number} value
     */
    private int getDigitCount(BigInteger number) {
        number = number.abs();
        double factor = Math.log(2) / Math.log(10);
        int digitCount = (int) (factor * number.bitLength() + 1);
        if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
            return digitCount - 1;
        }
        return digitCount;
    }

    /**
     * Function counts zeros at the beginning of value
     *
     * @param number value for counting
     * @return count of zeros
     */
    private static int zeroStartCounter(BigDecimal number) {
        int zeroCount = 0;
        while (number.toBigInteger().equals(BigInteger.ZERO) && number.compareTo(BigDecimal.ZERO) != 0) {
            zeroCount++;
            number = number.movePointRight(1);
        }
        return zeroCount;
    }



    /**
     * Function for converting {@code BigDecimal} value to a {@code String} value for displaying to user
     *
     * @param val number which have to be displayed
     * @return String which represents number for user
     * @throws NullPointerException If param {@code val} is empty
     */
    public String makeString(BigDecimal val) throws ArithmeticException {
        String result;
        symbols.setDecimalSeparator(FLOAT_POINT.charAt(0));
        symbols.setGroupingSeparator(BIG_NUMBER_SEPARATOR.charAt(0));
        formatter.setDecimalFormatSymbols(symbols);
        if (inputting && isValueFloat) {
            formatter.setMinimumFractionDigits(val.scale());
            result = formatter.format(val);
            //result += FLOAT_POINT + valStr.substring(valStr.indexOf('.') + 1); // todo: use formatter here
            formatter.setMinimumFractionDigits(0);
        } else if (val.abs().compareTo(MAX_NUMBER_IN_NORMAL_SYSTEM) == 1
                || (val.abs().compareTo(MIN_NUMBER_IN_NORMAL_SYSTEM) == -1 && val.compareTo(BigDecimal.ZERO) != 0)
                || (val.stripTrailingZeros().precision() >= MAX_DIGITS_IN_NUMBER - MAX_COUNT_OF_FIRST_ZEROS - 1 && zeroStartCounter(val) >= MAX_COUNT_OF_FIRST_ZEROS)) {
            result = convertIntoExp(val);
        } else {
            int fractionDigits = MAX_DIGITS_IN_NUMBER - getDigitCount(val.toBigInteger()) - 1;
            formatter.setRoundingMode(RoundingMode.HALF_UP);
            formatter.setMaximumFractionDigits(fractionDigits);

            result = formatter.format(val);
        }
        return result;
    }

    /**
     * Function for converting String value to a {@code BigDecimal} value for doing calculations
     *
     * @param numberStr String got from label of calculator
     * @return {@code BigDecimal} made from {@code String} inputted in label
     * @throws NullPointerException  If param {@code number} is empty
     * @throws NumberFormatException If param {@code number} given in inappropriate form as for number
     */
    public BigDecimal makeDecimal(String numberStr) {
        if (numberStr.length() == 0) {
            throw new NullPointerException();
        }
        numberStr = numberStr.replace(FLOAT_POINT, ".").replace(BIG_NUMBER_SEPARATOR, "").replace(SYMBOL_EXP, "E");
        return new BigDecimal(numberStr);
    }
}
