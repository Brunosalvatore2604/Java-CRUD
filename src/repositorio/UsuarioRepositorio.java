package repositorio;

import modelo.Usuario;
import db.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    public static void insertar(Usuario u,Connection conn) {
        String sql = "INSERT INTO usuarios(nombre, email,contraseña) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getContraseña());
            ps.executeUpdate();
            System.out.println("Usuario insertado.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: \n" + e.getMessage());
        }
    }

    public static void eliminar(int idUsuario,Connection conn){
        String sql = "DELETE FROM usuarios where id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            System.out.println("Usuario eliminado");
        }catch(SQLException e){
            System.out.println("Error eliminando: \n"+e.getMessage());    
        }
    }

    public static List<Usuario> listar(Connection conn) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: \n" + e.getMessage());
        }
        return lista;
    }

    public static Boolean IniciarSesion(String email,String contraseña,Connection conn){
        String sql = "SELECT id FROM usuarios WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                System.out.println("Ese mail no pertenece a ningun Usuario");
                return false;
            }
            int id = rs.getInt("id");
            String sql2 = "SELECT contraseña FROM usuarios WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            String pass;
            if(rs2.next()){
                pass = rs2.getString("contraseña");
            }else{
                System.out.println("Error, ese usuario no existe");
                return false;
            }
            if(pass.equals(contraseña)){
                System.out.println("Inicio de sesion exitoso");
                return true;
            }
            System.out.println("Contraseña incorrecta");
            return false;
        }catch(SQLException e){
            System.out.println("\nError Iniciando Sesion: \n"+e.getMessage());
            return false;
        }
    }
    public static Boolean IniciarSesionAdmin(String email,String contraseña,Connection conn){
        return false;
    }
}
