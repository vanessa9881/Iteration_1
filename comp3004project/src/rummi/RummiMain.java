package rummi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
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
	
    private final Deck tileDeck = new Deck(1);  
    private final Player hand = new Player();
    
    
    Button[][] buttonGrid = new Button[12][12];
    Button[] playerButton = new Button[80]; 
    
	BorderPane root = new BorderPane();	
    GridPane gameBoard = new GridPane();
	FlowPane userPane = new FlowPane();
	
	ScrollPane userScrollPane = new ScrollPane();
    Label tileSelectedLabel = new Label("Selected Tile");
    Tile currentSelectedTile;
	int nextSelectedTileButtonNumber;
	
	int coloumnIndex;
	int rowIndex;
    //Method for Drawing  Card
    public void drawCard(Player hand, Button[] b){
        try{
        	int handIndex = hand.getNumberOfTiles();
        	Tile addedTile = tileDeck.dealTile();
        	
            ImageView img = new ImageView(addedTile.getTileImage()); 	//Get and view of the image
            img.setFitHeight(50);										//image resize
            img.setFitWidth(70);										//image resize
            b[handIndex].setGraphic(img);								//Adding image
            hand.addTile(addedTile);
        } catch (Exception exception){
            System.out.println(exception.getMessage()); 
        }
    }
    
    
    private void drawTileToBoard(Tile i,Button[][] b) {
    	
    	System.out.println("Drawing the current selected tile object " + currentSelectedTile);
    	ImageView img = new ImageView(i.getTileImage());
        img.setFitWidth(60);				//image resize
        img.setFitHeight(40);
        b[coloumnIndex][rowIndex].setGraphic(img);
    }
    
    
    //Creates a new shuffled deck
    public void newDeck(){
        tileDeck.restoreDeck(); 
        tileDeck.shuffle(); 
        System.out.println("Shuffled the deck of tiles"); 
    }
    
    //Initial 14 tiles drawn
    public void newPlayer() {
    	
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 
    	drawCard(hand, playerButton); 

    }
    
    public void createUserButtons() {
		for (int i=0; i<64;i++) {					//Always have more buttons than tiles or null
			playerButton[i] = new Button();
			playerButton[i].setPrefSize(60, 60);
			userPane.getChildren().add(playerButton[i]);			
		}	
		
		for(int j=0;j<=63;j++) {
			final Button myPlayerButton = playerButton[j];
			myPlayerButton.setTooltip(new Tooltip(Integer.toString(j)));
	        myPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {	            	
	                String currentSelectedTileText;
	            	int index = Integer.parseInt(myPlayerButton.getTooltip().getText());
	            	System.out.println(index + "th index value selected in playerUserButton array");
	            	System.out.println(hand.getNumberOfTiles() + " Tiles in the hand");
            	
	            	if (index <= hand.getNumberOfTiles()-1) {
	            		tileSelectedLabel.setText(hand.getTile(index).toString()); 
	            		currentSelectedTile = hand.getTile(index);
		            	System.out.println(currentSelectedTile.toString()+ " Current tile object selected");            		
	            	} else {
	            		tileSelectedLabel.setText("No tile");	      
	            		//If a blank space is clicked, it should make the current tile clicked empty (saving the old one)............................
	            		//If blank tile is selected and clicked on board, crashes because no tile object..........
	            		//currentSelectedTile = null;		//--------------------------------------------------------????????

	            	}
	            	currentSelectedTileText = tileSelectedLabel.getText();
	               	System.out.println(currentSelectedTileText + " Selected");
	               	System.out.println("------------------------------------------------------------");
	            }
	            
	        }); 
		}
    }
    
    public void createGameBoardButtons() {
		for( int i=0 ; i<=11 ; i++) {
			for( int j=0 ; j<=11 ; j++) {
				buttonGrid[i][j] = new Button();
				buttonGrid[i][j].setPrefSize(100,100);
				GridPane.setHgrow(buttonGrid[i][j], Priority.ALWAYS);
				GridPane.setVgrow(buttonGrid[i][j], Priority.ALWAYS);
				gameBoard.add(buttonGrid[i][j], i, j);			
			}
		}
		
		 for(int i=0; i<=11; i++) {
			    for(int j=0; j<=11; j++) {
			        final Button myButton = buttonGrid[i][j];	    
			        Tooltip coloumnToolTip = new Tooltip(Integer.toString(i));
			        Tooltip rowToolTip = new Tooltip(Integer.toString(j));
			       
			        myButton.setOnAction(new EventHandler<ActionEvent>() {
			            public void handle(ActionEvent event) {
			            	System.out.println("If condition");
			            	coloumnIndex = (Integer.parseInt(coloumnToolTip.getText()));				//Do we need to make these variables global?
			            	rowIndex = Integer.parseInt(rowToolTip.getText());							//Do we need to make these variables global?
			            	int buttonNumber = 12 * rowIndex + coloumnIndex;							//Checks button position on grid	            	
			            	System.out.println(buttonNumber);	
			            	System.out.println("------------------------------------------------------------");
			            	nextSelectedTileButtonNumber = buttonNumber;		            	
			            	checkTilePlacement(currentSelectedTile, nextSelectedTileButtonNumber); 		//????????????????????????????????
			            }
			        });

			    }
			}
    }
    
    
    private  void checkTilePlacement(Tile i, int n) {
    	i = currentSelectedTile;				//This is a Tile
    	n = nextSelectedTileButtonNumber;		//This is an int
    	Boolean occupied = false;
    	
    	if (occupied == false) {
    	drawTileToBoard(i,buttonGrid);
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
            	drawCard(hand, playerButton);
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
	
	/*
	public static void main(String[] args) {
		Game gameObject = new Game();
		gameObject.play();
		
	}
	*/
}
