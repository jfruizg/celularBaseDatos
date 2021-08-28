package Controller;

import Exportables.exportarExcel;
import Model.Persistence.celularDAO;
import Model.Persistence.operatcionArchivo;
import Model.phone;
import View.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class controller {

    private view vista;
    private celularDAO celular;
    private operatcionArchivo archivo;
    private ArrayList<phone> listaCelulares;
    private File file = new File("registroCelular.dat");
    private exportarExcel excel;

    public controller() {
        vista = new view();
        listaCelulares = new ArrayList<phone>();
        archivo = new operatcionArchivo(file);
        celular = new celularDAO(archivo);
        listaCelulares = archivo.leerAchivo(file);
        this.excel = new exportarExcel();
        ejecutar();
    }

    private void ejecutar() {
        String CONST = "SI";

        while (CONST.equals("SI")) {
            vista.mostrarDatos("*********************************");
            vista.mostrarDatos("Welcome to the mobile center");
            vista.mostrarDatos("*********************************" + "\n" + "\n");

            vista.mostrarDatos("Options" + "\n");
            vista.mostrarDatos("Register cellphone [1]" + "\n" + "Remove [2]" + "\n" + "Show only one phone register [3]" + "\n" + "Show all the registers [4]" + "\n" + "Upgrade cellphone [5]" + "\n"+"Show in excel"+"\n");
            int opcionPrincipal = Integer.parseInt(vista.recibirDatos());

            if (opcionPrincipal > 1 || opcionPrincipal < 5) {

                switch (opcionPrincipal) {

                    case 1:
                        vista.mostrarDatos("In this option you need to write some data" + "\n");
                        vista.mostrarDatos("Mobile number");
                        String numeroCelular = (vista.recibirDatos());
                        vista.mostrarDatos("Owners Name");
                        String nombreDueño = vista.recibirDatos();
                        vista.mostrarDatos("IMEI code");
                        String codigoImei = (vista.recibirDatos());
                        vista.mostrarDatos("Mobile brand");
                        String marcaCelular = (vista.recibirDatos());
                        vista.mostrarDatos("Mobile reference");
                        String referenciCelular = (vista.recibirDatos());


                        if (celular.verificarImei(codigoImei, listaCelulares) == true && celular.verificarCelular(numeroCelular,listaCelulares) == true) {

                            if (codigoImei.length() == 15 || numeroCelular.length() > 10) {
                                if (celular.agregarCelular(numeroCelular, nombreDueño, codigoImei, marcaCelular, referenciCelular, listaCelulares, file)) {
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                } else {
                                    vista.mostrarDatos("error");
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                }
                            } else {
                                vista.mostrarDatos("Digite el codigo imei con 15 numeros");
                            }
                        } else {
                            vista.mostrarDatos("Error");
                        }


                        break;
                    case 2:
                        vista.mostrarDatos("Remove mobile" + "\n");
                        vista.mostrarDatos("Write the mobaile IMEI code");
                        String codigoIbei = vista.recibirDatos();
                        if (codigoIbei == null) {

                        } else {
                            try {
                                if (celular.eliminarCelular(codigoIbei, listaCelulares, file)) {
                                    vista.mostrarDatos("Perfcet");
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                } else {
                                    vista.mostrarDatos("End");
                                }
                            } catch (Exception e) {
                                vista.mostrarDatos("End");
                            }
                        }
                        break;
                    case 3:
                        vista.mostrarDatos("Show one register mobile" + "\n");
                        vista.mostrarDatos("Code IMEI");
                        String codigo = (vista.recibirDatos());

                        vista.mostrarDatos(celular.mostrarPorCelular(codigo, listaCelulares, file));
                        vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                        CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                        break;
                    case 4:
                        vista.mostrarDatos(celular.verCelular(listaCelulares));
                        vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                        CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                        break;
                    case 5:
                        vista.mostrarDatos("modify your mobile");
                        int tipoModificacion = Integer.parseInt(vista.recibirDatos());
                        break;

                    case 6:
                        try{
                            excel.Excel(listaCelulares);
                        }catch (NullPointerException e){
                            vista.mostrarDatos("No");
                        }
                        break;
                }
            }
        }
    }
}
