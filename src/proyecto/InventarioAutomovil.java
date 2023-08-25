package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioAutomovil extends JFrame {
    private JPanel inventarioAutomovil;
    private JLabel tituloInventarioAutomoviles;
    private JTextField inputBuscarMarca;
    private JTextField inputBuscarSerial;
    private JTextField inputAddModelo;
    private JComboBox inputAddCilindraje;
    private JLabel tituloNuevoAutomovil;
    private JTextField inputAddMarca;
    private JLabel tituloBorrarAutomovil;
    private JTextField inputBorrarMarca;
    private JTextField inputBorrarSerial;
    private JLabel tituloEditarAutomovil;
    private JTextField inputEditarMarca;
    private JTextField inputEditarSerial;
    private JTextField inputEditarPrecio;
    private JTextField inputEditarModelo;
    private JButton botonBuscar;
    private JButton botonCrear;
    private JButton botonBorrar;
    private JButton botonEditar;
    private JButton atrasButton;
    private JTextField inputAddSerial;
    private JLabel tituloBuscarAutomovil;
    private JButton botonEstado;

    public InventarioAutomovil() {
        setContentPane(inventarioAutomovil);
        setTitle("Hola");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Almacen.focus(inputAddMarca, "Marca");
        Almacen.focus(inputAddModelo, "Modelo");
        Almacen.focus(inputAddSerial, "Serial");
        Almacen.focus(inputBorrarMarca, "Marca");
        Almacen.focus(inputBorrarSerial, "Serial");
        Almacen.focus(inputBuscarMarca, "Marca");
        Almacen.focus(inputBuscarSerial, "Serial");
        Almacen.focus(inputEditarMarca, "Marca");
        Almacen.focus(inputEditarModelo, "Modelo");
        Almacen.focus(inputEditarPrecio, "Precio");
        Almacen.focus(inputEditarSerial, "Serial");
        botonAtras(atrasButton);
        String tipoCarro = "Automovil";
        Botones.enviarBuscar(botonBuscar,inputBuscarMarca,inputBuscarSerial,tipoCarro);
        Botones.enviarCrear(botonCrear,inputAddCilindraje,inputAddMarca,inputAddSerial,inputAddModelo,tipoCarro);
        Botones.enviarBorrar(botonBorrar,inputBorrarMarca,inputBorrarSerial,tipoCarro);
        Botones.enviarEditar(botonEditar,inputEditarMarca,inputEditarSerial,inputEditarModelo,inputEditarPrecio,tipoCarro);
        Botones.botonEstado(botonEstado,this);
    }

    public void botonAtras(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioGeneral();
                dispose();
            }
        });
    }

   /* public void enviarFormulario(JButton b) {

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (b.getText().toLowerCase()) {
                    case "buscar":
                        int index = Almacen.buscarCarro(inputBuscarMarca.getText(), inputBuscarSerial.getText());
                        try {
                            JOptionPane.showMessageDialog(null,
                                    "Nombre: " + Almacen.getCarros()[index].getMarca() +
                                            "\nModelo: " + Almacen.getCarros()[index].getModelo() +
                                            "\nPrecio: $" + Almacen.getCarros()[index].getPrecio(),
                                    "Automóvil", JOptionPane.INFORMATION_MESSAGE);
                            inputBuscarMarca.setText("Marca");
                            inputBuscarSerial.setText("Serial");
                        } catch (ArrayIndexOutOfBoundsException a) {
                            inputBuscarSerial.setText("Marca");
                            inputBuscarSerial.setText("Serial");
                            System.out.println(a);
                            JOptionPane.showMessageDialog(null,
                                    "Los datos ingresados no coinciden con ningún automóvil registrado.",
                                    "Automóvil", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "crear":
                        String textoCilindraje = inputAddCilindraje.getSelectedItem().toString();
                        Cilindraje cilindraje = textoCilindraje.equalsIgnoreCase("alto") ? Cilindraje.ALTO :
                                (textoCilindraje.equalsIgnoreCase("medio")) ? Cilindraje.MEDIO : Cilindraje.BAJO;
                        String marca = inputAddMarca.getText();
                        String modelo = inputAddSerial.getText();
                        String serial = inputAddModelo.getText();
                        if (Almacen.buscarCarro(marca, serial) != -1) { //Si al buscar no obtiene -1 significa que existe y no agrega nada.
                            JOptionPane.showMessageDialog(null, "Ya existe un automóvil de la marca " + marca + " ingresado con el serial indicado.", "Añadir Vehículo", JOptionPane.ERROR_MESSAGE);
                            inputAddMarca.setText("Marca");
                            inputAddSerial.setText("Serial");
                            inputAddModelo.setText("Modelo");
                            inputAddCilindraje.setSelectedItem("Cilindraje");
                            //Sino entonces verifica que los campos estén llenos para crear un empleado (Los campos no pueden ser los que están por defecto).
                        } else if ((Almacen.soloLetras(marca) && !marca.equalsIgnoreCase("Marca")) &&
                                (!Almacen.soloLetras(serial) && !serial.equalsIgnoreCase("Serial"))
                                && (!Almacen.soloLetras(modelo) && !modelo.equalsIgnoreCase("Modelo"))
                                && !cilindraje.toString().equalsIgnoreCase("Cilindraje")) {
                            Almacen.addCarro(inputAddMarca.getText(), inputAddModelo.getText(), inputAddSerial.getText(), cilindraje, true, true, "Automovil");
                            JOptionPane.showMessageDialog(null, "Automóvil creado correctamente.",
                                    "Automóvil", JOptionPane.INFORMATION_MESSAGE);
                            inputAddMarca.setText("Marca");
                            inputAddSerial.setText("Serial");
                            inputAddModelo.setText("Modelo");
                            inputAddCilindraje.setSelectedItem("Cilindraje");

                        } else {
                            JOptionPane.showMessageDialog(null, "No se ingresaron todos los datos solicitados.",
                                    "Añadir Personal", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "borrar":
                        String buscarMarca = inputBorrarMarca.getText();
                        String buscarSerial = inputBorrarSerial.getText();
                        if (Almacen.buscarCarro(buscarMarca, buscarSerial) == -1) {
                            JOptionPane.showMessageDialog(null, "No existe un automóvil de la marca " + buscarMarca + " con el serial ingresado.",
                                    "Automóvil", JOptionPane.ERROR_MESSAGE);
                            inputBorrarMarca.setText("Marca");
                            inputBorrarSerial.setText("Serial");
                        } else {
                            Almacen.delCarro(buscarMarca, buscarSerial);
                            inputBorrarMarca.setText("Marca");
                            inputBorrarSerial.setText("Serial");
                            JOptionPane.showMessageDialog(null, "Automóvil borrado correctamente.",
                                    "Automóvil", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case "editar":
                        String editarMarca = inputEditarMarca.getText();
                        String editarSerial = inputEditarSerial.getText();
                        int indexEditar = Almacen.buscarCarro(editarMarca, editarSerial);
                        try {
                            if (Almacen.getCarros()[indexEditar] instanceof Automovil) {
                                Automovil a = (Automovil) Almacen.getCarros()[indexEditar];
                                a.setModelo(inputEditarModelo.getText());
                                a.setPrecio(inputEditarPrecio.getText());
                                JOptionPane.showMessageDialog(null, "Automóvil editado correctamente.",
                                        "Automóvil", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "No existe un automóvil de la marca " + editarMarca + " con el serial ingresado.",
                                        "Automóvil", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null, "No existe un automóvil de la marca " + editarMarca + " con el serial ingresado.",
                                    "Automóvil", JOptionPane.ERROR_MESSAGE);
                        }
                        inputEditarMarca.setText("Marca");
                        inputEditarPrecio.setText("Precio");
                        inputEditarModelo.setText("Modelo");
                        inputEditarSerial.setText("Serial");
                        break;
                }
            }
        });
    }*/


}
