/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class Pedido {
   
    private int contadorPizzas=0;
    private HashMap<Integer,Pizza> alamacenPizzasPedido = new HashMap<>();

    public Pedido() {
    }

    public Collection<Pizza> getAlamacenPizzasPedido() {
        return alamacenPizzasPedido.values();
    }

    public void añadirPizzaPedido(Pizza pizza) {
        contadorPizzas++;
        alamacenPizzasPedido.put(pizza.getId(), pizza);
        
    }

    @Override
    public String toString() {
        return "Pedido{" + "alamacenPizzasPedido=" + alamacenPizzasPedido + '}';
    }
    
    
//    public String pizzasPedido(){
//    String respuesta="";
//        for (Integer numeroPizza : alamacenPizzasPedido.keySet()) {
//            Pizza pizza = alamacenPizzasPedido.get(numeroPizza);
//            respuesta+="PIZZA "+pizza.getId()+" "+numeroPizza+" "+pizza.getTipo().toUpperCase()+" MASA:"+pizza.getMasa();
//        }
//    return respuesta;
//    }
}
