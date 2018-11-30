//is getDeck wrong?
package rummi;

import java.awt.Point;
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
    
    Caretaker caretaker;
    Originator originator;
    int savedBoardNumber = 0, currentBoardNumber = 0;
    ArrayList<Object> storedBoardState;
    ArrayList<Object> tempCurrentBoardState;
    
    //Level 4 shit
    TextField riggedTextField;
    String riggedColour;
    String riggedNumber;
    
    private Tile priorSelectedTile;
    private Tile selectedTile;
    
    private BoardController controller;
    private Board board;
    private Board tempBoard;
    
	public BoardView(BoardController controller, Board model) {
		this.controller = controller;
		board = model;
		tempBoard = model;				//????????????????????????????????????????????
		caretaker = new Caretaker();
		originator = new Originator();	
		riggedTextField = new TextField();
		
		initView();
	}
    
	private void initView() {
		root = new BorderPane();	
		gameBoard = new GridPane();
		userPane = new FlowPane();
		userScrollPane = new ScrollPane();
		tileSelectedLabel = new Label("Selected Tile");
		
		storedBoardState = new ArrayList<Object>();
		tempBoard = new Board();
		
		
		root.setRight(addVBox());
		root.setBottom(userScrollPane);
		userScrollPane.setContent(userPane);
		userScrollPane.setMaxHeight(100);
		userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
		createHandButtons();	
		

		//resetBoard.setDisable(false);
		
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
            		int x = ((RummiButton) e.getSource()).getPos()[0];
            		int y = ((RummiButton) e.getSource()).getPos()[1];
                	System.out.println("Column Number " + x + " and " + " Row Number " + y);	
                	System.out.println("------------------------------------------------------------");
                	// Controller will call a method in Board that checks whether the tile can be added
                	// Will probably change the return type of this method to boolean,
                	// so that if true that means the tile was added and that the board button
                	// can have it's graphic changed
                	
                	if (controller.placeTile(x, y, priorSelectedTile)) {
                		// Update the button's graphic!
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
    		/*
    		System.out.println("Hello:	" + originator.restoreBoardFromMemento(caretaker.getMemento((currentBoardNumber)-1)));
    		System.out.println("-------------------------------------------------------------------------------------------");
    		System.out.println("Hello000:	" + originator.restoreBoardFromMemento(caretaker.getMemento((currentBoardNumber)-2)));
    		*/
    	}
    };
    
    EventHandler<ActionEvent> saveBoardAction = new EventHandler<ActionEvent>() {	
    	public void handle(final ActionEvent e) {
    		
    		/*
    		board.drawTile();
    		System.out.println(board.getDeck());
    		System.out.println("Default board address: " + board);
    		System.out.println("Default board.deck address: " + board.getDeckForMemento() + "\n\n\n\n");
    		storedBoardState = board.getSavedState();
    		originator.set(storedBoardState);		
    		caretaker.addMemento(originator.saveToMemento());
    		System.out.println("Currently saved state in caretaker ArrayList: \n" + caretaker.getMemento(0).getDeck() +"..." + caretaker.getMemento(0).getDeckForMemento()+"\n\n\n" );
    		
    		System.out.println("Caretaker memento address:" + caretaker.getMemento(0));				//A
    		System.out.println("Caretaker Deck:" + caretaker.getMemento(0).getDeckForMemento()+ "\n");
    		System.out.println(caretaker.getMemento(0).getDeck() +"\n");
    		System.out.println("Board memento address:" + board);									//A
    		System.out.println("Board deck:" + board.getDeckForMemento());
    		//board.drawTile();
    		//System.out.println("Currently saved state in caretaker ArrayList after removing tile from board: \n" + caretaker.getMemento(0).getDeck() +"\n\n\n" );
    		//System.out.println("This Deck should have first tiles deleted: \n" + board.getDeck()+"\n\n\n");
    		//originator.restoreFromMemento(caretaker.getMemento(0));
    		//board.setState(originator.getState());
    		//System.out.println("This Deck should have all tiles: \n" + board.getDeck()+"\n\n\n");
    		
    		/*
    		//getting default board constructors state and setting it to tempCurrentBoardState and then adding that state into a blank arralist<object>
    		//Then setting that to a new baord and adding that to the arrayList of boards.
    		
    		storedBoardState = board.getState();	//Setting the values of the storedBoardState to that of the board (original empty board created)
    		originator.setState(storedBoardState);	//Creating a blank temp ArrayList<Object> and setting its values of the storedBoardState
    		caretaker.addMemento(originator.storeInMemento());	//Creating a Board with the storesBoardState and adding that to the caretaker arrayList of boards		
    		System.out.println("Currently saved state in caretaker ArrayList: " + originator.restoreFromMemento(caretaker.getMemento(currentBoardNumber)).get(1));
    		
    		savedBoardNumber++;
    		currentBoardNumber++;
    		
    		tempCurrentBoardState = originator.restoreFromMemento(caretaker.getMemento(currentBoardNumber));
    		savedBoardNumber++;
    		currentBoardNumber++;
    		System.out.println("Saved Boards: " + savedBoardNumber + "\n\n");
    		tempBoard = new Board();
			tempBoard.setState(tempCurrentBoardState);
    		tempBoard.drawTile();
    		System.out.println("TempBoard used for editing: \n" + tempBoard.getDeck() + "..." + tempBoard.getDeckForMemento());
    		System.out.println("-----------------------------------------------------------------------");
 
    		System.out.println("Saved board in arraylist board: \n" + board.getDeck() + "..." + board.getDeckForMemento());
    		
    		/* attempt 1
    		//Create a temp Board with the state of the one saved in board
    		if(currentBoardNumber >= 1) {
    			currentBoardNumber--;
            	System.out.println("--------------------------------------------------------------------");
            	tempCurrentBoardState = originator.restoreFromMemento(caretaker.getMemento(currentBoardNumber));
    			tempBoard.setState(tempCurrentBoardState);
    			System.out.println("This is the State of the tempBoard: " + tempBoard.getDeck());
    			currentBoardNumber++;
    		}
    		//Here a new board State needs to be initialized with the previous board state????????????????? because when we delete a tile from the deck
    		//It deletes it from the deck in the array list.
    		  
    		  */
    		 
    	}
    };
    
    EventHandler<ActionEvent> resetBoardAction = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		//Reset Board to precious copy (doesnt need to check if board valid, this is user activated) 
    		/*
    		if(currentBoardNumber >= 1) {
    			currentBoardNumber--;
            	System.out.println("--------------------------------------------------------------------");
            	System.out.println("Now the Reset method should invoke and the deck should contain all tiles again");
    			ArrayList<Object> previousBoardState = originator.restoreFromMemento(caretaker.getMemento(currentBoardNumber));
    			board.setState(previousBoardState);
    			System.out.println("Address of Deck we are looking at after reset method:  " + board.getDeckForMemento() + "\n\n");
    			System.out.println("Deck in Board: " + board.getState().get(1));
    		}
    		*/
    	}
    };
      
    EventHandler<ActionEvent> rigTileButton = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {	
    		/*
    		System.out.println("--------------------------RIGGED TILE PRESS------------------------------------------\n\n");
        	String value = riggedTextField.getText();
        	riggedColour = Character.toString(value.charAt(0));
        	riggedNumber = Character.toString(value.charAt(1));
        	board.drawRiggedTile(riggedColour, riggedNumber);	
        	*/
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
		for (int x = 0; x < 12; x++) {
			for(int y = 0; y < 12; y++) {		
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
    	ArrayList<Tile> handTiles = controller.getHandTiles();
    	int index = 0;
    	for (Tile t : handTiles) {
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
    	HashMap<Point, Tile> boardTiles = controller.getBoardTiles();
    	int buttonIndex = 0;
    	for (int x = 1; x <= 12; x++) {
    		for (int y = 1; y <= 12; y++) {
    			boardButtons.get(buttonIndex).setTile(boardTiles.get(new Point(x,y)));
    		}
    	}
    	for (RummiButton b : boardButtons) {
    		if (b.getTile() != null) {
    			ImageView img = new ImageView(b.getTile().getTileImage());
        		img.setFitHeight(50);										//image resize
                img.setFitWidth(70);										//image resize
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
