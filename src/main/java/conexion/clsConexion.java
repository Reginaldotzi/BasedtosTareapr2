package conexion;

import java.sql.*;
import java.util.Scanner;

public class clsConexion
{


    private Connection conn = null;
    private void conexion() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Crud1", "root", "regi34");
            System.out.println("Conexion realizada exitosamente");
        } catch (SQLException ex) {
            System.out.println("Hubo error: " + ex.getMessage());

        }
    }

    public void imprimirTodo() throws SQLException
    {
        conexion();
        String sql = "Select * from crud_personas";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String linea = "Codigo: " + rs.getInt("id") +
                    ", Nombre: " + rs.getString("Nombre") +
                    ", Apellido: " + rs.getString("Apellido") +
                    ", Fecha: " + rs.getDate("Fecha") +
                    ", Sueldo: " + rs.getDouble("Sueldo") +
                    ", Sexo: " + rs.getString("Sexo") +
                    ", Edad: " + rs.getInt("Edad") +
                    ", Longitud: " + rs.getDouble("Longitud") +
                    ", Latitud: " + rs.getDouble("Latitud") +
                    ", Comentario: " + rs.getString("Comentario")+ "\n";
            System.out.println(linea);
        }

    }

    public void insertarDatos() throws SQLException
    {
        conexion();


        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nombre:");
        String TMPnombre = sc.nextLine();
        System.out.println("Ingrese apellido");
        String TMPapellido = sc.nextLine();
        System.out.println("Ingrese sueldo:");
        double TMPsueldo = sc.nextDouble();
        System.out.println("Sexo(F/M)");
        String TMPsexo = sc.next();
        System.out.println("Ingrese edad:");
        int TMPedad = sc.nextInt();
        System.out.println("Ingrese longitud:");
        double TMPlongitud = sc.nextDouble();
        System.out.println("Ingrese latitud:");
        double TMPlatitud = sc.nextDouble();
        System.out.println("Ingrese algun comentario:");
        String TMPcomentario = sc.next();




        String sql = "insert into crud_personas (Nombre, Apellido, Fecha, Sueldo, Sexo, Edad, Longitud, Latitud, Comentario) values (?,?,CURRENT_DATE(),?,?,?,?,?,?)";
        PreparedStatement ps = null;

        ps = conn.prepareStatement(sql);
        ps.setString(1,TMPnombre);
        ps.setString(2, TMPapellido);
        ps.setDouble(3, TMPsueldo);
        ps.setString(4, TMPsexo);
        ps.setInt(5, TMPedad);
        ps.setDouble(6, TMPlongitud);
        ps.setDouble(7, TMPlatitud);
        ps.setString(8, TMPcomentario);
        ps.executeUpdate();
        System.out.println("> Datos insertados correctamente\n\n");

    }

    public void consultarDatos() throws SQLException
    {
        conexion();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo de la persona a consultar:");
        int TMPid = sc.nextInt();

        // Verificar si el ID existe antes de continuar
        if (!existeRegistro(TMPid)) {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
            return;
        }

        String sql = "Select * from crud_personas where id = ?";
        PreparedStatement ps = null;
        ps = conn.prepareStatement(sql);
        ps.setInt(1, TMPid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String linea = "Codigo: " + rs.getInt("id") +
                    ", Nombre: " + rs.getString("Nombre") +
                    ", Apellido: " + rs.getString("Apellido") +
                    ", Fecha: " + rs.getDate("Fecha") +
                    ", Sueldo: " + rs.getDouble("Sueldo") +
                    ", Sexo: " + rs.getString("Sexo") +
                    ", Edad: " + rs.getInt("Edad") +
                    ", Longitud: " + rs.getDouble("Longitud") +
                    ", Latitud: " + rs.getDouble("Latitud") +
                    ", Comentario: " + rs.getString("Comentario");
            System.out.println(linea);

        }
    }

    public void actualizarDatos() throws SQLException {
        conexion();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID de la persona que desea actualizar:");
        int id = sc.nextInt();

        // Verificar si el ID existe antes de continuar
        if (!existeRegistro(id)) {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
            return;
        }


        System.out.println("Ingrese el nuevo nombre:");
        String nuevoNombre = sc.nextLine(); // Consumir la nueva línea
        nuevoNombre = sc.nextLine(); // Leer el nuevo nombre
        System.out.println("Ingrese el nuevo apellido:");
        String nuevoApellido = sc.nextLine();
        System.out.println("Ingrese el nuevo sueldo:");
        double nuevoSueldo = sc.nextDouble();
        System.out.println("Sexo(F/M)");
        String nuevoSexo = sc.next();
        System.out.println("Ingrese la nueva edad:");
        int nuevaEdad = sc.nextInt();
        System.out.println("Ingrese la nueva longitud:");
        double nuevaLongitud = sc.nextDouble();
        System.out.println("Ingrese la nueva latitud:");
        double nuevaLatitud = sc.nextDouble();
        System.out.println("Ingrese el nuevo comentario:");
        String nuevoComentario = sc.nextLine(); // Consumir la nueva línea
        nuevoComentario = sc.nextLine(); // Leer el nuevo comentario

        String sql = "UPDATE crud_personas SET Nombre=?, Apellido=?, Sueldo=?, Sexo=?, Edad=?, Longitud=?, Latitud=?, Comentario=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nuevoNombre);
        ps.setString(2, nuevoApellido);
        ps.setDouble(3, nuevoSueldo);
        ps.setString(4, nuevoSexo);
        ps.setInt(5, nuevaEdad);
        ps.setDouble(6, nuevaLongitud);
        ps.setDouble(7, nuevaLatitud);
        ps.setString(8, nuevoComentario);
        ps.setInt(9, id);

        int filasActualizadas = ps.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Datos actualizados correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }

    }

    public void eliminarDatos() throws SQLException {
        conexion();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el ID de la persona que desea eliminar:");
        int id = sc.nextInt();

        // Verificar si el ID existe antes de continuar
        if (!existeRegistro(id)) {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
            return;
        }

        String sql = "DELETE FROM crud_personas WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        int filasEliminadas = ps.executeUpdate();
        if (filasEliminadas > 0) {
            System.out.println("Datos eliminados correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }

    }

    private boolean existeRegistro(int id) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM crud_personas WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }

        return false;
    }




}