#encoding:utf-8

module Irrgarten 
	class Dice
	
		@@MAX_USES = 5 #Usos máximos de armas y escudos
		@@MAX_INTELLIGENCE = 10.0 #Valor máximo de inteligencia para monstruso y jugadores
		@@MAX_STRENGTH = 10.0 #Probabilidad de que un jugador sea resucitado en cada turno
		@@RESURRECT_PROB = 0.3 #Número máximo de armas recibidas al ganar un combate
		@@WEAPONS_REWARD = 2 #Número máximo de escudos recibidos al ganar un combate
		@@SHIELDS_REWARD = 3 #Número máximo de escudos recibidos al ganar un combate
		@@HEALTH_REWARD = 5 #Saludo máxima recibida al ganar un combate
		@@MAX_ATTACK = 3.0 #Potencia máxima de armas
		@@MAX_SHIELD = 2.0	#Protección máxima de escudos
			
		@@generator = Random.new
		
		def self.random_pos ( max ) #int
			@@generator.rand(0...max) # ... excluido .. incluido
		 
		end
		
		
		def who_starts ( nplayers ) #int
			@@generator.rand(0...nplayers)
		
		end
		
		
		def self.random_intelligence()
			@@generator.rand(0...@@MAX_INTELLIGENCE)
		end
		
		
		def self.random_strength()
			@@generator.rand(0...@@MAX_STRENGHT)
		end
		
		
		def self.resurrect_player()
			if (@@generador.rand < 0.3)
				return true
			else
				return false
		end
		
		
		def self.weapons_reward()
			@@generator.rand(0..@@WEAPONS_REWARD)
		end
		
		
		def self.shields_reward()
			@@generator.rand(0..@@SHIELDS_REWARD)
		end
		
		
		def self.health_reward()
			@@generator.rand(0..@@HEALTH_REWARD)
		end
		
		
		def self.weapon_power()
			@@generator.rand(0...@@MAX_ATTACK)
		end
		
		
		def self.shield_power()
			@@generator.rand(0...@@MAX_SHIELD)
		end
		
		
		def self.uses_left()
			@@generator.rand(0..@@MAX_USES)
		end
		
		
		def selfintensity( competence )
			@@generator.rand(0...competence)
		end
		
		
		def self.discard_element( uses_left )
			descartado = false
			probabilidad = 1.0 -  (uses_left.to_f/@@MAX_USES.to_f)
			if @@generator.rand(0.0..1.0) < probabilidad
			    descartado = true
			end
			
			return descartado
		end
	end	
end
