function enableDatepicker(idInput, idDiv) {
    var date_input = $('input[id="idInput"]'); //our date input has the name "date"
    var temp = "div#".concat(idDiv);
    var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : temp;
    date_input.datepicker({
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: true,
        autoclose: true
    });
}