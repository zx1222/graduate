/**
 * Created by dxt on 16/5/17.
 */

var user_info = JSON.parse(decodeURI($.cookie("user_info")));
var social = {};

social.focusUser = function(){

}

social.focusSite = function(){

}

social.upArticle = function(article_id,up_btn){
    var user_id =   user_info.id;
    console.log(up_btn);
    $.get("/mono/article/up",{article_id:article_id,visitor_id:user_id},function(){
        alert("操作成功!");
    })
    var pre_count = $(up_btn).children("p").html();
    $(up_btn).children("p").html(pre_count+1);
}

social.upComment = function(){

}

social.collect = function(article_id){

    var user_id =   user_info.id;

    $.get("/mono/article/collect",{article_id:article_id,visitor_id:user_id},function(){
        alert("操作成功!");
    })
}
