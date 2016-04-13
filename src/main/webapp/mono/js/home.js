window.onload = function () {
        var contentimgData={"data":
        [
             {"src":"content1.jpg"},
             {"src":"content2.jpg"},
             {"src":"content3.jpg"},
             {"src":"content2.jpg"}
       ]
    }
        var authorimgData={"data":
        [
             {"src":"user.jpg"},
             {"src":"user2.jpg"},
             {"src":"user.jpg"},
             {"src":"user3.jpeg"}
       ]
    }
        var interflowimgData={"data":
        [
             {"src":"zhuanfa.png"},
             {"src":"dianzan.png"},
             {"src":"like.png"},
             {"src":"pinglun.png"}
       ]
    }
        var authornameData={"data":
        [
             {"name":"夜礼服假面"},
             {"name":"didadi"},
             {"name":"吃土的girl"},
             {"name":"北城以北"}
       ]
    }
        var contenttimeData={"data":
        [
             {"time":"2016.02.12 10:11发布"},
             {"time":"2016.02.15 14:21发布"},
             {"time":"2016.02.02 20:45发布"},
             {"time":"2016.02.21 21:34发布"}
       ]
    }
        var contentsortData={"data":
        [
             {"sort":"#电影#"},
             {"sort":"#动漫#"},
             {"sort":"#电影#"},
             {"sort":"#动漫#"}
       ]
    }
    var articleLoader = {
        page:1,
        len:5,
        getdata:function(){
            $.get("/mono/article/list",{page:page,len:len},function(data){
                console.log(data);
                this.loadData(data);
            })
        },
        loadData:function(data){
            if(checkFlag()){


                var parent=document.getElementById("content");

                for(var i=0;i<contentimgData.data.length;i++){
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
                    authorphotoimg.src="../img/homeimg/"+authorimgData.data[i].src;
                    authorphoto.appendChild(authorphotoimg);

                    var nameAndtime=document.createElement("div");
                    nameAndtime.className="nameAndtime";
                    authortitle.appendChild(nameAndtime);

                    var name=document.createElement("span");
                    name.className="name";
                    name.innerHTML=authornameData.data[i].name;
                    nameAndtime.appendChild(name);

                    var time=document.createElement("span");
                    time.className="time";
                    time.innerHTML=contenttimeData.data[i].time;
                    nameAndtime.appendChild(time);

                    var sort=document.createElement("div");
                    sort.className="sort";
                    sort.innerHTML=contentsortData.data[i].sort;
                    authortitle.appendChild(sort);

                    var sortname=document.createElement("span");
                    sort.appendChild(sortname);

                    var authorcontent=document.createElement("div");
                    authorcontent.className="authorcontent";
                    contentbox.appendChild(authorcontent);

                    var contentlink=document.createElement("a");
                    authorcontent.appendChild(contentlink);

                    var articlecontent=document.createElement("div");
                    articlecontent.className="article-content";
                    contentlink.appendChild(articlecontent);

                    var articletitle=document.createElement("div");
                    articletitle.className="article-title";
                    articlecontent.appendChild(articletitle);

                    var articlesubhead=document.createElement("div");
                    articlesubhead.className="article-subhead";
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

        window.onscroll=function(){
            //articleLoader.getdata();
            //articleLoader.page++;
            console.log(articleLoader.page);
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
    console.log(pageheight);
    console.log(scrolltop);
    console.log(lastboxHeight);
    if(lastboxHeight<pageheight+scrolltop){
        return true;
    }
}
