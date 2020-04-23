$(document).ready(function() {
	var pageNum = $("#pageNum").val();
	
	$.ajax({
        url: "/page/category/daily/" + pageNum + "/load",
        type: "get",
        dataType: "JSON",
        data: null,
        success: function (data) {
        	var html = "";
        	var rows = data.rows;
        	var total = data.total;
        	var aCnt = 5;
        	var start = (Math.ceil(pageNum / aCnt) - 1) * aCnt + 1;
        	var end = Math.ceil(pageNum / aCnt) * aCnt;
        	var prev = (start - 1);
        	var next = end + 1; 
        	
        	if (total < end) {
        		end = total;
        	}
        	
        	for (var i = 0; i < rows.length; i++) {
        		if (i == 0 || i == 4) {
        			html += "<div class='row'>";
        		}
        		
        		html += "<div class='col-lg-3 col-md-3 col-sm-3 col-xs-12 page'>";
        		html += "<a href='/page/pageId/" + rows[i].pageId + "/load'>";
        		html += "<img src='/page/image/" + rows[i].imgId + "'/>";
        		html += "<span>" + rows[i].title + "</span>";
        		html += "</a>";
        		html += "</div>";
        		
        		if (i == 3 || i == 7) {
        			html += "</div>";
        		}
        	}
        	
        	$("#content").html(html);
        	
        	if (prev == 0) {
        		html = "";
        	} else {
        		html = "<a href='/page/" + prev + "/daily'><</a>"; 
        	}
        	
        	for (var i = start; i <= end; i++) {
        		html += "<a href='/page/" + i + "/daily'>" + i + "</a>";
        	}
        	
        	if (next < total) {
        		html += "<a href='/page/" + next + "/daily'>></a>";
        	}
        	
        	$("#pagination").html(html);
        },
        error:function(request,status,error){
        	alert("code = "+ request.status + " error = " + error); // 실패 시 처리
        }
    })
});