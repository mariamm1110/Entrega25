package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdicionarTrabajadores extends JFrame {
    private JLabel tituloAddEmpleado;
    private JTextField inputNombreEmpleado;
    private JTextField inputCcEmpleado;
    private JTextField inputTelefonoEmpleado;
    private JCheckBox isVendedor;
    private JLabel titulAddProveedor;
    private JTextField inputNombreProveedor;
    private JTextField inputCcProveedor;
    private JTextField inputTelProveedor;
    private JPanel adicionarTrabajadores;
    private JButton botonCrearEmpleado;
    private JButton botonCrearProveedor;
    private JLabel tituloBorrarEmpleado;
    private JTextField inputBorrarEmpleado;
    private JButton botonBorrarEmpleado;
    private JLabel tituloBorrarProveedor;
    private JTextField inputBorrarProveedor;
    private JButton botonBorrarProveedor;
    private JButton atrasButton;
    private JComboBox inputAddTipoCarro;
    private JTextField inputSalario;

    public AdicionarTrabajadores() {
        setContentPane(adicionarTrabajadores);
        setTitle("Hola");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        defaultCamposProveedor();
        defaultCamposEmpleado();
        crearPersonal(botonCrearEmpleado);
        crearPersonal(botonCrearProveedor);
        botonAtras(atrasButton);
        borrarPersonal(botonBorrarEmpleado);
        borrarPersonal(botonBorrarProveedor);

    }

    public void defaultCamposEmpleado() {
        Almacen.focus(inputNombreEmpleado, "Nombre");
        Almacen.focus(inputCcEmpleado, "Cédula");
        Almacen.focus(inputTelefonoEmpleado, "Teléfono");
        Almacen.focus(inputSalario, "Salario");
        Almacen.focus(inputBorrarEmpleado, "Cédula");
    }

    public void resetCamposEmpleado() {
        inputNombreEmpleado.setText("Nombre");
        inputCcEmpleado.setText("Cédula");
        inputSalario.setText("Salario");
        inputTelefonoEmpleado.setText("Teléfono");
        isVendedor.setSelected(false);
    }

    public void defaultCamposProveedor() {
        Almacen.focus(inputNombreProveedor, "Nombre");
        Almacen.focus(inputCcProveedor, "NIT");
        Almacen.focus(inputTelProveedor, "Teléfono");
        Almacen.focus(inputBorrarProveedor, "NIT");
        inputAddTipoCarro.setSelectedItem("Tipo de Carros");
    }

    public void resetCamposProveedor() {
        inputNombreProveedor.setText("Nombre");
        inputCcProveedor.setText("NIT");
        inputTelProveedor.setText("Teléfono");
    }

    public void botonAtras(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Almacen.getEmpleados() == null) {
                    new Interfaz();
                    dispose();
                } else {
                    new DatosTrabajadores();
                    dispose();
                }
            }
        });
    }

    public void borrarPersonal(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Esto puede cambiarse por case y falta ver cómo tirar error si no existe la cédula pero sí hay gente
                if (b.getText().toLowerCase().contains("empleado")) {
                    if (Almacen.getEmpleados() == null) {
                        JOptionPane.showMessageDialog(null, "No hay empleados registrados", "Borrar Personal", JOptionPane.ERROR_MESSAGE);

                    } else {
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el empleado?");
                        switch (confirm) {
                            case 0:
                                try {
                                    Almacen.delEmpleado(inputBorrarEmpleado.getText());
                                    JOptionPane.showMessageDialog(null, "Empleado eliminado", "Borrar Personal", JOptionPane.INFORMATION_MESSAGE);
                                }
                                catch(IOException exc){
                                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el empleado", "Borrar Personal", JOptionPane.INFORMATION_MESSAGE);
                                }
                                inputBorrarEmpleado.setText("Cédula");


                                break;
                            case 1:
                                inputBorrarEmpleado.setText("Cédula");
                                break;
                            case 2:
                                inputBorrarEmpleado.setText("Cédula");
                                break;
                        }
                    }
                } else {
                    if (Almacen.getProveedores() == null) {
                        JOptionPane.showMessageDialog(null, "No hay proveedores registrados", "Borrar Personal", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el empleado?");
                        switch (confirm) {
                            case 0:
                                try {
                                    Almacen.delProveedor(inputBorrarProveedor.getText());
                                    JOptionPane.showMessageDialog(null, "Proveedor eliminado", "Borrar Personal", JOptionPane.INFORMATION_MESSAGE);
                                }
                                catch(IOException exc){
                                    JOptionPane.showMessageDialog(null, "No se pudo borrar", "Borrar Personal", JOptionPane.INFORMATION_MESSAGE);
                                }
                                inputBorrarProveedor.setText("NIT");


                                break;
                            case 1:
                                inputBorrarProveedor.setText("Cédula");
                                break;
                            case 2:
                                inputBorrarProveedor.setText("Cédula");
                                break;
                        }
                    }
                }
            }
        });
    }

    public void crearPersonal(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (b.getText().toLowerCase().contains("empleado")) {
                    String nombre = inputNombreEmpleado.getText();
                    String nit = inputCcEmpleado.getText();
                    String tel = inputTelefonoEmpleado.getText();
                    if (Almacen.buscarEmpleado(nit) != -1) { //Si al buscar no obtiene -1 significa que existe y no agrega nada.
                        resetCamposEmpleado();
                        JOptionPane.showMessageDialog(null, "Ya existe un empleado identificado con la cédula ingresado", "Añadir Personal", JOptionPane.ERROR_MESSAGE);

                        //Sino entonces verifica que los campos estén llenos para crear un empleado (Los campos no pueden ser los que están por defecto).
                    } else if ((Almacen.soloLetras(nombre) && !nombre.equalsIgnoreCase("Nombre")) && (!Almacen.soloLetras(nit) && !nit.equalsIgnoreCase("nit")) && (!Almacen.soloLetras(tel) && !tel.equalsIgnoreCase("teléfono"))) {
                        try{
                            Almacen.addEmpleado(nombre, nit, tel, 0, isVendedor.isSelected());
                            resetCamposEmpleado();
                            JOptionPane.showMessageDialog(null, "Empleado añadido", "Añadir Personal", JOptionPane.INFORMATION_MESSAGE);
                        }
                        catch(IOException exc){
                            JOptionPane.showMessageDialog(null, "Error al adicionar empleado", "Añadir Personal", JOptionPane.INFORMATION_MESSAGE);
                        }



                    } else {
                        JOptionPane.showMessageDialog(null, "No se ingresaron todos los datos solicitados.",
                                "Añadir Personal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    String nombre = inputNombreProveedor.getText();
                    String nit = inputCcProveedor.getText();
                    String tel = inputTelProveedor.getText();
                    if (Almacen.buscarProveedor(nit) != -1) { //Si al buscar no obtiene -1 significa que existe y no agrega nada.
                        resetCamposProveedor();
                        JOptionPane.showMessageDialog(null, "Ya existe un proveedor identificado con el NIT ingresado", "Añadir Personal", JOptionPane.ERROR_MESSAGE);

                        //Sino entonces verifica que los campos estén llenos para crear un proveedor (Los campos no pueden ser los que están por defecto).
                    } else if ((Almacen.soloLetras(nombre) && !nombre.equalsIgnoreCase("Nombre")) && (!Almacen.soloLetras(nit) && !nit.equalsIgnoreCase("nit")) && (!Almacen.soloLetras(tel) && !tel.equalsIgnoreCase("teléfono")) && !inputAddTipoCarro.getSelectedItem().toString().equalsIgnoreCase("tipo de carros")) {
                       try {
                           Almacen.addProveedor(nombre, nit, tel, inputAddTipoCarro.getSelectedItem().toString());
                           resetCamposProveedor();
                           JOptionPane.showMessageDialog(null, "Proveedor añadido", "Añadir Personal", JOptionPane.INFORMATION_MESSAGE);
                       }
                       catch(IOException exc){
                           JOptionPane.showMessageDialog(null, "No se pudo añadir exitosamente", "Añadir Personal", JOptionPane.INFORMATION_MESSAGE);
                       }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ingresaron todos los datos solicitados.",
                                "Añadir Personal", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
