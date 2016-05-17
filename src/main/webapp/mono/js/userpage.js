window.onload=function () {
    var user_name=document.getElementById("user_name");
    user_name.innerHTML=JSON.parse($.cookie("user_info")).user_name;
}