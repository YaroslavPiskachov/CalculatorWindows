package calculatorTests;

import javafx.scene.input.MouseButton;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Yaroslav on 07.08.2017.
 */
public class MovingWindowTest extends MyTestBox {

    @Test
    public void movingWindowTest(){
        movingWindowTestTemplate(-40,30);
        movingWindowTestTemplate(10,20);
        movingWindowTestTemplate(26,142);
        movingWindowTestTemplate(325,1);
        movingWindowTestTemplate(-452,5);
        movingWindowTestTemplate(10,53);
        movingWindowTestTemplate(12,-74);
        movingWindowTestTemplate(75,-81);
    }





    private void movingWindowTestTemplate(double moveOnX, double moveOnY){
        double titleBarLocationX = stage.getX()+150;
        double titleBarLocationY = stage.getY()+10;
        double windowLocationX = stage.getX();
        double windowLocationY = stage.getY();
        moveTo(titleBarLocationX,titleBarLocationY).drag(MouseButton.PRIMARY).moveBy(moveOnX,moveOnY).release(MouseButton.PRIMARY);
        assertEquals(windowLocationX+moveOnX,stage.getX());
        assertEquals(windowLocationY+moveOnY,stage.getY());
        titleBarLocationX = stage.getX()+150;
        titleBarLocationY = stage.getY()+10;
        moveTo(titleBarLocationX,titleBarLocationY).drag(MouseButton.PRIMARY).moveBy(-moveOnX,-moveOnY).release(MouseButton.PRIMARY);
        assertEquals(windowLocationX,stage.getX());
        assertEquals(windowLocationY,stage.getY());
    }
}
