$(window).load(function () {

    //获取类别筛选的数据
    $.ajax({
        url: '/queryAll', success: function (data) {
            var data_obj = jQuery.parseJSON(data)
            //去重
            var bookTypes = new Set();
            for (i = 0; i < data_obj.length; i++) {
                bookTypes.add(data_obj[i]['bookType'])
            }
            bookTypes.forEach(function (element, sameElement, set) {
                $('.container div:first>select').append("<option>" + element + "</option>")
            });
        }
    });


    $('#mytab').bootstrapTable({
        method: 'POST',
        url: "/queryAll",//请求路径
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页
        pagination: true,//是否分页
        sidePagination: 'client',//server:服务器端分页|client：前端分页
        pageSize: 5,//单页记录数
        pageList: [5, 10, 20, 30],//可选择单页记录数
        showRefresh: true,//刷新按钮
        columns: [{
            title: '书名',
            field: 'bookName',
            sortable: true
        }, {
            title: '作者',
            field: 'bookAuthor',
            sortable: true
        }, {
            title: '类别',
            field: 'bookType',
        }, {
            title: '价格',
            field: 'bookPrice',
        }, {
            title: '出版时间',
            field: 'publicationTime',
        }, {
            title: '操作    <a href="/add_one.xhtml">添加图书</a>',
            field: 'publicationTime',
            formatter: operation,//对资源进行操作
        }]
    });

//删除、编辑操作
    function operation(value, row, index) {
        var htm = "<a href='/delete_one?bookName=" + row["bookName"] + "'>删除</a> " +
            "<a href='/modify_one.xhtml?bookName=" + row["bookName"] + "'>修改</a>"
        return htm;
    }
});

function queryInfo() {
    var jsonData = {
        bookName: $('input[name="bookName"]').val(),
        bookAuthor: $('input[name="bookAuthor"]').val(),
        startTime: $('input[name="startTime"]').val(),
        endTime: $('input[name="endTime"]').val(),
        bookTypeSection: $('select[name="bookType-section"]').val(),
        bookPrice_section: $('select[name="bookPrice-section"]').val()
    }
    $("#mytab").bootstrapTable('removeAll');

    $.ajax({
        url: '/query',
        type: 'POST',
        dataType: 'json',
        data: jsonData,
        success: function (res) {
            var data_obj = res
            for (i = 0; i < data_obj.length; i++) {
                var _data = {
                    "bookName": data_obj[i]["bookName"],
                    "bookAuthor": data_obj[i]["bookAuthor"],
                    "bookType": data_obj[i]["bookType"],
                    "bookPrice": data_obj[i]["bookPrice"],
                    "publicationTime": data_obj[i]["publicationTime"],

                }
                $("#mytab").bootstrapTable('append', _data);
            }
            removeAllSerech()
        },
        error: function (e) {
        }
    });
}

function removeAllSerech() {
    $('input[name="bookName"]').val('')
    $('input[name="bookAuthor"]').val('')
    $('input[name="startTime"]').val('')
    $('input[name="endTime"]').val('')
    $('select[name="bookType-section"]').val('')
    $('select[name="bookPrice-section"]').val('')

}


$(function () {
    var picker1 = $('#datetimepicker1').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn'),
        //minDate: '2016-7-1'
    });
    var picker2 = $('#datetimepicker2').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    });
    //动态设置最小值
    picker1.on('dp.change', function (e) {
        picker2.data('DateTimePicker').minDate(e.date);
    });
    //动态设置最大值
    picker2.on('dp.change', function (e) {
        picker1.data('DateTimePicker').maxDate(e.date);
    });
});
