var Calendario = (function() {
    return {
        aplicarMascara : function() {
        	$('.Calendario').mask( "99/99/9999" );
        }
    };
})();

$(document).ready(function() {
	Calendario.aplicarMascara();
});
