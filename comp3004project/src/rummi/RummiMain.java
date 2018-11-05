package rummi;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rummi.Deck;
import rummi.Player;
import rummi.Tile;

public class RummiMain extends Application {
	
    private Deck tileDeck = new Deck(1);  
    private Player hand = new Player();
    private ArrayList<Player> playerList;
    
    Button[][] buttonGrid = new Button[12][12];
    Button[] playerButton = new Button[80];    
	BorderPane root = new BorderPane();	
    GridPane gameBoard = new GridPane();
	FlowPane userPane = new FlowPane();
	ScrollPane userScrollPane = new ScrollPane();
	
    //Method for Drawing  Card
    public void drawTile(Player hand, Button[] b){
        try{
        	int handIndex = hand.getNumberOfTiles();
        	Tile addedTile = tileDeck.dealTile();
        	
        	ImageView img = new ImageView(addedTile.getTileImage()); 	//Get and view of the image
            img.setFitHeight(50);										//image resize
            img.setFitWidth(70);										//image resize
            b[handIndex].setGraphic(img); 								//Adding image to flow Pane "Pane"
            
            hand.addTile(addedTile);
        } catch (Exception exception){
            System.out.println(exception.getMessage()); 
        }
    }
    
    public void drawTile(Player hand) {
    	hand.addTile(tileDeck.dealTile());
    }
    
    //Creates a new shuffled deck
    public void newDeck(){
        tileDeck = new Deck(2);
        tileDeck.shuffle(); 
        System.out.println("Shuffled the deck of tiles"); 
    }
    
    //Initial 14 tiles drawn
    public void newPlayer() {
    	
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 
    	drawTile(hand, playerButton); 

    }
    
    public void createUserButtons() {	
		for (int i=0; i<64;i++) {					//Always have more buttons than tiles or null
			playerButton[i] = new Button();
			playerButton[i].setPrefSize(60, 60);
			userPane.getChildren().add(playerButton[i]);			
		}	
		
		for(int j=0;j<=63;j++) {
			final Button myPlayerButton = playerButton[j];
	        myPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	            	//drawCard(hand, playerButton);									
	            	
	            }
	            
	        });
	        
		}
		
    }
    
    public void createGameBoardButtons() {
		for( int i=0 ; i<=11 ; i++) {
			for( int j=0 ; j<=11 ; j++) {
				buttonGrid[i][j] = new Button("X");
				buttonGrid[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				GridPane.setHgrow(buttonGrid[i][j], Priority.ALWAYS);
				GridPane.setVgrow(buttonGrid[i][j], Priority.ALWAYS);
				gameBoard.add(buttonGrid[i][j], i, j);			
			}
		}
		
		 for(int i=0; i<=11; i++) {
			    for(int j=0; j<=11; j++) {
			        final Button myButton = buttonGrid[i][j];
			        myButton.setOnAction(new EventHandler<ActionEvent>() {
			            public void handle(ActionEvent event) {
			                if ("X".equals(myButton.getText())) {
			                    myButton.setText("O");
			                } else {
			                    myButton.setText("X");
			                }
			            }
			        });
			    }
			}
    }
    
    public VBox addVBox() {
        Button drawTileButton = new Button("Draw Tile");
        Button endTurnButton = new Button("End Turn");
        Label tileSelectedLabel = new Label("Selected Tile");
        TextArea moveInfoTextArea = new TextArea();
        
        VBox vbox = new VBox();
        Text title = new Text("Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().addAll(title,drawTileButton, endTurnButton, tileSelectedLabel, moveInfoTextArea);
        vbox.setSpacing(20);
        
        //Draw Tile Section
        drawTileButton.setMaxWidth(Double.MAX_VALUE);
        drawTileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	drawTile(hand, playerButton);
            }
        });

        
        //End Turn Section
        endTurnButton.setMaxWidth(Double.MAX_VALUE);
        
        //Tile Selected Section
        tileSelectedLabel.setMaxWidth(Double.MAX_VALUE);
        
        //TextArea Section
        moveInfoTextArea.setMaxWidth(200);
        moveInfoTextArea.setText("Move Information");
        moveInfoTextArea.setEditable(false);      
        return vbox;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {

	    //Controls Section--------------------------------------------------------------Controls-------------------------------------------
		root.setRight(addVBox());
		
	    //Controls Section--------------------------------------------------------------Controls-------------------------------------------    
	   
		//Player Section----------------------------------------------------------------Player Hand----------------------------------------
		root.setBottom(userScrollPane);	
        userScrollPane.setContent(userPane); 
		userScrollPane.setMaxHeight(100);
		userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
        createUserButtons();
        //Player Section----------------------------------------------------------------Player Hand----------------------------------------
		
		
		//GridBoard Section-------------------------------------------------------------Grid Board-----------------------------------------		
        gameBoard.setMaxSize(1150, 1000);
        BorderPane.setAlignment(gameBoard, Pos.TOP_LEFT);
        root.setCenter(gameBoard);	
        createGameBoardButtons();
		//GridBoard Section-------------------------------------------------------------Grid Board-----------------------------------------		 
	
	
		newPlayer();
		
		//Adding Main Frame to Window
		Scene scene = new Scene(root, 1300,700);
		primaryStage.setScene(scene);
	    primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
}
