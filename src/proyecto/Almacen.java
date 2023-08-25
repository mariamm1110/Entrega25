package proyecto;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;

class EAlmacen extends Exception {

    public EAlmacen(String a) {
        super(a);
    }

}

public abstract class Almacen {
    private static Carro[] carros;
    private static Cliente[] clientes;
    private static Empleado[] empleados;
    private static Proveedor[] proveedores;
    private static String nombre;
    private static String dirreccion;

    //Carro[] carros, Cliente[] clientes, Empleado[] empleados, Ventas[] ventas,
   /* public Almacen(String nombre, String dirreccion) {
        carros = new Carro[0];
        clientes = new Cliente[0];
        empleados = new Empleado[0];
        this.nombre = nombre;
        this.dirreccion = dirreccion;
    }*/

    public static Carro[] getCarros() {
        return carros;
    }

    public static Cliente[] getClientes() {
        return clientes;
    }

    public static Empleado[] getEmpleados() {
        return empleados;
    }

    public static Proveedor[] getProveedores() {
        return proveedores;
    }

    public static String getNombre() {
        return nombre;
    }

    public static String getDirreccion() {
        return dirreccion;
    }
    public static void addCliente(String nombre, String cedula, String tel) throws IOException
    {
        FileOutputStream fileOut=null;
        ObjectOutputStream out=null;
        try {
            if (clientes == null) {
                clientes = new Cliente[1];
                clientes[0] = new Cliente(nombre, cedula, tel);
                fileOut = new FileOutputStream("cliente"+".txt");
                out = new ObjectOutputStream(fileOut);
                out.writeObject(clientes[0]);
            } else {
                clientes = Arrays.copyOf(clientes, clientes.length + 1);
                clientes[clientes.length - 1] = new Cliente(nombre, cedula, tel);
                System.out.println(clientes[clientes.length - 1]);
                fileOut=new FileOutputStream("cliente"+".txt");
                out=new ObjectOutputStream(fileOut);
                out.writeObject(clientes[clientes.length-1]);
            }

        }
        catch(IOException e){
            throw new IOException();

        }
        finally {
            try{
                if(fileOut != null){
                    fileOut.close();

                }
            }
            catch(IOException e){
            }
        }
    }



    public static void addEmpleado(String nombre, String cedula, String tel, double salario, boolean isVendedor)
            throws IOException {
        FileOutputStream fileOut=null;
        ObjectOutputStream out=null;
        try {
            if (empleados == null) {
                empleados = new Empleado[1];
                if (isVendedor) {

                    empleados[0] = new Vendedor(nombre, cedula, tel, salario);
                    fileOut=new FileOutputStream("vendedor"+".txt");
                    out=new ObjectOutputStream(fileOut);
                    out.writeObject(empleados[0]);
                } else {
                    empleados[0] = new Empleado(nombre, cedula, tel, salario);
                    fileOut=new FileOutputStream("empleado"+".txt");
                    out=new ObjectOutputStream(fileOut);
                    out.writeObject(empleados[0]);
                }
            } else {
                empleados = Arrays.copyOf(empleados, empleados.length + 1);
                if (isVendedor) {
                    empleados[empleados.length - 1] = new Vendedor(nombre, cedula, tel, salario);

                    fileOut=new FileOutputStream("vendedor"+".txt");
                    out=new ObjectOutputStream(fileOut);
                    out.writeObject(empleados[empleados.length-1]);
                } else {
                    empleados[empleados.length - 1] = new Empleado(nombre, cedula, tel, salario);
                    fileOut=new FileOutputStream("empleado"+".txt");
                    out=new ObjectOutputStream(fileOut);
                    out.writeObject(empleados[empleados.length-1]);
                }
            }
        }

        catch(IOException e){
            throw new IOException();

        }
        finally {
            try{
                if(fileOut != null){
                    fileOut.close();

                }

            }
            catch(IOException e){

            }
        }


    }

