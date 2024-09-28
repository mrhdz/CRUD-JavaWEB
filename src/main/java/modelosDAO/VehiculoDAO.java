package modelosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Vehiculo;
import otros.cn;

public class VehiculoDAO {

    private PreparedStatement ps;
    private Connection con;
    private final cn CN;
    private ResultSet rs;

    public VehiculoDAO() {
        CN = new cn();
    }

   public ArrayList consultaGeneral() {
        ArrayList<Vehiculo> consulta = new ArrayList<>();
        String sql = "select * from vehiculos";
        try {
            ps = CN.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setId(rs.getInt("IDVehiculo"));
                vehiculo.setMarca(rs.getString("MarcaVehiculo"));
                vehiculo.setModelo(rs.getString("ModeloVehiculo"));
                vehiculo.setAnio(rs.getInt("Anio"));
                consulta.add(vehiculo);
            }
        } catch (Exception e) {

        }
        return consulta;
    }

    public Vehiculo obtenerPorId(int id) {
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM vehiculos WHERE IDVehiculo = ?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new Vehiculo();
                    vehiculo.setId(rs.getInt("IDVehiculo"));
                    vehiculo.setMarca(rs.getString("MarcaVehiculo"));
                    vehiculo.setModelo(rs.getString("ModeloVehiculo"));
                    vehiculo.setAnio(rs.getInt("Anio"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener vehículo: " + e.getMessage());
        }
        return vehiculo;
    }

    public boolean agregar(Vehiculo vehiculo) throws SQLException {
        String sql = "INSERT INTO vehiculos(MarcaVehiculo, ModeloVehiculo, Anio) VALUES (?, ?, ?)";
        try {
            ps = CN.getConexion().prepareStatement(sql);
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getAnio());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al agregar vehículo: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculos SET MarcaVehiculo=?, ModeloVehiculo=?, Anio=? WHERE IDVehiculo=?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getAnio());
            ps.setInt(4, vehiculo.getId());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM vehiculos WHERE IDVehiculo=?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }
}
