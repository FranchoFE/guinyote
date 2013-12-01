/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guinyote;

/**
 *
 * @author ffelez
 */
public class Carta 
{   
    int numero=0;
    int palo=0;
    
    @Override
    public String toString()
    {
        String result = getNumeroString() + " de " + getPaloString();
        return result;
    }

    private String getNumeroString() 
    {
        String result = "" + numero;
        switch (numero)
        {
            case 1:
                result = "As";
                break;
            case 10:
                result = "Sota";
                break;
            case 11:
                result = "Caballo";
                break;
            case 12:
                result = "Rey";
                break;
        }
        
        return result;
    }
    
    private String getPaloString() 
    {
        String result = "";
        switch (palo)
        {
            case 1:
                result = "Oros";
                break;
            case 2:
                result = "Copas";
                break;
            case 3:
                result = "Espadas";
                break;
            case 4:
                result = "Bastos";
                break;
        }
        
        return result;
    }
    
    public int peso()
    {
        int result = numero;
        
        if (numero == 1)
        {
            result = 12;
        }
        else if (numero == 3)
        {
            result = 11;
        }
        else if (numero == 12)
        {
            result = 10;
        }
        else if (numero == 10)
        {
            result = 9;
        }
        else if (numero == 11)
        {
            result = 8;
        }
        
        return result;
    }
    
    public int valor()
    {
        int result = 0;
        
        if (numero == 1)
        {
            result = 11;
        }
        else if (numero == 3)
        {
            result = 10;
        }
        else if (numero == 12)
        {
            result = 4;
        }
        else if (numero == 10)
        {
            result = 3;
        }
        else if (numero == 11)
        {
            result = 2;
        }
        
        return result;
    }
    
}
