
package irrgarten;

/**
 *
 * @author leonin04
 */
public class Monster {
    private static final int INITIAL_HEALTH = 5;
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster (String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        health = INITIAL_HEALTH;
        row = -1;
        col = -1; //Con esto representamos que no está colocado
    }
    
    public boolean dead(){
        if (health <= 0){
            return true;
        } else return false;
    }
    
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack){
        boolean isDead = dead();
        if(isDead == false){
            float defensiveEnergy = Dice.intensity(intelligence);
            if (defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = this.dead();
            }
            
        }
        return isDead;
    }  
    
    public void setPos(int row, int col){
        assert row >= 0 && col >= 0 : "El monstruo no puede estar en una posición negativa";
        
        this.row = row;
        this.col = col;
    }
    
    public void gotWounded(){
        health--;
    }
    
    @Override
    public String toString(){
        return ("Name: " + name + ", Intelligence: " + intelligence + ", Strength: " + strength + ", Health: " + health + ", Position: (" + row + "," + col + ")\n");
    }
}
