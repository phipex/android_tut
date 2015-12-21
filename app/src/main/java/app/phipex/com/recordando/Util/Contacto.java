package app.phipex.com.recordando.Util;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "Contacto")
public class Contacto implements Serializable{

    @DatabaseField(generatedId = true)
    private int id;//primary key

    @DatabaseField(index = true, canBeNull = false)
    private String nombre;

    @DatabaseField
    private String telefono;

    @DatabaseField
    private String email;

    @DatabaseField
    private String direccion;

    @DatabaseField
    private String imageUri;

    //ejemplo de como serializar uri @DatabaseField(persisterClass = /* Clase para convertir objeto uri a string*/)


    public Contacto() {

    }

    public Contacto(String nombre, String telefono, String email, String direccion,String imageUri) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.imageUri = imageUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (!nombre.equals(contacto.nombre)) return false;
        if (telefono != null ? !telefono.equals(contacto.telefono) : contacto.telefono != null)
            return false;
        if (email != null ? !email.equals(contacto.email) : contacto.email != null) return false;
        if (direccion != null ? !direccion.equals(contacto.direccion) : contacto.direccion != null)
            return false;
        return !(imageUri != null ? !imageUri.equals(contacto.imageUri) : contacto.imageUri != null);

    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (imageUri != null ? imageUri.hashCode() : 0);
        return result;
    }

    //<editor-fold desc="Getters and setters">
    public int getId() {
        return id;
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    //</editor-fold>
}
