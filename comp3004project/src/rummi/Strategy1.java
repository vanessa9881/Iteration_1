package rummi;

public class Strategy1 extends Player{
	Player p2= new Player();
	
	public int thirty_or_over() {
		p2.setHandValue(40);
		// TODO Auto-generated method stub
		if (p2.getHandValue() >= 30) {
			return p2.getHandValue();
		}
		else {
			return 1;
		}
	}

	public boolean playTurn() {
		p2.setHandValue(20);
		// TODO Auto-generated method stub
		if (p2.getHandValue()>1) {
			//String c = "Play all the melds it can";
			return true;
		}
		return false;
	}

	
}
