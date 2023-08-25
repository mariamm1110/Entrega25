package proyecto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;


public class Ventas extends JFrame {
    private JPanel Ventas;
    private JLabel tituloNuevaVenta;
    private JTextField inputAddCliente;
    ;
    private JTextField inputAddVendedor;
    private JTextField inputAddCarroMarca;
    private JComboBox inputAddFormaDePAgo;
    private JLabel tituloVentas;
    private JLabel tituloCarro;
    private JLabel tituloFormaDePago;
    private JTextField inputAddCarroSerial;
    private JButton botonCrearVenta;
    private JButton atrasButton;

    public Ventas() {
        setContentPane(Ventas);
        setTitle("Ventas");
        setSize(960, 540);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Almacen.focus(inputAddCliente, "Cedula Cliente");
        Almacen.focus(inputAddVendedor, "Cedula Vendedor");
        Almacen.focus(inputAddCarroSerial, "Modelo");
        Almacen.focus(inputAddCarroMarca, "Marca");
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interfaz();
                dispose();
            }
        });
        botonCrearVenta.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e){

                System.out.println(inputAddCliente.getText());

                if (inputAddCliente.getText().equals("Cedula Cliente") ||
                        inputAddVendedor.getText().equals("Cedula Vendedor") ||
                        inputAddCarroSerial.getText().equals("Modelo") ||
                        inputAddCarroMarca.getText().equals("Marca")
                ) {
                    JOptionPane.showMessageDialog(null, "Campos vacios",
                            "Añadir empleado", JOptionPane.INFORMATION_MESSAGE);
                } else {


                    int posicionCliente = Almacen.buscarCliente(inputAddCliente.getText());
                    int posicionEmpleado = Almacen.buscarEmpleado(inputAddVendedor.getText());
                    int carroPos = Almacen.buscarCarro(inputAddCarroMarca.getText(), inputAddCarroSerial.getText());
                    if (carroPos == -1 || posicionCliente == -1 || posicionEmpleado == -1 || Almacen.getCarros()[carroPos].getPlaca().length()==6) {
                        JOptionPane.showMessageDialog(null, "Faltan datos o el vehículo no está disponible",
                                "Añadir usuario", JOptionPane.ERROR_MESSAGE);
                        inputAddCliente.setText("Cedula Cliente");
                        inputAddVendedor.setText("Cedula Vendedor");
                        inputAddCarroSerial.setText("Modelo");
                        inputAddCarroMarca.setText("Marca");
                    } else {
                        try {
                            Cliente c1 = Almacen.getClientes()[posicionCliente];
                            Vendedor v1 = (Vendedor) Almacen.getEmpleados()[posicionEmpleado];
                            Carro carro = Almacen.getCarros()[carroPos];
                            String formaPago = inputAddFormaDePAgo.getSelectedItem().toString();
                            switch (formaPago) {
                                case "Crédito":
                                    Almacen.addVenta(v1, c1, carro, LocalDate.now(), MP.CREDITO);
                                    carro.setPlaca();
                                    carro.setDisponible(false);
                                    break;
                                case "Transferencia Bancaria":
                                    Almacen.addVenta(v1, c1, carro, LocalDate.now(), MP.DEBITO);
                                    carro.setPlaca();
                                    carro.setDisponible(false);
                                    break;
                                case "Efectivo":
                                    Almacen.addVenta(v1, c1, carro, LocalDate.now(), MP.EFECTIVO);
                                    carro.setPlaca();
                                    carro.setDisponible(false);
                            }
                            int y = Almacen.getClientes()[posicionCliente].getVentasC().length - 1;
                            Venta v = Almacen.getClientes()[posicionCliente].getVentasC()[y];
                            JOptionPane.showMessageDialog(null, "Venta añadida: " + v.getCodigo() + " \nTotal: $" + v.calcularVenta() + "\nPlaca: " + carro.getPlaca(),
                                    "Añadir venta", JOptionPane.INFORMATION_MESSAGE);
                            inputAddCliente.setText("Cedula Cliente");
                            inputAddVendedor.setText("Cedula Vendedor");
                            inputAddCarroSerial.setText("Modelo");
                            inputAddCarroMarca.setText("Marca");
                        } catch (ClassCastException c) {
                            JOptionPane.showMessageDialog(null,
                                    "La cédula ingresada no coincide con ningún vendedor registrado.",
                                    "Añadir venta", JOptionPane.ERROR_MESSAGE);
                        }catch (IOException z){
                            z.printStackTrace();
                        }

                    }
                }
            }
        });
    }
}
