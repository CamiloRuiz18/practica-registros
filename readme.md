PROCESOS

1) Inicializacion de sprint boot

Proceso: Empezamos iniciando un nuevo proyecto maven con sprint boot desde la terminal, cosas que tenemos que tener en cuenta son...la version que vamos a trabajar en este caso 21, el tipo de lenguaje que trabajaremos, nos da tres opciones en este caso elegimos JAVA, el .com que se llevara el proyecto que normalmente viene siendo el mismo nombre del proyecto o asi lo he trabajado, las dependencias que instalaremos al momento de iniciar que seran 3: "Sprint Web" , "Sprint Data JPA" Y "MySQL Driver" que son las principales y despues de iniciado manualmente tenemos que realizar la conexion con la base de datos

2) Creamos la base de datos que manejaremos para poder conectarla, esta conexion se hace a traves del archivi "application.properties"
3) Verificamos que la conexion se haya echo correctamente, para esto vamos a realizar dos pasos como prueba:
    - En el archivo de conexion vamos a dejar una opcion sin rellenar ya sea Url o Password e iniciamos el sprint (arrojara error)
    - Luego ingresamos las credeciales correctas e inicializamos (deberia iniciar bien hasta el momento)
4) Enlazamos con gitHub:

Tenemos que crear primero el portafolio donde queremos guardar en gitHub manualmente y luego seguimos este camino de comandos para conectar el proyecto a git

git initDéjameNo seas atrevida
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/CamiloRuiz18/practica-registros.git
git push -u origin main


El recorrido que hay o que se relaliza en el sistema viene siendo:

Navegacion (usuario desde la pagina )-> controller -> service -> repository -> base de datos


Hasta el momento no hemos creamo nada, ni en index ni en base de datos, pero la conexion esta funcionando correctamente, para este caso haremos un proyecto de prueba para familiarizarnos y entender correctamente como funciona el modelo de vista controlado en el caso de registros, tocare temas como:

- Entidades : Una entidad se utiliza para representar una tabla en una base de datos relacional. Cada instancia de la clase equivale a una fila dentro de esa tabla, y sus atributos corresponden a las columnas


- Repositorio :  El repositorio es el que se encarga de hacer las consultas de todo tipo (actualizar, borrar, añadir, etc), es la capa encargada de hablar con la base de datos y la que tiene acceso a ella

PARA TENER EN CUENTA MIENTRAS SE TRABAJA CON SRPINT BOOT:

IMPORTAMOS la entidad a la que va direccionada de la base de datos por eso cada entidad tiene su repositorio .demo.entity."entidad"
IMPORTAMOS la capa del framework sprint encargada de las consultas import "org.springframework.data.jpa.repository.JpaRepository;" 

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    " es la interfas que HEREDA la capa del framework para consultas a la base de datos en el cual sprint boot se encarga totalmente de eso por eso no escribimos consultas "

}


- Controllador
