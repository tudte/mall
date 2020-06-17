<%--
  Created by IntelliJ IDEA.
  User: LoveYou
  Date: 2020/6/1
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset=utf-8> <meta http-equiv=x-ua-compatible content="ie=edge"> <meta name=keywords content=happymmall,电商> <meta name=description content=happymmall电商平台开发教程>
    <title>用户登录 - mymall电商平台</title>
    <link href="http://s.starkchen.top/mmall_fe/dist/css/common.css?c46219e408038ce40af1" rel="stylesheet"><link href="http://s.starkchen.top/mmall_fe/dist/css/user-login.css?c46219e408038ce40af1" rel="stylesheet">
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div class=nav-simple> <div class=w> <a class=logo href=./index.html>MYMALL</a> </div> </div>
<div class="page-wrap">
    <div class="w">
        <div class="user-con">
            <div class="user-title">用户登录</div>
            <div class="user-box">
                <div class="error-item">
                    <i class="fa fa-minus-circle error-icon"></i>
                    <p class="err-msg">Error</p>
                </div>
                <div class="user-item">
                    <label class="user-label" for="username">
                        <i class="fa fa-user"></i>
                    </label>
                    <input class="user-content" id="username" placeholder="请输入用户名" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="password">
                        <i class="fa fa-lock"></i>
                    </label>
                    <input type="password" class="user-content" id="password" placeholder="请输入密码" autocomplete="off">
                </div>
                <a class="btn btn-submit" onclick="doLogin()" id="submit">登录</a>
                <div class="link-item">
                    <a class="link" href="forgotPassword">忘记密码</a>
                    <a class="link" href="./user-register.html">免费注册</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class=footer> <div class=w> <div class=links> <a class=link href=http://www.imooc.com target=_blank>慕课网</a> | <a class=link href=https://www.baidu.com target=_blank>百度</a> | <a class=link href=https://www.taobao.com target=_blank>淘宝</a> | <a class=link href=https://www.zhihu.com target=_blank>知乎</a> </div> <p class=copyright> Copyright © 2019 starkchen.top All Right Reserved 闽ICP备19025887号-1 </p> </div> </div>
<!--    <script type="text/javascript" src="http://s.starkchen.top/mmall_fe/dist/js/base.js?c46219e408038ce40af1"></script><script type="text/javascript" src="http://s.starkchen.top/mmall_fe/dist/js/user-login.js?c46219e408038ce40af1"></script>-->
<script src="jquery/jquery-3.3.1.min.js"></script>
<script src="layui/layui.all.js"></script>
<script>
    function doLogin() {
            let username = $("#username").val();
            if(username==""){
                //弹出一个tips层
                /*layer.msg("用户登录账号不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
                });*/
                layer.tips('账号不能为空', '#username');
                return;
            }
            let password = $("#password").val();
            if(password==""){
                //弹出一个tips层
                layer.tips('密码不能为空', '#password');
                return;
            }
            var ii = null;
            $.ajax({
                type : "POST",
                url:"user/login.do",
                data:{
                    "username": username,
                    "password": password
                },
                beforeSend:function(){
                    ii = layer.load();
                },
                success: function (data) {
                    setTimeout(function(){
                        layer.close(ii);
                    }, 1000);
                    if (data.status==0) {
                        window.location.href = "index";
                    } else {
                        layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){
                        });
                    }
                }
            });
        };
</script>

</body>
</html>
