package rummi;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BoardView {

	private BorderPane root = new BorderPane();	
    private GridPane gameBoard = new GridPane();
	private FlowPane userPane = new FlowPane();
	private ScrollPane userScrollPane = new ScrollPane();
    private Label tileSelectedLabel = new Label("Selected Tile");
    private ArrayList<RummiButton> boardButtons;
    private RummiButton[] handButtons;
    
    private Tile priorSelectedTile;
    private Tile selectedTile;
    
    private BoardController controller;
    private Board board;
    
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		selectedTile = ((RummiButton) e.getSource()).getTile();
    		if (selectedTile == null) {
        		System.out.println("Selected board space is empty");
        		if (priorSelectedTile != null) {
            		int x = ((RummiButton) e.getSource()).getPos()[0];
            		int y = ((RummiButton) e.getSource()).getPos()[1];
                	System.out.println("Column Number " + x + " and " + " Row Number " + y);	
                	System.out.println("------------------------------------------------------------");
                	// Controller will call a method in Board that checks whether the tile can be added
                	// Will probably change the return type of this method to boolean,
                	// so that if true that means the tile was added and that the board button
                	// can have it's graphic changed
                	controller.placeTile(x, y, priorSelectedTile);
                	priorSelectedTile = selectedTile;
        		}
        	}
        	else {
        		System.out.println("Selected board space is occupied");
        	}
    	}
    };
    
	public BoardView(BoardController controller, Board model) {
		this.controller = controller;
		board = model;
		
		initView();
	}
	
	private void initView() {
		root = new BorderPane();	
		gameBoard = new GridPane();
		userPane = new FlowPane();
		userScrollPane = new ScrollPane();
		tileSelectedLabel = new Label("Selected Tile");
		
		root.setRight(addVBox());
		root.setBottom(userScrollPane);
		userScrollPane.setContent(userPane);
		userScrollPane.setMaxHeight(100);
		userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
		createHandButtons();
		
		gameBoard.setMaxSize(1150, 1000);
	    BorderPane.setAlignment(gameBoard, Pos.TOP_LEFT);
	    root.setCenter(gameBoard);	
	    createGameBoardButtons();
	}
    
	 public void createHandButtons() {
		handButtons = new Button[64];
		//Always have more buttons than tiles or null
		for (int i=0; i <= 64; i++) {
			handButtons[i].setPrefSize(60, 60);
			handButtons[i].setOnAction(new ButtonHandler(controller, handButtons[i]));
			handButtons[i].setOnAction(new ButtonHandler());
			userPane.getChildren().add(handButtons[i]);			
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
            	controller.drawTile(playerHand, playerHandButtons);
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

	public Parent asParent() {
		return view;
	}
	
	public BoardController getController() {
		return controller;
	}
	

}
