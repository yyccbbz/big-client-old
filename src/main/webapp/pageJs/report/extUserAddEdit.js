//取页面高度
var bodyHeight = $(document).height();// 整个BODY高度
$(document).ready(function () {

});

var flag = false;

//ajax校验手机号
function checkMobile() {
    //获取页面中的控件的输入的值  
    var mobile_no = $("#mobile_no").val();
    $.ajax({
        type: "Post",
        url: postUrl + "/report/ext/checkMobile.do",
        data: {mobile: mobile_no},
        //重要的后台的回调函数（很重要）
        success: function (data) {
            //返回提示给界面效果
            alert(data);
            // console.debug(msg);
        },
        //出错提示
        error: function (err) {
            alert(err);
            // console.debug(err);
        }
    });
}


function saveUser() {

    // checkMobile();

    $("#uperror").hide();
    var currPage = $("#currPage").val();
    var order_id = $("#order_id").val();
    var order_type = $("#order_type").val();
    var action = $("#action").val();
    var userInfoData = $("#addEditUserForm").serialize();

    jQuery.ajax({
        type: 'post',
        url: postUrl + '/report/ext/saveOneUser.do',
        data: userInfoData,
        success: function (data) {
            if (data.result == "success") {
                $("#uperror").show();
                $("#pwdmsg").removeClass();
                $("#pwdmsg").addClass("right-text");
                $("#pwdmsg").text(data.msg);
                alert("保存成功!");
                window.location = postUrl + "/report/ext/extList.do?currPage=" + currPage + "&order_id=" + order_id + "&order_type=" + order_type;
            } else if (data.result == "fail") {
                $("#uperror").show();
                $("#pwdmsg").removeClass();
                $("#pwdmsg").addClass("error-text");
                $("#pwdmsg").text(data.msg);
            }
        },
    });
}

function clickReturnBtn() {
    var currPage = $("#currPage").val();
    var order_id = $("#order_id").val();
    var order_type = $("#order_type").val();
    window.location = postUrl + "/report/ext/extList.do?currPage=" + currPage + "&order_id=" + order_id + "&order_type=" + order_type;
}