package com.example.tap_intellij.Componentes;

public class Impresión_de_tarea {
    private String Número_de_archivo;
    private String Nombre_de_archivo;
    private int Número_de_hojas;
    private String Hora_de_acceso;

    public Impresión_de_tarea(String Número_de_archivo, String Nombre_de_archivo, int Número_de_hojas, String Hora_de_acceso) {
        this.Número_de_archivo = Número_de_archivo;
        this.Nombre_de_archivo = Nombre_de_archivo;
        this.Número_de_hojas = Número_de_hojas;
        this.Hora_de_acceso = Hora_de_acceso;
    }

    public String getNúmero_de_archivo() {
        return Número_de_archivo;
    }

    public void setNúmero_de_archivo(String número_de_archivo) {
        this.Número_de_archivo = número_de_archivo;
    }

    public String getNombre_de_archivo() {
        return Nombre_de_archivo;
    }

    public void setNombre_de_archivo(String nombre_de_archivo) {
        this.Nombre_de_archivo = nombre_de_archivo;
    }

    public int getNúmero_de_hojas() {
        return Número_de_hojas;
    }

    public void setNúmero_de_hojas(int número_de_hojas) {
        this.Número_de_hojas = número_de_hojas;
    }

    public String getHora_de_acceso() {
        return Hora_de_acceso;
    }

    public void setHora_de_acceso(String hora_de_acceso) {
        this.Hora_de_acceso = hora_de_acceso;
    }
}
