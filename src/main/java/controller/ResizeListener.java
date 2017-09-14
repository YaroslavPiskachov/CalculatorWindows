package controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Class implements event handler for resizing window by mouse
 */
public class ResizeListener implements EventHandler<MouseEvent> {
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
     * Margin from borders where you can press to resize window
     */
    private double border = 10;

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
     * Scene of window
     */
    private Scene scene;

    public ResizeListener(Stage stage) {
        this.stage = stage;
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
            if (t.getX() < border && t.getY() < border) {
                scene.setCursor(Cursor.NW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = true;
            } else if (t.getX() < border && t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.SW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = false;
            } else if (t.getX() > scene.getWidth() - border && t.getY() < border) {
                scene.setCursor(Cursor.NE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = true;
            } else if (t.getX() > scene.getWidth() - border && t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.SE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = false;
            } else if (t.getX() < border || t.getX() > scene.getWidth() - border) {
                scene.setCursor(Cursor.E_RESIZE);
                resizeH = true;
                resizeV = false;
                moveH = (t.getX() < border);
                moveV = false;
            } else if (t.getY() < border || t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.N_RESIZE);
                resizeH = false;
                resizeV = true;
                moveH = false;
                moveV = (t.getY() < border);
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

            minPositionX = stage.getX() + (stage.getWidth() - stage.getMinWidth());
            minPositionY = stage.getY() + (stage.getHeight() - stage.getMinHeight());
        } else if (MouseEvent.MOUSE_DRAGGED.equals(t.getEventType())) {
            if (resizeH) {
                if (moveH) {
                    deltaX = lastScreenX - t.getScreenX();
                    double newWight = lastWight + deltaX;
                    double x = t.getScreenX();

                    if (newWight < stage.getMinWidth()) {
                        newWight = stage.getMinWidth();
                        x = minPositionX;
                    }

                    setWight(newWight);
                    stage.setX(x);
                } else {
                    deltaX = t.getScreenX() - lastScreenX;
                    double wight = lastWight + deltaX;

                    if (t.getScreenX() < lastScreenX) {
                        wight = stage.getMinWidth();
                    }

                    setWight(wight);
                }
            }
            if (resizeV) {
                if (moveV) {
                    deltaY = lastScreenY - t.getScreenY();
                    double newHeight = lastHeight + deltaY;
                    double newY = t.getScreenY();

                    if (newHeight < stage.getMinHeight()) {
                        newHeight = stage.getMinHeight();
                        newY = minPositionY;
                    }

                    setHeight(newHeight);
                    stage.setY(newY);
                } else {
                    deltaY = t.getScreenY() - lastScreenY;
                    double height = lastHeight + deltaY;

                    if (t.getScreenY() < lastScreenY) {
                        height = stage.getMinHeight();
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
