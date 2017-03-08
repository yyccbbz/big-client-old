/**
 * Created by wuchh on 5/9/16.
 */





/**
 * 获取用户列表数据
 */
function listData(pageDataObj) {
    var tbody = $("#fill-list-data-async");
    var webAppRoot = "/" + window.location.pathname.substr(1).split('/')[0];
    currPage= pageDataObj.currPage;//当前页号

    $("#currPage").val(currPage);
    var pageSize= pageDataObj.pageSize;//当前页面显示条数
    var totalNum= pageDataObj.totalNum;//数据总数
    var dataList= pageDataObj.list;//当前页面元素集
    tbody.empty();
    if (dataList != null && dataList.length > 0) {
    } else {
        return;
    }
    for (var i = 0; i < dataList.length; i++) {

        var fid = dataList[i].fid;
        var filename = dataList[i].filename;
        var alias = dataList[i].alias;
        var user_count = dataList[i].user_count;
        var created_ts = dataList[i].created_ts;
        var comments = dataList[i].comments;
        if(null==comments) {
            comments = "";
        }

        var owner = dataList[i].owner;
        if(null == owner) {
            owner = "";
        }
        var share = dataList[i].share;
        var myCreated = dataList[i].myCreated;


        var trObj = $("<tr id='row_idx_" + i + "'></tr>");  // keep the id

        if (i % 2 != 0) {
            trObj.attr("class", "active");
        }


        trObj.append("<td hidden='hidden' class='td_col_0_id_hide'>" + fid + "</td>");

        trObj.append("<td class='tbody-td td_col_A_import_alias'  style='text-align:left;padding-left:40px;'>" + alias + "</td>");  //导入列表名
        trObj.append("<td class='tbody-td td_col_B_import_comments' style='text-align:center;'>" + comments + "</td>"); // 描述
        trObj.append("<td class='tbody-td td_col_C_import_user_count'  style='text-align:center;'>" + user_count + "</td>");//用户数目
        trObj.append("<td class='tbody-td td_col_D_import_time' style='text-align:center;'>" + created_ts + "</td>");  //导入时间
        trObj.append("<td class='tbody-td td_col_E_import_owner' style='text-align:center;'>" + owner + "</td>");  //创建人



        // ----操作
        // 共享
        // 导出
        // 删除
        var td_tag_op = $("<td class='tbody-td td_col_F_import_op'  style='padding-left: 35px; cursor: default;'></td>");
        var div_ops_all = $("<div align='center' valign='middle' style='float: left; width:100%'></div>");
        td_tag_op.append(div_ops_all);
        trObj.append(td_tag_op);

/////////// 1, share public
        var div_1_share = $("<div class='div_tag_cover_mini_chart' ></div>" );
        div_ops_all.append(div_1_share);

        if(!myCreated) {
            div_1_share.append("<label class='sprite-img-first file-icon-share' title='共享' ></label>");
        } else {
            if(share) {
                div_1_share.attr("onclick", 'disableShare(' + fid + ',"' +  alias + '")');
                div_1_share.append("<label class='sprite-img-first file-icon-share' title='点击取消共享' ></label>");
            } else {
                div_1_share.attr("onclick", 'enableShare(' + fid + ',"' +  alias + '")');
                div_1_share.append("<label class='sprite-img-first file-icon-private' title='点击设置共享' ></label>");
            }
        }

/////////// 2, export
        var div_2_export = $("<div valign='middle' class='div_tag_favo_icon' ></div>");
        div_ops_all.append(div_2_export);

        div_2_export.attr("onclick", 'exportFile(' + fid + ',"' +  alias + '")');
        div_2_export.append("<label class='sprite-img-first file-icon-export'  title='导出'></label>");

/////////// 3, delete

        if(myCreated) {
            var div_3_delete = $("<div valign='middle' class='div_tag_favo_icon' ></div>");
            div_ops_all.append(div_3_delete);

            div_3_delete.attr("onclick", 'deleteFile(' + fid + ',"' +  alias + '")');
            div_3_delete.append("<label class='sprite-img-first file-icon-delete'  title='删除'></label>");
        }

        tbody.append(trObj);

    }

}




function popupOperation(opObj,event){

    var share = $(opObj).attr("share");
    var fid =      $(opObj).attr("fid");

    $('#operationDiv').remove();
    if(isOpenCampId!=''&&isOpenCampId==camp_Id){//二次点击,关闭
        isOpenCampId = '';
        isOpen = false;
        operClick = false;
        return ;
    }else{
        isOpen = true;
        operClick = true;
    }

//	if(isOpen){
//		isOpen = false;
//		operClick = false;
//	}else{
//		isOpen = true;
//		operClick = true;
//	}

//	alert("showOperation after "+operClick+"  "+isOpen);

    isOpenCampId = camp_Id;


//	if(isOpen){
//		$('#operationDiv').remove();
//		isOpen = false;
//	}else{
    var showColumNum = 1;
//		var parentObj = $(opObj).parent();
    var parentObj = $("body");
    var operationDiv = $("<div id='operationDiv' class='oprtDiv'></div>");
    operationDiv.css("margin-left",'89%');
    operationDiv.css("margin-top",event.pageY+15);
    operationDiv.append("<div id='"+camp_Id+"' operType='copy' onclick='doOperation(this)'>复制</p></div>");

    var userId = $("#userId").val();
    if(userId==creatUser){//非创建用户除了复制和审批,无其他权限
        if(status=='01'||status=='10'){//只有未提交&已驳回的活动可编辑
            var userId = $("#userId").val();
            if(userId==creatUser){//非创建用户不允许修改
                operationDiv.append("<div id='"+camp_Id+"' operType='edit' onclick='doOperation(this)'>编辑</div>");
                showColumNum++;
            }
        }
        if(status=='02'||status=='03'){//只有审批中(提交审批)&待发布(已审批) 显示终止
            operationDiv.append("<div id='"+camp_Id+"' operType='stop' onclick='doOperation(this)'>终止</div>");
            showColumNum++;
        }

        if(status=='01'||status=='10'){//只有未提交&驳回可删除
            operationDiv.append("<div id='"+camp_Id+"' operType='delete' onclick='doOperation(this)'>删除</div>");
            showColumNum++;
        }
    }

    operationDiv.css("height",showColumNum*40+'px');
    parentObj.append(operationDiv);
//		isOpen = true;
//	}

}


function uploadUsers() {

    var request = new FormData();
    $.each(context.prototype.fileData, function(i, obj) { request.append(i, obj.value.files[0]); });
    request.append('action', 'upload');
    request.append('id', response.obj.id);
    $.ajax({

        type        : 'POST',
        url     : context.controller,
        data        : request,
        processData : false,
        contentType : false,
        success     : function(r) {
            console.log(r);
            //if (errors != null) { } else context.close();

        },

        error       : function(r) { alert('jQuery Error'); }

    });


}



$(function(){

    var browseText = $("#browseText");
    browseText.css({"opacity":"0","width":"278px","height":"24px"});

});

function setFilePath(){
    $("#browseText").val(document.forms["uploadForm"].upload.value);
};

 