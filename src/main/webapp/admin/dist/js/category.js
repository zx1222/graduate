/**
 * Created by dxt on 16/4/13.
 */

var category = {}

category.fillCategory = function (func) {
    $.get("/admin/category/list", function (data) {
        func(data.data);
    })
}

category.fillCategoryTable = function (data) {
    $("#category_table").DataTable({
        data: data,
        columns: [
            {data: "id", title: "ID"},
            {data: "category_name", title: "类名"},
            {data: "article_num", title: "文章数量"},
            {data: "create_time", title: "创建时间"}
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
            targets:3  //还可以渲染添加列.
        }]

    });
}

window.onload = function(){
    checkLogin();
    category.fillCategory(category.fillCategoryTable);
}