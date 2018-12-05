package rummi;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RummiApp extends Application{
	int getStrat1 =0;
	int getStrat2 =0;
	int getStrat3 =0;
	int getStrat4 =0;
	int getNumberHPlayers =0;
	Boolean start = false;
	
    @Override
    public void start(Stage primaryStage) {
    	
    	initStart();
    	
    	if (start == true) {
        Board model = new Board();
        BoardView view = new BoardView(model);

        Scene scene = new Scene(view.asParent(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        model.setPlayers(getNumberHPlayers,getStrat1, getStrat2, getStrat3,getStrat4);
        model.startOrder();
    	}
        

    }
    public void initStart() {
    	int totalNumber = 0;
    	Scanner sc = new Scanner(System.in);
    	
		do {
			totalNumber = 0;
			System.out.println("Only 2-4 players combined");
			System.out.println("Enter Number of Human Players: ");
	    	getNumberHPlayers = sc.nextInt();
	    	System.out.println("Enter Number of Strat 1: ");
	    	getStrat1 = sc.nextInt();
	    	System.out.println("Enter Number of Strat 2: ");
	    	getStrat2 = sc.nextInt();
	    	System.out.println("Enter Number of Strat 3: ");
	    	getStrat3 = sc.nextInt();
	    	System.out.println("Enter Number of Strat 4: ");
	    	getStrat4 = sc.nextInt();
	    	totalNumber = getNumberHPlayers + getStrat1 + getStrat2 + getStrat3 + getStrat4;
		} while (totalNumber < 2 || totalNumber > 4);
    	sc.close();

    	

    	
    	/*
    	 * if (enter is pressed){
    	 * start = true;
    	 * }
    	 */
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}