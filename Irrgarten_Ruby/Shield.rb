#encoding:utf-8

require_relative 'Dice'

module Irrgarten 
	class Shield
		
		def initialize (protection, uses) #float, int
			@protection = protection
			@uses = uses	
		end
		
		def protect ()
			salida = 0.0
			if @uses > 0 then 
				salida = @protection
				@uses -= 1
			end
			salida
		end
		
		def to_s ()
			"S[#{@protection},#{@uses}]" 
		end
		
		def discard 
			return Dice.discard_element(@uses)
		end
	end	
end
