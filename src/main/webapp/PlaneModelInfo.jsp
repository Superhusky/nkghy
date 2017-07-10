<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/bootstrap_table/bootstrap-table.min.css">

    <script src="<%=basePath%>/assets/js/jquery-3.2.1.js"></script>
    <script src="<%=basePath%>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>/assets/bootstrap_table/bootstrap-table.min.js"></script>
    <script src="<%=basePath%>/assets/bootstrap_table/bootstrap-table-zh-CN.js"></script>
    <script src="<%=basePath%>/assets/js/modal.js"></script>
</head>
<body>

<div style="padding: 30px">
<div id="toolbar" class="btn-group">
    <button id="btn_add" type="button" class="btn btn-primary btn-sm">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
    </button>
    <button id="btn_delete" type="button" class="btn btn-danger btn-sm">
        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
    </button>
</div>

<div class="table-responsive" >
    <table id="planelist"></table>
</div>
</div>

</body>

<script>

    $(function () {
        //1.初始化Table
        var oTable = new TableInit();
        oTable.Init();

//        //2.初始化Button的点击事件
//        var oButtonInit = new ButtonInit();
//        oButtonInit.Init();
    });


    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#planelist').bootstrapTable({
                url: '/planeInfo/list.do',         //请求后台的URL（*）
                method : 'post', //请求方式（*）
                contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
                classes : 'table table-hover table-no-bordered table-condensed',//table的css样式
                striped : false, //是否各行变色
                cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination : true, //是否显示分页（*）
                paginationVAlign : 'bottom',//top或者bottom，表示分页显示在上面还是下面
                paginationHAlign : 'left',
                paginationDetailHAlign : 'right',
                paginationLoop : false,
                sortable : true, //是否启用排序
                sortName : 'guid',//排序字段,可以 用java类中的属性名称
                sortOrder : "desc", //排序方式
                queryParams : oTableInit.queryParams,//传递参数（*）
                sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
                pageNumber : 1, //初始化加载第一页，默认第一页
                pageSize : 10, //每页的记录行数（*）
                pageList : [ 10, 20, 50, 100, 500 ], //可供选择的每页的行数（*）
                search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch : true,
                showColumns : false, //是否显示所有的列
                showRefresh : false, //是否显示刷新按钮
                minimumCountColumns : 2, //最少允许的列数
                clickToSelect : true, //是否启用点击选中行
                height : screen.availHeight - 330, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId : "guid", //每一行的唯一标识，一般为主键列
                showToggle : false, //是否显示详细视图和列表视图的切换按钮
                cardView : false, //是否显示详细视图
                detailView : false, //是否显示父子表
                columns: [{
                    checkbox: true
                }, {
                    field: 'aircraftnum',
                    title: '机号'
                }, {
                    field: 'model',
                    title: '机型'
                }, {
                    field: 'maximumweight',
                    title: '最大起全权重'
                }, {
                    field: 'maximuncarrying',
                    title: '最大业载'
                }, {
                    field: 'seating',
                    title: '座位数'
                },{
                    field: 'airwaysid',
                    title: '航空公司id'
                },{
                    field: 'starttime',
                    title: '有效起始日期'
                },{
                    field: 'endtime',
                    title: '有效结束日期'
                },{
                    field: 'rmark',
                    title: '备注'
                },{
                    field : 'option',
                    title : '操作',
                    align : 'center',
                    formatter : function(value, row, index) {
                        var edit = '<button type="button" class="btn btn-primary btn-xs" aria-label="Left Align" onclick="doUpdate('+row.guid+')">'
                           + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑'
                           +  '</button>';
                        var del = '<button type="button" class="btn btn-danger btn-xs" aria-label="Left Align" onclick="doDel('+row.guid+')">'
                            + '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除'
                            +  '</button>';
                        return edit + del;

                    }
                }
                ]
            });
        };
        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                offset: params.offset  //数据偏移量:依次是0,1*limit,2*limit
            };
            return temp;
        };
        return oTableInit;
    };

    function doUpdate(guid) {
        Ewin.dialog({
            id:'aa',
            title:'用户编辑',
            url:'/user/edit.do',
            width:800
        })
    }
    
    function doDel(guid) {
        Ewin.alert(guid)
    }

</script>

</html>
