package calculatorTests;

import javafx.scene.input.MouseButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Yaroslav on 03.08.2017.
 */

public class ResizeWindowTest extends MyTestBox {
    private double previousWidth;
    private double previousHeight;

    @Before
    public void setUp() {
        previousWidth = scene.getWidth();
        previousHeight = scene.getHeight();
    }

    @Test
    public void resizeByRightSide() {
        double valueOfMovement = 40.0;
        moveTo(stage.getX() + stage.getWidth() - 1,stage.getY()+100).drag(MouseButton.PRIMARY).moveBy(valueOfMovement, 0);
        assertEquals(previousWidth + valueOfMovement, scene.getWidth());
        release();
        drag(MouseButton.PRIMARY).moveBy(-(valueOfMovement+60), 0);
        assertEquals(previousWidth, scene.getWidth());
    }

    @Test
    public void resizeByLeftSide() {
        double valueOfMovement = 40.0;
        moveTo(stage.getX(), stage.getY()+100).drag(MouseButton.PRIMARY).moveBy(-valueOfMovement, 0);
        assertEquals(previousWidth + valueOfMovement, scene.getWidth());
        release();
        drag(MouseButton.PRIMARY).moveBy(valueOfMovement+60, 0);
        assertEquals(previousWidth, scene.getWidth());
    }

    @Test
    public void resizeByUpperSide() {
        double valueOfMovement = 40.0;
        moveTo(stage.getX()+100, stage.getY()).drag(MouseButton.PRIMARY).moveBy(0, -valueOfMovement);
        assertEquals(previousHeight + valueOfMovement, scene.getHeight());
        release();
        drag(MouseButton.PRIMARY).moveBy(0, valueOfMovement+60);
        assertEquals(previousHeight, scene.getHeight());
    }

    @Test
    public void resizeByDownSide() {
        double valueOfMovement = 40.0;
        moveTo(stage.getX()+100,stage.getY() + stage.getHeight() - 1).drag(MouseButton.PRIMARY).moveBy(0, valueOfMovement);
        assertEquals(previousHeight + valueOfMovement, scene.getHeight());
        release();
        drag(MouseButton.PRIMARY).moveBy(0, -(valueOfMovement+60));
        assertEquals(previousHeight, scene.getHeight());
    }

    @Test
    public void resizeByUpperLeftCorner(){
        resizeCornerTemplateTest(stage.getX(),stage.getY(),-40,-40);
    }

    @Test
    public void resizeByUpperRightCorner(){
        resizeCornerTemplateTest(stage.getX() + stage.getWidth() - 1,stage.getY(),40,-40);
    }

    @Test
    public void resizeByDownRightCorner(){
        resizeCornerTemplateTest(stage.getX() + stage.getWidth() - 1,stage.getY() + stage.getHeight() - 1,40,40);
    }

    @Test
    public void resizeByDownLeftCorner(){
        resizeCornerTemplateTest(stage.getX(),stage.getY() + stage.getHeight() - 1,-40,40);
    }

    private void resizeCornerTemplateTest(double fromX, double fromY, double toX, double toY){
        previousWidth = stage.getWidth();
        previousHeight = stage.getHeight();
        moveTo(fromX,fromY).drag(MouseButton.PRIMARY).moveBy(toX, toY);
        assertEquals(previousHeight + Math.abs(toY), scene.getHeight());
        assertEquals(previousWidth + Math.abs(toX), scene.getWidth());

        drag(MouseButton.PRIMARY).moveBy(-toX, -toY);
        assertEquals(previousHeight, scene.getHeight());
        assertEquals(previousWidth , scene.getWidth());

        assertEquals(previousWidth,stage.getWidth());
        assertEquals(previousHeight,stage.getHeight());
    }

    @After
    public void release() {
        release(MouseButton.PRIMARY);
    }
}
