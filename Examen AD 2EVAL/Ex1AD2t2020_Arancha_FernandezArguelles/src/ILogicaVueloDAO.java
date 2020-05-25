import java.util.Date;
import pojos.Aeropuerto;
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
public interface ILogicaVueloDAO {

    /**
     * Codigo: Se autogenera AL PERSISTIR (luego hay qeu asignárselo al ORM a devolver)
     * Asientos: Los genero
     * Billetes: Aún no existirá ninguno (no están vendidos), lista vacía
     * @param origen
     * @param destino
     * @param fechaVuelo
     * @return vuelo generado
     */

    Vuelo creaVuelo737(Aeropuerto origen,Aeropuerto destino, Date fechaVuelo);
}
