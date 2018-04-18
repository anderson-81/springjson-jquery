<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PICTURE</title>
        <script src="../../resources/vendor/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>PICTURE</h1>
        <form enctype="multipart/form-data">
            <input type="file" id="picture" name="picture">
            <input type="submit" value="SEND">
            <img id="imgTest" src="" alt="">
        </form>

        <script>
            $("form").submit(function (event) {
                event.preventDefault();
                
                $("form :input").prop("disabled", true);
                var jForm = new FormData();
                jForm.append("picture", $('#picture').get(0).files[0]);

                $.ajax({
                    url: "/person/picture",
                    type: "POST",
                    data: jForm,
                    mimeType: "multipart/form-data",
                    contentType: false,
                    cache: false,
                    processData: false,
                    success: function (data, textStatus, jqXHR) {
                        var result = JSON.parse(data);
                        if (result.alert !== undefined) {
                            alert(result.alert);
                        }
                        if (result.page !== undefined) {
                            $(location).attr("href", "/index");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $(location).attr("href", "/errors/505");
                    }
                });
            });
        </script>

    </body>
</html>
