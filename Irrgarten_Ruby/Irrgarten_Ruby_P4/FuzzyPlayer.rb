#encoding:utf-8

require_relative 'Irrgarten'
require_relative 'Dice'
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Player'


module Irrgarten
	class FuzzyPlayer < Player
		def initialize()
			
		end
		
		public
		
		def copiar_player(otro)
			super(otro)
		end
		
		def move(direction, valid_moves)
			preference = super(direction,valid_moves)
			Dice.next_step(preference,valid_moves,@intelligence)
		end
		
		def attack()
			damage = Dice.intensity(@strength) + sum_weapons()
			return damage
		end
		
		def to_s()
			salida = super
			return "Fuzzy #{salida}"
		end
		
		protected
		
		def defensive_energy()
			defense = Dice.intensity(@intelligence) + sum_shields()
			return defense
		end
	end
end
