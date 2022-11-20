package Hotel.Habitaciones.controllers;
/**
 * Esta clase UserController pertenece al paquete controllers.
 * En principio, solo debería atender a los requests HTTP,
 * y según sea GET, POST, u otro método, analizar el contenido
 * del request, y decidir a qué método llamar. 
 * En principio, esta clase no debería hacer el trabajo. No.
 * Lo que debería hacer es llamar al método encargado de hacer
 * el trabajo y pasarle los parámetros necesarios. 
 * Ese método llamado debería pertenecer a una clase del paquete services. 
 * Pero nosotros no tenemos ese paquete, porque este es un ejemplo muy simple.
 * Veremos que esta clase hace el trabajo, lo que no debería ser así.
 * Entonces, recordar que estamos dejando de lado un principio
 * muy importante, para no complicar este ejemplo.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Hotel.Habitaciones.models.Habitacion;
import Hotel.Habitaciones.repositorios.HabitacionRepositorio;

@RestController
@RequestMapping("")

public class HabitacionController {
  @Autowired //Esta anotación nos permite inyectar en un componente cualquier otro bean definido en el contexto de Spring.
  private HabitacionRepositorio hotelRepository;

  @PostMapping("/add")
  public String addNewHabitacion(@RequestParam int capacidad, @RequestParam int precio,
      @RequestParam String disponibilidad) {
    Habitacion n = new Habitacion();
    n.setCapacidad(capacidad);
    n.setDisponibilidad(disponibilidad);
    n.setPrecio(precio);
    hotelRepository.save(n);
    return "Saved";
  }

  @DeleteMapping("Hotel/Habitacion/{id}")
  public String deletehabitacionbyId(@PathVariable Long id) {
    hotelRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping("/{id}")
  public String findHabitacionById(@PathVariable Long id) {
    String resp = """
          <style>
            #habitaciones {
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }
            #habitaciones td, #habitaciones th {
              border: 1px solid #ddd;
              padding: 8px;
            }
            #habitaciones tr:nth-child(even){background-color: #f2f2f2;}
            #habitaciones tr:hover {background-color: #ddd;}
            #habitaciones th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #15a5ed;
              color: white;
            }
          </style>
          <table id ='habitaciones'>
            <tr>
              <th>Id</th>
              <th>Capacidad</th>
              <th>Precio</th>
              <th>Disponibilidad</th>
            </tr>
        """;
    if (hotelRepository.findById(id).isPresent()) {

      Habitacion hab = hotelRepository.findById(id).get();
      resp += "<tr>"
          + "<td>" + hab.getid() + "</td>"
          + "<td>" + hab.getCapacidad() + " Personas "+ "</td>"
          + "<td>" + hab.getPrecio() + "</td>"
          + "<td>" + hab.getDisponibilidad() + "</td>"
          + "</tr>";
    } else {
      resp += "<tr>"
          + "<td>" + "-" + "</td>"
          + "<td>" + "-" + "</td>"
          + "<td>" + "-" + "</td>"
          + "<td>" + "No existe habitacion" + "</td>"
          + "</tr>";
    }
    return resp + "</table";
  }

  @GetMapping("/all")
  public String getAllHabitacion() {
    Iterable<Habitacion> iterable = hotelRepository.findAll();
    String resp = """
          <style>
            #habitaciones {
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }
            #habitaciones td, #habitaciones th {
              border: 1px solid #ddd;
              padding: 8px;
            }
            #habitaciones tr:nth-child(even){background-color: #f2f2f2;}
            #habitaciones tr:hover {background-color: #ddd;}
            #habitaciones th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #15a5ed;
              color: white;
            }
          </style>
          <table id ='habitaciones'>
            <tr>
              <th>Id</th>
              <th>Capacidad</th>
              <th>Precio</th>
              <th>Disponibilidad</th>
            </tr>
        """;
    /**
     * Ya terminé con la fila de los encabezados, y ahora tengo que
     * generar el cuerpo de la tabla, una fila por cada registro.
     * No puedo usar forEach() con una función lambda
     * porque el scope de las variables no lo permite.
     * Por eso uso el for mejorado, para recorrer el objeto iterable.
     */
    for (Habitacion habitacion : iterable) {
      resp += "<tr>"
          + "<td>" + habitacion.getid() + "</td>"
          + "<td>" + habitacion.getCapacidad() + " personas "+ "</td>"
          + "<td>" + habitacion.getPrecio() + "</td>"
          + "<td>" + habitacion.getDisponibilidad() + "</td>"
          + "</tr>";
    }
    return resp + "</table>";
  }

  @GetMapping("")
  public String hola() {
    String resp = """

        <style>
        h2{
          color: white;
          font-size: 50px;
          text-align: center;
          font-family: sans-serif;
      }
        body{
            background-image: url(https://pix10.agoda.net/hotelImages/124/1246280/1246280_16061017110043391702.jpg?ca=6&ce=1&s=1024x768);
            background-size: cover;
        }
        nav{
            max-width: 900px;
            margin: auto;
            background-color: rgb(45, 111, 211);
            font-size: 20px;
            margin-top: 50px;

        }


        .Menu-horizontal{
            list-style: none;
            display: flex;
            justify-content: space-around;
        }
        .Menu-horizontal > li > a{
            display: block;
            padding: 15px 20px;
            color: rgb(255, 255, 255);
            text-decoration: none;
        }

        .Menu-horizontal > li:hover{
            background-color: rgb(124, 167, 231);
        }

        .Menu-vertical{
            position: absolute;
            display: none;
            list-style: none;
            width: 200px;
            background-color: rgba(0,0,0,.5);
        }
        .Menu-horizontal li:hover .Menu-vertical{
            display: block;

        }
        .Menu-vertical li:hover{
            background-color: rgb(0, 0, 0);
        }
        .Menu-vertical li a{
            display: block;
            color: rgb(255, 255, 255);
            padding: 15px 15px 15px 20px;
            text-decoration: none;

        } </style>

              <<body>
              <header>
                      <h2 class='logo-img'> Hotel California</h2>

                  </div>
                  <nav>
                      <ul class='Menu-horizontal'>
                          <li><a href=''>Inicio</a></li>
                          <li>
                              <a href=''>Habitaciones</a>
                              <ul class='Menu-vertical'>
                                  <li><a href='http://localhost:8080/all'>Todas las habitaciones</a></li>
                                  <li><a href='http://localhost:8080/1'>Habitacion 1</a></li>
                                  <li><a href='http://localhost:8080/2'>Habitacion 2</a></li>
                                  <li><a href='http://localhost:8080/3'>Habitacion 3</a></li>
                                  <li><a href='http://localhost:8080/4'>Habitacion 4</a></li>
                                  <li><a href='http://localhost:8080/5'>Habitacion 5</a></li>
                              </ul>
                          </li>
                      </ul>

                  </nav>

              </header>
          </body>

                """;
    return resp;
  }
}
