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
			for i in 0...@weapons.size
				discard = @weapons[i].discard
				
				if (discard)
					@weapons.delete_at(i)
				end
			end
			
			size = @weapons.size
			if (size < @@MAX_WEAPONS)
				@weapons.push(w)
			end
		end
		
		def receive_shield (s) #shield
			for i in 0...@shields.size
				discard = @shields[i].discard
				
				if (discard)
					@shields.delete_at(i)
				end
			end
			
			size = @shields.size
			if (size < @@MAX_SHIELDS)
				@shields.push(s)
			end
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
		
		def manage_hit(received_attack)
			defense = defensive_energy
			lose = false
			
			if (defense < received_attack)
				get_wounded
				inc_consecutive_hits
			else
				reset_hits
			end
			
			if (@consecutive_hits == @@HITS2LOSE || dead)
				reset_hits
				lose=true
			else
				lose=false
			end
			
			lose
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
			dir = direction
			
			size = valid_moves.size
			
			contained = valid_moves.include?(direction)
			
			if (!contained && size>0)
				dir = valid_moves[0]
			end
			
			dir
		end	
		
		def attack()
			a = @strength + sum_weapons()
		end
		
		def defend (received_attack) #float
			#Practica 3
		end
		
		def receive_reward()
			w_reward = Dice.weapons_reward
			s_reward = Dice.shields_reward
			
			for i in 0...w_reward
				wnew = new_weapon
				receive_weapon(wnew)
			end
			
			for i in 0...s_reward
				snew = new_shield
				receive_shield(snew)
			end
			
			extra_health = Dice.health_reward
			@health += extra_health
		end
		
		def to_s()
			"Name: #{@name}, Number: #{@number}, Intelligence: #{@intelligence}, Strength: #{@strength}, Health: #{@health}, Position: (#{@row},#{@col}), ConsecutiveHits: #{@consecutive_hits} \n Armas: \n #{@weapons} \n Escudos: \n #{@shields}"
		end
	end
end
