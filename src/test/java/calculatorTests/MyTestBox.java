package calculatorTests;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import model.BinaryOperation;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static calculatorTests.MyTestBox.ButtonEnum.*;
import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.find;

/**
 * Created by Yaroslav on 26.07.2017.
 */

public class MyTestBox extends ApplicationTest {
    static Label mainLabel;
    static Label historyLabel;
    static Scene scene;
    static Stage stage;
    private String[] digitButtonId = new String[]{ZERO.fxId, ONE.fxId, TWO.fxId, THREE.fxId, FOUR.fxId,
            FIVE.fxId, SIX.fxId, SEVEN.fxId, EIGHT.fxId, NINE.fxId};
    Map<BinaryOperation, String> binaryOperationStringMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        if(!primaryStage.isShowing()) {
            primaryStage.initStyle(StageStyle.UNDECORATED);
        }

        primaryStage.getIcons().add(new Image("/calc_Ico/calc1.png"));
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(new Scene(root, 334, 507));
        primaryStage.setMinWidth(334);
        primaryStage.setMinHeight(507);
        Controller controller = loader.getController();
        controller.setStageAndInitialize(primaryStage);
        primaryStage.show();

        mainLabel = find("#mainLabel");
        historyLabel = find("#historyLabel");
        scene = primaryStage.getScene();
        stage = primaryStage;

