#encoding:utf-8

require_relative 'Weapon'

module Irrgarten
	
	class Irrgarten
		
		def self.prueba_weapon
			w = Weapon.new(2.0, 3)
			puts w.to_s	
		end
		
	end

	Irrgarten.prueba_weapon

end
