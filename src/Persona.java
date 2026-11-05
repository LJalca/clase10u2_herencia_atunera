/**
 * Superclase base que modela a las personas y digitadores del sistema de trazabilidad atunera.
 * Aplica principios de encapsulamiento con atributos protegidos y metodos de acceso.
 */
public class Persona {
    protected String cedula;
    protected String nombreCompleto;
    protected String rolCargo;

    /**
     * Constructor general de la clase Persona.
     *
     * @param cedula         Numero de cedula de identidad o identificacion
     * @param nombreCompleto Nombre y apellido del colaborador
     * @param rolCargo       Rol o cargo que desempena en la planta procesadora
     */
    public Persona(String cedula, String nombreCompleto, String rolCargo) {
        this.cedula = (cedula != null) ? cedula.trim() : "";
        this.nombreCompleto = (nombreCompleto != null) ? nombreCompleto.trim() : "";
        this.rolCargo = (rolCargo != null) ? rolCargo.trim() : "";
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = (cedula != null) ? cedula.trim() : "";
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = (nombreCompleto != null) ? nombreCompleto.trim() : "";
    }

    public String getRolCargo() {
        return rolCargo;
    }

    public void setRolCargo(String rolCargo) {
        this.rolCargo = (rolCargo != null) ? rolCargo.trim() : "";
    }

    /**
     * Metodo polimorfico para mostrar la identificacion y rol del colaborador.
     * Puede ser sobreescrito por las subclases especializadas de digitadores.
     *
     * @return Cadena descriptiva con el rol, nombre y cedula.
     */
    public String mostrarIdentificacion() {
        return String.format("[%s] %s (C.I.: %s)", rolCargo, nombreCompleto, cedula);
    }

    @Override
    public String toString() {
        return mostrarIdentificacion();
    }
}

