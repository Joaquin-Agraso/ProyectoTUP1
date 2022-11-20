package Hotel.Habitaciones.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // Anotacion para crear una tabla a partir de esta clase
public class Habitacion {//Esta clase es ideal que tenga el mismo nombre que la tabla(base de datos)
    //esta clase contendra las variables que seran los mismos mcampos que tendrá la tabla.

    @Id/*indica la llave primaria, esta se hereda de javax.persistence.Id,
     lo que indica que el campo miembro a continuación es la clave principal de la entidad actual. 
     Por lo tanto, su marco de Hibernate y Spring, 
     así como también puede hacer algunos trabajos de reflexión basados ​​en esta anotación.*/

    @GeneratedValue(strategy = GenerationType.IDENTITY)/*
    Se basa en una columna de base de datos con incremento automático y permite que la base de datos genere
     un nuevo valor con cada operación de inserción. Desde el punto de vista de la base de datos, 
     esto es muy eficiente porque las columnas de incremento automático están altamente optimizadas
      y no requiere ninguna declaración adicional.
    */
    private Long id;

    private int Capacidad;

    private int Precio;

    private String Disponibilidad;
    //Se generen los getter y setter para acceder a los datos y o settear valores.

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.Capacidad = capacidad;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        this.Precio = precio;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.Disponibilidad = disponibilidad;
    }

    
}
