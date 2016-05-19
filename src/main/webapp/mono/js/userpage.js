window.onload=function () {
    checkLogin();
    var user_name=document.getElementById("user_name");
    user_name.innerHTML=decodeURI(JSON.parse($.cookie("user_info")).nick_name);
    var user_photo=document.getElementById("user_photo");
    user_photo.src=JSON.parse($.cookie("user_info")).head_url;


    var user_id = commonJs.user_info.id;

    $.get("/user/site",{user_id:user_id},function(data){

        var site_list = data.data;
        var parent=document.getElementById("createsitebox");
        for(var i in site_list) {
            var create_sitebox=document.createElement("div");
            create_sitebox.className="create_sitebox";
            parent.appendChild(create_sitebox);

            var create_site_photo=document.createElement("div");
            create_site_photo.className="create_site_photo";
            create_site_photo.style.backgroundImage="url("+site_list[i].userphoto+")";
            create_sitebox.appendChild(create_site_photo);

            var create_site_id=document.createElement("div");
            create_site_id.className="create_site_id";
            create_site_id.innerHTML=site_list[i].name;
            create_sitebox.appendChild(create_site_id);

        }
    });


    $.get("/user/focus/user",{user_id:user_id},function(data){

        var focus_userlist = data.data;
        console.log(data.data);

        var parent=document.getElementById("focususerbox");
        for(var i in focus_userlist) {
            console.log(focus_userlist[i]);
            var focus_userbox=document.createElement("div");
            focus_userbox.className="focus_userbox";
            parent.appendChild(focus_userbox);

            var focus_user_photo=document.createElement("div");
            focus_user_photo.className="focus_user_photo";
            focus_user_photo.style.backgroundImage="url("+focus_userlist[i].userphoto+")";
            focus_userbox.appendChild(focus_user_photo);

            var focus_user_id=document.createElement("div");
            focus_user_id.className="focus_user_id";
            focus_user_id.innerHTML=focus_userlist[i].name;
            focus_userbox.appendChild(focus_user_id);

        }
    });


    $.get("/user/focus/site",{user_id:user_id},function(data){

        var focus_sitelist = data.data;
        console.log(data.data);

        var parent=document.getElementById("focussitebox");
        for(var i in focus_sitelist) {
            console.log(focus_userlist[i]);
            var focus_sitebox=document.createElement("div");
            focus_sitebox.className="focus_sitebox";
            parent.appendChild(focus_sitebox);

            var focus_site_photo=document.createElement("div");
            focus_site_photo.className="focus_site_photo";
            focus_site_photo.style.backgroundImage="url("+focus_sitelist[i].sitephoto+")";
            focus_sitebox.appendChild(focus_site_photo);

            var focus_user_id=document.createElement("div");
            focus_site_id.className="focus_site_id";
            focus_site_id.innerHTML=focus_sitelist[i].name;
            focus_sitebox.appendChild(focus_site_id);

        }
    });


    function getChildBox(parent, content) {         /*用来获取content下每一个contentbox*/
        var parent = document.getElementById("createsitebox");
        var allcontent = parent.getElementsByTagName("*");
        var contentboxArr = [];
        for (var i = 0; i < allcontent.length; i++) {
            if (allcontent[i].className == content) {
                contentboxArr.push(allcontent[i]);
            }
        }
        return contentboxArr;
    }


}
