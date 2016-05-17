window.onload = function () {
    
    var pinglun_list={};
    var is_flip = true;
    var page = 1;
    var article_id = window.location.search.split("=")[1];
    console.log(article_id);
    var user_info = JSON.parse($.cookie("user_info"));
    console.log(user_info);
    $.get("/mono/article/comment/list?",{"article_id":article_id,"page":page},function(data){
        console.log(data.data);
        pinglun_list = data.data;
        fillCountInfo();
        fillComment(pinglun_list);
        page++;
    });

    function fillCountInfo() {
        var newcomment_count=pinglun_list.comment_count;
        var newup_count=pinglun_list.up_count;
        var comment_count=document.getElementById("comment_count");
        comment_count.innerHTML=newcomment_count;
        var up_count=document.getElementById("up_count");
        up_count.innerHTML=newup_count;
    }
    
    $("#comment_submit").click(function(){
        var comment_content=$(".commenttext").val();
        console.log(comment_content);
        var visitor_id= user_info.id;
        $.get("/mono/article/comment",{"content":encodeURI(comment_content),
            "visitor_id":visitor_id,"article_id":article_id},function(data){
            if(data.status==0){
                alert("评论成功");
                window.location.reload();
            }

        });

    });


    window.onscroll=function () {
        if(checkFlag()&&is_flip){
            $.get("/mono/article/comment/list?",{"article_id":article_id,"page":page},function(data){
                console.log(data.data);
                pinglun_list = data.data;
                fillComment(pinglun_list);
                page++;
            });
        }
    }
    function fillComment(pinglun_list){
        var parent=document.getElementById("pinglun");
        var comment_list=pinglun_list.comment_list;
        console.log(comment_list);
        if(comment_list.length==0){
            is_flip=false
            return ;
        }
        for(var i=0;i<comment_list.length;i++){

            var pinglunbox=document.createElement("div");
            pinglunbox.className="pinglunbox";
            parent.appendChild(pinglunbox);

            var pinglunbox_title=document.createElement("div");
            pinglunbox_title.className="pinglunbox_title";
            pinglunbox.appendChild(pinglunbox_title);

            var userphoto=document.createElement("div");
            userphoto.className="userphoto";
            pinglunbox_title.appendChild(userphoto);

            var userphotoimg=document.createElement("img");
            userphotoimg.src=comment_list[i].head_url;
            userphoto.appendChild(userphotoimg);

            var username=document.createElement("div");
            username.className="username";
            username.innerHTML=comment_list[i].user_name;
            pinglunbox_title.appendChild(username);

            var time=document.createElement("div");
            time.className="time";
             time.innerHTML = checkDate(comment_list[i].create_time);
            //time.innerHTML=(new Date(Number(comment_list[i].create_time))).Format("yyyy-MM-dd hh:mm:ss");
            pinglunbox_title.appendChild(time);

            var dianzan=document.createElement("div");
            dianzan.className="dianzan";
            pinglunbox_title.appendChild(dianzan);

            var dianzan_eachcount=document.createElement("span");
            dianzan_eachcount.className="dianzan_eachcount";
            dianzan_eachcount.innerHTML=comment_list[i].up_count;
            dianzan.appendChild(dianzan_eachcount);

            var d_span=document.createElement("span");
            dianzan.appendChild(d_span);

            var d_span_a=document.createElement("a");
            d_span.appendChild(d_span_a);

            var d_span_a_img=document.createElement("img");
            d_span_a_img.src="../img/pinglunimg/like.png";
            d_span_a.appendChild(d_span_a_img);

            var pinglun_content=document.createElement("div");
            pinglun_content.className="pinglun_content";
            pinglunbox.appendChild(pinglun_content);

            var pc_p=document.createElement("p");
            pc_p.innerHTML=decodeURI(comment_list[i].content);
            pinglun_content.appendChild(pc_p);
        }
    }
}




function getChildBox(parent,content){         /*用来获取content下每一个contentbox*/
    var parent=document.getElementById("pinglun");
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
    var parent = document.getElementById("pinglun");
    var pinglunbox=getChildBox(parent,"pinglunbox");
    // console.log(pinglunbox.length);
    var pageheight = document.documentElement.clientHeight||document.body.clientHeight;
    var scrolltop = document.documentElement.scrollTop||document.body.scrollTop;
    var lastboxHeight =pinglunbox[pinglunbox.length-1].offsetTop;
    // console.log(pageheight);
    // console.log(scrolltop);
    // console.log(lastboxHeight);
    // console.log(pageheight+scrolltop-40);
    if(lastboxHeight<pageheight+scrolltop-40){
        return true;
    }
}

function checkDate(time){
    var timestamp = Date.parse(new Date());
    var diffStamp = (timestamp - time)/1000;

    console.log(timestamp+","+time+","+(timestamp-time));
    if(diffStamp<3600){
        return parseInt(diffStamp/60) +"分钟前";
    }else if(diffStamp<86400){
        return parseInt(diffStamp/3600) +"小时前";

    }else{
        return parseInt(diffStamp/86400) +"天前";
    }
    return 0;
}
