package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield>{


    private static final int DEFAULT_DECK_SIZE = 10; 
    
    @Override 
    protected void addCards(){
        for(int i=0; i<DEFAULT_DECK_SIZE; i++){
            this.addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}