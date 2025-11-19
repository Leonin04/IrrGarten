/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author leonin04
 */
abstract public class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement( float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        float salida = 0.0f;
        
        if (uses > 0) {
            salida = effect;
            uses--;
        }
        return salida;
    }
    
    public boolean discard(){
        return Dice.discardElement(this.uses);
    }
    
    @Override
    public String toString(){
        return "[" + effect + "," + uses + "]";
    }
}
