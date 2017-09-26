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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.List;

import static javafx.scene.input.KeyCode.*;
import static model.SpecialOperation.*;


/**
 * Class represents controller for window of Application
 * Class consist of methods which are event listeners for every button in Application
 * and some methods for their support.
 * Also there are methods for correct displaying data
 *
 * @author Yaroslav Piskachov
 */

public class Controller {

   /**
    * If height greater than this valuer resizing is needed
    */
   private static final double CONTENT_BUTTON_LIMIT_HEIGHT = 645.0;

   /**
    * If width greater than this valuer resizing is needed
    */
   private static final double CONTENT_BUTTON_LIMIT_WIDTH = 375.0;

   /**
    * Define font of numbers in main label of window
    */
   private static final Font fontForMainLabel = new Font("Microsoft JhengHei UI Bold", 45);

   /**
    * Css style of font of buttons with text after increasing window size
    */
   private static final String EXTENDED_FONT_SIZE = "-fx-font-size: 26px";

   /**
    * Css style of background size for buttons with image after increasing window size
    */
   private static final String EXTENDED_BACKGROUND_SIZE = "-fx-background-size: 85 60";

   /**
    * Max count of zeros that can be at the beginning of number
    */
   private static final int MAX_COUNT_OF_FIRST_ZEROS = 4;

   /**
    * Max number in normal system for doing calculations
    */
   private static final BigDecimal MAX_NUMBER_IN_NORMAL_SYSTEM = new BigDecimal("9999999999999999.4999999999999999");

   /**
    * Minimal value which can be displayed not in exponential system
    */
   private static final BigDecimal MIN_NUMBER_IN_NORMAL_SYSTEM = new BigDecimal("0.0000000000000001");

   /**
    * Max count of digits in number calculator can display
    */
   private static final int MAX_DIGITS_IN_NUMBER = 17;

   /**
    * Symbol which defines number as in exponential system
    */
   private static final String SYMBOL_EXP = "e";

   /**
    * Symbol which separates parts of float number
    */
   private static final String FLOAT_POINT = ",";

   /**
    * Symbol which separates parts of big number by three digits
    */
   private static final String BIG_NUMBER_SEPARATOR = " ";

   /**
    * Value which is defined as initial for calculator display
    */
   private static final BigDecimal DEFAULT_VALUE = BigDecimal.ZERO;

   /**
    * The greatest value calculator can work with
    */
   private static final BigDecimal MAX_VALUE = new BigDecimal("1.000000000000000E+10000");

   /**
    * The nearest positive value to zero
    */
   private static final BigDecimal MIN_POSITIVE_VALUE = new BigDecimal("1E-9999");

   /**
    * The nearest negative value to zero
    */
   private static final BigDecimal MAX_NEGATIVE_VALUE = new BigDecimal("-1E-9999");

   /**
    * Minimal value calculator can work with
    */
   private static final BigDecimal MIN_VALUE = new BigDecimal("-9.999999999999999E+9999");

   /**
    * Massage will be shown to user when division by zero exception was thrown
    */
   private static final String DIVISION_BY_ZERO_MSG = "Деление на 0";

   /**
    * Massage will be shown to user when negative value for sqrt exception was thrown
    */
   private static final String NEGATIVE_VALUE_FOR_SQRT_EXCEPTION_MSG = "Введены неверные данные";

   /**
    * Default pattern according to out localization
    */
   private static final String DEFAULT_PATTERN = "#,##0.###;-#,##0.###";

   /**
    * Pattern which is used by formatter when exponential system is needed
    */
   private static final String PATTERN_FOR_EXP = "0.######E0";

   /**
    * Pattern which is used by formatter when digit is float
    */
   private static final String PATTERN_FOR_FLOAT = "#.";

   /**
    * Sign for minus operation or negate sign for value in exp systen
    */
   private static final String MINUS_SIGN = "-";

