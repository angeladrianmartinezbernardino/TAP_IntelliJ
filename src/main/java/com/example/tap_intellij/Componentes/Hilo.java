package com.example.tap_intellij.Componentes;

import javafx.scene.control.ProgressBar;

public class Hilo extends Thread {
    private ProgressBar pgbCorredor;
    private String i;

    public Hilo(String nombre, ProgressBar pgb) {
        this.setName(nombre);
        this.pgbCorredor = pgb;
    }

    @Override
    public void run() {
        super.run();
        try {
            double avance = 0;
            while (avance <= 1) {
                sleep((long) (Math.random() * 1500));
                avance += Math.random() / 10;
                pgbCorredor.setProgress(avance);
                System.out.println("Corredor " + this.getName() + " llegó a la meta " + i + ".");
            }
            System.out.println("Corredor " + this.getName() + " llegó a la meta.");
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }
}
