/**
 * Especializacion de EtapaProceso para el control de calidad e inocuidad termica.
 * Verifica que el atun cumpla los estandares de temperatura de refrigeracion.
 */
public class ControlCalidad extends EtapaProceso {
    private double temperaturaMuestra;
    private String codigoSonda;

    /**
     * Constructor de la etapa ControlCalidad.
     *
     * @param codigoLote         Identificador del lote
     * @param fecha              Fecha de medicion
     * @param digitador          Inspector de calidad acreditado
     * @param temperaturaMuestra Temperatura de la muestra en grados Celsius
     * @param codigoSonda        Identificador del termometro/sonda calibrada
     */
    public ControlCalidad(String codigoLote, String fecha, DigitadorCalidad digitador,
                          double temperaturaMuestra, String codigoSonda) {
        super(codigoLote, fecha, digitador, "Control de Calidad");
        this.temperaturaMuestra = temperaturaMuestra;
        this.codigoSonda = (codigoSonda != null) ? codigoSonda.trim() : "";
    }

    /**
     * Invariante de Dominio: La temperatura de la muestra debe ser menor o igual a 4.0 °C.
     */
    @Override
    public boolean validarInvariante() {
        return temperaturaMuestra <= 4.0;
    }

    @Override
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Sonda: %-12s | Temp:   %8.2f °C | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, codigoSonda, temperaturaMuestra, estado, infoDigitador);
    }

    public double getTemperaturaMuestra() {
        return temperaturaMuestra;
    }

    public void setTemperaturaMuestra(double temperaturaMuestra) {
        this.temperaturaMuestra = temperaturaMuestra;
    }

    public String getCodigoSonda() {
        return codigoSonda;
    }

    public void setCodigoSonda(String codigoSonda) {
        this.codigoSonda = (codigoSonda != null) ? codigoSonda.trim() : "";
    }
}

