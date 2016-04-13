window.onload=function(){
    var rollimg=document.getElementById("roll-img");
    var imgboxArr=getChildElement(rollimg,"roll-imgbox");
    var rollcircle=document.getElementById("roll-linecircle").getElementsByTagName("div");
    for(var i=0;i<rollcircle.length;i++){
        rollcircle[i].id=i;                        /*给每一个小圆圈赋id*/
        rollcircle[i].onmouseover=function(){
            for(var j=0;j<rollcircle.length;j++){
                rollcircle[j].className="";
                imgboxArr[j].style.display="none";
            }
            this.className="circleselect";
            imgboxArr[this.id].style.display="block";
        }
    }
}
function getChildElement(parent,content){      /*用来获取每一个滚动小圆圈*/
    var childArr=[];
    var allcontent=parent.getElementsByTagName("*");
    for(var i=0;i<allcontent.length;i++){
        if(allcontent[i].className==content){
            childArr.push(allcontent[i]);
        }
    }
    return childArr;
}

function checkFlag(){
    var pageheight=document.documentElement.clientHeight;
    var scrolltop=document.documentElement.scrollTop;
    var content=document.getElementById("content");
    var contentbox=getChildElement(content,"contentbox");
    var lastboxtop=contentbox[contentbox.length-1].offsetTop;
    if(lastboxtop<pageheight+scrolltop){
        return true;
    }
    
}