    public static void addProveedor(String nombre, String nit, String tel, String tipoCarro) throws IOException{
        FileOutputStream fileOut=null;
        ObjectOutputStream out=null;
        try {
            if (proveedores == null) {
                proveedores = new Proveedor[1];
                proveedores[0] = new Proveedor(nombre, nit, tel, tipoCarro);
                fileOut = new FileOutputStream("proveedor"+".txt");
                out = new ObjectOutputStream(fileOut);
                out.writeObject(proveedores[0]);

            } else {
                proveedores = Arrays.copyOf(proveedores, proveedores.length + 1);
                proveedores[proveedores.length - 1] = new Proveedor(nombre, nit, tel, tipoCarro);
                fileOut = new FileOutputStream("proveedor"+".txt");
                out = new ObjectOutputStream(fileOut);
                out.writeObject(proveedores[proveedores.length - 1]);
            }
        }
        catch(IOException e){
            throw new IOException();

        }
        finally {
            try{
                if(fileOut != null){
                    fileOut.close();

                }

            }
            catch(IOException e){

            }
        }
    }

    public static void addCarro(String marca, String modelo, String serial, Cilindraje tipo, boolean disponible, boolean estado, String tipoCarro) {//Ver cómo involucrar al proveedor
        FileOutputStream fileOut=null;
        ObjectOutputStream out=null;
        try{
            switch (tipoCarro.toLowerCase()) {
                case "campero":
                    Campero campero = new Campero(marca, modelo, serial, tipo, disponible, estado);
                    if (carros == null) {
                        carros = new Carro[1];
                        carros[0] = campero;

                    } else {
                        carros = Arrays.copyOf(carros, carros.length + 1);
                        carros[carros.length - 1] = campero;
                    }
                    break;
                case "deportivo":
                    Deportivo deportivo = new Deportivo(marca, modelo, serial, tipo, disponible, estado);
                    if (carros == null) {
                        carros = new Carro[1];
                        carros[0] = deportivo;
                    } else {
                        carros = Arrays.copyOf(carros, carros.length + 1);
                        carros[carros.length - 1] = deportivo;
                    }
                    break;
                case "automovil":
                    Automovil automovil = new Automovil(marca, modelo, serial, tipo, disponible, estado);
                    if (carros == null) {
                        carros = new Carro[1];
                        carros[0] = automovil;
                    } else {
                        carros = Arrays.copyOf(carros, carros.length + 1);
                        carros[carros.length - 1] = automovil;
                        break;
                    }
            }
            fileOut=new FileOutputStream("carro"+".txt");
            out=new ObjectOutputStream(fileOut);
            out.writeObject(carros[carros.length-1]);

        }catch(IOException e){

        }


            


    }

    public static void addVenta(Vendedor vendedor, Cliente cliente, Carro carro, LocalDate fecha, MP formaDePago) throws IOException {
        try{

            cliente.registrarCompra(new Venta(vendedor, cliente, carro, fecha, formaDePago));
            carro.setDisponible(false);

        }catch (IOException e){
            throw new IOException();

        }

    }

    public static int buscarProveedor(String nit) {//ver cómo poner con personas
        int i = 0;
        if (proveedores == null) {//Si es null envía -1 para indicar que no existe
            return -1;
        } else {
            while (i < proveedores.length && !nit.equals(proveedores[i].getCedula())) {
                i++;
            }
            if (i >= proveedores.length) {
                return -1;//Si i es mayor que el arreglo entonces no encontró nada y envía -1 para indicar que no existe
            }
            return i;
        }
    }

    public static int buscarCliente(String cedula) {//ver cómo poner con personas
        int i = 0;
        if (clientes == null) {
            return -1;

        }
        while (i < clientes.length && !cedula.equals(clientes[i].getCedula())) {
            i++;
        }
        if (i >= clientes.length) {
            return -1;
        }
        return i;
    }

    public static int buscarEmpleado(String cedula) throws ArrayIndexOutOfBoundsException {
        int i = 0;
        if (empleados == null) {//Si es null envía -1 para indicar que no existe
            return -1;
        } else {
            while (i < empleados.length && !cedula.equals(empleados[i].getCedula())) {
                i++;
            }
            if (i >= empleados.length) {
                return -1;//Si i es mayor que el arreglo entonces no encontró nada y envía -1 para indicar que no existe
            }
            return i;
        }
    }

    public static int buscarCarro(String marca, String serial) { //Buscar por marca y modelo y que muestre las cilindradas;
        int i = 0;
        if (carros == null) {//Si es null envía -1 para indicar que no existe
            return -1;
        } else {
            while ((i < carros.length) && !(serial.equals(carros[i].getSerial()) && marca.equalsIgnoreCase(carros[i].getMarca()))) {
                i++;
            }
            if (i >= carros.length) {
                return -1;//Si i es mayor que el arreglo entonces no encontró nada y envía -1 para indicar que no existe
            }
            return i;
        }
    }

