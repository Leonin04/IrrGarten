
package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 3;
    private static final int HITS2LOSE = 3;
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    private char number;
    private int consecutiveHits=0;
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    
    private void receiveWeapon( Weapon w){ 
        for(int i=0; i< weapons.size(); i++){
            boolean discard=weapons.get(i).discard();
            
            if (discard){
                weapons.remove(i);
            }
        }
        
        int size= weapons.size();
        if (size< MAX_WEAPONS){ 
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s){ 
        for(int i=0; i< shields.size(); i++){
            boolean discard=shields.get(i).discard();
            
            if (discard){
                shields.remove(i);
            }
        }
        
        int size= shields.size();
        if (size< MAX_SHIELDS){ 
            shields.add(s);
        }
    }
    
    private Weapon newWeapon(){
        return weaponCardDeck.nextCard();
    }
    
    private Shield newShield(){
        return shieldCardDeck.nextCard();
    }
    
    protected float sumWeapons(){
        float suma = 0.0f;
        for (int i=0; i< weapons.size(); i++){
            suma += weapons.get(i).attack();
        }
        return suma;
    }
    
    protected float sumShields(){
        float suma = 0.0f;
        for (int i=0; i< shields.size(); i++){
            suma += shields.get(i).protect();
        }
        return suma;
    }
    
    protected float defensiveEnergy(){
        return super.getIntelligence() + sumShields();
    }
    
    private boolean manageHit(float receivedAttack){
        float defense= defensiveEnergy();
        boolean lose=false;
        
        if(defense<receivedAttack){
            super.getWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }

        if(consecutiveHits==HITS2LOSE || (super.dead())) {
            resetHits();
            lose=true;
        } else {
            lose=false;
        }
        return lose;
    }
    
    private void resetHits(){
        consecutiveHits=0;
    }
    
   
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    public Player ( char number , float intelligence, float strength){
           super("Player"+number,intelligence,strength,INITIAL_HEALTH);
           this.number=number;
           this.weapons = new ArrayList<>();
           this.shields = new ArrayList<>();
           this.weaponCardDeck = new WeaponCardDeck();
           this.shieldCardDeck = new ShieldCardDeck();
    }

    public Player(Player other){
        super(other);
        this.number=other.number;
        this.consecutiveHits=other.consecutiveHits;
        this.weapons = other.weapons;
        this.shields = other.shields;
        this.weaponCardDeck = other.weaponCardDeck;
        this.shieldCardDeck =other.shieldCardDeck;
    }
    
    public void resurrect(){
        assert super.dead() : "Estas intentando resucitar sin estar muerto";
       
        super.setHealth(INITIAL_HEALTH);
        this.weapons.clear();
        this.shields.clear();
    }
    
    
    
    public char getNumber(){
        return this.number;
    }
    
    public Directions move ( Directions direction, ArrayList<Directions> validMoves){ 
        Directions dir=direction;
        
        int size= validMoves.size();
       
        boolean contained=validMoves.contains(direction); 
        
        if (!contained && size>0){
            dir=validMoves.get(0);
        }
        
          return dir;
    }
    
    @Override
    public float attack(){
        return super.getStrength()+ sumWeapons();
    }
    
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        int wReward= Dice.weaponsReward();
        int sReward= Dice.shieldsReward();
        
        for (int i=0; i< wReward; i++){
            Weapon wnew= newWeapon();
            this.receiveWeapon(wnew);
        }
        for (int i=0; i< sReward; i++){
            Shield snew= newShield();
            this.receiveShield(snew);
        }
        int extraHealth= Dice.healthReward();
        super.setHealth((super.getHealth()+extraHealth));
    }
    
    @Override
    public String toString(){
        return (super.toString()+ ", Number: " + number 
        + ", ConsecutiveHits: " + consecutiveHits + " \nArmas: \n" + weapons + "\nEscudos: \n" + shields + "\nBaraja Armas: \n" + weaponCardDeck + "\nBaraja Escudos: \n" + shieldCardDeck + "\n");
    }
    
    
    
}
