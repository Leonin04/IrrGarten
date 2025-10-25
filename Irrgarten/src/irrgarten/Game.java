package irrgarten;

import java.util.ArrayList;

public class Game {
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    private Playerl currentPlayer;

    public Game(int nplayers) {
        this.players = new ArrayList<>();
        for (int i = 0; i < nplayers; i++) {
            this.players.add(new Player(i,Dice.randomIntelligence(), Dice.randomStrength()));
        }
        this.monsters = new ArrayList<>();
        this.labyrinth = new Labyrinth();
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.log = "Game started with " + nplayers + " players.\n";
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
        this.configureLabyrinth();
        this.labyrinth.spreadPlayers(this.players);
    }

    private boolean finished() {
        return this.labyrinth.haveAWinner();
    }

    private boolean nextStep(Directions PreferredDirection) {
        throw new UnsupportedOperationException();
    }

    private GameState getGameState() {
        GameState state = new GameState(this.labyrinth.toString(), this.players.toString(), this.monsters.toString(), this.currentPlayerIndex, this.finished(), this.log);
        return state;
    }

    void configureLabyrinth() {
        //help 
    }

    void nextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        this.currentPlayer = this.players.get(this.currentPlayerIndex);
    }

    void logPlayerWon() {
        this.log += "Player " + this.currentPlayer.getName() + " has won the game!\n";
    }

    void logMonsterWon() {
        this.log += "Monster has won against player " + this.currentPlayer.getName() + "!\n";
    }   

    void logResurrected() {
        this.log += "Player " + this.currentPlayer.getName() + " has resurrected!\n";
    }

    void logPlayerSkipTurn(){
        this.log += "Player " + this.currentPlayer.getName() + " skips this turn.\n";
    }

    void logPlayerNoOrders(){
        this.log += "Player " + this.currentPlayer.getName() + " has no orders to play.\n";
    }

    void logNoMonster(){
        this.log += "No monster present to attack player " + this.currentPlayer.getName() + ".\n";
    }

    void logRounds(int rounds,int max) {
        this.log += "Round " + rounds + " of " + max + " completed.\n";
    }

    Directions actualDirection(Directions preferredDirection) {
       //P3
    }

    GameCharacter combat(Monster monster)  {
       //P3
    }
    void manageReward(GameCharacter winner)     {
       //P3
    }
    void manageResurrection()       {
       //P3
    }
}