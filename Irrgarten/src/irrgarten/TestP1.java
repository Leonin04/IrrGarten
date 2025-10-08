
package irrgarten;

/**
 *
 * @author leonin04
 */
public class TestP1 {
    static void pruebaEnum (){
        for (Directions dir : Directions.values()) {
            System.out.println("Probando dirección: " + dir);
        }
        
        for (Orientation ori : Orientation.values()) {
            System.out.println("Probando orientación: " + ori);
        }
        
        for (GameCharacter charac : GameCharacter.values()) {
            System.out.println("Probando personajes: " + charac);
        }

    }

    static void pruebaWeapon (float daño, int usos){
        Weapon arma1 = new Weapon(daño,usos);
        System.out.println("Dañó: " + arma1.attack());
        
        System.out.println(arma1.toString());
        
        System.out.println("¿Se descarta? " + arma1.discard());
    }
    
    static void pruebaShield(float durabilidad, int usos) {
        Shield escudo1 = new Shield(durabilidad,usos);
        System.out.println("Protegio: " + escudo1.protect());
        
        System.out.println(escudo1.toString());
        
        System.out.println("¿Se descarta? " + escudo1.discard());

    }
    
    static void pruebaDice(){
        
        for (int i=0; i<100; i++){
            System.out.println("\nPrueba " + (i+1));
            System.out.println("Posición: " + Dice.randomPos(10));
            System.out.println("Empieza jugador " + Dice.whoStarts(3));
            System.out.println("Inteligencia " + Dice.randomIntelligence());
            System.out.println("Fuerza " + Dice.randomStrength());
            System.out.println("¿Resucita? " + Dice.resurrectPlayer());
            System.out.println("Armas conseguidas: " + Dice.weaponsReward());
            System.out.println("Escudos conseguidos: " + Dice.shieldsReward());
            System.out.println("Salud conseguida: " + Dice.healthReward());
            System.out.println("Poder arma: " + Dice.weaponPower());
            System.out.println("Poder escudo: " + Dice.shieldPower());
            int usos = Dice.usesLeft();
            System.out.println("Usos restantes: " + usos);
            System.out.println("Intensidad: " + Dice.intensity(3.0f));
            System.out.println("¿Se descarta? " + Dice.discardElement(usos));
        }
    }
    
    static void pruebaGameState(){
        GameState game = new GameState("laberintooo", "jugadoresee", "monstruooos", 1, false, "logeee");
        System.out.println(game.getLabyrinth());
        System.out.println(game.getPlayers());
        System.out.println(game.getMonsters());
        System.out.println(game.getCurrentPlayer());
        System.out.println(game.getWinner());
        System.out.println(game.getLog());
    }
    
     public static void main(String[] args) {
       System.out.println("Probando enum:");
       pruebaEnum();
       
       System.out.println("\n Probando arma:");
       pruebaWeapon(10.0f,1);
       pruebaWeapon(10.0f,0);
       
       System.out.println("\n Probando escudo:");
       pruebaShield(10.0f,1);
       pruebaShield(10.0f,0);
       
       System.out.println("\n Probando dado:");
       pruebaDice();
       
       System.out.println("\nProbando GameState:");
       pruebaGameState();

    }
}
