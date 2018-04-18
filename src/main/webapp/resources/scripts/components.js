$(function () {
    $('#salary').maskMoney({thousands: '', decimal: '.', allowZero: true});
    $('#birthday').datepicker({
        format: 'dd/mm/yyyy',
        language: 'pt-BR',
        autoclose: true,
        endDate: "-18y",
        startDate: "-120y"
    });
});