$(function () {
    $('#salary').maskMoney({thousands: '', decimal: '.', allowZero: true});
    $('#salary').val('0.00');
    $('#birthday').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    });
});