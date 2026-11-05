/**
 * Especializacion de EtapaProceso para el almacenamiento en camara y bodega.
 * Controla el peso estandar de tina y la cadena de ultra-congelacion.
 */
public class AlmacenamientoBodega extends EtapaProceso {
    private String codigoTina;
    private double pesoTinaKg;
    private double temperaturaCamara;

    /**
     * Constructor de la etapa AlmacenamientoBodega.
     *
     * @param codigoLote        Identificador del lote
     * @param fecha             Fecha de ingreso a bodega
     * @param digitador         Operador de camara y bodega
     * @param codigoTina        Identificador de la tina o canastilla
     * @param pesoTinaKg        Peso neto de la tina (kg)
     * @param temperaturaCamara Temperatura medida en la camara frigorifica (°C)
     */
    public AlmacenamientoBodega(String codigoLote, String fecha, DigitadorBodega digitador,
                                String codigoTina, double pesoTinaKg, double temperaturaCamara) {
        super(codigoLote, fecha, digitador, "Almacenamiento Bodega");
        this.codigoTina = (codigoTina != null) ? codigoTina.trim() : "";
        this.pesoTinaKg = pesoTinaKg;
        this.temperaturaCamara = temperaturaCamara;
    }

    /**
     * Invariante de Dominio: El peso de la tina debe estar entre 600.0 y 605.0 kg inclusive,
     * y la temperatura de la camara debe ser menor o igual a -18.0 °C.
     */
    @Override
    public boolean validarInvariante() {
        return pesoTinaKg >= 600.0 && pesoTinaKg <= 605.0 && temperaturaCamara <= -18.0;
    }

    @Override
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Tina: %-13s | Peso: %6.1f kg, T: %5.1f °C | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, codigoTina, pesoTinaKg, temperaturaCamara, estado, infoDigitador);
    }

    public String getCodigoTina() {
        return codigoTina;
    }

    public void setCodigoTina(String codigoTina) {
        this.codigoTina = (codigoTina != null) ? codigoTina.trim() : "";
    }

    public double getPesoTinaKg() {
        return pesoTinaKg;
    }

    public void setPesoTinaKg(double pesoTinaKg) {
        this.pesoTinaKg = pesoTinaKg;
    }

    public double getTemperaturaCamara() {
        return temperaturaCamara;
    }

    public void setTemperaturaCamara(double temperaturaCamara) {
        this.temperaturaCamara = temperaturaCamara;
    }
}

