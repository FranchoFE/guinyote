/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guinyote;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author ffelez
 */
class Jugador {
    
    String nombre = "";
    LinkedList<Carta> cartas = new LinkedList<Carta>();
    LinkedList<Carta> cartasGanadas = new LinkedList<Carta>();

    Jugador (String nombre)
    {
        this.nombre = nombre;
    }
    
    void anyadeCarta(Carta carta) {
        cartas.add(carta);
    }

    String muestraCartas() 
    {
        String result = "";
        for (Carta carta : cartas)
        {
            result += carta + " || ";
        }
        return result;
    }
    
    String muestraCartasGanadas() {
        String result = "";
        for (Carta carta : cartasGanadas)
        {
            result += carta + " || ";
        }
        return result;
    }

    Carta pideCarta() 
    {
        Carta result = null;
        Scanner teclado = new Scanner(System.in);
        System.out.print ("¿Carta a soltar por el jugador " + nombre + "?: ");
        String carta = teclado.nextLine();
        
        boolean valid = false;
        while (!valid)
        {
            try
            {
                Integer cartaInt = Integer.valueOf(carta);
                if (cartaInt >= 1 && cartaInt <= cartas.size())
                {
                    valid = true;
                    result = cartas.remove(cartaInt-1);
                }
            }
            catch (NumberFormatException ex)
            {

            }
            
            if (!valid)
            {
                System.out.println ("Carta no válida. Introduzca número entre 1 y " + (cartas.size() + 1));
                carta = teclado.nextLine();
            }
        }
        
        return result;
    }

    int cuentaPuntos() 
    {
        int result = 0;
        for (Carta carta : cartasGanadas)
        {
            result += carta.valor();
        }
        return result;
    }
    
}
