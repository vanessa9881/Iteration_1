package rummi;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rummi.Deck;
import rummi.Player;
import rummi.Tile;

public class RummiMain extends Application {
	
    private final Deck tileDeck = new Deck(1);  
    private final Player hand = new Player();
    
	BorderPane root = new BorderPane();
	
    GridPane gameBoard = new GridPane();
    FlowPane controlsPane = new FlowPane(Orientation.VERTICAL);
	FlowPane userPane = new FlowPane(Orientation.HORIZONTAL);
	ScrollPane userScrollPane = new ScrollPane();
    
    public void drawCard(Player hand, FlowPane pane){
        try{
            Tile tile = tileDeck.dealTile(); 
            ImageView img = new ImageView(tile.getTileImage()); 
            img.setFitHeight(50);
            img.setFitWidth(70);
            pane.getChildren().add(img); 
         //   MouseControlUtil.makeDraggable(img);
            hand.addTile(tile); 
        } catch (Exception exception){
            System.out.println(exception.getMessage()); 
        }
    }
    
    public void newDeck(){
        tileDeck.restoreDeck(); 
        tileDeck.shuffle(); 
        System.out.println("Shuffled the deck of tiles"); 
    }
    
    public void newPlayer() {
    	
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	drawCard(hand, userPane); 
    	
  
    }
    

	@Override
	public void start(Stage primaryStage) throws Exception {

	    //Controls Section
		root.setRight(new Label("Rightt"));
	    
	    //Player Section
		root.setBottom(userScrollPane);	
		userScrollPane.setContent(userPane);
		userScrollPane.setMaxWidth(100000);
		
		//GridBoard Section		
		root.setCenter(gameBoard);
	    for (int r = 0; r < 12; r++) {
	        for (int c = 0; c < 12; c++) {
	        	//int number = (10*r)+c;		Number the buttons
	        	Button tileHolder = new Button();
	            tileHolder.setPrefHeight(100);
	            tileHolder.setPrefWidth(110);
	            gameBoard.add(tileHolder, c, r);
	            //tileHolder.setStyle("-fx-background-color: transparent");
	            //tileHolder.setStyle("-fx-border-color: black");
	        }
	    }
	
	    //Main Frame Section
	    userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
		userScrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		
		newPlayer();
		//Adding Main Frame to Window
		Scene scene = new Scene(root, 1300,700);
		primaryStage.setScene(scene);
	    primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	public static void main(String[] args) {
		Game gameObject = new Game();
		gameObject.play();
		
	}
	*/
}
