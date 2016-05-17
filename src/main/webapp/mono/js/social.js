/**
 * Created by dxt on 16/5/17.
 */

var social = {};

social.focusUser = function(){

}

social.focusSite = function(){

}

social.upArticle = function(article_id){
    var user_id =  commonJs.user_info.id;

    $.get("/mono/article/up",{article_id:article_id,visitor_id:user_id},function(){
        alert("操作成功!");
    })
}

social.upComment = function(){

}

social.collect = function(article_id){

    var user_id =  commonJs.user_info.id;

    $.get("/mono/article/collect",{article_id:article_id,visitor_id:user_id},function(){
        alert("操作成功!");
    })
}
