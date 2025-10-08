#encoding:utf-8

require_relative 'Dice'

module Irrgarten 
	class Monster 
		@@INITIAL_HEALTH = 5
		
		
		def initialize ( name, intelligence, strength) #string,float,float
			@name = name
			@intelligence = intelligence
			@strength = strength
			@health = @@INITIAL_HEALTH
			@col = -1
			@row = -1
		end
		
		def dead () 
			if @health <= 0
				return true
			else
				return false
			end
		
		end
		
		def attack()
			return Dice.intensity(@strength)
		end
		
		def defend( received_attack ) #float
			#se completa mas tarde
			
		end
		
		def set_pos ( row, col) # int, int
			if row >= 0 && col >=0
				@row=row
				@col=col
			else 
				puts "El monstruo no puede estar en una posición negativa"
			end
			
		end
		
		def got_wounded()
			@health = @health - 1
		
		end
		
		def to_s()
			"Name: #{@name}, Intelligence: #{@intelligence}, Strength: #{@strength}, Health: #{@health}, Position: (#{@row}, #{@col})"
		end
			
		
	end
end
