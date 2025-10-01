
package irrgarten;

import java.util.Random;

public class Dice {
    private static final int MAX_USES = 5; //Usos máximos de armas y escudos
    private static final float MAX_INTELLIGENCE = 10.0f; //Valor máximo de inteligencia para monstruso y jugadores
    private static final float MAX_STRENGTH = 10.0f; //Valor máximo de fuerza para monstruos y jugadores
    private static final float RESURRECT_PROB = 0.3f; //Probabilidad de que un jugador sea resucitado en cada turno
    private static final int WEAPONS_REWARD = 2; //Número máximo de armas recibidas al ganar un combate
    private static final int SHIELDS_REWARD = 3; //Número máximo de escudos recibidos al ganar un combate
    private static final int HEALTH_REWARD = 5; //Saludo máxima recibida al ganar un combate
    private static final float MAX_ATTACK = 3.0f; //Potencia máxima de armas
    private static final float MAX_SHIELD = 2.0f; //Protección máxima de escudos
    
    private static final Random generator = new Random();
    
    public int randomPos (int max){
        
        return generator.nextInt(max);
    }
    
    public int whoStarts (int nplayers){
        
        return generator.nextInt(nplayers);
    }
    
    public float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    }
    
    public float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    public boolean resurrectPlayer(){
        boolean resucita = false;
        if (generator.nextFloat(1.0f) < 0.3) {
            resucita = true;
        }
        return resucita;
    }
    
    public int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD+1);
    }
    
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD+1);
    }
    
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD+1);
    }
    
    public float weaponPower(){
        return generator.nextFloat(MAX_ATTACK);
    }
    
    public float shieldPower(){
        return generator.nextFloat(MAX_SHIELD);
    }
    
    public int usesLeft(){
        return generator.nextInt(MAX_USES+1);
    }
    
    public float intensity(float competence){
        return generator.nextFloat(competence);
    }
    
    public boolean discardElement(int usesLeft){
        boolean descartado = false;
        float probabilidad = 1.0f - ((float) usesLeft/(float) MAX_USES);
        if (generator.nextFloat(1.0f) < probabilidad){
            descartado = true;
        }
        
        return descartado;
    }
}
