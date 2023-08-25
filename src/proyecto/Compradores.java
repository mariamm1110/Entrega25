package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Compradores extends JFrame {
    private JLabel tituloDatosCompradores;
    private JTextField inputCcBuscar;
    private JLabel tituloAddUsuario;
    private JTextField inputNombreNuevo;
    private JTextField inputCcNuevo;
    private JTextField inputTelNuevo;
    private JPanel compradores;
    private JButton botonBuscarComprador;
    private JButton botonCrarComprador;
    private JLabel tituloBorrarUsuario;
    private JTextField inputBorrarCc;
    private JButton botonBorrarUsuario;
    private JButton atrasButton;

    public Compradores() {
        setContentPane(compradores);
        setTitle("Hola");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Almacen.focus(inputBorrarCc, "Cédula");
        Almacen.focus(inputCcBuscar, "Cédula");
        Almacen.focus(inputCcNuevo, "Cédula");
        Almacen.focus(inputTelNuevo, "Teléfono");
        Almacen.focus(inputNombreNuevo, "Nombre");
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interfaz();
                dispose();
            }
        });

        botonCrarComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = inputNombreNuevo.getText();
                String tel = inputTelNuevo.getText();
                String cc = inputCcNuevo.getText();
                try {

                    if (nombre.equals("Nombre") ||
                            tel.equals("Teléfono") ||
                            cc.equals("Cédula")
                    ) {
                        JOptionPane.showMessageDialog(null, "Campos vacios",
                                "Añadir usuario", JOptionPane.ERROR_MESSAGE);
                    }
                    //Si ya existe devuelve la posición, si no existe devuelve -1 y lo crea
                    else {
                        if (Almacen.buscarCliente(cc) == -1) {

                            Almacen.addCliente(nombre, cc, tel);
                            JOptionPane.showMessageDialog(null, "Se añadió el usuario",
                                    "Añadir usuario", JOptionPane.INFORMATION_MESSAGE);


                        } else {
                            JOptionPane.showMessageDialog(null, "Este usuario ya ha sido " +
                                            "registrado",
                                    "Añadir usuario", JOptionPane.INFORMATION_MESSAGE);


                        }


                    }


                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(null, "No se pudo añadir exitosamente",
                            "Añadir usuario", JOptionPane.INFORMATION_MESSAGE);

                }


            }
        });

        botonBuscarComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cc = inputCcBuscar.getText();


                if (cc.equals("Cédula")
                ) {
                    JOptionPane.showMessageDialog(null, "Campos vacios",
                            "Añadir usuario", JOptionPane.ERROR_MESSAGE);
                }
                //Si ya existe devuelve la posición, si no existe devuelve -1 y lo crea
                else {

                    if (Almacen.buscarCliente(cc) == -1) {
                        JOptionPane.showMessageDialog(null, "Usuario no registrado",
                                "Añadir usuario", JOptionPane.ERROR_MESSAGE);

                    } else {

                        Cliente a = Almacen.getClientes()[Almacen.buscarCliente(cc)];

                        JOptionPane.showMessageDialog(null, "Nombre: " + a.getNombre() + " \nCompras realizadas: " + a.getCantVentas(),
                                "Añadir usuario", JOptionPane.INFORMATION_MESSAGE);

                    }

                }


                inputCcBuscar.setText("Cédula");

            }
        });
        botonBorrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cc = inputBorrarCc.getText();


                if (cc.equals("Cédula")
                ) {
                    JOptionPane.showMessageDialog(null, "Campos vacios",
                            "Editar usuario", JOptionPane.ERROR_MESSAGE);
                }
                //Si ya existe devuelve la posición, si no existe devuelve -1 y lo crea
                else {
                    if (Almacen.buscarCliente(cc) == -1) {
                        JOptionPane.showMessageDialog(null, "Usuario no registrado",
                                "Añadir usuario", JOptionPane.ERROR_MESSAGE);

                    }
                    else {

                        try {
                            Almacen.delCliente(cc);
                            JOptionPane.showMessageDialog(null, "El cliente ha sido borrado",
                                    "Editar usuario", JOptionPane.INFORMATION_MESSAGE);
                        }
                        catch(IOException exc){
                            JOptionPane.showMessageDialog(null, "Error al borrar el cliente",
                                    "Editar usuario", JOptionPane.INFORMATION_MESSAGE);
                        }


                    }

                }

            }


        });
    }
}
