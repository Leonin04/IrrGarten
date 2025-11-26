package irrgarten;


import java.util.ArrayList;
import javax.swing.AbstractAction;

abstract class CardDeck <T extends CombatElement>{
    private ArrayList<T> cardDeck;

    public CardDeck(){
        cardDeck=new ArrayList<>();
    }

    protected void addCard(T card){
        cardDeck.add(card);
    }

    protected abstract void addCards();

    public T nextCard(){
        //
    }

}