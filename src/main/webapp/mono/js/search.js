window.onload = function (){

    var s_str = window.search.split("=")[1];


    $.get("/mono/article/search",{s_str:"张国荣"},function(data){
        var article_list = data.data;
        search.fillArticle(article_list);
    });


    $.get("/mono/article/search",{s_str:"张国荣"},function(data){
        var site_list = data.data;
        search.fillArticle(site_list);
    });



}

var search = {};

search.fillArticle= function(article_list){
    console.log(article_list);
}

search.fillSite = function(site_list){

    console.log(site_list);


}

