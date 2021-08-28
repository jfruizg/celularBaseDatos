package View;

import java.util.Scanner;

public class view {

    Scanner in = new Scanner(System.in);

    public String recibirDatos(){
        String dato = in.nextLine();
        return dato;


    }
    public void mostrarDatos(String dato){
        System.out.print(dato);
    }



}
