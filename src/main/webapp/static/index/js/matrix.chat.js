$(document).ready(function () {

    var msg_template = '<p><span class="msg-block"><strong></strong><span class="time"></span><span class="msg"></span></span></p>';

    $('.chat-message button').click(function () {
        var input = $(this).siblings('span').children('input[type=text]');
        if (input.val() != '') {
            var value = input.val()
            add_message('You', '/static/index/img/demo/av3.jpg', value, true);
            $.post("sendMsg",{word:value},function (data){
                setTimeout(function () {
                    add_message('校管家机器人', '/static/index/img/demo/av1.jpg', data.result.text, true);
                },'1000');
            },"json")
        }
    });

    $('.chat-message input').keypress(function (e) {
        if (e.which == 13) {
            if ($(this).val() != '') {
                var value = $(this).val();
                add_message('You', '/static/index/img/demo/av3.jpg', value, true);
                $.post("sendMsg",{word:value},function (data){
                    setTimeout(function () {
                        add_message('校管家机器人', '/static/index/img/demo/av1.jpg', data.result.text, true);
                    },'1000');
                },"json")
            }
        }
    });

    setTimeout(function () {
        add_message('校管家机器人', '/static/index/img/demo/av1.jpg', '你好,我是校管家小助手,请问有什么可以帮您的吗?')
    }, '1000');


    var i = 0;

    function add_message(name, img, msg, clear) {
        i = i + 1;
        var inner = $('#chat-messages-inner');
        var time = new Date();
        var hours = time.getHours();
        var minutes = time.getMinutes();
        if (hours < 10) hours = '0' + hours;
        if (minutes < 10) minutes = '0' + minutes;
        var id = 'msg-' + i;
        var idname = name.replace(' ', '-').toLowerCase();
        inner.append('<p id="' + id + '" class="user-' + idname + '">'
            + '<span class="msg-block"><img src="' + img + '" alt="" /><strong>' + name + '</strong> <span class="time">- ' + hours + ':' + minutes + '</span>'
            + '<span class="msg">' + msg + '</span></span></p>');
        $('#' + id).hide().fadeIn(800);
        if (clear) {
            $('.chat-message input').val('').focus();
        }
        $('#chat-messages').animate({scrollTop: inner.height()}, 1000);
    }

    function remove_user(userid, name) {
        i = i + 1;
        $('.contact-list li#user-' + userid).addClass('offline').delay(1000).slideUp(800, function () {
            $(this).remove();
        });
        var inner = $('#chat-messages-inner');
        var id = 'msg-' + i;
        $('#' + id).hide().fadeIn(800);
    }
});
