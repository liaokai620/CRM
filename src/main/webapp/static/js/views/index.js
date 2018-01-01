
$(function () {
    // This function is called from the pop-up menus to transfer to
    // a different page. Ignore if the value returned is a null string:
    function goPage (newURL) {

        // if url is empty, skip the menu dividers and reset the menu selection to default
        if (newURL != "") {

            // if url is "-", it is this page -- reset the menu:
            if (newURL == "-" ) {
                resetMenu();
            }
            // else, send page to designated URL
            else {
                document.location.href = newURL;
            }
        }
    }

    // resets the menu selection upon entry to this page:
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    };


    $("#clock").linkbutton({
        iconCls: "icon-save",
        onClick: function () {
            $.post("/attendance/save", {}, function (data) {
                if (data.success) {
                    $.messager.alert("温馨提示", "打卡成功", "info");
                }else {
                    $.messager.alert("温馨提示", "打卡失败", "warning");
                }
            }, "json");
        }
    })



})
