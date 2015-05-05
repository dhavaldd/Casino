package BlackJack;

public class BlackJackPlayer {

	String name;
	public int rankCard1 = -1;
	public int suitCard1 = -1;

	public int rankCard2 = -1;
	public int suitCard2 = -1;

	public int rankCard3 = -1;
	public int suitCard3 = -1;
	
	public int amount;
	
	public String toString(){
		return "name:"+name
				+",rankCard1:"+rankCard1
				+",suitCard1:"+suitCard1

				+",rankCard2:"+rankCard2
				+",suitCard2:"+suitCard2

				+",rankCard3:"+rankCard3
				+",suitCard3:"+suitCard3
				
				+",amount:"+amount;
	}
	
}
