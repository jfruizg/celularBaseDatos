package Model.Persistence;

import Model.phone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class celularDAO {

    private operatcionArchivo archivo;

    /**
     * @param : Recibe parametros de Tipo archivo
     */

    public celularDAO(operatcionArchivo archivo) {
        this.archivo = archivo;
        verificarInvariante();
    }

    /**
     * @param : Recibe parametros un arraylist
     * @return : El metodo retorna un dato de tipo String
     */
    public String verCelular(ArrayList<phone> listaCelular) {
        String texto = "";
        for (int i = 0; i < listaCelular.size(); i++) {
            texto = texto.concat(listaCelular.get(i).toString() + "\n");
        }
        return texto;
    }

    /**
     * @param : Recibe parametros un arrayList y String
     * @return : El metodo retorna un dato de tipo Empleado
     */
    public phone buscarCelular(ArrayList<phone> listaCelular, String codigoImei) {
        phone encontrado = null;
        if (!listaCelular.isEmpty()) {
            for (int i = 0; i < listaCelular.size(); i++) {
                if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                    encontrado = listaCelular.get(i);
                }
            }
        }
        return encontrado;
    }

    /**
     * @param : Recibe parametros de Tipos String, ArrayList, File, imt
     * @return : El metodo retorna un dato de tipo Booleano
     */

    public boolean agregarCelular(String numeroCelular, String nombreDueño, String codigoImei, String marcaCelular, String referencia,
                                  ArrayList<phone> listaCelular, File file) {
        phone nuevo = new phone(numeroCelular, nombreDueño, codigoImei, marcaCelular, referencia);


        if (buscarCelular(listaCelular, codigoImei) == null) {
            listaCelular.add(nuevo);
            archivo.escribirArchivo(listaCelular, file);
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param : Recibe parametros de Tipo String, arrayList, File
     * @return : El metodo retorna un dato de tipo Booleano
     * @throws: el metodo  realiza una exception
     */

    public boolean eliminarCelular(String codigoImei, ArrayList<phone> listaCelular, File file) {
        boolean resp = false;
        try {
            phone e = buscarCelular(listaCelular, codigoImei);
            if (e != null) {
                listaCelular.remove(e);
                file.delete();
                file.createNewFile();
                archivo.escribirArchivo(listaCelular, file);
                resp = true;
            }
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;

    }

    /**
     * @param : Recibe parametros de Tipo String, int, arrayList, File
     * @return : El metodo retorna un dato de tipo booleano
     * @throws: el metodo registrar casa realiza una exception
     */

    public boolean modificarTodoEmpleado(String numeroCelular, String nombreDueño, String codigoImei, String marcaCelular, String referencia,
                                         ArrayList<phone> listaCelular, File file) {

        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                listaCelular.get(i).setNumeroCelular(numeroCelular);
                listaCelular.get(i).setNombreDueño(nombreDueño);
                listaCelular.get(i).setMarcaCelular(marcaCelular);
                listaCelular.get(i).setReferencia(referencia);
            }
        }
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.archivo.escribirArchivo(listaCelular, file);

        return true;
    }

    public boolean modificarNumero(String numeroCelular, String codigoImei,
                                   ArrayList<phone> listaCelular, File file) {

        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                listaCelular.get(i).setNumeroCelular(numeroCelular);
            }
        }
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.archivo.escribirArchivo(listaCelular, file);

        return true;
    }

    public boolean modificarNombreDueño(String nombreDueño, String codigoImei,
                                        ArrayList<phone> listaCelular, File file) {

        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                listaCelular.get(i).setNombreDueño(nombreDueño);
            }
        }
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.archivo.escribirArchivo(listaCelular, file);

        return true;
    }

    public boolean modificarMarcaReferencia(String codigoImei, String marcaCelular, String referencia,
                                            ArrayList<phone> listaCelular, File file) {

        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                listaCelular.get(i).setMarcaCelular(marcaCelular);
                listaCelular.get(i).setReferencia(referencia);
            }
        }
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.archivo.escribirArchivo(listaCelular, file);

        return true;
    }

    public boolean modificarCodigoImei(String codigoImei, String codigoNuevo,
                                       ArrayList<phone> listaCelular, File file) {

        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                listaCelular.get(i).setCodigoImei(codigoNuevo);
            }
        }
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.archivo.escribirArchivo(listaCelular, file);

        return true;
    }

    public String mostrarPorCelular(String codigoImei, ArrayList<phone> listaCelular, File file) {
        String texto = "";
        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigoImei)) {
                texto = texto.concat(listaCelular.get(i).toString() + "\n");
            }
        }
        return texto;
    }

    public Boolean verificarImei(String codigImei, ArrayList<phone> listaCelular) {
        Boolean result = true;
        for (int i = 0; i < listaCelular.size(); i++) {
            if (listaCelular.get(i).getCodigoImei().equals(codigImei)) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }
    public Boolean verificarModificacionImei(String codigoImei, ArrayList<phone> listaCelular){
        Boolean result = true;
        for(int i= 0; i<listaCelular.size(); i++){
            if(listaCelular.get(i).getCodigoImei().equals(codigoImei)){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }
    public Boolean verificarCelular(String numeroCelular, ArrayList<phone> listaCelular){
        Boolean result = true;
        for(int i = 0; i<listaCelular.size(); i++){
            if(listaCelular.get(i).getNumeroCelular().equals(numeroCelular)){
                result = false;
            }else {
                result = true;
            }
        }
        return result;
    }

    private void verificarInvariante() {
        assert archivo != null : "El archivo en el que se va a trabajar no puede ser null";
    }

}

