package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;


public class Interfaz extends JFrame {
    private JPanel Principal;
    private JLabel Titulo;
    private JButton botonInventario;
    private JButton botonPersonal;
    private JButton botonVentas;
    private JButton botonUsuarios;

    public Interfaz() {
        setContentPane(Principal);
        setTitle(Titulo.getText());
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        botonInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioGeneral();
                dispose();
            }
        });
        botonPersonal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Almacen.getEmpleados() == null) {
                    JOptionPane.showMessageDialog(null, "Aún no existen empleados registrados. Redirigiendo al formulario de ingreso de personal.");
                    new AdicionarTrabajadores();
                    dispose();
                } else {
                    new DatosTrabajadores();
                    dispose();
                }
            }
        });
        botonVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ventas();
                dispose();
            }
        });
        botonUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Compradores();
                dispose();
            }
        });
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Almacen.addCarro("Re", "32", "C09H1", Cilindraje.ALTO, true, true, "Campero");


        Carro c1 = Almacen.getCarros()[0];
        LocalDate de = LocalDate.now();


        Almacen.addCarro("Ren", "32", "C0F9H", Cilindraje.ALTO, true, true, "Automovil");


        //c1=Almacen.getCarros()[1];


        Almacen.addCarro("Volvo", "2023", "0", Cilindraje.MEDIO, true, true, "Deportivo");
        Almacen.addCarro("Suzuki", "2023", "1", Cilindraje.MEDIO, true, true, "Automovil");
        Almacen.addCarro("BMW", "2023", "2", Cilindraje.ALTO, true, true, "Deportivo");
        Almacen.addCarro("BMW", "2023", "3", Cilindraje.ALTO, true, true, "Automovil");
        Almacen.addCarro("BMW", "2023", "4", Cilindraje.BAJO, true, true, "Campero");
        Almacen.addCarro("Volvo", "2023", "5", Cilindraje.MEDIO, true, true, "Campero");
        Almacen.addCarro("BMW", "2023", "6", Cilindraje.BAJO, true, true, "Automovil");
        try {
            cargarDatos();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        new Interfaz();
    }

    public static void leerFichero(String ruta) throws IOException {
        try {
            if (ruta.toLowerCase().contains("cliente")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Cliente a = (Cliente) in.readObject();
                while (a != null) {
                    Almacen.addCliente(a.getNombre(), a.getCedula(), a.getTel());
                    a = (Cliente) in.readObject();
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("empleado")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Empleado e = (Empleado) in.readObject();
                while (e != null) {
                    Almacen.addEmpleado(e.getNombre(), e.getCedula(), e.getTel(), e.getSalario(), false);
                    e = (Empleado) in.readObject();
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("proveedor")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Proveedor p = (Proveedor) in.readObject();
                while (p != null) {
                    Almacen.addProveedor(p.getNombre(), p.getNit(), p.getTel(), p.getTipoCarros());
                    p = (Proveedor) in.readObject();
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("vendedor")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Vendedor v = (Vendedor) in.readObject();
                while (v != null) {
                    Almacen.addEmpleado(v.getNombre(), v.getCedula(), v.getTel(), v.getSalario(), true);
                    v = (Vendedor) in.readObject();
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("carro")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Carro c = (Carro) in.readObject();
                while (c != null) {
                    String tipoCarro = c.getClass().getSimpleName().toLowerCase();
                    switch (tipoCarro) {
                        case "automovil":
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                            c = (Carro) in.readObject();
                            break;
                        case "deportivo":
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                            c = (Carro) in.readObject();
                            break;
                        case "campero":
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                            c = (Carro) in.readObject();
                            break;
                    }
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("venta")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Venta b = (Venta) in.readObject();
                while (b != null) {
                    Almacen.addVenta(b.getVendedor(), b.getCliente(), (Carro) b.getCarro(), b.getLocalDate(), b.getMetodoP());
                    b = (Venta) in.readObject();
                }
                fileIn.close();
                in.close();
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cargarDatos() throws IOException {
        try {
            leerFichero("cliente.txt");
            leerFichero("empleado.txt");
            leerFichero("proveedor.txt");
            leerFichero("vendedor.txt");
            leerFichero("venta.txt");
            leerFichero("carro.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

