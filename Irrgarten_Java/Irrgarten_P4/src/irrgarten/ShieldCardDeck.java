package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield>{


    protected void addCards(){
        for(int i=0; i<ShieldCardDeck.size(); i++){
            this.addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
    }
}