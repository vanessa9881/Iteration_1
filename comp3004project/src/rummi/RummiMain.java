package rummi;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import rummi.Deck;
import rummi.Player;
import rummi.Tile;

public class RummiMain extends Application {
	
    private Deck tileDeck;  
    private Player playerHand;
    
    //TIMER------------------------------------------------------------------------------------------------
    Clock timer;
    //TIMER------------------------------------------------------------------------------------------------
    
    boolean timerEnabled = false;
    RummiButton[][] boardButtonGrid = new RummiButton[12][12];
    RummiButton[] playerHandButtons = new RummiButton[64];    
	BorderPane root = new BorderPane();	
    GridPane gameBoard = new GridPane();
	FlowPane userPane = new FlowPane();
	ScrollPane userScrollPane = new ScrollPane();
    Label tileSelectedLabel = new Label("Selected Tile");
    Tile currentSelectedTile;
    Tile previousSelectedTile;
    RummiButton currentSelectedButton;
    RummiButton previousSelectedButton;
	
	// Start method for app when it is launched
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 timer = new Clock();
		
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

        
        newDeck();
        System.out.println("Size of intial Deck size: " + tileDeck.deckSize());
        
        playerHand = new Player(); 
		newPlayer();

		
		//Adding Main Frame to Window
		Scene scene = new Scene(root, 1300,700);
		primaryStage.setScene(scene);
	    primaryStage.show();		
	}
	
    //Method for Drawing  Card
    public void drawTile(Player player, RummiButton[] button){
        	//int handIndex = player.getNumberOfTiles();
        	Tile addedTile = tileDeck.dealTile();
        	
        	player.addTile(addedTile);
        	player.sort();
        	button[player.findTile(addedTile)].setTile(addedTile);
        	for (RummiButton b : playerHandButtons) {
        		b.setGraphic(null);
        	}
        	for (RummiButton b : playerHandButtons) {
        		if (b.getTile() == null) {
        		}
        		else {
        			ImageView img = new ImageView(b.getTile().getTileImage());
            		img.setFitHeight(50);										//image resize
                    img.setFitWidth(70);										//image resize
                    b.setGraphic(img);
        		}
        	}            
    }
    
    public void drawTile(Player p) {
    	p.addTile(tileDeck.dealTile());
    	p.sort();
	}
    
    private void drawTileToBoard(Tile i, RummiButton b) {
    	//System.out.println("Drawing a card to Game Board from Player Button (currentSelectedTile): " + currentSelectedTile.toString());
    	System.out.println("------------------------------------------------------------");
    	ImageView img = new ImageView(i.getTileImage());
    	img.setFitHeight(40);
        img.setFitWidth(70);				//image resize
        b.setGraphic(img);  
    }
    
    
    //Creates a new shuffled deck
    public void newDeck(){
        tileDeck = new Deck();
    }

    //Initial 14 tiles drawn
    public void newPlayer() {
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons); 
    	drawTile(playerHand, playerHandButtons);
    }
    
    public void createUserButtons() {
		for (int i=0; i<64;i++) {					//Always have more buttons than tiles or null
			playerHandButtons[i] = new RummiButton();
			playerHandButtons[i].setPrefSize(60, 60);
			userPane.getChildren().add(playerHandButtons[i]);			
		}	
		
		for(int j=0;j<=63;j++) {
			RummiButton myPlayerButton = playerHandButtons[j];
			myPlayerButton.setPos(j,j);
	        myPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        	// This part will need to be moved to its own class
	            public void handle(ActionEvent event) {	            	
	                String currentSelectedTileText;
	            	System.out.println(myPlayerButton.getPos()[0] + "the index value selected in playerUserButton array");
	            	System.out.println(playerHand.getNumberOfTiles() + " Tiles in the hand");
	            	       	
	            	if (myPlayerButton.getPos()[0] <= playerHand.getNumberOfTiles() -1 ) {
	            		tileSelectedLabel.setText(playerHand.getTile(myPlayerButton.getPos()[0]).toString()); 
	            		currentSelectedTile = playerHand.getTile(myPlayerButton.getPos()[0]);					// Set currently selected tile
	            		currentSelectedButton = myPlayerButton;
	            		System.out.println(currentSelectedTile.toString()+ " Current tile object selected"); 	            		
	            	} else if (myPlayerButton.getPos()[0] > playerHand.getNumberOfTiles() - 1){
	            		tileSelectedLabel.setText("No tile");	
	            		currentSelectedTile = null;
	            		if (currentSelectedTile == null) {
	            		System.out.println("This is clicking on empty tile in hand after setting to null");
	            		}
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
				boardButtonGrid[i][j] = new RummiButton();
				boardButtonGrid[i][j].setPrefSize(100, 100);
				GridPane.setHgrow(boardButtonGrid[i][j], Priority.ALWAYS);
				GridPane.setVgrow(boardButtonGrid[i][j], Priority.ALWAYS);
				gameBoard.add(boardButtonGrid[i][j], i, j);			
			}
		}
		
		 for(int i=0; i<=11; i++) {
			    for(int j=0; j<=11; j++) {
			        RummiButton myButton = boardButtonGrid[i][j];
			        myButton.setPos(i,j);
			      
			        myButton.setOnAction(new EventHandler<ActionEvent>() {
			            public void handle(ActionEvent event) {
			            	if (currentSelectedTile == null) {
			            		System.out.println("Current selected tile is null");
			            	}
			            	else {
			            		previousSelectedTile = currentSelectedTile;
			            		previousSelectedButton = currentSelectedButton;
			            		int x = myButton.getPos()[0];
			            		int y = myButton.getPos()[1];
			            		currentSelectedTile = boardButtonGrid[x][y].getTile();
			            		currentSelectedButton = boardButtonGrid[x][y];
				            	System.out.println("Column Number " + x + " and " + " Row Number " + y);	
				            	System.out.println("------------------------------------------------------------");		            	
				            	checkTilePlacement();
			            	}
			            }
			        });
			        }

			    }
			}
    
    private void checkTilePlacement() { 
    	Boolean occupied = false;
    	
    	if (occupied == false) {
    		drawTileToBoard(previousSelectedTile, currentSelectedButton);
    		previousSelectedButton.setGraphic(null);
    		playerHand.deleteTile(previousSelectedTile);
    		currentSelectedTile = null;
    		currentSelectedButton = null;
    	}
    }

    //TIMER------------------------------------------------------------------------------------------------
    public class Clock extends VBox {
    	private Timeline animation;
    	private int tmp = 10;
    	private String S = "";
    	Label label = new Label();
    	
    	private Clock() {
    		label.setFont(Font.font("Arial", FontWeight.BOLD, 25));
    		getChildren().add(label);
    		
    		if (timerEnabled == true) {
    		animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
    		animation.setCycleCount(Timeline.INDEFINITE);
    		animation.play();
    		}
    	}
    	
    	private void timelabel() { 
    		if (tmp > 0) {
    			tmp--;
    		}
    		S = tmp + "";
    		label.setText(S);
    	}	
    }
    //TIMER------------------------------------------------------------------------------------------------
    
    private VBox addVBox() {    	
    	
        Button drawTileButton = new Button("Draw Tile");
        Button endTurnButton = new Button("End Turn");
        Button setTimer = new Button("Set TImer");  
        TextArea moveInfoTextArea = new TextArea();
        
        
        //SetTimer Section
        setTimer.setMaxWidth(Double.MAX_VALUE);     
        setTimer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	timerEnabled = true;
            }
        });
        
        
        VBox vbox = new VBox();
        Text title = new Text("Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().addAll(title,drawTileButton, endTurnButton, setTimer, tileSelectedLabel, moveInfoTextArea, timer);
        vbox.setSpacing(20);
        
        //Draw Tile Section
        drawTileButton.setMaxWidth(Double.MAX_VALUE);
        drawTileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	drawTile(playerHand, playerHandButtons);
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
	public static void main(String[] args) {
		RummiMain.launch(args);
	}
}
