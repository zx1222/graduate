var article=  {
    fillArticlePage:function (article_page) {
        var author_name=document.getElementById("author_name");
        var article_time=document.getElementById("article_time");
        var article_cover=document.getElementById("article_cover");
        var title=document.getElementById("title");
        var subhead=document.getElementById("subhead");
        var author = document.getElementById("author");
        var main_content = document.getElementById("main_content");

        author_name.innerHTML=article_page.author_name;
        article_time.innerHTML=article_page.article_time;
        article_cover.style.backgroundImage="url("+article_page.article_cover+")";
        title.innerHTML=article_page.title;
        subhead.innerHTML=article_page.subhead;
        author.innerHTML = article_page.author;
        main_content.innerHTML = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+decodeURI(article_page.main_content);
    },
    getArticleData:function (func) {
        var data;
        $.get("/mono/article/detail?id=21", function (data) {
            console.log(data.data);
            func(data.data);
        })
    }
};
window.onload = function () {
    article.getArticleData(article.fillArticlePage);
}