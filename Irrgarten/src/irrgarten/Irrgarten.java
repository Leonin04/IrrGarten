package irrgarten;

enum Directions {
    LEFT, RIGHT, UP, DOWN
}

enum Orientation {
    VERTICAL, HORIZONTAL
}

enum GameCharacter {
    PLAYER, MONSTER
}
public class Irrgarten {

    static void pruebaWeapon (float daño, int usos){
        Weapon arma1 = new Weapon(daño,usos);
        System.out.println("Dañó: " + arma1.attack());
        
        System.out.println(arma1.toString());
    }
    
    static void pruebaShield(float durabilidad, int usos) {
        Shield escudo1 = new Shield(durabilidad,usos);
        System.out.println("Protegio: " + escudo1.protect());
        
        System.out.println(escudo1.toString());
    }
    
    static void pruebaDice(){
        Dice dado = new Dice();
        System.out.println("Posición: " + dado.randomPos(10));
        System.out.println("Empieza jugador " + dado.whoStarts(3));
        System.out.println("Inteligencia " + dado.randomIntelligence());
        System.out.println("Fuerza " + dado.randomStrength());
        System.out.println("¿Resucita? " + dado.resurrectPlayer());
        System.out.println("Armas conseguidas: " + dado.weaponsReward());
        System.out.println("Escudos conseguidos: " + dado.shieldsReward());
        System.out.println("Poder arma: " + dado.weaponPower());
        System.out.println("Poder escudo: " + dado.shieldPower());
        int usos = dado.usesLeft();
        System.out.println("Usos restantes: " + usos);
        System.out.println("Intensidad: " + dado.intensity(3.0f));
        System.out.println("¿Se descarta? " + dado.discardElement(usos));
    }
            
    public static void main(String[] args) {
       System.out.println("Probando arma:");
       pruebaWeapon(10.0f,1);
       pruebaWeapon(10.0f,0);
       
       System.out.println("\n Probando escudo:");
       pruebaShield(10.0f,1);
       pruebaShield(10.0f,0);
       
       System.out.println("\n Probando dado:");
       pruebaDice();

    }
    
}
