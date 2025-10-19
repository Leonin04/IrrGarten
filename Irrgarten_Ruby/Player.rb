#encoding:utf-8

module Irrgarten
	class Player
		@@MAX_WEAPONS = 2;
		@@MAX_SHIELDS = 3;
		@@INITIAL_HEALTH = 10;
		@@HITS2LOSE = 3;
		
		def initialize(number, intelligence, strength) #char,float,float
			@number = number
			@intelligence = intelligence
			@strength = strength
			@name = "Player ##{@number}"
			@row = -1
			@col = -1
			@health = @@INITIAL_HEALTH
			@weapons = []
			@shields = []
		end
		
		attr_reader :row, :col, :number
		
		def resurrect() 
			if (dead())
				@health = @@INITIAL_HEALTH
				@weapons.clear()
				@shields.clear()
			end
		end
		
		def set_pos(row, col) #int,int
			@row = row
			@col = col
		end
		
		def dead()
			if (@health <= 0)
				return true
			else
				return false
			end
		end
			
	end
end
