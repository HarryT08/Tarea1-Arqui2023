# School Management (Administración escolar)

School Management es una aplicación web que permite gestionar estudiantes dentro de un centro educativo. La aplicación permite crear ciclos escolares, registrar cursos dentro de los ciclos escolares, registrar materias a cursos, registrar horarios de clase, registrar y asignar maestros para las clases, registrar estudiantes dentro de cursos y llevar el control de notas. 

La aplicación cuenta valores que pueden cambiarse a nivel de configuración, como en la siguiente imagen **"Colegio Central"** y "**Para un mejor futuro"** son valores configurables que corresponden al nombre de la institución y al eslogan que utilicen. Además la aplicación cuenta con archivo de traducción en español, lo cual permite crear tus propios archivos de traducciones y tener la aplicación en varios idiomas de mantera automática. 

![School Management](http://rs.ronaldmirandaweb.com/img.png)

## Instalación
### Instalación de paquete de aplicación.
1. Descargar la [https://github.com/rmirandasv/school-management/releases/tag/0.0.9](última versión) del binario de School Management.
2. Descargar y ejecutar en tu base de datos MySQL el script install.sql
3. Modificar application.properties para agregar los datos de conexión a MySQL y configurar el nombde de la institución y su eslogan.
4. Iniciar la aplicación java -jar school-management*.jar
5. Ingresar a http://localhost:8080 (o el puerto que hayas configurado en application.properties) con usuario **manager** y contraseña **secret**. Al ingresar correctamente deberías ver una imagen como la siguiente

![School Management](http://rs.ronaldmirandaweb.com/img.png)

## Instalación para desarrolladores.
### Clonar el proyecto
`git clone git@github.com:rmirandasv/school-management.git`
### Instalar las dependencias Java
`cd school-management
./mvwn install`
### Instalar dependencias para modificar estilos de Bulmacss utilizando sass
Dentro del directorio school-management<br>
`cd src/main/resources && npm install`
### Iniciando aplicación
`./mvwn spring-boot:run`
### Compilando sass a css
En school-management/src/main/resources <br>
`npm run css-build`
### Compilar sass continuamente
`npm run css-watch`
