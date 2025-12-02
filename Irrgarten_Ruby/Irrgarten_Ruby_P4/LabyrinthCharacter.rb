#encoding:utf-8

module Irrgarten
	class LabyrinthCharacter
		def initialize(name, intelligence, strength, health)
			@name = name
			@intelligence = intelligence
			@strength = strength
			@health = health
			@row = -1
			@col = -1
		end
		
		protected
		
		attr_reader :intelligence, :strength, :health, :name
		
		def got_wounded ()
			@health-=1
		end
		
		def set_health(health)
			@health=health
		end
		
		public
		
		attr_reader :row, :col
		
		def set_pos(row, col) #int,int
			@row = row
			@col = col
		end
		
		def dead()
			if (@health <= 0)
				return true
			else
				return false
			end
		end
		
		def attack()
		end
		
		def defend (received_attack) #float
		end
		
		def copiar_player(otro)
			@intelligence = otro.intelligence
			@strength = otro.strength
			@name = otro.name
			@health = otro.health
			@row = otro.row
			@col = otro.col
		end
		
		def to_s()
			"Name: #{@name}, Intelligence: #{@intelligence}, Strength: #{@strength}, Health: #{@health}, Position: (#{@row},#{@col})"
		end
	end
end
