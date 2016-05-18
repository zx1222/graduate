
window.onload = function () {
    checkLogin();
    var search = window.location.search;
    var site_id = search.split("=")[1];
    var site_list = {};
    var page = 1;
    var is_flip = true;
    var is_request = true;
    console.log(site_id);
    $.get("/mono/site/detail?site_id=" + site_id, function (data) {
        site_list = data.data;
         fillSiteInfo(site_list.site_info);
        fillArticleContent(site_list.article_info);
        page=page+1;
    });
    var interflowimgData =
    {
        "data": [
            {"src": "zhuanfa.png"},
            {"src": "dianzan.png"},
            {"src": "like.png"},
            {"src": "pinglun.png"}
        ]
    };
    function fillSiteInfo(site_info) {
        // var site_info = site_list.site_info;
        var user_site_background = document.getElementById("user_site_background");
        user_site_background.style.backgroundImage = "url(" + site_info.site_cover + ")";

        var site_id = document.getElementById("site_id");
        site_id.innerHTML = site_info.site_name;

        var site_photo = document.getElementById("site_photo");
        site_photo.style.backgroundImage = "url("+site_info.site_photo+")";

        var site_describe = document.getElementById("site_describe");
        site_describe.innerHTML = site_info.site_descript;

        var user_photo = document.getElementById("user_photo");
        user_photo.style.backgroundImage = "url(" + site_info.user_photo + ")";

        var user_name = document.getElementById("user_id");
        user_name.innerHTML = site_info.user_name;

        var user_describe = document.getElementById("user_describe");
        user_describe.innerHTML = site_info.user_descript;
    }

    window.onscroll = function () {

        var article_info = [];
        if (checkFlag()&&is_flip&&is_request) {
            is_request = false;
            $.get("/mono/site/detail", {page: page, len: 5,site_id:site_id}, function (data) {
                console.log(article_info);
                if (article_info.length == 0) {
                    is_flip=false;
                    return;
                }
                is_request=true;
                fillArticleContent(article_info);
                console.log(article_info);
                page=page+1;
            });


            $.ajax({
                url:"/mono/site/detail",
                data:{page: page, len: 5,site_id:site_id},
                type:"GET",
                sync:false,

            })
        }
    }

    function fillArticleContent(article_info) {
        var parent = document.getElementById("content");
        for (var i = 0; i < article_info.length; i++) {
            var contentbox = document.createElement("div");
            contentbox.className = "contentbox";
            parent.appendChild(contentbox);

            var contentlink = document.createElement("a");
            contentlink.href="article_page.html?id="+article_info[i].id;
            contentbox.appendChild(contentlink);

            var articlecontent = document.createElement("div");
            articlecontent.className = "article-content";
            articlecontent.style.backgroundImage = "url(" + article_info[i].article_cover + ")";
            contentlink.appendChild(articlecontent);

            var time = document.createElement("div");
            time.className = "time";
            articlecontent.appendChild(time);

            var time_p = document.createElement("p");
            // time_p.innerHTML = article_info[i].time;
            time_p.innerHTML=(new Date(article_info[i].time).Format("yyyy-MM-dd hh:mm:ss"));
            time.appendChild(time_p);


            var articletitle = document.createElement("div");
            articletitle.className = "article-title";
            articletitle.innerHTML = article_info[i].articletitle;
            articlecontent.appendChild(articletitle);

            var articlesubhead = document.createElement("div");
            articlesubhead.className = "article-subhead";
            articlesubhead.innerHTML = article_info[i].articlesubhead;
            articlecontent.appendChild(articlesubhead);

            var interflow = document.createElement("div");
            interflow.className = "interflow";
            contentbox.appendChild(interflow);

            var interflowspan = document.createElement("span");
            interflow.appendChild(interflowspan);
            var interflowaforward = document.createElement("a");
            interflowspan.appendChild(interflowaforward);
            var interflowforwardimg = document.createElement("img");
            interflowforwardimg.src = "../img/homeimg/" + interflowimgData.data[0].src;
            interflowaforward.appendChild(interflowforwardimg);
            var interflowforwardcount=document.createElement("p");
            interflowforwardcount.innerHTML=article_info[i].forward_count;
            interflowaforward.appendChild(interflowforwardcount)

            var interflowspan = document.createElement("span");
            interflow.appendChild(interflowspan);
            var interflowaup = document.createElement("a");
            interflowaup.href = "javascript:social.upArticle("+article_info[i].id+")"
            interflowspan.appendChild(interflowaup);
            var  interflowupimg = document.createElement("img");
            interflowupimg.src = "../img/homeimg/" + interflowimgData.data[1].src;
            interflowaup.appendChild( interflowupimg);
            var interflowupcount=document.createElement("p");
            interflowupcount.innerHTML=article_info[i].up_count;
            interflowaup.appendChild(interflowupcount);

            var interflowspan = document.createElement("span");
            interflow.appendChild(interflowspan);
            var interflowalike = document.createElement("a");
            interflowalike.href="javascript:social.collect("+article_info[i].id+")"
            interflowspan.appendChild(interflowalike);
            var interflowlikeimg = document.createElement("img");
            interflowlikeimg.src = "../img/homeimg/" + interflowimgData.data[2].src;
            interflowalike.appendChild(interflowlikeimg);
            var interflowcollectioncount=document.createElement("p");
            interflowcollectioncount.innerHTML=article_info[i].collect_count;
            interflowalike.appendChild(interflowcollectioncount);

            var interflowspan = document.createElement("span");
            interflow.appendChild(interflowspan);
            var interflowacomment = document.createElement("a");
            interflowacomment.href="pinglun.html?id="+article_info[i].id;
            interflowspan.appendChild(interflowacomment);
            var interflowcommentimg = document.createElement("img");
            interflowcommentimg.src = "../img/homeimg/" + interflowimgData.data[3].src;
            interflowacomment.appendChild(interflowcommentimg);
            var interflowcommentcount=document.createElement("p");
            interflowcommentcount.innerHTML=article_info[i].comment_count;
            interflowacomment.appendChild(interflowcommentcount);
        }
    }

    function getChildBox(parent, content) {         /*用来获取content下每一个contentbox*/
        var parent = document.getElementById("content");
        var allcontent = parent.getElementsByTagName("*");
        var contentboxArr = [];
        for (var i = 0; i < allcontent.length; i++) {
            if (allcontent[i].className == content) {
                contentboxArr.push(allcontent[i]);
            }
        }
        return contentboxArr;
    }

    function checkFlag() {                         /*监听滚动条，用来判断是否要加载内容*/
        var parent = document.getElementById("content");
        var contentbox = getChildBox(parent, "contentbox");
        var pageheight = document.documentElement.clientHeight || document.body.clientHeight;
        var scrolltop = document.documentElement.scrollTop || document.body.scrollTop;
        var lastboxHeight = contentbox[contentbox.length - 1].offsetTop;
        // console.log(pageheight);
        // console.log(scrolltop);
        // console.log(lastboxHeight);
        if (lastboxHeight < pageheight + scrolltop) {
            return true;
        }
    }
}

