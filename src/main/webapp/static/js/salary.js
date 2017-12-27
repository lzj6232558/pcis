$(function () {
    //抽取变量
    var salary_form = $("#salary_form");
    var salary_dialog = $("#salary_dialog");
    var salary_datagrid = $("#salary_datagrid");
    var btn_changeState = $("#btn_changeState");


    //01:初始化datagrid
    salary_datagrid.datagrid({
        fit: true,
        url: "/salary/query.do",
        fitColumns: true,
        striped: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#salary_toolbar",
        columns: [[
            {field: 'employeeId', title: '姓名', width: 100, align: 'center',formatter:function(value, row, index) {
                return value ? value.username : "";
            }},
            {field: 'departmentId', title: '部门', width: 100, align: 'center',formatter:function(value, row, index) {
                return value ? value.name : "";
            }},
            {field:'tel', title:'电话', width: 100, align: 'center'},
            {field:'email', title:'邮箱', width: 100, align: 'center'},
            {field:'baseSalary', title:'基本工资', width: 100, align: 'center'},
            {field:'bonus', title:'奖金', width: 100, align: 'center'},
            {field:'month', title:'日期', width: 100, align: 'center'},
            {field:'totalSalary', title:'总薪资', width: 100, align: 'center'},
            {field: 'remark', title: '查看薪资明细', width: 100, align: 'center',formatter:function(value, row, index){
                return '<a href="javascript:;" class="easyui-linkbutton" onclick="look('+index+')">查看</a>';
            },
            }
        ]],
    });

    //02: 初始化dialog
    $("#salary_dialog").dialog({
        width: 350,
        height: 300,
        closed: true
    });

    //03:初始化工资表导入弹出框
    $("#importXls").dialog({
        width: 250,
        height: 270,
        closed: true
    })
    //01:所有按钮标签的方法都交给methodObject对象来管理
    var methodObject = {
        /*//添加
        add: function () {
            salary_form.form("clear");
            salary_dialog.dialog("setTitle", "新建任务单");
            salary_dialog.dialog("open");
        },

        //编辑
        edit: function () {
            var salary = salary_datagrid.datagrid("getSelected");
            if (!salary) {
                $.messager.alert('温馨提示', '亲！请选择一条记录哦^_^', 'info');
                return;
            }
            salary_form.form("clear");
            salary_dialog.dialog("setTitle", "编辑任务单");
            var salId = salary.id;
            $.ajax({
                url: "/salary/listAll.do",
                data: {id: salId},
                async: false,
                success:function(data) {
                    $("[name=id]").val(data[0].task.id)
                    $("#sal_name").val(salary.employee.username);
                    $("#sal_sn").val(data[0].task.sn);
                    $("#_easyui_textbox_input5").val(data[0].task.plan);
                    $("#sal_place").val(data[0].task.place);
                    $("#sal_content").val(data[0].task.content);
                }
            });
            salary_dialog.dialog("open");
        },

        //保存
        save: function () {
            salary_form.form("submit", {
                url: "/salary/saveOrUpdate.do",
                success: function (data) {
                    var data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.message, "info", function () {
                            salary_dialog.dialog("close");
                            salary_datagrid.datagrid("load");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.message, "info");
                    }
                }
            })
        },

        //取消
        cancel: function () {
            salary_dialog.dialog("close");
        },

        //改变任务完成的状态
        changState: function () {
            var salary = salary_datagrid.datagrid("getSelected");
            if (!salary) {
                $.messager.alert("温馨提示", "亲！请选中一条数据哦！", "info");
                return;
            }
            var flag = salary.state ? "待完成" : "已完成";
            var state = salary.state ? "0" : "1";
            $.messager.confirm("确认窗口", "确认要标记" + flag + "么?", function (r) {
                if (r) {
                    var id = salary_datagrid.datagrid("getSelected").id;
                    $.get("/salary/changeState.do", {id: id, state: state}, function (data) {
                        if (data.success) {
                            $.messager.alert("温馨提示", data.message, "info", function () {
                                salary_datagrid.datagrid("reload");
                            })
                        } else {
                            $.messager.alert("温馨提示", data.message, "info");
                        }
                    })
                }
            })
        },*/

        //高级查询
      searchForSche: function () {
           //获取表单数据:
          var keyword = $("[name='keyword']").val();
           var dept = $(".easyui-combobox").combobox("getValue");
           var beginDate = $(".beginDate").combobox("getValue");
           var endDate = $(".endDate").combobox("getValue");
            //重新加载
          salary_datagrid.datagrid("load", {
               keyword: keyword,
               dept: dept,
               beginDate: beginDate,
               endDate: endDate
           });
        },


        //导出工资表
        exportXls: function () {
            $.messager.prompt('温馨提示', '请输入文件名!：', function(fileName){
                if (fileName){
                    window.location.href = "/salary/exportXls.do?fileName="+fileName;
                }
            });
        },
        //导入工资表:
        importXls:function () {
            $("#importXls").dialog("open")
            //统一设置属性:
            var improtMethod = {
                //1:下载模板:
                download:function () {
                    //$("#importXls").dialog("close")
                    window.location.href="/salary/dowloadTemplate.do"
                },
                beginImportXls:function () {
                    //先判断有没有选中文件:
                    var file = $("#improtFileBox").filebox("getValue");
                    if(file){
                        $("#importXls").dialog("close")
                        $('#endForm').submit();
                    }else{
                        $.messager.alert('温馨提示','请先选中一个文件!','info');
                    }
                }
            }
            //同一绑定事件:
            $("[data-improt]").click(function () {
                var improt = $(this).data("improt");
                improtMethod[improt]();
            });
        },


        //刷新
        reload: function () {
            salary_datagrid.datagrid("load");
        }
    };
    //所有按钮标签的点击事件对应的方法都交给methodObject对象来管理,调用的时候只需要获取对应的cmd质量,然后通过method对象来调用即可
    $("a[data-cmd]").click(function () {
        var cmd = $(this).data("cmd");
        methodObject[cmd]();
    });

});

//查看明细:
function look(index) {
    //根据索引选中一行:
    $('#salary_datagrid').datagrid('selectRow',index);
    //获取第一次选中一行的数据
    var salary = $('#salary_datagrid').datagrid('getSelected');
    console.log(salary)
    //清楚所有数据
    $("#salary_form").form("clear");
    //设置标题
    $("#salary_dialog").dialog("setTitle", "查看薪资明细");
    //获取选中数据的id;
    var salId = salary.id;
   $.ajax({
        url: "/salary/getItemBySalaryId.do",
        data: {id: salId},
        async: false,
        success:function(data) {
            console.log(data);
            $("#salary_basesalary").val(salary.baseSalary);
            $("#salary_bonus").val(salary.bonus);
            $("#salary_hire").val(data.hire);
            $("#salary_pay").val(data.pay);
            $("#salary_tax").val(data.tax);
            $("#salary_postsalary").val(data.postsalary);
            $("#salary_ifpay").val(data.ifpay ? '是':'否');
            $("#salary_programme").val(data.programme);
            $("#salary_totalsalary").val(salary.totalSalary);
        }
    });
    $("#salary_dialog").dialog("open");
}

