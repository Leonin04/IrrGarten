#encoding:utf-8

module Irrgarten 
	class Weapon
		
		def initialize (power, uses) #float, int
			@power = power
			@uses = uses	
		end
		
		def attack ()
			salida = 0.0
			if @uses > 0 then 
				salida = @power
				@uses -= 1
			end
			salida
		end
		
		def to_s ()
			"W[#{@power},#{@uses}]" 
		end
		
		def discard 
			return Dice.discard_element(@uses)
		end
	end	
end
