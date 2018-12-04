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
    
    private Tile priorSelectedTile;
    private Tile selectedTile;
    
    private BoardController controller;
    
    EventHandler<ActionEvent> boardButtonPress = new EventHandler<ActionEvent>() {
    	public void handle(final ActionEvent e) {
    		priorSelectedTile = selectedTile;
    		selectedTile = ((RummiButton) e.getSource()).getTile();

    		if (selectedTile == null ) {
        		System.out.println("Selected board space is empty");
        		if (priorSelectedTile != null) {
            		int x = ((RummiButton) e.getSource()).getPos()[0] + 1;
            		int y = ((RummiButton) e.getSource()).getPos()[1] + 1;
                	System.out.println("Column Number " + x + " and " + " Row Number " + y);	
                	System.out.println("------------------------------------------------------------");
                	
                	if (controller.placeTile(x, y, priorSelectedTile)) {
                		System.out.println("\nTile added to board");
                		controller.removeHandTile(priorSelectedTile);
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
    		controller.drawTile();
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
	    createBoardButtons();
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
		for (int x = 0; x < controller.BOARDSIZE; x++) {
			for(int y = 0; y < controller.BOARDSIZE; y++) {		
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
        TextArea moveInfoTextArea = new TextArea();
        
        VBox vbox = new VBox();
        Text title = new Text("Information");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().addAll(title,drawTileButton, endTurnButton, tileSelectedLabel, moveInfoTextArea);
        vbox.setSpacing(20);
        
        //Draw Tile Section
        drawTileButton.setMaxWidth(Double.MAX_VALUE);
        drawTileButton.setOnAction(drawTileButtonPress);
        
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
    	for (int x = 1; x <= controller.BOARDSIZE; x++) {
    		for (int y = 1; y <= controller.BOARDSIZE; y++) {
    			boardButtons.get(buttonIndex).setTile(boardTiles.get(new Point(x,y)));
    			buttonIndex++;
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

	//public Parent asParent() {
	//	return gameBoard;
	//}
	
}

