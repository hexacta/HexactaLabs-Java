# Hexacta E-Bookstore - Hexacta Labs
===================
## Objetivo de la Aplicación 

Servir de Template para otros proyectos que utilicen tecnologías AngularJS, Bootstrap (frontend), Hibernate y Spring (backend). 

## Requisitos Previos

* Tener [Eclipse instalado](https://wiki.hexacta.com/mediawiki/index.php/HAT:IDE_Eclipse-Hexacta), con el pluggin de Jetty. 
* Tener [Maven instalado](https://wiki.hexacta.com/mediawiki/index.php/HAT:Introducción_a_Maven), recomendado tener el pluggin de maven en el eclipse. 
* Tener [Git instalado](https://wiki.hexacta.com/mediawiki/index.php/HAT:GIT). 
* Tener MySql instalado, recomendado tener instalado el [workbench](http://dev.mysql.com/downloads/workbench/) para poder visualizar los cambios producidos en la base de datos. Recomendamos no cambiar los valores default de user y password (root:root). 

## Instrucciones de Instalación y Ejecución

Clonar el repositorio git: https://github.com/hexacta/HexactaLabs-Java.git (puede tardar un rato): 
<code>
<pre>git clone https://github.com/hexacta/HexactaLabs-Java.git</pre>
</code>
Abrir Eclipse, e importar el proyecto (que se encuentra en "HexactaLabs-Java\dev\Tpl") al workspace. 
En caso de necesitar instalar alguna de las dependencias, en una consola (sobre el directorio del proyecto, en donde se encuentre el archivo pom.xml), hacer:
<code>
<pre>mvn eclipse:eclipse</pre>
</code>

En caso de tener otro usuario y/o contraseña configurados en el MySql a los mencionados antes, es necesario cambiar la configuración del proyecto. Para ésto, es necesario ir, dentro del proyecto, a: "Tpl\src\main\webapp\WEB-INF\constant.properties". Dentro de este archivo, cambiar los valores de los parámetros de **db.username** y **db.password** a los valores correspondientes. 

Ir al workbench, y crear una nueva conexión, con nombre **hexactalabs**, y dejar el resto de los parámetros por defecto. Recomendamos que antes de terminar la creación, testear la conexión. Entrar a la conexión, y crear un nuevo Schema, con el nombre **hexactalabs**, con Server Default. No es necesario crear las tablas, cuando se ejecute el servidor, el mismo Hibernate se encargará de hacerlo. 

Para ejecutar el servidor, sobre el proyecto click derecho, *Run As &gt; Run Jetty*. 

Para poder utilizar la aplicación cliente, en un browser acceder a: [http://localhost:8080/Tpl](http://localhost:8080/Tpl).

## Estado actual de la aplicación
Hoy en día la aplicación se encuentra en un estado de desarrollo, tiene implementados los siguientes servicios: 

####Del Backend:
* Las clases del modelo están creadas
* Los DAOs y su unión con Hibernate.
* Los webservices con sus respectivos servicios: 
  * Books: crear, cargar, editar, eliminar.
  * Comments: crear, borrar, editar, cargar y cargar por usuario.
  * Login: login.
  * Users: registrar, eliminar y editar.
  * Categories: cargar las categorias de libros.
  * Loans: cargar todos los préstamos, cargar un préstamos, cargar todos los préstamos de un libro, crear un préstamos, actualizar un préstamo, eliminar un préstamo.
  * Copies: Cargar todas las copias, y cargar copias por libro.


####Del Frontend:
* Pantalla de inicio que permite visualizar todos los libros disponibles, pudiendo acceder a los comentarios. En caso de iniciar sesión (por defecto, se puede hacer con usuario: '''admin''', password: '''admin'''), se pueden crear comentarios, y se permite acceder a la pantalla de edición de libro, creación de libro, eliminación de libro y préstamos del libro.
* Pantalla de edición y creación de libro, con todos los campos necesarios para la creación del libro.
* Modal de comentarios: permite visualizar los comentarios de un libro e información adicional del mismo (como una imagen cargada de la portada del libro), y en caso de haber iniciado sesión, permite crear un comentario del libro.
* Pantalla de Préstamos: que permite seleccionar un usuario al cual se le creará el préstamo entre las fechas indicadas.
* Pantalla de registración de usuario: para crear un nuevo usuario.
