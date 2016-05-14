/**
 * Created by dxt on 16/5/13.
 */
var table_article = {}


table_article.tableData = function (data) {
    return {
        data: data,
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
    }
}

var table_site = {}

table_site.tableData = function (data) {
    return {
        data: data,
        columns: [
            {data: "id", title: "ID"},
            {data: "site_name", title: "小站名称"},
            {data: "cover_url", title: "封面"},
            {data: "description", title: "简介"},
            {data: "article_count", title: "文章数量"},
            {data: "create_time", title: "创建时间"},
            {data: null, title: "操作"}

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
                    return ("<img width='200' src=" + data + ">");
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

    }
}

table_site.tableDataAbstract = function (data) {
    return {
        data: data,
        columns: [
            {data: "id", title: "ID"},
            {data: "site_name", title: "小站名称"},
            {data: "cover_url", title: "封面"},
            {data: "description", title: "简介"},
            {data: "article_count", title: "文章数量"},

        ],
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "autoWidth": false,
        "columnDefs": [{
                "render": function (data, type, row) {
                    return ("<img width='100' src=" + data + ">");
                },
                targets: 2  //还可以渲染添加列.
            }]

    }
}

