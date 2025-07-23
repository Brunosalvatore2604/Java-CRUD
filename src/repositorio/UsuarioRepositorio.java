package repositorio;

import modelo.Usuario;
import db.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    public static void insertar(Usuario u) {
        String sql = "INSERT INTO usuarios(nombre, email) VALUES (?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.executeUpdate();
            System.out.println("Usuario insertado.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public static List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = Conexion.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}
