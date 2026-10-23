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

El orden de creacion precisamente es al contrario:

- MySql -> Entidad -> Repositorio -> Servicio -> Controlador -> llega a la Web

Hasta el momento no hemos creamo nada, ni en index ni en base de datos, pero la conexion esta funcionando correctamente, para este caso haremos un proyecto de prueba para familiarizarnos y entender correctamente como funciona el modelo de vista controlado en el caso de registros, tocare temas como:

- Entidades : Una entidad se utiliza para representar una tabla en una base de datos relacional. Cada instancia de la clase equivale a una fila dentro de esa tabla, y sus atributos corresponden a las columnas


LAS TRES REGLAS OBLIGATORIAS

Antes de las anotaciones, tres cosas que JPA exige o no arranca:

- Constructor vacío : JPA crea el objeto sin datos y después lo llena. Si no hay constructor sin argumentos, falla.

- Una llave primaria Siempre : Sin @Id no es una entidad.

- Atributos privados con getters y setters : Es como JPA entra y sale del objeto.

En la entidad va practicamente una clase con dos anotaciones: @Entity para representar esa clase como una entidad y @Table para saber que es una tabla

IMPORTANTE 
*import jakarta.persistence.* : Es para importar todas las anotaciones que se usan para manejar las tablas

*import com.fasterxml.jackson.annotation.JsonIgnore;* es la libreria que convierte objetos a JSON es la responsable de que puedas recibir un arreglo json cpn tu "fech"

Dentro de es entidad iran las variables o "Atributos" que viven dentro y existen mientras la entidad existe, los tributos en el caso de la entidad seria la representacion de las columnas de la tabla a la que señalamos

*private "El tipo de dato" + "El dato"*

a estos atributos se le asignan anotacion dependiendo el caso, un ejemplo seria la *llave primaria*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

se usan estas anotaciones para asignarle el valor de primaria

@Id                 : llave primaria
@GenerateValue()    : quien produce el numero de la llave

@GenerateValue tiene 4 capas: 

- IDENTITY. : el valor lo genera la base de datos en este caso MySQL con AUTO_INCREMENT

- SEQUENCE  : genera una secuencia de la BD cuando se usa PostgreSQL, Oracle

- TABLE	casi nunca se usa

- *AUTO	evítar usarla, es impredecible*

Para el caso de el atributo donde se crea o guardamos la hora y fecha de creacion del usuario hay que tener cuidado, ese tributo va con la anotacion de @Column y ciertas caracteristicas


"@Column(name = "creado_en", insertable = false, updatable = false)
private LocalDateTime creado_en;"

insertable = false : no lo inserto
updatable = false : no lo actualizo

Se usa para que ese valor no se toque al momento de actualizar o insertar datos

COMO SE LEE LOS TIPOS DE DATOS DE MYSQL EN JAVA

*MySQL*                   *JAVA*
- INT	                  : Integer
- BIGINT	              : Long
- VARCHAR	              : String
- TEXT	                  : String + @Lob
- DECIMAL(10,2)	          : BigDecimal
- DATE	                  : LocalDate
- DATETIME / TIMESTAMP	  : LocalDateTime
- BOOLEAN / TINYINT(1)	  : Boolean

*la contraseña siempre lleva la anotacion @JsonIgnore para que ese campo nunca viaje por la red, se guarda pero no se envia a ningun lado*

LLAVES FORANEAS : 

En java no guardas el numero, guardas el objeto completo...Las anotaciones que se usan son @ManyToOne y @JoinColum

- @ManyToOne : Muchos productos pertenecen a una categoria 
- @JoinColum : La columna de la tabla que guarda el numero de la foranea

ESTRUCTURA: 

@ManyToOne
@JoinColumn(name = "id_categorias")
private Categorias categorias;

PARA REVISAR DESPUES:

Anotaciones : @Transient , @Enumerated , @Lob , @CreationTimestamp , @UpdateTimestamp

CONSTRUCTOR GET Y SET

*CONSTRUCTOR* : Es el metodo que se ejecuta en el momento de crear la clase para dejar el objeto listo para usar...Dos señales que lo distinguen de otros metodos:

Se llama igual que la clase - no tiene ningun tipo de retorno, ni siquiera void

La regla: *si escribes un constructor, escribe también el vacío.*

public Usuarios() {
}

public Usuarios(String nombre, String telefono) {
    this.nombre = nombre;
    this.telefono = telefono;
}

Eso se llama sobrecarga: varios constructores con el mismo nombre y distintos parámetros. Java elige según lo que le pases.

*GET* : Devuelve el valor de un atributo el tipo de retorno es igual al del atributo

su estructura vendria siendo

public + Tipo del atributo + getAtributo{
    return atributo
}

"Para BOOLEANOS"
public + Tipo del atributo + isActivo{
    return activo
}

*SET* : Asignacion de valores, siempre co void y siempre recibe un valor del tipo del atributo

su estructura es

public void + setAtributo(tipo atributo + atributo){
    this.atributo = atributo
}

*this* es obligarorio porque si no se estaria reaccionando a el mismo, el this se usa para decir "este es el objeto"


- Repositorio :  El repositorio es el que se encarga de hacer las consultas de todo tipo (actualizar, borrar, añadir, etc), es la capa encargada de hablar con la base de datos y la que tiene acceso a ella

PARA TENER EN CUENTA MIENTRAS SE TRABAJA CON SRPINT BOOT:

IMPORTAMOS la entidad a la que va direccionada de la base de datos por eso cada entidad tiene su repositorio .demo.entity."entidad"
IMPORTAMOS la capa del framework sprint encargada de las consultas import "org.springframework.data.jpa.repository.JpaRepository;" 

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    " es la interfas que HEREDA la capa del framework para consultas a la base de datos en el cual sprint boot se encarga totalmente de eso por eso no escribimos consultas "

}


