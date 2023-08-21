<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="svlt20232108.entidadesdenegocio.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<% List<Book> libros = (List<Book>) request.getAttribute("libros");
    int numPage = 1;
    int numReg = 5;
    int countReg = 0;
    if (libros == null) {
        libros =  new ArrayList<>();
    } else if (libros.size() > numReg) {
        double divNumPage = (double) libros.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }   
%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />    
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">  
            <div class="row">
                <div class="col l12 s12">
                    <h5>Crear Tarea</h5>
                    <form action="Task" method="post">                                   
                        <div class="row">
                            <input type="hidden" name="accion" value="create">
                            <div class="input-field col l3 s12">
                                <input  id="txtTitle" type="text" name="titulo" required class="validate" maxlength="50">
                                <label for="txtTitle">Titulo</label>
                            </div>   
                            <div class="input-field col l3 s12">
                                <input  id="txtTitle" type="text" name="autor" required class="validate" maxlength="50">
                                <label for="txtTitle">Autor</label>
                            </div>   
                            <div class="input-field col l3 s12">
                                <input  id="txtTitle" type="text" name="title" required class="validate" maxlength="50">
                                <label for="txtTitle">Año de Publicacion</label>
                            </div>   
                        </div>
                        <div class="row">
                            <div class="col l12 s12">
                                <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>                       
                            </div>
                        </div>
                    </form>     
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <td style="text-align: center" colspan="5">Lista de Libros</td>                                   
                                </tr>
                                <tr>
                                    <th>Titulo</th> 
                                    <th>Autor</th> 
                                    <th>Año de Publicacion</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Book taskItemPending : taskPending) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">
                                    <td><%=taskItemPending.getTitle()%></td>  
                                    <td><%=taskItemPending.getDescription()%></td>  
                                    <td><%=taskItemPending.getDateCreate()%></td>  
                                    <td><%=taskItemPending.getStatus()%></td>  
                                    <td>
                                        <div style="display:flex">
                                            <form action="Task" method="post">
                                                <input type="hidden" name="accion" value="complete">
                                                <input type="hidden" name="id" value="<%=taskItemPending.getId()%>">
                                                <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Complete</button>
                                            </form>                                                                                                                             
                                        </div>
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>

            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPageComplete%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />        
    </body>
</html>
