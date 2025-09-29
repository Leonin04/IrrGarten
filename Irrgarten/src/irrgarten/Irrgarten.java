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
            
    public static void main(String[] args) {
       System.out.println("Probando arma:");
       pruebaWeapon(10.0f,1);
       pruebaWeapon(10.0f,0);
       
       System.out.println("\n Probando escudo:");
       pruebaShield(10.0f,1);
       pruebaShield(10.0f,0);

    }
    
}
