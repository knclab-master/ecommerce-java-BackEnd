Se puede probar la API REST tanto desde el frontend provisto (index.html) como con Postman.

El sistema está listo para funcionar localmente, usando la base de datos MySQL:

Se puede usar el respaldo adjunto (ecommerce_respaldo.sql) que incluye datos de ejemplo (productos, categorías, pedidos).
O bien partir de una base vacía, el backend creará las tablas automáticamente.

Funcionalidad principal


-ABM productos y categorías (CRUD).

-Asociación productos ↔ categorías.

-Gestión de pedidos: alta y consulta, con control de stock y validaciones de negocio.

-Respuestas 100% JSON, preparadas para consumo por frontend.

-CORS habilitado para probar desde el frontend estático.

Pasos mínimos para probar


-Configurar en /src/main/resources/application.properties

-Con el frontend (https://github.com/knclab-master/ecommerce-java-FrontEnd) según instrucciones. O directamente con Postman sobre los endpoints:
