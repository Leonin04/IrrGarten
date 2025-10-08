#encoding:utf-8

require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'
require_relative 'irrgarten'

module Irrgarten 
	class TestP1
	
		def self.prueba_enum
			puts "Probando dirección: #{Directions::LEFT}"
			puts "Probando dirección: #{Directions::RIGHT}"
			puts "Probando dirección: #{Directions::UP}"
			puts "Probando dirección: #{Directions::DOWN}"
			
			puts "Probando orientación: #{Orientation::VERTICAL}"
			puts "Probando orientación: #{Orientation::HORIZONTAL}"
			
			puts "Probando personajes: #{GameCharacter::PLAYER}"
			puts "Probando personajes: #{GameCharacter::MONSTER}"
		end
		
		def self.prueba_weapon
			w = Weapon.new(2.0, 3)
			puts "\n"
			puts w.to_s	
			4.times do
				puts "Potencia de disparo #{w.attack}"
				puts w.to_s
				puts "¿Se descarta el arma? #{w.discard}"
			end
		end
		
		def self.prueba_shield
			s = Shield.new(2.0,3)
			puts "\n"
			puts s.to_s
			4.times do
				puts "Daño protegido #{s.protect}"
				puts s.to_s
				puts "¿Se descarta el escudo? #{s.discard}"
			end
		end
		
		def self.prueba_dice
			for i in 1..100
				puts "\nPrueba #{i}"
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
		
		def self.prueba_game_state
			g = GameState.new("laberintoooo", "jugadoreees", "monstruitoss", 1, false, "logeate")
			puts "\n"
			puts g.labyrinth
			puts g.players
			puts g.monsters
			puts g.current_player
			puts g.winner
			puts g.log
		end
	end
end

Irrgarten::TestP1.prueba_enum
Irrgarten::TestP1.prueba_weapon
Irrgarten::TestP1.prueba_shield
Irrgarten::TestP1.prueba_dice
Irrgarten::TestP1.prueba_game_state
