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
		@@MAX_ATTACK = 3 #Potencia máxima de armas
		@@MAX_SHIELD = 2	#Protección máxima de escudos
			
		@@generator = Random.new
		
		def random_pos ( max ) #int
			@generator.rand(0...max) # ... excluido .. incluido
		 
		end
		
		def who_starts ( nplayers ) #int
			@generator.rand(0...nplayers)
		
		end
		
		def random_intelligence()
			@generator.rand(0...@MAX_INTELLIGENCE)
		end
		
		def random_strength()
			@generator.rand(0...@MAX_STRENGHT)
		end
		
		def resurrect_player()
			if (@generador.rand < 0.3)
				return true
			else
				return false
		end
		
	end	
end
