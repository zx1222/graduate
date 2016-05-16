/**
 * Created by dxt on 16/4/12.
 */

var article = {
    datatable: null,

    contactArticle: function () {

        var articleObj = commonJs.loadFormValue("articleForm", "id,title,category_id,author_name,content,description,cover_url");
        var user_info = JSON.parse($.cookie("user_info"));
        articleObj.user_id=user_info.id;

        $.post("/admin/article/add", articleObj, function (data) {
            if (data.status == 0) {
                alert("发布成功!");
                window.location.href = "/admin/articlelist.html"
            }
        });
    },
    fillCategory:function(data){
        var cate_div= $("#category");
        for(var tmp in data){
            var optionNode = commonJs.createNode("option",null,{value:data[tmp].id},data[tmp].category_name);
            cate_div[0].appendChild(optionNode);
        }
    },
    getArticleList: function () {
        var article_url = "/admin/article/list";
        var data = commonJs.getData(article_url)
        return data;
    },
    initDataTable: function (data,table_id) {
        commonJs.initTable(table_id,table_article.tableData(data));

    },
    addArticle: function () {
        $(".box").css("display", "none");
        $("#article_plain").removeClass("hide");
        $("#article_plain").css("display", "block");
        category.fillCategory(this.fillCategory);

    },
    browsArticle: function (_id) {
        var article_detail = {};
        var data = commonJs.getData("/admin/article", {id: _id});
        $("#article_detail").html(decodeURI(data.content));
        $("#article_detail").css("display", "block");
    },
    editArticle: function (_id) {

        var data = commonJs.getData("/admin/article", {id: _id});
        this.showEditView();
        var editForm = $("#articleForm");
        category.fillCategory(this.fillCategory);

        commonJs.fillForm(data, editForm)

        $(".show-img").attr("src", data.cover_url);
        $(".cover_img").css("display", "none");

    },
    delArticle: function (_id) {
        $.get("/admin/article/del", {id: _id}, function (data) {
            if (data.status == 0) {
                alert('删除成功!');
                article.init();
            }
        })
    },
    init: function () {
        var data = this.getArticleList();
        this.initDataTable(data,"#article_table");

        $('#article_table tbody').on('click', 'tr', function () {
            $("tr").removeClass('selected');
            $(this).toggleClass('selected');
        });

        $(".box").css("display", "none");
        $("#article_list").css("display", "block");

    },
    showEditView: function () {
        $(".box").css("display", "none");
        $("#article_plain").removeClass("hide");
        $("#article_plain").css("display", "block");
    },
    showSiteList:function(){
        checkSelected();
        var article_id = commonJs.getTableCellValue(0);

        commonJs.getDataHandle("/admin/site/list",function(data){

            commonJs.initTable("#site_table",table_site.tableDataAbstract(data.data));
        });

        $("#model_site_ok").click(function(){
            if(!checkSelected()){
                return;
            }
            var site_id = commonJs.getTableCellValue(0);
            $.get("/admin/site/add/article",{article_id:article_id,site_id:site_id},function(data){
                checkResult(data);
                window.location.href = "site.html"
            })
        })

    }
}





window.onload = function () {
    checkLogin();

    article.init();

    $("#submitArticle").click(function () {
        article.contactArticle();
    });

    $("#btn-add").click(function () {
        article.addArticle();
    });

    $("#btn_add_site").click(function(){
        article.showSiteList();
    })

    $("#cancle_add_article").click(function () {
        article.init();
    });

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
    });

    $(".bootstrap-wysihtml5-upload-image-url").fileupload({
        autoUpload: false,//是否自动上传
        dataType: 'json',
        url: commonJs.constant.uploadUrl + "?dir=dxt/test",//文件上传地址，当然也可以直接写在input的data-url属性内
        type: "POST",

        done: function (e, result) {
            var imgUrl = result.result.data
            console.log(result);
            alert('上传成功！');
            $(".bootstrap-wysihtml5-insert-image-url").val(imgUrl);
        }
    });


}


