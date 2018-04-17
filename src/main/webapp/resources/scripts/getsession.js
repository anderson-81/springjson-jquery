$(document).ready(function () {
    $.ajax({
        url: "/getsession",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            if (data !== "null") {
                $("#lnkLogin").remove();
                $("#navleft").append("<li><a href=\"/person/index\" id=\"lnkPerson\">Person</a></li>");
                $("#navright").append("<li><a id=\"lnkLogout\" href=\"#\"><span></span>Logout</a></li>");
                $("#lnkLogout").attr("href", "#");
                $("#lnkLogout").click(function () {
                    if (confirm("Do you want logout?")) {
                        $.ajax({
                            url: "/logout",
                            type: 'GET',
                            success: function (data, textStatus, jqXHR) {
                                var result = JSON.parse(data);
                                alert(result.alert);
                                $(location).attr("href", "/index");
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $(location).attr("href", "/errors/505");
                            }
                        });
                    }
                });
                $("#nav").append("<h3>Welcome, " + JSON.parse(data) + "!</h3>");
            }
            if (data === "null") {
                $("#navright").append("<li><a id=\"lnkLogin\" href=\"/login\"><span></span>Login</a></li>");
                $("#lnkPerson").remove();
                $("#lnkLogout").remove();
                $("h3").remove();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $(location).attr("href", "/errors/505");
        }
    });
});