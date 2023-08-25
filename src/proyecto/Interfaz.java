package proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;


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
                    JOptionPane.showMessageDialog(null,"Aún no existen empleados registrados. Redirigiendo al formulario de ingreso de personal.");
                    new AdicionarTrabajadores();
                    dispose();
                }else{
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


    public static void main(String[] args) throws IOException, ClassNotFoundException{

        Almacen.addCarro("Re","32","C09H1",Cilindraje.ALTO,true,true, "Campero");



        Carro c1=Almacen.getCarros()[0];
        LocalDate de=LocalDate.now();


        Almacen.addCarro("Ren","32","C0F9H",Cilindraje.ALTO,true,true, "Automovil");


        //c1=Almacen.getCarros()[1];



        Almacen.addCarro("Volvo", "2023", "0", Cilindraje.MEDIO, true, true, "Deportivo");
        Almacen.addCarro("Suzuki","2023","1",Cilindraje.MEDIO,true,true, "Automovil");
        Almacen.addCarro("BMW","2023","2",Cilindraje.ALTO,true,true, "Deportivo");
        Almacen.addCarro("BMW","2023","3",Cilindraje.ALTO,true,true, "Automovil");
        Almacen.addCarro("BMW","2023","4",Cilindraje.BAJO,true,true, "Campero");
        Almacen.addCarro("Volvo","2023","5",Cilindraje.MEDIO,true,true, "Campero");
        Almacen.addCarro("BMW","2023","6",Cilindraje.BAJO,true,true, "Automovil");
        cargarDatos();
        new Interfaz();
    }

    public static void cargarDatos() throws IOException {
        try{
            FileInputStream fileIn=new FileInputStream("cliente.txt");
            ObjectInputStream in=new ObjectInputStream(fileIn);
            Cliente a=(Cliente)in.readObject();
            while(a!=null){
                Almacen.addCliente(a.getNombre(),a.getCedula(),a.getTel());
                a=(Cliente) in.readObject();
            }
            in.close();
            fileIn=new FileInputStream("empleado.txt");
            in=new ObjectInputStream(fileIn);
            Empleado e=(Empleado)in.readObject();
            while(e!=null){
                Almacen.addEmpleado(e.getNombre(),e.getCedula(),e.getTel(),e.getSalario(), false);
                e=(Empleado) in.readObject();
            }
            in.close();
            fileIn=new FileInputStream("proveedor.txt");
            in=new ObjectInputStream(fileIn);
            Proveedor p=(Proveedor) in.readObject();
            while(p!=null){
                Almacen.addProveedor(p.getNombre(),p.getNit(),p.getTel(),p.getTipoCarros());
                p=(Proveedor) in.readObject();
            }
            in.close();
            fileIn=new FileInputStream("vendedor.txt");
            in=new ObjectInputStream(fileIn);
            Vendedor v=(Vendedor) in.readObject();
            while(v!=null){
                Almacen.addEmpleado(v.getNombre(),v.getCedula(),v.getTel(),v.getSalario(),false);
                v=(Vendedor) in.readObject();
            }
            in.close();
        }catch(IOException r){

        }catch(ClassNotFoundException p){

        }
    }

}
