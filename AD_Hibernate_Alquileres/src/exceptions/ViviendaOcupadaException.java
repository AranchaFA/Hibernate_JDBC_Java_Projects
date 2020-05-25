/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author aranx
 */
public class ViviendaOcupadaException extends Exception{

    public ViviendaOcupadaException() {
        super("La vivienda ya está ocupada!");
    }

    public ViviendaOcupadaException(String message) {
        super(message);
    }
    
    
}
