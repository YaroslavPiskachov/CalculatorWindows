package calculatorTests;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.loadui.testfx.GuiTest.find;

/**
 * Created by Yaroslav on 14.08.2017.
 */

public class ResizeFontTest extends MyTestBox {

    private String extendedFontSize = "-fx-font-size: 26px";

    private String extendedBackgroundSize = "-fx-background-size: 85 60";

    @Test
    public void resizeFontMainLabelTest() {
        resizeFontTestTemplate("999", 45);
        resizeFontTestTemplate("9999999999", 45);
        resizeFontTestTemplate("99999999999", 42);
        resizeFontTestTemplate("999999999999", 39);
        resizeFontTestTemplate("9999999999999", 35);
        resizeFontTestTemplate("99999999999999", 33);
        resizeFontTestTemplate("999999999999999", 31);
        resizeFontTestTemplate("9999999999999999", 29);
        resizeFontTestTemplate("999999999999999999999", 29);
    }

    private void resizeFontTestTemplate(String number, int expectedSize) {
        executeLine(number);
        assertEquals(expectedSize, (int) mainLabel.getFont().getSize());
        clickOn("C");
    }

    @Test
    public void resizeButtonsContentTest(){
        resizeButtonsContentTestTemplate(-63,-91,"","");
        resizeButtonsContentTestTemplate(-1,-1,"","");
        resizeButtonsContentTestTemplate(0,0,"","");
        resizeButtonsContentTestTemplate(13,8,"","");
        resizeButtonsContentTestTemplate(22,41,"","");
        resizeButtonsContentTestTemplate(45,130,"","");
        resizeButtonsContentTestTemplate(12,150,"","");
        resizeButtonsContentTestTemplate(51,118,"","");


        resizeButtonsContentTestTemplate(41,138,"","");
        resizeButtonsContentTestTemplate(42,139,extendedFontSize,extendedBackgroundSize);

        resizeButtonsContentTestTemplate(90,145,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(104,210,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(111,185,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(141,140,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(156,151,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(516,147,extendedFontSize,extendedBackgroundSize);
        resizeButtonsContentTestTemplate(892,340,extendedFontSize,extendedBackgroundSize);

        // full screen test

        testButtonsContent("","");
        clickOn("#extendButton");
        testButtonsContent(extendedFontSize,extendedBackgroundSize);
        clickOn("#extendButton");
        testButtonsContent("","");
    }

    private void resizeButtonsContentTestTemplate(double x, double y, String expectedFont, String expectedBackground){
        testButtonsContent("","");
        moveTo(stage.getX() + stage.getWidth() - 1,stage.getY() + stage.getHeight() - 1).drag(MouseButton.PRIMARY).moveBy(x,y);
        testButtonsContent(expectedFont,expectedBackground);
        drag(MouseButton.PRIMARY).moveBy(-x, -y);
        release(MouseButton.PRIMARY);
        testButtonsContent("","");
    }

    private void testButtonsContent(String expectedFont, String expectedBackground){
        for (ButtonEnum buttonEnum: ButtonEnum.values()) {
            Button b = find(buttonEnum.fxId);
            if(buttonEnum == ButtonEnum.M_MINUS || buttonEnum == ButtonEnum.M_PLUS || buttonEnum == ButtonEnum.MC || buttonEnum == ButtonEnum.MR || buttonEnum == ButtonEnum.MS){
                assertEquals("",b.getStyle());
                continue;
            }
            if(buttonEnum.isFontResize){
                assertEquals(expectedFont,b.getStyle());
            } else {

                assertEquals(expectedBackground,b.getStyle());
            }
        }
    }

}
