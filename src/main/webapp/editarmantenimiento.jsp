<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
    <head>
        <title>Editar Mantenimiento</title>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous"/>
    </head>

    <body>
        <jsp:include page="menu.jsp"/>
        <main>
            <div class="container">
                <div class="card mt-5">
                    <div class="card-header">Editar Mantenimiento</div>
                    <div class="card-body">
                        <form method="post" action="mantenimientos">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${mantenimiento.getId()}">

                            <div class="mb-3 row">
                                <label for="fecha" class="col-4 col-form-label">Fecha de Mantenimiento</label>
                                <div class="col-8">
                                    <input type="date" class="form-control" name="fecha" id="fecha" 
                                           value="${mantenimiento.getFecha()}" required/>
                                </div>
                            </div>
                            <!--
                            <div class="mb-3 row">
                            <label class="col-4 col-form-label">Vehículo Actual</label>
                            <div class="col-8">
                                <input type="text" class="form-control" value="${mantenimiento.marca_vehiculo} - ${mantenimiento.modelo_vehiculo}" readonly/>
                            </div>
                        </div>
                            --> 
                            <div class="mb-3 row">
                                <label for="vehiculoId" class="col-4 col-form-label">Cambiar Vehículo</label>
                                <div class="col-8">
                                    <select name="vehiculoId" id="vehiculoId" class="form-control" required>
                                        <c:forEach var="vehiculo" items="${listavehiculos}">
                                            <option value="${vehiculo.getId()}"
                                                    <c:if test="${vehiculo.getId() == mantenimiento.getId_vehiculo()}">selected</c:if>>
                                                ${vehiculo.getMarca()} - ${vehiculo.getModelo()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <label for="costo" class="col-4 col-form-label">Costo</label>
                                <div class="col-8">
                                    <input type="number" class="form-control" name="costo" id="costo"
                                           value="${mantenimiento.getCosto()}" min="0" step="0.01" required/>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="offset-sm-4 col-sm-8">
                                    <button type="submit" class="btn btn-primary">Actualizar</button>
                                    <a href="mantenimientos" class="btn btn-secondary">Cancelar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
    </body>
</html>
