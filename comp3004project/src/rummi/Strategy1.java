package rummi;

public class Strategy1 extends Player{

	public int thirty_or_over() {
		// TODO Auto-generated method stub
		if (getHandValue() >= 30) {
			return getHandValue();
		}
		else {
			return 1;
		}
	}

	
}
