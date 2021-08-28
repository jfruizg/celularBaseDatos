package Controller;

import Model.Persistence.celularDAO;
import Model.Persistence.operatcionArchivo;
import Model.phone;
import View.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class controller {

    view vista;
    celularDAO celular;
    operatcionArchivo archivo;
    private ArrayList<phone> listaCelulares;
    private File file = new File("registroCelular.dat");

    public controller() {
        vista = new view();
        listaCelulares = new ArrayList<phone>();
        archivo = new operatcionArchivo(file);
        celular = new celularDAO(archivo);
        listaCelulares = archivo.leerAchivo(file);
        ejecutar();
    }

    private void ejecutar() {
        String CONST = "SI";

        while (CONST.equals("SI")) {
            vista.mostrarDatos("*********************************");
            vista.mostrarDatos("Welcome to the mobile center");
            vista.mostrarDatos("*********************************" + "\n" + "\n");

            vista.mostrarDatos("Options" + "\n");
            vista.mostrarDatos("Register cellphone [1]" + "\n" + "Remove [2]" + "\n" + "Show only one phone register [3]" + "\n" + "Show all the registers [4]" + "\n" + "Upgrade cellphone [5]" + "\n");
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
                        for (int i = 0; i < listaCelulares.size(); i++) {
                            if (listaCelulares.get(i).getNumeroCelular().equals(numeroCelular)) {
                                vista.mostrarDatos("You cant repeat the number phone" + "\n");
                            } else if (listaCelulares.get(i).getCodigoImei().equals(codigoImei)) {
                                vista.mostrarDatos("You cant repeat the code " + "\n");
                            } else {
                                if (celular.agregarCelular(numeroCelular, nombreDueño, codigoImei, marcaCelular, referenciCelular, listaCelulares, file)) {
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                } else {
                                    vista.mostrarDatos("error");
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                }

                            }
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
                        vista.mostrarDatos("upgrade all the mobile -> [1]" + "\n" + "Upgrade only the number -> [2]" + "\n" + "Upgrade only the owner -> [3]" + "\n" + "Upgrade only the brand and the reference -> [4]" + "\n" + "Upgrade the IMEI code -> [5]" + "\n");
                        int tipoModificacion = Integer.parseInt(vista.recibirDatos());

                        switch (tipoModificacion) {
                            case 1:
                                vista.mostrarDatos("Upgrade all the mobile" + "\n");
                                vista.mostrarDatos("Write the number IBEI code");
                                String code = vista.recibirDatos();
                                vista.mostrarDatos("Mobile number");
                                String numeroCelularModificar = (vista.recibirDatos());
                                vista.mostrarDatos("Owners Name");
                                String nombreDueñoModificar = vista.recibirDatos();
                                vista.mostrarDatos("Mobile brand");
                                String marcaCelularModificar = (vista.recibirDatos());
                                vista.mostrarDatos("Mobile reference");
                                String referenciCelularModificar = (vista.recibirDatos());

                                for (int i = 0; i < listaCelulares.size(); i++) {
                                    if (listaCelulares.get(i).getNumeroCelular().equals(numeroCelularModificar)) {
                                        vista.mostrarDatos("You cant repeat the number phone" + "\n");
                                    } else {
                                        if (celular.modificarTodoEmpleado(numeroCelularModificar, nombreDueñoModificar, code, marcaCelularModificar, referenciCelularModificar, listaCelulares, file)) {
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        } else {
                                            vista.mostrarDatos("O no");
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        }
                                    }
                                }

                                break;
                            case 2:
                                vista.mostrarDatos("Upgrade only Number" + "\n");
                                vista.mostrarDatos("Write the number IBEI code");
                                String codeNumber = vista.recibirDatos();
                                vista.mostrarDatos("Mobile number");
                                String numeroCelularNumero = (vista.recibirDatos());

                                for (int i = 0; i < listaCelulares.size(); i++) {
                                    if (listaCelulares.get(i).getNumeroCelular().equals(numeroCelularNumero)) {
                                        vista.mostrarDatos("You cant repeat the number phone" + "\n");
                                    } else {
                                        if (celular.modificarNumero(numeroCelularNumero, codeNumber, listaCelulares, file)) {
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        } else {
                                            vista.mostrarDatos("O no");
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        }
                                    }
                                }
                                break;
                            case 3:
                                vista.mostrarDatos("Upgrade only Name" + "\n");
                                vista.mostrarDatos("Write the number IBEI code");
                                String codeName = vista.recibirDatos();
                                vista.mostrarDatos("Owners Name");
                                String nombreDueñoSModificar = vista.recibirDatos();
                                if (celular.modificarNombreDueño(nombreDueñoSModificar, codeName, listaCelulares, file)) {
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                } else {
                                    vista.mostrarDatos("O no");
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                }
                                break;
                            case 4:
                                vista.mostrarDatos("Upgrade only the brand and the reference" + "\n");
                                vista.mostrarDatos("Write the number IBEI code");
                                String codigoReferenciaIbei = vista.recibirDatos();
                                vista.mostrarDatos("Write the Mobile brand");
                                String marcaCelularModificarS = vista.recibirDatos();
                                vista.mostrarDatos("Write the reference");
                                String referenciaModificarS = vista.recibirDatos();

                                if (celular.modificarMarcaReferencia(codigoReferenciaIbei, marcaCelularModificarS, referenciaModificarS, listaCelulares, file)) {
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                } else {
                                    vista.mostrarDatos("O no");
                                    vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                    CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                }
                                break;
                            case 5:
                                vista.mostrarDatos("Upgrade only the IBEI code" + "\n");
                                vista.mostrarDatos("Write the number IBEI code");
                                String codigoIbeiModificar = vista.recibirDatos();
                                vista.mostrarDatos("Write the new code");
                                String nuevoCodigo = vista.recibirDatos();

                                for (int i = 0; i < listaCelulares.size(); i++) {
                                    if (listaCelulares.get(i).getCodigoImei().equals(nuevoCodigo)) {
                                        vista.mostrarDatos("You cant repeat the code phone" + "\n");
                                    } else {

                                        if (celular.modificarCodigoImei(codigoIbeiModificar, nuevoCodigo, listaCelulares, file)) {
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        } else {
                                            vista.mostrarDatos("O no");
                                            vista.mostrarDatos("You want to go to the Menu again? or end the program" + "\n" + "SI" + "\n" + "NO" + "\n");
                                            CONST = vista.recibirDatos().toUpperCase(Locale.ROOT);
                                        }
                                    }
                                }
                                break;
                        }
                        break;
                }
            }
        }
    }
}
