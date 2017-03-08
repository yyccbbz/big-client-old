



/**
 * 获取系统现有标签&收藏标签数据
 * @param tagListData
 */
function getTagListData(tagListData){

    $("#tag-market").show();
    $("#tag-fav").hide();
    $("#tagMarketSpan").css("font-weight","bold");

    $("#tagFavSpan").click(function(){
        $("#tag-fav").show();
        $("#tagFavSpan").css("font-weight","bold");
        $("#tag-market").hide();
        $("#tagMarketSpan").css("font-weight","");
    });

    $("#tagMarketSpan").click(function(){
        $("#tag-fav").hide();
        $("#tagFavSpan").css("font-weight","");
        $("#tag-market").show();
        $("#tagMarketSpan").css("font-weight","bold");
    });

    $("#addTagGrpLb").click(function(){
        addTagGrp();
        clearCheckNode();
    });

    initFavList(tagListData.favTagList);
    initMarketList(tagListData.marketTagList);

    $("#tag-fav").mCustomScrollbar({});
    $("#tag-market").mCustomScrollbar({});

}




function show_deDuplicate_config_dialog() {

}