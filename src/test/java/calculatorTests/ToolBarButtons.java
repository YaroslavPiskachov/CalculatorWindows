package calculatorTests;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.loadui.testfx.GuiTest.find;

/**
 * Created by Yaroslav on 08.08.2017.
 */
public class ToolBarButtons extends MyTestBox {

    @Test
    public void fullScreenTest(){
        double previousX = stage.getX();
        double previousY = stage.getY();
        assertFalse(stage.isMaximized());
        clickOn("#extendButton");
        assertTrue(stage.isMaximized());
        clickOn("#extendButton");
        assertFalse(stage.isMaximized());
        assertEquals(previousX,stage.getX());
        assertEquals(previousY,stage.getY());
    }

    @Test
    public void hideTest(){
        clickOn("#hideButton");
        assertEquals(stage.isIconified(),true);
        this.interact(() -> stage.setIconified(false));
    }
}
