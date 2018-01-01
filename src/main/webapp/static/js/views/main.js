



$(function () {
    $("#clock").linkbutton({
        onClick: function () {
            console.log("1111")
            $.post("/attendance/save", {}, function (data) {
                if (data.success) {
                    $.messager.alert("温馨提示", "打卡成功", "info");
                }else {
                    $.messager.alert("温馨提示", "打卡失败", "warning");
                }
            }, "json");
        }
    })
});

