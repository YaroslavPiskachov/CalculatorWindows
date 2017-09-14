import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controller.Controller;


import static com.sun.jna.platform.win32.WinUser.GWL_STYLE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));

        Parent root = loader.load();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/calc_Ico/calc1.png"));
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(new Scene(root, 334, 507));
        primaryStage.setMinWidth(334);
        primaryStage.setMinHeight(507);

        Controller controller = loader.getController();
        controller.setStageAndInitialize(primaryStage);

        primaryStage.show();

        long lhwnd = com.sun.glass.ui.Window.getWindows().get(0).getNativeWindow();
        Pointer lpVoid = new Pointer(lhwnd);
        WinDef.HWND hwnd = new WinDef.HWND(lpVoid);
        final User32 user32 = User32.INSTANCE;
        int oldStyle = user32.GetWindowLong(hwnd, GWL_STYLE);
        int newStyle = oldStyle | 0x00020000;//WS_MINIMIZEBOX
        user32.SetWindowLong(hwnd, GWL_STYLE, newStyle);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
