# Lab1 AREP

Escribir un servidor web que soporte múlltiples solicitudes seguidas no concurrentes. El servidor debe leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Construya una aplicación web con  javascript, css, e imágenes para probar su servidor. Incluya en la aplicación la comunicación asíncrona con unos servicios REST en el backend. NO use frameworks web como Spark o Spring, use solo Java y las librerías para manejo de la red.

## Comenzando

Se debe clonar el proyecto localmente con el comando:

` git clone https://github.com/lalaro/Lab1AREP.git`

Y luego revisar las intrucciones a continuación para el manejo de soluciones del proyecto.

El desarrollo del Laboratorio es el siguiente:

Como arquitectura tenemos:

![image22.jpeg](src%2Fmain%2Fresources%2Fimage22.jpeg)

Como se muestra en la imagen el servidor HTTP escucha en el puerto 35000, que actúa como el punto de entrada único para todas las solicitudes del cliente (navegador). A través de este puerto, el servidor maneja tanto solicitudes de archivos estáticos (como GET de estilo css, html, js o imagen (png/jpeg)) como solicitudes al backend REST. Para las solicitudes de archivos estáticos, el servidor accede al sistema de archivos local, recupera el recurso y lo devuelve en la respuesta HTTP; para las solicitudes REST, el servidor redirige la petición al módulo backend, 
el cual procesa la lógica y devuelve una respuesta en formato JSON, como {"message": "Hello"}. El usuario del servidor HTTP (navegador) solo interactúa con el puerto 35000, mientras que el backend REST opera internamente dentro del servidor HTTP, sin exponer un puerto adicional, centralizando así todas las comunicaciones en un único punto de entrada y simplificando la infraestructura.

1. Escriba un programa en el cual usted cree un objeto URL e imprima en pantalla cada uno de los datos que retornan los 8 metodos de la seccion anterior (getProtocol, getAuthority, getHost, getPort, getPath, getQuery, getFile, getRef.).
   
   En la clase URLParser.java:
   
   ![image1.jpeg](src%2Fmain%2Fresources%2Fimage1.jpeg)
   ![image2.jpeg](src%2Fmain%2Fresources%2Fimage2.jpeg)

2. Escriba una aplicacion browser que pregunte una direccion URL al usuario y que lea datos de esa direccion y que los almacene en un archivo con el nombre resultado.html. Luego intente ver este archivo en el navegador.
   
   En la clase URLReader.java:

   ![image3.jpeg](src%2Fmain%2Fresources%2Fimage3.jpeg)
   ![image4.jpeg](src%2Fmain%2Fresources%2Fimage4.jpeg)
   ![image5.jpeg](src%2Fmain%2Fresources%2Fimage5.jpeg)
   ![image6.jpeg](src%2Fmain%2Fresources%2Fimage6.jpeg)

3. Escriba un servidor web que soporte multiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo paginas html e imagenes.
   
   En la clase HTTPServer.java:

   Vamos a correr el archivo en el puerto 35000, por ejemplo http://localhost:35000/ghl.html?vl=8&t=5
   
   ![image7.jpeg](src%2Fmain%2Fresources%2Fimage7.jpeg)
   
   todos los archivos se van a ingresar o guardar por la ruta que se indica a continuación en requestedFile
   
   ![image8.jpeg](src%2Fmain%2Fresources%2Fimage8.jpeg)

   Se va tener la pagina inicial en paginanew

   ![image8.1.jpeg](src%2Fmain%2Fresources%2Fimage8.1.jpeg)
   
   El archivo predeterminado cada vez que se ingrese en el puerto será el siguiente: 
   
   ![image9.jpeg](src%2Fmain%2Fresources%2Fimage9.jpeg)
   ![image10.jpeg](src%2Fmain%2Fresources%2Fimage10.jpeg)
   
   Para poder entender los formatos de archivos entonces, agregamos cada uno; según lo que se requiera. El archivo que no encuentre en nuestro formato debería poder descargarlo localmente.
   
   ![image11.jpeg](src%2Fmain%2Fresources%2Fimage11.jpeg)

   La pagina principal, se verá así inicialmente
   
   ![image12.jpeg](src%2Fmain%2Fresources%2Fimage12.jpeg)

   Para archivo html:

   ![image13.jpeg](src%2Fmain%2Fresources%2Fimage13.jpeg)

   Para archivo css:
   
   ![image14.jpeg](src%2Fmain%2Fresources%2Fimage14.jpeg)

   Para archivo js:

   ![image15.jpeg](src%2Fmain%2Fresources%2Fimage15.jpeg)
   
   Para archivo png:

   ![image16.jpeg](src%2Fmain%2Fresources%2Fimage16.jpeg)

   Para otro tipo de archivo, se descargara localmente:

   ![image17.jpeg](src%2Fmain%2Fresources%2Fimage17.jpeg)


