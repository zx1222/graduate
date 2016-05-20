window.onload = function (){

    var s_str = window.location.search.split("=")[1];
    // var inserttxt=document.getElementById("searchbox").value;
    // console.log(inserttxt);
    $.get("/mono/article/search",{s_str:s_str},function(data){
        var article_list = data.data;
        search.fillArticle(article_list);
    });



    $.get("/mono/site/search",{s_str:s_str},function(data){
        var site_list = data.data;
        search.fillSite(site_list);
    });



}

var search = {};

search.fillArticle= function(article_list){
    console.log(article_list);
    var parent=document.getElementById("search_content");
    for(var i in article_list){
        var search_contentbox=document.createElement("div");
        search_contentbox.className="search_contentbox";
        parent.appendChild(search_contentbox);

        var article_cover=document.createElement("div");
        article_cover.className="article_cover";
        article_cover.style.backgroundImage="url("+article_list[i].cover_url+")";
        search_contentbox.appendChild(article_cover);

        var article_text=document.createElement("div");
        article_text.className="article_text";
        search_contentbox.appendChild(article_text);

        var search_article_title=document.createElement("div");
        search_article_title.className="search_article_title";
        search_article_title.innerHTML=article_list[i].title;
        article_text.appendChild(search_article_title);

        var search_article_subhead=document.createElement("div");
        search_article_subhead.className="search_article_subhead";
        search_article_subhead.innerHTML=article_list[i].title;
        article_text.appendChild(search_article_subhead);
    }
}




search.fillSite = function(site_list){
    console.log(site_list);
    var parent=document.getElementById("search_site");
    for(var i in site_list){
        var search_sitebox=document.createElement("div");
        search_sitebox.className="search_sitebox";
        parent.appendChild(search_sitebox);

        var site_photo=document.createElement("div");
        site_photo.className="site_photo";
        site_photo.style.backgroundImage="url("+site_list[i].icon_url+")";
        search_sitebox.appendChild(site_photo);

        var site_text=document.createElement("div");
        site_text.className="site_text";
        search_sitebox.appendChild(site_text);

        var site_id=document.createElement("div");
        site_id.className="site_id";
        site_id.innerHTML=site_list[i].site_name;
        site_text.appendChild(site_id);

        var site_describle=document.createElement("div");
        site_describle.className="site_describle";
        site_describle.innerHTML=site_list[i].descript;
        site_text.appendChild(site_describle);
    }

}

