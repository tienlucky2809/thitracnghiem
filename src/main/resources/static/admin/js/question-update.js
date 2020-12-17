$(function () {
    $('form').submit(function () {
        var data = {}
        data["id"] = $("#idQuest").val();
        data["content"] = $("#contentQuest").val();
        data["level"] = $("#level").val();
        data["point"] = $("#point").val();
        data["categoryId"] = $("#categoryId").val();
        data["questionTypeValue"] = $("#questionType").val();

        var answers = [];
        var correctAnswer = $(".answerCorrect");
        for (let i = 0; i < correctAnswer.length; i++) {
            var answerTinys = {};
            if ($(correctAnswer[i]).val() != "") {
                answerTinys["correct"] = $("#correct").val();
                answerTinys["content"] = $(correctAnswer[i]).val();
                answers.push(answerTinys);
            }
        }

        var answerIncorrect = $(".answerIncorrect");
        for (let i = 0; i < answerIncorrect.length; i++) {
            var answerInTinys = {};
            if ($(answerIncorrect[i]).val() != "") {
                answerInTinys["correct"] = $("#inCorrect").val();
                answerInTinys["content"] = $(answerIncorrect[i]).val();
                answers.push(answerInTinys);
            }
        }
        console.log(answers);
        data["answerDTOS"] = answers;
        $.ajax({
            url: window.location.origin + "/api/admin/question",
            type: 'put',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function () {
                window.location.href = window.location.origin + "/admin/question"
            }
        });
        return false;
    });
});

$(document).ready(function () {
    $(".addCorrect").click(function () {
        $("#tableCorrect").append('<tr><th></th><td><input class="answerCorrect form-control" type="text"> &nbsp; <a href="javascript:void(0);" class="remCorrect">Remove</a></td></tr>');
    });
    $("#tableCorrect").on('click', '.remCorrect', function () {
        $(this).parent().parent().remove();
    });
});
$(document).ready(function () {
    $(".addInCorrect").click(function () {
        $("#tableInCorrect").append('<tr><th></th><td><input class="answerIncorrect form-control" type="text">  &nbsp; <a href="javascript:void(0);" class="remInCorrect">Remove</a></td></tr>');
    });
    $("#tableInCorrect").on('click', '.remInCorrect', function () {
        $(this).parent().parent().remove();
    });
});


//Bắt sự kiện ajax từ category -> sub category
$(document).ready(function () {
    $('.input-group #categoryParentId').on('change', function () {

        var send = {};
        send['categoryParentId'] = this.value;

        $.ajax({
            url: window.location.origin + "/api/admin/category/list",
            type: 'post',
            data: JSON.stringify(send),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (data) {
                $(".input-group #categoryId").empty(); //To reset cities
                $(".input-group #categoryId").append("<option>--SELECT--</option>");

                for (let i = 0; i < data.length; i++) {
                    $(".input-group #categoryId").append("<option value=" + data[i].id + ">" + data[i].name + "</option>")
                }
            }
        });
    });
});