### Prerrequisitos

Se necesita de Maven (La más reciente) y Java 21, la instalación debe realizarse desde las paginas oficiales de cada programa.


### Instalación

Para Maven debe irse a https://maven.apache.org/download.cgi, descargar la versión más nueva que allá de Maven (En este caso tenemos la versión 3.9.6) y agregarse en la carpeta de Program Files, luego se hace la respectiva configuración de variables de entorno según la ubicación que tenemos para el archivo de instalación, tanto de MAVEN_HOME y de Path.
Luego revisamos que haya quedado bien configurado con el comando para Windows:

` mvn - v `
o
` mvn -version `

Para Java debe irse a https://www.oracle.com/java/technologies/downloads/?er=221886, descargar la versión 21 de Java y agregarse en la carpeta de Program Files, luego se hace la respectiva configuración de variables de entorno según la ubicación que tenemos para el archivo de instalación, tanto de JAVA_HOME y de Path.
Luego revisamos que haya quedado bien configurado con el comando para Windows:

` java -version `

## Ejecutando las pruebas

Podemos Abrir en terminal el proyecto y ejecutar las pruebas desde el PowerShell, en el caso de Windows. Y ejecutamos el comando:

` mvn test `

O de igual forma en el ID que deseemos.

Así se vera:

![image18.jpeg](src%2Fmain%2Fresources%2Fimage18.jpeg)

### Desglose en pruebas de extremo a extremo

1. testRootResponse
   Qué prueba: Esta prueba verifica que la ruta raíz (/) del servidor devuelva una respuesta con el estado "200 OK".
   Por qué la prueba: Es una prueba fundamental para asegurar que la ruta principal del servidor esté funcionando correctamente. Cualquier error aquí indicaría que el servidor no está respondiendo correctamente en su ruta más básica.
2. testHelloResponse
   Qué prueba: Verifica que la ruta /hello devuelva el mensaje esperado "Hello, world!".
   Por qué la prueba: Esta prueba valida que el servidor maneje correctamente una ruta específica y devuelva la respuesta adecuada. En este caso, se está comprobando que la ruta /hello funcione como se espera.
3. testSumResponse
   Qué prueba: Verifica que la ruta /sum?num1=5&num2=10 devuelva la suma correcta de los dos números, es decir, "15".
   Por qué la prueba: Este es un ejemplo de una prueba que valida el procesamiento de parámetros de consulta en una URL. El servidor debe manejar correctamente los parámetros y devolver el resultado correcto.
4. testEchoResponse
   Qué prueba: Verifica que la ruta /echo?msg=Hola devuelva el mismo mensaje "Hola" que recibe.
   Por qué la prueba: Esta prueba valida la funcionalidad básica de "echo" o "eco", que es un caso común de prueba en servidores. El servidor debe devolver el mismo mensaje que recibe como parte de la respuesta.
5. testNotFoundResponse
   Qué prueba: Verifica que una ruta inexistente (/ruta-inexistente) devuelva el código de estado "404 Not Found".
   Por qué la prueba: Esta es una prueba común para asegurar que el servidor maneje correctamente los casos en los que el recurso solicitado no existe, devolviendo el estado adecuado de error.
6. testServerResponseSimulation
   Qué prueba: Esta prueba simula una respuesta 404 del servidor. Verifica que cuando el código de respuesta es 404, el servidor devuelva "404 Not Found".
   Por qué la prueba: Es importante verificar que el servidor responda correctamente con el código de estado adecuado en función de la situación simulada. Aquí, se valida que el servidor maneje correctamente una solicitud que no tiene una ruta válida.
