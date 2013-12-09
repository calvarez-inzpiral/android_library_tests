#Frogmi Gson Test

##Objetivo
Mejorar la eficiencia de la obtención de información codificada en JSON y persistirla, dado que el algoritmo actiual se demora más de 10 segundos por proceso.

- Se debe obtener la misma salida que el algoritmo actual
- La salida debe obtenerse un 50% más rápido

##Caso de Prueba
Proceso 864: "SMU P.P Chequeo de Cajas Unimarc 2.0"

## Resultados


##Cómo probar

###Desde el proyecto [FrogmiGsontest](https://github.com/calvarez-inzpiral/android_library_tests/tree/master/FrogmiGsonTest)

Éste es el proyecto de test de GSON. Los pasos a seguir son:
1. Conectarse directamente a la API: [presentationAsJson 864](http://www.frogmi.com/api/presentationAsJson?id=864)
2. Se mostrará en pantalla el tiempo que se tarda en parsear el JSON

###Desde el proyecto [FrogmiTaskManager](https://github.com/inzpiral/frogmi_taskmanager_android/tree/master/Frogmi2)

Éste es el proyecto oficial de Task Manager, el cual utiliza el Framework mobile de inzpiral. Los pasos a seguir son:
1. Cambiar la API donde se conecta para descargar tareas ubicada en:
		Archivo: TaskManager/src/com/inzpiral/frogmi/taskmanager/views/MainScreenGroup.java
		Método: "MainScreenGroup(Context context)" (Constructor)
		Variable: mURL
2. Cambiar variable anterior por: [tarea_proceso_864.json](http://github.com/calvarez-inzpiral/android_library_tests/raw/master/FrogmiGsonTest/tarea_proceso_864.json)
3. Ejecutar taskmanager y presionar el botón de descarga. En el logcat de android se mostrará el tiempo que se tardó en parsear el JSON con el Framework