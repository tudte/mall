webpackJsonp([14],{1:function(e,t,r){"use strict";var s=r(0),o={login:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/login.do"),data:e,method:"POST",success:t,error:r})},checkUsername:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/check_valid.do"),data:{type:"username",str:e},method:"POST",success:t,error:r})},register:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/register.do"),data:e,method:"POST",success:t,error:r})},checkLogin:function(e,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/get_user_info.do"),method:"POST",success:e,error:t})},getQuestion:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_get_question.do"),data:{username:e},method:"POST",success:t,error:r})},checkAnswer:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_check_answer.do"),data:e,method:"POST",success:t,error:r})},resetPassword:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_reset_password.do"),data:e,method:"POST",success:t,error:r})},getUserInfo:function(e,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/get_information.do"),method:"POST",success:e,error:t})},updateUserInfo:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/update_information.do"),data:e,method:"POST",success:t,error:r})},updatePassword:function(e,t,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/reset_password.do"),data:e,method:"POST",success:t,error:r})},logout:function(e,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/logout.do"),method:"POST",success:e,error:t})}};e.exports=o},10:function(e,t,r){"use strict";r(11)},11:function(e,t){},50:function(e,t,r){e.exports=r(51)},51:function(e,t,r){"use strict";r(52),r(10);var s=r(1),o=r(0),n={show:function(e){$(".error-item").show().find(".err-msg").text(e)},hide:function(){$(".error-item").hide().find(".err-msg").text("")}},u={init:function(){this.bindEvent()},bindEvent:function(){var e=this;$("#submit").click(function(){e.submit()}),$(".user-content").keyup(function(t){13===t.keyCode&&e.submit()})},submit:function(){var e={username:$.trim($("#username").val()),password:$.trim($("#password").val())},t=this.formValidate(e);t.status?s.login(e,function(e){window.location.href=o.getUrlParam("redirect")||"./index.html"},function(e){n.show(e)}):n.show(t.msg)},formValidate:function(e){var t={status:!1,msg:""};return o.validate(e.username,"require")?o.validate(e.password,"require")?(t.status=!0,t.msg="验证通过",t):(t.msg="密码不能为空",t):(t.msg="用户名不能为空",t)}};$(function(){u.init()})},52:function(e,t){}},[50]);