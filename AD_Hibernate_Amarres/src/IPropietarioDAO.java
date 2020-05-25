
import java.util.List;
import pojos.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aranx
 */
public interface IPropietarioDAO {

    public List<Propietario> listaPropietarios();

    public Propietario getPropietario(String dni);

    public Propietario deletePropietario(String dni);

    public boolean persistPropietario(Propietario propietario);

    public int saveOrUpdatePropietario(Propietario propietario);

}
