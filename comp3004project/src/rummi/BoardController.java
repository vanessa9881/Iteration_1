package rummi;

import java.util.ArrayList;

public class BoardController {

	private final Board board;
	
	public BoardController(Board model) {
		board = model;
	}

	public Tile drawTile() {
		return board.drawTile();
	}
	
	public void placeTile(int xpos, int ypos, Tile t) {
		board.addBoardTile(t, xpos, ypos);
	}

	public ArrayList<Tile> getHandTiles() {
		return board.getHandTiles();
	}
}
