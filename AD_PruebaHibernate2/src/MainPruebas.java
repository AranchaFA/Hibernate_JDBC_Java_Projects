
import java.util.HashSet;
import mydb.Cuadro;
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
public class MainPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DAO_cuadros dao = new DAO_cuadros();
        Pintor pintor = new Pintor("Carracuca");
        pintor.setAnioNacimiento(1977);
        pintor.setEstilo("Tururú");
        System.out.println(dao.buscarPintor(1).toString());
        //System.out.println(dao.pintorDeUnCuadroHQL(1));
        //System.out.println(dao.insertarPintor(pintor));
        //pintor.setAnioNacimiento(2000);
        //pintor.setIdPintor(8); // No le hemos asignado el ID autogenerado por la BD al objeto pintor después de persistirlo!
        //System.out.println(dao.modificarPintor(pintor));
        //System.out.println(dao.eliminarPintor(pintor.getIdPintor()));
    }

}
