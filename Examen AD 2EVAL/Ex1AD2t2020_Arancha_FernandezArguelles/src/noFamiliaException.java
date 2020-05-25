/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aranx
 */
public class noFamiliaException extends Exception{

    public noFamiliaException() {
        super("Número de pasajeros excesivo!");
    }

    public noFamiliaException(String message) {
        super(message);
    }
    
}
