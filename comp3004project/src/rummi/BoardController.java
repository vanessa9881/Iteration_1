package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardController {

	private final Board board;
	private final BoardView view;
	public static final int BOARDSIZE = 15;
	
	public BoardController(Board model, BoardView view) {
		board = model;
		this.view = view;
	}

	public void drawTile() {
		board.drawTile();
	}
	
	public Board returnBoard() {
		return board;
	}
	
	/*
	public ArrayList<Tile> getDeck() {
		return board.getDeckTiles();
	}
	*/
	
	public boolean placeTile(int xpos, int ypos, Tile t) {
		return board.addBoardTile(t, xpos, ypos);
	}

	public ArrayList<Tile> getHandTiles() {
		return board.getHandTiles();
	}

	public HashMap<Point, Tile> getBoardTiles() {
		return board.getBoardTiles();
	}
	
	public void updateView() {
		view.draw();
	}

	public void removeHandTile(Tile priorSelectedTile) {
		board.removeHandTile(priorSelectedTile);
		
	}

	public boolean moveTile(int x, int y, Tile priorSelectedTile) {
		return board.moveBoardTile(x, y, priorSelectedTile);
		
	}
}
