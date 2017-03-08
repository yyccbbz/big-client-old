//取页面高度
var bodyHeight = $(document).height();// 整个BODY高度
$(document).ready(function () {
    /*var otherHeight = bodyHeight - 170;
    var tableAllHeight = otherHeight * 0.8;// 整个TABLE占otherHeight的80%,如果80%的高度超过400px
    // 那么tableHeight设置为400px
    var btnAllHeight = otherHeight * 0.2;// 整个下方操作按钮占20%,如果20%的值超过50,那么btnAllHeight设置为50
    var tableDivHeight = tableAllHeight * 5 / 7;
    $("#centerdiv").css("height", tableDivHeight);
    $("#centerdiv").css("margin-top", tableAllHeight / 7);
    var actionValue = $("#action").val();
    var tableHeight = tableDivHeight;
    if (actionValue == 'add') {
        // 新增table高度设置
        if (tableDivHeight > 250) {
            tableHeight = 250;
        }
    } else {
        // 编辑table高度设置
        if (tableDivHeight > 100) {
            tableHeight = 100;
        }
        $("#centerdiv").css("height", tableAllHeight * 2 / 7);
    }
    if (btnAllHeight > 50)
        btnAllHeight = 50;

    $("#btn-div").css("height", btnAllHeight);
    $("#btn-tab").css("height", btnAllHeight);
    $("#center-tab").css("height", tableHeight);*/
});

function saveUser() {
    $("#uperror").hide();
    var currPage = $("#currPage").val();
    var order_id = $("#order_id").val();
    var order_type = $("#order_type").val();
    var action = $("#action").val();
    var userInfoData = $("#addEditUserForm").serialize();
    // if('add' == action){
    //
    // 	var password_text = $("#password").val();
    // 	var confpwd_text = $("#confpwd").val();
    //
    // 	userInfoData += "&password=" + encodePassword( password_text );
    // 	userInfoData += "&confpwd=" + encodePassword( confpwd_text );
    // }

    //alert(userInfoData);

    jQuery.ajax({
        type: 'post',
        // contentType : 'application/json',
        url: postUrl + '/report/ext/saveOneUser.do',
        data: userInfoData,
        // dataType : 'json',
        success: function (data) {
            if (data.result == "success") {
                $("#uperror").show();
                // $("#upbank").hide();
                $("#pwdmsg").removeClass();
                $("#pwdmsg").addClass("right-text");
                $("#pwdmsg").text(data.msg);
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

/**
window.onload=function(){
    var oTxt=document.getElementById('txt');
    var oBtn=document.getElementById('btn');

    var CheckIdCard={
        //Wi 加权因子 Xi 余数0~10对应的校验码 Pi省份代码
        Wi:[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2],
        Xi:[1,0,"X",9,8,7,6,5,4,3,2],
        Pi:[11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91],

        //检验18位身份证号码出生日期是否有效
        //parseFloat过滤前导零，年份必需大于等于1900且小于等于当前年份，用Date()对象判断日期是否有效。
        brithday18:function(sIdCard){
            var year=parseFloat(sIdCard.substr(6,4));
            var month=parseFloat(sIdCard.substr(10,2));
            var day=parseFloat(sIdCard.substr(12,2));
            var checkDay=new Date(year,month-1,day);
            var nowDay=new Date();
            if (1900<=year && year<=nowDay.getFullYear() && month==(checkDay.getMonth()+1) && day==checkDay.getDate()) {
                return true;
            };
        },

        //检验15位身份证号码出生日期是否有效
        brithday15:function(sIdCard){
            var year=parseFloat(sIdCard.substr(6,2));
            var month=parseFloat(sIdCard.substr(8,2));
            var day=parseFloat(sIdCard.substr(10,2));
            var checkDay=new Date(year,month-1,day);
            if (month==(checkDay.getMonth()+1) && day==checkDay.getDate()) {
                return true;
            };
        },

        //检验校验码是否有效
        validate:function(sIdCard){
            var aIdCard=sIdCard.split("");
            var sum=0;
            for (var i = 0; i < CheckIdCard.Wi.length; i++) {
                sum+=CheckIdCard.Wi[i]*aIdCard[i]; //线性加权求和
            };
            var index=sum%11;//求模，可能为0~10,可求对应的校验码是否于身份证的校验码匹配
            if (CheckIdCard.Xi[index]==aIdCard[17].toUpperCase()) {
                return true;
            };
        },

        //检验输入的省份编码是否有效
        province:function(sIdCard){
            var p2=sIdCard.substr(0,2);
            for (var i = 0; i < CheckIdCard.Pi.length; i++) {
                if(CheckIdCard.Pi[i]==p2){
                    return true;
                };
            };
        }
    };

    oBtn.onclick=function(){
        var sIdCard=oTxt.value.replace(/^\s+|\s+$/g,"");//去除字符串的前后空格，允许用户不小心输入前后空格
        if (sIdCard.match(/^\d{14,17}(\d|X)$/gi)==null) {//判断是否全为18或15位数字，最后一位可以是大小写字母X
            alert("身份证号码须为18位或15位数字");      //允许用户输入大小写X代替罗马数字的Ⅹ
        }
        else if (sIdCard.length==18) {
            if (CheckIdCard.province(sIdCard)&&CheckIdCard.brithday18(sIdCard)&&CheckIdCard.validate(sIdCard)) {
                alert("身份证号码合法");
            }
            else{
                alert("请输入有效的身份证号码");
            };
        }
        else if (sIdCard.length==15) {
            if (CheckIdCard.province(sIdCard)&&CheckIdCard.brithday15(sIdCard)) {
                alert("身份证号码合法");
            }
            else{
                alert("请输入有效的身份证号码");
            };
        };
    };
}
*/
