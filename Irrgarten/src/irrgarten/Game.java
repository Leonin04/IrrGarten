package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    private Player currentPlayer;

    private void configureLabyrinth() {
        throw new UnsupportedOperationException(); //PREGUNTAR SI PODEMOS HACERLO
    }

    private void nextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
    }

    private void logPlayerWon() {
        this.log += " Player " + this.currentPlayerIndex + " has won the game!\n";
    }

    private void logMonsterWon() {
        this.log += "Monster has won against player " + this.currentPlayerIndex + "!\n";
    }   

    private void logResurrected() {
        this.log += "Player " + this.currentPlayerIndex + " has resurrected!\n";
    }

    private void logPlayerSkipTurn(){
        this.log += "Player " + this.currentPlayerIndex + " skips this turn.\n";
    }

    private void logPlayerNoOrders(){
        this.log += "Player " + this.currentPlayerIndex + " has no orders to play.\n";
    }

    private void logNoMonster(){
        this.log += "No monster present to attack player " + this.currentPlayerIndex + ".\n";
    }

    private void logRounds(int rounds,int max) {
        this.log += "Round " + rounds + " of " + max + " completed.\n";
    }

    private Directions actualDirection(Directions preferredDirection) {
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        return currentPlayer.move(preferredDirection, validMoves);
    }

    private GameCharacter combat(Monster monster)  {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while (!lose && rounds < MAX_ROUNDS){
            rounds++;
            winner = GameCharacter.MONSTER;
            
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds,MAX_ROUNDS);
        
        return winner;
    }
    private void manageReward(GameCharacter winner)     {
        if (winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        } else{
            logMonsterWon();
        }
    }
    private void manageResurrection()       {
        boolean resurrect = Dice.resurrectPlayer();
        
        if (resurrect){
            currentPlayer.resurrect();
            logResurrected();
        } else {
            logPlayerSkipTurn();
        }
    }
    
    public Game(int nplayers) {
        this.players = new ArrayList<>();
        for (int i = 0; i < nplayers; i++) {
            this.players.add(new Player((char) (i + '0'),Dice.randomIntelligence(), Dice.randomStrength()));
        }
        this.monsters = new ArrayList<>();
        this.labyrinth = new Labyrinth(10,10, 4,5); //POR EJEMPLO
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        //this.labyrinth.spreadPlayers(this.players);
        //this.configureLabyrinth();
        this.log = "Game started with " + nplayers + " players.\n Labyrinth: " + this.labyrinth.toString() + "\n";

    }

    public boolean finished() {
        return labyrinth.haveAWinner();
    }

    public boolean nextStep(Directions preferredDirection) {
        log = "";
        boolean dead = currentPlayer.dead();
        GameCharacter winner = null;
        Directions direction = null;
        boolean endGame = finished();
        
        if (!dead){
            direction = actualDirection(preferredDirection);
            
            if (direction != preferredDirection){
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
        
            if (monster == null){
                logNoMonster();
            } else {
                winner = combat(monster);
                manageReward(winner);
            }
        } else {
            manageResurrection();
        }
        
        endGame = finished();
        
        if (!endGame){
            nextPlayer();
        }
        
        return endGame;
    }

    public GameState getGameState() {
        GameState state = new GameState(this.labyrinth.toString(), this.players.toString(), this.monsters.toString(), this.currentPlayerIndex, this.finished(), this.log);
        return state;
    }
}