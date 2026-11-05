import java.util.ArrayList;
import java.util.List;

/**
 * Fase 6: Orquestacion, Pruebas Semilla y Validacion en Main
 *
 * Sistema de Trazabilidad de Cadena de Valor Atunera (POO en Java).
 * Aplica conceptos de:
 *  - Herencia: Reutilizacion de Persona y EtapaProceso en clases especializadas.
 *  - Polimorfismo: Procesamiento homogeneo mediante List<EtapaProceso> y despacho dinamico
 *    de validarInvariante() y mostrarResumen().
 *  - Invariantes de Dominio: Control de reglas tecnicas de temperatura, pesos, rendimientos y tiempos.
 */
public class Main {

    public static void main(String[] args) {
        imprimirEncabezadoGeneral();

        // ---------------------------------------------------------------------
        // 1. Instanciacion de Digitadores Especializados (Jerarquia Persona)
        // ---------------------------------------------------------------------
        DigitadorRecepcion digRecepcion = new DigitadorRecepcion(
                "1312345678", "Carlos Intriago", "BAL-M01");

        DigitadorCalidad digCalidad = new DigitadorCalidad(
                "1309876543", "Dra. Patricia Macías", "CERT-SAN-2026-088");

        DigitadorProduccion digProduccion = new DigitadorProduccion(
                "1315566778", "Ing. Roberto Mero", "LINEA-F02");

        DigitadorBodega digBodega = new DigitadorBodega(
                "1319988776", "Miguel Delgado", "LIC-MC-044");

        DigitadorExportacion digExportacion = new DigitadorExportacion(
                "1314433221", "Lcda. Andrea Saltos", "Aduanas del Pacífico S.A.");

        imprimirEquipoDigitadores(digRecepcion, digCalidad, digProduccion, digBodega, digExportacion);

        // ---------------------------------------------------------------------
        // 2. Construccion del Lote Semilla 1: Caso Exitoso (LOTE-2026-OK)
        // ---------------------------------------------------------------------
        List<EtapaProceso> cadenaValorLote1 = new ArrayList<>();
        cadenaValorLote1.add(new RecepcionMuelle(
                "LOTE-2026-OK", "04/11/2026", digRecepcion, "B/P Don Rafa", 15400.0, 5200.0));
        cadenaValorLote1.add(new ControlCalidad(
                "LOTE-2026-OK", "04/11/2026", digCalidad, 2.8, "SONDA-DIG-03"));
        cadenaValorLote1.add(new ProduccionFileteo(
                "LOTE-2026-OK", "04/11/2026", digProduccion, 10200.0, 4950.0, 5250.0));
        cadenaValorLote1.add(new AlmacenamientoBodega(
                "LOTE-2026-OK", "04/11/2026", digBodega, "TINA-ISO-104", 602.5, -22.5));
        cadenaValorLote1.add(new DespachoExportacion(
                "LOTE-2026-OK", "04/11/2026", digExportacion, "REEFER-EC-883", 0.40, "España"));

        // ---------------------------------------------------------------------
        // 3. Construccion del Lote Semilla 2: Caso con Fallo de Invariante (LOTE-2026-FAIL)
        //    (Temperatura de calidad 5.2 °C > 4.0 °C, rendimiento < 45 %, etc.)
        // ---------------------------------------------------------------------
        List<EtapaProceso> cadenaValorLote2 = new ArrayList<>();
        cadenaValorLote2.add(new RecepcionMuelle(
                "LOTE-2026-FAIL", "04/11/2026", digRecepcion, "B/P Mar Abierto", 14000.0, 5000.0));
        cadenaValorLote2.add(new ControlCalidad(
                "LOTE-2026-FAIL", "04/11/2026", digCalidad, 5.2, "SONDA-DIG-01"));
        cadenaValorLote2.add(new ProduccionFileteo(
                "LOTE-2026-FAIL", "04/11/2026", digProduccion, 9000.0, 3600.0, 5400.0));
        cadenaValorLote2.add(new AlmacenamientoBodega(
                "LOTE-2026-FAIL", "04/11/2026", digBodega, "TINA-ISO-019", 615.0, -14.0));
        cadenaValorLote2.add(new DespachoExportacion(
                "LOTE-2026-FAIL", "04/11/2026", digExportacion, "REEFER-EC-102", 1.80, "EE. UU."));

        // ---------------------------------------------------------------------
        // 4. Ejecucion y Despacho Polimorfico de los Lotes
        // ---------------------------------------------------------------------
        procesarCadenaValorLote("LOTE-2026-OK (Simulación: Flujo Óptimo)", cadenaValorLote1);
        procesarCadenaValorLote("LOTE-2026-FAIL (Simulación: Lote Rechazado por Invariantes)", cadenaValorLote2);

        imprimirCierreGeneral();
    }

    /**
     * Procesa polimorficamente una lista de etapas operativas List<EtapaProceso>,
     * invocando dinamicamente mostrarResumen() y validarInvariante().
     *
     * @param titulo Identificador o titulo descriptivo del lote a evaluar
     * @param lote   Coleccion heterogenea de subclases de EtapaProceso tratadas polimorficamente
     */
    public static void procesarCadenaValorLote(String titulo, List<EtapaProceso> lote) {
        System.out.println("========================================================================================================================");
        System.out.printf("  TRAZABILIDAD DE CADENA DE VALOR: %s%n", titulo);
        System.out.println("========================================================================================================================");

        boolean loteConforme = true;
        int conformes = 0;
        int noConformes = 0;

        // Bucle polimorfico for-each
        for (EtapaProceso etapa : lote) {
            etapa.mostrarResumen();
            if (etapa.validarInvariante()) {
                conformes++;
            } else {
                noConformes++;
                loteConforme = false;
            }
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("  RESUMEN DEL LOTE -> Total Etapas: %d | Conformes: %d | No Conformes: %d%n",
                lote.size(), conformes, noConformes);

        if (loteConforme) {
            System.out.println("  DICTAMEN FINAL  : [LOTE APTO PARA EXPORTACIÓN ✓] Todas las invariantes operativas fueron satisfechas.");
        } else {
            System.out.println("  DICTAMEN FINAL  : [LOTE RECHAZADO / RETENIDO ✗] Incumplimiento de una o más invariantes críticas.");
        }
        System.out.println("========================================================================================================================\n");
    }

    private static void imprimirEncabezadoGeneral() {
        System.out.println("\n########################################################################################################################");
        System.out.println("#                 SISTEMA INTEGRAL DE TRAZABILIDAD - PLANTA PROCESADORA Y EXPORTADORA DE ATÚN                  #");
        System.out.println("#                          ARQUITECTURA DE HERENCIA, ENCAPSULAMIENTO Y POLIMORFISMO                            #");
        System.out.println("########################################################################################################################\n");
    }

    private static void imprimirEquipoDigitadores(Persona... personal) {
        System.out.println(">>> EQUIPO DE PERSONAL Y DIGITADORES ESPECIALIZADOS (Jerarquía Persona):");
        for (Persona p : personal) {
            System.out.println("  * " + p.mostrarIdentificacion());
        }
        System.out.println();
    }

    private static void imprimirCierreGeneral() {
        System.out.println(">>> EJECUCIÓN DE FASE 6 COMPLETADA SATISFACTORIAMENTE.");
        System.out.println("    - Polimorfismo demostrado mediante List<EtapaProceso> y despacho dinámico de métodos.");
        System.out.println("    - Validación de invariantes de dominio ejecutada con rigurosidad.");
        System.out.println("########################################################################################################################\n");
    }
}

