<h1 align="center">School Management<a href="https://www.producthunt.com/posts/awesome-github-profiles?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-awesome-github-profiles" target="_blank"><img src="https://i.ibb.co/H4WWWZN/logo.png" alt="Awesome GitHub Profiles - Best curated list of developers readme, updated every 15 min | Product Hunt" style="width: 200px; height: 144px;" width="200" height="184" /></a></h1>
<div align="center">
<img src="https://img.shields.io/badge/Autor-Rmirandasv-2ea44f?logo=Visual+Studio+Code&logoColor=007ACC" alt="Awesome Badge"/>
<img src="https://img.shields.io/badge/Backend-Spring_Boot-2ea44f?logo=Spring+Boot&logoColor=6DB33F" alt="Autores"/>
<img src="https://img.shields.io/badge/Front_End-HTML5-E34F26?logo=HTML5&logoColor=E34F26" alt="Google Chrome Extension" /> 
<br>
</div>
<br>
School Management  es una aplicación web que te permite gestionar los alumnos de un centro educativo. Esta aplicacion se caracteriza por crear ciclos escolares, Inscribir materias, planificar horarios de clase, asignar maestros a las clases, generar control y seguimiento a las calificaciones, entre otras ventajas

![alusion](https://cdn.discordapp.com/attachments/745748087880024127/1085738105660788786/image.png)
>Pagina principal de School Management

Ademas de gestionar un sistema de informacion de gestion y administracion academica, se caracteriza por tener 2 lenguajes disponibles:  Ingles & Español, a continuacion algunas capturas de las interfaces graficas del proyecto:
|                                                                                    Pagina Principal                                                                                    |                                                                                   Lista de usuarios del Sistema                                                                                   |                                                                                Listado de Cursos                                                                                |                                                                             Matriz de Ciclo Escolares                                                                              |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://cdn.discordapp.com/attachments/745748087880024127/1085738105660788786/image.png" title="Pagina Principal" width="100%" crossorigin> | <img src="https://cdn.discordapp.com/attachments/745748087880024127/1085738202368847922/image.png" title="Lista de usuarios del Sistema" width="100%" crossorigin> | <img src="https://cdn.discordapp.com/attachments/745748087880024127/1085738272220790844/image.png" title="Listado de Cursos" width="100%" crossorigin> | <img src="https://cdn.discordapp.com/attachments/745748087880024127/1085738334090969088/image.png" title="Matriz de Ciclo Escolares" width="100%" crossorigin> |


## Contenido del repositorio
- [Instalacion](#instalacion)
    + [Usuario Comun](#usuario_comun)
    + [Usuario Desarrollador](#usuario_dev)
 - [Uso](#ussage)
 - [Creditos](#creditos)

<img src="https://cdn-icons-png.flaticon.com/512/4961/4961639.png" align="right" width="44px" />


## Instalacion
### usuario_comun
Es un gusto que te interese ejecutar el proyecto :smiley:, a continuación se describe una serie de pasos a tener en cuenta para la instalación local del proyecto:
- :green_heart: Descarga el proyecto desde la sección de Code presionando en Download Zip y/o si tienes instalado [Git](https://git-scm.com/download/win), abre una nueva instancia de la consola de comando y pega la siguiente línea de código: <code>git clone https://github.com/rmirandasv/school-management.git</code>

![alusión](https://sites.northwestern.edu/researchcomputing/files/2021/05/github.png)
>Descarga directa del proyecto

- :green_heart: Descarga [MySQL](https://www.mysql.com/), configure y a través de MysqlWorkbrench importe el script de la base de datos denominada: <code>install.sql</code>

- :green_heart: Modificamos el fichero *application.properties* con la configuración de conexión a servidor de la base de datos de MySql, opcionalmente puedes modificar personalizadamente las variables de nombre de institución y logo oficial del instituto educativo

- :green_heart: Ejecuta la aplicación en [Java](https://www.java.com/es/) con la siguiente línea: <code>java -jar school-management*.jar</code>

- :green_heart: Finalmente,  nuestro proyecto correrá en la dirección <code>http://localhost:8080</code> (Siempre y cuando el puerto no este ocupado por otro programa), donde encontraremos la interfaz inicial del programa 

![Interfaz Inicial](https://cdn.discordapp.com/attachments/745748087880024127/1085738105660788786/image.png)
> Pagina inicial del proyecto

### usuario_dev
Felicidades, gran programador, es un gusto que hayas aceptado este reto :neckbeard:, a continuación una, guia con pasos similares para elaboración, desarrollo y ejecución del proyecto
- :blue_heart: Descarga el proyecto desde la sección de Code presionando en Download Zip y/o si tienes instalado [Git](https://git-scm.com/download/win), abre una nueva instancia de la consola de comando y pega la siguiente línea de código: <code>git clone https://github.com/rmirandasv/school-management.git</code>

![alusión](https://sites.northwestern.edu/researchcomputing/files/2021/05/github.png)
>Descarga directa del proyecto

- :blue_heart: Descarga [MySQL](https://www.mysql.com/), configure y a través de MysqlWorkbrench importe el script de la base de datos denominada: <code>install.sql</code>

- :blue_heart: Modificamos el fichero *application.properties* con la configuración de conexión a servidor de la base de datos de MySql, opcionalmente puedes modificar personalizadamente las variables de nombre de institución y logo oficial del instituto educativo

- :blue_heart: Instalamos las dependencias necesarias de Java y Bulmas a través de los siguientes comandos
   <code>cd school-management ./mvwn install</code>
   <code>cd src/main/resources && npm install</code>
   > psdatta: La segunda línea de código debe ejecutar dentro del directorio del proyecto


![Interfaz Inicial](https://repository-images.githubusercontent.com/24268127/6b76a23f-40d7-4c68-ad10-fe2abc404f66)
> Es necesario disponer de Node Package Manager [NPM](https://www.npmjs.com/) puedes descargarlo tocando el enlace anterior la version 14.0.0


<img src="https://www.cursor.cl/wp-content/uploads/2022/05/03home-if.png" align="right" width="44px" />



## ussage

Una vez ejecutado el proyecto nos llevará a la zona de inicio de sesión del proyecto, esta interfaz la vemos a continuación:
![Interfaz Inicial](https://cdn.discordapp.com/attachments/745748087880024127/1085769828008218735/image.png)
> Login del Proyecto

Las credenciales de administrador del Sistema son las siguientes:
| **Usuario** | **Clave**  |
|---------|--------|
| Manager | Secret |


<img src="https://cdn-icons-png.flaticon.com/512/2905/2905138.png" align="right" width="44px" />

## Arquitectura


### Actores 
Este Sistema web se caracteriza por la colaboración entre diversos agentes dentro de su ámbito de desarrollo, estas personas juegan un rol importante del sistema, los roles del presente proyecto son:
![Actores](https://i.ibb.co/LJ4tKF0/actores.png)
> Actores del Sistema

### Funcionalidades del Sistema
#### Requerimientos Funcionales
| #RF | Descripción                                                                                                                                                                     |
|-----|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | Un usuario puede registrarse en la plataforma con rol de Estudiante y/o Docente                                                                                                 |
| 2   | Un usuario con rol de Estudiante/Docente inicia sesión en la plataforma a través de sus credenciales válidas                                                                    |
| 3   | Un usuario con rol estudiante puede matricular materias en un curso académico                                                                                                   |
| 4   | Un usuario con rol estudiante participa sus clases dentro de un ciclo académico                                                                                                 |
| 5   | Un usuario con rol estudiante planifica sus materias a través de un horario de clases                                                                                           |
| 6   | Un usuario con rol docente dicta un grupo de materias                                                                                                                           |
| 7   | Un usuario con rol docente puede ser responsable de elaborar su docencia entre 1 a muchos cursos en la academia escolar                                                         |
| 8   | Un usuario con rol docente evalúa a sus estudiantes generándole una calificación en la materia cursada y dictada                                                                 |
| 9   | Un usuario con rol administrador tiene control, gestión y accesos a todos los usuarios del sistema con la capacidad de agregar, actualizar, modificar o eliminar dichos usuarios |

#### Requerimientos no funcionales
| #RNF | Descripcion                                                                        |
|------|------------------------------------------------------------------------------------|
| 1    | El aplicativo debe permitir la integridad y seguridad de los datos de los usuarios |
| 2    | El aplicativo debe ser adaptables a las diferentes resoluciones de Pantalla        |
| 3    | El aplicativo debe ejecutarse con tiempo de respuesta no mayores a 3 segundos      |

## Mejoras, Cambio y actualizaciones del Proyecto
### Problema

El proyecto muestra una dificultad con los mensajes de confirmación, advertencia o error, debido a que muestran mensaje por alertas en Javascript, un ejemplo se evidencia a continuación:
![Actores](https://i.ibb.co/G221qbQ/Ejemplo-de-alert-del-Sistema.png)
> Una alegoria respecto a los mensaje de alerta del proyecto

Crudos, ambiguos hasta considerarse básicos, puesto que actualmente existen tecnologías que aportan en la construcción y mejora de estas ventanas emergentes, una de las librerías más destacadas es Sweet Alert, genera interfaces profesionales y amigables para la comunicación con los usuarios
![Actores](http://sweetalert2.github.io/images/sweetalert2-social.png)
> Sweet Alert

Convertimos esos aburridos mensajes básicos y planos en algo más agradable al usuario final, aquí algunos ejemplos:
![Actores](https://cdn.discordapp.com/attachments/745748087880024127/1085782504100859944/image.png)
> Errores en las credenciales de usuario renovado

![Actores](https://cdn.discordapp.com/attachments/745748087880024127/1085782597025665085/image.png)
> Sesion iniciada correctamente




## Evidencia de video de modificación y despliegue del código
[Comprehensive Markdown Crash Course](https://youtu.be/8qgFcPjb7gI)
> Video de modificacion de Sweet Alert
## Institución Académica 🏫
Aplicativo base de un proyecto existente con pequeñas modificaciones para la materia de arquitectura de software [Programa de Ingeniería de Sistemas](<https://ingsistemas.cloud.ufps.edu.co/>) de la [Universidad Francisco de Paula Santander](<https://ww2.ufps.edu.co/>).

## Autores ✒️
Proyecto desarrollado por:
- Santiago Felipe Alferez Villamizar (**santiagofelipeavil@ufps.edu.co**)
- Carlos Eduardo Contreras Mendoza (**carloseduardocmen@ufps.efu.co**)
- Harold Rueda Antolinez (**haroldrant@ufps.edu.co**)
- Oscar Ivan Bayona Diaz (**oscarivanbdia@ufps.edu.co**)
- Marlon Stiven Prado (**marlonstivenprod@ufps.edu.co**)
