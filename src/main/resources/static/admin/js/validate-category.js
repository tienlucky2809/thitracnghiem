$(document).ready(function () {

    var name = document.forms["submit"]["name"].value;
    var send = {};
    send['name'] = name;

    $("#submit").validate({

        rules: {
            "name": {
                required: true,
                maxLength: 12,
                /*                remote: {
                                    async: false,
                                    url: window.location.origin + "/api/admin/category/validate",
                                    dataType: 'json',
                                    type: "POST",
                                    contentType: "application/json; charset=utf-8",
                                    data: JSON.stringify(send),
                                    success: function (data) {
                                        if (data == true) {
                                            alert(data.length)
                                            return false;
                                        }
                                    }
                                }*/
            }
        },
        messages: {
            "name": {
                required: "Bắt buộc nhập tên Category",
                maxlength: "Hãy nhập tối đa 12 ký tự"
            }
        }
    });

    /*    if (name == "") {
            alert("Please input Name of Category");
            return false;
        }*/
    /*
        var data = {};
        data['name'] = name;
        $.ajax({
            url: window.location.origin + "/api/admin/category/list",
            type: 'post',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                if (data.length != 0) {
                    return false;
                }
            }
        });*/

});