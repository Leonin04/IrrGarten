
require 'io/console'
require_relative 'Irrgarten'

module UI

  class TextUI

    #https://gist.github.com/acook/4190379
    def read_char
      STDIN.echo = false
      STDIN.raw!
    
      input = STDIN.getc.chr
      if input == "\e" 
        input << STDIN.read_nonblock(3) rescue nil
        input << STDIN.read_nonblock(2) rescue nil
      end
    ensure
      STDIN.echo = true
      STDIN.cooked!
DISEÑO Y DESARROLLO DE SISTEMAS DE INFORMACIÓN - 2526 (COMÚN)
Nombre del curso DISEÑO Y DESARROLLO DE SISTEMAS DE INFORMACIÓN - 2526 (COMÚN)
Categoría del curso (296) GRADUADO-A EN INGENIERÍA INFORMÁTICA (2010) (296)
ECONOMETRÍA - 2526 (COMÚN)
Nombre del curso ECONOMETRÍA - 2526 (COMÚN)
Categoría del curso (216) GRADUADO-A EN ING.ª INFORMÁTICA-ADMINIST. Y DIRECC. EMPRESAS (2017) (216)

    
      return input
    end

    def next_move
      print "Where? "
      got_input = false
      while (!got_input)
        c = read_char
        case c
          when "\e[A"
            puts "UP ARROW"
            output = Irrgarten::Directions::UP
            got_input = true
          when "\e[B"
            puts "DOWN ARROW"
            output = Irrgarten::Directions::DOWN
            got_input = true
          when "\e[C"
            puts "RIGHT ARROW"
            output = Irrgarten::Directions::RIGHT
            got_input = true
          when "\e[D"
            puts "LEFT ARROW"
            output = Irrgarten::Directions::LEFT
            got_input = true
          when "\u0003"
            puts "CONTROL-C"
            got_input = true
            exit(1)
          else
            puts "Please choose a valid character: w,a,s,d \n" 
        end
      end
      output
    end

    def show_game(game_state)

    end

  end # class   

end # module   


