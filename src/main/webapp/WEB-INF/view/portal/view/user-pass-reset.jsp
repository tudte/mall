<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2020/6/11
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset=utf-8> <meta http-equiv=x-ua-compatible content="ie=edge"> <meta name=keywords content=happymmall,电商> <meta name=description content=happymmall电商平台开发教程>
    <title>找回密码 - happymmall电商平台</title>
    <link href="http://s.starkchen.top/mmall_fe/dist/css/common.css?c46219e408038ce40af1" rel="stylesheet"><link href="http://s.starkchen.top/mmall_fe/dist/css/user-pass-reset.css?c46219e408038ce40af1" rel="stylesheet"></head>
<body>
<div class=nav-simple> <div class=w> <a class=logo href=./index.html>MMALL</a> </div> </div>
<div class="page-wrap">
    <div class="w">
        <div class="user-con">
            <div class="user-title">找回密码</div>
            <div class="user-box">
                <div class="error-item">
                    <i class="fa fa-minus-circle error-icon"></i>
                    <p class="err-msg">Error</p>
                </div>
                <div class="step-con step-username">
                    <p class="user-item-text">请输入用户名：</p>
                    <div class="user-item">
                        <label class="user-label" for="username">
                            <i class="fa fa-user"></i>
                        </label>
                        <input class="user-content" id="username" name="username" placeholder="请输入用户名" autocomplete="off">
                    </div>
                    <a class="btn btn-submit" onclick="submit_username()" id="submit-username">下一步</a>
                </div>
                <div class="step-con step-question">
                    <p class="user-item-text">密码提示问题是：<span class="question"></span></p>
                    <div class="user-item">
                        <label class="user-label" for="answer">
                            <i class="fa fa-key"></i>
                        </label>
                        <input class="user-content" id="answer" placeholder="请输入密码提示问题答案" autocomplete="off">
                    </div>
                    <a class="btn btn-submit" onclick="submit_question()" id="submit-question">下一步</a>
                </div>
                <div class="step-con step-password">
                    <p class="user-item-text">请输入新密码：</p>
                    <div class="user-item">
                        <label class="user-label" for="password">
                            <i class="fa fa-lock"></i>
                        </label>
                        <input type="password" class="user-content" id="password" placeholder="请输入新密码" autocomplete="off">
                    </div>
                    <a class="btn btn-submit" id="submit-password" onclick="submit_password()">下一步</a>
                </div>

                <div class="link-item">
                    <a class="link" href="toLogin">返回登录>></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class=footer> <div class=w> <div class=links> <a class=link href=http://www.imooc.com target=_blank>慕课网</a> | <a class=link href=https://www.baidu.com target=_blank>百度</a> | <a class=link href=https://www.taobao.com target=_blank>淘宝</a> | <a class=link href=https://www.zhihu.com target=_blank>知乎</a> </div> <p class=copyright> Copyright © 2019 starkchen.top All Right Reserved 闽ICP备19025887号-1 </p> </div> </div> <script src=http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js></script>
<script src="${pageContext.request.contextPath}/resources/js/user-pass-resrt01.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/layui.all.js"></script>
<script>
    $(function () {
        $(".step-username").css("display","inline");
    });
    function submit_username() {
        let username = $("#username").val();
        let ii=null;
        $.ajax({
            type:"POST",
            url:"user/forgetQuestion.do" ,
            data:{
               "username":username
            },
            beforeSend:function(){
                ii = layer.load();
            },
            success:function (data) {
                setTimeout(function(){
                    layer.close(ii);
                }, 1000);
                if (data.status==0) {
                    $(".step-username").css("display","none");
                    $(".step-question").css("display","inline");
                    $(".question").html(data.msg);
                } else {
                    layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){
                    });
                }
            }
        });
    }
    let forgetToken=null;
    function submit_question() {
        let username = $("#username").val();
        let question = $(".question").html();
        let answer = $("#answer").val();
        let ii=null;
        $.ajax({
            type:"POST",
            url:"user/checkAnswer.do" ,
            data:{
                "username":username,
                "question":question,
                "answer":answer
            },
            beforeSend:function(){
                ii = layer.load();
            },
            success:function (data) {
                setTimeout(function(){
                    layer.close(ii);
                }, 1000);
                if (data.status==0) {
                    $(".step-username").css("display","none");
                    $(".step-question").css("display","none");
                    $(".step-password").css("display","inline");
                    forgetToken=data.data;
                } else {
                    layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){
                    });
                }
            }
        });
    }

    function submit_password() {
        let username = $("#username").val();
        let password = $("#password").val();
        let ii=null;
        $.ajax({
            type:"POST",
            url:"user/forgetResetPassword.do" ,
            data:{
                "username":username,
                "password":password,
                "forgetToken":forgetToken
            },
            beforeSend:function(){
                ii = layer.load();
            },
            success:function (data) {
                setTimeout(function(){
                    layer.close(ii);
                }, 1000);
                if (data.status==0) {
                    window.location.href = "toLogin";
                } else {
                    layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){
                    });
                }
            }
        });
    }
</script>
<%--
<script type="text/javascript" src="http://s.starkchen.top/mmall_fe/dist/js/user-pass-reset.js?c46219e408038ce40af1"></script>
--%>
</body>
</html>
