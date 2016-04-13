/**
 * Created by dxt on 16/4/13.
 */

var category ={}

category.fillCategory=function(func){
    $.get("/mono/category",function(data){
        func(data.data);
    })
}