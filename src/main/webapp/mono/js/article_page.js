var article=  {
    fillArticlePage:function (article_page) {
        var site_photo=document.getElementById("site_photo");
        var site_name=document.getElementById("site_name");
        var create_time=document.getElementById("create_time");
        var article_cover=document.getElementById("article_cover");
        var title=document.getElementById("title");
        var subhead=document.getElementById("subhead");
        var site_name = document.getElementById("site_name");
        var main_content = document.getElementById("main_content");
        var site_photo_url = document.getElementById("site_photo_url");

        site_photo_url.href  = "user_site.html?site_id="+article_page.site_id;

        site_photo.style.backgroundImage="url("+article_page.site_icon+")";
        site_name.innerHTML=article_page.author_name;
        // create_time.innerHTML=article_page.article_time;
        create_time.innerHTML=(new Date(article_page.article_time).Format("yyyy-MM-dd hh:mm:ss"));
        article_cover.style.backgroundImage="url("+article_page.article_cover+")";
        title.innerHTML=article_page.title;
        subhead.innerHTML=article_page.subhead;
        site_name.innerHTML = article_page.author;
        main_content.innerHTML = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+decodeURI(article_page.main_content);
    },
    getArticleData:function (func,id) {
        var data;
        $.get("/mono/article/detail?id="+id, function (data) {
            console.log(data.data);
            func(data.data);
        })
    }
};
window.onload = function () {
    var search = window.location.search;
    var article_id = search.split("=")[1];
    console.log(article_id);

    article.getArticleData(article.fillArticlePage,article_id);
}