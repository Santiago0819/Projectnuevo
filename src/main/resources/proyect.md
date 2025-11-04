Sistema de Gestión de Reservas y Recursos para un Centro Deportivo Resumen:

Desarrolle una aplicación REST en Java (versión > 23) que gestione reservas, usuarios, instalaciones, equipos y servicios de un centro deportivo. La aplicación debe implementarse con Spring Boot y Lombok, persistir los datos en archivos CSV (uno por entidad) y demostrar los conceptos de POO: encapsulamiento, herencia, polimorfismo, interfaces, composición, agregación, records, enums y clases abstractas. Debe proveer un CRUD mínimo para 10 clases distintas expuestas mediante una API REST documentada.

Requisitos funcionales (qué debe hacer)

Entidades principales (mínimo 10 clases): por ejemplo:
Usuario (abstracta o clase base)
Socio (hereda de Usuario)
Empleado (hereda de Usuario)
Instalacion (pista, cancha, sala)
Equipo (por ejemplo, raquetas, pelotas)
Servicio (clases de entrenamiento, fisioterapia)
Reserva
Factura
Pago (o Transaccion)
Notificacion (o Historial)

puede añadir otras como CategoriaInstalacion, Promocion, Horario, etc., hasta completar al menos 10 clases.
CRUD: Implementar Create, Read, Update, Delete para cada entidad principal a través de endpoints REST (/usuarios, /reservas, etc.).
Relaciones y composición/aggregación:
Reserva debe componer o agregar Instalacion, Usuario y opcionalmente Equipo.
Instalacion puede tener una colección de Equipo (agregación).
Factura debe referenciar Reserva y Pago.

Persistencia:
Guardar y leer datos desde archivos CSV (uno por entidad).
Diseñar formato CSV coherente (encabezados, id único, relaciones referenciadas por id).
Implementar una capa de acceso a datos (p. ej. CsvRepository<T>) que maneje I/O, parseo y sincronización en memoria.

POO avanzado:
Usar herencia (ej. Socio y Empleado extienden Usuario).
Usar polimorfismo (métodos sobrecargados/overridden para calcular descuentos, tarifas, etc.).
Usar interfaces para definir comportamiento (ej. Notificable, Pagable).
Implementar clases abstractas donde tenga sentido (ej. Usuario abstracta).
Incluir al menos un record (por ejemplo para DTOs inmutables o para un Direccion/Horario).
Usar enum para estados o tipos (ej. TipoInstalacion, EstadoReserva, MetodoPago).
Mostrar encapsulamiento (campos privados + getters/setters o lombok) y composición/aggregación clara entre clases.

Validaciones y reglas de negocio:
No permitir reservas solapadas para la misma instalación.
Limitar número máximo de equipos prestados por socio.
Calcular tarifas según tipo de usuario, hora, promociones.
Rechazar operaciones inválidas con respuestas HTTP y mensajes claros.

API:
Diseñar endpoints REST bien nombrados (GET, POST, PUT/PATCH, DELETE).
Soportar paginación/búsqueda básica en listados.
Proveer endpoints para operaciones específicas (ej. /reservas/{id}/cancelar, /usuarios/{id}/historial).

Documentación y pruebas:
Documentar la API (Swagger/OpenAPI preferible).
Incluir pruebas unitarias y/o de integración para las reglas críticas (mínimo 8 tests).
Incluir README con instrucciones de compilación, ejecución y ejemplos de uso.

Manejo de concurrencia / seguridad básica:
Evitar condiciones de carrera al escribir archivos CSV (sincronización o bloqueo).
Autenticación simple (token estático o basic auth) no es obligatoria pero suma puntos.

Requisitos técnicos (cómo debe hacerse)

Lenguaje: Java superior a la versión 23.
Frameworks: Spring Boot (REST), Lombok (para reducir boilerplate).
Persistencia: Archivos CSV (una carpeta data/ con archivos por entidad).
Estructura: Capas claras: controller → service → repository (CSV).
DTOs: Separar entidad interna de DTOs para la API; use record al menos para un DTO.
Manejo de excepciones: Global exception handler para respuestas JSON con código y mensaje.
Build/Run: Usar Maven o Gradle; incluir scripts o comandos en README.
Formato CSV: incluir ejemplo de cada CSV con encabezados; por ejemplo:
Logging: Usar logging con niveles (info, warn, error).
Entregables

Código fuente en un repositorio (Git) con historial mínimo (commits significativos).
README.md con:
Requisitos (JDK, Maven/Gradle).
Cómo compilar y ejecutar.
Endpoints principales y ejemplos curl.
Formato de los CSV y ruta data/.

Archivo data/ con CSV de ejemplo poblados (mínimo 3-5 registros por entidad).
Documentación de la API (Swagger UI o openapi.yaml).
Tests automatizados.
Un documento corto (1-2 páginas) que explique cómo su diseño evidencia:
Encapsulamiento, herencia, polimorfismo, interfaces, composición, agregación, uso de record, enum, clases abstractas.

(Opcional) Diagrama UML simplificado.

Criterios de evaluación (rubrica sugerida)

Funcionalidad (40%): CRUDs implementados, persistencia CSV, reglas de negocio (no solapamiento), endpoints operativos.
Diseño orientado a objetos (25%): Uso correcto y claro de herencia, polimorfismo, interfaces, records, enums, clases abstractas, composición/aggregación y encapsulamiento.
Calidad del código y arquitectura (15%): Capas separadas, uso de Lombok correctamente, manejo de excepciones y logging.
Documentación y pruebas (15%): README claro, API documentada, tests presentes y passing.
Extras (5%): Manejo de concurrencia al escribir CSV, autenticación simple, diagrama UML, UX en mensajes de error.

Restricciones / Pistas útiles

Evite bases de datos SQL/NoSQL — la persistencia debe ser en CSV. Pueden cargar todo en memoria al inicio y sincronizar con los CSV al modificar, siempre cuidando consistencia.
Para parseo CSV puede usar librerías (OpenCSV, Apache Commons CSV) o implementar su propio parser.
Use Lombok (@Data, @Builder, @Value, @RequiredArgsConstructor) para reducir código repetitivo.
Use record para DTOs de respuesta inmutables o para tipos que no necesiten comportamiento adicional.
Documente qué archivos CSV contienen relaciones por id y cómo se resuelven (ej. reserva tiene usuarioId, instalacionId).