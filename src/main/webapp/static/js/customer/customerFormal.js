$(function () {
    $("#customer_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,  // 斑马线  正式
        url:"/customerFormal/query.do",
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
    }),$("#fomal_dialog").dialog({
        width: 500,
        height: 500,
        closed: true,
        buttons:"#customerformal_btns"
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
        url:"/customer/update.do",
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
    var id = row.id;
    $.messager.confirm('恭喜', '您已成功开发客户？', function(r){
        if (r){
             $.get("/customer/formal.do",{id:id},function (data) {
                    if(data.success){
                        alert("修改成功");
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
    $.messager.confirm('确认', '确认开发失败？', function(r){
        if (r){
            $.get("/customer/failure.do",{id:id},function (data) {
                if(data.success){
                    alert("开发成功");
                }else {
                    alert("开发失败");
                }
            },"json");
        }
    });
}// 开发失败
function fail() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    var id = row.id;
    $.messager.confirm('确认', '确认开发失败？', function(r){
        if (r){
            $.get("/customer/failure.do",{id:id},function (data) {
                if(data.success){
                    alert("开发成功");
                }else {
                    alert("开发失败");
                }
            },"json");
        }
    });
}
// 开发失败
function bed() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    // 如果不为空.  就弹窗
    var id = row.id;
    $.messager.confirm('确认', '客户已流失？', function(r){
        if (r){
            $.get("/customer/away.do",{id:id},function (data) {
                if(data.success){
                    alert("修改成功");
                }else {
                    alert("修改失败");
                }
            },"json");
        }
    });
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
// 移交客户
function transfer() {
    // 判断用户有没有已经选择了一行数据
    var row = $("#customer_datagrid").datagrid("getSelected");
    if(row == null){
        $.messager.alert("温馨提示","请选择一条数据",'info');
        return;
    }
    $("#fomal_dialog").dialog("open");
    $("#fomal_dialog").dialog("setTitle","移交客户");
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
                $("#fomal_dialog").dialog("close");
                $("#customer_datagrid").datagrid("load");
            }else {
                alert("移交失败");
            }
        }
    });
}

function known() {
    $("#fomal_dialog").dialog("close");
}
