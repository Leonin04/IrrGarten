#encoding:utf-8

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
	end	
end
