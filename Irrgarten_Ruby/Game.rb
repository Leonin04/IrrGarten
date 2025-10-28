#encoding:utf-8

module Irrgarten 
    class Game
    
            
        @@MAX_ROUNDS = 10

       
        private

        def configure_labyrinth
        # P3
        end

        def next_player
		@currentPlayerIndex = (@currentPlayerIndex + 1) % @players.size
		@currentPlayer = @players[@currentPlayerIndex]
        end

        def log_player_won
        	@log += "Player #{@currentPlayerIndex} has won the game!\n"
        end

        def log_monster_won
        	@log += "Monster has won against player #{@currentPlayerIndex}!\n"
        end

        def log_resurrected
        @log += "Player #{@currentPlayerIndex} has resurrected!\n"
        end

        def log_player_skip_turn
        @log += "Player #{@currentPlayerIndex} skips this turn.\n"
        end

        def log_player_no_orders
        @log += "Player #{@currentPlayerIndex} has no orders to play.\n"
        end

        def log_no_monster
        @log += "No monster present to attack player #{@currentPlayerIndex}.\n"
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
        
        public
        
        def initialize(nplayers)
            @players = Array.new
                nplayers.times do |i|
                    @players.push(Player.new(i.to_s, Dice.random_intelligence, Dice.random_strength))
                end
            
            @monsters = Array.new
            @labyrinth = Labyrinth.new(10,10,4,5) #POR AHORA
            @currentPlayerIndex = Dice.who_starts(nplayers)
            @log = "Game started with #{nplayers} players.\n Labyrinth: #{@labyrinth.to_s}"
            @currentPlayer = @players[@currentPlayerIndex]
            #configure_labyrinth()
            #@labyrinth.spread_players(@players)
        end

        def finished
            @labyrinth.have_a_winner
        end

        def next_step(preferred_direction)
            # P3
        end

        def get_game_state
        	GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @currentPlayerIndex, self.finished, @log)
        end

    end
end
