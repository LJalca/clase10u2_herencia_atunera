/**
 * Especializacion de Persona para el supervisor o digitador de Produccion y Fileteo.
 * Registra y gestiona la linea de fileteo asignada en la planta.
 */
public class DigitadorProduccion extends Persona {
    private String lineaFileteoAsignada;

    /**
     * Constructor para DigitadorProduccion.
     *
     * @param cedula               Numero de cedula del digitador/supervisor
     * @param nombreCompleto       Nombre y apellido del digitador/supervisor
     * @param lineaFileteoAsignada Identificador de la linea de fileteo (ej. "LINEA-F02")
     */
    public DigitadorProduccion(String cedula, String nombreCompleto, String lineaFileteoAsignada) {
        super(cedula, nombreCompleto, "Supervisor / Digitador de Fileteo");
        this.lineaFileteoAsignada = (lineaFileteoAsignada != null) ? lineaFileteoAsignada.trim() : "";
    }

    public String getLineaFileteoAsignada() {
        return lineaFileteoAsignada;
    }

    public void setLineaFileteoAsignada(String lineaFileteoAsignada) {
        this.lineaFileteoAsignada = (lineaFileteoAsignada != null) ? lineaFileteoAsignada.trim() : "";
    }

    @Override
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s, Línea Fileteo: %s)",
                rolCargo, nombreCompleto, cedula, lineaFileteoAsignada);
    }
}

