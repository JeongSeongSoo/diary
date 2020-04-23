$(document).ready(function() {
    // hide the menu when the page load
    $("#navigation-list").hide();
    // when .menuBtn is clicked, do this
    $(".menuBtn").click(function() {
        // open the menu with slide effect
        $("#navigation-list").slideToggle(300);
    });
});

function trim(val) {
	if (val == null) return "";
	return val.replace(/(^\s*)|(\s*$)/gi, "");
}

var InputUtil = {
	validUserId : function(userIdStr) {
    	/*
    	 * 6 ~ 15자 이내의 영문 소문자, 숫자, 특수문자 @, ., _, - 사용
    	 */
    	var str = userIdStr;
    	var regx = /^[0-9a-z@._-]{6,15}$/;

		return regx.test(str);
    },
    validPassword : function(passStr) {
    	/*
    	 * 영문, 숫자, 특수문자 중 세 가지 조합 : 9자 ~ 20자 사용 가능
		 *	사용가능특수문자 : ! " # $ % & '( ) * + , - . / : ; > = < ? @ [ \ ] ^ _ { | } ~
		 *	영문 대소문자 구분 / 아이디 재사용 불가
		 *	3자 이상 연속 영문 / 숫자 조합 불가 (ex: aaa, 111)
    	 */

    	var str = passStr;

		var anUpperCase = /[A-Z]/;
		var aLowerCase = /[a-z]/;
		var aNumber = /[0-9]/;
		var aSpecial = /^[!"#$%&'()*+,\-.\/:;>=<?@\[\\\]\^_{\|}~]$/;
		var alphaNumeric = /^[a-zA-Z0-9]$/;

		if(str.length < 9 || str.length > 20) return false;
		var numUpper = 0;
		var numLower = 0;
		var numNums = 0;
		var numSpecials = 0;
		for(var i=0; i<str.length; i++){
			if(anUpperCase.test(str[i])) numUpper++;
			else if(aLowerCase.test(str[i])) numLower++;
			else if(aNumber.test(str[i])) numNums++;
			else if(aSpecial.test(str[i])) numSpecials++;
		}

		if((numUpper + numLower) < 1 || numNums < 1 || numSpecials <1) return false;

		// 영자,숫자,특수문자를 제외한 문자 체크
		var numAlphaNumSpecial = numUpper + numLower + numNums + numSpecials;
		if (numAlphaNumSpecial != str.length) return false;

		// 3자 연속 동일 영자숫자 체크
		var pos = 0;
		while(pos < str.length-2) {
			var c = str[pos];
			var s = 1;

			if (!alphaNumeric.test(c)) {
				++pos;
				continue;
			}

			for(var j=(pos+1); j<str.length; j++) {
				++pos;
				if (str[j] == c) s++;
				else break;

				if (s >= 3) break;
			}

			if (s >= 3) return false;
		}

		return true;
    },
	validEmail : function(emailStr) {
		var str = trim(emailStr);

		var regx = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;

		return regx.test(str);
	}
}