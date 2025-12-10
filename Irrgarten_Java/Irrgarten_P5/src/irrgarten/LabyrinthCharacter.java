/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author leonin04
 */
abstract public class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private int row;
    private int col;
    private float health;
    
    
    public LabyrinthCharacter(String name,float intelligence,float strength,float health){
        this.intelligence=intelligence;
        this.strength=strength;
        this.name = name;
        this.row=-1;
        this.col=-1;
        this.health=health;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        intelligence=other.intelligence;
        strength=other.strength;
        name=other.name;
        row=other.row;
        col=other.col;
        health=other.health;
    }
    
    public boolean dead(){
        if (this.health <=0) {
                return true;
        } else {
                return false;
        }
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
    }
    
    protected void setHealth(float health){
        this.health=health;
    }
    
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    @Override
    public String toString(){
        return ("Name: " + name + ", Intelligence: " + intelligence + ", Strength: " + strength + ", Health: " + health + ", Position: (" + row + "," + col +")");
    
    }
    
    protected void getWounded(){
        health--;
    }
    
    abstract public float attack();
    
    abstract public boolean defend(float attack);
}
