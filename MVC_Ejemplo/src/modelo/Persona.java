
package modelo;

/**
 *
 * @author Sabina
 */
public class Persona {
    
    int id;
    String nombre;
    String correo;
    String celular;

    public Persona() {
    }

    public Persona(int id, String nombre, String correo, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
    
}
