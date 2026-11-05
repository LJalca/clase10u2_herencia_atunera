/**
 * Especializacion de EtapaProceso para el despacho y exportacion de producto final.
 * Controla el codigo de contenedor, pais de destino y la antiguedad maxima en bodega.
 */
public class DespachoExportacion extends EtapaProceso {
    private String codigoContenedor;
    private double antiguedadMeses;
    private String paisDestino;

    /**
     * Constructor de la etapa DespachoExportacion.
     *
     * @param codigoLote       Identificador del lote
     * @param fecha            Fecha de embarque / despacho
     * @param digitador        Oficial de comercio exterior a cargo
     * @param codigoContenedor Identificador del contenedor reefer de exportacion
     * @param antiguedadMeses  Tiempo de almacenamiento previo al despacho (en meses)
     * @param paisDestino      Pais de destino de la exportacion
     */
    public DespachoExportacion(String codigoLote, String fecha, DigitadorExportacion digitador,
                               String codigoContenedor, double antiguedadMeses, String paisDestino) {
        super(codigoLote, fecha, digitador, "Despacho Exportación");
        this.codigoContenedor = (codigoContenedor != null) ? codigoContenedor.trim() : "";
        this.antiguedadMeses = antiguedadMeses;
        this.paisDestino = (paisDestino != null) ? paisDestino.trim() : "";
    }

    /**
     * Invariante de Dominio: La antiguedad del producto en bodega debe ser estrictamente menor a 1.0 mes.
     */
    @Override
    public boolean validarInvariante() {
        return antiguedadMeses < 1.0;
    }

    @Override
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Destino: %-10s | Antigüedad: %4.2f meses | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, paisDestino, antiguedadMeses, estado, infoDigitador);
    }

    public String getCodigoContenedor() {
        return codigoContenedor;
    }

    public void setCodigoContenedor(String codigoContenedor) {
        this.codigoContenedor = (codigoContenedor != null) ? codigoContenedor.trim() : "";
    }

    public double getAntiguedadMeses() {
        return antiguedadMeses;
    }

    public void setAntiguedadMeses(double antiguedadMeses) {
        this.antiguedadMeses = antiguedadMeses;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = (paisDestino != null) ? paisDestino.trim() : "";
    }
}

