#encoding:utf-8

require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Dice'

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
		
		def self.prueba_dice
			puts "\n"
			puts "Posición: #{Dice.random_pos(10)}"
			puts "Empieza jugador: #{Dice.who_starts(3)}"
			puts "Inteligencia: #{Dice.random_intelligence}"
			puts "Fuerza: #{Dice.random_strength}"
			puts "¿Resucita? #{Dice.resurrect_player}"
			puts "Armas conseguidas: #{Dice.weapons_reward}"
			puts "Escudos conseguidos: #{Dice.shields_reward}"
			puts "Salud conseguida: #{Dice.health_reward}"
			puts "Poder arma: #{Dice.weapon_power}"
			puts "Poder escudo: #{Dice.shield_power}"
			usos = Dice.uses_left
			puts "Usos restantes: #{usos}"
			puts "Intensidad: #{Dice.intensity(3.0)}"
			puts "¿Se descarta? #{Dice.discard_element(usos)}"
		end
		
	end

	#Irrgarten.prueba_weapon

end

Irrgarten::Irrgarten.prueba_weapon
Irrgarten::Irrgarten.prueba_shield
Irrgarten::Irrgarten.prueba_dice
