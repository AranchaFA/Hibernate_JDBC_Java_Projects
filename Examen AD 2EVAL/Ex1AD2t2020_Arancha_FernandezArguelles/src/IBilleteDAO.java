
import java.util.List;
import java.util.Map;
import pojos.Billete;
import pojos.Cliente;
import pojos.Pasajero;
import pojos.Vuelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aranx
 */
public interface IBilleteDAO {
    
    /**
     * Recorrerá la lista de pasajeros buscando aquellos que tengan menos de 18 años, y les aplicará el descuento
     * @param billete del que se desea calcular el importe (será un ORM TRANSIENT, aún no habrá sido persistido)
     * @return billete con el importe calculado, si falla la operación el billete devuelvo no tendrá asignada ningún ID
     */
    Billete calcularPrecioFinal(Billete billete);

    /**
     * Se mirará el nº de pasajeros del billete (size de su lista pasajeros) para comprobar que son 6 o menos,
     * luego se buscará en el vuelo una fila ENTERA libre (para no complicarnos)
     * El objeto Billete deberá estar en estado DETACHED (Transient daría exception, pues vamos a hacer update()).
     * @param billete del que se desea recolocar los asientos
     * @return map con la respuesta a la operación (TRUE exitosa, FALSE error) y el billete retornado (modificado o no, según el ressultado)
     */
    Map<Boolean,Billete> reAsignarAsientosFamilia(Billete billete);

    /**
     * Calculará el precio final utilizando otro método de esta interface, los reasignará si son familia, la fecha
     * de emisión será la del momento de generar el billete
     * @param pasajeros
     * @param vuelo
     * @param cliente
     * @param precioPasajero
     * @param descuentoNiños
     * @return
     */
    Billete creaBillete(List<Pasajero> pasajeros, Vuelo vuelo,Cliente cliente,float precioPasajero,float descuentoNiños, boolean isFamilia);
    
}
