package org.example;
import conexion.clsConexion;
import java.sql.SQLException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main
{

    public static void main(String[] args)
    {
        boolean salir = false;

        clsConexion crud = new clsConexion();

        while (!salir)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Bienvenido al CRUD de personas");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Imprimir tabla");
            System.out.println("2. Insertar datos");
            System.out.println("3. Consultar datos");
            System.out.println("4. Actualizar datos");
            System.out.println("5. Eliminar datos");
            System.out.println("6. Salir");
            int opcion = sc.nextInt();

            switch (opcion)
            {
                case 1:
                    try {
                        crud.imprimirTodo();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        crud.insertarDatos();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        crud.consultarDatos();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        crud.actualizarDatos();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        crud.eliminarDatos();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    salir = true;
                    System.out.println("Gracias por usar el CRUD de personas");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

    }
}