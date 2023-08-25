package proyecto;

public class Automovil extends Carro {
    private final Cilindraje cilindraje;
    private double precio =20000;

    public Automovil(String marca, String modelo, String serial, Cilindraje cilindraje, boolean estado, boolean disponible) {
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
