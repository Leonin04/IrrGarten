#encoding:utf-8

require_relative 'Monster'

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
	end
end

Irrgarten::TestP2.prueba_monster
