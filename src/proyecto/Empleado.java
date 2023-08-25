package proyecto;

import java.io.*;

public class Empleado extends Persona implements Serializable {
    protected double salario;

    public Empleado(String nombre, String cc, String tel, double salario) {
        super(nombre, cc, tel);
        this.salario = salario;

    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }




}
