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
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String lastSpecialAddedToHistory;

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
        String toHistory;

        if (lastSpecialAddedToHistory == null) {
            toHistory = makeString(currentValue).replace(BIG_NUMBER_SEPARATOR, "");
        } else {
            toHistory = lastSpecialAddedToHistory;
        }

        if (!(specialOperation == NEGATE && lastSpecialAddedToHistory == null)) {
            addToHistoryLabel(specialOperation.getOperationSign(toHistory));
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
            //replaceValue = true;
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
    private void printException(Exception e){
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
        }catch (OverflowException r){
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
        addToHistoryLabel(binaryOperation.operationSign);
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
        //isValueFloat = false;
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
        String[] history = historyLabel.getText().split(" ");
        if (history.length > 1) {
            String toHistory = "";
            int deleteElements = 2;
            String lastStr = history[history.length - 1];
            int countOfOperations = 0;
            for (int i = 2; lastStr.equals(")"); i++) {
                countOfOperations++;
                lastStr = history[history.length - i];
            }
            deleteElements *= countOfOperations;
            if (deleteElements != 0) {
                deleteElements++;
            }
            for (int i = 0; i < history.length - deleteElements; i++) {
                toHistory += history[i] + " ";
            }
            historyLabel.setText(toHistory);
        }
        isOperationSpecial = false;
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
        } else if (isValueFloat) {
            toMainLabel = makeString(val);
            if (inMainLabel.contains(FLOAT_POINT) && currentValue.scale() == 0 && val.equals(currentValue)) {
                toMainLabel = toMainLabel.replace(FLOAT_POINT, "");
                isValueFloat = false;
            } else if (!toMainLabel.contains(FLOAT_POINT) && inputting) {
                toMainLabel += FLOAT_POINT;
            }
        } else {
            if (isIntegerNormal(val) && val.scale() != 0 ) {
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
            return;                                                                     // number too long
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

            if (!isIntegerNormal(newCurrentValue)) {
                newDigit = newDigit.movePointLeft(1);
            }

            newDigit = newDigit.movePointLeft(newCurrentValue.scale());

            if (new BigDecimal(digit).equals(BigDecimal.ZERO)) {
                int newScale = newCurrentValue.scale() + 1;

                newCurrentValue = newCurrentValue.setScale(newScale);
            } else {
                if (isIntegerNormal(newCurrentValue) || newCurrentValue.compareTo(BigDecimal.ZERO) == 0) {
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

    private boolean isIntegerNormal(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

    /**
     * Function adds numbers and operation to history
     *
     * @param str expression with operation and numbers
     */
    private void addToHistoryLabel(String str) {
        String history = historyLabel.getText();
        if (!isOperationSpecial) {
            if (!isDigitAddedLast && lastValue != null) {
                history = history.substring(0, history.length() - 3) + str; // history without last operation symbol + new operation symbol
            } else {
                if (history.charAt(history.length() - 1) == ')') {
                    history += str;
                } else {
                    history += makeString(currentValue).replace(" ", "") + str;
                }
                lastSpecialAddedToHistory = null;
            }
        } else {
            deleteLastSpecialInHistory();
            history = historyLabel.getText() + str;
            lastSpecialAddedToHistory = str;
        }
        historyLabel.setText(history);
    }

    /**
     * Function resizes font in main label after resizing window or adding new digits to number
     */
    private void resizeFontMainLabel() {
        mainLabel.setFont(fontForMainLabel);
        double textWidth = computeTextWidth(mainLabel.getFont(), mainLabel.getText());
        if (mainLabel.getWidth() < textWidth) {
            double scale = textWidth / mainLabel.getWidth() + 0.05;
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
            } else if (keyCode == L) {
                System.out.println(currentValue + " - current value");
                System.out.println(currentValue.scale() + " - scale of current value");
                System.out.println(lastValue + " - last value");
            }
            if (button != null) {
                button.fire();
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
            }
        });

        stage.getScene().setOnKeyReleased(event -> {
            for (Button button : buttonsWithImage) {
                button.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
            }
            for (Button button : buttonsWithText) {
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

        if (stage.getHeight() > 645.0 && stage.getWidth() > 375.0) {
            for (Button button : buttonsWithImage) {
                button.setStyle(extendedBackgroundSize);
            }
            for (Button button : buttonsWithText) {
                button.setStyle(extendedFontSize);
            }
        } else {
            for (Button button : buttonsWithImage) {
                button.setStyle("");
            }
            for (Button button1 : buttonsWithText) {
                button1.setStyle("");
            }
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
        if (bigDecimal.toPlainString().length() <= 18 && bigDecimal.compareTo(BigDecimal.ONE) == -1) {
            return bigDecimal.toPlainString().replace(".", FLOAT_POINT);
        }
        String res = (bigDecimal.round(MathContext.DECIMAL64) + "")
                .replace("E-", SYMBOL_EXP + "-")
                .replace("E+", SYMBOL_EXP + "+")
                .replace(".", FLOAT_POINT);


        int indexOfExp = res.indexOf(SYMBOL_EXP);
        String afterExp = res.substring(indexOfExp);
        res = res.substring(0, indexOfExp);
        if (res.length() > MAX_DIGITS_IN_NUMBER - 1) {
            res = res.substring(0, MAX_DIGITS_IN_NUMBER) + res.substring(indexOfExp);
        }

        while (res.charAt(res.length() - 1) == '0') {
            res = res.substring(0, res.length() - 1);
        }

        double val = Double.valueOf(res.replace(FLOAT_POINT, "."));
        if (val == (int) val && !res.contains(FLOAT_POINT)) {
            res = res + FLOAT_POINT;
        }

        return res + afterExp;
    }

    /**
     * Function for converting {@code BigDecimal} value to a {@code String} value for displaying to user
     *
     * @param val number which have to be displayed
     * @return String which represents number for user
     * @throws NullPointerException If param {@code val} is empty
     */
    public String makeString(BigDecimal val) throws ArithmeticException {
        String result = null;
        boolean isNegate = false;
        if (val.compareTo(BigDecimal.ZERO) == -1) {
            val = val.negate();
            isNegate = true;
        }

        if (val.compareTo(MAX_NUMBER_IN_NORMAL_SYSTEM) == 1
                || val.compareTo(MAX_NUMBER_IN_NORMAL_SYSTEM.negate()) == -1
                || val.toString().contains("E")) {
            result = convertIntoExp(val);
        }

        val = val.round(MathContext.DECIMAL64);
        StringBuilder numberStr = new StringBuilder(val.toPlainString()
                .replace("E+", SYMBOL_EXP + "+")
                .replace("E-", SYMBOL_EXP + "-")
                .replace(".", FLOAT_POINT));

        String afterPoint = "";

        int indexOfPoint = numberStr.indexOf(FLOAT_POINT);

        if (indexOfPoint != -1 && result == null) {

            afterPoint = numberStr.substring(indexOfPoint);
            numberStr.delete(indexOfPoint, numberStr.length());

            if (afterPoint.length() > MAX_DIGITS_IN_NUMBER) {
                afterPoint = afterPoint.substring(0, MAX_DIGITS_IN_NUMBER);
            }
        }

        if (result == null) {
            if (numberStr.toString().length() < 4) {
                result = numberStr + afterPoint;
            } else {
                for (int i = numberStr.toString().length() - 4; i >= 0; i -= 3) {
                    numberStr.insert(i + 1, BIG_NUMBER_SEPARATOR);
                }
                result = numberStr + afterPoint;
            }
        }

        if (isNegate) {
            result = "-" + result;
        }
        if (!inputting && !afterPoint.equals("")) {
            char lastSymbol = result.charAt(result.length() - 1);
            while (lastSymbol == '0') {
                result = result.substring(0, result.length() - 1);
                lastSymbol = result.charAt(result.length() - 1);
            }
            if(lastSymbol == ','){
                result = result.substring(0, result.length() - 1);
            }
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

    /**
     * Function defines if string consist of only one type of characters
     *
     * @param string    value will be checked
     * @param character symbols for checking
     * @return boolean if string consist of only such characters
     */
    private boolean stringContainsOnly(String string, char character) {
        for (char symbol : string.toCharArray()) {
            if (symbol != character) {
                return false;
            }
        }
        return true;
    }


}
