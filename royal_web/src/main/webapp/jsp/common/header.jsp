<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <a href="javascript:;" id="login" class="to-login">游客登录</a>
                <a href="${pageContext.request.contextPath}/user/findRegister.do">【新用户注册】</a>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="${pageContext.request.contextPath}/images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action="" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
                            <li><input type="submit" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
  $(function () {
      //显示弹框
      $('.box #login').click(function () {
          var className = $(this).attr('class');
          $('#dialogBg').fadeIn(300);
          $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
          $('#userName').focus();
          $("#j_fixedBar").hide();
      });

      //关闭弹窗
      $('.closeDialogBtn').click(function () {
          $('#dialogBg').fadeOut(300, function () {
              $('#dialog').addClass('bounceOutUp').fadeOut();
              $("#j_fixedBar").show();
          });
      });

      //校验用户名
      function checkUserName() {
          $("#checkUserName").text("");
          $("#checkUserName").attr("style","display:none");
          var userName_reg = $("#userName_reg").val();
          var userName_Reg =/^[a-zA-Z0-9_]+$/;
          var boolean = userName_Reg.test(userName_reg);
          if("" == userName_reg){
              alert("用户名不能为空!");
              return false;
          }else{
              if (boolean){
                  return true;
              }else{
                  alert("用户名格式错误,请重新输入");
                  return false;
              }
          }
      }

      //校验密码
      function checkPassWord() {
          var userPass_in = $("#userPass_reg").val();
          var userPass_Reg =/^[a-zA-Z0-9]{6,10}$/;
          var boolean = userPass_Reg.test(userPass_in);
          if("" == userPass_in){
              alert("密码不能为空!");
              return false;
          }else{
              if (boolean){
                  return true;
              }else{
                  alert("密码格式错误");
                  return false;
              }
          }
      }

  });
</script>
</html>