/**
 * Especializacion de EtapaProceso para la recepcion de pesca en muelle.
 * Registra pesajes de camiones con atun y valida la consistencia del peso neto.
 */
public class RecepcionMuelle extends EtapaProceso {
    private String nombreBarco;
    private double pesoBruto;
    private double taraCamion;

    /**
     * Constructor de la etapa RecepcionMuelle.
     *
     * @param codigoLote Identificador del lote
     * @param fecha      Fecha del pesaje
     * @param digitador  Digitador asignado con turno de balanza
     * @param nombreBarco Nombre del buque pesquero de procedencia
     * @param pesoBruto  Peso bruto medido en balanza (kg)
     * @param taraCamion Tara o peso vacio del camion (kg)
     */
    public RecepcionMuelle(String codigoLote, String fecha, DigitadorRecepcion digitador,
                           String nombreBarco, double pesoBruto, double taraCamion) {
        super(codigoLote, fecha, digitador, "Recepción Muelle");
        this.nombreBarco = (nombreBarco != null) ? nombreBarco.trim() : "";
        this.pesoBruto = pesoBruto;
        this.taraCamion = taraCamion;
    }

    /**
     * Calcula el peso neto de la captura de atun ingresada.
     *
     * @return pesoNeto = pesoBruto - taraCamion
     */
    public double calcularPesoNeto() {
        return pesoBruto - taraCamion;
    }

    /**
     * Invariante de Dominio: El peso bruto debe ser estrictamente mayor a la tara del camion,
     * garantizando que el peso neto sea positivo (> 0).
     */
    @Override
    public boolean validarInvariante() {
        return pesoBruto > taraCamion && calcularPesoNeto() > 0.0;
    }

    @Override
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Barco: %-12s | P.Neto: %8.2f kg | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, nombreBarco, calcularPesoNeto(), estado, infoDigitador);
    }

    public String getNombreBarco() {
        return nombreBarco;
    }

    public void setNombreBarco(String nombreBarco) {
        this.nombreBarco = (nombreBarco != null) ? nombreBarco.trim() : "";
    }

    public double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public double getTaraCamion() {
        return taraCamion;
    }

    public void setTaraCamion(double taraCamion) {
        this.taraCamion = taraCamion;
    }
}

