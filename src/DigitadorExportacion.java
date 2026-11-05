/**
 * Especializacion de Persona para el oficial de comercio exterior o digitador de Despacho y Exportacion.
 * Registra y gestiona el nombre o identificador de la agencia aduanera colaboradora.
 */
public class DigitadorExportacion extends Persona {
    private String agenciaAduanera;

    /**
     * Constructor para DigitadorExportacion.
     *
     * @param cedula          Numero de cedula del oficial
     * @param nombreCompleto  Nombre y apellido del oficial
     * @param agenciaAduanera Nombre o codigo de la agencia aduanera asignada
     */
    public DigitadorExportacion(String cedula, String nombreCompleto, String agenciaAduanera) {
        super(cedula, nombreCompleto, "Oficial de Comercio Exterior / Despacho");
        this.agenciaAduanera = (agenciaAduanera != null) ? agenciaAduanera.trim() : "";
    }

    public String getAgenciaAduanera() {
        return agenciaAduanera;
    }

    public void setAgenciaAduanera(String agenciaAduanera) {
        this.agenciaAduanera = (agenciaAduanera != null) ? agenciaAduanera.trim() : "";
    }

    @Override
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s, Agencia Aduanera: %s)",
                rolCargo, nombreCompleto, cedula, agenciaAduanera);
    }
}

