window.onload = function () {

    var article_list=[];
    $.get("../js/article.json",function(data){
        console.log(data);
        article_list = JSON.parse(data);
    });

    var interflowimgData=
    {"data":
        [
            {"src":"zhuanfa.png"},
            {"src":"dianzan.png"},
            {"src":"like.png"},
            {"src":"pinglun.png"}
        ]
    };


    window.onscroll=function(){
        if(checkFlag()){
            var parent=document.getElementById("content");

            for(var i=0;i<article_list.length;i++){
                var contentbox=document.createElement("div");
                contentbox.className="contentbox";
                parent.appendChild(contentbox);

                var authorcontent=document.createElement("div");
                authorcontent.className="authorcontent";
                contentbox.appendChild(authorcontent);

                var contentlink=document.createElement("a");
                authorcontent.appendChild(contentlink);

                var articlecontent=document.createElement("div");
                articlecontent.className="article-content";
                // articlecontent.style.cssText("background-image","url("../img/homeimg/"+art)")
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
                var interflowa=document.createElement("a");
                interflowspan.appendChild(interflowa);
                var interflowimg=document.createElement("img");
                interflowimg.src="../img/homeimg/"+interflowimgData.data[2].src;
                interflowa.appendChild(interflowimg);

                var interflowspan=document.createElement("span");
                interflow.appendChild(interflowspan);
                var interflowa=document.createElement("a");
                interflowspan.appendChild(interflowa);
                var interflowimg=document.createElement("img");
                interflowimg.src="../img/homeimg/"+interflowimgData.data[3].src;
                interflowa.appendChild(interflowimg);

            }
        }
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
    // console.log(pageheight);
    // console.log(scrolltop);
    // console.log(lastboxHeight);
    if(lastboxHeight<pageheight+scrolltop){
        return true;
    }
}
