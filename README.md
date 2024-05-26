
# PROJECTO FINAL AWS1: CIVILIZATION

Segundo proyecto del curso AWS1. 

https://github.com/Erikpr04/Projecte_Civilizations_23-24

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/civilization.jpg)

## Authors

- Erik Pinto: https://github.com/Erikpr04

- Erik Rojas: https://github.com/ErikRojaas

- William Molina: https://github.com/DarKoul-Wmg


## Description

Este proyecto consiste en crear un videojuego de gestión de recursos al mas puro estilo Civilization.

En él, nos enfrentaremos al desafío de gestionar recursos, desarrollar tecnologías, construir edificios, y crear un ejercito.

No todo será pacífico, ya que habrán enemigos que  intentarán conquistarnos, obligándonos a mantener nuestras defensas siempre listas para repeler estos ataques constantes. 

Este juego de estrategia te permitirá demostrar tus habilidades de gestión y planificación para asegurar la supervivencia y el crecimiento de tu civilización.


## Archivos

Los archivos como tal ya se encuentran descargados junto a este documento.

## Installation

Antes de poder disfrutar de este juego, es necesario un pequeño indice de todo lo necesario para que el juego funcione:

 - Eclipse con jdk21. 
 - Servidor Oracle o cliente SQLDeveloper


Si no tiene Servidor de Oracle, a continuación se deja un video para hacer la instalación: https://youtu.be/uoxd5oloEQY?si=GAMev1O34gqEWHm4

Para que todo sea más ameno, a continuación se dejan unos pasos para la puesta en marcha de todo:

### 1. Configuración Base de Datos

1. Script de la base de datos: 

En los archivos descargados, navega hasta la carpeta llamada M2 y abre el archivo llamado ScriptBD. Este archivo se encarga de crear la estructura necesaria para almacenar los datos del juego. 

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/local_script.PNG)

2. Crear User en la Base de Datos: 

Abrimos el cliente de la base de datos y nos vamos a la conexión system (por defecto tiene que haber una).Abrimos una hoja de trabajo y copiamos el comando para crear un nuevo usuario del SciptDB (Señalizado dentro del propio Script).

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/createuser.PNG)

3. Crear nueva Conexión:

Una vez hemos creado el usuario pulsamos el icono de la cruz verde y añadimos una nueva conexión. Los valores recomendados son los siguientes (El apartado detalles puede variar):

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/newconnection.PNG)

El usuario debe ser el que se ha creado anteriormente u otro válido. Además se recomienda guardar esta información para pasos futuros.

4. Crear la Base de Datos:

Una vez creada la conexión, copiamos y pegamos el script completo (las partes comentadas NO hay que ejecutarlas).

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/scriptbd.PNG)


### 2. Configuración Eclipse - Oracle

Y por ultimo pero no menos importante, en algunos casos es necesario cambiar algunos valores para establecer la conexión con la base de datos sin ningun problema.

Dentro de los archivos del juego (M3/P_Civilization_EWE), navega hasta la carpeta llamada interfaces, a continuación localiza el archivo Variables y abrelo con un editor de texto o código.

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/variables.PNG)

Dependiendo del tipo de servidor que tenemos, habrá que seleccionar una de las dos opciones:

- Si tu servidor Oracle NO ES local (maquina virtual o servicio como Azure): "jdbc:oracle:thin:@192.168.10.100:1521/orcl?serverTimezone=UTC&autoReconnect=true&useSSL=false"

- Si tu servidor Oracle ES local (SQLDeveloper): "jdbc:oracle:thin:@localhost:1521/XE?serverTimezone=UTC&autoReconnect=true&useSSL=false" (Por Defecto esta seleccionada esta).

[Si quieres usar otro usuario, tambien debes configurar el usuario y la password con tus credenciales].




### Paso extra 1 : Añadir el proyecto a Eclipse

Desde nuestro editor de codigo preferido vamos a la opcion:   

- [Open Projects From File System...]
Desde la ventana emergente, pulsamos el botón Directory y navegamos hasta el directorio del proyecto (la localización puede variar, normalmente esta donde lo hayas descargado).


Para que se importe bien, es recomendable, una vez dentro del directorio del proyecto, seleccionar la carpeta P_Civilization_EWE (dentro de M3).  

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/Capturaproject.PNG)


### Paso extra 2 : Driver para conectar Java - Oracle  

Una vez establecido como proyecto, es necesario añadir un componente extra, el archivo: ojdbc8-23.4.0.24.05

Este archivo lo encontrás adjunto en los archivos del proyecto (dentro de src), en caso contrario puedes descargarlo aqui: https://www.oracle.com/es/database/technologies/appdev/jdbc-downloads.html


Click derecho sobre el proyecto, entramos en la opción build path, add external archives. Navegamos hasta el driver y lo seleccionamos (la ubicación puede variar): 

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/add_ext_arch.png)


El resultado de este proceso seria este:

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/conectorpuesto.PNG)

Si el editor resalta un error, es posible que tengas que editar y configurar correctamente el path del archivo. Se puede acceder entrando en la opción: build path - configure build path:

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/configurebuildpath.PNG)

## Fin de la Instalación

Y listo, una vez hemos acabado esta breve configuración ya puedes disfrutar del juego. 

Ejecuta el archivo Main situado en la carpeta Main para empezar tu Civilización. 

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/main.png)


## Error con la resolución

Si su dispositivo experimenta un error a la hora de ejecutar la ventana del luego, debe cambiar el tamaño de fuente a 100%.
Para acceder a esa opción (Windows), en la barra de busqueda introduzca: cambiar la resolución de pantalla y seleccione la siguiente opción:

![](https://github.com/Erikpr04/Projecte_Civilizations_23-24/blob/Preproduccion/M5/imagenesREADME/resolucion.PNG)

