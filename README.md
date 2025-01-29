# Lab1 AREP

Escribir un servidor web que soporte múlltiples solicitudes seguidas no concurrentes. El servidor debe leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Construya una aplicación web con  javascript, css, e imágenes para probar su servidor. Incluya en la aplicación la comunicación asíncrona con unos servicios REST en el backend. NO use frameworks web como Spark o Spring, use solo Java y las librerías para manejo de la red.

## Comenzando

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas. Consulta la sección de despliegue para obtener notas sobre cómo implementar el proyecto en un sistema en vivo.

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

Explica cómo ejecutar las pruebas automatizadas para este sistema.

### Desglose en pruebas de extremo a extremo

Explica qué prueban estas pruebas y por qué.


### Y pruebas de estilo de código

Explica qué prueban estas pruebas y por qué.



## Despliegue

Agrega notas adicionales sobre cómo implementar esto en un sistema en vivo.

## Construido con

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - El framework web utilizado.
* [Maven](https://maven.apache.org/) - Gestión de dependencias.
* [ROME](https://rometools.github.io/rome/) - Utilizado para generar feeds RSS.

## Contribuyendo

Por favor, lee [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) para detalles sobre nuestro código de conducta y el proceso para enviarnos solicitudes de cambios (*pull requests*).

## Versionado

Usamos [SemVer](http://semver.org/) para el versionado. Para las versiones disponibles, consulta los [tags en este repositorio](https://github.com/your/project/tags).

## Autores

* **Laura Valentina Rodríguez Ortegón** - *Lab1 AREP* - [Repositorio](https://github.com/lalaro)

Consulta también la lista de [colaboradores](https://github.com/your/project/contributors) que participaron en este proyecto.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Reconocimientos

* Agradecimientos a cualquiera cuyo código fue utilizado
* Inspiración
* Etc