    public static void delCliente(String cedula) throws IOException{//ver cómo poner con personas
        int i = 0;
        while (!cedula.equals(clientes[i].getCedula())) {
            i++;
        }
        if(i<clientes.length){
            File fileObj=new File(clientes[i].getNombre()+clientes[i].getCedula()+"cliente.txt");
            if(fileObj.exists()){
                if(fileObj.delete()){
                    System.arraycopy(clientes, i + 1, clientes, i, clientes.length - 1 - i);
                    clientes = Arrays.copyOf(clientes, clientes.length - 1);
                }
                else throw new IOException();
            }
            else throw new IOException();
        }


    }

    public static void delEmpleado(String cedula) throws IOException{
        int i = 0;
        while (!cedula.equals(empleados[i].getCedula())) {
            i++;
        }
        if(i<empleados.length ) {
            if(empleados[i] instanceof Vendedor){
                File fileObj=new File(empleados[i].getNombre()+empleados[i].getCedula()+"vendedor.txt");
                if(fileObj.exists()){
                    if(fileObj.delete()){
                        System.arraycopy(empleados, i + 1, empleados, i, empleados.length - 1 - i);
                        empleados = Arrays.copyOf(empleados, empleados.length - 1);
                    }
                    else{
                        throw new IOException();
                    }
                }
                else throw new IOException();
            }
            else{
                File fileObje=new File(empleados[i].getNombre()+empleados[i].getCedula()+"empleado.txt");
                if(fileObje.exists()){
                    if(fileObje.delete()){
                        System.arraycopy(empleados, i + 1, empleados, i, empleados.length - 1 - i);
                        empleados = Arrays.copyOf(empleados, empleados.length - 1);
                    }
                    else{
                        throw new IOException();
                    }
                }
                else throw new IOException();
            }



        }
    }

    public static void delProveedor(String cedula) throws IOException{
        int i = 0;
        while (!cedula.equals(proveedores[i].getCedula())) {
            i++;
        }
        if(i<proveedores.length){
            File fileObj=new File(proveedores[i].getNombre()+proveedores[i].getCedula()+"proveedor.txt");
            if(fileObj.exists()){
                if(fileObj.delete()){
                    System.arraycopy(proveedores, i + 1, proveedores, i, proveedores.length - 1 - i);
                    proveedores = Arrays.copyOf(proveedores, proveedores.length - 1);
                }
                else{
                    throw new IOException();
                }
            }
            else throw new IOException();
        }



    }

    public static void delCarro(String marca, String serial) {
        int i = 0;
        while (i < carros.length && !marca.equals(carros[i].getMarca()) && !serial.equals(carros[i].getModelo())) {
            i++;
        }

        System.arraycopy(carros, i + 1, carros, i, carros.length - 1 - i);
        carros = Arrays.copyOf(carros, carros.length - 1);
    }

    public static void editDisponibilidad(String marca, String modelo, boolean disponible) {
        int indexCarro = buscarCarro(marca, modelo);
        if (indexCarro != -1) {
            carros[indexCarro].setDisponible(disponible);
        }
    }

    public static void editEstado(String marca, String serial, boolean disponible) {
        int indexCarro = buscarCarro(marca, serial);
        if (indexCarro != -1) {
            carros[indexCarro].setEstado(disponible);
        }
    }

    public static int CantidadVentasRealizasHastaAhora() {
        int cont = 0;
        for (Cliente c : clientes)
            cont += c.getCantVentas();
        return cont;
    }

    public static int CantidadClientesHastaAhora() {
        return clientes.length;
    }

    public static void focus(JTextField c, String texto) {
        c.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (c.getText().contains(texto)) {
                    c.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!c.getText().equals("")) {

                } else {
                    c.setText(texto);
                }
            }
        });
    }

    public static boolean soloLetras(String texto) {
        boolean containsOnlyLetters = true;
        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c)) {
                containsOnlyLetters = false;
                break;
            }
        }
        return containsOnlyLetters;
    }




/*
    public static void main(String[] args) {
        Automovil carro = new Automovil("Samsung", "GalaxyS11", "3312123", Cilindraje.ALTO, true, true);

        carro.setPlaca("ABC123");//Ya se hizo una compra.

        System.out.println(carro.getPlaca());

        new Interfaz();
    }
    */
}

