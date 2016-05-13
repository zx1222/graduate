/**
 * Created by dxt on 16/4/13.
 */

var site = {}

site.fillSite = function (func) {
    $.get("/admin/site/list", function (data) {
        func(data.data);
    })
}

site.fillSiteTable = function (data) {
    $("#site_table").DataTable({
        data: data,
        columns: [
            {data: "id", title: "ID"},
            {data: "site_name", title: "小站名称"},
            {data: "cover_url", title: "封面"},
            {data: "description", title: "简介"},
            {data: "article_count", title: "文章数量"},
            {data: "create_time", title: "创建时间"},
            {data: null,title: "操作"}

        ],
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "autoWidth": false,
        "columnDefs": [{
            "render": function (data, type, row) {
                return (new Date(parseInt(data))).Format("yyyy-MM-dd hh:mm:ss");
            },
            targets: 5  //还可以渲染添加列.
        },
            {
                "render": function (data, type, row) {
                    return ("<img width='200' src=" + data + "/>");
                },
                targets: 2  //还可以渲染添加列.
            },
            {
                "render": function (data, type, row) {
                    return '<button onClick="site.browsSite(' + data.id + ')" class="btn btn-success btn-sm"><i class="fa fa-eye" ></i></button>' +
                        '<button onClick="site.editSite(' + data.id + ')" class="btn btn-primary btn-sm"><i class="fa fa-edit " ></i></button>' +
                        '<button onclick="site.delSite(' + data.id + ')" class="btn btn-danger btn-sm"><i class="fa fa-minus" ></i></button>';
                },
                "targets": 6
            }]

    });
    $('#site_table tbody').on('click', 'tr', function () {
        $("tr").removeClass('selected');
    });

}

site.browsSite = function(id){
    site.id=id;
    site.showArticleList(id);
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

    var siteObj = commonJs.loadFormValue("siteForm", "id,site_name,description,cover_url");
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
    var  article_id = $(".selected")[0].cells[0].innerText;
    $.get("/admin/site/del/article",{article_id:article_id,site_id:site.id},function(data){
        if(data.status==0){
            alert("移除成功!");
            site.browsSite(site.id);
        }
    });
    site.browsSite(site.id);
}



window.onload = function () {
    site.init();



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


}