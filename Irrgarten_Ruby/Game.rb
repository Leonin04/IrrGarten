#encoding:utf-8

module Irrgarten 
    class Game
    
            
        @@MAX_ROUNDS = 10

       
        def initialize(nplayers)
            @players = Array.new
                nplayers.times do |i|
                    @players.push(Player.new(i.to_s, Dice.random_intelligence, Dice.random_strength))
                end
            
            @monsters = Array.new
            @labyrinth = Labyrinth.new
            @currentPlayerIndex = Dice.who_starts(nplayers)
            @log = "Game started with #{nplayers} players.\n"
            @currentPlayer = @players[@currentPlayerIndex]
            configure_labyrinth()
            @labyrinth.spread_players(@players)
        end

        private
        def finished
            @labyrinth.have_a_winner
        end

        def next_step(preferred_direction)
            # P3
        end

        def get_game_state
        GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, 
                        @currentPlayerIndex, self.finished, @log)
        end

        def configure_labyrinth
        # help
        end

        def next_player
        @currentPlayerIndex = (@currentPlayerIndex + 1) % @players.size
        @currentPlayer = @players[@currentPlayerIndex]
        end

        def log_player_won
        @log += "Player #{@currentPlayer.name} has won the game!\n"
        end

        def log_monster_won
        @log += "Monster has won against player #{@currentPlayer.name}!\n"
        end

        def log_resurrected
        @log += "Player #{@currentPlayer.name} has resurrected!\n"
        end

        def log_player_skip_turn
        @log += "Player #{@currentPlayer.name} skips this turn.\n"
        end

        def log_player_no_orders
        @log += "Player #{@currentPlayer.name} has no orders to play.\n"
        end

        def log_no_monster
        @log += "No monster present to attack player #{@currentPlayer.name}.\n"
        end

        def log_rounds(rounds, max)
        @log += "Round #{rounds} of #{max} completed.\n"
        end

        def actual_direction(preferred_direction)
        # P3
        end

        def combat(monster)
        # P3
        end

        def manage_reward(winner)
        # P3
        end

        def manage_resurrection
        # P3
        end

    end
end
