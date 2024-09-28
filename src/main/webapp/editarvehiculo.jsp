<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
    <head>
        <title>Editar Vehiculo</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

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
                    <div class="card-header">Editar Persona</div>
                    <div class="card-body">
                        <form method="post" action="vehiculos">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${vehiculo.getId()}">
                            <div class="mb-3 row">
                                <label for="marca" class="col-4 col-form-label">Marca</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" name="marca" id="marca" 
                                           value="${vehiculo.getMarca()}" required/>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="modelo" class="col-4 col-form-label">Modelo</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" name="modelo" id="modelo"
                                           value="${vehiculo.getModelo()}" required/>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="anio" class="col-4 col-form-label">Año</label>
                                <div class="col-8">
                                    <input type="text" class="form-control" name="anio" id="anio"
                                           value="${vehiculo.getAnio()}" required/>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="offset-sm-4 col-sm-8">
                                    <button type="submit" class="btn btn-primary">Actualizar</button>
                                    <a href="vehiculos" class="btn btn-secondary">Cancelar</a>
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