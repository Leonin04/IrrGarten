#encoding:utf-8

require_relative 'Dice'

module Irrgarten 
	class Labyrinth
		@@BLOCK_CHAR ='X'
		@@EMPTY_CHAR = '-'
		@@MONSTER_CHAR = 'M'
		@@COMBAT_CHAR = 'C'
		@@EXIT_CHAR = 'E'
		@@ROW = 0
		@@COL = 1
		
		attr_accessor :labyrinth
		
		private
	
		def pos_ok ( row, col) #int, int
			ok=false
			if (row >=0 && col>=0 && row < @n_rows && col< @n_cols) 
				ok=true
			end
			return ok	
		end
	
		def empty_pos ( row, col) #int, int
			empty=false
			if (pos_ok(row,col)) 
				if( @labyrinth[row][col]==@@EMPTY_CHAR) 
					empty=true
				end
			end
			return empty
		end
	
		def monster_pos (row, col) #int, int
			monster=false
			if (pos_ok(row,col)) 
				if( @labyrinth[row][col]==@@MONSTER_CHAR) 
					monster=true
				end
			end
			return monster
		end
		
		def exit_pos (row, col) #int, int
			exit=false
			if (pos_ok(row,col)) 
				if( @labyrinth[row][col]==@@EXIT_CHAR) 
					exit=true
				end
			end
			return exit
		end
		
		def combat_pos (row, col) #int, int
			combat=false
			if (pos_ok(row,col)) 
				if( @labyrinth[row][col]==@@COMBAT_CHAR) 
					combat=true
				end
			end
			return combat
		end
		
		def can_step_on (row, col) #int, int
			can=false
			if (pos_ok(row,col) && (empty_pos(row,col)||monster_pos(row,col) || exit_pos(row,col))) 				 
				can=true
			end
			return can
		end
		
		def update_old_pos(row, col) #int,int
			if( pos_ok(row,col) )
				if (@labyrinth[row][col]==@@COMBAT_CHAR) 
					@labyrinth[row][col]=@@MONSTER_CHAR
				end
			else 
				@labyrinth[row][col]=@@EMPTY_CHAR
			end
		end
		
		def dir_2_pos(row,col,direction)#int,int, Directions		
			fila=row
			columna=col
			case direction
				when Directions::UP
					fila-=1
				when Directions::DOWN
					fila+=1
				when Directions::RIGHT
					columna+=1
				when Directions::LEFT
					columna-=1
			end
			return [fila,columna]
		end
		
		def random_empty_pos()
			fila=Dice.random_pos(@n_rows)
			columna=Dice.random_pos(@n_cols)
			while (@labyrinth[fila][columna]!=@@EMPTY_CHAR)
				fila=Dice.random_pos(@n_rows)
				columna=Dice.random_pos(@n_cols)
			end
			return [fila,columna]
		end
		
		def put_player_2d (old_row,old_col,row,col,player)#int,int,int,int,Player
			output = nil
			
			if (can_step_on(row,col))
				if (pos_ok(old_row,old_col))
					if (@players[old_row][old_col]==player)
						update_old_pos(old_row,old_col)
						@players[old_row][old_col]=nil
					end
				end
				
				if(monster_pos(row,col))
					@labyrinth[row][col]=@@COMBAT_CHAR
					output=@monsters[row][col]
				else
					number = player.number
					@labyrinth[row][col]=number
				end
				
				@players[row][col]=player
				player.set_pos(row,col)
			end
			output
		end
		
		
		
		public
		
		def initialize( n_rows,n_cols,exit_row,exit_col) #int,int,int,int
			@n_rows=n_rows
			@n_cols=n_cols
			@exit_row=exit_row
			@exit_col=exit_col
			
			@labyrinth = Array.new(n_rows){Array.new(n_cols){@@EMPTY_CHAR}}
			
			@monsters = Array.new(n_rows){Array.new(n_cols){nil}}
			
			@players = Array.new(n_rows){Array.new(n_cols){nil}}
			
			@labyrinth[exit_row][exit_col]=@@EXIT_CHAR
		end
		
		def spread_players(players)
  			players.each do |p|
    				pos = random_empty_pos
    				put_player_2d(-1, -1, pos[@@ROW], pos[@@COL], p)
  			end
		end
		
		def have_a_winner()
			ganador=false
			if( @players[@exit_row][@exit_col] != nil) 
				ganador=true
			end
			return ganador
		end
		
		def to_s()
			salida = "Nº de filas: #{@n_rows}, Nº de columnas: #{@n_cols} \n"
			for fila in @labyrinth
				for i in fila
					salida+= i
				end 
				salida += "\n"
			end
			salida
		end
		
		def add_monster ( row, col, monster)#int,int,monster
			if((pos_ok(row,col)) && (empty_pos(row,col))) 
				@monsters[row][col] = monster
				@labyrinth[row][col]=@@MONSTER_CHAR
			end
		end
		
		def put_player (direction, player) #Direction, Player
			old_row = player.row
			old_col = player.col
			new_pos = dir_2_pos(old_row,old_col,direction)
			monster = put_player_2d(old_row,old_col,new_pos[@@ROW],new_pos[@@COL],player)
			monster
		end
		
		def add_block (orientation, start_row, start_col, length) #Orientation, int ,int ,int
			inc_row = 0
			inc_col = 0
			if (orientation == Orientation::VERTICAL)
				inc_row = 1
			else
				inc_col = 1
			end
			
			col = start_col
			row = start_row
			
			while (pos_ok(row,col) && empty_pos(row,col) && length > 0)
				@labyrinth[row][col]=@@BLOCK_CHAR
				length -= 1
				row += inc_row
				col += inc_col
			end
		end
		
		def valid_moves (row,col) #int,int
			output = Array.new
			
			if (can_step_on(row+1,col))
				output.push(Directions::DOWN)
			end
			
			if (can_step_on(row-1,col))
				output.push(Directions::UP)
			end
			
			if (can_step_on(row,col+1))
				output.push(Directions::RIGHT)
			end
			
			if (can_step_on(row,col-1))
				output.push(Directions::LEFT)
			end
			
			output
		end
	end
end
