/**
 * Created by dxt on 16/4/13.
 */

var site = {}

site.fillSite = function (func) {
    var user_id =commonJs.user_info.id;
    $.get("/admin/site/list?user_id="+user_id, function (data) {
        func(data.data);
    })
}

site.fillSiteTable = function (data) {
    $("#site_table").DataTable(table_site.tableData(data));

    $('#site_table tbody').on('click', 'tr', function () {
        $("tr").removeClass('selected');
    });

}

site.browsSite = function(id){
    site.id=id;
    site.showArticleList(id);
    commonJs.show("site_articlelist");

}

site.delSite = function(id){
    $.get("/admin/site/del", {id: id,status:1}, function (data) {
        if (data.status == 0) {
            alert('删除成功!');
            site.init();
        }
    })
}

site.init = function(){
    site.fillSite(site.fillSiteTable);

}


site.addSite = function(){

    var siteObj = commonJs.loadFormValue("siteForm", "id,site_name,description,cover_url,icon_url");
    var user_info = JSON.parse($.cookie("user_info"));
    siteObj.user_id=user_info.id;

    $.post("/admin/site/add", siteObj, function (data) {
        if (data.status == 0) {
            alert("发布成功!");
            window.location.href = "/admin/site.html"
        }
    });
}

site.editSite =  function (_id) {

    var data = commonJs.getData("/admin/site/detail", {id: _id});
    this.showEditView();
    var editForm = $("#siteForm");

    commonJs.fillForm(data, editForm)

    $(".show-img").attr("src", data.cover_url);
    $(".show-img-icon").attr("src", data.icon_url);
    $(".cover_img").css("display", "none");

}

site.showEditView= function (){
    $(".box").css("display", "none");
    $("#site_plain").removeClass("hide");
    $("#site_plain").css("display", "block");
}

site.showArticleList = function(site_id){
    var data = commonJs.getData("/admin/article/list",{site_id:site_id})
    article.initDataTable(data,"#site_article_table")
    $('#site_article_table tbody').on('click', 'tr', function () {
        $("tr").removeClass('selected');
        $(this).toggleClass('selected');
    });
}

site.removeArticle = function(){
    var  article_id =commonJs.getTableCellValue(0);
    $.get("/admin/site/del/article",{article_id:article_id,site_id:site.id},function(data){
        if(data.status==0){
            alert("移除成功!");
            site.browsSite(site.id);
        }
    });
    site.browsSite(site.id);
}



window.onload = function () {
    checkLogin();

    site.init();

    $("#btn-add").click(function(){
        commonJs.clearForm("#siteForm");

        site.showEditView();
    })

    $("#submitSite").click(function(){
        site.addSite();

    });

    $("#btn-remove-article").click(function(){
        site.removeArticle();
    })

    $("#cover_img").fileupload({
        autoUpload: false,//是否自动上传
        dataType: 'json',
        url: commonJs.constant.uploadUrl + "?dir=dxt/test",//文件上传地址，当然也可以直接写在input的data-url属性内
        type: "POST",

        done: function (e, result) {
            var imgUrl = result.result.data

            alert('上传成功！');
            $(".show-img").attr("src", imgUrl);

            $("#cover_url").val(imgUrl);
        }
    })

    $("#icon_img").fileupload({
        autoUpload: false,//是否自动上传
        dataType: 'json',
        url: commonJs.constant.uploadUrl + "?dir=dxt/test",//文件上传地址，当然也可以直接写在input的data-url属性内
        type: "POST",

        done: function (e, result) {
            var imgUrl = result.result.data

            alert('上传成功！');
            $(".show-img-icon").attr("src", imgUrl);

            $("#icon_url").val(imgUrl);
        }
    })


}