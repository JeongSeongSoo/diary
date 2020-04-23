$(document).ready(function() {
	$('#summernote').summernote({
		height: 500,
		minHeight: null,
		maxHeight: null,
		focus: true,
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					sendFile(files[i], this);
				}
			}
		}
	});
	
	$("#pageSubmit").click(function() {
		$(".note-editable").find('img').each(function(){
			var src = $(this).attr('src'); 
			var path = src.split('/');
			var imgId = $(".imgId").val();
			if (imgId == "0") {
				$(".imgId").val(path[path.length - 1]);
			}
		});
		
		$("#pageUpdateForm").submit();
	});
	
	$("#cancelBtn").click(function() {
		location.href="/page/" + $("#category").val();
	});
});

function sendFile(file, el) {
	var form_data = new FormData();
	
	form_data.append('file', file);
	
	$.ajax({
		data: form_data,
		type: "POST",
		url: '/page/image/add',
		cache: false,
		contentType: false,
		enctype: 'multipart/form-data',
		processData: false,
		success: function(url) {
			$(el).summernote('editor.insertImage', url);
		}
	});
}