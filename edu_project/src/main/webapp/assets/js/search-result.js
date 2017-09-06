 function clickCover(me){
	 if ($("#right_panel").css("display") == "block"){
		 return;
	 }
	 $(".book-attr0").each(function () { 
		 $(this).fadeOut(300);
     })
     var mySerial = $(me).attr("serial");
	 $(".book-attr" + mySerial).each(function () { 
		 $(this).fadeIn(1000);
     })
 }
 
 function showPageContent(me){
	 //$("#main_panel").animate({margin:'0% 0% 0% 3%'});
	 var fileName = $(me).attr("title");
	 $("#main_panel").css("margin-left","3%");
     $("#right_panel").css("display","block");
     $("#iframe_panel").empty();
	 $("#iframe_panel").append('<iframe id="displayPdfIframe" width="100%" height="100%" src="loadPpfJsIframe.htm?file='+fileName+'"></iframe>');
     
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
	 var span = $(btn).parent().parent().find("div[class='item-num']").find("span");
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
 

 function refreshList(currentPage){
	  $.ajax({
		  url : WEB_ROOT + "/refreshList.htm",
   		 type: 'get',
   		 data:{"currentPage":currentPage},
   		 success: function (data) {
   			 refreshTbody(data);
   		 },
   		 error: function () {
   			 
   		 }
	  });
 };
 
 function refreshTbody(data)
 {
	  var dataJson = JSON.parse(data);
	  var page = dataJson[0];
	  $("table").empty();
	  var $tbody = $("<tbody></tbody>");
	  for(var i=0; i<page.list.length;i++){
		  var book = page.list[i],
		  coverPath = WEB_ROOT+book.cover,
		  index = i + 1;
		  $tbody.append(
				 "<tr class='column_'>" +
			       "<td class='cover-td' rowspan='2'>" +
			       		"<div class='book-cover'>" +
			       			"<img onclick='clickCover(this);' alt='' src='"+coverPath+"' serial='"+index+"'>" +
			       		"</div>" +
			       "</td>" +
			       "<td class='book-tbl-td' rowspan='1'><span class='book-attr0 book-attr"+index+"'>《"+book.bookName+"》</span></td>" +
			       "<td class='book-tbl-td' rowspan='1'><span class='book-attr0 book-attr"+index+"'>"+book.author+"</span></td>" +
			       "<td class='book-tbl-td' rowspan='1'><span class='book-attr0 book-attr"+index+"'>"+book.publisher+"</span></td>" +
			       "<td class='book-tbl-td' rowspan='1'><span class='book-attr0 book-attr"+index+"'>本书含"+book.content.length+"条搜索结果，当前是</span></td>" +
			    "</tr>"
			).append(
				"<tr class='column_'>" +
			       "<td class='' colspan='4' style='height:80px;'>" +
			       		"<div class='item-text book-attr0 book-attr"+index+"'>" +
							"<span class='content-span' style='margin:20px'>"+book.content[0]+"</span>" +
						"</div>" +
						"<div class='item-option-btn book-attr0 book-attr"+index+"'>" +
							"<div class='item-option'>" +
								"<div class='item-arrow'>" +
									"<img onclick='btnDecrease(this,"+book.bookId+")' alt='' src='"+WEB_ROOT+"/assets/img/arrow_up.png' style='cursor:pointer;'>" +
								"</div>" +
								"<div class='item-num'>第<span>1</span>条</div>" +
								"<div class='item-arrow'>" +
									"<img onclick='btnIncrease(this,"+book.bookId+","+book.content.length+")' alt='' src='"+WEB_ROOT+"/assets/img/arrow_down.png' style='cursor:pointer;'>" +
								"</div>" +
							"</div>" +
							"<div class='arrow-right'>" +
								"<div class='arrow-right-black'></div>" +
								"<img onclick='showPageContent(this)' title='"+book.bookName+"' alt='' src='"+WEB_ROOT+"/assets/img/arrow_right.png' style='cursor:pointer;'>" +
							"</div>" +
						"</div>" +
			       "</td>" +
			    "</tr>"
			).append(
				"<tr style='height:10px;'></tr>"
			);
	  }
	  
	  $("table").append($tbody);
	  
	  $(".book-attr1").css("display","block");
 }