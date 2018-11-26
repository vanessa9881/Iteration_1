package rummi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardController {

	private final Board board;
	
	public BoardController(Board model) {
		board = model;
	}

	public void drawTile() {
		board.drawTile();
	}
	
	public boolean placeTile(int xpos, int ypos, Tile t) {
		return board.addBoardTile(t, xpos, ypos);
	}

	public ArrayList<Tile> getHandTiles() {
		return board.getHandTiles();
	}

	public ArrayList<Tile> getDeck() {
		return board.getDeck();
	}

	public HashMap<Point, Tile> getBoardTiles() {
		return board.getBoardTiles();
	}

}
