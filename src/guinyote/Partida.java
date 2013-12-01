/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guinyote;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author ffelez
 */
class Partida {

    LinkedList<Carta> mazo = new LinkedList<Carta>();
    Carta cartaArriba = null;
    Jugador jugador1 = null;
    Jugador jugador2 = null;
    private int lasUltimas = 0;
        
    
    public Partida ()
    {
        LinkedList<Carta> baraja = new LinkedList<Carta>();
        for (int i= 0; i < 12; i++)
        {
            if ((i+1 != 8) && (i+1 != 9))
            {
                for (int j=0; j<4; j++)
                {
                    Carta carta = new Carta();
                    carta.numero = i+1;
                    carta.palo=j+1;
                    baraja.add (carta);
                }
            }
        }
        
        Random random = new Random();
        while (!baraja.isEmpty())
        {
            int nextElement = random.nextInt(baraja.size());
            Carta carta = baraja.remove(nextElement);
            mazo.add(carta);
        }
        
        System.out.println ("Barajadas: ");
        for (Carta carta : mazo)
        {
            System.out.println (carta);
        }
    }

    void empieza() {
        cartaArriba = mazo.peekLast();
        
        jugador1 = new Jugador("Juan");
        jugador2 = new Jugador("Antonio");
        
        reparte (jugador1, 3);
        reparte (jugador2, 3);
        reparte (jugador1, 3);
        reparte (jugador2, 3);
        
        while (!terminado())
        {
            muestraEstado();
            nuevoMovimiento();
            
            if (lasUltimas == 0)
            {
                reparte (jugador1, 1);
                reparte (jugador2, 1);
            }
            else
            {
                reparte (jugador2, 1);
                reparte (jugador1, 1);
            }
        }
        
        cuentaPuntos();
    }

    void reparte(Jugador jugador, int num) 
    {
        for (int i=0; i < num; i++)
        {
            if (!mazo.isEmpty())
            {
                jugador.anyadeCarta (mazo.pollFirst());
            }
        }
    }

    void muestraEstado() 
    {
        System.out.println();
        System.out.println();
        System.out.println ("Ganadas jugador " + jugador1.nombre + ": " + jugador1.muestraCartasGanadas());
        System.out.println ("Ganadas jugador " + jugador2.nombre + ": " + jugador2.muestraCartasGanadas());
        System.out.println();
        System.out.println();
        System.out.println ("Carta mesa: " + cartaArriba); 
        
        if (lasUltimas == 0)
        {
            System.out.println ("Mano jugador " + jugador1.nombre + ": " + jugador1.muestraCartas());
            System.out.println ("Mano jugador " + jugador2.nombre + ": " + jugador2.muestraCartas());
        }
        else
        {
            System.out.println ("Mano jugador " + jugador2.nombre + ": " + jugador2.muestraCartas());
            System.out.println ("Mano jugador " + jugador1.nombre + ": " + jugador1.muestraCartas());
        }
    }

    void nuevoMovimiento() 
    {
        Carta carta1 = jugador1.pideCarta();
        Carta carta2 = jugador2.pideCarta();
        
        Jugador jugadorGanador = jugador1;
        int ganador = ganaJugada (carta1, carta2);
        if (ganador == 1)
        {
            jugadorGanador = jugador2;
        }
        jugadorGanador.cartasGanadas.add(carta1);
        jugadorGanador.cartasGanadas.add(carta2);
        lasUltimas = ganador;
    }

    private int ganaJugada(Carta carta1, Carta carta2) 
    {
        int result = 0;
        if (carta1.palo == carta2.palo)
        {
            if (carta1.peso() < carta2.peso())
            {
                result = 1;
            }
        }
        else if (carta2.palo == cartaArriba.palo)
        {
            result = 1;
        }
            
        return result;
    }

    private boolean terminado()
    {
        return jugador1.cartas.isEmpty();
    }

    private void cuentaPuntos() {
        int puntos1 = jugador1.cuentaPuntos();
        int puntos2 = jugador2.cuentaPuntos();
        
        System.out.println ();
        System.out.println ("Partida terminada");
        System.out.println ("Puntos " + jugador1.nombre + " " + puntos1 + " -> " + jugador1.muestraCartasGanadas());
        System.out.println ("Puntos " + jugador2.nombre + " " + puntos2 + " -> " + jugador2.muestraCartasGanadas());
        
        if (lasUltimas == 0)
        {
            puntos1 += 10;
        }
        else
        {
            puntos2 += 10;
        }
        
        System.out.println ();
        if (puntos1 > puntos2)
        {
            System.out.println ("Ha ganado jugador " + jugador1.nombre + " con " + puntos1 + " puntos");
        }
        else if (puntos1 < puntos2)
        {
            System.out.println ("Ha ganado jugador " + jugador2.nombre + " con " + puntos2 + " puntos");
        }
        else
        {
            System.out.println ("Empate");
        }
    }
}
