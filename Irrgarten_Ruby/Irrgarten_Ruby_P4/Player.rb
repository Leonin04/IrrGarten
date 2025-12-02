#encoding:utf-8

require_relative 'Irrgarten'
require_relative 'Weapon'
require_relative 'Shield'
require_relative 'LabyrinthCharacter'


module Irrgarten
	class Player < LabyrinthCharacter
		@@MAX_WEAPONS = 2;
		@@MAX_SHIELDS = 3;
		@@INITIAL_HEALTH = 1;
		@@HITS2LOSE = 3;
		
		def initialize(number, intelligence, strength) #char,float,float
			super("Player ##{@number}",intelligence,strength,@@INITIAL_HEALTH)
			
			@number = number
			@consecutive_hits = 0 
			@weapons = []
			@shields = []
		end
		
		private
		
		def receive_weapon (w) #weapon
			
			@weapons.delete_if do |w| 
				w.discard 
			end
			
			size = @weapons.size
			if (size < @@MAX_WEAPONS)
				@weapons.push(w)
			end
		end
		
		def receive_shield (s) #shield
			 @shields.delete_if do |s| 
        			s.discard
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
		
		def manage_hit(received_attack)
			defense = defensive_energy
			lose = false
			
			if (defense < received_attack)
				got_wounded
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
		
		def inc_consecutive_hits ()
			@consecutive_hits += 1
		end
		
		protected
		
		attr_reader :weapons, :shields, :consecutive_hits
		
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
				suma += shield.protect
			end
			suma
		end
		
		def defensive_energy()
			suma = @intelligence + sum_shields
		end
		
		public
		
		attr_reader :number
		
		def copiar_player(otro)
			super(otro)
			@number = otro.number
			@consecutive_hits = otro.consecutive_hits
			@weapons = otro.weapons
			@shields = otro.shields
		end
		
		def resurrect() 
			if (dead())
				@health = @@INITIAL_HEALTH
				@weapons.clear()
				@shields.clear()
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
			manage_hit(received_attack)
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
			armas = ""
			escudos = ""
			
			@weapons.each do |w|
  				armas += w.to_s + "\n"
			end
			
			@shields.each do |s|
  				escudos += s.to_s + "\n"
			end
			"#{super}, Number: #{@number}, ConsecutiveHits: #{@consecutive_hits} \n Armas: \n #{armas} \n Escudos: \n #{escudos}"
		end
	end
end
