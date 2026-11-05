/**
 * Especializacion de Persona para el digitador de la etapa de Recepcion en Muelle.
 * Registra y gestiona el codigo del turno de balanza asignado.
 */
public class DigitadorRecepcion extends Persona {
    private String codigoTurnoBalanza;

    /**
     * Constructor para DigitadorRecepcion.
     *
     * @param cedula             Numero de cedula del digitador
     * @param nombreCompleto     Nombre y apellido del digitador
     * @param codigoTurnoBalanza Codigo identificador del turno de balanza (ej. "BAL-T1")
     */
    public DigitadorRecepcion(String cedula, String nombreCompleto, String codigoTurnoBalanza) {
        super(cedula, nombreCompleto, "Digitador de Recepción Muelle");
        this.codigoTurnoBalanza = (codigoTurnoBalanza != null) ? codigoTurnoBalanza.trim() : "";
    }

    public String getCodigoTurnoBalanza() {
        return codigoTurnoBalanza;
    }

    public void setCodigoTurnoBalanza(String codigoTurnoBalanza) {
        this.codigoTurnoBalanza = (codigoTurnoBalanza != null) ? codigoTurnoBalanza.trim() : "";
    }

    @Override
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s, Turno Balanza: %s)",
                rolCargo, nombreCompleto, cedula, codigoTurnoBalanza);
    }
}

