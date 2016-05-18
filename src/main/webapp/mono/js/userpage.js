window.onload=function () {
    var user_name=document.getElementById("user_name");
    user_name.innerHTML=decodeURI(JSON.parse($.cookie("user_info")).nick_name);
    var user_photo=document.getElementById("user_photo");
    user_photo.src=JSON.parse($.cookie("user_info")).head_url;
}