   /**
    * Sign for plus operation or negate sign for value in exp systen
    */
   private static final String PLUS_SIGN = "+";

   /**
    * Model of calculator
    */
   private Calculator calculator = new Calculator();

   /**
    * Previous inputted value for calculations
    */
   private BigDecimal lastValue;

   /**
    * Collection for mapping operations and id's
    */
   private Map<Button, Operation> operationMap = new HashMap<>();

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
    * Text object for measuring string width
    */
   private Text helper = new Text();

   /**
    * Variable describes is user work with inputting right now
    */
   private boolean inputting = false;

   /**
    * Variable describes is user work with float value right now
    */
   private boolean isValueFloat = false;

   /**
    * Defines is putting comma needed
    */
   private boolean putComma;

   /**
    * Defines if zero with negative sign on a screen
    */
   private boolean isNegativeZero;

   /**
    * Collection for mapping operations with their symbols
    */
   private Map<BinaryOperation, String> binaryOperationStringMap = new HashMap<>();


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
      int digit = buttonsWithText.indexOf(pressed); // in the list every button with digit has the same index as digit in this button
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
      if (!isValueFloat) {
         isValueFloat = true;
         inputting = true;
         putComma = true;
         displayValue(currentValue);
         putComma = false;
         inputting = false;
         replaceValue = false;
      }
   }

   /**
    * Function represents listener on button for clearing only value in main label
    */
   @FXML
   public void clearCurrentButton() throws OverflowException {
      deleteLastSpecialInHistory();
      lastSpecialAddedToHistory = null;
      isNegativeZero = false;
      isValueFloat = false;
      displayValue(DEFAULT_VALUE);
   }

   /**
    * Function represents listener on button for clearing all elements
    */
   @FXML
   public void clearAllButton() {
      setDisableButtons(false);
      binaryOperation = null;
      currentValue = DEFAULT_VALUE;
      lastValue = null;

      try {
         displayValue(DEFAULT_VALUE);
      } catch (OverflowException e) {
         closeWindowButton();
         throw new IllegalArgumentException("Default value - "+ DEFAULT_VALUE +" cannot be bigger than max value - " + MAX_VALUE
                 + " or less than min value - " + MAX_VALUE.negate());
      }

      historyLabel.setText("");
      calculator.makeHistoryEmpty();
      isCalculateButtonLast = false;
      isValueFloat = false;
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
      if (putComma) {
         isNegativeZero = false;
         putComma = false;
         displayValue(currentValue);
      } else if (!replaceValue) {
         int floatDigits = 0;
         BigDecimal value = currentValue;
         if (currentValue.signum() == -1 && currentValue.precision() == 1) { // if value is negative and has only last not zero digit; example -0,0004
            isNegativeZero = true;
         }
         while (value.scale() != 0) {
            floatDigits++;
            value = value.movePointRight(1);
         }

         value = calculator.executeOperation(BinaryOperation.DIVIDE, BigDecimal.TEN, value);
         value = value.setScale(0, BigDecimal.ROUND_DOWN);

         if (floatDigits != 0) {
            value = value.movePointLeft(floatDigits - 1);
            if (floatDigits == 1 && value.scale() == 0) {
               putComma = true;
            }
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
         historyLabel.setText("");
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

      if (specialOperation != NEGATE || lastSpecialAddedToHistory != null) { //
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
         toMainLabel = DIVISION_BY_ZERO_MSG;
      } else if (e instanceof NegativeValueForSqrtException) {
         toMainLabel = NEGATIVE_VALUE_FOR_SQRT_EXCEPTION_MSG;
      } else if (e instanceof OverflowException) {
         toMainLabel = overflowExceptionMsg;
      } else {
         toMainLabel = "Ошибка";
      }

      String history = historyLabel.getText();

      clearAllButton();

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
      for (int i = historySize - 1; i >= 0; i--) {

         if (calculator.getFromHistory(i) instanceof SpecialOperation) {
            inLoop = true;
            calculator.deleteLastInHistory();
         } else {
            break;
         }
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
      if ((val.compareTo(MAX_VALUE) > 0 || val.compareTo(MAX_VALUE) == 0 || val.compareTo(MIN_VALUE) < 0)
              || (val.compareTo(MIN_POSITIVE_VALUE) < 0 && val.compareTo(BigDecimal.ZERO) > 0)
              || (val.compareTo(MAX_NEGATIVE_VALUE) > 0 && val.compareTo(BigDecimal.ZERO) < 0)) {
         throw new OverflowException();
      }

      currentValue = val;
      mainLabel.setText(makeString(val));
      isDigitAddedLast = true;
      resizeFontMainLabel();
   }


   /**
    * Function adds digits to number which is in main label
    *
    * @param digit digit will be added to number on display
    */
   private void addToMainLabel(int digit) throws DivisionByZeroException, OverflowException {
      putComma = false;
      inputting = true;
      if (replaceValue) {
         isValueFloat = false;
         currentValue = DEFAULT_VALUE;
      }

      int countOfZeros = zeroStartCounter(currentValue);
      int precision = currentValue.precision();

      if ((countOfZeros > 0 && precision == MAX_DIGITS_IN_NUMBER)
              || countOfZeros == 0 && precision == MAX_DIGITS_IN_NUMBER - 1) {
         inputting = false;
         return; // number too long
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
    * {@see https://stackoverflow.com/questions/1078953/check-if-bigdecimal-is-integer-value}
    * <p>
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

   /**
    * Function fills history label
    */
   private void showHistory() {
      StringBuilder history = new StringBuilder("");
      int historySize = calculator.getHistorySize();

      for (int i = 0; i < historySize; i++) {
         Object obj = calculator.getFromHistory(i);

         String toHistory = null;
         if (obj instanceof BigDecimal) {
            toHistory = makeString((BigDecimal) obj).replace(BIG_NUMBER_SEPARATOR, ""); // history needs the same formatting but with out group separating

            for (int j = i; j + 1 < historySize; j++) {
               Object sp = calculator.getFromHistory(j + 1);

               if (sp instanceof SpecialOperation) { // for nested operations
                  toHistory = specialOperationSign((SpecialOperation) sp, toHistory);
               } else {
                  break;
               }
            }

         } else if (obj instanceof BinaryOperation) {
            toHistory = binaryOperationStringMap.get(obj);
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
      double textWidth = computeTextWidth(fontForMainLabel, mainLabel.getText());
      double labelWidth = mainLabel.getWidth();
      double additional = 0.05;
      if (labelWidth < textWidth) {
         double scale = textWidth / labelWidth + additional;
         mainLabel.setFont(new Font(fontForMainLabel.getName(), fontForMainLabel.getSize() / scale));
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


      //mapping keys and their button analogs
      Map<KeyCode, Button> keyCodeButtonMap = new HashMap<>();
      keyCodeButtonMap.put(NUMPAD0, zeroButton);
      keyCodeButtonMap.put(DIGIT0, zeroButton);
      keyCodeButtonMap.put(NUMPAD1, oneButton);
      keyCodeButtonMap.put(DIGIT1, oneButton);
      keyCodeButtonMap.put(NUMPAD2, twoButton);
      keyCodeButtonMap.put(DIGIT2, twoButton);
      keyCodeButtonMap.put(NUMPAD3, threeButton);
      keyCodeButtonMap.put(DIGIT3, threeButton);
      keyCodeButtonMap.put(NUMPAD4, fourButton);
      keyCodeButtonMap.put(DIGIT4, fourButton);
      keyCodeButtonMap.put(NUMPAD5, fiveButton);
      keyCodeButtonMap.put(DIGIT5, fiveButton);
      keyCodeButtonMap.put(NUMPAD6, sixButton);
      keyCodeButtonMap.put(DIGIT6, sixButton);
      keyCodeButtonMap.put(NUMPAD7, sevenButton);
      keyCodeButtonMap.put(DIGIT7, sevenButton);
      keyCodeButtonMap.put(NUMPAD8, eightButton);
      keyCodeButtonMap.put(DIGIT8, eightButton);
      keyCodeButtonMap.put(NUMPAD9, nineButton);
      keyCodeButtonMap.put(DIGIT9, nineButton);
      keyCodeButtonMap.put(SUBTRACT, minusButton);
      keyCodeButtonMap.put(MINUS, minusButton);
      keyCodeButtonMap.put(PLUS, plusButton);
      keyCodeButtonMap.put(ADD, plusButton);
      keyCodeButtonMap.put(MULTIPLY, multiplyButton);
      keyCodeButtonMap.put(DIVIDE, divideButton);
      keyCodeButtonMap.put(EQUALS, calculateButton);
      keyCodeButtonMap.put(COMMA, commaButton);


      // keyboard buttons listeners
      stage = primaryStage;
      stage.getScene().setOnKeyPressed(event -> {
         KeyCode keyCode = event.getCode();
         Button button = keyCodeButtonMap.get(keyCode);
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
      stage.widthProperty().addListener(
              (observable, oldValue, newValue) -> {
                 resizeFontButtons();
                 resizeFontMainLabel();
              });
      stage.heightProperty().addListener((
              (observable, oldValue, newValue) -> {
                 resizeFontButtons();
                 resizeFontMainLabel();
              }));

      // mapping binary operations with symbols
      binaryOperationStringMap.put(BinaryOperation.PLUS, PLUS_SIGN);
      binaryOperationStringMap.put(BinaryOperation.MINUS, MINUS_SIGN);
      binaryOperationStringMap.put(BinaryOperation.MULTIPLY, "×");
      binaryOperationStringMap.put(BinaryOperation.DIVIDE, "÷");

      symbols.setDecimalSeparator(FLOAT_POINT.charAt(0));
      symbols.setGroupingSeparator(BIG_NUMBER_SEPARATOR.charAt(0));
      formatter.setGroupingSize(3);
      formatter.setGroupingUsed(true);

   }

   /**
    * Function increases or reduces content of buttons after resizing window
    */
   private void resizeFontButtons() {
      List<Button> buttons = new ArrayList<>(buttonsWithImage);
      buttons.addAll(buttonsWithText);
      String imageStyle;
      String textStyle;
      if (stage.getHeight() > CONTENT_BUTTON_LIMIT_HEIGHT && stage.getWidth() > CONTENT_BUTTON_LIMIT_WIDTH) {
         imageStyle = EXTENDED_BACKGROUND_SIZE;
         textStyle = EXTENDED_FONT_SIZE;
      } else {
         imageStyle = "";
         textStyle = "";
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
   private double computeTextWidth(Font font, String text) {
      helper.setText(text);
      helper.setFont(font);
      return helper.getLayoutBounds().getWidth();
   }

   /**
    * {@see https://stackoverflow.com/questions/18828377/biginteger-count-the-number-of-decimal-digits-in-a-scalable-method}
    * <p>
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
      while (number.setScale(0, RoundingMode.DOWN).signum() == 0 && number.compareTo(BigDecimal.ZERO) != 0) {
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
   private String makeString(BigDecimal val) throws ArithmeticException {
      String result;
      String pattern = DEFAULT_PATTERN;
      String exponentialSymbolSign = SYMBOL_EXP;


      int minFractDigits = 0;
      int maxFractDigits = MAX_DIGITS_IN_NUMBER - 1;

      if (specialOperation == NEGATE && isValueFloat) {
         if (val.signum() == 0) {
            if (isNegativeZero) {
               isNegativeZero = false;
               pattern = "";
            } else {
               isNegativeZero = true;
               pattern = MINUS_SIGN;
            }
         }

         minFractDigits = val.scale();
         pattern += PATTERN_FOR_FLOAT;
      } else if (inputting && isValueFloat) {
         if (putComma) {
            pattern = PATTERN_FOR_FLOAT;
         } else if (val.scale() == 0) {
            isValueFloat = false;
         } else {
            minFractDigits = val.scale();
         }

         if (isNegativeZero && !isValueFloat) {
            pattern = MINUS_SIGN + pattern;
         }
      } else if (val.abs().compareTo(MAX_NUMBER_IN_NORMAL_SYSTEM) > 0
              || (val.abs().compareTo(MIN_NUMBER_IN_NORMAL_SYSTEM) < 0 && val.compareTo(BigDecimal.ZERO) != 0)
              || (val.stripTrailingZeros().precision() >= MAX_DIGITS_IN_NUMBER - MAX_COUNT_OF_FIRST_ZEROS - 1 && zeroStartCounter(val) >= MAX_COUNT_OF_FIRST_ZEROS)) {


         if (val.abs().compareTo(BigDecimal.ONE) > 0) {
            exponentialSymbolSign += PLUS_SIGN;
         }

         if (ifOnlyZerosAround(val.round(MathContext.DECIMAL64))) {
            exponentialSymbolSign = FLOAT_POINT + exponentialSymbolSign;
         }

         pattern = PATTERN_FOR_EXP;
         maxFractDigits = MAX_DIGITS_IN_NUMBER - 2;

      } else {
         maxFractDigits = MAX_DIGITS_IN_NUMBER - getDigitCount(val.toBigInteger()) - 1;
         formatter.setRoundingMode(RoundingMode.HALF_UP);
      }

      symbols.setExponentSeparator(exponentialSymbolSign);
      formatter.setDecimalFormatSymbols(symbols);
      formatter.applyPattern(pattern);

      formatter.setMinimumFractionDigits(minFractDigits);
      formatter.setMaximumFractionDigits(maxFractDigits);
      result = formatter.format(val);
      return result;
   }


   /**
    * Function checks if {@code BigDecimal} value has only one not zero digit
    *
    * @param bd value which will be checked
    * @return if {@code BigDecimal} value has only one not zero digit
    */
   private static boolean ifOnlyZerosAround(BigDecimal bd) {
      boolean res = false;
      bd = bd.stripTrailingZeros().abs();
      if (bd.compareTo(BigDecimal.ONE) < 0 && bd.precision() == 1 && bd.scale() != 0) {
         res = true;
      } else if (bd.compareTo(BigDecimal.ONE) >= 0) {
         BigDecimal before = bd;
         int i = 0;

         while (bd.compareTo(BigDecimal.TEN) > 0) {
            i++;
            bd = bd.movePointLeft(1).setScale(0, RoundingMode.DOWN);
         }

         if (i != 0 && bd.multiply(BigDecimal.TEN.pow(i)).compareTo(before) == 0) {
            res = true;
         }
      }

      return res;
   }

   /**
    * Function for getting special operation appearance
    *
    * @param specialOperation operation whose symbol we need
    * @param str              number or string operation should be displayed with
    * @return String with operation symbol and {@code str}
    */
   private String specialOperationSign(SpecialOperation specialOperation, String str) {
      String res;
      if (specialOperation == SQR) {
         res = "sqr( " + str.replace(".", ",") + " )";
      } else if (specialOperation == SQRT) {
         res = "√( " + str.replace(".", ",") + " )";
      } else if (specialOperation == PERCENT) {
         res = "%( " + str.replace(".", ",") + " )";
      } else if (specialOperation == ONE_DIVIDED) {
         res = "1/( " + str.replace(".", ",") + " )";
      } else if (specialOperation == NEGATE) { // special operation = negate
         res = "negate( " + str.replace(".", ",") + " )";
      } else {
         throw new IllegalArgumentException("wrong operation; Operation is " + specialOperation);
      }
      return res;
   }
}
