#Frogmi Gson Test

##Objetivo
Mejorar la eficiencia de la obtención de información codificada en JSON y persistirla, dado que el algoritmo actual se demora más de 10 segundos por proceso.

- La salida debe obtenerse un 50% más rápido

##Caso de Prueba
Proceso 864: "SMU P.P Chequeo de Cajas Unimarc 2.0"

##Resultados
El tiempo que se tomó cada uno fue:
<table>
    <tr>
        <td> </td>
        <td>FrogmiGsonTest</td>
        <td>FrogmiTaskManager</td>
    </tr>
    <tr>
        <td>Tiempo [ms]</td>
        <td>5556</td>
        <td>15240</td>
    </tr>
</table>

Es decir, en este ejemplo se redujo un __63.5%__.

##Cómo probar

###Desde el proyecto [FrogmiGsonTest](https://github.com/calvarez-inzpiral/android_library_tests/tree/master/FrogmiGsonTest)
Éste es el proyecto de test de GSON. Los pasos a seguir son:

1. Conectarse directamente a la API: [presentationAsJson 864](http://www.frogmi.com/api/presentationAsJson?id=864)
2. Se mostrará en pantalla el tiempo que se tarda en parsear el JSON

###Desde el proyecto [FrogmiTaskManager](https://github.com/inzpiral/frogmi_taskmanager_android/tree/master/Frogmi2)
Éste es el proyecto oficial de Task Manager, el cual utiliza el Framework mobile de inzpiral. Los pasos a seguir son:

1. Cambiar la API donde se conecta para descargar tareas ubicada en:
    * __Archivo__: TaskManager/src/com/inzpiral/frogmi/taskmanager/views/MainScreenGroup.java
    * __Método__: "MainScreenGroup(Context context)" (Constructor)
    * __Variable__: mURL
2. Cambiar variable anterior por: [tarea_proceso_864.json](http://github.com/calvarez-inzpiral/android_library_tests/raw/master/FrogmiGsonTest/tarea_proceso_864.json?)
3. Ejecutar taskmanager y presionar el botón de descarga. En el logcat de android se mostrará el tiempo que se tardó en parsear el JSON con el Framework
