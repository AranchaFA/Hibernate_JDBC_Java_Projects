
import java.util.ArrayList;
import java.util.Date;
import pojos.Amarre;
import pojos.Barco;
import pojos.Propietario;
import pojos.Puerto;
import pojos.Regata;

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

        PropietarioDAOImplementation dao = new PropietarioDAOImplementation();

        Date fecha = new Date(); // Fecha del sistema, para hacer la prueba
        Propietario propietario = new Propietario("12344321D", "Pepiiiii", "Pépez Jósez", fecha);

        // LISTA DE PUERTOS para la regata
        ArrayList<Puerto> puertos = new ArrayList<Puerto>();
        puertos.add(new Puerto("Un puerto mu bonito", 100, 30));

        // LISTA DE REGATAS para el barco
        ArrayList<Regata> regatas = new ArrayList<Regata>();
        Regata regata = new Regata("Regata1", new Date(), new Date());
        regata.setPuertos(puertos);
        regatas.add(regata);

        // LISTA DE BARCOS
        ArrayList<Barco> barcos = new ArrayList<Barco>();
        Barco barco = new Barco("1234", "Mi barquito chiquitito", 10, 5, regatas, new Amarre(1, 1, null));
        barcos.add(barco);

        // Añadimos la lista de barcos al propietario
        propietario.setBarcos(barcos);
        //System.out.println(dao.persistPropietario(propietario));
        //System.out.println(dao.listaPropietarios());
        
        //System.out.println(dao.deletePropietario(propietario.getDni()).toString());
        //System.out.println(dao.persistPropietario(propietario));

        System.out.println(dao.saveOrUpdatePropietario(propietario));
        //System.out.println(dao.listaPropietarios());
    }

}
