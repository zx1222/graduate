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
    initDataTable: function (data) {
        if (this.datatable) {
            this.datatable.destroy();
        }
        this.datatable = $('#article_table').DataTable({
            data: this.getArticleList(),
            columns: [
                {"data": "id", title: 'ID'},
                {"data": "title", title: "标题"},
                {'data': "description", title: '描述'},
                {'data': "create_time", title: '发布时间'},
                {'data': null, title: "操作", class: 'col-md-2'}
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
                "targets": 3
            }, {
                "render": function (data, type, row) {
                    return '<button onClick="article.browsArticle(' + data.id + ')" class="btn btn-success btn-sm"><i class="fa fa-eye" ></i></button>' +
                        '<button onClick="article.editArticle(' + data.id + ')" class="btn btn-primary btn-sm"><i class="fa fa-edit " ></i></button>' +
                        '<button onclick="article.delArticle(' + data.id + ')" class="btn btn-danger btn-sm"><i class="fa fa-minus" ></i></button>';
                },
                "targets": 4
            }]
        });
        $('#article_table tbody').on('click', 'tr', function () {
            $("tr").removeClass('selected');
            $(this).toggleClass('selected');
        });
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
        this.initDataTable();
        $(".box").css("display", "none");
        $("#article_list").css("display", "block");

    },
    showEditView: function () {
        $(".box").css("display", "none");
        $("#article_plain").removeClass("hide");
        $("#article_plain").css("display", "block");
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
    })

}


