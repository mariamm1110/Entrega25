package proyecto;

import java.io.Serializable;

public class Deportivo extends Carro implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private final Cilindraje cilindraje;
    private double precio = 100000;

    public Deportivo(String marca, String modelo, String serial, Cilindraje cilindraje, boolean estado, boolean disponible) {
        super(marca, modelo, serial, cilindraje, estado, disponible);
        this.cilindraje = cilindraje;
    }

    public Cilindraje getCilindraje() {
        return cilindraje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio=Integer.valueOf(precio);
    }

}
