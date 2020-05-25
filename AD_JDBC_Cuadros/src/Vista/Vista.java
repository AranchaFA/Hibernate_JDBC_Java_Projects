/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import VOs.Cuadro;
import VOs.VOPintor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aranx
 */
public class Vista {
    
    public static void visualizarListaPintores(List<VOPintor> listaPintores) {
        for (VOPintor pintor : listaPintores) {
            System.out.println(pintor.toString());
        }
    }
    
    public static void visualizarListaCuadros(List<Cuadro> listaCuadros) {
        for (Cuadro cuadro : listaCuadros) {
            System.out.println(cuadro.toString());
        }
    }
    
    public static void visualizarMapCuadrosPorAutor(Map<Cuadro, String> mapCuadros) {
        for (Cuadro cuadro : mapCuadros.keySet()) {
            System.out.println("Pintor:  " + mapCuadros.get(cuadro) + " -> " + cuadro.toString());
        }
    }
    
    public static void visualizarInfoComentarios(Map<Integer, Map<String, String>> mapInfoComentarios) {
        for (Integer id_comentario : mapInfoComentarios.keySet()) {
            System.out.println("Comentario: " + id_comentario + " ->"
                    + " Cuadro: " + mapInfoComentarios.get(id_comentario).get("titulo")
                    + " Pintor: " + mapInfoComentarios.get(id_comentario).get("pintor")
                    + " Usuario: " + mapInfoComentarios.get(id_comentario).get("usuario"));
            if (mapInfoComentarios.get("fecha") != null) {
                System.out.println(" Fecha: " + mapInfoComentarios.get("fecha"));
            }
        }
    }
    
}
