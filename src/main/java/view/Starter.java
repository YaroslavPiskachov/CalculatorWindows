package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Yaroslav on 25.09.2017.
 */
public class Starter extends Application {

   @Override
   public void start(Stage primaryStage) throws IOException {
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

   }

   public void main(String[] args){
      launch(args);
   }
}
