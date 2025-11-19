package irrgarten;

public class Shield extends CombatElement{
    
    public Shield (float protection, int uses) {
        super(protection,uses);
    }
    
    public float protect() {
        return super.produceEffect();
    }
    
    @Override
    public String toString () { 
        String temp = super.toString();
        return "S" + temp;
    }
}