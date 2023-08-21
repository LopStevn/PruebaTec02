/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import svlt20232108.accesoadatos.BookDAL;
import svlt20232108.entidadesdenegocio.Book;
import utils.Utilidad;

/**
 *
 * @author MINEDUCYT
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/Book"})
public class BookServlet extends HttpServlet {

    
    private Book obtenerBook(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Book book = new Book();
        if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Rol.
            book.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        book.setTitulo(Utilidad.getParameter(request, "titulo", ""));
        book.setAutor(Utilidad.getParameter(request, "autor", ""));    
        book.setAñoPubli(Integer.parseInt(Utilidad.getParameter(request, "añoPubli", "0")));
        // Devolver la instancia de la entidad Rol con los valores obtenidos del request.
        return book;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           
            List<Book> libros = BookDAL.obtenerTodos(); // Ir a la capa de acceso a datos y buscar los registros de Rol.
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.
            request.setAttribute("libros", libros); // Enviar los roles al jsp utilizando el request.setAttribute con el nombre del atributo roles.
                     
            request.getRequestDispatcher("Views/Books/index.jsp").forward(request, response); // Direccionar al jsp index de Rol.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    } 
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Rol
        request.getRequestDispatcher("Views/Books/index.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Book book = obtenerBook(request); // Llenar la instancia de Rol con los parámetros enviados en el request.
            // Enviar los datos de Rol a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = BookDAL.crear(book);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
               request.setAttribute("confirmacion", "El libro se añadio correctamente");                         
               request.getRequestDispatcher("Views/Books/confirmacion.jsp").forward(request, response);
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "create":                   
                    doPostRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                    break;               
                default:
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
    }

    // </editor-fold>

}
