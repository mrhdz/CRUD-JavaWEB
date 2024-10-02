/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import com.google.gson.Gson;
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

@WebServlet(name = "VehiculoController", urlPatterns = {"/vehiculos"})
public class VehiculoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VehiculoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VehiculoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();

        if (action != null) {
            switch (action) {
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Vehiculo vehiculo = vehiculoDAO.obtenerPorId(id);
                    request.setAttribute("vehiculo", vehiculo);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editarvehiculo.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    vehiculoDAO.eliminar(id);
                    response.sendRedirect("vehiculos");
                    break;
                case "verMantenimientos":
                    id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Mantenimiento> listaMantenimientos = mantenimientoDAO.obtenerMantenimientosPorVehiculoId(id);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    Gson gson = new Gson(); // es importante agregar la dependecia Gson
                    out.print(gson.toJson(listaMantenimientos));
                    out.flush();
                    break;
                default:
                    break;
            }
        } else {
            ArrayList<Vehiculo> lista = vehiculoDAO.consultaGeneral();
            request.setAttribute("lista", lista);
            RequestDispatcher dispatcher = request.getRequestDispatcher("mostrarvehiculos.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            int anio = Integer.parseInt(request.getParameter("anio"));
            Vehiculo vehiculo = new Vehiculo(id, marca, modelo, anio);
            vehiculoDAO.actualizar(vehiculo);
        } else if ("add".equals(action)) {
            String marca = request.getParameter("marca");
            String modelo = request.getParameter("modelo");
            int anio = Integer.parseInt(request.getParameter("anio"));
            Vehiculo vehiculo = new Vehiculo(marca, modelo, anio);
            try {
                vehiculoDAO.agregar(vehiculo);
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("vehiculos");
    }

    @Override
    public String getServletInfo() {
        return "VehiculoController handles CRUD operations for vehicles";
    }

}
