$(function () {
    $("#customer_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,  // 斑马线
        url:"/customer/query.do",
        toolbar:"#toolbar",
        pagination: true,
        singleSelect: true,
        rownumbers:true,
        columns: [[
            {field:'inputdate',title:'创建日期',width:100},
            {field:'inputuser',title:'创建人',width:60,formatter: function(value,row,index){
                return value != null? value.username:'';
            }},
            {field:'changeuser',title:'负责人',width:60,formatter: function(value,row,index){
                return value !=null ? value.username:'';
            }},
            {field:'name',title:'客户姓名',width:70},
            {field:'age',title:'年龄',width:35},
            {field:'gender',title:'性别',width:35,formatter: function(value,row,index){
                    return value == 0? '女':'男';
            }
            },
            {field:'job',title:'职业',width:100},
            {field:'salary',title:'薪资水平',width:100},
            {field:'tel',title:'电话',width:100},
            {field:'email',title:'邮箱',width:100},
            {field:'address',title:'客户地址',width:100},
            {field:'source',title:'客户来源',width:100},
            {field:'qq',title:'QQ',width:80},
            {field:'idno',title:'身份证号',width:100}
        ]]
    }),
      // 定义好一个dialog 等下可以直接用
    $("#customer_dialog").dialog({
        width: 500,
        height: 500,
        closed: true,
        buttons:"#customer_btns"
    }),
    $("#fail_dialog").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons:"#customerfail_btns"
    }),
    // 失败框
    $("#lost_dialog").dialog({

        width: 300,
        height: 300,
        closed: true,
        buttons:"#customerlost_btns"
    }),
        // 移交框
    $("#transfer_dialog").dialog({
        width: 300,
        height: 400,
        closed: true,
        buttons:"#customertrans_btns"
    })
})

function add() {
    // 因为填了之后会显示; 所以先清空表单
    $("#customer_form").form("clear");
    //打开弹窗
    $("#customer_dialog").dialog("open");
    //设置标题
    $("#customer_dialog").dialog("setTitle","新增客户");
}


 // 编辑
function edit() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 因为填了之后会显示; 所以先清空表单
    $("#customer_form").form("clear");
    // 选择了就做回显
    $("#customer_form").form("load",row);

    //打开弹窗
    $("#customer_dialog").dialog("open");
    //设置标题
    $("#customer_dialog").dialog("setTitle","编辑客户");
}

// 保存
function save() {
    $('#customer_form').form('submit', {
        url:"/customer/saveOrUpdate.do",
        success: function(data){
            var data = eval('(' + data + ')');
            if (data.success){
                alert(data.message);
                //关闭弹窗
                  $("#customer_dialog").dialog("close");
            // 刷新表
                $("#customer_datagrid").datagrid("load");
            }else {
                alert(data.message)
            }
        }
    })
}
//取消按钮
function cancel() {
    // 关闭当前弹窗即可
    $("#customer_dialog").dialog("close");
}

// 刷新
 function reload() {
  $("#customer_datagrid").datagrid("load");
 }

 // 开发成功
function ok() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    //清空表单数据

    var id = row.id;
    var name = row.name;
    $.messager.confirm('恭喜', '您已成功开发客户'+name+'？', function(r){
        if (r){
             $.get("/customer/formal.do",{id:id},function (data) {
                    if(data.success){
                        alert("修改成功");
                        $("#customer_datagrid").datagrid("load");
                    }else {
                        alert("修改失败");
                    }
             },"json");
        }
    });
}

// 开发失败
function fail() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    var id = row.id;
    var name = row.name;
    $.messager.confirm('确认', '客户:'+name+'开发失败？', function(r){
        if (r){
            // 要先打开失败原因框  否则发送了ajax 直接就修改了
            $("#fail_dialog").dialog("open");
            $("#fail_dialog").dialog("setTitle","设置开发失败");
            var row = $("#customer_datagrid").datagrid("getSelected");
            if(row.changeuser){
            $("#nowname").text(row.changeuser.username)};
        }
    });
}
// 流失
function bed() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    var id = row.id;
    var name = row.name;
    $.messager.confirm('确认', '客户:'+name+'已流失？', function(r){
        if (r){
            // 要先打开失败原因框  否则发送了ajax 直接就修改了
            $("#lost_dialog").dialog("open");
            $("#lost_dialog").dialog("setTitle","设置客户流失");
            var row = $("#customer_datagrid").datagrid("getSelected");
            if(row.changeuser){
            $("#nowname_lost").text(row.changeuser.username)};
        }
    });
}

