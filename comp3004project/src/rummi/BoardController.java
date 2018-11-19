package rummi;

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
}
