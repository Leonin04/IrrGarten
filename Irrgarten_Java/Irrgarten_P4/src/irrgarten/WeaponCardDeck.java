package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon>{


    private static final int DEFAULT_DECK_SIZE = 10; 
    
    @Override 
    protected void addCards(){
        for(int i=0; i<DEFAULT_DECK_SIZE; i++){
            this.addCard(new Weapon(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}