// 移入资源池
function share() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    var id = row.id;
    var name = row.name;
    $.messager.confirm('确认', '将客户:'+name+'移入资源池？', function(r){
        if (r){
            $.get("/customer/move.do",{id:id},function (data) {
                if(data.success){
                    alert("移入成功");
                    $("#customer_datagrid").datagrid("load");
                }else {
                    alert("移入失败");
                }
            },"json");
        }
    });
}
// 失败确认
function bigo() {
    // 提交表单上去.
    var row = $("#customer_datagrid").datagrid("getSelected");
    var id = row.id;
    row["cust.id"] = row.id;
    if(row.changeuser){
        row["chargeuser.id"] = row.changeuser.id;
    }
    $("#fail_form").form('load',row);
    $("#fail_form").form('submit',{
        url:"/customerfail/saveOrUpdate.do",
        success:function(data){
           var data= $.parseJSON(data);
           console.log(data);
           if(data.success){
                $.get("/customer/failure.do",{id:id},function (data) {
                    if(data.success){
                        alert("设置成功");
                        $("#customer_datagrid").datagrid("load");
                        $("#fail_dialog").dialog("close");
                    }else {
                        alert("设置失败");
                    }
                },"json");
            }
        }
    })
}
// 流失确认按钮
 function nice() {
     // 提交表单上去.
     var row = $("#customer_datagrid").datagrid("getSelected");
     var id = row.id;
     row["cust.id"] = row.id;
     if(row.changeuser){
         row["chargeuser.id"] = row.changeuser.id;
     }
     console.log(row);
     $("#lost_form").form('load',row);
     $("#lost_form").form('submit',{
         url:"/customerlost/saveOrUpdate.do",
         success:function(data){
             var data= $.parseJSON(data);
             if(data.success){
                 $.get("/customer/away.do",{id:id},function (data) {
                     if(data.success){
                         alert("设置成功");
                         $("#customer_datagrid").datagrid("load");
                         $("#lost_dialog").dialog("close");
                         //关闭窗口
                     }else {
                         alert("设置失败");
                     }
                 },"json");
             }
         }
     })
 }
 // 取消按钮
//取消按钮
function non() {
    // 关闭当前弹窗即可
    $("#fail_dialog").dialog("close");
}
//取消按钮
function cance() {
    // 关闭当前弹窗即可
    $("#lost_dialog").dialog("close");
}
// 高级查询
function mysearch() {
    // 获取到组件上的值
    var begin= $("#beg").textbox("getValue");
    var end = $("#end").textbox("getValue");
    var keywords= $("#keyword").textbox("getValue");
    // 加载和显示第一页的所有行。
    $("#customer_datagrid").datagrid("load",{
        beginDate:begin,
        endDate:end,
        keywords:keywords
    })
}
 function known() {
     $("#transfer_dialog").dialog("close");
 }

function transfer() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    $("#transfer_dialog").dialog("open");
    $("#transfer_dialog").dialog("setTitle","移交客户");
    // 数据显示
    var name = row.name;
    $("#name").text(name);
    $("#transfer_form").form("clear");
}
function yees() {
    // 点击保存的时候.  把row中的数据放入form中
    var row = $("#customer_datagrid").datagrid("getSelected");
    row["customer.id"] = row.id;  // 同名匹配.   所以为它设置  customer.id 这个属性
    $("#transfer_form").form("load",row);
    $("#transfer_form").form("submit",{
        url:"/customertransfer/save.do",
        success:function(data){
            var data= $.parseJSON(data);
            if(data.success){
                alert("移交成功");
                $("#transfer_dialog").dialog("close");
                $("#customer_datagrid").datagrid("load");
            }else {
                alert("移交失败");
            }
        }
    });
}