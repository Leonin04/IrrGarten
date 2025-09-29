
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
    private static final int MAX_ATTACK = 3; //Potencia máxima de armas
    private static final int MAX_SHIELD = 2; //Protección máxima de escudos
    
    private static final Random generator = new Random();
}
