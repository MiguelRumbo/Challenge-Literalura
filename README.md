# Literatura - Aplicación de Gestión de Libros

Este proyecto es una aplicación de gestión de libros que se conecta a la API de [Gutendex](https://gutendex.com/), una API abierta para acceder a los libros de dominio público de Project Gutenberg. La aplicación permite realizar búsquedas de libros y guarda las búsquedas realizadas en la base de datos.

## Características

- **Búsqueda de libros**: Los usuarios pueden buscar libros por término y visualizar los resultados.
- **Registra las búsquedas**: Cada búsqueda realizada se guarda en la base de datos para futuras referencias.
- **Gestión de autores**: Los autores relacionados con los libros se guardan en la base de datos.
  
## Tecnologías

- **Java**: Lenguaje de programación.
- **Spring Boot**: Framework utilizado para el backend.
- **JPA (Java Persistence API)**: Para el manejo de la base de datos.
- **H2 Database**: Base de datos en memoria para pruebas.
- **Gutendex API**: API externa para acceder a los libros de dominio público.
  
## Requisitos

- Java 11 o superior.
- Maven o Gradle (para la construcción del proyecto).
  
## Instalación

1. Clona este repositorio:

    ```bash
    git clone https://github.com/tu-usuario/literatura.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd literatura
    ```

3. Compila el proyecto usando Maven o Gradle. Para Maven:

    ```bash
    mvn clean install
    ```

4. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

5. La aplicación debería estar disponible en [http://localhost:8080](http://localhost:8080).

## Uso

- Al acceder a la aplicación, puedes realizar búsquedas de libros utilizando el término de búsqueda.
- Cada búsqueda se guarda en la tabla `search_history` de la base de datos.

## Estructura del Proyecto

- **com.alura.literatura.model**: Contiene las clases de modelo, como `Author`, `Book` y `SearchHistory`.
- **com.alura.literatura.repository**: Contiene las interfaces de repositorio para interactuar con la base de datos.
- **com.alura.literatura.service**: Contiene la lógica del negocio, incluida la integración con la API de Gutendex.
- **com.alura.literatura.controller**: Controladores para manejar las peticiones HTTP (si aplicable).

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`).
3. Haz tus cambios y confirma (`git commit -am 'Añadir nueva característica'`).
4. Empuja tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un pull request.

## Autores

- **Tu nombre** - *Desarrollador Principal* - [TuUsuario](https://github.com/tu-usuario)

## Agradecimientos

- [Gutendex](https://gutendex.com/) por proporcionar una API excelente para acceder a libros de dominio público.
- [Spring Boot](https://spring.io/projects/spring-boot) para facilitar la creación de aplicaciones Java.
- [H2 Database](https://www.h2database.com/html/main.html) para la base de datos en memoria.

