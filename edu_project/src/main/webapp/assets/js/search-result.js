 function clickCover(me){
	 if ($("#right_panel").css("display") == "block"){
		 return;
	 }
	 $(".book-info").each(function () { 
		 $(this).fadeOut(500);
     })
	 $(me).next().fadeIn(1500);
 }
 
 function showPageContent(bookTile){
	 //$("#main_panel").animate({margin:'0% 0% 0% 3%'});
	 $("#main_panel").css("margin-left","3%");
     $("#right_panel").css("display","block");
     //$("#right_panel").show();
//     var $iframe = $("<iframe><iframe/>",{
//		  "id":"displayPdfIframe",
//		  "width" : "100%",
//		  "height" : "100%",
//		  "src":"http://localhost:8080/edu-project/assets/pdf/LianChengJue.pdf"
//	  });
//     $("#iframe_panel").empty();
//	  $("#iframe_panel").append('<iframe id="displayPdfIframe" width="100%" height="100%" src="http://localhost:8080/edu-project/assets/pdf/LianChengJue.pdf"></iframe>');
	  //$('#displayPdfIframe').attr('src', $('#displayPdfIframe').attr('src'));
	  //document.frames("displayPdfIframe").document.location="http://localhost:8080/edu-project/assets/pdf/LianChengJue.pdf"; 
     
 }
 
 function hidePageContent(){
	 $("#main_panel").css("margin","0% 0% 0% 25%");
	 $("#right_panel").css("display","none");
	 //$("#main_panel").animate({margin:'0% 0% 0% 25%'});
	 //$("#right_panel").animate({margin:'0% 0% 0% 0%'});
	 //$("#right_panel").hide();
     //$("#main_panel").css("margin","0 auto");
     //$("#main_panel").css("float","inherit");
	 
 }
 
 function createRightPanel(){
	 $("#main_panel").after("<div id='right_panel'></div>");
	 $("#right_panel").append("<div class='right-panel-top'>").append("<div class='to-right-btn'>").append("<img onclick='hidePageContent()' src='"+WEB_ROOT+"/assets/img/arrow_carrot_left.png' style='cursor:pointer;'/>");
	 $(".right-panel-top").append("<div class='to-right-btn'>");
	 var $rightPanel = $("<div>");
	 $rightPanel.attr("id","right_panel");
 }
 
 function btnDecrease(btn,bookId){
	 if (itemNum <= 1){
		 return;
	 }
	 
	 var itemSpan = $(btn).parent().next().children()[0];
	 var itemNum = $(itemSpan).html();
     if (itemNum > 1) {
    	 $(itemSpan).html(itemNum - 1);
    	 var contentSpan = $(btn).parent().parent().parent().prev().children()[0];
    	 itemNum = parseInt(itemNum) - 2;
    	 $.ajax({
    		 url : WEB_ROOT + "/searchContByIdAndItem.htm",
    		 type: 'get',
    		 data:{"bookId":bookId,"itemNum":itemNum},
    		 success: function (data) {
    			 console.log(data);
    			 $(contentSpan).html(data);
    		 },
    		 error: function () {
    			 
    		 }
    	 })
     }
     
 }
 
 function btnIncrease(btn,bookId,itemLen){
	 var span = $(btn).parent().prev().children()[0];
	 var itemNum = $(span).html();
	 if (itemNum < parseInt(itemLen)) {
		 $(span).html(parseInt(itemNum) + 1);
		 var contentSpan = $(btn).parent().parent().parent().prev().children()[0];
		 $.ajax({
			 url : WEB_ROOT + "/searchContByIdAndItem.htm",
			 type: 'get',
			 data:{"bookId":bookId,"itemNum":itemNum},
			 success: function (data) {
				 console.log(data);
				 $(contentSpan).html(data);
			 },
			 error: function () {
				 
			 }
		 })
	 }
	 
 }