window.onload=function () {
    checkLogin();
    var user_name=document.getElementById("user_name");
    user_name.innerHTML=decodeURI(JSON.parse($.cookie("user_info")).nick_name);
    var user_photo=document.getElementById("user_photo");
    user_photo.src=JSON.parse($.cookie("user_info")).head_url;


    var user_id = commonJs.user_info.id;

    $.get("/user/site",{user_id:user_id},function(data){

        var site_list = data.data;
        for(var i in site_list) {
            console.log(site_list[i]);
            $(".createsitebox").append(" <div class='create_sitebox'>" +
                "  <div class='create_site_photo'><img width ='100' src='"+site_list[i].userphoto+"'/></div>" +
                "  <div class='create_site_id'>"+site_list[i].name+"</div>" +
                " </div>");

        }
    })


    $.get("/user/focus/user");


    $.get("/user/focus/site");


}