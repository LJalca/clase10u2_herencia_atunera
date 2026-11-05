/**
 * Especializacion de Persona para el operador y digitador de Bodega y Camaras de Frio.
 * Registra y gestiona el numero de licencia o autorizacion de montacarga.
 */
public class DigitadorBodega extends Persona {
    private String licenciaMontacarga;

    /**
     * Constructor para DigitadorBodega.
     *
     * @param cedula             Numero de cedula del operador
     * @param nombreCompleto     Nombre y apellido del operador
     * @param licenciaMontacarga Numero o codigo de la licencia de operador de montacargas
     */
    public DigitadorBodega(String cedula, String nombreCompleto, String licenciaMontacarga) {
        super(cedula, nombreCompleto, "Operador de Cámara y Bodega");
        this.licenciaMontacarga = (licenciaMontacarga != null) ? licenciaMontacarga.trim() : "";
    }

    public String getLicenciaMontacarga() {
        return licenciaMontacarga;
    }

    public void setLicenciaMontacarga(String licenciaMontacarga) {
        this.licenciaMontacarga = (licenciaMontacarga != null) ? licenciaMontacarga.trim() : "";
    }

    @Override
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s, Licencia Montacarga: %s)",
                rolCargo, nombreCompleto, cedula, licenciaMontacarga);
    }
}