- Controllador : El controlador es el encargado de traducir la informacion que recibe y se la entrega a servicio, quien es el que decide que hacer con una informacion y biseversa, recibe informacion tipo json y la pasa a objetos...recibe objetos y los convierte en json en el camino de regreso al naegador

Sus cuatro acciones son: RECIBIR - TRADUCIR - ENTREGAR - RESPONDER

RESPUESTA DEL CONTROLLADOR Y SU SIGNIFICADO

200	salió bien
201	se creó algo nuevo
400	mandaste datos malos
401	no te identificaste bien
404	no existe
500	se rompió algo del lado del servidor

En el controlador se importan dos tipos de cosas, las creadas por nosotros y las librerias de spring, solo conoce de nuestros archivos entidades y servicios

.demo.entity.Usuarios;
.demo.entity.UsuariosService;

las importaciones de archivos es lo mismo como navegar entre carpetas, estas significan la ruta en la que importaras esos archivos ejemplo: /demo/entity/Usuarios/ es la ubicacion de nuestro archivo .java de la entidad Usuarios

import org.springframework.http.HttpStatus;       
import org.springframework.http.ResponseEntity;   
import org.springframework.web.bind.annotation.*; 

SIGNIFICADOS

.http.ResponseEntity : Es el "sobre" de la respuesta, es el encargado de transportar la respuesta o el contenido y codigo juntos

.http.HttpStatus : los nombres de los códigos: CREATED, UNAUTHORIZED, NOT_FOUND.

import org.springframework.web.bind.annotation.* : aqui vivden todas las anotaciones juntas:

@RestController · @RequestMapping · @PostMapping · @GetMapping · @PutMapping · @DeleteMapping · @RequestBody · @PathVariable · @RequestParam · @CrossOrigin

"*" al igual que en css significa TODAS por lo tanto trae o importa todas las anotaciones

IMPORTACIONES QUE NO DEBEN APARECER NUNCA EN UN CONTROLADOR

import com.capilares.demo.repository.UsuarioRepository;
import jakarta.persistence.*;                  
import org.springframework.security.crypto...; 

"El controlador solo conce el servicio y las clases que le entrega el servicio"

QUE ES UNA ANOTACION

Una anotacion es una etiqueta que pegas en un motodo o parametro para darle identidad es informacion sobre el codigo, se escribe arriba de los metodos o parametros iniciando con @

Sintaxis : 

@RestController                                  // sola, sin nada

@RequestMapping("/auth")                         // un valor suelto

@Column(name = "id_usuario")                     // atributo con nombre

@Column(name = "correo", nullable = false)       // varios atributos

@CrossOrigin(origins = {"http://a", "http://b"}) // una lista, entre llaves

COMO SE USAN : 

@RestController                          // ← sobre la CLASE
public class AutenticacionControlador {

    @Autowired                           // ← sobre un CAMPO
    private UsuariosServicio servicio;

    @PostMapping("/registro")            // ← sobre un MÉTODO
    public ResponseEntity<?> registrar(

            @RequestBody DatosRegistro datos) {   // ← antes del PARÁMETRO, misma línea
    }
}

ANOTACIONES TIPOS :

Registran una clase en sprint

- @SpringBootApplication	 : el punto de arranque; desde aquí Spring empieza a buscar
- @RestController	         : atiende peticiones web y devuelve JSON
- @Service	                 : aquí viven las reglas del negocio
- @Repository	             : habla con la base de datos
- @Component	             : el genérico — las tres de arriba son versiones suyas con nombre

Definen rutas

- @RequestMapping("/auth")	    : prefijo de toda la clase
- @PostMapping("/registro")	    : crear algo
- @GetMapping("/{id}")	        : traer algo
- @PutMapping("/{id}")	        : actualizar
- @DeleteMapping("/{id}")	    : borrar

En la entidad

- @Entity	                     : esta clase es una tabla
- @Table(name = "usuarios")	     : el nombre real de la tabla
- @Id	                         : esta columna es la llave primaria
- @GeneratedValue(...)	         : el número lo pone MySQL, no tú
- @Column(name = "id_usuario")	 : el nombre real de la columna

QUE ES UN METODO

Un metodo es una funcion que vive dentro de una clase, es lo mismo que una funcion de JavaScripts un bloque de codigo que haces correr cuando lo llamas

PARTES :

public ResponseEntity<?> registrar(DatosRegistro datos) {
    // cuerpo
}

- public                 : quién puede llamarlo
- ResponseEntity<?>	     : qué devuelve
- registrar	             : como se llama
- (DatosRegistro datos)	 : qué necesita para trabajar
- { }	                 : el cuerpo: lo que hace

public Usuarios registrar(DatosRegistro datos) {
    return algo
}

Ese Usuarios antes del nombre es una promesa: “este método devuelve un objeto Usuarios, siempre”. Si intentas devolver otra cosa, no compila.

Es más estricto, pero te atrapa errores antes de ejecutar nada.

Una clase es un molde que define qué datos tiene algo y qué sabe hacer, y del cual se crean objetos.

Un método es una acción con nombre: recibe valores, hace algo con ellos y puede devolver un resultado.

Un atributo es una variable que pertenece al objeto y vive mientras él viva.

public class Usuarios {

    private String contrasena;              // ← datos: PRIVADOS

    public String getContrasena() {         // ← acciones: PÚBLICAS
        return contrasena;
    }
}

Datos privados, acciones públicas. Esto se llama encapsulamiento y es casi toda la idea.