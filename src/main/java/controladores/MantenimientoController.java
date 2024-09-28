package controladores;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mantenimiento;
import modelos.Vehiculo;
import modelosDAO.MantenimientoDAO;
import modelosDAO.VehiculoDAO;

@WebServlet(name = "MantenimientoController", urlPatterns = {"/mantenimientos"})
public class MantenimientoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MantenimientoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MantenimientoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        if (action != null) {
            switch (action) {
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Mantenimiento mantenimiento = mantenimientoDAO.obtenerPorId(id);
                    request.setAttribute("mantenimiento", mantenimiento);

                    // obtener la lista de vehículos
                    ArrayList<Vehiculo> listavehiculos = vehiculoDAO.consultaGeneral();
                    request.setAttribute("listavehiculos", listavehiculos);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("editarmantenimiento.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    mantenimientoDAO.eliminar(id);
                    response.sendRedirect("mantenimientos");
                    break;
                default:
                    break;
            }
        } else {
            // Obtener todos los vehículos para el select
            ArrayList<Vehiculo> listavehiculos = vehiculoDAO.consultaGeneral();
            request.setAttribute("listavehiculos", listavehiculos);

            // Obtener todos los mantenimientos para la tabla
            ArrayList<Mantenimiento> lista = mantenimientoDAO.consultaGeneralDetallada();
            request.setAttribute("lista", lista);

            RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarmantenimiento.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();

        if ("update".equals(action)) {
            // Obteniendo los parámetros del formulario de edición
            int id = Integer.parseInt(request.getParameter("id"));
            String fecha = request.getParameter("fecha");
            int idVehiculo = Integer.parseInt(request.getParameter("vehiculoId")); //
            double costo = Double.parseDouble(request.getParameter("costo"));

            // Creando el objeto Mantenimiento con los datos obtenidos
            Mantenimiento mantenimiento = new Mantenimiento(id, fecha, idVehiculo, costo);

            mantenimientoDAO.actualizar(mantenimiento);
            /*// Llamada al método actualizar del DAO
        boolean resultado = mantenimientoDAO.actualizar(mantenimiento);

        if (resultado) {
            System.out.println("Mantenimiento actualizado con éxito. ID: " + id);
            response.sendRedirect(request.getContextPath() + "/mantenimientos");
        } else {
            System.out.println("Error al actualizar el mantenimiento. ID: " + id);
            request.setAttribute("error", "Error al actualizar el mantenimiento");
            request.getRequestDispatcher("editarmantenimiento.jsp").forward(request, response);
        }*/

        } else if ("add".equals(action)) {
            // Obteniendo los parámetros del formulario de agregar
            String fecha = request.getParameter("fecha");
            int idVehiculo = Integer.parseInt(request.getParameter("vehiculoId")); // llamamos al select donde esta el ID del Vehiculo
            double costo = Double.parseDouble(request.getParameter("costo"));

            // Creando el objeto Mantenimiento con los datos obtenidos
            Mantenimiento nuevoMantenimiento = new Mantenimiento(fecha, idVehiculo, costo);

            try {
                mantenimientoDAO.agregar(nuevoMantenimiento);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*
            try {
                boolean resultado = mantenimientoDAO.agregar(nuevoMantenimiento);
                if (resultado) {
                    response.sendRedirect("mantenimientos"); // Redirigir a la lista de mantenimientos
                } else {
                    request.setAttribute("error", "Error al agregar el mantenimiento");
                    request.getRequestDispatcher("agregarmantenimiento.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MantenimientoController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", "Error al agregar el mantenimiento: " + ex.getMessage());
                request.getRequestDispatcher("agregarmantenimiento.jsp").forward(request, response);
            }*/
        }
        
        // redigir a mantenimientos
        response.sendRedirect("mantenimientos");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
