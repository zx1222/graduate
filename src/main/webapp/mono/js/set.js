window.onload=function () {
    var user_id=document.getElementById("user_id");
    console.log(user_id);
    user_id.innerHTML=decodeURI(JSON.parse($.cookie("user_info")).nick_name);

    var user_photo=document.getElementById("user_photo");
    user_photo.src=JSON.parse($.cookie("user_info")).head_url;
}
