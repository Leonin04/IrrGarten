#encoding:utf-8

require_relative 'CombatElement'

module Irrgarten 
	class Weapon < CombatElement
		
		def attack ()
			produce_effect()
		end
		
		def to_s ()
			"W#{super}"
		end
	end	
end
