<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="media/jquery.easyui.min.js"/>
<link rel="stylesheet" href="media/easyui.css">
<%--顶部搜索--%>
<div class="layui-form-item">
    <form class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">学号:</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="stuId" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="stuname" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <button class="layui-btn" lay-submit lay-filter="searchForm">查询</button>
        </div>
    </form>

</div>
<%--动态表格--%>
<table id="userTable" lay-filter="userTable"></table>
<%--表格头部工具条--%>
<script type="text/html" id="headtool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm " lay-event="add"><i class="layui-icon">&#xe654;</i>添加</button>
        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-event="update"><i class="layui-icon">&#xe642;</i>编辑
        </button>
        <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除
        </button>
    </div>
</script>
<%--行工具条--%>
<script type="text/html" id="rowtool">
    <%--<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="assign">授权</a>--%>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>

<%--工具条--%>
<script>
    layui.use(['table', 'form'], function () {
        //初始化table对象，当前页面所有table都归lay管理,以id区分
        var table = layui.table;
        var form = layui.form;

        table.render({
            id: 'userTable'//设置渲染后的table
            , elem: '#userTable'//根据id取首次渲染的table
            , toolbar: '#headtool'//头部工具条
            , height: 523
            , url: '/sys/student.html?act=table' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'stuId', title: '学号', width: 80}
                , {field: 'stuname', title: '姓名'}
                , {field: 'stusex', title: '性别'}
                , {field: 'stuage', title: '年龄'}
                , {field: 'stumajor', title: '专业'}
                , {field: 'stuadress', title: '家庭地址'}
                , {field: 'phone', title: '联系电话'}
                , {field: 'status', title: '状态'}
                , {fixed: 'right', width:200, align:'center', toolbar: '#rowtool'}
            ]]
        });
        //添加搜索渲染
        form.on('submit(searchForm)', function (data) {
            console.log(data.field);//当前容器的全部表单字段，名值对形式：{name: value}
            table.reload('userTable', {//渲染后的tableid
                where: data.field //设定异步数据接口的额外参数
                //,height: 300
            });
            return false;
        });
        //监听头部工具条事件
        table.on('toolbar(userTable)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;//获取选中行数据
            switch (obj.event) {
                case 'add':
                    if (data.length>0) {
                        return;
                    }
                    openidielayer(data);//null
                    break;
                case 'delete':
                    if (data.length<1) {
                        return;
                    }
                    layer.confirm('你确定要删除吗？', {
                        btn: ['确定','取消'] //按钮
                    }, function(index){
                        var ids = new Array();
                        for (var i = 0; i < data.length; i++) {
                            ids.push(data[i].stuId)
                        }
                        $.ajax({
                            url: "/sys/student.html?act=delete",
                            method: "post",
                            data: "ids="+ids,//jquery获取表单内容
                            success: function (res) {
                                if (res.status) {
                                    table.reload('userTable', {});//删除数据后刷新table
                                } else {
                                    layer.msg(res.message);
                                }
                            }
                        })
                        layer.close(index)
                    })

                    break;
                case 'update':
                    if (data.length != 1) {
                        layer.msg('请选中一行数据');
                        return;
                    }
                    openidielayer(data[0]);
                    break;
            }
            ;
        });
        //监听行工具条
        table.on('tool(userTable)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            switch (layEvent) {
                case 'del':
                    layer.confirm('你确定要删除吗？', {
                        btn: ['确定','取消'] //按钮
                    }, function(index){
                        $.ajax({
                            url: "/sys/student.html?act=delete",
                            method: "post",
                            data: "ids="+data.stuId,//获取当前行的id
                            success: function (res) {
                                if (res.status) {
                                    table.reload('userTable', {});//删除数据后刷新table
                                } else {
                                    layer.msg(res.message);
                                }
                            }
                        })
                        layer.close(index)
                    })

                    break;
                case 'edit':
                    openidielayer(data);
                    break;
                case 'assign':
                    openrolelayer(data.id);
                    break;
            }
        })
        //打开弹出层,添加编辑共用
        function openidielayer(data) {//打开后往表单里添加数据
            layer.open({
                type: 1
                , area: '500px'
                , content: $("#editFormlayer").html()
                , btn: ['确认', '取消']
                , yes: function (index, layero) {
                    $.ajax({
                        url: "/sys/student.html?act=edit",
                        method: "post",
                        data: $("#editForm").serialize(),//jquery获取表单内容
                        success: function (res) {
                            if (res.status) {
                                table.reload('userTable', {});//添加数据后刷新table
                                layer.close(index);
                            } else {
                                layer.msg(res.message);
                            }
                        }
                    })
                }
                , btn2: function (index, layero) {
                    layer.close(index)
                }, success: function (layero, index) {
                    console.log(data);
                    form.render();//重新渲染table
                    form.val("editForm",data);//获取array的第0个元素渲染表单
                    form.val("editForm",{status:data.status+'',password:''});//数字转字符串才能被渲染
                }
            });
        }
    });
</script>
<%--添加功能弹出层--%>
<script type="text/html" id="editFormlayer">
    <form class="layui-form" style="width:80%;padding-top: 20px" id="editForm" lay-filter="editForm">
        <%--<input type="hidden" name="id">--%>
        <div class="layui-form-item">
            <label class="layui-form-label">学号：</label>
            <div class="layui-input-block">
                <input type="text" name="stuId" required lay-verify="required" placeholder="请输入学号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-block">
                <input type="text" name="stuname" required lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-block">
                <input type="text" name="stusex" required lay-verify="required" placeholder="请输入性别" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">年龄：</label>
            <div class="layui-input-block">
                <input type="text" name="stuage" required lay-verify="required" placeholder="请输入年龄" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">专业：</label>
            <div class="layui-input-block">
                <input type="text" name="stumajor" required lay-verify="required" placeholder="请输入专业" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家庭地址：</label>
            <div class="layui-input-block">
                <input type="text" name="stuadress" required lay-verify="required" placeholder="请输入家庭地址" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话：</label>
            <div class="layui-input-block">
                <input type="text" name="phone" required lay-verify="required" placeholder="请输入联系电话" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="text" name="status" required lay-verify="required" placeholder="请输入状态" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <%--<div class="layui-form-item">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" value="1" title="有效">
                <input type="radio" name="status" value="0" title="无效">
            </div>
        </div>--%>
    </form>
</script>

<%--授权功能弹出层--%>
<script type="text/html" id="menulayer">
    <ul id="menu-tree" class="easyui-tree">
    </ul>
</script>