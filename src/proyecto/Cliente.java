package proyecto;

import java.io.*;
import java.util.Arrays;

public class Cliente extends Persona implements Serializable {
    private Venta[] comprasRealizadas;

    public Cliente(String nombre, String cc, String tel) {

        super(nombre, cc, tel);

    }


    public Venta[] getVentasC() {
        return comprasRealizadas;
    }

    public int getCantVentas() {
        if (comprasRealizadas==null){
            return 0;
        }
        return comprasRealizadas.length;
    }

    //Método buscar venta
    public void registrarCompra(Venta venta) throws IOException {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;

        try{

            if (comprasRealizadas==null){
                comprasRealizadas=new Venta[1];
                comprasRealizadas[0]=venta;
                fileOut = new FileOutputStream("venta"+".txt");
                out = new ObjectOutputStream(fileOut);
                out.writeObject(comprasRealizadas[0]);
            }else{
                this.comprasRealizadas = Arrays.copyOf(this.comprasRealizadas, this.comprasRealizadas.length + 1);
                comprasRealizadas[comprasRealizadas.length - 1] = venta;

            }

        }catch(IOException e){
            throw new IOException();

        }
        finally {
            try{
                if(fileOut != null){
                    fileOut.close();

                }

            }
            catch(IOException e){

            }
        }



    }



    public void delVenta(String code) {
        int i = 0;
        while (!code.equals(comprasRealizadas[i].getCodigo())) {
            i++;
        }
        System.arraycopy(comprasRealizadas, i + 1, comprasRealizadas, i, comprasRealizadas.length - 1 - i);
        comprasRealizadas = Arrays.copyOf(comprasRealizadas, comprasRealizadas.length - 1);
    }


}
