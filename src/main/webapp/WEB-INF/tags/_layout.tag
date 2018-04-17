<!DOCTYPE html>
<%@attribute name="body_area" fragment="true" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="../../resources/vendor/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="../../resources/vendor/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../../resources/vendor/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="../../resources/vendor/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../resources/vendor/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <script src="../../resources/vendor/js/jquery.maskMoney.min.js" type="text/javascript"></script>
        <script src="../../../resources/vendor/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="../../../resources/vendor/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../../resources/vendor/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css"/>
         <script src="../../../resources/vendor/js/jquery.dataTables.min.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">SpringJson</a>
                </div>
                <ul class="nav navbar-nav" id="navleft">
                    <li><a href="/index">Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="navright">
                </ul>
            </div>
        </nav>
        <div id="alert"></div>
        <jsp:invoke fragment="body_area"/>
        <script src="../../resources/scripts/getsession.js" type="text/javascript"></script>
    </body>
</html>