window.onresize = function() {//监控浏览器的窗口大小变化
	var mediaHeg = document.body.offsetHeight;
	var mediaWidth = document.body.offsetWidth;
	var canUseHeight = mediaHeg-735;
	var canUseWidth = mediaWidth-735;
	$("#logInfoDiv").css("margin-top",canUseHeight/2+"px");
	$("#logInfoDiv").css("margin-left",canUseWidth/2+"px");
};

$(document).ready(function(){
	var mediaHeg = document.body.offsetHeight;
	var mediaWidth = document.body.offsetWidth;
	var canUseHeight = mediaHeg-735;
	var canUseWidth = mediaWidth-735;
	$("#logInfoDiv").css("margin-top",canUseHeight/2+"px");
	$("#logInfoDiv").css("margin-left",canUseWidth/2+"px");
	$("#gthpng").hide();
});

if(window !=top){  
	top.location.href=location.href; 
}

function login(){
	$("#upbank").show();
	$("#uperror").hide();
	var username = $("#username").val();
	var password = $("#password").val();
	/*
	 * For Java script side demo: refer to
	 * http://sladex.org/blowfish.js/
	 * use
	 * Cipher mode= 0 "ECB"
	 * Output type= 1 "HEX"
	 */
	password = encodePassword( password );
	if(!validate()){
		return;
	}
	jQuery.ajax({
		type : 'post',
		url : postUrl + '/sys/login.do',
		data : 'username='+username+'&password='+password,
		success : function(result) {
			if(result.result=="success"){
				$("#uperror").show();
				$("#upbank").hide();
				$("#gthpng").hide();
				$("#pwdmsg").css("color","green");
				$("#pwdmsg").text(result.msg);
				window.location = postUrl + "/sys/mainframe.do";
			}else if(result.result=="errorpwd"){
				$("#uperror").show();
				$("#upbank").hide();
				$("#gthpng").show();
				$("#pwdmsg").css("color","red");
				$("#pwdmsg").text(result.msg);
			}
		},
	}); 
}

function validate(){
	var username=$("#username").val();
	var password=$("#password").val();
	if(username==null || username.trim()=="" ||password==null ||password.trim()==""){
			$("#uperror").show();
			$("#upbank").hide();
			$("#gthpng").show();
			$("#pwdmsg").css("color","red");
			$("#pwdmsg").text("用户名或密码不能为空");
			return false;
	}
	return true;
}
