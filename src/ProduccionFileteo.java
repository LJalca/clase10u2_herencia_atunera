/**
 * Especializacion de EtapaProceso para el proceso de produccion y fileteo.
 * Calcula el porcentaje de rendimiento a partir del peso de lomos limpios
 * y valida el rango de eficiencia industrial permitido.
 */
public class ProduccionFileteo extends EtapaProceso {
    private double pesoInicial;
    private double pesoLomos;
    private double pesoMerma;

    /**
     * Constructor de la etapa ProduccionFileteo.
     *
     * @param codigoLote  Identificador del lote
     * @param fecha       Fecha de ejecucion del fileteo
     * @param digitador   Supervisor o digitador de produccion asignado
     * @param pesoInicial Peso inicial de atun entero a procesar (kg)
     * @param pesoLomos   Peso util de lomos limpios obtenidos (kg)
     * @param pesoMerma   Peso de subproductos y mermas (kg)
     */
    public ProduccionFileteo(String codigoLote, String fecha, DigitadorProduccion digitador,
                             double pesoInicial, double pesoLomos, double pesoMerma) {
        super(codigoLote, fecha, digitador, "Producción Fileteo");
        this.pesoInicial = pesoInicial;
        this.pesoLomos = pesoLomos;
        this.pesoMerma = pesoMerma;
    }

    /**
     * Calcula el porcentaje de rendimiento del fileteo.
     * Rendimiento = (pesoLomos / pesoInicial) * 100
     *
     * @return Rendimiento porcentual (0.0 si pesoInicial <= 0)
     */
    public double calcularRendimiento() {
        if (pesoInicial <= 0.0) {
            return 0.0;
        }
        return (pesoLomos / pesoInicial) * 100.0;
    }

    /**
     * Invariante de Dominio: El rendimiento debe encontrarse en el rango de 45.0% a 51.0% inclusive.
     */
    @Override
    public boolean validarInvariante() {
        double rendimiento = calcularRendimiento();
        return rendimiento >= 45.0 && rendimiento <= 51.0;
    }

    @Override
    public void mostrarResumen() {
        String estado = validarInvariante() ? "[CONFORME ✓]" : "[NO CONFORME ✗]";
        String infoDigitador = (digitadorResponsable != null)
                ? digitadorResponsable.mostrarIdentificacion()
                : "Sin digitador asignado";

        System.out.printf("Etapa: %-22s | Lote: %-13s | Lomos: %7.1f kg | Rend.:  %8.2f %%  | Invariante: %-15s | Digitador: %s%n",
                nombreEtapa, codigoLote, pesoLomos, calcularRendimiento(), estado, infoDigitador);
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoLomos() {
        return pesoLomos;
    }

    public void setPesoLomos(double pesoLomos) {
        this.pesoLomos = pesoLomos;
    }

    public double getPesoMerma() {
        return pesoMerma;
    }

    public void setPesoMerma(double pesoMerma) {
        this.pesoMerma = pesoMerma;
    }
}

