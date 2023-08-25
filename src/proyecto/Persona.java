package proyecto;

import java.io.*;

public abstract class Persona implements Serializable {

    protected String nombre;
    protected final String cedula;
    protected String tel;

    public Persona(String nombre, String cedula, String tel) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.tel = tel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTel() {
        return tel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


}
