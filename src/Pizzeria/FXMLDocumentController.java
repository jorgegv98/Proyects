/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pizzeria;

import Gestion.Pedido;
import Gestion.Precios;
import Gestion.Pizza;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Jorge
 */
public class FXMLDocumentController implements Initializable {

    private Precios listadoPrecios = new Precios();
    private Pizza pizza = new Pizza();
    private Pedido pedido = new Pedido();

    @FXML
    private RadioButton botonMasaIntegral;
    @FXML
    private RadioButton botonMasaNormal;
    @FXML
    private ComboBox<String> boxTiposPizza;
    @FXML
    private ListView<String> listaIngredientesExtra;
    @FXML
    private Spinner<String> SpinnerTamaño;
    @FXML
    private Label textoPrecioTotal;
    @FXML
    private ToggleGroup masas;
    @FXML
    private TextArea pedidoResumen;
    @FXML
    private CheckBox checkBoxBebida;
    @FXML
    private CheckBox checkBoxGratinar;
    @FXML
    private Button botonAñadirPizzaPedido;
    @FXML
    private ListView<Pizza> listViewPizzas;
    @FXML
    private Label textoPrecioPizza;
    @FXML
    private Label numerodePizza;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarDatos();
        obtenerDatosPizza();

    }

    public void cargarDatos() {
        pizza.setPrecios(listadoPrecios);

        // --------------------------------- Introducir datos en ventana --------------------------
        boxTiposPizza.setItems(FXCollections.observableArrayList(listadoPrecios.getTipos()));
        listaIngredientesExtra.setItems(FXCollections.observableArrayList(listadoPrecios.getIngredientes()));
        listaIngredientesExtra.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        botonMasaNormal.setSelected(true);
        boxTiposPizza.setValue("Escoge el tipo");

        SpinnerValueFactory.ListSpinnerValueFactory<String> factoryTamaños = new SpinnerValueFactory.ListSpinnerValueFactory(FXCollections.observableArrayList(listadoPrecios.getTamaños()));
        SpinnerTamaño.setValueFactory(factoryTamaños);
        pizza.setTamañoPizza(String.valueOf(SpinnerTamaño.getValue()));

    }

    public void obtenerMasa() {
        String masaObtenida = ((RadioButton) masas.getSelectedToggle()).getText();
        pizza.setMasa(masaObtenida);
        pedidoResumen.setText(pizza.resumenPedido());

    }

    public void obtenerTipo() {
        String tipo = boxTiposPizza.getValue();
        pizza.setTipo(tipo);

    }

    public void obtenerIngredientes() {
        for (String ingrediente : listaIngredientesExtra.getSelectionModel().getSelectedItems()) {
            pizza.setIngredientes(ingrediente);
            System.out.println(ingrediente);
        }

        // ----------------
    }

    public void obtenerTamaño() {
        String tamañoPizzaSeleccion = SpinnerTamaño.getValue();
        pizza.setTamañoPizza(tamañoPizzaSeleccion);
    }

    public void obtenerGratinar() {
        if (checkBoxGratinar.isSelected()) {
            pizza.setGratinar(true);
        } else {
            pizza.setGratinar(false);
        }

    }

    public void obtenerBebida() {
        if (checkBoxBebida.isSelected()) {
            pizza.setBebida(true);
        } else {
            pizza.setBebida(false);
        }
    }

    private void obtenerDatosPizza() {
        try {
            obtenerMasa();
            obtenerTipo();
            obtenerGratinar();
//            obtenerIngredientes();
//            obtenerTamaño();
            obtenerBebida();
            pedidoResumen.setText(pizza.resumenPedido());
            DecimalFormat patron = new DecimalFormat("00.00");
            textoPrecioPizza.setText(patron.format(pizza.calcularPrecio()) + " €");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void añadirPizzaPedido(ActionEvent event) {
        pedido.añadirPizzaPedido(pizza);
        pizza = new Pizza();
        pizza.setPrecios(listadoPrecios);
        obtenerDatosPizza();
        obtenerIngredientes();
        obtenerTamaño();
        numerodePizza.setText(String.valueOf(pizza.getId()));
        listViewPizzas.setItems(FXCollections.observableArrayList(pedido.getAlamacenPizzasPedido()));
        pizza.setTamañoPizza(String.valueOf(SpinnerTamaño.getValue()));

    }

    @FXML
    private void elaborarPizza(ActionEvent event) {
        obtenerDatosPizza();

    }

    @FXML
    private void recogerDatosList(MouseEvent event) {
        if (event.getClickCount() == 2) {
            obtenerIngredientes();

        }
        obtenerTamaño();
        pedidoResumen.setText(pizza.resumenPedido());
        DecimalFormat patron = new DecimalFormat("00.00");
        textoPrecioPizza.setText(patron.format(pizza.calcularPrecio()) + " €");

    }

}
