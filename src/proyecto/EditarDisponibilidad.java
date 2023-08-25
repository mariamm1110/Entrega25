package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarDisponibilidad extends JFrame{
    private JLabel tituloEstado;
    private JLabel tituloDisponibilidad;
    private JTextField inputEstadoMarca;
    private JTextField inputDisponibilidadMarca;
    private JTextField inputEstadoSerial;
    private JTextField inputDisponibilidadSerial;
    private JCheckBox isEstado;
    private JCheckBox isDisponible;
    private JPanel editarDisponibilidad;
    private JButton botoneditarEstado;
    private JButton botonEditarDisponibilidad;
    private JButton atrasButton;

    public EditarDisponibilidad(){
        setContentPane(editarDisponibilidad);
        setTitle("Hola");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Almacen.focus(inputEstadoMarca,"Marca");
        Almacen.focus(inputDisponibilidadSerial,"Serial");
        Almacen.focus(inputEstadoSerial,"Serial");
        Almacen.focus(inputDisponibilidadMarca,"Marca");
        Botones.botonEditarEstado(botoneditarEstado ,inputEstadoMarca, inputEstadoSerial, isEstado.isSelected());
        Botones.botonEditarDisponibilidad(botonEditarDisponibilidad,inputEstadoMarca, inputEstadoSerial,isDisponible.isSelected());
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioGeneral();
                dispose();
            }
        });
    }
}
