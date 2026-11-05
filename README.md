# Taller Práctico: Unificación Industrial de la Planta Atunera con Herencia en Java

**Carrera:** Carrera de Desarrollo de Software  
**Asignatura:** Programación Orientada a Objetos (CDS-0201)  
**Unidad 2:** Mecanismos de Reutilización de Código y Polimorfismo  
**Sesión:** Clase 10 (U2)  
**Duración Estimada:** 90 minutos (1 hora y 30 minutos)  
**Modalidad:** Desarrollo práctico en laboratorio / IDE  

---

## 1. El Reto: De los Proyectos de la Hackathon a un Sistema Integral

Durante la HackathonPOO, cada equipo modeló un eslabón aislado de una **planta procesadora y exportadora de atún industrial**. En este taller unificaremos todos los eslabones en una arquitectura orientada a objetos mediante **Herencia y Polimorfismo**.

Al finalizar el taller, tendrás un sistema capaz de ejecutar la trazabilidad completa de un lote de pesca a lo largo de las 5 fases de la cadena de valor:
1. **Recepción en Muelle:** Descarga de pesca, tara de camión y conciliación de peso neto.
2. **Control de Calidad:** Muestreo térmico con sonda digital e invariante de temperatura sanitaria ($\le 4.0^\circ\text{C}$).
3. **Producción y Fileteo:** Transformación del pescado entero en lomos limpios y rendimiento de masa ($45.0\% - 51.0\%$).
4. **Almacenamiento en Bodega:** Control de tinas isotérmicas estándar ($600.0 - 605.0\text{ kg}$) en cámara de frío ($\le -18.0^\circ\text{C}$).
5. **Despacho de Exportación:** Consolidación de contenedor reefer y evaluación de antigüedad de lote ($< 1.0\text{ mes}$).

---

## 2. Recursos de Referencia en la Carpeta `Docs/`

Antes de comenzar a programar, explora los documentos gráficos y técnicos disponibles en la carpeta `Docs/`:

| Archivo en `Docs/` | Tipo | Contenido y Utilidad |
| :--- | :--- | :--- |
| **`especificaciones_cadena_valor_atunera.html`** | Tablero Web Unificado | **¡Abre este archivo en tu navegador!** Contiene el resumen visual de los 5 eslabones con sus reglas matemáticas y la especificación de clases. |
| **`flujo_recepcion_pesca.jpg`** | Diagrama de Flujo (Grupo 0) | Fotografía del proceso físico de descarga en muelle, tolva y báscula. |
| **`slide_calidad.html`** y **`flujo_calidad.jpg`** | Diapositiva y Foto (Grupo 1) | Muestreo de temperatura con sonda y validación del umbral crítico de 4°C. |
| **`slide_produccion.html`** y **`flujo_produccion.jpg`** | Diapositiva y Foto (Grupo 2) | Proceso de fileteo, pesaje de lomos limpios y cálculo de rendimiento. |
| **`slide_bodega.html`** y **`flujo_bodega.jpg`** | Diapositiva y Foto (Grupo 3) | Llenado de tinas isotérmicas y condiciones de almacenamiento sub-cero. |
| **`slide_exportacion.html`** y **`flujo_exportacion.jpg`** | Diapositiva y Foto (Grupo 4) | Despacho internacional en contenedores refrigerados y control de fechas. |

---

## 3. Hoja de Ruta de Implementación (Paso a Paso)

### Fase 1: La Superclase Base (`src/EtapaProceso.java`) — 20 min
1. Abre `src/EtapaProceso.java`.
2. Observa los 4 atributos `protected`: `codigoLote`, `fecha`, `responsable`, `nombreEtapa`. ¿Por qué son `protected` y no `private`? Porque las subclases necesitan acceder directamente a ellos sin intermediarios.
3. Completa el constructor e implementa los métodos base `validarInvariante()` y `mostrarResumen()`.

### Fase 2: Las Subclases Especializadas (Eslabones 1 al 5) — 45 min
Implementa cada subclase extendiendo de `EtapaProceso`:
- **`RecepcionMuelle.java`**: Añade `nombreBarco`, `pesoBruto`, `taraCamion`. En el constructor invoca `super(...)`. Implementa `calcularPesoNeto()` y sobreescribe `validarInvariante()` y `mostrarResumen()`.
- **`ControlCalidad.java`**: Añade `temperaturaMuestra` y `codigoSonda`. Enlaza con `super(...)`. Sobreescribe `validarInvariante()` para que retorne `true` si $\text{temperatura} \le 4.0^\circ\text{C}$.
- **`ProduccionFileteo.java`**: Añade `pesoInicialPescado`, `pesoLomosObtenidos`, `pesoMerma`. Implementa `calcularRendimiento()` y sobreescribe `validarInvariante()` para evaluar el rango $45.0\% - 51.0\%$.
- **`AlmacenamientoBodega.java`**: Añade `codigoTina`, `pesoTinaKg`, `temperaturaCamara`. Sobreescribe `validarInvariante()` para comprobar peso entre $600.0 - 605.0\text{ kg}$ y temperatura $\le -18.0^\circ\text{C}$.
- **`DespachoExportacion.java`**: Añade `codigoContenedor`, `antiguedadMeses`, `paisDestino`. Sobreescribe `validarInvariante()` para comprobar que la antigüedad sea menor a $1.0\text{ mes}$.

### Fase 3: Orquestación Polimórfica en `Main.java` — 25 min
1. En `src/Main.java`, crea una lista polimórfica: `List<EtapaProceso> cadenaLote01 = new ArrayList<>();`.
2. Agrega una instancia de cada una de las 5 subclases con datos de prueba.
3. Recorre la lista con un bucle `for-each` invocando `etapa.mostrarResumen()` y evaluando `etapa.validarInvariante()`.
4. Observa cómo Java ejecuta automáticamente el método sobreescrito de la subclase real que se encuentre en memoria (despacho dinámico polimórfico).

---

## 4. Compilación y Ejecución

Puedes trabajar desde tu IDE favorito (VS Code, IntelliJ IDEA, NetBeans, Eclipse) o directamente desde la terminal:

```bash
# Compilar todos los archivos Java
javac -encoding utf-8 -d bin src/*.java

# Ejecutar el programa principal
java -cp bin Main
```

---

## 5. Lista de Autoevaluación

Antes de finalizar tu entrega, verifica los siguientes puntos:
- [ ] ¿Todas las 5 subclases utilizan la palabra clave `extends EtapaProceso`?
- [ ] ¿Todos los constructores de las subclases invocan `super(...)` como su primera instrucción?
- [ ] ¿Los métodos `validarInvariante()` y `mostrarResumen()` están anotados con `@Override`?
- [ ] ¿El programa compila limpiamente sin errores de sintaxis?
- [ ] ¿La clase `Main.java` itera una lista de tipo `List<EtapaProceso>` demostrando polimorfismo?
