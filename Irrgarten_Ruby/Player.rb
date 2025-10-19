#encoding:utf-8

require_relative 'Irrgarten'

module Irrgarten
	class Player
		@@MAX_WEAPONS = 2;
		@@MAX_SHIELDS = 3;
		@@INITIAL_HEALTH = 10;
		@@HITS2LOSE = 3;
		
		def initialize(number, intelligence, strength) #char,float,float
			@number = number
			@intelligence = intelligence
			@strength = strength
			@name = "Player ##{@number}"
			@row = -1
			@col = -1
			@health = @@INITIAL_HEALTH
			@consecutive_hits = 0 #????
			@weapons = []
			@shields = []
		end
		
		attr_reader :row, :col, :number
		
		private
		
		def receive_weapon (w) #weapon
			#Practica 3
		end
		
		def receive_shield (s) #shield
			#Practica 3
		end
		
		def new_weapon ()
			w =  Weapon.new(Dice.weapon_power,Dice.uses_left)
		end
		
		def new_shield ()
			s = Shield.new(Dice.shield_power, Dice.uses_left)
		end
		
		def sum_weapons ()
			suma = 0.0
			for weapon in @weapons
				suma += weapon.attack
			end
			suma	
		end
		
		def sum_shields ()
			suma = 0.0
			for shield in @shields
				suma += shield.attack
			end
			suma
		end
		
		def defensive_energy()
			suma = @intelligence + sum_shields
		end
		
		def manage_hit()
			#Practica 3
		end
		
		def reset_hits ()
			@consecutive_hits = 0
		end
		
		def get_wounded ()
			@health-=1
		end
		
		def inc_consecutive_hits ()
			@consecutive_hits += 1
		end
		
		public
		
		def resurrect() 
			if (dead())
				@health = @@INITIAL_HEALTH
				@weapons.clear()
				@shields.clear()
			end
		end
		
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
		
		def move (direction, valid_moves) #Directions, Directions[]
			#Practica 3
		end	
		
		def attack()
			a = @strength + sum_weapons()
		end
		
		def defend (received_attack) #float
			#Practica 3
		end
		
		def receive_reward()
			#Practica 3
		end
		
		def to_s()
			"Name: #{@name}, Number: #{@number}, Intelligence: #{@intelligence}, Strength: #{@strength}, Health: #{@health}, Position: (#{@row},#{@col}), ConsecutiveHits: #{@consecutive_hits} \n Armas: \n #{@weapons} \n Escudos: \n #{@shields}"
		end
	end
end
