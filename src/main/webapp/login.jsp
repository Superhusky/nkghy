<%--
  Created by IntelliJ IDEA.
  User: zhangkaihua
  Date: 2017/6/30
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="common/header.jsp" %>
<div class="container">
    <div class="loginWrap">
        <div class="loginForm">
            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;账号不存在
            </div>
            <div class="input-group">
				  <span class="input-group-addon" id="basic-addon1"><span class="
glyphicon glyphicon-envelope"></span></span>
                <input type="text" class="form-control" placeholder="邮箱" aria-describedby="basic-addon1">
            </div>
            <div class="input-group">
                <span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-lock"></span></span>
                <input type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-sm-7">
                        <div class="input-group">
							  <span class="input-group-addon" id="basic-addon3"><span class="
			glyphicon glyphicon-exclamation-sign"></span></span>
                            <input type="text" class="form-control" placeholder="验证码" aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <img border="0" style="cursor:pointer" alt="点击刷新验证码" onclick="getimgcode()"
                             src="/captcha.action" id="captchaImg">
                    </div>
                </div>
            </div>
            <div class="loginBtn">
                <a href="#" class="btn btn-primary" role="button">登&nbsp;&nbsp;录</a>
            </div>
            <div class="forgetPass">
                <a target="_blank" href="findPass.html">忘记密码？</a>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
</div>
<%@ include file="common/footer.jsp" %>