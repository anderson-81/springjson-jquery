$(document).ready(function () {
    function Token() {
        $.ajax({
            url: "/token",
            method: "PUT",
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                $("#token").val(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $(location).attr("href", "/errors/505");
            }
        });
    }
    Token();
    $("#errors").hide();
    $("form").submit(function (event) {
        $("#errors").hide();
        $("li").remove();
        $("form :input").prop("disabled", true);
        var jForm = new FormData();
        jForm.append("token", $('#token').val());
        jForm.append("name", $('#name').val());
        jForm.append("email", $('#email').val());
        jForm.append("salary", $('#salary').val());
        jForm.append("birthday", $('#birthday').val());
        jForm.append("gender", $('#gender').val());
        if (document.getElementById("picture").files.length > 0) {
            jForm.append("picture", $('#picture').get(0).files[0]);
        }
        $.ajax({
            url: "/person/new",
            type: "POST",
            data: jForm,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function (data, textStatus, jqXHR) {
                var result = JSON.parse(data);
                if (result.alert !== undefined) {
                    $("body").append("<p>" + result.alert + "</p>");
                    alert(result.alert);
                }
                if (result.errors !== undefined) {
                    $("#errors").show();
                    $.each(result.errors, function (idx, error) {
                        $("#errors > ul").append("<li>" + error + "</li>");
                    });
                    $("form :input").prop("disabled", false);
                    Token();
                }
                if (result.page !== undefined) {
                    $(location).attr("href", result.page);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $(location).attr("href", "/errors/505");
            }
        });
        event.preventDefault();
    });
});