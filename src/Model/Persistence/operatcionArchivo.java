package Model.Persistence;

import Model.phone;

import java.io.*;
import java.util.ArrayList;

public class operatcionArchivo {


    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public operatcionArchivo(File archivo) {

        if (archivo.exists()) {
            System.out.println("Existe");
        } else {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<phone> leerAchivo(File archivo) {
        ArrayList<phone> juegos = new ArrayList<phone>();
        if (archivo.length() != 0) {
            try {
                entrada = new ObjectInputStream(new FileInputStream(archivo));
                juegos = (ArrayList<phone>) entrada.readObject();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return juegos;
    }

    public void escribirArchivo(ArrayList<phone> celular, File archivo) {
        try {
            salida = new ObjectOutputStream(new FileOutputStream(archivo));
            salida.writeObject(celular);
            salida.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
