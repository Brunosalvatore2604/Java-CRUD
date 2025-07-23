package app;
import repositorio.UsuarioRepositorio;
import modelo.Usuario;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Insertar usuario\n2. Listar usuarios\n3. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (op) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    UsuarioRepositorio.insertar(new Usuario(nombre, email));
                    break;
                case 2:
                    List<Usuario> lista = UsuarioRepositorio.listar();
                    lista.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Chau!");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
