$(window).load(function () {
    var url = window.location.toString()
    var bookName = url.split('=')[1];
    $.ajax({
        url: '/queryOne?bookName='+bookName, success: function (data) {
            var data_obj = jQuery.parseJSON(data)
            $('input[type="text"]:lt(1)').val(data_obj[0]['bookName'])
            $('input[type="text"]:gt(0)').val(data_obj[0]['bookAuthor'])
            $('input[type="text"]:gt(1)').val(data_obj[0]['bookType'])
            $('input[type="text"]:gt(2)').val(data_obj[0]['bookPrice'])
            $('.time-sel').val(data_obj[0]['publicationTime'])
        }
    });
});
$(function () {
    var picker1 = $('#datetimepicker3').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn'),
    });
});