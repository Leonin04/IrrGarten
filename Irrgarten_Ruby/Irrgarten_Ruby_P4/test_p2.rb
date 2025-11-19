#encoding:utf-8

require_relative 'Monster'
require_relative 'Player'
require_relative 'Labyrinth'
require_relative 'Game'
require_relative 'GameState'

module Irrgarten
	class TestP2
		def self.prueba_monster()
			m = Monster.new("Monstruito",10,10)
			puts m.to_s
			puts "¿Muerto? #{m.dead}"
			puts "Daño: #{m.attack}"
			puts "Colocando monstruo en el (1,1)..."
			m.set_pos(1,1)
			puts m.to_s
			puts "Matando monstruo..."
			m.got_wounded
			puts m.to_s
			m.got_wounded
			m.got_wounded
			m.got_wounded
			m.got_wounded
			puts m.to_s
			puts "¿Muerto? #{m.dead}"
		end
		
		def self.prueba_player()
			p = Player.new('1', 10,10)
			puts p.to_s
			puts "Colocando en 0,0:"
			p.set_pos(0,0)
			puts "Colocado en: (#{p.row},#{p.col})"
			puts "¿Muerto? #{p.dead}"
			puts "Atacando: #{p.attack}"
			#puts "Defendiendo #{p.defend}"
		end
		
		def self.prueba_labyrinth()
			l = Labyrinth.new(10,10,1,1)
			puts "¿Hay ganador? #{l.have_a_winner}"
			puts "Pintando laberinto:"
			puts l.to_s
			m = Monster.new("Ares",10,10)
			puts "Poniendo monstruo en (2,2)"
			l.add_monster(2,2,m)
			puts l.to_s
		end
		
		def self.prueba_game()
			g = Game.new(3)
			puts "¿Hay ganador? #{g.finished}"
			gs = g.get_game_state
			puts "#{gs.labyrinth}"
		end
	end
end

puts "Probando Monster"
Irrgarten::TestP2.prueba_monster
puts "\n Probando Player"
Irrgarten::TestP2.prueba_player
puts "\n Probando Labyrinth"
Irrgarten::TestP2.prueba_labyrinth
puts "\n Probando Game"
Irrgarten::TestP2.prueba_game
