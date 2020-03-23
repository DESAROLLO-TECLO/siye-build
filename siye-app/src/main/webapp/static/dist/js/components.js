$(function() {
	
	var typingTimer; 
	var doneTypingInterval = 300;
	
	/*TABLAS PRIMER PANTALLA*/
    $('body').on('keyup paste',"#searchsomething", function(){
    	DOMMessage();
    	$('.fixed-table-loading').css("display", "block");
    	clearTimeout(typingTimer);
	  	typingTimer = setTimeout(doneTyping, doneTypingInterval);
	});
	
	$('body').on('keydown',"#searchsomething", function(){
    	 clearTimeout(typingTimer);
	});
	
	function doneTyping () {
		$('.fixed-table-loading').css("display", "none");
	}
	
	function DOMMessage(){
		var divMessage = $('div.table-responsive').children('div.fixed-table-loading');
		
		if(divMessage.length === 0){
			$('<div class="fixed-table-loading"><label>Buscando coincidencias...</label></div>').appendTo('div.table-responsive');
		}
	}
	
	/*TABLAS MODAL*/
	 $('body').on('keyup paste',"#searchModal", function(){
		DOMMessageModal();
    	$('.mdlfixed').css("display", "block");
    	clearTimeout(typingTimer);
	  	typingTimer = setTimeout(doneTypingModal, doneTypingInterval);
	});
		
	$('body').on('keydown',"#searchModal", function(){
    	 clearTimeout(typingTimer);
	});
	
	function DOMMessageModal(){
		var divMessage = $('div.modalparent').children('div.fixed-table-loading');
		
		if(divMessage.length === 0){
			$('<div class="fixed-table-loading mdlfixed"><label>Buscando coincidencias...</label></div>').appendTo('div.modalparent');
		}
	}
	
	function doneTypingModal () {
		$('.fixed-table-loading').css("display", "none");
	}	
	
//	$('body').on('mousemove', function(){
//		
//		$('.content').attr("id", "contentScroll");
//		
//		$('#contentScroll').slimScroll({
//			height: '100%',
//	        color: '#abb2b9',
//	        opacity: .7,
//	        size: "7px",
//	        alwaysVisible: true,
//	        allowPageScroll : true
//	    });
//	});
});