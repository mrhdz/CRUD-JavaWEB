package modelosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Mantenimiento;
import otros.cn;

public class MantenimientoDAO {

    private PreparedStatement ps;
    private Connection con;
    private final cn CN;
    private ResultSet rs;

    public MantenimientoDAO() {
        CN = new cn();
    }

    public ArrayList<Mantenimiento> consultaGeneralDetallada() {
        ArrayList<Mantenimiento> consulta = new ArrayList<>();
        String sql = "SELECT m.IDMantenimiento as IDMantenimiento, m.FechaMantenimiento as FechaMantenimiento, m.Costo as Costo, "
                + "v.IDVehiculo as VehiculoID, v.MarcaVehiculo as Marca_Vehiculo, v.ModeloVehiculo as Modelo_Vehiculo "
                + " FROM mantenimiento m "
                + "INNER JOIN vehiculos v ON m.VehiculoID = v.IDVehiculo";

        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mantenimiento mantenimiento = new Mantenimiento();
                mantenimiento.setId(rs.getInt("IDMantenimiento"));
                mantenimiento.setFecha(rs.getString("FechaMantenimiento"));
                mantenimiento.setCosto(rs.getDouble("Costo"));
                mantenimiento.setId_vehiculo(rs.getInt("VehiculoID"));
                mantenimiento.setMarca_vehiculo(rs.getString("Marca_Vehiculo"));
                mantenimiento.setModelo_vehiculo(rs.getString("Modelo_Vehiculo"));
                consulta.add(mantenimiento);
            }
        } catch (SQLException e) {
            System.out.println("Error en consulta detallada de mantenimiento: " + e.getMessage());
            e.printStackTrace();
        }

        return consulta;
    }

    public Mantenimiento obtenerPorId(int id) {
        Mantenimiento mantenimiento = null;
        String sql = "SELECT m.*, v.MarcaVehiculo, v.ModeloVehiculo FROM mantenimiento m "
                + "INNER JOIN vehiculos v ON m.VehiculoID = v.IDVehiculo "
                + "WHERE m.IDMantenimiento = ?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mantenimiento = new Mantenimiento();
                    mantenimiento.setId(rs.getInt("IDMantenimiento"));
                    mantenimiento.setFecha(rs.getString("FechaMantenimiento"));
                    mantenimiento.setId_vehiculo(rs.getInt("VehiculoID"));
                    mantenimiento.setCosto(rs.getDouble("Costo"));
                    mantenimiento.setMarca_vehiculo(rs.getString("MarcaVehiculo"));
                    mantenimiento.setModelo_vehiculo(rs.getString("ModeloVehiculo"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mantenimiento: " + e.getMessage());
        }
        return mantenimiento;
    }

    public ArrayList<Mantenimiento> obtenerMantenimientosPorVehiculoId(int idVehiculo) {
        ArrayList<Mantenimiento> consulta = new ArrayList<>();
        String sql = "SELECT m.*, v.MarcaVehiculo, v.ModeloVehiculo "
                + "FROM mantenimiento m "
                + "INNER JOIN vehiculos v ON m.VehiculoID = v.IDVehiculo "
                + "WHERE m.VehiculoID = ? "
                + "ORDER BY m.FechaMantenimiento DESC LIMIT 100"; // Ajusta el límite según tus necesidades

        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mantenimiento mantenimiento = new Mantenimiento();
                    mantenimiento.setId(rs.getInt("IDMantenimiento"));
                    mantenimiento.setFecha(rs.getString("FechaMantenimiento"));
                    mantenimiento.setCosto(rs.getDouble("Costo"));
                    mantenimiento.setId_vehiculo(rs.getInt("VehiculoID"));
                    mantenimiento.setMarca_vehiculo(rs.getString("MarcaVehiculo"));
                    mantenimiento.setModelo_vehiculo(rs.getString("ModeloVehiculo"));
                    consulta.add(mantenimiento);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mantenimientos por vehiculo ID: " + e.getMessage());
            e.printStackTrace(); // Considera usar un sistema de logging más robusto
        }

        return consulta;
    }

    public boolean agregar(Mantenimiento mantenimiento) throws SQLException {
        String sql = "INSERT INTO mantenimiento(FechaMantenimiento, VehiculoID, Costo) VALUES (?, ?, ?)";
        try {
            ps = CN.getConexion().prepareStatement(sql);
            ps.setString(1, mantenimiento.getFecha());
            ps.setInt(2, mantenimiento.getId_vehiculo());
            ps.setDouble(3, mantenimiento.getCosto());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("Error al agregar mantenimiento: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizar(Mantenimiento mantenimiento) {
        String sql = "UPDATE mantenimiento SET FechaMantenimiento=?, VehiculoID=?, Costo=? WHERE IDMantenimiento=?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mantenimiento.getFecha());
            ps.setInt(2, mantenimiento.getId_vehiculo());
            ps.setDouble(3, mantenimiento.getCosto());
            ps.setInt(4, mantenimiento.getId());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar mantenimiento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM mantenimiento WHERE IDMantenimiento=?";
        try (Connection con = CN.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar mantenimiento: " + e.getMessage());
            return false;
        }
    }
}
