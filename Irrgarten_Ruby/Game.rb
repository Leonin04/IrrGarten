#encoding:utf-8

require_relative 'Irrgarten'
require_relative 'Player'
require_relative 'Dice'
require_relative 'Labyrinth'
require_relative 'Monster'
require_relative 'GameState'

module Irrgarten 
    class Game
    
            
        @@MAX_ROUNDS = 10

       
        private

        def configure_labyrinth
        	rows = 10
        	cols = 10
        	n_monsters = 3
        	exit_row = 2
        	exit_col = 9
        	
        	@labyrinth = Labyrinth.new(rows,cols,exit_row,exit_col)
        	@labyrinth.add_block(Orientation::VERTICAL,0,1,4);
        	@labyrinth.add_block(Orientation::HORIZONTAL, 3,2, 3);
        	@labyrinth.add_block(Orientation::HORIZONTAL, 0, 4, 6);
        	@labyrinth.add_block(Orientation::HORIZONTAL, 1, 4, 6);
        	@labyrinth.add_block(Orientation::HORIZONTAL, 6, 0, 5);
        	@labyrinth.add_block(Orientation::VERTICAL,7,4,2);
        	@labyrinth.add_block(Orientation::VERTICAL, 3, 6, 6);
        	@labyrinth.add_block(Orientation::VERTICAL,2,8,7);
        	
        	for i in 0...n_monsters
        		@monsters.push(Monster.new("monstruo #{i}",Dice.random_intelligence,Dice.random_strength))
        	end
        	
        	@labyrinth.add_monster(8,1,@monsters[0])
        	@labyrinth.add_monster(9,7,@monsters[1])
        	@labyrinth.add_monster(2,5,@monsters[2])
        end

        def next_player
		@current_player_index = (@current_player_index + 1) % @players.size
		@current_player = @players[@current_player_index]
        end

        def log_player_won
        	@log += "Player #{@current_player_index} has won the battle!\n"
        end

        def log_monster_won
        	@log += "Monster has won against player #{@current_player_index}!\n"
        end

        def log_resurrected
        	@log += "Player #{@current_player_index} has resurrected!\n"
        end

        def log_player_skip_turn
        	@log += "Player #{@current_player_index} skips this turn.\n"
        end

        def log_player_no_orders
        	@log += "Player #{@current_player_index} has no orders to play.\n"
        end

        def log_no_monster
        	@log += "No monster present to attack player #{@current_player_index}.\n"
        end

        def log_rounds(rounds, max)
        	@log += "Round #{rounds} of #{max} completed.\n"
        end

        def actual_direction(preferred_direction)
        	current_row=@current_player.row
        	current_col=@current_player.col
        	valid_moves=@labyrinth.valid_moves(current_row,current_col)
        	output=@current_player.move(preferred_direction,valid_moves)
        	output
        end

        def combat(monster)
        	rounds=0
        	winner=GameCharacter::PLAYER
        	player_attack=@current_player.attack()
        	lose=@monster.defend(player_attack)
        	while((!lose)&&(@rounds<@@MAX_ROUNDS)) 
        		winner=GameCharacter::MONSTER
        		rounds+=1
        		monster_attack=@monster.attack()
        		lose=@player.defend(monster_attack)
        		if !lose then
        			player_attack=@player.attack()
        			winner=GameCharacter::PLAYER
        			lose=@monster.defend(player_attack)
        		end
        	end
        	log_rounds(rounds,@@MAX_ROUNDS)
        	winner
        end

        def manage_reward(winner)
        	if (winner == GameCharacter::PLAYER)
        		@current_player.receive_reward
        		log_player_won
        	else
        		log_monster_won
        	end
        end

        def manage_resurrection
        	resurrect = Dice.resurrect_player
        	if (resurrect)
        		@current_player.resurrect
        		log_resurrected
        	else
        		log_player_skip_turn
        	end
        end
        
        public
        
        def initialize(nplayers)
            @players = Array.new
                nplayers.times do |i|
                    @players.push(Player.new(i.to_s, Dice.random_intelligence, Dice.random_strength))
                end
            
            @monsters = Array.new
            @current_player_index = Dice.who_starts(nplayers)
            @log = "Game started with #{nplayers} players.\n Labyrinth: #{@labyrinth.to_s}"
            @current_player = @players[@current_player_index]
            configure_labyrinth()
            @labyrinth.spread_players(@players)
        end

        def finished
            @labyrinth.have_a_winner
        end

        def next_step(preferred_direction)
            log = ""
            winner = nil
            dead = @current_player.dead
            
            if (!dead)
            	direction = actual_direction(preferred_direction)
            	if (direction != preferred_direction)
            		log_player_no_orders
            	end
            	monster = @labyrinth.put_player(direction,@current_player)
            	
            	if (monster==nil)
            		log_no_monster
            	else
            		winner=combat(monster)
            		manage_reward(winner)
            	end
            else
            	manage_resurrection
            end
            
            end_game = finished
            
            if (!end_game)
            	next_player
            end
            
            end_game
        end

        def get_game_state
        	GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @current_player_index, self.finished, @log)
        end

    end
end
