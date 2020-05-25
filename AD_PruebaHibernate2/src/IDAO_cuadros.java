
import java.util.List;
import mydb.Pintor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aranx
 */
public interface IDAO_cuadros {
    
    Pintor buscarPintorHQL(int id_pintor); // SELECT
    Pintor buscarPintor(int id_pintor);
    Pintor buscarPintor2(int id_pintor);
    Pintor pintorDeUnCuadroHQL(int id_cuadro); // SELECT cruzada
    List<Pintor> todosLosPintoresHQL();
    int insertarPintor(Pintor pintor); // INSERT
    Pintor eliminarPintor(int id_pintor); // DELETE
    Pintor modificarPintor(Pintor pintor); // UPDATE
      
}
