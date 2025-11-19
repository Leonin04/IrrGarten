
package irrgarten;

import java.util.ArrayList;

public class Labyrinth {
    
    private static char BLOCK_CHAR = 'X';
    private static char EMPTY_CHAR = '-';
    private static char MONSTER_CHAR = 'M';
    private static char COMBAT_CHAR = 'C';
    private static char EXIT_CHAR = 'E';
    private static int ROW = 0;
    private static int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow; //Fila en la que está la salida
    private int exitCol; //Columna en la que está la salida
    
    private Monster[][] monsters; //Array que representa las posiciones del laberinto en las que hay monstruos
    private Player[][] players; //Array que representa las posiciones del laberinto en las que hay jugadores
    private char[][] labyrinth; //Array que representa las posiciones del laberinto
    
    private boolean posOK(int row, int col){
        return (row >= 0 && col >= 0 && row < nRows && col < nCols); 
    }
    
    private boolean emptyPos (int row, int col){
        if (posOK(row,col)){
            return labyrinth[row][col]==Labyrinth.EMPTY_CHAR;
        }
        else 
            return false;
    }
    
    private boolean monsterPos (int row, int col){
        if (posOK(row,col)){
            return labyrinth[row][col]==Labyrinth.MONSTER_CHAR;
        } else 
            return false;
    }
    
    private boolean exitPos (int row, int col){
        if (posOK(row,col)){
            return labyrinth[row][col]==Labyrinth.EXIT_CHAR;
        }
        else 
            return false;
    }
    
    private boolean combatPos(int row, int col){
        if (posOK(row,col)){
            return labyrinth[row][col]==Labyrinth.COMBAT_CHAR;
        }
        else 
            return false;    
    }
    
    private boolean canStepOn (int row, int col){
        if (posOK(row,col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col))){
            return true;
        } else
            return false;
    }
    
    private void updateOldPos (int row, int col){
        if (posOK(row,col)){
            if (labyrinth[row][col]==Labyrinth.COMBAT_CHAR){
                labyrinth[row][col]=Labyrinth.MONSTER_CHAR;
            } else {
                labyrinth[row][col]=Labyrinth.EMPTY_CHAR;
            } 
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int fila=row, columna=col;
        switch (direction){
            case UP -> fila--;
            case DOWN -> fila++;
            case RIGHT -> columna++;
            case LEFT -> columna--;
        }
        return new int[]{fila,columna};
    }
    
    private int[] randomEmptyPos(){
        int fila, columna;
        fila = Dice.randomPos(nRows);
        columna = Dice.randomPos(nCols);
        while (labyrinth[fila][columna]!=Labyrinth.EMPTY_CHAR) {
            fila = Dice.randomPos(nRows);
            columna = Dice.randomPos(nCols);
        }
        return new int[]{fila,columna};
    }
    
    private Monster putPlayer2D (int oldRow, int oldCol, int row, int col, Player player){
        Monster output=null;
        
        if (canStepOn(row,col)){
            if(posOK(oldRow,oldCol)){
                if(this.players[oldRow][oldCol]==player){
                    updateOldPos(oldRow,oldCol);
                    this.players[oldRow][oldCol]=null; 
                }
            }
        
            if(monsterPos(row,col)){
                this.labyrinth[row][col]=Labyrinth.COMBAT_CHAR;
                output=monsters[row][col];  
            }
            else{
                char number=player.getNumber();
                this.labyrinth[row][col]=number;
            }
            
            this.players[row][col]=player;
            player.setPos(row, col);

        }
        return output;

    }
    
    private void set(int row, int col, char block){ //NECESARIO?
            this.labyrinth[row][col]=Labyrinth.BLOCK_CHAR; 
    }
    
    public Labyrinth (int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.labyrinth = new char[nRows][nCols];
        
        for (int i=0; i<nRows;i++){
            for (int j=0; j<nCols; j++){
                monsters[i][j] = null;
                players[i][j] = null;
                labyrinth[i][j] = Labyrinth.EMPTY_CHAR;
            }
        }
        
        labyrinth[exitRow][exitCol] = Labyrinth.EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        for (int i=0; i<players.size(); i++){
            Player p= players.get(i);
            
            int[] pos = randomEmptyPos();
            putPlayer2D(-1,-1,pos[ROW],pos[COL],p);
        }
    }
    
    public boolean haveAWinner(){
        if (players[exitRow][exitCol] != null){
            return true;
        } else
            return false;
    }
    
    @Override
    public String toString(){
        String salida = "Nº de filas: " + nRows + " Nº Columnas: " + nCols + " Casilla de meta: (" + exitRow + "," + exitCol + ")" + "\n Laberinto: \n";
        for (int i=0; i<nRows;i++){
            for (int j=0; j<nCols; j++){
                salida += labyrinth[i][j] + " ";
            }
            salida += "\n";
        }
        return salida;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if (posOK(row,col) && emptyPos(row,col)){
            monsters[row][col]=monster;
            labyrinth[row][col]=Labyrinth.MONSTER_CHAR;
        }
    }
    
    public Monster putPlayer(Directions direction, Player player){ //Que significa este error?
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow=0, incCol=0;
        if(orientation==Orientation.VERTICAL){
            incRow=1;
        } else {
            incCol=1;
        }
        
        int col=startCol, row=startRow;
        
        while(posOK(row,col) && emptyPos(row,col) && length>0){
            set(row,col,BLOCK_CHAR);
            length--;
            row+=incRow;
            col+=incCol;
        }
            
    }
    
    public ArrayList<Directions> validMoves (int row, int col){
        ArrayList<Directions> output = new ArrayList<>();
        
        if (canStepOn(row+1,col)){
            output.add(Directions.DOWN);
        }
        if (canStepOn(row-1,col)){
            output.add(Directions.UP);
        }
        if (canStepOn(row,col+1)){
            output.add(Directions.RIGHT);
        }
        if (canStepOn(row,col-1)){
            output.add(Directions.LEFT);
        }
        return output;
    }
    
    public int getNRows(){
        return nRows;
    }
    
    public int getNCols(){
        return nCols;
    }
    
}
