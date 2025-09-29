#encoding:utf-8

require_relative 'Weapon'
require_relative 'Shield'

module Directions
	LEFT =:left
	RIGHT=:right
	UP=:up
	DOWN=:down
end

module Orientation
	VERTICAL=:vertical
	HORIZONTAL=:horizontal
end

module GameCharacter
	PLAYER =:player
	MONSTER =:monster
end

module Irrgarten
	
	class Irrgarten
		
		def self.prueba_weapon
			w = Weapon.new(2.0, 3)
			puts "\n"
			puts w.to_s	
			4.times do
				puts "Potencia de disparo #{w.attack}"
				puts w.to_s
			end
		end
		
		def self.prueba_shield
			s = Shield.new(2.0,3)
			puts "\n"
			puts s.to_s
			4.times do
				puts "Daño protegido #{s.protect}"
				puts s.to_s
			end
		end
		
	end

	#Irrgarten.prueba_weapon

end

Irrgarten::Irrgarten.prueba_weapon
Irrgarten::Irrgarten.prueba_shield
