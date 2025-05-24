# Project Development Log

## Initial Setup - DDD Monorepo Structure for School Communication SaaS

### User Prompt 1
```
En el proyecto cursor-example encontrarás la base de un proyecto Java con SpringBoot. Quiero que realices las siguientes acciones: 
1. Crear un archivo markdown en la raíz del proyecto llamado prompt.md donde registres las interacciones dentro del proyecto. 
Mis prompts literalmente y un resumen de mis respuestas. 
2. Vamos a desarrollar usando DDD y un monorepositorio el backend para una SaaS que va a prestar servicios de comunicación entre padres, maestro y escueelas. 
Esta app permitirá gestionar a padres desde aplicaciones móviles y portal web, donde las colegios pueden publicar anuncios, manejar calendario de eventos, 
envío de circulares personales, notas de reuniones, gestión de excusas médicas, reuniones, boletines de notas, expediente de comportamiento del alumno como diferentes módulos. 
Este proyecto es solo el backend y tenemos un bounded context de backoffice para los colegios, schools para el portal al que acceden los padres y un proyecto principal que inicializa lo demás. 
3. Conceptos como eventos, calendario, circulares son dominios dentro de los bounded context y cada uno de ellos aplica clean architecture (infrastructure, application y domain) 
con puertos y adaptadores. Crea un boilerplate con estos.
```

### Assistant Response 1
Created a DDD-based monorepo structure with the following components:
1. Created prompt.md to document interactions
2. Restructured the project into a monorepo with:
   - Main application module (school-communication-app)
   - Backoffice bounded context
   - Schools bounded context
   Each bounded context follows clean architecture with:
   - Domain layer
   - Application layer
   - Infrastructure layer
   Using ports and adapters pattern

### User Prompt 2
```
Realiza los siguientes ajustes: 
1. En prompt.md actualiza el histórico de prompts (está incompleto). 
2. Cada proyecto de este monorepo debe a contener los domains de events, communications, boards y tickets. 
Cada uno de estos domains aplica clean architecture (infrastructure, application y domain como paquetes internos). 
3. Crea un domain de shared kernel como nuevo proyecto del monorepo. 
4. Ajusta para que los bounded context sean, schools, backoffices y apps. 
En apps está el código para inicializar el backend, todos los demás componentes son innecesarios.
```

### Assistant Response 2
Restructured the project to:
1. Update prompt.md with complete history
2. Add shared kernel module
3. Reorganize bounded contexts into:
   - apps (main application)
   - schools
   - backoffice
4. Add common domains to each bounded context:
   - events
   - communications
   - boards
   - tickets
   Each domain following clean architecture internally

### User Prompt 3
```
Ajusta para que ./src no contenga más código, genera estructura de test en todos los proyectos. Recuerda registrar el prompt
```

### Assistant Response 3
Reorganized project structure to:
1. Move all source code from ./src to appropriate module directories
2. Add test directories and structure to all modules
3. Update prompt.md with new interaction

### User Prompt 4
```
Elimina el código dentro de ./src. Nuestro único punto de entrada para inicializar una applicación debe estar en apps. Continúa registrando prompts
```

### Assistant Response 4
Cleaning up project structure to:
1. Remove all code from ./src directory
2. Ensure application entry point is only in apps module
3. Update prompt.md with new interaction