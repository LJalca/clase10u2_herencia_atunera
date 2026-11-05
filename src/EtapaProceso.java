/**
 * Superclase base que modela una etapa operativa de la cadena de valor atunera.
 * Contiene los atributos comunes a toda fase de procesamiento y establece el contrato polimorfico
 * de validacion de invariantes de dominio y visualizacion de resumen.
 */
public class EtapaProceso {
    protected String codigoLote;
    protected String fecha;
    protected Persona digitadorResponsable;
    protected String nombreEtapa;

    /**
     * Constructor base de la etapa de proceso.
     *
     * @param codigoLote           Identificador unico del lote (ej. "LOTE-2026-001")
     * @param fecha                Fecha u hora de ejecucion del proceso (ej. "04/11/2026")
     * @param digitadorResponsable Persona o digitador a cargo de la etapa
     * @param nombreEtapa          Nombre descriptivo de la etapa operativa
     */
    public EtapaProceso(String codigoLote, String fecha, Persona digitadorResponsable, String nombreEtapa) {
        this.codigoLote = (codigoLote != null) ? codigoLote.trim() : "";
        this.fecha = (fecha != null) ? fecha.trim() : "";
        this.digitadorResponsable = digitadorResponsable;
        this.nombreEtapa = (nombreEtapa != null) ? nombreEtapa.trim() : "";
    }

    /**
     * Metodo base polimorfico para validar las invariantes o reglas de dominio de la etapa.
     * Las subclases sobreescriben este metodo para implementar sus reglas tecnicas especificas
     * (temperaturas, rendimientos, pesos de tinas, antiguedad, etc.).
     *
     * @return true si la etapa cumple sus invariantes operativas; false en caso contrario.
     */
    public boolean validarInvariante() {
        return true;
    }

    /**
     * Metodo base polimorfico para mostrar en consola el resumen del estado de la etapa.
     * Imprime el nombre de la etapa, codigo del lote, fecha, el resultado de la invariante
     * y los datos de identificacion del digitador responsable.
     */
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Fecha: %-10s | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, fecha, estado, infoDigitador);
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = (codigoLote != null) ? codigoLote.trim() : "";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = (fecha != null) ? fecha.trim() : "";
    }

    public Persona getDigitadorResponsable() {
        return digitadorResponsable;
    }

    public void setDigitadorResponsable(Persona digitadorResponsable) {
        this.digitadorResponsable = digitadorResponsable;
    }

    public String getNombreEtapa() {
        return nombreEtapa;
    }

    public void setNombreEtapa(String nombreEtapa) {
        this.nombreEtapa = (nombreEtapa != null) ? nombreEtapa.trim() : "";
    }
}

