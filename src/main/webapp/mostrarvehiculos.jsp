<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
    <head>
        <title>Vehículos</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous"/>
        <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/5ce7fe4c46.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <jsp:include page="menu.jsp"/>
        <main>
            <div class="container">
                <div class="card mt-5">
                    <div class="card-header">Información del nuevo Vehículo</div>
                    <div class="card-body">
                        <div class="container">
                            <form method="post" action="vehiculos">
                                <input type="hidden" name="action" value="add">
                                <div class="mb-3 row">
                                    <label for="inputMarca" class="col-4 col-form-label">Marca</label>
                                    <div class="col-8">
                                        <input type="text" class="form-control" name="marca" id="inputMarca" 
                                               placeholder="Ingrese la marca del Vehículo" required/>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="inputModelo" class="col-4 col-form-label">Modelo</label>
                                    <div class="col-8" >
                                        <input type="text" class="form-control" name="modelo" id="inputModelo"
                                               placeholder="Ingrese el modelo del Vehículo" required/>
                                    </div>
                                </div>
                                <div class="mb-3 row">
                                    <label for="inputAnio" class="col-4 col-form-label" >Año</label>
                                    <div class="col-8">
                                        <input type="number" class="form-control" name="anio" id="inputAnio"
                                               placeholder="Ingrese el año del Vehículo" min="1800"  max="2024" step="1" required/>
                                    </div>
                                </div>

                                <div class="mb-3 row">
                                    <div class="offset-sm-4 col-sm-8">
                                        <button type="submit" class="btn btn-primary">
                                            Agregar Vehículo
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card-footer text-muted mt-5">
                        <h3 >Vehículos Registrados</h3>
                        <table id="vehiculos-table" class="table table-striped table-hover table-responsive">
                            <thead class="table-dark">
                                <tr>
                                    <th class='align-middle text-center'>ID</th>
                                    <th class='align-middle text-center'>Marca</th>
                                    <th class='align-middle text-center'>Modelo</th>
                                    <th class='align-middle text-center'>Año</th>
                                    <th class='align-middle text-center'>Acciones</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="vehiculo" items="${lista}">
                                    <tr>
                                        <td class='align-middle text-center'>${vehiculo.getId()}</td>
                                        <td class='align-middle text-center'>${vehiculo.getMarca()}</td>
                                        <td class='align-middle text-center'>${vehiculo.getModelo()}</td>
                                        <td class='align-middle text-center'>${vehiculo.getAnio()}</td>
                                        <td class='align-middle text-center'>
                                            <a href="vehiculos?action=edit&id=${vehiculo.getId()}" class="btn btn-warning btn-sm"><i class='fa-solid fa-pen-to-square'></i>  Editar</a>
                                            <a href="vehiculos?action=delete&id=${vehiculo.getId()}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de que desea eliminar este vehículo?')"><i class='fa-solid fa-trash-can'></i>  Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#vehiculos-table').DataTable({
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ registros por página",
                        "zeroRecords": "No se encontraron registros",
                        "info": "Mostrando página _PAGE_ de _PAGES_",
                        "infoEmpty": "No hay registros disponibles",
                        "infoFiltered": "(filtrado de _MAX_ registros totales)",
                        "search": "Buscar:",
                        "paginate": {
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                });
            });
        </script>
    </body>
</html>