window.onload = function () {

    var search_site=[];
    $.get("../js/search_site.json",function(data){
        console.log(data);
        search_site = JSON.parse(data);
    });


    window.onscroll=function () {
        if(checkFlag()){
            var parent=document.getElementById("search_site");

            for(var i=0;i<search_site.length;i++){
                var search_sitebox=document.createElement("div");
                search_sitebox.className="search_sitebox";
                parent.appendChild(search_sitebox);

                var site_photo=document.createElement("div");
                site_photo.className="site_photo";
                site_photo.style.backgroundImage="url("+search_site[i].site_photo+")";
                search_sitebox.appendChild(site_photo);

                var site_text=document.createElement("div");
                site_text.className="site_text";
                search_sitebox.appendChild(site_text);

                var site_id=document.createElement("div");
                site_id.className="site_id";
                site_id.innerHTML=search_site[i].site_id;
                site_text.appendChild(site_id);

                var site_describle=document.createElement("div");
                site_describle.className="site_describle";
                site_describle.innerHTML=search_site[i].site_describle;
                site_text.appendChild(site_describle);

            }
        }
    }
}

function getChildBox(parent,content){         /*用来获取content下每一个contentbox*/
    var parent=document.getElementById("search_site");
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
    var parent = document.getElementById("search_site");
    var contentbox=getChildBox(parent,"search_sitebox");
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
