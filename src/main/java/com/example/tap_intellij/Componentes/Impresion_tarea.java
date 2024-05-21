package com.example.tap_intellij.Componentes;

public class Impresion_tarea {

    private String Numero_archivo;
    private String Nombre_archivo;
    private int Numero_hojas;
    private String Hora_acceso;

    public Impresion_tarea(String Numero_archivo, String Nombre_archivo, int Numero_hojas, String Hora_acceso) {
        this.Numero_archivo = Numero_archivo;
        this.Nombre_archivo = Nombre_archivo;
        this.Numero_hojas = Numero_hojas;
        this.Hora_acceso = Hora_acceso;
    }

    public String getNumero_archivo() {
        return Numero_archivo;
    }

    public void setNumero_archivo(String Numero_archivo) {
        this.Numero_archivo = Numero_archivo;
    }

    public String getNombre_archivo() {
        return Nombre_archivo;
    }

    public void setNombre_archivo(String Nombre_archivo) {
        this.Nombre_archivo = Nombre_archivo;
    }

    public int getNumero_hojas() {
        return Numero_hojas;
    }

    public void setNumero_hojas(int Numero_hojas) {
        this.Numero_hojas = Numero_hojas;
    }

    public String getHora_acceso() {
        return Hora_acceso;
    }

    public void setHora_acceso(String Hora_acceso) {
        this.Hora_acceso = Hora_acceso;
    }
}
