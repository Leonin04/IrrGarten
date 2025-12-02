#encoding:utf-8

module Irrgarten 
	class CombatElement
		
		def initialize(effect,uses) #float,int
			@effect=effect
			@uses=uses
		end
		
		
		
		def discard()
			return Dice.discard_element(@uses)
		end
		
		def to_s()
			"[#{@effect},#{@uses}]"
		end
		
		protected
		
		def produce_effect()
			salida = 0.0
			if @uses > 0 then 
				salida = @effect
				@uses -= 1
			end
			salida
		end
		
		def to_s()
			salida = "[#{@effect},#{@uses}]"
			salida
		end
	end
end
