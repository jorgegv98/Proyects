/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jorge
 */
public class Precios {

    private Map<String, Double> tipos = new HashMap<>();
    private Map<String, Double> ingredientes = new HashMap<>();
    private Map<String, Double> masas = new HashMap<>();
    private Map<String, Double> tamaño = new HashMap<>();

    public Precios() {
          masas.put("NORMAL",9.00);
        masas.put("INTEGRAL",9.50);
        masas.put("BORDES DE QUESO CHEDDAR",9.50);

        ingredientes.put("QUESO",0.75);
        ingredientes.put("TOMATE",1.50);
        ingredientes.put("CEBOLLA",2.50);
        ingredientes.put("BACON",2.50);
        ingredientes.put("PIÑA",2.50);
        ingredientes.put("OLIVAS",1.00);

        tipos.put("BARBACOA", 7.00);
        tipos.put("MEXICANA", 8.50);
        tipos.put("BASICA", 3.00);
        tipos.put("4 QUESOS ", 5.00);
        tipos.put("BACON Y QUESO ", 6.00);
        tipos.put("PECADO CARNAL", 5.00);

        tamaño.put("PEQUEÑA", 8.00);
        tamaño.put("MEDIANA", 15.00);
        tamaño.put("FAMILIAR", 30.00);

    }

    public Set<String> getTipos() {
        return tipos.keySet();
    }

    public Set<String> getTamaños() {
        return tamaño.keySet();
    }

    public Set<String> getIngredientes() {
        return ingredientes.keySet();
    }

    public Set<String> getMasas() {
        return masas.keySet();
    }

    public Double buscarPrecioIngrediente(String ingrediente) {
        Double precio = 0.0;
        for (String ing : ingredientes.keySet()) {
            if (ing.equalsIgnoreCase(ingrediente)) {
                precio = ingredientes.get(ing);
            }
        }
        return precio;
    }

    public Double buscarPrecioMasa(String masa) {
        Double precio = 0.0;
        for (String ing : masas.keySet()) {
            if (ing.equalsIgnoreCase(masa)) {
                precio = masas.get(ing);
            }
        }
        return precio;
    }

    public Double buscarPrecioTipo(String tipo) {
        Double precio = 0.0;
        for (String ing : tipos.keySet()) {
            if (ing.equalsIgnoreCase(tipo)) {
                precio = tipos.get(ing);
            }
        }
        return precio;
    }

    public Double buscarPorcentajeTamaño(String tamaño) {
        Double precio = 0.0;
        for (String tamañoLista : this.tamaño.keySet()) {
            if (tamañoLista.equalsIgnoreCase(tamaño)) {
                precio = this.tamaño.get(tamañoLista);
            }
        }
        return precio;
    }

    

    public Double precioDeIngredientes(Set<String> ingredientesAsumar) {
        Double precioIngredientes = 0.00;

        for (String ingrediente : ingredientesAsumar) {
            precioIngredientes += this.ingredientes.get(ingrediente);
        }

        return precioIngredientes;
    }

}
