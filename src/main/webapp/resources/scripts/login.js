$(document).ready(function () {
    $("#errors").hide();
    $.ajax({
        url: "token",
        method: "PUT",
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            $("#token").val(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(location).attr("href", "/errors/505");
        }
    });

    $("form").submit(function (event) {
        $("form :input").prop("disabled", true);
        $("#errors").hide();
        $("li").remove();
        var datas = {
            username: $("#username").val(),
            password: $("#password").val(),
            token: $("#token").val()
        };
        $.ajax({
            url: "login",
            type: "POST",
            data: datas,
            async: false,
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
                $("form :input").prop("disabled", false);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $(location).attr("href", "/errors/505");
            }
        });
        event.preventDefault();
    });
});