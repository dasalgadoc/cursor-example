# Project Development Log

## Initial Setup - DDD Monorepo Structure for School Communication SaaS

### User Prompt
```
En el proyecto cursor-example encontrarás la base de un proyecto Java con SpringBoot. Quiero que realices las siguientes acciones: 
1. Crear un archivo markdown en la raíz del proyecto llamado prompt.md donde registres las interacciones dentro del proyecto. 
Mis prompts literalmente y un resumen de tus respuestas. 
2. Vamos a desarrollar usando DDD y un monorepositorio el backend para una SaaS que va a prestar servicios de comunicación entre padres, maestro y escueelas. 
Esta app permitirá gestionar a padres desde aplicaciones móviles y portal web, donde las colegios pueden publicar anuncios, manejar calendario de eventos, 
envío de circulares personales, notas de reuniones, gestión de excusas médicas, reuniones, boletines de notas, expediente de comportamiento del alumno como diferentes módulos. 
Este proyecto es solo el backend y tenemos un bounded context de backoffice para los colegios, schools para el portal al que acceden los padres y un proyecto principal que inicializa lo demás. 
3. Conceptos como eventos, calendario, circulares son dominios dentro de los bounded context y cada uno de ellos aplica clean architecture (infrastructure, application y domain) 
con puertos y adaptadores. Crea un boilerplate con estos.
```

### Assistant Response
Creating a DDD-based monorepo structure with the following components:
1. Created prompt.md to document interactions
2. Planning to restructure the project into a monorepo with:
   - Main application module
   - Backoffice bounded context
   - Schools bounded context
   Each bounded context will follow clean architecture with:
   - Domain layer
   - Application layer
   - Infrastructure layer
   Using ports and adapters pattern 