package Exportables;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Model.phone;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Esta es la clase para exportar Excel clientes
 *
 * @author Juan Felipe Ruiz, Andres Nu�ez, Brayan Moreno,Edwin Daniel Yepes
 */

public class exportarExcel {

    public exportarExcel() {

    }

    public void Excel(ArrayList<phone> listaCelular) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        HSSFWorkbook objWB = new HSSFWorkbook();

        String[] tabla = {"Numero", "Dueño", "IMEI", "Marca", "Referencia"};
        // Creo la hoja
        HSSFSheet hoja1 = objWB.createSheet("hoja 1");

        // Proceso la informaci�n y genero el xls.
        HSSFRow fila = hoja1.createRow(0);

        // Aunque no es necesario podemos establecer estilos a las celdas.
        // Primero, establecemos el tipo de fuente
        HSSFFont fuente = objWB.createFont();
        fuente.setFontHeightInPoints((short) 11);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // Luego creamos el objeto que se encargar� de aplicar el estilo a la celda
        HSSFCellStyle estiloCelda = objWB.createCellStyle();
        estiloCelda.setWrapText(true);
        estiloCelda.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        estiloCelda.setFont(fuente);

        // Tambi�n, podemos establecer bordes...
        estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setBottomBorderColor((short) 8);
        estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setLeftBorderColor((short) 8);
        estiloCelda.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setRightBorderColor((short) 8);
        estiloCelda.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        estiloCelda.setTopBorderColor((short) 8);

        // Establecemos el tipo de sombreado de nuestra celda
        estiloCelda.setFillForegroundColor((short) 22);
        estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFCell celda = fila.createCell((short) 0);
        celda.setCellStyle(estiloCelda);
        celda.setCellType(HSSFCell.CELL_TYPE_STRING);

        for (int i = 0; i < tabla.length; i++) {
            String tabla1 = tabla[i];
            HSSFCell cell = fila.createCell(i);
            cell.setCellValue(tabla1);

        }

        for (int i = 0; i < listaCelular.size(); i++) {

            HSSFRow on = hoja1.createRow(i + 1);

            on.createCell(0).setCellValue(listaCelular.get(i).getNumeroCelular());
            on.createCell(1).setCellValue(listaCelular.get(i).getNombreDueño());
            on.createCell(2).setCellValue(listaCelular.get(i).getCodigoImei());
            on.createCell(3).setCellValue(listaCelular.get(i).getMarcaCelular());
            on.createCell(4).setCellValue(listaCelular.get(i).getReferencia());


        }

        try {
            FileOutputStream elFichero = new FileOutputStream("Excel_Celular.xls");
            objWB.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
