package rummi;

import javafx.scene.control.Button;

public class RummiButton extends Button implements Comparable<RummiButton>{

	int xPos;
	int yPos;
	Tile buttonTile;
	
	public RummiButton(){
	}
	
	public RummiButton(int x, int y){
		xPos = x;
		yPos = y;
	}
	
	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public int[] getPos() {
		int[] pos = {xPos, yPos};
		return pos;
	}
	
	public void setTile(Tile t) {
		buttonTile = t;
	}
	
	public Tile getTile() {
		return buttonTile;
	}
	
	@Override
	public int compareTo(RummiButton b2) {
		if (buttonTile == null && b2.getTile() == null) {
			return 0;
		}
		if (buttonTile == null) {
			return 1;
		}
		if ((b2).getTile() == null) {
			return -1;
		}
		if (this.getTile().getValue() > b2.getTile().getValue()) {
			return 1;
		}
		else if (this.getTile().getValue() < b2.getTile().getValue()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
