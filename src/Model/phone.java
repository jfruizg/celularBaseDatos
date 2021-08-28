package Model;

import java.io.Serializable;

public class phone implements Serializable {

    private static final long serializable = 1l;
    private String numeroCelular;
    private String nombreDueño;
    private String codigoImei;
    private String marcaCelular;
    private String referencia;

    public phone(String numeroCelular, String nombreDueño, String codigoImei, String marcaCelular, String referencia) {
        this.numeroCelular = numeroCelular;
        this.nombreDueño = nombreDueño;
        this.codigoImei = codigoImei;
        this.marcaCelular = marcaCelular;
        this.referencia = referencia;
        verificarinvariante();
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public String getCodigoImei() {
        return codigoImei;
    }

    public void setCodigoImei(String codigoImei) {
        this.codigoImei = codigoImei;
    }

    public String getMarcaCelular() {
        return marcaCelular;
    }

    public void setMarcaCelular(String marcaCelular) {
        this.marcaCelular = marcaCelular;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public static long getSerializable() {
        return serializable;
    }

    public String toString() {

        return "Mobile"+"\n"+"++++++++++++++++++++++++++++++++++++++"+"\n"+"1) Number = " + getNumeroCelular() + "\n" + "2) Owners Name =" + getNombreDueño() + "\n" + "3) IMEI Code = "
                + getCodigoImei() + "\n" + "3) Phone brand =" + getMarcaCelular() + "\n" + "4) Reference = " + getReferencia() + "\n"
                + "++++++++++++++++++++++++++++++++++++++"+"\n";
    }

    public void verificarinvariante() {

        assert numeroCelular != null : "El numero no pude ser menor a 0";
        assert nombreDueño != null : "El nombre del dueño no puede estar en Null";
        assert codigoImei != null: "El codigo no puede ser menor a 0";
        assert marcaCelular != null : "La marca no puede ser NULL";
        assert referencia != null : "La referencia no pude ser Null";

    }

}
