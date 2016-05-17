window.onload = function () {
    checkLogin();

    var article_list=[];
    var p=1;
    var isFlip = true

    $.get("/mono/article/list",{page:p,len:5},function(data) {
        console.log(p);
        article_list = data.data;
        fillArticleList(article_list);
        p++;
        if (article_list.length > 0) {
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

        var article_nav=document.createElement("div");
        article_nav.className="article_nav";
        contentbox.appendChild(article_nav);

        var site_photo=document.createElement("div");
        site_photo.className="site_photo";
        article_nav.appendChild(site_photo);

        var site_photoa=document.createElement("a");
        site_photoa.href="user_site.html?id="+article_list[i].site_info.site_Id;
        site_photo.appendChild(site_photoa);

        var sitephotoimg=document.createElement("img");
        sitephotoimg.src=article_list[i].site_info.icon_url;
        site_photoa.appendChild(sitephotoimg);

        var nameAndtime=document.createElement("div");
        nameAndtime.className="nameAndtime";
        article_nav.appendChild(nameAndtime);

        var site_name=document.createElement("span");
        site_name.className="site_name";
        site_name.innerHTML=article_list[i].site_info.site_name;
        nameAndtime.appendChild(site_name);

        var create_time=document.createElement("span");
        create_time.className="site_time";
        create_time.innerHTML=(new Date(parseInt(parseInt(article_list[i].article_data.time)))).Format("yyyy-MM-dd hh:mm:ss");
        nameAndtime.appendChild(create_time);

        var sort=document.createElement("div");
        sort.className="sort";
        sort.innerHTML=article_list[i].article_data.sort;
        article_nav.appendChild(sort);

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

        articlecontent.style.backgroundImage="url("+article_list[i].article_data.article_cover+")";
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

        var interflowspan = document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowaforward = document.createElement("a");
        interflowspan.appendChild(interflowaforward);
        var interflowforwardimg = document.createElement("img");
        interflowforwardimg.src = "../img/homeimg/" + interflowimgData.data[0].src;
        interflowaforward.appendChild(interflowforwardimg);

        var interflowspan = document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowaup = document.createElement("a");
        interflowaup.href="javascript:social.upArticle("+article_list[i].article_data.id+")"
        interflowspan.appendChild(interflowaup);
        var  interflowupimg = document.createElement("img");
        interflowupimg.src = "../img/homeimg/" + interflowimgData.data[1].src;
        interflowaup.appendChild( interflowupimg);

        var interflowspan = document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowalike = document.createElement("a");
        interflowalike.href="javascript:social.collect("+article_list[i].article_data.id+")"
        interflowspan.appendChild(interflowalike);
        var interflowlikeimg = document.createElement("img");
        interflowlikeimg.src = "../img/homeimg/" + interflowimgData.data[2].src;
        interflowalike.appendChild(interflowlikeimg);

        var interflowspan = document.createElement("span");
        interflow.appendChild(interflowspan);
        var interflowacomment = document.createElement("a");
        interflowacomment.href="pinglun.html?id="+article_list[i].article_data.id;
        interflowspan.appendChild(interflowacomment);
        var interflowcommentimg = document.createElement("img");
        interflowcommentimg.src = "../img/homeimg/" + interflowimgData.data[3].src;
        interflowacomment.appendChild(interflowcommentimg);

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
