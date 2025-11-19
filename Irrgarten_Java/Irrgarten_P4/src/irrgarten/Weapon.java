
package irrgarten;

/**
 *
 * @author leonin04
 */
public class Weapon extends CombatElement{
    
    public Weapon (float power, int uses) {
        super(power,uses);
    }
    
    public float attack () {
        return super.produceEffect();
    }
    
    
    @Override
    public String toString () { 
        String temp = super.toString();
        return "W" + temp;
    }
    
}
