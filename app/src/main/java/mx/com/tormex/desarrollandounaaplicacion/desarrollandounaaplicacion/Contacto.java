package mx.com.tormex.desarrollandounaaplicacion.desarrollandounaaplicacion;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sistemas on 22/06/2017.
 */

public class Contacto {
    private String nombre;
    private Date fechaNacimiento;
    private String telefono;
    private String eMail;
    private String descripcion;

    public Contacto(String nombre, Date fechaNacimiento, String telefono, String eMail, String descripcion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.eMail = eMail;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
