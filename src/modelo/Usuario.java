package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contraseña;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario(String nombre, String email,String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getContraseña(){return contraseña;}

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setContraseña(String contraseña){this.contraseña = contraseña;}

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " - " + email;
    }
}
