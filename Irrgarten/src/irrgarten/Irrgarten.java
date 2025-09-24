package irrgarten;

/**
 *
 * @author leonin04
 */
public class Irrgarten {

    static void pruebaWeapon (){
        Weapon arma1 = new Weapon(10.0f,1);
        arma1.attack();
        
        System.out.println(arma1.toString());
    }
            
    public static void main(String[] args) {
       System.out.println("hola");
       pruebaWeapon();
       pruebaWeapon();
    }
    
}
