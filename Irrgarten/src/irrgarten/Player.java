
package irrgarten;

import java.util.ArrayList;

public class Player {
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
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
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    private float sumWeapons(){
        float suma = 0.0f;
        for (int i=0; i< weapons.size(); i++){
            suma += weapons.get(i).attack();
        }
        return suma;
    }
    
    private float sumShields(){
        float suma = 0.0f;
        for (int i=0; i< shields.size(); i++){
            suma += shields.get(i).protect();
        }
        return suma;
    }
    
    private float defensiveEnergy(){
        return intelligence + sumShields();
    }
    
    private boolean manageHit(float receivedAttack){
        float defense= defensiveEnergy();
        boolean lose=false;
        
        if(defense<receivedAttack){
            getWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }

        if(consecutiveHits==HITS2LOSE || (dead())) {
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
    
    private void getWounded(){
        health--;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    public Player ( char number , float intelligence, float strength){
           this.number=number;
           this.intelligence=intelligence;
           this.strength=strength;
           this.name = "Player #"+number;
           this.row=-1;
           this.col=-1;
           this.health=INITIAL_HEALTH;
           this.weapons = new ArrayList<>();
           this.shields = new ArrayList<>();
    }
    
    public void resurrect(){
        assert dead() : "Estas intentando resucitar sin estar muerto";
       
        this.health=INITIAL_HEALTH;
        this.weapons.clear();
        this.shields.clear();
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }  
    
    public char getNumber(){
        return this.number;
    }
    
    public void setPos(int row, int col){
        this.row=row;
        this.col= col;
    }
    
    public boolean dead(){
        if (this.health <=0) {
                return true;
        } else {
                return false;
        }
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
    
    public float attack(){
        return this.strength + sumWeapons();
    }
    
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
        this.health += extraHealth;
    }
    
    @Override
    public String toString(){
        return ("Name: " + name + ", Number: " + number + ", Intelligence: " + intelligence + ", Strength: " + strength + 
                ", Health: " + health + ", Position: (" + row + "," + col + ") , ConsecutiveHits: " + consecutiveHits + " \nArmas: \n" + weapons + "\nEscudos: \n" + shields);
    }
    
    
    
}
