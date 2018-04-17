$(document).ready(function () {
    $.ajax({
        url: "/person/list",
        type: 'PUT',
        async: false,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data !== undefined) {
                $("table").show();
                $.each(data, function (idx, obj) {
                    $("tbody").append("<tr><td class=\"col-md-2 text-center\">" + obj.id + "</td><td class=\"col-md-3\">" + obj.name + "</td><td class=\"col-md-3 text-center\">" + obj.birthday + "</td><td class=\"col-md-2 text-center\"><a id=\"lnkSelect\" href=\"/person/edit/" + obj.id + "\"><img class=\"icon-edit\" src=\"../../../resources/images/icons/edit.png\" alt=\"\"/></a></td></tr>");
                });
                $('table').on().DataTable({
                    "aLengthMenu": [[1, 5, 10, -1], [1, 5, 10, "All"]],
                    "iDisplayLength": 5,
                    responsive: true
                });
                $('.dataTables_length').remove();
                $("input[type=search]").addClass("form-control col-md-6");
            } else {
                alert("Not found.");
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
            $(location).attr("href", "/errors/505");
            alert("erro!");
        }
    });
});