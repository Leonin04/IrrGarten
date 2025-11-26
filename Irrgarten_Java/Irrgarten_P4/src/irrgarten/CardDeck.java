package irrgarten;


import java.util.ArrayList;
import java.util.Collections;

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
        if(this.cardDeck.isEmpty()){
            this.addCards();
            Collections.shuffle(this.cardDeck);
        }
        T carta=this.cardDeck.get(0);
        this.cardDeck.remove(0);
        return carta;
    }
   

}