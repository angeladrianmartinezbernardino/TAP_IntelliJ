package com.example.tap_intellij.Vistas.Taqueria;

import com.example.tap_intellij.Modelos.Taqueria.Usuario_DAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Stage {
    private Scene escena;
    private TextField txtUsuario;
    private PasswordField txtContrasena;
    private Button btnEntrar;
    private Usuario_DAO modeloUsuario;

    public Login() {
        modeloUsuario = new Usuario_DAO();
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
        btnEntrar.setOnAction(event -> Autenticar_y_acceder());

        layout.getChildren().addAll(txtUsuario, txtContrasena, btnEntrar);

        escena = new Scene(layout, 300, 200);
    }

    private void Autenticar_y_acceder() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        if (modeloUsuario.autenticar(usuario, contrasena)) {
            Categoria categoria = new Categoria();
            categoria.show();
            this.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText("Credenciales inválidas");
            alert.setContentText("El usuario o la contraseña son incorrectass.");
            alert.showAndWait();
        }
    }
}
