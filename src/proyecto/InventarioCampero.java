package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioCampero extends JFrame {


    private JLabel tituloInventarioCamperos;
    private JLabel tituloBuscarCampero;
    private JTextField inputBuscarMarca;
    private JTextField inputBuscarSerial;
    private JLabel tituloNuevoCampero;
    private JLabel tituloBorrarCampero;
    private JTextField inputBorrarMarca;
    private JLabel tituloEditarCampero;
    private JTextField inputEditarModelo;
    private JButton botonBuscar;
    private JButton botonCrear;
    private JButton botonBorrar;
    private JButton botonEditar;
    private JTextField inputEditarPrecio;
    private JButton atrasButton;
    private JTextField inputBorrarSerial;
    private JTextField inputEditarMarca;
    private JTextField inputEditarSerial;
    private JTextField inputAddSerial;
    private JComboBox inputAddCilindraje;
    private JTextField inputAddModelo;
    private JTextField inputAddMarca;
    private JPanel inventarioCampero;
    private JButton botonEstado;

    public InventarioCampero() {
        setContentPane(inventarioCampero);
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
        String tipoCarro = "Campero";
        Botones.enviarBuscar(botonBuscar, inputBuscarMarca, inputBuscarSerial, tipoCarro);
        Botones.enviarCrear(botonCrear, inputAddCilindraje, inputAddMarca, inputAddSerial, inputAddModelo, tipoCarro);
        Botones.enviarBorrar(botonBorrar, inputBorrarMarca, inputBorrarSerial, tipoCarro);
        Botones.enviarEditar(botonEditar, inputEditarMarca, inputEditarSerial, inputEditarModelo, inputEditarPrecio, tipoCarro);
        Botones.botonEstado(botonEstado,this);
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioGeneral();
                dispose();
            }
        });
    }


}

