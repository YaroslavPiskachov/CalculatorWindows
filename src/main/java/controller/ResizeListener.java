package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Class implements event handler for resizing window by mouse
 *
 * @author Yaroslav Piskachov
 */
public class ResizeListener implements EventHandler<MouseEvent> {

    /**
     * Margin from borders where you can press to resize window
     */
    private static final double border = 10;
    /**
     * Wight of window before resizing
     */
    private double lastWight;

    /**
     * Height of window before resizing
     */
    private double lastHeight;

    /**
     * X coordinate of location window on the screen before resizing
     */
    private double lastScreenX;

    /**
     * Y coordinate of location window on the screen before resizing
     */
    private double lastScreenY;

    /**
     * Value represents difference between last and new wight
     */
    private double deltaX;

    /**
     * Value represents difference between last and new height
     */
    private double deltaY;

    /**
     * Minimal x coordinate window can be moved by resizing
     */
    private double minPositionX;

    /**
     * Minimal y coordinate window can be moved by resizing
     */
    private double minPositionY;





    /**
     * Is horizontal moving needed
     */
    private boolean moveH;

    /**
     * Is vertical moving needed
     */
    private boolean moveV;

    /**
     * Is horizontal resizing needed
     */
    private boolean resizeH = false;

    /**
     * Is vertical resizing needed
     */
    private boolean resizeV = false;

    /**
     * Stage of window
     */
    private Stage stage;

    /**
     * Minimal width of stage
     */
    private double stageMinWidth;

    /**
     * Minimal height of stage
     */
    private double stageMinHeight;

    /**
     * Scene of window
     */
    private Scene scene;

    ResizeListener(Stage stage) {
        this.stage = stage;
        stageMinWidth = stage.getMinWidth();
        stageMinHeight = stage.getMinHeight();
        scene = stage.getScene();

    }


    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param t the event which occurred
     */
    @Override
    public void handle(MouseEvent t) {
        if (MouseEvent.MOUSE_MOVED.equals(t.getEventType())) {
            double windowX = t.getX();
            double windowY = t.getY();
            double sceneWidth = scene.getWidth();
            double sceneHeight = scene.getHeight();

            if (windowX < border && windowY < border) {
                scene.setCursor(Cursor.NW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = true;
            } else if (windowX < border && windowY > sceneHeight - border) {
                scene.setCursor(Cursor.SW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = false;
            } else if (windowX > sceneWidth - border && windowY < border) {
                scene.setCursor(Cursor.NE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = true;
            } else if (windowX > sceneWidth - border && windowY > sceneHeight - border) {
                scene.setCursor(Cursor.SE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = false;
            } else if (windowX < border || windowX > sceneWidth - border) {
                scene.setCursor(Cursor.E_RESIZE);
                resizeH = true;
                resizeV = false;
                moveH = (windowX < border);
                moveV = false;
            } else if (windowY < border || windowY > sceneHeight - border) {
                scene.setCursor(Cursor.N_RESIZE);
                resizeH = false;
                resizeV = true;
                moveH = false;
                moveV = (windowY < border);
            } else {
                scene.setCursor(Cursor.DEFAULT);
                resizeH = false;
                resizeV = false;
                moveH = false;
                moveV = false;
            }
        } else if (MouseEvent.MOUSE_PRESSED.equals(t.getEventType())) {
            lastWight = stage.getWidth();
            lastHeight = stage.getHeight();

            lastScreenX = t.getScreenX();
            lastScreenY = t.getScreenY();

            minPositionX = stage.getX() + (lastWight - stageMinWidth);
            minPositionY = stage.getY() + (lastHeight - stageMinHeight);
        } else if (MouseEvent.MOUSE_DRAGGED.equals(t.getEventType())) {
            if (resizeH) {
                double newScreenX = t.getScreenX();
                if (moveH) {
                    deltaX = lastScreenX - newScreenX;
                    double newWight = lastWight + deltaX;

                    if (newWight < stageMinWidth) {
                        newWight = stageMinWidth;
                        newScreenX = minPositionX;
                    }

                    setWight(newWight);
                    stage.setX(newScreenX);
                } else {
                    deltaX = newScreenX - lastScreenX;
                    double wight = lastWight + deltaX;

                    if (newScreenX < lastScreenX) {
                        wight = stageMinWidth;
                    }

                    setWight(wight);
                }
            }
            if (resizeV) {
                double newScreenY = t.getScreenY();
                if (moveV) {
                    deltaY = lastScreenY - newScreenY;
                    double newHeight = lastHeight + deltaY;

                    if (newHeight < stageMinHeight) {
                        newHeight = stageMinHeight;
                        newScreenY = minPositionY;
                    }

                    setHeight(newHeight);
                    stage.setY(newScreenY);
                } else {
                    deltaY = newScreenY - lastScreenY;
                    double height = lastHeight + deltaY;

                    if (newScreenY < lastScreenY) {
                        height = stageMinHeight;
                    }

                    setHeight(height);
                }
            }
        } else if (MouseEvent.MOUSE_RELEASED.equals(t.getEventType())) {
            if (moveH || moveV) {
                stage.setX(lastScreenX);
                stage.setY(lastScreenY);
            }
        }
    }

    private void setWight(double wight) {
        stage.setWidth(wight);
    }

    private void setHeight(double height) {
        stage.setHeight(height);
    }
}
