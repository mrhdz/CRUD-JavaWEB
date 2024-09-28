<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Poppins', sans-serif;
            line-height: 1.6;
            color: #333;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }
        header {
            background-color: #222;
            color: #fff;
            padding: 20px 0;
        }
        nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .logo {
            font-size: 24px;
            font-weight: 600;
        }
        .nav-links {
            display: flex;
            list-style: none;
        }
        .nav-links li {
            margin-left: 20px;
        }
        .nav-links a {
            color: #fff;
            text-decoration: none;
            position: relative;
            padding-bottom: 5px;
        }
        .nav-links a::after {
            content: '';
            position: absolute;
            width: 100%;
            height: 2px;
            bottom: 0;
            left: 0;
            background-color: #fff;
            transform: scaleX(0);
            transition: transform 0.3s ease-in-out;
        }
        .nav-links a:hover::after,
        .nav-links a.active::after {
            transform: scaleX(1);
        }
        .nav-links a.active::after {
            transform: scaleX(1);
        }
    </style>
</head>
<body>
    <header>
        <nav class="container">
            <div class="logo">FRAMEWORKS</div>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
                <li><a href="${pageContext.request.contextPath}/vehiculos">Vehiculo</a></li>
                <li><a href="${pageContext.request.contextPath}/mantenimientos">Mantenimiento</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>