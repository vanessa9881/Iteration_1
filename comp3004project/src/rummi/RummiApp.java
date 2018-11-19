package rummi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RummiApp extends Application{
	
    @Override
    public void start(Stage primaryStage) {    	
        Board model = new Board();
        BoardController controller = new BoardController(model);
        BoardView view = new BoardView(controller, model);

        Scene scene = new Scene(view.asParent(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
