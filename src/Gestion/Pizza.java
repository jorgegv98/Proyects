/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class Pizza {

    private static int contadorPizzas = 1;
    private int id;
    private String masa, tipo;
    private Set<String> ingredientes = new HashSet<>();
    private tamaño tamañoPizza;
    private Precios precios;
    private boolean bebida;
    private boolean gratinar;

    public Pizza(String masa, String tipo) {
        this.masa = masa;
        this.tipo = tipo;
        this.id = contadorPizzas++;
    }

    public Pizza() {
        this.id = contadorPizzas++;
    }

    enum tamaño {

        PEQUEÑA, MEDIANA, FAMILIAR
    }

    public int getId() {
        return id;
    }

    public String getBedida() {
        String respuesta;
        if (this.bebida == true) {
            respuesta = "SI";
        } else {
            respuesta = "NO";
        }
        return respuesta;
    }

    public void setBebida(boolean bebida) {
        this.bebida = bebida;
    }

    public String getGratinar() {
        String respuesta;
        if (this.gratinar == true) {
            respuesta = "SI";
        } else {
            respuesta = "NO";
        }

        return respuesta;
    }

    public void setGratinar(boolean gratinar) {
        this.gratinar = gratinar;
    }

    public void setTamañoPizza(String tamañoPizza) {
        if (tamañoPizza.equalsIgnoreCase("pequeña")) {
            this.tamañoPizza = tamaño.PEQUEÑA;
        } else if (tamañoPizza.equalsIgnoreCase("mediana")) {
            this.tamañoPizza = tamaño.MEDIANA;
        } else if (tamañoPizza.equalsIgnoreCase("familiar")) {
            this.tamañoPizza = tamaño.FAMILIAR;
        }


    }

    public String getTamañoPizza() {
        return String.valueOf(tamañoPizza);
    }

    public Precios getPrecios() {
        return precios;
    }

    public void setPrecios(Precios precios) {
        this.precios = precios;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    // ----------------------------------
    public double calcularPrecio() {
         double precioTotal = 0.00;
        try {
           
            double precioMasa, precioTipo, precioIngredientes, porcentajeTamaño;
            precioMasa = precios.buscarPrecioMasa(masa);
            precioTipo = precios.buscarPrecioTipo(tipo);
            precioIngredientes = precios.precioDeIngredientes(ingredientes);
            porcentajeTamaño = precios.buscarPorcentajeTamaño(String.valueOf(tamañoPizza)) / 100;
            precioTotal = (precioMasa + precioTipo + precioIngredientes) + ((precioMasa + precioTipo + precioIngredientes) * porcentajeTamaño);
            if (this.gratinar == true) {
                precioTotal += precioTotal * 0.02;
            }
            if (this.bebida == true) {
                precioTotal += 2;
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return precioTotal;

    }

    public String resumenPedido() {
        String respuesta = "";
        respuesta += "MASA: " + this.getMasa() + " " + precios.buscarPrecioMasa(masa) + " €";
        respuesta += "\n TIPO: " + this.getTipo() + " " + precios.buscarPrecioTipo(tipo) + " €";
        respuesta += "\n INGREDIENTES: " + this.getIngredientes().toString() + " " + precios.precioDeIngredientes(ingredientes) + " €";
        respuesta += "\n TAMAÑO: " + this.getTamañoPizza() + " " + precios.buscarPorcentajeTamaño(this.getTamañoPizza()) + " %";
        respuesta += "\n BEBIDA (+2€): " + this.getBedida();
        respuesta += "\n GRATINADA (+2%): " + this.getGratinar();
//        respuesta += "\n Total: "+this.calcularPrecio();

        return respuesta;
    }

    @Override
    public String toString() {
        DecimalFormat formatoPrecio = new DecimalFormat("00.00");
        return "PIZZA "+this.getId()+":  " + tipo.toUpperCase() + "  MASA: " + masa + "   " + formatoPrecio.format(this.calcularPrecio()) + " €";
    }

}
