$(window).load(function () {
    $.ajax({
        url: '/queryAll', success: function (data) {
            var data_obj = jQuery.parseJSON(data)
            //去重
            var bookTypes = new Set();
            for (i = 0; i < data_obj.length; i++) {
                $('.table tbody').append("        <tr>\n" +
                    "            <td>" + data_obj[i]['bookName'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookAuthor'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookType'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookPrice'] + "</td>\n" +
                    "            <td>" + data_obj[i]['publicationTime'] + "</td>\n" +
                    "            <td><a href='/delete_one?bookName=" + data_obj[i]['bookName'] + "'>删除</a></td>\n" +
                    "            <td><a href='/modify_one.xhtml?bookName=" + data_obj[i]['bookName'] + "'>修改</a></td>\n" +
                    "        </tr>")

                bookTypes.add(data_obj[i]['bookType'])

            }
            bookTypes.forEach(function (element, sameElement, set) {
                $('.container div:first>select').append("<option>" + element + "</option>")
            });
        }
    });
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
    $('.table tbody').find('tr').remove();
    $.ajax({
        url: '/query',
        type: 'POST',
        dataType: 'json',
        data: jsonData,
        success: function (res) {
            var data_obj = res
            for (i = 0; i < data_obj.length; i++) {
                $('.table tbody').append("        <tr>\n" +
                    "            <td>" + data_obj[i]['bookName'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookAuthor'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookType'] + "</td>\n" +
                    "            <td>" + data_obj[i]['bookPrice'] + "</td>\n" +
                    "            <td>" + data_obj[i]['publicationTime'] + "</td>\n" +
                    "            <td><a href='/delete_one?bookName=" + data_obj[i]['bookName'] + "'>删除</a></td>\n" +
                    "            <td><a href='/modify_one.xhtml?bookName=" + data_obj[i]['bookName'] + "'>修改</a></td>\n" +
                    "        </tr>")
            }
        },
        error: function (e) {
        }
    });
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