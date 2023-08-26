package proyecto;//Crear los ficheros con cada tipo de carro.

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Carro implements Serializable {
    protected static int cant = 0;
    protected String marca, modelo, serial, placa;
    protected boolean estado, disponible;
    protected double precio = 0;
    protected Cilindraje cilindraje;

    public Carro(String marca, String modelo, String serial, Cilindraje tipo, boolean estado, boolean disponible) {
        super();
        cant++;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
        this.disponible = disponible;
        this.serial = serial;
        this.cilindraje = tipo;
        placa = "";
    }

    public static int getCant() {
        return cant;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSerial() {
        return serial;
    }

    public String getPlaca() {
        return placa;
    }

    public Cilindraje getCilindraje() {
        return cilindraje;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setPlaca() {
        String banco = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        this.placa = "";
        // La cadena en donde iremos agregando un carácter aleatorio
        for (int x = 0; x < 6; x++) {
            int indiceAleatorio = ThreadLocalRandom.current().nextInt(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            this.placa += caracterAleatorio;
        }
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


}
