var min = 0;
var max = 3;
var isPass;
$(document).ready(function () {
    setHidden(min, max);
    $("#next").click(function () {
        min += 3;
        max += 3;
        if (max >= $(".single-quizes").length) {
            $("#next").attr("disabled", true);
        }
        if (min > 0) {
            $("#previous").attr("disabled", false);
        }
        setHidden(min, max);
    });
    $("#previous").click(function () {

        min -= 3;
        max -= 3;
        if (max < $(".single-quizes").length) {
            $("#next").attr("disabled", false);
        }
        if (min <= 0) {
            $("#previous").attr("disabled", true);
        }
        setHidden(min, max);
    });
    if (min <= 0) {
        $("#previous").attr("disabled", true);
    }
});

$(document).ready(function () {
    var countQuest = $(".single-quizes");
    for (let i = 1; i <= countQuest.length; i++) {
        if ($('input[id=typeId-' + i + ']').val() == 1) {
            $('input[name=quest' + i + ']').prop('type', 'radio');
        }
        if ($('input[id=typeId-' + i + ']').val() == 2) {
            $('input[name=quest' + i + ']').prop('type', 'checkbox');
        }
        if ($('input[id=typeId-' + i + ']').val() == 3) {
            $('input[name=quest' + i + ']').prop('type', 'text');
        }
    }
});

function setHidden(min, max) {
    var countQuest = $(".single-quizes");
    for (let i = 1; i <= countQuest.length; i++) {
        if (i > min && i <= max) {
            $('div[id=questionId-' + i + ']').attr("hidden", false);
            continue;
        }
        $('div[id=questionId-' + i + ']').attr("hidden", true);
    }
}

function submit() {
    var countQuest = $(".single-quizes");
    var userAnswer = '';
    var correctAns = [];
    var inCorrectAns = [];
    var numCorrect = 0;
    var numInCorrect = 0;
    var send = {};

    for (let i = 1; i <= countQuest.length; i++) {
        var answ = {};
        answ["question_id"] = $('input[id=questId-' + i + ']').val();
        answ["userDoingTest_id"] = [[${testDTO.id}]]
        if ($('input[name=quest' + i + ']').attr('type') == 'radio') {
            userAnswer = $('input[name=quest' + i + ']:checked').val();
            let ans = [];

            /*                    console.log(userAnswer + " " + i)*/
            if (userAnswer == 'true') {
                numCorrect++;
            } else {
                numInCorrect++;
            }
            ans.push($('label[id=content' + $('input[name=quest' + i + ']:checked').attr("id") + ']').text());
            answ["content"] = ans;

        }
        if ($('input[name=quest' + i + ']').attr('type') == 'text') {
            let ans = [];
            userAnswer = $('input[name=quest' + i + ']').val();
            let count = $('input[name=quest' + i + ']');
            for (let j = 1; j <= count.length; j++) {
                let a = $('input[id=ans' + i + j + ']').val();
                if (a == count.val()) {
                    numCorrect++;
                } else {
                    numInCorrect++;
                }
            }
            ans.push($('input[name=quest' + i + ']').val());
            answ["content"] = ans;
        }
        if ($('input[name=quest' + i + ']').attr('type') == 'checkbox') {
            let count = $('input[name=quest' + i + ']');
            let countTrue = 0;
            let ans = [];
            for (let j = 1; j <= count.length; j++) {
                if ($('input[id=ans' + i + j + ']').val() == "true") {
                    countTrue++;
                }
            }
            let checkTrue = 0;
            for (let j = 1; j <= count.length; j++) {
                let a = $('input[id=ans' + i + j + ']:checked').val();
                if (a == "true") {
                    ans.push($('label[id=contentans' + i + j + ']').text());
                    checkTrue++;
                }
                if (a == "false") {
                    ans.push($('label[id=contentans' + i + j + ']').text());
                    checkTrue--;
                }
            }
            if (checkTrue == countTrue) {
                numCorrect++;
            } else {
                numInCorrect++;
            }
            answ["content"] = ans;
            checkTrue = 0;
        }
        answ["point"] = Math.round((numCorrect / countQuest.length) * 10);

        if (numCorrect < [[${testDTO.passing_point}]]) {
            isPass = false;
        }
        if (numCorrect >= [[${testDTO.passing_point}]]) {
            isPass = true;
        }
        answ["isPass"] = isPass;
        correctAns.push(answ);

    }
    console.log("So cau dung la: " + numCorrect);
    console.log("So cau sai la: " + numInCorrect);
    console.log("Diem pass la: " + [[${testDTO.passing_point}]]);

    console.log(JSON.stringify(correctAns))
    $.ajax({
        url: window.location.origin + "/api/userdoingtest/submit",
        type: 'post',
        data: JSON.stringify(correctAns),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (data) {
            console.log(data)
            window.location.href = window.location.origin + "/result/" + data.id
        }
    })
}