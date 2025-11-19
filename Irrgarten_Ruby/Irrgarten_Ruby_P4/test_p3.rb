require_relative 'Game'
require_relative 'controller'
require_relative 'textUI'

module Irrgarten
  	N_PLAYERS = 1

	vista = UI::TextUI.new() 
	juego = Game.new(N_PLAYERS)
	controlador = Control::Controller.new(juego, vista)  

	controlador.play()
end 
