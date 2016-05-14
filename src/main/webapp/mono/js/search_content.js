window.onload = function () {

    var search_content=[];
    $.get("../js/search_content.json",function(data){
        console.log(data);
        search_content = JSON.parse(data);
    });


    window.onscroll=function () {
        if(checkFlag()){
            var parent=document.getElementById("search_content");

            for(var i=0;i<search_content.length;i++){
                var search_contentbox=document.createElement("div");
                search_contentbox.className="search_contentbox";
                parent.appendChild(search_contentbox);

                var article_cover=document.createElement("div");
                article_cover.className="article_cover";
                article_cover.style.backgroundImage="url("+search_content[i].article_cover+")";
                search_contentbox.appendChild(article_cover);

                var article_text=document.createElement("div");
                article_text.className="article_text";
                search_contentbox.appendChild(article_text);

                var search_article_title=document.createElement("div");
                search_article_title.className="search_article_title";
                search_article_title.innerHTML=search_content[i].search_article_title;
                article_text.appendChild(search_article_title);

                var search_article_subhead=document.createElement("div");
                search_article_subhead.className="ssearch_article_subhead";
                search_article_subhead.innerHTML=search_content[i].search_article_subhead;
                article_text.appendChild(search_article_subhead);

            }
        }
    }
}

function getChildBox(parent,content){         /*用来获取content下每一个contentbox*/
    var parent=document.getElementById("search_content");
    var allcontent=parent.getElementsByTagName("*");
    var contentboxArr=[];
    for(var i=0;i<allcontent.length;i++){
        if(allcontent[i].className==content){
            contentboxArr.push(allcontent[i]);
        }
    }
    return contentboxArr;
}

function checkFlag(){                         /*监听滚动条，用来判断是否要加载内容*/
    var parent = document.getElementById("search_content");
    var contentbox=getChildBox(parent,"search_contentbox");
    var pageheight = document.documentElement.clientHeight||document.body.clientHeight;
    var scrolltop = document.documentElement.scrollTop||document.body.scrollTop;
    var lastboxHeight =contentbox[contentbox.length-1].offsetTop;
    console.log(pageheight);
    console.log(scrolltop);
    console.log(lastboxHeight);
    if(lastboxHeight<pageheight+scrolltop){
        return true;
    }
}

