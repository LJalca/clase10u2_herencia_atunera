/**
 * Especializacion de Persona para el inspector o digitador de Control de Calidad.
 * Registra y gestiona el numero de certificacion sanitaria oficial.
 */
public class DigitadorCalidad extends Persona {
    private String numeroCertificacionSanitaria;

    /**
     * Constructor para DigitadorCalidad.
     *
     * @param cedula                       Numero de cedula del inspector
     * @param nombreCompleto               Nombre y apellido del inspector
     * @param numeroCertificacionSanitaria Numero oficial de acreditacion/certificacion sanitaria
     */
    public DigitadorCalidad(String cedula, String nombreCompleto, String numeroCertificacionSanitaria) {
        super(cedula, nombreCompleto, "Inspector / Digitador de Calidad");
        this.numeroCertificacionSanitaria = (numeroCertificacionSanitaria != null)
                ? numeroCertificacionSanitaria.trim()
                : "";
    }

    public String getNumeroCertificacionSanitaria() {
        return numeroCertificacionSanitaria;
    }

    public void setNumeroCertificacionSanitaria(String numeroCertificacionSanitaria) {
        this.numeroCertificacionSanitaria = (numeroCertificacionSanitaria != null)
                ? numeroCertificacionSanitaria.trim()
                : "";
    }

    @Override
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s, Cert. Sanitaria: %s)",
                rolCargo, nombreCompleto, cedula, numeroCertificacionSanitaria);
    }
}

