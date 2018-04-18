$(document).ready(function () {

    var token = "";

    function Token() {
        $.ajax({
            url: "/token",
            method: "PUT",
            dataType: 'json',
            async: false,
            success: function (data, textStatus, jqXHR) {
                token = data;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $(location).attr("href", "/errors/505");
            }
        });
    }

    Token();
    $("#errors").hide();

    $.ajax({
        url: window.location.pathname + "/" + token,
        type: 'GET',
        async: false,
        success: function (data, textStatus, jqXHR) {
            var result = JSON.parse(data);

            if (result !== null)
            {
                $("form").show();

                $("#token").val(token);

                $("#id").val(result["person"].id);
                $("#name").val(result["person"].name);
                $("#email").val(result["person"].email);
                $('#birthday').val(result["birthday"]);
                $("#salary").val(result["person"].salary);
                $("#gender").val(result["person"].gender);
                $("#avatar").attr("src", result["picture"]);
                $("#lnkDelete").click(function () {
                    if (confirm('Do you want delete this record?'))
                    {
                        $.ajax({
                            url: "/person/delete/" + result["person"].id + "/" + token,
                            method: "GET",
                            success: function (data, textStatus, jqXHR) {

                                var result = JSON.parse(data);
                                if (result.alert !== undefined) {
                                    alert(result.alert);
                                }
                                $(location).attr("href", result.page);
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $(location).attr("href", "/errors/505");
                            }
                        });
                    }
                });
            } else {
                $(location).attr("href", "/person/index");
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
            $(location).attr("href", "/errors/505");
        }
    });

    $("form").submit(function (event) {
        if (confirm('Do you want edit this record?'))
        {
            $("#errors").hide();

            $("li").remove();

            $("form :input").prop("disabled", true);

            var jForm = new FormData();
            jForm.append("token", $('#token').val());
            jForm.append("id", $('#id').val());
            jForm.append("name", $('#name').val());
            jForm.append("email", $('#email').val());
            jForm.append("salary", $('#salary').val());
            jForm.append("birthday", $('#birthday').val());
            jForm.append("gender", $('#gender').val());

            if (document.getElementById("picture").files.length > 0) {
                jForm.append("picture", $('#picture').get(0).files[0]);
            }

            $.ajax({
                url: "/person/edit/update",
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

                    if (result.errors !== undefined) {
                        $("#errors").show();
                        $.each(result.errors, function (idx, error) {
                            $("#errors > ul").append("<li>" + error + "</li>");
                        });

                        $("form :input").prop("disabled", false);
                    }

                    if (result.page !== undefined) {
                        $(location).attr("href", result.page);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $(location).attr("href", "/errors/505");
                }
            });
        }
        event.preventDefault();
    });
});