window.onload = function () {

    var article_list=[];
    var p=1;
    var isFlip = true

    $.get("/mono/article/list",{page:p,len:5},function(data) {
        console.log(p);
        article_list = data.data;
        fillArticleList(article_list);
        p++;
        if (data.length > 0) {
            isFlip = true;
        }
    });


        window.onscroll=function(){
            if(checkFlag()&&isFlip){
                isFlip = false;
                console.log("scroll");
                $.get("/mono/article/list",{page:p,len:5},function(data){
                    console.log(p);
                    article_list = data.data;
                    fillArticleList(article_list);
                    p++;
                    if(data.length>0) {
                        isFlip = true;
                    }

                });
            }
        }
}


function fillArticleList(article_list){

    var interflowimgData=
    {"data":
        [
            {"src":"zhuanfa.png"},
            {"src":"dianzan.png"},
            {"src":"like.png"},
            {"src":"pinglun.png"}
        ]
    };

    var parent=document.getElementById("content");

    for(var i=0;i<article_list.length;i++){
        var contentbox=document.createElement("div");
        contentbox.className="contentbox";
        parent.appendChild(contentbox);

        var authortitle=document.createElement("div");
        authortitle.className="author-title";
        contentbox.appendChild(authortitle);

        var authorphoto=document.createElement("div");
        authorphoto.className="authorphoto";
        authortitle.appendChild(authorphoto);

        var authorphotoimg=document.createElement("img");
        authorphotoimg.src=article_list[i].user_info.userphoto;
        authorphoto.appendChild(authorphotoimg);

        var nameAndtime=document.createElement("div");
        nameAndtime.className="nameAndtime";
        authortitle.appendChild(nameAndtime);

        var name=document.createElement("span");
        name.className="name";
        name.innerHTML=article_list[i].user_info.name;
        nameAndtime.appendChild(name);

        var time=document.createElement("span");
        time.className="time";
        time.innerHTML=(new Date(parseInt(parseInt(article_list[i].article_data.time)))).Format("yyyy-MM-dd hh:mm:ss");
        nameAndtime.appendChild(time);

        var sort=document.createElement("div");
        sort.className="sort";
        sort.innerHTML=article_list[i].article_data.sort;
        authortitle.appendChild(sort);

        var sortname=document.createElement("span");
        sort.appendChild(sortname);

        var authorcontent=document.createElement("div");
        authorcontent.className="authorcontent";
        contentbox.appendChild(authorcontent);

        var contentlink=document.createElement("a");
        contentlink.href="article_page.html?id="+article_list[i].article_data.id;
        authorcontent.appendChild(contentlink);

        var articlecontent=document.createElement("div");
        articlecontent.className="article-content";
        $(articlecontent).css("background-image","url("+article_list[i].article_data.article_cover+")")
        console.log(article_list[i].article_cover);

        contentlink.appendChild(articlecontent);

        var articletitle=document.createElement("div");
        articletitle.className="article-title";
        articletitle.innerHTML=article_list[i].article_data.articletitle;
        articlecontent.appendChild(articletitle);

        var articlesubhead=document.createElement("div");
        articlesubhead.className="article-subhead";
        articlesubhead.innerHTML=article_list[i].article_data.articlesubhead;
        articlecontent.appendChild(articlesubhead);

        var interflow=document.createElement("div");
        interflow.className="interflow";
        authorcontent.appendChild(interflow);

        var interflowspan=document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowa=document.createElement("a");
        interflowspan.appendChild(interflowa);
        var interflowimg=document.createElement("img");
        interflowimg.src="../img/homeimg/"+interflowimgData.data[0].src;
        interflowa.appendChild(interflowimg);

        var interflowspan=document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowa=document.createElement("a");
        interflowspan.appendChild(interflowa);
        var interflowimg=document.createElement("img");
        interflowimg.src="../img/homeimg/"+interflowimgData.data[1].src;
        interflowa.appendChild(interflowimg);

        var interflowspan=document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowa=document.createElement("p");
        interflowspan.appendChild(interflowa);
        var interflowimg=document.createElement("img");
        interflowimg.src="../img/homeimg/"+interflowimgData.data[2].src;
        interflowa.appendChild(interflowimg);

        var interflowspan=document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowa=document.createElement("p");
        interflowspan.appendChild(interflowa);
        var interflowimg=document.createElement("img");
        interflowimg.src="../img/homeimg/"+interflowimgData.data[3].src;
        interflowa.appendChild(interflowimg);

    }
}

function getChildBox(parent,content){         /*用来获取content下每一个contentbox*/
    var parent=document.getElementById("content");
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
    var parent = document.getElementById("content");
    var contentbox=getChildBox(parent,"contentbox");
    var pageheight = document.documentElement.clientHeight||document.body.clientHeight;
    var scrolltop = document.documentElement.scrollTop||document.body.scrollTop;
    var lastboxHeight =contentbox[contentbox.length-1].offsetTop;

    if(lastboxHeight<pageheight+scrolltop){
        return true;
    }
}
