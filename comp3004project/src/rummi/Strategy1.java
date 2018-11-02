package rummi;

public class Strategy1 extends Player{
	Player p1= new Player();
	
	public int thirty_or_over() {
		p1.setHandValue(40);
		// TODO Auto-generated method stub
		if (p1.getHandValue() >= 30) {
			return p1.getHandValue();
		}
		else {
			return 1;
		}
	}

	public boolean playTurn() {
		p1.setHandValue(20);
		// TODO Auto-generated method stub
		if (p1.getHandValue()>1) {
			//String c = "Play all the melds it can";
			return true;
		}
		return false;
	}

	
}
