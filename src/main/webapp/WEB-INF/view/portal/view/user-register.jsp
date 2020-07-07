<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2020/6/5
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset=utf-8> <meta http-equiv=x-ua-compatible content="ie=edge"> <meta name=keywords content=happymmall,电商> <meta name=description content=happymmall电商平台开发教程>
    <title>用户注册 - happymmall电商平台</title>
    <link href="http://s.starkchen.top/mmall_fe/dist/css/common.css?c46219e408038ce40af1" rel="stylesheet"><link href="http://s.starkchen.top/mmall_fe/dist/css/user-register.css?c46219e408038ce40af1" rel="stylesheet"></head>
<body>
<div class=nav-simple> <div class=w> <a class=logo href=./index.html>MMALL</a> </div> </div>
<div class="page-wrap">
    <div class="w">
        <div class="user-con">
            <div class="user-title">用户注册</div>
            <div class="user-box">
                <div class="error-item">
                    <i class="fa fa-minus-circle error-icon"></i>
                    <p class="err-msg">Error</p>
                </div>
                <div class="user-item">
                    <label class="user-label" for="username">
                        <i class="fa fa-user"></i>
                    </label>
                    <input class="user-content" id="username" name="username" placeholder="请输入用户名" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="password">
                        <i class="fa fa-lock"></i>
                    </label>
                    <input type="password" class="user-content" id="password" name="password" placeholder="请输入密码" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="password-confirm">
                        <i class="fa fa-lock"></i>
                    </label>
                    <input type="password" class="user-content" id="password-confirm" placeholder="请再次输入密码" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="phone">
                        <i class="fa fa-phone"></i>
                    </label>
                    <input class="user-content" id="phone" name="phone" placeholder="请输入手机号" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="email">
                        <i class="fa fa-envelope"></i>
                    </label>
                    <input class="user-content" id="email" name="email" placeholder="请输入邮箱" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="question">
                        <i class="fa fa-question"></i>
                    </label>
                    <input class="user-content" id="question" name="question" placeholder="请输入密码提示问题" autocomplete="off">
                </div>
                <div class="user-item">
                    <label class="user-label" for="answer">
                        <i class="fa fa-key"></i>
                    </label>
                    <input class="user-content" id="answer" name="answer" placeholder="请输入密码提示问题答案" autocomplete="off">
                </div>
                <a class="btn btn-submit" onclick="toRegister()" id="submit">立即注册</a>
                <div class="link-item">
                    <a class="link" href="./user-login.html">已有帐号，去登录>></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class=footer> <div class=w> <div class=links> <a class=link href=http://www.imooc.com target=_blank>慕课网</a> | <a class=link href=https://www.baidu.com target=_blank>百度</a> | <a class=link href=https://www.taobao.com target=_blank>淘宝</a> | <a class=link href=https://www.zhihu.com target=_blank>知乎</a> </div> <p class=copyright> Copyright © 2019 starkchen.top All Right Reserved 闽ICP备19025887号-1 </p> </div> </div>
<script src="jquery/jquery-3.3.1.min.js"></script>
<script src="layui/layui.all.js"></script>
<script>

    //校验用户名
    let username;
    function checkUsername() {
        //获取用户输入的数据
        username = $("#username").val().trim().replace(/\s/g,"");
        //定义正则 任意2到10个字符
        let reg_username=/^.{2,10}$/;
        //校验
        let flag = reg_username.test(username);
        if(flag){
            //校验成功
            layer.tips('用户名合法', '#username');
        }else{
            //失败给予提示
            layer.msg("用户名不合法,2至10个字符", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验密码
    let password;
    function checkPassword() {
        //获取用户输入的数据
        password = $("#password").val().trim().replace(/\s/g,"");
        //定义正则 6至10位
        let reg_password=/^\w{6,10}$/;
        //校验
        let flag = reg_password.test(password);
        if(flag){
            //校验成功
            layer.tips('密码合法', '#password');
        }else{
            //失败给予提示
            layer.msg("密码不合法,6至10位", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验确认密码
    function checkPasswordConfirm() {
        //调用checkPassword
        checkPassword();
        //获取用户输入的数据
        let password = $("#password").val().trim().replace(/\s/g,"");
        let password_confirm = $("#password-confirm").val().trim().replace(/\s/g,"");

        let flag;
        //判断再次输入密码是否一致
        if(password===password_confirm){
            flag = true;
            layer.tips('合法', '#password-confirm');
        }else {
            flag=false;
            //失败给予提示
            layer.msg("不一致", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验手机号码
    let phone;
    function checkPhone(){
        //获取用户输入的数据
        phone = $("#phone").val().trim().replace(/\s/g,"");
        //定义正则 任意2到10个字符
        let reg_phone=/^\d{11}$/;
        //校验
        let flag = reg_phone.test(phone);
        if(flag){
            //校验成功
            layer.tips('合法', '#phone');
        }else{
            //失败给予提示
            layer.msg("手机号有11位", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验邮箱
    let email;
    function checkEmail() {
        //获取用户输入的数据
        email = $("#email").val();
        //定义正则 任意2到10个字符
        let reg_email=/^\w+@\w+\.\w+$/;
        //校验
        let flag = reg_email.test(email);
        if(flag){
            //校验成功
            layer.tips('合法邮箱', '#email');
        }else{
            //失败给予提示
            layer.msg("邮箱格式不正确", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验问题
    let question;
    function checkQuestion() {
        //获取用户输入的数据
        question = $("#question").val().trim().replace(/\s/g,"");
        let flag;
        if(!question==""){
            flag=true;
            //校验成功
            layer.tips('合法', '#question');
        }else {
            flag=false;
            //失败给予提示
            layer.msg("问题不能为空", {time:2000, icon:5, shift:6}, function(){
            });
        }
        return flag;
    }
    //校验答案
    let answer;
    function checkAnswer() {
        //获取用户输入的数据
        answer = $("#answer").val().trim().replace(/\s/g,"");
        let flag;
        if(!answer==""){
            flag=true;
            //校验成功
            layer.tips('合法', '#answer');
        }else {
            flag=false;
            //失败给予提示
            layer.msg("答案不能为空", {time:2000, icon:5, shift:6}, function(){
            });

        }
        return flag;
    }
    function toRegister() {
        if(checkUsername()&&checkPassword()&&checkPasswordConfirm()&&checkPhone()&&checkEmail()&&checkQuestion()&&checkAnswer()){
            let ii=null;
            $.ajax({
                url:"user/register.do",
                type:"POST",
                data:{
                    "username": username,
                    "password": password,
                    "email":email,
                    "phone":phone,
                    "question":question,
                    "answer":answer
                },
                beforeSend: function(){
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
    }
    //鼠标失去焦点事件
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#password-confirm").blur(checkPasswordConfirm);
    $("#phone").blur(checkPhone);
    $("#email").blur(checkEmail);
    $("#question").blur(checkQuestion);
    $("#answer").blur(checkAnswer);
</script>
<%--<script type="text/javascript" src="http://s.starkchen.top/mmall_fe/dist/js/base.js?c46219e408038ce40af1"></script><script type="text/javascript" src="http://s.starkchen.top/mmall_fe/dist/js/user-register.js?c46219e408038ce40af1"></script></body>--%>
</html>
