
package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player{
    

    public FuzzyPlayer(Player other){
        super(other.getNumber(),other.getIntelligence(),other.getStrength());
    }

    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions preference=super.move(direction, validMoves);
        return Dice.nextStep(preference, validMoves, super.getIntelligence());
       
    }

    @Override
    public float attack(){
        return(Dice.intensity(super.getStrength())+super.sumWeapons());
    }

    @Override
    protected float defensiveEnergy(){
        return (Dice.intensity(super.getIntelligence())+super.sumShields());
    }

    @Override
    public String toString(){
        return super.toString();
    }


}