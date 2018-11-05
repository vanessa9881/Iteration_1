package rummi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
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

public class RummiApp extends Application {
	
    private Deck tileDeck;  
    private Player playerHand = new Player();
    
    Button[][] boardButtonGrid = new Button[12][12];
    Button[] playerHandButtons = new Button[80];    
	BorderPane root = new BorderPane();	
    GridPane gameBoard = new GridPane();
	FlowPane userPane = new FlowPane();
	ScrollPane userScrollPane = new ScrollPane();
    Label tileSelectedLabel = new Label("Selected Tile");
	//Tile currentSelectedTile;								//Global variable?
    String currentSelectedTileText;
    Tile currentSelectedTile;
	int nextSelectedTileButtonNumber;
	
	// Start method for app when it is launched
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    //Controls Section--------------------------------------------------------------Controls-------------------------------------------
		root.setRight(addVBox());
	   
		//Player Section----------------------------------------------------------------Player Hand----------------------------------------
		root.setBottom(userScrollPane);	
        userScrollPane.setContent(userPane); 
		userScrollPane.setMaxHeight(100);
		userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
        createUserButtons();		
		
		//GridBoard Section-------------------------------------------------------------Grid Board-----------------------------------------		
        gameBoard.setMaxSize(1150, 1000);
        BorderPane.setAlignment(gameBoard, Pos.TOP_LEFT);
        root.setCenter(gameBoard);	
        createGameBoardButtons();
	
		newPlayer();
		
		//Adding Main Frame to Window
		Scene scene = new Scene(root, 1300,700);
		primaryStage.setScene(scene);
	    primaryStage.show();		
	}
	
    //Method for Drawing  Card
    public void drawCard(Player player, Button[] b){
        try{
        	int handIndex = player.getNumberOfTiles();
        	Tile addedTile = tileDeck.dealTile();
        	
            ImageView img = new ImageView(addedTile.getTileImage()); 	//Get and view of the image
            img.setFitHeight(50);										//image resize
            img.setFitWidth(70);										//image resize
            b[handIndex].setGraphic(img); 								//Adding image
            player.addTile(addedTile);
            
        } catch (Exception exception){
            System.out.println(exception.getMessage()); 
        }
    }
    
    private void drawTileToBoard(Tile i,Button[][] b) {
    	i = currentSelectedTile;
    	b = boardButtonGrid;
    	
    	ImageView img = new ImageView(i.getTileImage());
        img.setFitHeight(50);										//image resize
        img.setFitWidth(70);
    }
    
    
    //Creates a new shuffled deck
    public void newDeck(){
        tileDeck = new Deck(104);
        System.out.println("Deck refreshed"); 
    }
    
    //Initial 14 tiles drawn
    public void newPlayer() {
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    	drawCard(playerHand, playerHandButtons); 
    }
    
    public void createUserButtons() {
		for (int i=0; i<64;i++) {					//Always have more buttons than tiles or null
			playerHandButtons[i] = new Button();
			playerHandButtons[i].setPrefSize(60, 60);
			userPane.getChildren().add(playerHandButtons[i]);			
		}	
		
		for(int j=0;j<=63;j++) {
			Button myPlayerButton = playerHandButtons[j];
			myPlayerButton.setTooltip(new Tooltip(Integer.toString(j)));
	        myPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        	// This part will need to be moved to its own class
	            public void handle(ActionEvent event) {	            	
	            	int index = Integer.parseInt(myPlayerButton.getTooltip().getText());
	            	System.out.println(index + "th index value selected in playerUserButton array");
	            	System.out.println(playerHand.getNumberOfTiles() + " Tiles in the hand");
	            	       	
	            	if (index <= playerHand.getNumberOfTiles()-1) {
	            		tileSelectedLabel.setText(playerHand.getTile(index).toString()); 
	            		currentSelectedTile = playerHand.getTile(index);
	            		
	            	} else {
	            		tileSelectedLabel.setText("No tile");
	            		currentSelectedTile = null;
	            	}
	            	currentSelectedTileText = tileSelectedLabel.getText();
	               	System.out.println(currentSelectedTile + " Selected");
	               	System.out.println("------------------------------------------------------------");
	            }
	        }); 
	        // --------------------------------------------------------------------------
		}
    }
    
    public void createGameBoardButtons() {
		for( int i=0 ; i<=11 ; i++) {
			for( int j=0 ; j<=11 ; j++) {
				boardButtonGrid[i][j] = new Button();
				boardButtonGrid[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				GridPane.setHgrow(boardButtonGrid[i][j], Priority.ALWAYS);
				GridPane.setVgrow(boardButtonGrid[i][j], Priority.ALWAYS);
				gameBoard.add(boardButtonGrid[i][j], i, j);			
			}
		}
		
		 for(int i=0; i<=11; i++) {
			    for(int j=0; j<=11; j++) {
			        Button myButton = boardButtonGrid[i][j];	    
			        Tooltip coloumnToolTip = new Tooltip(Integer.toString(i));
			        Tooltip rowToolTip = new Tooltip(Integer.toString(j));
			        myButton.setOnAction(new EventHandler<ActionEvent>() {
			            public void handle(ActionEvent event) {
			            	int coloumnIndex = (Integer.parseInt(coloumnToolTip.getText()));
			            	int rowIndex = Integer.parseInt(rowToolTip.getText());
			            	int buttonNumber = 12 * rowIndex + coloumnIndex; //Checks button position on grid	            	
			            	System.out.println(buttonNumber);	
			            	System.out.println("------------------------------------------------------------");
			            	nextSelectedTileButtonNumber = buttonNumber;
			            	//checkTilePlacement(currentSelectedTile, nextSelectedTile);
			            }
			        });
			    }
			}
    }
     
    private void checkTilePlacement(Tile i, int n) {
    	i = currentSelectedTile;				//This is a string
    	n = nextSelectedTileButtonNumber;		//This is an int
    	Boolean occupied = false;
    	
    	if (occupied == false) {
    	drawTileToBoard(i,boardButtonGrid);
    	} else {
    		return;
    	}
    	
    }
    
    private VBox addVBox() {
        Button drawTileButton = new Button("Draw Tile");
        Button endTurnButton = new Button("End Turn");
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
            	drawCard(playerHand, playerHandButtons);
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
}
