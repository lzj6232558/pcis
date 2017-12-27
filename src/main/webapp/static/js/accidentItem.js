$(function () {
    //图片大小:
    $(".fancybox").fancybox();
    //抽取变量
    var accidentItem_form = $("#accidentItem_form");
    var accidentItem_dialog = $("#accidentItem_dialog");
    var accidentItem_datagrid = $("#accidentItem_datagrid");
    var btn_changeState = $("#btn_changeState");
    var badTable_dialog = $("#badTable_dialog");
    var totalDamage = 0;


    //01:初始化datagrid
    accidentItem_datagrid.datagrid({
        fit: true,
        url: "/accidentItem/query.do",
        fitColumns: true,
        striped: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#accidentItem_toobar",
        columns: [[
            {field: 'imagePath',title: '事故图片',width:100,align:'center',formatter:function(value, row, index) {
                var imagePathSmall = value ? row.imagePathSmall:"/upload/car_small.jpg";
                var imagePath = value ? row.imagePath:"/upload/car_small.jpg";
                imagePathSmall = "/static"+imagePathSmall;
                imagePath = "/static"+imagePath;
                return '<a class="fancybox" href="'+imagePath+'" data-fancybox-group="gallery" title="事故现场"><img src="'+imagePathSmall+'" class="list_img_min"/></a>'
            }},
            {field: 'name',title: '车主',width:100,align:'center',formatter:function(value, row, index) {
                return row.accidentId ? row.accidentId.name : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'plateNumber',title: '车牌号',width:100,align:'center',formatter:function(value, row, index) {
                return row.accidentId ? row.accidentId.plateNumber : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'policySn',title: '保单编号',width:100,align:'center',formatter:function(value, row, index) {
                return row.accidentId ? row.accidentId.policySn : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'accidentId',title: '事故地点',width:100,align:'center',formatter:function(value, row, index) {
                return value ? value.reporterPlace : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'introduction',title: '事故描述',width:100,align:'center',formatter:function(value, row, index) {
                return value ? row.introduction : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'totalDamage',title: '赔损总金额',width:100,align:'center',formatter:function(value, row, index) {
                return value >= 0 ? row.totalDamage: "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'handDate', title: '处理日期', width: 100, align: 'center',formatter:function(value, row, index) {
                return value ? row.handDate : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'username', title: '处理人员', width: 100, align: 'center',formatter:function(value, row, index) {
                return row.employeeId ? row.employeeId.username : "<font color='#ccc'>请补充完整</font>";
            }},
            {field: 'xxx', title: '赔偿单', width: 100, align: 'center',formatter:function(value, row, index) {
                return '<a href="javascript:;" class="easyui-linkbutton" onclick="look('+index+')">查看赔偿单</a>';
            }},
            /*{field: 'employeeId', title: '状态', width: 100, align: 'center',formatter:function(value, row, index) {
                return '<a href="javascript:;" class="easyui-linkbutton" onclick="look('+index+')">审核</a>';
            }},*/
        ]],
    });

    //初始化dialog
    badTable_dialog.dialog({
        title:"赔偿单明细",
        width: 320,
        height: 350,
        closed: true
    });

    // 初始化dialog
    accidentItem_dialog.dialog({
        width: 500,
        height: 500,
        buttons:"#accidentItem_btns",
        closed: true
    });

    //03:所有按钮标签的方法都交给methodObject对象来管理
    var methodObject = {
        //补全明细
        edit: function () {
            //回显赔偿单:
            var row = accidentItem_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请先选择一条车险明细', 'info');
                return;
            }
            $.get("/badTable/getBadListById.do",{id:row.id},function (data) {
                for (var i = 0;i<data.length;i++){
                    $("#edit_table_body :input").each(function (index,item) {
                        $("[tag='badPath']").val(data[i].badPath);
                       $("[tag='badDegree'] option").each(function (index,item) {
                            if(item.value == data[i].badDegree){$(item).prop("selected",true)}
                       })
                        $("[tag='badMoney']").val(data[i].badMoney);
                    })
                }
            })
            //回显明细单id:
            accidentItem_form.form("clear");
            //回显id:
            $("#itemId").val(row.id);
            //回显所有数据:
            accidentItem_form.form('load', row);
            accidentItem_dialog.dialog("setTitle", "添加报案单");
            accidentItem_dialog.dialog("open");
        },


        //删除:
        remove:function () {
            //先获取一条数据
            var row = accidentItem_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '亲！请先选择一条记录!', 'info');
                return;
            }
            //确认删除:
            $.messager.confirm('温馨提示', '确认删除吗？', function(){
                alert(444);
                $.get("/accidentItem/delete.do",{id:row.id},function (data) {
                    if(data.success){
                        $.messager.confirm('温馨提示', '删除成功!', function(r){
                            //重新加载
                            accidentItem_datagrid.datagrid("reload");
                        });
                    }else{
                        $.messager.alert('温馨提示', '删除失败!');
                    }
                })
            });
        },

        //保存
        save: function () {
            accidentItem_form.form("submit", {
                url: "/accidentItem/saveOrUpdate.do",
               onSubmit: function(){
                    //提交多条明细:在提交表单之前修改name值:
                   //遍历:提交
                   $("#edit_table_body tr").each(function(index,item){
                        $(item).find("[tag=badPath]").prop("name","badTables["+index+"].badPath");
                        $(item).find("[tag=badDegree]").prop("name","badTables["+index+"].badDegree");
                        $(item).find("[tag=badMoney]").prop("name","badTables["+index+"].badMoney");
                   })
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.message, "info", function () {
                            accidentItem_dialog.dialog("close");
                            accidentItem_datagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.message, "info");
                    }
                }
            })
        },

        //取消
        cancel: function () {
            accidentItem_dialog.dialog("close");
        },


        //高级查询
        searchForm: function () {
           //获取表单数据:
          var keyword = $("[name='keyword']").val();
           var beginDate = $(".beginDate").combobox("getValue");
           var endDate = $(".endDate").combobox("getValue");
            //重新加载
          accidentItem_datagrid.datagrid("load", {
               keyword: keyword,
               beginDate: beginDate,
               endDate: endDate
           });
        },

        //生成事故明细表:
        chakan2:function(){
            //先选中一条数据
            var row = accidentItem_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条事故信息！", "info");
                return;
            }
            //生成事故明细表:
            $.post("/accidentItemItem/save.do",{"accidentItemId.id":row.id},function (data) {
                if(data.success){
                    $.messager.alert("温馨提示", "事故明细表已生成！", "info");
                }else{
                    $.messager.alert("温馨提示", "事故明细表生成失败！", "info");
                }
            })
        },

        //刷新
        reload: function () {
            accidentItem_datagrid.datagrid("load");
        },

    };
    //所有按钮标签的点击事件对应的方法都交给methodObject对象来管理,调用的时候只需要获取对应的cmd质量,然后通过method对象来调用即可
    $("a[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

    //计算总赔偿金额:
    $("#badMoney").blur(function(){
        totalDamage = 0;
        //拿到所有的数字框:
        var badMoney = this.value || 0;
        totalDamage += parseInt(badMoney);
        $("#totalDamage").textbox("setValue",totalDamage);
    })

});


//查看赔偿单:
function look(index) {
    //获取行:
    $("#accidentItem_datagrid").datagrid("selectRow",index);
    var row = $("#accidentItem_datagrid").datagrid("getSelected");
    //清除数据
    $("#badTable_form").form("clear");
    //回显数据:
    $.get("/badTable/getBadListById.do",{id:row.id},function (data) {
        for (var i = 0;i<data.length;i++){
            $("#badTable_dialog :input").each(function (index,item) {
                $("[tag='badPath']").val(data[i].badPath);
                $("[tag='badDegree'] option").each(function (index,item) {
                    if(item.value == data[i].badDegree){$(item).prop("selected",true)}
                })
                $("[tag='badMoney']").val(data[i].badMoney);
            })
        }
    })
    $("#badTable_dialog").dialog("open");
}

//计算总和:
function addMoney(){
    alert(44);
}

