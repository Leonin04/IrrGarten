
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
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
        assert posOK(row,col) && emptyPos(row,col);
        monsters[row][col]=monster;
        labyrinth[row][col]=Labyrinth.MONSTER_CHAR;
    }
    
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<Directions> validMoves (int row, int col){
        throw new UnsupportedOperationException();
    }
    
}
