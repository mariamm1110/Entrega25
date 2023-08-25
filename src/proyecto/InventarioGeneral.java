package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioGeneral extends JFrame {
    private JLabel tituloInventario;
    private JButton botonAutomoviles;
    private JButton botonCamperos;
    private JButton botonDeportivos;
    private JButton botonDisponibilidad;
    private JPanel principal;
    private JButton atrasButton;

    public InventarioGeneral() {
        setContentPane(principal);
        setTitle("Hola");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interfaz();
                dispose();
            }
        });
        botonAutomoviles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioAutomovil();
                dispose();
            }
        });
        botonCamperos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioCampero();
                dispose();
            }
        });
        botonDeportivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventarioDeportivos();
                dispose();
            }
        });
        botonDisponibilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarDisponibilidad();
                dispose();
            }
        });
    }
}
