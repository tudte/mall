webpackJsonp([12],{1:function(e,r,t){"use strict";var s=t(0),o={login:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/login.do"),data:e,method:"POST",success:r,error:t})},checkUsername:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/check_valid.do"),data:{type:"username",str:e},method:"POST",success:r,error:t})},register:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/register.do"),data:e,method:"POST",success:r,error:t})},checkLogin:function(e,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/get_user_info.do"),method:"POST",success:e,error:r})},getQuestion:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_get_question.do"),data:{username:e},method:"POST",success:r,error:t})},checkAnswer:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_check_answer.do"),data:e,method:"POST",success:r,error:t})},resetPassword:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/forget_reset_password.do"),data:e,method:"POST",success:r,error:t})},getUserInfo:function(e,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/get_information.do"),method:"POST",success:e,error:r})},updateUserInfo:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/update_information.do"),data:e,method:"POST",success:r,error:t})},updatePassword:function(e,r,t){s.request({url:s.getServerUrl("http://www.starkchen.top/user/reset_password.do"),data:e,method:"POST",success:r,error:t})},logout:function(e,r){s.request({url:s.getServerUrl("http://www.starkchen.top/user/logout.do"),method:"POST",success:e,error:r})}};e.exports=o},10:function(e,r,t){"use strict";t(11)},11:function(e,r){},53:function(e,r,t){e.exports=t(54)},54:function(e,r,t){"use strict";t(55),t(10);var s=t(1),o=t(0),n={show:function(e){$(".error-item").show().find(".err-msg").text(e)},hide:function(){$(".error-item").hide().find(".err-msg").text("")}},u={init:function(){this.bindEvent()},bindEvent:function(){var e=this;$("#username").blur(function(){var e=$.trim($(this).val());e&&s.checkUsername(e,function(e){n.hide()},function(e){n.show(e)})}),$("#submit").click(function(){e.submit()}),$(".user-content").keyup(function(r){13===r.keyCode&&e.submit()})},submit:function(){var e={username:$.trim($("#username").val()),password:$.trim($("#password").val()),passwordConfirm:$.trim($("#password-confirm").val()),phone:$.trim($("#phone").val()),email:$.trim($("#email").val()),question:$.trim($("#question").val()),answer:$.trim($("#answer").val())},r=this.formValidate(e);r.status?s.register(e,function(e){window.location.href="./result.html?type=register"},function(e){n.show(e)}):n.show(r.msg)},formValidate:function(e){var r={status:!1,msg:""};return o.validate(e.username,"require")?o.validate(e.password,"require")?e.password.length<6?(r.msg="密码长度不能少于6位",r):e.password!==e.passwordConfirm?(r.msg="两次输入的密码不一致",r):o.validate(e.phone,"phone")?o.validate(e.email,"email")?o.validate(e.question,"require")?o.validate(e.answer,"require")?(r.status=!0,r.msg="验证通过",r):(r.msg="密码提示问题答案不能为空",r):(r.msg="密码提示问题不能为空",r):(r.msg="邮箱格式不正确",r):(r.msg="手机号格式不正确",r):(r.msg="密码不能为空",r):(r.msg="用户名不能为空",r)}};$(function(){u.init()})},55:function(e,r){}},[53]);