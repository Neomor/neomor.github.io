$(function() {

    /**
     * Initialize option for slider
     */
    $("#slider").slider({
        min: 10,
        max: 10000,
        step: 10,
        value: [1000, 9000],
        tooltip: "hide"
    }).on("slide", function(ev){
        $("#startPrice").val(ev.value[0]);
        $("#endPrice").val(ev.value[1]);
    });

    /**
     * Initialize option for datepicker
     */
    $("#datepicker").datepicker({
        autoclose: true
    });

    $("#datepicker").datepicker('setDate', new Date());

    $( "#calendar" ).click(function() {
        $("#datepicker").datepicker('show');
    });

    /**
     * Initialize option for spinedit
     */
    $("#spinEdit").spinedit({
        minimum: 1,
        maximum: 100,
        value: 3,
        step: 1
    });

});