package com.example.tap_intellij.Vistas.Taqueria.Tablas;

import com.example.tap_intellij.Modelos.Taqueria.Empleado_DAO;
import com.example.tap_intellij.Modelos.Taqueria.Mesa_DAO;
import com.example.tap_intellij.Modelos.Taqueria.Orden_detalle_DAO;
import com.example.tap_intellij.Modelos.Taqueria.Producto_DAO;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class Menu_Taqueria extends Stage {
    private Map<Button, String> categoriaMap;

    public Menu_Taqueria() {
        crearUI();
        Scene scene = new Scene(crearUI(), 800, 600);
        this.setTitle("Menú Taquería");
        this.setScene(scene);
        this.show();
    }

    private VBox crearUI() {
        GridPane gridPane = new GridPane();
        Button loginButton = new Button("Login");
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Button tortasButton = crearBoton("Tortas", "/Imagenes/tortas.jpg");
        Button tacosButton = crearBoton("Tacos", "/Imagenes/tacos.jpg");
        Button especialidadesButton = crearBoton("Especialidades", "/Imagenes/especialidades.jpg");
        Button bebidasButton = crearBoton("Bebidas", "/Imagenes/refrescos.jpg");
        Button mesasButton = crearBoton("Mesas", "/Imagenes/mesas.jpg");
        Button empleadosButton = crearBoton("Empleados", "/Imagenes/employee.png");
        gridPane.add(loginButton, 0, 0);
        gridPane.add(empleadosButton, 1, 0);
        gridPane.add(mesasButton, 2, 0);
        gridPane.add(tortasButton, 3, 0);
        gridPane.add(tacosButton, 4, 0);
        gridPane.add(especialidadesButton, 5, 0);
        gridPane.add(bebidasButton, 6, 0);
        TableView<Producto_DAO> tablaProductos = new TableView<>();
        tablaProductos.setPrefSize(200, 200);
        TableColumn<Producto_DAO, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Producto_DAO, Double> columnaPrecio = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tablaProductos.getColumns().addAll(columnaNombre, columnaPrecio);
        TableView<Orden_detalle_DAO> tablaOrden = new TableView<>();
        tablaOrden.setPrefSize(200, 200);
        TableColumn<Orden_detalle_DAO, Integer> columnaIdOrden = new TableColumn<>("ID Orden");
        columnaIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<Orden_detalle_DAO, Integer> columnaIdProducto = new TableColumn<>("ID Producto");
        columnaIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        TableColumn<Orden_detalle_DAO, Integer> columnaCantidad = new TableColumn<>("Cantidad");
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn<Orden_detalle_DAO, Double> columnaPrecios = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        TableColumn<Orden_detalle_DAO, Integer> columnaIdPromocion = new TableColumn<>("ID Promoción");
        columnaIdPromocion.setCellValueFactory(new PropertyValueFactory<>("idPromocion"));
        TableColumn<Orden_detalle_DAO, Integer> columnaIdMesa = new TableColumn<>("ID Mesa");
        columnaIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        tablaOrden.getColumns().addAll(columnaIdOrden, columnaIdProducto, columnaCantidad, columnaPrecios, columnaIdPromocion, columnaIdMesa);
        VBox root = new VBox(20);
        root.getChildren().addAll(gridPane, tablaProductos, tablaOrden);
        root.setAlignment(Pos.TOP_CENTER);
        tortasButton.setOnAction(event -> mostrarProductosDeCategoria("Tortas", tablaProductos));
        tacosButton.setOnAction(event -> mostrarProductosDeCategoria("Tacos", tablaProductos));
        especialidadesButton.setOnAction(event -> mostrarProductosDeCategoria("Especialidades", tablaProductos));
        bebidasButton.setOnAction(event -> mostrarProductosDeCategoria("Bebidas", tablaProductos));
        empleadosButton.setOnAction(event -> abrirVentanaDeEmpleados());
        mesasButton.setOnAction(event -> abrirVentanaDeMesas());
        return root;
    }

    private void abrirVentanaDeEmpleados() {
        Stage ventanaEmpleados = new Stage();
        ventanaEmpleados.setTitle("Lista de Empleados");
        TableView<Empleado_DAO> tablaEmpleados = new TableView<>();
        TableColumn<Empleado_DAO, String> columnaNombre = new TableColumn<>("Empleado");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nomEmpleado"));
        TableColumn<Empleado_DAO, String> columnaRFC = new TableColumn<>("RFC");
        columnaRFC.setCellValueFactory(new PropertyValueFactory<>("RFCEmpleado"));
        TableColumn<Empleado_DAO, Float> columnaSalario = new TableColumn<>("Salario");
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        TableColumn<Empleado_DAO, String> columnaTelefono = new TableColumn<>("Teléfono");
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        TableColumn<Empleado_DAO, String> columnaDireccion = new TableColumn<>("Dirección");
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tablaEmpleados.getColumns().addAll(columnaNombre, columnaRFC, columnaSalario, columnaTelefono, columnaDireccion/* Agrega las columnas adicionales aquí */);
        Empleado_DAO empleadoDAO = new Empleado_DAO();
        ObservableList<Empleado_DAO> empleados = empleadoDAO.Consultar();
        tablaEmpleados.setItems(empleados);
        VBox layout = new VBox(10);
        layout.getChildren().add(tablaEmpleados);
        Scene scene = new Scene(layout, 400, 300);
        ventanaEmpleados.setScene(scene);
        ventanaEmpleados.show();
    }

    private void abrirVentanaDeMesas() {
        Stage ventanaMesas = new Stage();
        ventanaMesas.setTitle("Mesas Disponibles");
        TableView<Mesa_DAO> tablaMesas = new TableView<>();
        TableColumn<Mesa_DAO, Integer> columnaIdMesa = new TableColumn<>("ID Mesa");
        columnaIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<Mesa_DAO, Integer> columnaNumero = new TableColumn<>("Número de Mesa");
        columnaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        TableColumn<Mesa_DAO, Integer> columnaEstado = new TableColumn<>("Estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tablaMesas.getColumns().addAll(columnaIdMesa, columnaNumero, columnaEstado);
        Mesa_DAO mesasDAO = new Mesa_DAO();
        ObservableList<Mesa_DAO> mesas = mesasDAO.Consultar();
        tablaMesas.setItems(mesas);
        VBox layout = new VBox(10);
        layout.getChildren().add(tablaMesas);
        Scene scene = new Scene(layout, 400, 300);
        ventanaMesas.setScene(scene);
        ventanaMesas.show();
    }


    private Button crearBoton(String texto, String imagePath) {
        Button button = new Button(texto);
        button.setPrefSize(100, 100);
        Image imagen = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        button.setGraphic(imageView);
        return button;
    }

    private void mostrarProductosDeCategoria(String categoria, TableView<Producto_DAO> tablaProductos) {
        Producto_DAO productoDAO = new Producto_DAO();
        ObservableList<Producto_DAO> productos = productoDAO.obtenerProductosPorCategoria(categoria);
        tablaProductos.setItems(productos);
    }
}