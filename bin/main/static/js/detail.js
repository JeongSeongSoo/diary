$(document).ready(function() {
	var pageId = $("#pageId").val();
	
	$("#updateBtn").click(function() {
		location.href="/page/" + pageId + "/update";
	});
	
	$("#deleteBtn").click(function() {
		location.href="/page/" + pageId + "/delete";
	});
});