#encoding:utf-8

require_relative 'Dice'
require_relative 'LabyrinthCharacter'

module Irrgarten 
	class Monster < LabyrinthCharacter
		@@INITIAL_HEALTH = 5
		
		
		def initialize ( name, intelligence, strength) #string,float,float
			super(name,intelligence,strength,@@INITIAL_HEALTH)
		end
		
		def attack()
			return Dice.intensity(@strength)
		end
		
		def defend( received_attack ) #float
			is_dead = dead
			
			if (!is_dead)
				defensive_energy = Dice.intensity(@intelligence)
				if (defensive_energy < received_attack)
					got_wounded
					is_dead = dead
				end
			end
			
			is_dead
		end	
	end
end
