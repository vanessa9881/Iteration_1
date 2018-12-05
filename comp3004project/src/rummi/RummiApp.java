package rummi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RummiApp extends Application{
	
    @Override
    public void start(Stage primaryStage) {
    	
        Board model = new Board();
        BoardView view = new BoardView(model);

        Scene scene = new Scene(view.asParent(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        model.setPlayers(0, 4);
        model.startOrder();
    }

    public static void main(String[] args) {
        launch(args);
    }
}