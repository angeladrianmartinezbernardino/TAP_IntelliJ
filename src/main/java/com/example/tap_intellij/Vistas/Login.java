package com.example.tap_intellij.Vistas;

import com.example.tap_intellij.Modelos.Usuarios_DAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Stage {
    private Scene escena;
    private TextField txtUsuario;
    private PasswordField txtContrasena;
    private Button btnEntrar;
    private Usuarios_DAO modeloUsuario;

    public Login() {
        modeloUsuario = new Usuarios_DAO();
        CrearUI();
        this.setTitle("Login");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        VBox layout = new VBox(10);
        txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario");

        txtContrasena = new PasswordField();
        txtContrasena.setPromptText("Contraseña");

        btnEntrar = new Button("Entrar");
        btnEntrar.setOnAction(event -> autenticarYAcceder());

        layout.getChildren().addAll(txtUsuario, txtContrasena, btnEntrar);

        escena = new Scene(layout, 300, 200);
    }

    private void autenticarYAcceder() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (modeloUsuario.autenticar(usuario, contrasena)) {
            Acceso acceso = new Acceso();
            acceso.show();
            this.close(); // Cierra la ventana de login
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText("Credenciales inválidas");
            alert.setContentText("El usuario y/o la contraseña son incorrectos.");
            alert.showAndWait();
        }
    }
}
