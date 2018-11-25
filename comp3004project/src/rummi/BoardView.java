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
    int saveHMap = 0, currentHMap = 0;
    ArrayList<Object> storedBoard;
    
    //Level 4 shit
    TextField riggedTextField;
    String riggedColour;
    String riggedNumber;
    
    private Tile priorSelectedTile;
    private Tile selectedTile;
    
    private BoardController controller;
    private Board board;
    
	public BoardView(BoardController controller, Board model) {
		this.controller = controller;
		board = model;
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
		
		root.setRight(addVBox());
		root.setBottom(userScrollPane);
		userScrollPane.setContent(userPane);
		userScrollPane.setMaxHeight(100);
		userScrollPane.setFitToWidth(true);
		userScrollPane.setFitToHeight(true);
		createHandButtons();	
		
		storedBoard = board.getState();
		originator.setState(storedBoard);
		caretaker.addMemento(originator.createMemento());
		
		saveHMap++;
		currentHMap++;
		//resetBoard.setDisable(false);
		System.out.println("Added the HMap to the arraylist");
		
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
    		
    	}
    };
    
    EventHandler<ActionEvent> resetBoard = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		//Reset Board to precious copy (doesnt need to check if board valid, this is user activated) 
    		if(currentHMap >= 1) {
    			currentHMap--;
    			ArrayList<Object> previousHMap = originator.restoreFromMemento(caretaker.getMemento(currentHMap));
    			board.setState(previousHMap);
    		}
    	}
    };
      
    EventHandler<ActionEvent> rigTileButton = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
        	String value = riggedTextField.getText();
        	riggedColour = Character.toString(value.charAt(0));
        	riggedNumber = Character.toString(value.charAt(1));
        	board.drawRiggedTile(riggedColour, riggedNumber);	
        	for (Tile t : controller.getDeck()) {
        		System.out.println(t);
        	}
        	System.out.println("Pressed rigged button");
    	}
    };
    
    EventHandler<ActionEvent> handButtonPress = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		priorSelectedTile = selectedTile;
    		selectedTile = ((RummiButton) e.getSource()).getTile();
    		System.out.println("Selected hand tile: " + selectedTile.toString());
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
        Button endTurnButton = new Button("End Turn");
        Button resetBoard = new Button("Reset Board");
        TextArea moveInfoTextArea = new TextArea();
        Button enterRiggedTile = new Button("Enter - (example r5)");
        enterRiggedTile.setOnAction(rigTileButton);
        
        VBox vbox = new VBox();
        Text title = new Text("Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().addAll(title,drawTileButton, endTurnButton, resetBoard, tileSelectedLabel, moveInfoTextArea, riggedTextField, enterRiggedTile);
        vbox.setSpacing(20);
        
        //Draw Tile Section
        drawTileButton.setMaxWidth(Double.MAX_VALUE);
        drawTileButton.setOnAction(drawTileButtonPress);
        
        //End Turn Section
        endTurnButton.setMaxWidth(Double.MAX_VALUE);
        
        //ResetBoard Button Section
        resetBoard.setMaxWidth(Double.MAX_VALUE);
        
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
	
    public Parent asParent() {
    	return root;
    }
    
	public BoardController getController() {
		return controller;
	}
	
}