7. testServerResponse200
   Qué prueba: Similar a la prueba anterior, pero en este caso simula una respuesta con código 200 OK.
   Por qué la prueba: Asegura que el servidor pueda manejar una solicitud válida y devuelva la respuesta correcta, que en este caso es un código de éxito 200 OK.
8. testServerResponse500
   Qué prueba: Esta prueba simula una respuesta con código 500 Internal Server Error y verifica que el servidor devuelva el mensaje correcto.
   Por qué la prueba: Es importante verificar cómo el servidor maneja los errores internos. En este caso, el servidor debe devolver el estado de error adecuado cuando ocurre un problema interno.
9. testServerResponse301
   Qué prueba: Simula una respuesta con el código 301 Moved Permanently y verifica que el servidor lo devuelva correctamente.
   Por qué la prueba: Verifica que el servidor pueda manejar correctamente una redirección permanente (un cambio en la ubicación de un recurso). Esto es importante en aplicaciones web donde las URL pueden cambiar y los usuarios deben ser redirigidos.
10. testServerResponse403
    Qué prueba: Simula una respuesta con código 403 Forbidden y verifica que el servidor lo devuelva correctamente.
    Por qué la prueba: Esta prueba valida cómo el servidor maneja los intentos de acceso no autorizado o prohibido, como cuando el usuario no tiene permisos para acceder a un recurso.
11. testEchoCommunication
    Qué prueba: Esta es una prueba de integración que lanza un servidor EchoServer en un hilo y luego establece una conexión de cliente a ese servidor. El cliente envía un mensaje y el servidor debe devolverlo.
    Por qué la prueba: Esta prueba valida la comunicación entre un cliente y un servidor en tiempo real. Asegura que el servidor esté funcionando correctamente y pueda recibir y responder mensajes, lo que simula la interacción real con el servidor.
12. testURLParsing
    Qué prueba: Verifica que una URL específica se descomponga correctamente en sus diferentes componentes (protocolo, autoridad, host, puerto, ruta, consulta y referencia).
    Por qué la prueba: Esta prueba valida que el servidor maneje correctamente las URLs y pueda desglosarlas en sus partes constituyentes. Esto es crucial para los servidores que necesitan trabajar con rutas y parámetros en las URLs.

### Y pruebas de estilo de código

Las pruebas son variadas y cubren diferentes aspectos delservidor web y la interacción del cliente. En primer lugar, se prueban rutas específicas para asegurar que devuelvan las respuestas correctas, como "200 OK", "404 Not Found" o "500 Internal Server Error". 
Además, algunas pruebas validan cómo se manejan los parámetros en la URL, como la suma de dos números o la funcionalidad del programa. También se verifica la comunicación entre el cliente y el servidor, asegurando que esta interacción funcione correctamente. 
Finalmente, se simulan errores comunes de servidor para asegurar que se devuelvan las respuestas adecuadas cuando ocurren problemas. Estas pruebas ayudan a verificar que la aplicación maneje correctamente tanto las rutas y respuestas estándar como los posibles errores y la integración entre los diferentes componentes del sistema.


## Despliegue

Podemos Abrir en terminal el proyecto y compilar y empaquetar el proyecto desde el PowerShell, en el caso de Windows. Y ejecutamos los comandos:

` mvn clean `

` mvn compile `

` mvn package `

O de igual forma en el ID que deseemos.

Así se vera:

![image19.jpeg](src%2Fmain%2Fresources%2Fimage19.jpeg)
![image20.jpeg](src%2Fmain%2Fresources%2Fimage20.jpeg)
![image21.jpeg](src%2Fmain%2Fresources%2Fimage21.jpeg)

## Construido con

* [Maven](https://maven.apache.org/) - Gestión de dependencias.
* [Java](https://www.java.com/es/) - Versionamiento en Java.

## Contribuyendo

Por favor, lee [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) para detalles sobre nuestro código de conducta y el proceso para enviarnos solicitudes de cambios (*pull requests*).

## Versionado

Usamos [SemVer](http://semver.org/) para el versionado.

## Autores

* **Laura Valentina Rodríguez Ortegón** - *Lab1 AREP* - [Repositorio](https://github.com/lalaro)

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Reconocimientos

* Agradecimientos a la Escuela Colombiana de Ingeniería
* La documentación de Git Hub
* Al profesor Luis Daniel Benavides
