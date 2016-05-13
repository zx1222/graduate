
window.onload = function () {

    var site_list=[];
    $.get("../js/user_site.json",function(data){
        console.log(data);
        site_list = JSON.parse(data);
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
     var user_site_background=document.getElementById("user_site_background");
    user_site_background.style.backgroundImage="url("+site_list[i].site_info.sitecover+")";

    window.onscroll=function(){
        if(checkFlag()){
            var parent=document.getElementById("content");

            for(var i=0;i<site_list.length;i++){
                var user_site_background=document.getElementById("user_site_background");
                user_site_background.style.backgroundImage="url("+site_list[i].site_info.site_cover+")";

                var site_id=document.getElementById("site_id");
                site_id.innerHTML=site_list[i].site_info.site_name

                var site_photo=document.getElementById("site_photo");
                site_photo.backgroundImage="url("+site_list[i].site_info.site_photo+")";

                var site_describe=document.getElementById("site_describe");
                ssite_describe.innerHTML=site_list[i].site_info.sitedescribe;

                var user_photo=document.getElementById("user_photo");
                user_photo.backgroundImage="url("+site_list[i].site_info.user_photo+")";
                
                var user_name=document.getElementById("user_id");
                user_name.innerHTML=site_list[i].site_info.user_name;
                
                var user_describe=document.getElementById("user_describe");
                user_describe.innerHTML=site_list[i].site_info.user_describe;

                var contentbox=document.createElement("div");
                contentbox.className="contentbox";
                parent.appendChild(contentbox);
                
                var contentlink=document.createElement("a");
                contentbox.appendChild(contentlink);

                var articlecontent=document.createElement("div");
                articlecontent.className="article-content";
                articlecontent.style.backgroundImage="url("+site_list[i].article_info.article_cover+")";
                contentlink.appendChild(articlecontent);

                var time=document.createElement("div");
                time.className="time";
                articlecontent.appendChild(time);
                
                var time_p=document.createElement("p");
                time_p.innerHTML=(new Date(parseInt(parseInt(site_list[i].article_info.time)))).Format("yyyy-MM-dd hh:mm:ss");
                time.appendChild(time_p);
                
                
                var articletitle=document.createElement("div");
                articletitle.className="article-title";
                articletitle.innerHTML=site_list[i].article_info.article_title;
                articlecontent.appendChild(articletitle);

                var articlesubhead=document.createElement("div");
                articlesubhead.className="article-subhead";
                articlesubhead.innerHTML=site_list[i].article_info.article_subhead;
                articlecontent.appendChild(articlesubhead);

                var interflow=document.createElement("div");
                interflow.className="interflow";
                contentbox.appendChild(interflow);

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