        binaryOperationStringMap.put(BinaryOperation.PLUS,"+");
        binaryOperationStringMap.put(BinaryOperation.MINUS,"-");
        binaryOperationStringMap.put(BinaryOperation.MULTIPLY,"×");
        binaryOperationStringMap.put(BinaryOperation.DIVIDE,"÷");
    }

    void clickOn(String identifier) {
        if (Character.isDigit(identifier.charAt(0))) {
            identifier = digitButtonId[Integer.parseInt(identifier)];
        }
        Button b = find(identifier);
        this.interact(b::fire);
    }

    void combinationTestTemplate(String operationLine, String expectedAnswer, String expectedHistory) {
        executeLine(operationLine);

        assertEquals(expectedAnswer, mainLabel.getText());
        assertEquals(expectedHistory, historyLabel.getText().trim());
        clickOn("C");
    }

    void combinationTestTemplate(String operationLine, String expectedAnswer) {
        executeLine(operationLine);
        assertEquals(expectedAnswer, mainLabel.getText());
        String expectedHistory = "";

        if (!operationLine.contains("=")) {
            expectedHistory += operationLine.replace("*", "×").replace("/", "÷").replaceAll("( )+", " ").replace("÷(", "/(");
        }

        char[] historyArray = expectedHistory.toCharArray();

        assertEquals(stringFromArray(historyArray), historyLabel.getText().trim());
        clickOn("C");
    }

    private String stringFromArray(char[] string) {
        String res = "";
        for (char s : string) {
            res += s + "";
        }
        return res;
    }

    public void executeLine(String operationLine) {
        isRegularButtonsDisableTest(false);
        isMemoryButtonsDisableTest(true);
        char[] operationLineArray = operationLine.toCharArray();
        for (int i = 0; i < operationLineArray.length; i++) {
            String buttonId = null;
            char symbol = operationLineArray[i];
            if (symbol == ' ') {
                continue;
            }

            if (symbol == ',') {
                buttonId = COMMA.fxId;
            } else if (symbol == '±') {
                buttonId = NEGATE.fxId;
            } else if (symbol == '%') {
                buttonId = PERCENT.fxId;
            } else if (symbol == '√') {
                buttonId = SQRT.fxId;
            } else if (symbol == 's') {
                buttonId = SQR.fxId;
            } else if (symbol == '1') {
                if (i <= operationLineArray.length - 3 && operationLineArray[i + 2] == 'x') {
                    buttonId = ONE_DIVIDED.fxId;
                    i+=2;
                } else {
                    buttonId = "1";
                }
            } else if (Character.isDigit(symbol)) {
                buttonId = symbol + "";
            } else if (symbol == 'M') {
                if (operationLineArray[i + 1] == 'S') {
                    buttonId = MS.fxId;
                } else if (operationLineArray[i + 1] == 'R') {
                    buttonId = MR.fxId;
                } else if (operationLineArray[i + 1] == 'C') {
                    buttonId = MC.fxId;
                } else if (operationLineArray[i + 1] == '+') {
                    buttonId = M_PLUS.fxId;
                } else if (operationLineArray[i + 1] == '-') {
                    buttonId = M_MINUS.fxId;
                }
                i++;
            } else if (symbol == 'C') {
                if (i < operationLineArray.length && operationLineArray[i + 1] == 'E') {
                    buttonId = CLEAR_CURRENT.fxId;
                } else {
                    buttonId = CLEAR.fxId;
                }
            } else if (symbol == 'b') {
                buttonId = BACKSPACE.fxId;
            } else if (symbol == '/') {
                buttonId = DIVIDE.fxId;
            } else if (symbol == '+') {
                buttonId = PLUS.fxId;
            } else if (symbol == '*') {
                buttonId = MULTIPLY.fxId;
            } else if (symbol == '-') {
                buttonId = MINUS.fxId;
            } else if (symbol == '=') {
                buttonId = CALCULATE.fxId;
            }

            if(buttonId != null) {
                clickOn(buttonId);
            }
        }
        if (mainLabel.getText().matches("[a-zA-Z]+")) {       // if there is exception on main label
            isRegularButtonsDisableTest(true);
        } else {
            clickOn(MC.fxId);
            isMemoryButtonsDisableTest(true);
        }
    }

    private void isMemoryButtonsDisableTest(boolean isDisableExpected) {
        Button mRead = find(MR.fxId);
        Button mClear = find(MC.fxId);
        assertEquals(isDisableExpected, mRead.isDisable());
        assertEquals(isDisableExpected, mClear.isDisable());
    }

    private void isRegularButtonsDisableTest(boolean isDisableExpected) {
        List<ButtonEnum> buttonsChanging = Arrays.asList(PERCENT, SQR, SQRT, ONE_DIVIDED, DIVIDE, MULTIPLY, MINUS, PLUS, NEGATE,
                M_MINUS, M_PLUS); // buttons which changes own disable status if there is a mistake

        for (ButtonEnum buttonEnum : ButtonEnum.values()) {
            Button b = find(buttonEnum.fxId);
            if (buttonsChanging.contains(buttonEnum)) {
                assertEquals(isDisableExpected, b.isDisable());
            } else {
                if (buttonEnum != MC && buttonEnum != MR)
                    assertEquals(false, b.isDisable());
            }
        }
    }
    enum ButtonEnum {
        // binary operations
        PLUS("#plusButton", true),
        MINUS("#minusButton", true),
        MULTIPLY("#multiplyButton", false),
        DIVIDE("#divideButton", false),

        //special operations
        SQR("#SqrButton", false),
        SQRT("#SqrtButton", false),
        ONE_DIVIDED("#oneDividedButton", false),
        NEGATE("#PlusMinusButton", false),
        PERCENT("#PercentButton", false),

        // digits
        ONE("#oneButton", true),
        TWO("#twoButton", true),
        THREE("#threeButton", true),
        FOUR("#fourButton", true),
        FIVE("#fiveButton", true),
        SIX("#sixButton", true),
        SEVEN("#sevenButton", true),
        EIGHT("#eightButton", true),
        NINE("#nineButton", true),
        ZERO("#zeroButton", true),

        // memory buttons
        M_PLUS("#memoryPlusButton", false),
        M_MINUS("#memoryMinusButton", false),
        MC("#memoryClearButton", false),
        MR("#memoryReadButton", false),
        MS("#memorySaveButton", false),

        // others buttons
        CLEAR("#clearButton", true),
        CLEAR_CURRENT("#clearCurrentButton", true),
        BACKSPACE("#backspaceButton", false),
        COMMA("#commaButton", true),
        CALCULATE("#calculateButton", true);

        /**
         * fx id of button written in xml
         */
        public String fxId;

        /**
         * content button is text or background
         */
        public boolean isFontResize;

        ButtonEnum(String fxId, boolean isFontResize) {
            this.fxId = fxId;
            this.isFontResize = isFontResize;
        }
    }
}


