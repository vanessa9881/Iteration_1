package rummi;

import java.awt.Point;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
    private Button startButton;
    
    //Level 2
    Caretaker caretaker;
    Originator originator;
    int savedBoardNumber = 0, currentBoardNumber = 0;
    ArrayList<Object> storedBoardState;
    Clock timer;
    
    
    //Level 4 
    TextField riggedTextField;
    String riggedColour;
    String riggedNumber;
    
    private Tile priorSelectedTile;
    private Tile selectedTile;
    
    private BoardController controller;
    
	private void initView() {
		root = new BorderPane();	
		gameBoard = new GridPane();
		userPane = new FlowPane();
		userScrollPane = new ScrollPane();
		tileSelectedLabel = new Label("Selected Tile");
		riggedTextField = new TextField();
		
		originator = new Originator();
		caretaker = new Caretaker();
		
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
	    createBoardButtons();
	}
    
    EventHandler<ActionEvent> boardButtonPress = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		priorSelectedTile = selectedTile;
    		selectedTile = ((RummiButton) e.getSource()).getTile();
    		
    		if (selectedTile == null ) {
        		System.out.println("Selected board space is empty");
        		if (priorSelectedTile != null) {
        			int x = ((RummiButton) e.getSource()).getPos()[0] + 1;
    				int y = ((RummiButton) e.getSource()).getPos()[1] + 1;
        			
        			// Moving a tile already on board
        			if (controller.getBoardTiles().containsValue(priorSelectedTile)) {
        				if (controller.moveTile(x, y, priorSelectedTile)) {
        					System.out.println("\nTile moved to new space");
        					System.out.println("--------------------------------------------------");
        				}
        			}
        			
        			// Placing a tile from hand onto the board
        			else {
        				System.out.println("Column Number " + x + " and " + " Row Number " + y + " selected.");
        				
        				if (controller.placeTile(x, y, priorSelectedTile)) {
        					System.out.println("Tile added to board");
        					System.out.println("--------------------------------------------------");
        				}
        			}
        		}
        	}
        	else {
        		System.out.println("Selected board space is occupied");
        	}
    	}
    };
       
    
    EventHandler<ActionEvent> drawTileButtonPress = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		System.out.println("-----------------------DRAW TILE BUTTON PRESSED, DELETING TILE FROM THE FRONT-------------------------------");
    		controller.drawTile();
    		//testp.hand.add(controller.returnBoard().getDeck().dealTile());
    		System.out.println("----------SAVED BOARD BUTTON PRESSES--------------");
    		///* ----------This is for testing through hard coding values--------------
    		System.out.println("Board Address: 	  " + controller.returnBoard());
    		System.out.println("Board.deck Address: 	" + controller.returnBoard().getDeckTiles());
    		System.out.println("Board.deck: " + controller.returnBoard().getDeck());
    		System.out.println("Board.HasMap Address: 	" + controller.returnBoard().getBoardTiles());
    		System.out.println("Board.HandTiles Address: 	" + controller.returnBoard().getHandTiles());
    		if (controller.returnBoard().getMelds().size() >= 1) {
    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMeldTiles());
    		} else {
    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMelds());
    		}
    		/* ----------This is for testing through hard coding values--------------
    		//This button was used for testing 4/5 object states not including playerList
    		board.drawTile();
    		board.getBoardTiles().put(new Point(2,2), new Tile(new Colour("Red", "r"), new Number("Two", "2"), new Image("file:resources/2r.gif")));
    		board.addHandTile(new Tile(new Colour("Black", "b"), new Number("Two", "2"), new Image("file:resources/2b.gif")));
    		board.createMeld();
    		
    		System.out.println("Board.Deck after deleting a tile: \n" + board.getDeck());		
    		System.out.println("Board.HasMap Address: 	" + board.getBoardTiles());		
    		System.out.println("Board.HandTiles Address: 	" + board.getHandTiles());
    		if (board.getMeld().size() >= 1) {
    			System.out.println("Board.Melds Address: 	" + board.getMeldTiles());
    		} else {
    			System.out.println("Board.Melds Address: 	" + board.getMeld());
    		}
    		-----------------------------------------------------------------------*/
    	}
    };
    
    EventHandler<ActionEvent> saveBoardAction = new EventHandler<ActionEvent>() {	
    	public void handle(final ActionEvent e) {
    		System.out.println("----------SAVED BOARD BUTTON PRESSES--------------");
    		///* ----------This is for testing through hard coding values--------------
    		System.out.println("Board Address: 	  " + controller.returnBoard());
    		System.out.println("Board.deck Address: 	" + controller.returnBoard().getDeckTiles());
    		System.out.println("Board.deck: " + controller.returnBoard().getDeck());
    		System.out.println("Board.HasMap Address: 	" + controller.returnBoard().getBoardTiles());
    		System.out.println("Board.HandTiles Address: 	" + controller.returnBoard().getHandTiles());
    		if (controller.returnBoard().getMelds().size() >= 1) {
    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMeldTiles());
    		} else {
    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMelds());
    		}
    		//-----------------------------------------------------------------------*/
    		originator.set(controller.returnBoard());
    		caretaker.addMementoBoard(originator.saveToMemento());
    		savedBoardNumber++;
    		currentBoardNumber++;
    		}
    };
    
    EventHandler<ActionEvent> resetBoardAction = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		if(currentBoardNumber >= 1){	//And if the board is not valid.	//Check if there is an error here with caretaker.getLatInde()				
				System.out.println("-----------------------RESET BUTTON PRESSED-------------------------------------");
				controller.returnBoard().setBoard(originator.restoreFromMemento( caretaker.getMementoBoard(caretaker.getLastIndex())));
				draw();
	    		///* ----------This is for testing through hard coding values--------------
	    		System.out.println("Board Address: 	  " + controller.returnBoard());
	    		System.out.println("Board.deck Address: 	" + controller.returnBoard().getDeckTiles());
	    		System.out.println("Board.deck: " + controller.returnBoard().getDeck());
	    		System.out.println("Board.HasMap Address: 	" + controller.returnBoard().getBoardTiles());
	    		System.out.println("Board.HandTiles Address: 	" + controller.returnBoard().getHandTiles());
	    		if (controller.returnBoard().getMelds().size() >= 1) {
	    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMeldTiles());
	    		} else {
	    			System.out.println("Board.Melds Address: 	" + controller.returnBoard().getMelds());
	    		}
				/* ----------This is for testing through hard coding values--------------
	    		System.out.println("Board Address: 	  " + board);
	    		System.out.println("Board.deck Address: 	" + board.getDeckForMemento());
	    		System.out.println("Board.deck:\n" + board.getDeck());
	    		System.out.println("Board.HasMap Address: 	" + board.getBoardTiles());
	    		System.out.println("Board.HandTiles Address: 	" + board.getHandTiles());
	    		if (board.getMeld().size() >= 1) {
	    			System.out.println("Board.Melds Address: 	" + board.getMeldTiles());
	    		} else {
	    			System.out.println("Board.Melds Address: 	" + board.getMeld());
	    		}
	    		-----------------------------------------------------------------------*/
	    		
    		}
    	}
    };
      
    EventHandler<ActionEvent> rigTileButton = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {	
    	
    		System.out.println("--------------------------RIGGED TILE PRESS------------------------------------------\n\n");
        	String value = riggedTextField.getText();
        	riggedColour = Character.toString(value.charAt(0));
        	riggedNumber = Character.toString(value.charAt(1));
        	//controller.returnBoard().drawRiggedTile(riggedColour, riggedNumber);	
    		controller.returnBoard().getPlayerList().get(0).hand.add(controller.returnBoard().getDeck().dealRiggedTile(riggedColour, riggedNumber));
    		System.out.print(controller.returnBoard().getPlayerList().get(0).getHandTiles());
    		
    		/* ----------This is for testing through hard coding values(This part can be ignored)--------------
    		board.addHandTile(new Tile(new Colour("Red", "r"), new Number("One", "1"), new Image("file:resources/1r.gif")));
    		System.out.println("Board Address: 	  " + board);
    		System.out.println("Board.deck Address: 	" + board.getDeckForMemento());
    		System.out.println("Board.deck:\n" + board.getDeck());
    		System.out.println("Board.HasMap Address: 	" + board.getBoardTiles());
    		System.out.println("Board.HandTiles Address: 	" + board.getHandTiles());
    		if (board.getMeld().size() >= 1) {
    			System.out.println("Board.Melds Address: 	" + board.getMeldTiles());
    		} else {
    			System.out.println("Board.Melds Address: 	" + board.getMeld());
    		}
    		---------------------------------------------------------------------------------------------------*/
    	}
    };
    
    EventHandler<ActionEvent> handButtonPress = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		priorSelectedTile = selectedTile;
    		selectedTile = ((RummiButton) e.getSource()).getTile();
    		
    		if (selectedTile == null ) {
    			System.out.println("Selected hand space is empty");
    		}
    		else {
    			System.out.println("Selected hand tile: " + selectedTile.toString());
    		}
    	}
    };

	public BoardView(Board model) {
		controller = new BoardController(model, this);
		model.setController(controller);
		
		initView();
	}
	
	public void createHandButtons() {
		handButtons = new RummiButton[64];
		//Always have more buttons than tiles or null
		for (int i = 0; i < 64; i++) {
			handButtons[i] = new RummiButton();
			handButtons[i].setPrefSize(60, 60);
			handButtons[i].setOnAction(handButtonPress);
			userPane.getChildren().add(handButtons[i]);			
		}	
	 }
	
	public void createBoardButtons() {
		boardButtons = new ArrayList<RummiButton>();
		for (int x = 0; x < BoardController.BOARDSIZE; x++) {
			for(int y = 0; y < BoardController.BOARDSIZE; y++) {		
				RummiButton newButton = new RummiButton(x,y);
				newButton.setPrefSize(100, 100);
				newButton.setOnAction(boardButtonPress);
				GridPane.setHgrow(newButton, Priority.ALWAYS);
				GridPane.setVgrow(newButton, Priority.ALWAYS);
				gameBoard.add(newButton, x, y);	
				boardButtons.add(newButton);
			}
		}	
	}
	
    private VBox addVBox() {
        Button drawTileButton = new Button("Draw Tile");
        Button saveBoardButton = new Button("Save Board");
        Button resetBoard = new Button("Reset Board");
        TextArea moveInfoTextArea = new TextArea();
        Button enterRiggedTile = new Button("Enter - (example r5)");
        enterRiggedTile.setOnAction(rigTileButton);
        
        VBox vbox = new VBox();
        Text title = new Text("Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().addAll(title,drawTileButton, saveBoardButton, resetBoard, tileSelectedLabel, moveInfoTextArea, riggedTextField, enterRiggedTile);
        vbox.setSpacing(20);
        
        //Draw Tile Section
        drawTileButton.setMaxWidth(Double.MAX_VALUE);
        drawTileButton.setOnAction(drawTileButtonPress);
        
        //End Turn Section
        saveBoardButton.setMaxWidth(Double.MAX_VALUE);
        saveBoardButton.setOnAction(saveBoardAction);
        
        //ResetBoard Button Section
        resetBoard.setMaxWidth(Double.MAX_VALUE);
        resetBoard.setOnAction(resetBoardAction);
        
        //Tile Selected Section
        tileSelectedLabel.setMaxWidth(Double.MAX_VALUE);
        
        //TextArea Section
        moveInfoTextArea.setMaxWidth(200);
        moveInfoTextArea.setText("Move Information");
        moveInfoTextArea.setEditable(false);      
        
        //RiggedTextField section
        riggedTextField.setMaxWidth(Double.MAX_VALUE);
        
        //enterRiggedTile button Section
        enterRiggedTile.setMaxWidth(Double.MAX_VALUE);
        
        return vbox;
    }
	
    public void draw() {
    	// Draw hand tiles
    	// Remove all images and tile references from buttons first
    	// Aka reset it to a blank canvas
    	int i = 0;
    	while (i < 64) {
    		handButtons[i].setTile(null);
    		handButtons[i].setGraphic(null);
    		i++;
    	}
    	// Add cooresponding tile reference to buttons
    	ArrayList<Tile> handTiles = controller.getHandTiles();
    	int index = 0;
    	for (Tile t : handTiles) {
    		if (index > 64) {
    			System.out.print("Error, can't have more than 64 tiles in hand");
    			controller.returnBoard().getDeck().addTile(t);
    			handTiles.remove(t);
    			return;
    		}
    		handButtons[index].setTile(t);
    		index++;
    	}
    	for (RummiButton b : handButtons) {
    		if (b.getTile() != null) {
    			ImageView img = new ImageView(b.getTile().getTileImage());
        		img.setFitHeight(50);										//image resize
                img.setFitWidth(70);										//image resize
                b.setGraphic(img);
    		}
    	}
    	
    	// Draw board tiles
    	// Remove all images and tile references from buttons first
    	// Aka reset it to a blank canvas
    	for (RummiButton b : boardButtons) {
    		b.setTile(null);
    		b.setGraphic(null);
    	}
    	// Add tile references to the cooresponding button
    	HashMap<Point, Tile> boardTiles = controller.getBoardTiles();
    	int buttonIndex = 0;
    	for (int x = 1; x <= BoardController.BOARDSIZE; x++) {
    		for (int y = 1; y <= BoardController.BOARDSIZE; y++) {
    			boardButtons.get(buttonIndex).setTile(boardTiles.get(new Point(x,y)));
    			buttonIndex++;
    		}
    	}
    	for (RummiButton b : boardButtons) {
    		if (b.getTile() != null) {
    			ImageView img = new ImageView(b.getTile().getTileImage());
        		img.setFitHeight(30);										//image resize
                img.setFitWidth(40);										//image resize
                b.setGraphic(img);
    		}
    	}
    }
    
    public Parent asParent() {
    	return root;
    }
    
	public BoardController getController() {
		return controller;
	}
	
}

