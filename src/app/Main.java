package app;
import repositorio.UsuarioRepositorio;
import modelo.Usuario;
import db.Conexion;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean iniciado = false;
        String tipo;
        Connection conn = Conexion.conectar();
        Scanner sc = new Scanner(System.in);

        while(!iniciado){
            System.out.println("\n1. Ingresar como usuario \n2. Ingresar como admin");
            System.out.println("Opcion: ");
            int op1 = sc.nextInt();
            sc.nextLine();
            switch(op1){
                case 1:
                    String emailU; 
                    String contraseñaU; 
                    System.out.println("\nDigite su email: ");
                    emailU = sc.nextLine();
                    System.out.println("\nDigite su contraseña: ");
                    contraseñaU = sc.nextLine();
                    iniciado = UsuarioRepositorio.IniciarSesion(emailU,contraseñaU,conn);
                    if(iniciado){tipo = "usuario";}
                    break;
                case 2:
                    String usuarioAdmin;
                    String contraseñaAdmin;
                    System.out.println("\n Digite usuario de admin: ");
                    usuarioAdmin = sc.nextLine();
                    System.out.println("\n Digite contraseña de admin: ");
                    contraseñaAdmin = sc.nextLine();
                    iniciado = UsuarioRepositorio.IniciarSesionAdmin(usuarioAdmin, contraseñaAdmin, conn);
                    if(iniciado){tipo = "admin";}
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
        
        while (true){
            System.out.println("\n1. Insertar usuario\n2. Listar usuarios\n3. Eliminar Usuario \n4. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String contraseña = sc.nextLine();
                    UsuarioRepositorio.insertar(new Usuario(nombre, email,contraseña),conn);
                    break;
                case 2:
                    List<Usuario> lista = UsuarioRepositorio.listar(conn);
                    lista.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("ID a eliminar: ");
                    int id = sc.nextInt();
                    UsuarioRepositorio.eliminar(id,conn);
                    break; 
                case 4:
                    System.out.println("Chau!");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
