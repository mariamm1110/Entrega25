package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

        // Almacen.addCarro("Re", "32", "C09H1", Cilindraje.ALTO, true, true, "Campero");


        //Carro c1 = Almacen.getCarros()[0];
        //LocalDate de = LocalDate.now();


        // Almacen.addCarro("Ren", "32", "C0F9H", Cilindraje.ALTO, true, true, "Automovil");


        //c1=Almacen.getCarros()[1];

/*
        Almacen.addCliente("Maria","123","123");
        Almacen.addCarro("Re","32","C09H1",Cilindraje.ALTO,true,true, "Campero");
        Almacen.addEmpleado("Juan","378","83",389,true);
        Vendedor v1=new Vendedor("Juan","3432","334",1400);
        Empleado em=Almacen.getEmpleados()[0];
        Cliente e1=Almacen.getClientes()[0];
        Carro c1=Almacen.getCarros()[0];
        LocalDate de=LocalDate.now();
        Almacen.addVenta(v1,e1,c1,de,MP.CREDITO);
        Almacen.addCliente("Germán","1234","1234");
        Almacen.addCarro("Ren","32","C0F9H",Cilindraje.ALTO,true,true, "Automovil");
        Almacen.addEmpleado("Juan","378","83",389,true);
        e1=Almacen.getClientes()[1];
        //c1=Almacen.getCarros()[1];
        Almacen.addVenta(v1,e1,c1,de,MP.EFECTIVO);
        Almacen.addEmpleado("Andres","1","3333333", 5000000,false);
        Almacen.addEmpleado("Julio","2","444444444", 2000000,true);
        Almacen.addEmpleado("Gullermo","3","55555555", 2000000,true);
        Almacen.addEmpleado("Mateo","4","8888888", 8000000,false);
        Almacen.addEmpleado("Camilo","5","3331333", 4000000,false);
        Almacen.addProveedor("Pepitos","111","3232323","Deportivos");
        Almacen.addProveedor("Pepitos","222","3232323","Automóviles");
        Almacen.addProveedor("Pepitos","333","3232323","Deportivos");
        Almacen.addProveedor("Pepitos","444","3232323","Camperos");
        Almacen.addCliente("Johnny","22","1231213");
        Almacen.addCliente("Johnny","33","1231213");
        Almacen.addCliente("Johnny","44","1231213");
        Almacen.addCliente("Johnny","55","1231213");
        Almacen.addCliente("Johnny","66","1231213");
        Almacen.addCarro("Volvo", "2023", "0", Cilindraje.MEDIO, true, true, "Deportivo");
        Almacen.addCarro("Suzuki","2023","1",Cilindraje.MEDIO,true,true, "Automovil");
        Almacen.addCarro("BMW","2023","2",Cilindraje.ALTO,true,true, "Deportivo");
        Almacen.addCarro("BMW","2023","3",Cilindraje.ALTO,true,true, "Automovil");
        Almacen.addCarro("BMW","2023","4",Cilindraje.BAJO,true,true, "Campero");
        Almacen.addCarro("Volvo","2023","5",Cilindraje.MEDIO,true,true, "Campero");
        Almacen.addCarro("BMW","2023","6",Cilindraje.BAJO,true,true, "Automovil");
*/
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
                while (true) {
                    try {
                        Cliente a = (Cliente) in.readObject();
                        Almacen.addCliente(a.getNombre(), a.getCedula(), a.getTel());
                    } catch (EOFException e) {
                        break;
                    }
                    fileIn.close();
                    in.close();
                }
            } else if (ruta.toLowerCase().contains("empleado")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                while (true) {
                    try {
                        Empleado e = (Empleado) in.readObject();
                        Almacen.addEmpleado(e.getNombre(), e.getCedula(), e.getTel(), e.getSalario(), false);
                    } catch (EOFException e) {
                        break;
                    }
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("proveedor")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                while (true) {
                    try {
                        Proveedor e = (Proveedor) in.readObject();
                        Almacen.addProveedor(e.getNombre(), e.getCedula(), e.getTel(), e.getTipoCarros());
                    } catch (EOFException e) {
                        break;
                    }
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("vendedor")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                while (true) {
                    try {
                        Vendedor e = (Vendedor) in.readObject();
                        Almacen.addEmpleado(e.getNombre(), e.getCedula(), e.getTel(), e.getSalario(), true);
                    } catch (EOFException e) {
                        break;
                    }
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("carro")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                while (true) {
                    try {
                        Carro c = (Carro) in.readObject();
                        String tipoCarro = c.getClass().getSimpleName().toLowerCase();
                        if (tipoCarro.equalsIgnoreCase("automovil")) {
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                        } else if (tipoCarro.equalsIgnoreCase("deportivo")) {
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                        } else if (tipoCarro.equalsIgnoreCase("campero")) {
                            Almacen.addCarro(c.getMarca(), c.getModelo(), c.getSerial(), c.getCilindraje(), c.isDisponible(), c.isEstado(), tipoCarro);
                        }
                    } catch (EOFException e) {
                        break;
                    }
                }
                fileIn.close();
                in.close();
            } else if (ruta.toLowerCase().contains("venta")) {
                FileInputStream fileIn = new FileInputStream(ruta);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                while (true) {
                    try {
                        Venta b = (Venta) in.readObject();
                        Almacen.addVenta(b.getVendedor(), b.getCliente(), (Carro) b.getCarro(), b.getLocalDate(), b.getMetodoP());
                    } catch (EOFException e) {
                        break;
                    }
                }
                fileIn.close();
                in.close();
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
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

