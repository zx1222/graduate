window.onLoad=function () {
    var collection_box=document.getElementById("collection_box");
    var user_id = commonJs.user_info.id;
    var article_id = window.location.search.split("=")[1];

    $.get("mono/article/collect",{article_id:article_id,visitor_id:user_id},function(data){
        var collect_list=data.data;
        console.log(collect_list);

        var parent=document.getElementById("colllection_box");
        for(var i in collect_list){
            var articletbox=document.createElement("div");
            articletbox.className="articletbox";
            parent.appendChild(articletbox);

            var article_cover=document.createElement("div");
            article_cover.className="article_cover";
            article_cover.style.backgroundImage="url("+collect_list.article_cover+")";
            articletbox.appendChild(article_cover);

            var article_text=document.createElement("div");
            article_text.className="article_text";
            articletbox.appendChild(article_text);

            var article_title=document.createElement("div");
            article_title.className="article_title";
            article_title.innerHTML=collect_list.article_title;
            article_text.appendChild(article_title);

            var article_subhead=document.createElement("div");
            article_subhead.className="article_subhead";
            article_subhead.innerHTML=collect_list.article_subhead;
            article_text.appendChild(article_subhead);

        }
    });
    }
