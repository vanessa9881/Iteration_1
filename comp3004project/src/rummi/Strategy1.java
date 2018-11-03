package rummi;

public class Strategy1 extends Player{
	Player p1= new Player();
	
	public void play(Game game){
		
	}

	public boolean playTurn() {
		// TODO Auto-generated method stub
		if (p1.getHandValue()>1) {
			//String c = "Play all the melds it can";
			return true;
		}
		return false;
	}

	
}
