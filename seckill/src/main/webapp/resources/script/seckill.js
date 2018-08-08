//存放主要交互逻辑js代码
//js 模块化

var seckill = {
    //封装秒杀相关ajax的url
    URL : {
        now : function () {
            return '/seckill/time/now';
        },

        exposer : function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },

        execution : function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handleSeckill : function(seckillId, node) {
        //处理秒杀逻辑
        node.hide().html(
            '<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>'
        );
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);

                    console.log("killUrl : " + killUrl);

                    $('#killBtn').one('click', function () {
                        $(this).addClass('disabled');

                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];

                                node.html(
                                    '<span class="label label-success">' + stateInfo + '</span>'
                                );
                            }
                        });
                    });
                    node.show();
                } else {
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckillId.countdown(seckillId, now, start, end);
                }
            } else {
                console.log('result : ' +  result);
            }
        });
    },

    //验证手机号
    validataPhone : function(phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countdown : function(seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //时间判断
        if (nowTime > endTime) {
            //秒杀结束
            console.log('nowTime : ' + new Date(nowTime).toLocaleString());
            console.log('endTime : ' + new Date(endTime).toLocaleString());

            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            //秒杀为开始
            //计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //获取秒杀地质
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    //封装页面秒杀逻辑
    detail : {
        //详情页初始化
        init : function (params) {
            //手机验证和登陆，计时交互

            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');


            //验证手机号
            if (!seckill.validataPhone(killPhone)) {
                //绑定手机号
                var killPhoneModel = $('#killPhoneModel');
                killPhoneModel.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false //关闭键盘事件
                });

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if (seckill.validataPhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expiress : 7, path : '/seckill'});

                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html(
                            '<label class="label label-danger">手机号错误</label>'
                        ).show(300);
                    }
                });

            }

            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result : ' + result);
                }
            })
        }
    }
}