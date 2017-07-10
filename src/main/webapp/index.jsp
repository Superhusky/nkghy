<%--
  Created by IntelliJ IDEA.
  User: 15207
  Date: 2017/7/10
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>空港科技首页</title>
    <%@ include file="/common/headres.jsp" %>
    <script src="assets/includes/bootstrap/js/bootstrap-treeview.js"/>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        $('#treeController').treeview({data: getData()});
    });

    function getData() {
        alert(11);
    }
</script>
<body>
<div id="main" style="width: 1450px; height: 700px;">
    <div id="treeController" style="float: left; width: 280px;;height: 700px; border-right: solid red">

    </div>

    <div id="mainShow" style="float: right; width: 1150px;; height: 700px; border-bottom: solid red">
        <div id="messageShow" style="float : top; width: 1150px; height: 25px; border-bottom: solid red">

        </div>
        <div id="mapShow" style=" width:1150px;height: 600px; border-bottom: solid red">
            <button onclick="getData()">111111</button>
        </div>

        <div id="consoleShow" style="float : bottom; width: 1150px; height: 70px; border-bottom: solid red">

        </div>
    </div>
</div>
</body>
</html>
