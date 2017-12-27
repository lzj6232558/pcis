jQuery.ajaxSettings.traditional = true;
$(function () {
    $("#hidethis").hide();
    var customer_dialog = $("#customer_dialog");
    var choose_product_dialog = $("#choose_product_dialog");
    var product_datagrid = $("#product_datagrid");
    var car_datagrid = $("#car_datagrid");
    var customer_datagrid = $("#customer_datagrid");
    var customer_form = $("#customer_form");
    var choose_customer_dialog = $("#choose_customer_dialog");
    var choose_car_dialog = $("#choose_car_dialog");
    var safe_select = $("#safe_select");
    var province = $("#province")
    var city = $("#city")
    var tempoary_datagrid = $("#tempoary_datagrid");
    var temp_form = $("#temp_form");
    var editIndex = undefined;
    //选择产品弹出框初始化
    $("#choose_productItem_dialog").dialog({
        width: 800,
        height: 350,
        buttons: '#product_btns2',
        closed: true,
        modal: true
    });

    //初始化暂存列表//查询状态为0的保单信息
    tempoary_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#temporary_toolbar",
        url: '/policy/query.do?state=' + 0,
        columns: [[
            {
                field: 'sn', title: '保单编号', width: 100, align: 'center', formatter:
                function (value, row, index) {
                    return '<span style="font-size:10px">' + value + '</span>';//改变表格中内容字体的大小
                },
            },
            {
                field: 'customer', title: '投保人', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.name : "";
            }
            },
            {
                field: 'car', title: '车牌号', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.plateNumber : "";
            }
            },
            {field: 'beginDate', title: '开始日期', width: 100, align: 'center'},

            {field: 'checkDate', title: '核保日期', width: 100, align: 'center'},
            {field: 'endDate', title: '保险截止日', width: 100, align: 'center'},
            {field: 'duration', title: '保险时长(年)', width: 100, align: 'center'},
            {
                field: 'inputUser', title: '业务员', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.realname : "";
            }
            },
            {
                field: 'auditor', title: '审核人', width: 100, align: 'center', formatter: function (value, row, index) {
                return value ? value.realname : "";
            }
            },
            {field: 'totalAmount', title: '投保总金额', width: 100, align: 'center'},
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                var state = "";
                if (value == 0) {
                    state = "暂存";
                } else if (value == 1) {
                    state = "已提交待审核"
                } else if (value == 2) {
                    state = "已审核待缴费"
                } else if (value == 3) {
                    state = "待修改"
                } else if (value == 4) {
                    state = "已缴费"
                } else if (value == 5) {
                    state = "拒保"
                }
                return state;
            }
            },
            {
                field: 'detail', title: '查看明细', width: 100, align: 'center', formatter: function (value, row, index) {
                return "<a onclick='lookProductItem(this)' href='javascript:;' id='detail'>保险产品明细</a>";
            }
            }
            /*{
             field: 'deptSn', title: '部门编号', width: 100, align: 'center', formatter: function (value, row, index) {
             return row.department ? row.department.sn : "";
             }
             },*/

        ]]
    })
    //初始化编辑弹出框
    $('#temp_dialog').dialog({
        title: '保单修改',
        width: 1200,
        height: 600,
        buttons: "#temporary_btns",
        resizable: true,
        closed: true,
        cache: false,
        modal: true
    });

    /*初始化操作*/
    // 初始化保险产品选择页面数据表格
    product_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        toolbar: '#product_toolbar',
        url: "/product/query.do",
        pagination: true,
        rownumbers: true,
        ctrlSelect: true,
        columns: [[
            {field: 'sn', title: '保险编号', width: 100},
            {field: 'name', title: '保险名称', width: 100},
            {
                field: 'safetymechanism', title: '保险机构', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'safeguardYear', title: '保障年限', width: 100},
            {
                field: 'totalMoney', title: '保额/车价(万)', width: 100, editor: 'text',
                type: 'combobox'
            },
            {field: 'annuafee', title: '基本年费', width: 100},
            {
                field: 'salesStatus', title: '销售状态', width: 100,
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>在售</font>" : "<font color='red'>停售</font>"
                }
            },
            {
                field: 'undeduction', title: '不计免赔', width: 100,
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>是</font>" : "<font color='red'>否</font>"
                }
            }
        ]], onClickCell: function (index, field, value) {
            if (editIndex == undefined) {
                if (field == "totalMoney") { // 判断是否是field为six列，如果不是固定某列的话，不需要判断
                    $(this).datagrid('beginEdit', index);
                    var ed = $(this).datagrid('getEditor', {index: index, field: field});
                    $(ed.target).focus();
                }
                editIndex = index;
                //alert("点击触发editIndex:" + editIndex);

            }
            else if (editIndex != undefined) {//如果不相等，说明已经打开编辑器了，需要关闭编辑器

                $(this).datagrid('endEdit', editIndex);
                editIndex = undefined;
                //alert("关闭编辑器");
            }
            //$(this).datagrid('onClickRow');
        }, onAfterEdit: function (index, row, changes) { // 关闭编辑器后触发
            var updated = $(this).datagrid('getChanges', 'updated');  // updated 是一个getChanges的属性，可以查看api
            //alert("onAfterEdit。。"+updated.length);
            if (updated.length < 1) {  // 如果编辑器中的数据已经修改，则length为1，否则为0，判断是否已经修改数据
                editRow = undefined;
                $(this).datagrid('unselectAll');
                return;
            } else {
                // 传值
                //alert("提交数据");
                submitForm(index, row, changes);  //这里ajax提交数据的方法
            }
        }
    });
    //初始化查看商品明细数据表格
    $("#productItem_datagrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        toolbar: '#product_toolbar',
        url: "/product/queryItem.do",
        rownumbers: true,
        ctrlSelect: true,
        columns: [[
            {field: 'sn', title: '保险编号', width: 100},
            {field: 'name', title: '保险名称', width: 100},
            {
                field: 'safetyMechanism', title: '保险机构', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'currentTotalMoney', title: '保额(万)', width: 100},
            {field: 'currenAnnufee', title: '基本年费', width: 100},
        ]]
    });
    //从远程加载省份的城市
    province.combobox({
        onChange: function (newValue, oldValue) {
            city.combobox({
                valueField: 'id', textField: 'name',
                url: "/province/selectAllCity.do?provinceId=" + newValue
            })
        }
    })

    $("#carcategory").combobox({})
    //机构的选择
    safe_select.combogrid({
        url: '/safe/list.do',
        panelWidth: 450,
        value: '请选择',
        idField: 'id',
        textField: 'name',
        columns: [[
            {field: 'id', title: '编号', width: 50},
            {field: 'sn', title: '机构代码', width: 100},
            {field: 'name', title: '机构名称', width: 100},
            {field: 'legalperson', title: '法人代表', width: 100},
            {field: 'dentitycard', title: '法人身份证', width: 100},
            {field: 'contactway', title: '联系方式', width: 100},
            {field: 'address', title: '联系地址', width: 100},
            {
                field: 'cooperation', title: '合作状态', width: 100,
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>合作</font>" : "<font color='red'>解除</font>"
                }
            }
        ]],
        onChange: function () {

            //$("#safetyMechanismId").val(i);
            var hiddens = $(".product-tr");
            $.each(hiddens, function (index, item) {
                if (index != 0) {
                    $(item).remove();
                }
            });
            $("#totalAmount").html(0);
        }
    })
    //选择客户弹出框初始化
    choose_customer_dialog.dialog({
        width: 800,
        height: 350,
        buttons: '#customer_btns2',
        closed: true,
        modal: true
    });
    //选择产品弹出框初始化
    choose_product_dialog.dialog({
        width: 800,
        height: 350,
        buttons: '#product_btns2',
        closed: true,
        modal: true
    });
    choose_car_dialog.dialog({
        width: 800,
        height: 350,
        buttons: '#car_btns2',
        closed: true,
        modal: true
    });
    //初始化客户数据表格
    customer_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        // toolbar: '#customer_toolbar',
        //查询状态为2的客户(会员客户)
        url: "/customer/selectOfficialCustomer.do",
        pagination: true,
        singleSelect: true,
        rownumbers: true,
        columns: [[
            {field: 'name', title: '客户姓名', width: 70},
            {
                field: 'gender', title: '性别', width: 40, formatter: function (value, row, index) {
                if (value == null) {
                    return '未知';
                }
                if (value) {
                    return '男';
                }
                if (!value) {
                    return '女';
                }
            }
            },
            {field: 'tel', title: '电话', width: 100},
            {field: 'address', title: '客户地址', width: 100},
            {field: 'idno', title: '身份证号', width: 100}
        ]],
        onDblClickRow: function () {
            sure_customer();
        }
    })
    //初始化车辆信息的弹出框
    car_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        // toolbar: '#customer_toolbar',
        url: "/car/query.do",
        pagination: true,
        singleSelect: true,
        rownumbers: true,
        columns: [[
            {field: 'brand', title: '车辆品牌', width: 100},
            {field: 'model', title: '车辆型号', width: 100},
            {field: 'gasDisplacement', title: '排气量', width: 100},
            {field: 'valuation', title: '市场估价', width: 100},
            {
                field: 'category', title: '分类', width: 100,
                formatter: function (value, row, index) {
                    return value ? "<font color='blue'>载客</font>" : "<font color='red'>载物</font>"
                }
            },
            {
                field: 'size', title: '车载大小', width: 100,
                formatter: function (value, row, index) {
                    if (value == 0 && value == '') {
                        return "小型";
                    } else if (value == 1) {
                        return "中型";
                    } else if (value == 1) {
                        return "大型";
                    } else {
                        return "超大型";
                    }
                }
            },
            {field: 'remarks', title: '备注信息', width: 100}
        ]],
        onDblClickRow: function () {
            sure_car();
        }
    });


    // 统一绑定方法
    var methodObject = {
        // 选中的保险产品信息添加到页面
        select_product: function () {
            var products = product_datagrid.datagrid("getSelections");

            var oids = $.map($(".product-tr"), function (item, index) {
                return $(item).attr("data-oid");
            });

            $.each(products, function (index, item) {
                if ($.inArray(item.id + "", oids) == -1) {
                    var clone = $("#hidethis").clone(true);
                    clone.show();
                    clone.attr("data-oid", item.id);
                    clone.find("#productName").html(item.name);
                    clone.find("#productSn").html(item.sn);
                    clone.find("#annuaFlee").html(item.annuafee);
                    clone.insertAfter($("#hidethis"));
                }
            });
            var totalAmount = 0;
            $.each($(".annuaFlee"), function (index, item) {
                totalAmount = totalAmount + parseInt(item.innerHTML);
            })
            var duration = $("[name=duration]").val();
            $("#totalAmount").html(totalAmount * duration);
            choose_product_dialog.dialog("close");
        },
        // 添加商品信息
        add_product: function () {
            var safeId = $("#safe_select").combobox("getValue");
            console.log(safeId)
            if (safeId == "请选择" || safeId == null || safeId == '') {
                $.messager.alert("温馨提示", "亲, 请先选择承保机构!", "warning");
                return;
            }
            choose_product_dialog.dialog("open");
            choose_product_dialog.dialog("setTitle", "按住 Ctrl 多选保险产品");
            //机构id和合作状态
            /*$.get("/product/query.do" ,{smId: safeId, sStatus: 1}, function (data) {
                console.log(data)
                product_datagrid.datagrid("loadData", data);
            });*/
            product_datagrid.datagrid("load", {smId: safeId, sStatus: 1});
            var clone = $("#hidethis").clone(true);

        },
        add_customer: function () {
            //清空表单数据
            customer_form.form("clear");

            customer_dialog.dialog("setTitle", "新增");

            customer_dialog.dialog("open")
        },
        //选择客户
        choose_customer: function () {

            choose_customer_dialog.dialog("setTitle", "选择客户");

            choose_customer_dialog.dialog("open")
        },
        //选择车辆
        choose_car: function () {
            choose_car_dialog.dialog("setTitle", "选择车辆");
            choose_car_dialog.dialog("open")
        },
        //保存,提交表单
        save: function () {
            customer_form.form("submit", {
                url: '/customer/saveOrUpdate.do',
                success: function (data) {
                    //使用easyui的form提交,需要把data转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.message, 'info', function () {
                            //重新加载数据表格
                            customer_datagrid.datagrid("load");

                            //关闭弹出框
                            customer_dialog.dialog("close");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.message, 'info');
                    }
                }
            })
        },

        //提审按钮
        putUp: function () {
            var row = $("#tempoary_datagrid").datagrid("getSelected");
            if (!row) {
                $.messager.alert('提示信息', '大哥,请选择一条需要提审的保单');
                return;
            }

            //修改保单状态为1
            $.get("/policy/changeState.do", {state: 1, id: row.id}, function (data) {
                if (data.success) {
                    $.messager.alert('我的消息', '火速要上级来审核', 'info', function () {
                        $("#tempoary_datagrid").datagrid("load");
                    });
                } else {
                    $.messager.alert('我的消息', '提审失败', 'erro');
                }
            }, "json")

        },
        cancel: function () {
            $("#temp_dialog").dialog("close");
        },
        select_customer: function () {
            sure_customer();
        },
        select_car: function () {
            sure_car();
        }, reload: function () {
            tempoary_datagrid.datagrid("load")
        }, edit: function () {
            //判断用户是否选中数据
            var row = tempoary_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert("温馨提示", "请选中一条数据!", "warning");
                return;
            }
            row["safetymechanism.id"]=row.safetymechanism.id
            row["car.id"] = row.car.id;
            row["customer.id"] = row.customer.id;
            row["customer.name"] = row.customer.name;
            $("#customergender").combobox("setValue", row.customer.gender)
            row["customer.idno"] = row.customer.idno
            row["customer.tel"] = row.customer.tel
            row["customer.address"] = row.customer.address
            row["car.customer.name"] = row.customer.name
            row["car.brand"] = row.car.brand;
            row["car.model"] = row.car.model;
            row["car.plateNumber"] = row.car.plateNumber
            row["car.gasDisplacement"] = row.car.gasDisplacement
            $("#carcategory").combobox("setValue", row.car.category)
            row["car.purchaseDate"] = row.car.purchaseDate
            row["car.valuation"] = row.car.valuation
            row["car.size"] = row.car.size;
            row["safetymechanism.id"] = row.safetymechanism.id;
            //回显到表单中(根据同名匹配的原则来回显)

            $("#applyDate").text(row.applyDate)
            $("#beginDate").text(row.beginDate)
            //回显产品的信息
            var oids = $.map($(".product-tr"), function (item, index) {
                return $(item).attr("data-oid");
            });


            //设置表单隐藏域的保单id值
            $("#policyId").val(row.id);
            temp_form.form("load", row);
            $.each(row.products, function (index, item) {
                if ($.inArray(item.id + "", oids) == -1) {
                    var clone = $("#hidethis").clone(true);
                    console.log(item.currentTotalMoney)
                    clone.show();
                    clone.attr("data-oid", item.id);
                    clone.find("#productName").html(item.name);
                    clone.find("#productSn").html(item.sn);
                    clone.find("#totalMoney").html(item.currentTotalMoney);
                    clone.find("#annuaFlee").html(item.currenAnnufee);
                    clone.insertAfter($("#hidethis"));
                    console.log(clone)
                }
            });
            $("#totalAmount").text(row.totalAmount)
            //回显下拉框
            //设置标题
            $("#temp_dialog").dialog("setTitle", "修改保单");
            //打开弹出框
            $("#temp_dialog").dialog("open");

        }

    };
    // 所有的 a 标签统一绑定点击事件, 从 methodObject 对象中
    $("[data-cmd]").click(function () {
        methodObject[$(this).data("cmd")]();
    });
    $("#accord1").accordion({
        animate: true,
        width: 1500,
        height: 190
    });
    $("#accord2").accordion({
        animate: true,
        width: 1500,
        height: 190
    });
    $("#accord3").accordion({
        animate: true,
        width: 1500,
        height: 190
    });
    $("#accord4").accordion({
        animate: true,
        width: 1500,
        height: 220
    });
    /*  //为accord添加点击事件f
      $(".easyui-accordion .panel-header").click();*/

    $("#pp").combobox({
        onChange: function (NewId, oldId) {
            $('#cc').combobox({
                width: 143,
                panelHeight: 'auto',
                valueField: 'id',
                textField: 'name',
                url: "/location_children?id=" + NewId,
                onLoadSuccess: function (data) {
                    if (data) {
                        $('#cc').combobox('setValue', data[0].id);
                    }
                }
            });
        }
    });
    $("#ppp").combobox({
        onChange: function (NewId, oldId) {
            $('#ccc').combobox({
                width: 143,
                panelHeight: 'auto',
                valueField: 'id',
                textField: 'name',
                url: "/location_children?id=" + NewId,
                onLoadSuccess: function (data) {
                    if (data) {
                        $('#ccc').combobox('setValue', data[0].id);
                    }
                }
            });
        }
    });

})
;

function delete_this(data) {
    $(data).closest("#hidethis").remove();
    var totalAmount = 0;
    $.each($(".annuaFlee"), function (index, item) {
        totalAmount = totalAmount + parseInt(item.innerHTML);
    })
    console.log($("#duration"));
    duration = $("#duration").val();
    $("#totalAmount").html(totalAmount * duration);
}

//算保费
function calculate() {
    var totalAmount = 0;
    $.each($(".annuaFlee"), function (index, item) {
        totalAmount = totalAmount + parseInt(item.innerHTML);
    })
    var duration = $("[name=duration]").val();
    $("#totalAmount").html(totalAmount * duration);
}

// 暂存保单, 提交表单
function temporary_submit() {
    $("#temp_form").form("submit", {
        url: '/policy/saveOrUpdate.do',
        onSubmit: function (param) {
            var oids = $.map($(".product-tr"), function (item, index) {
                return $(item).attr("data-oid");
            });
            var totalMoneys = $.map($(".totalMoney"), function (item, index) {
                return $(item).html();
            })
            var annuaFlees = $.map($(".annuaFlee"), function (item, index) {
                return $(item).html();
            })
            oids.shift();
            totalMoneys.shift();
            annuaFlees.shift();
            //产品的id
            param.pIds = oids;
            //总价
            param.totalAmount = $("#totalAmount").html();
            //设置隐藏域保单的id
            param.totalMoney = totalMoneys;
            param.annuaFlee = annuaFlees;

        },
        success: function (data) {
            if (data != null && data != '' && data != undefined) {
                data = $.parseJSON(data)
                if (!data.success) {
                    $.messager.alert("温馨提示", data.message, "error");
                } else {
                    $.messager.alert("温馨提示", data.message, "info", function () {
                        $("#temp_form").form('clear');
                    });
                }
            }
        }
    });
}

//点击确认客户信息回显到表单中
function sure_customer() {
    var customer = $("#customer_datagrid").datagrid("getSelected");
    console.log(customer.id)
    $("#customerId").val(customer.id)
    console.log($("#customerId").val())

    $("[name='customer.idno']").val(customer.idno);
    $("[name='customer.tel']").val(customer.tel);
    $("#address").textbox("setValue", customer.address);
    $("[name='customer.name']").val(customer.name);
    $("#customergender").combobox("setValue", customer.gender);
    $("#choose_customer_dialog").dialog("close");
}

//点击确认回显车辆信息到表单中
function sure_car() {
    var car = $("#car_datagrid").datagrid("getSelected");
    $("#carCustomer").val(car.customer.id)
    $("#carId").val(car.id)
    $("#carbrand").textbox("setValue", car.brand);
    $("#carmodel").textbox("setValue", car.model);
    $("#cargasDisplacement").textbox("setValue", car.gasDisplacement);
    $("#carcategory").combobox("setValue", car.category);
    $("#carsize").combobox("setValue", car.size);
    $("#carvaluation").textbox("setValue", car.valuation);
    $("#choose_car_dialog").dialog("close");
}

//投保人用户确认
function affirm() {
    var idCard = $("#inIdCard").val();
    $.post('/offInsurance_selectByIdCard', {'idCard': idCard}, function (data) {
        if (!data.success) {
            $.messager.alert("温馨提示", data.msg, "error");
        } else {
            $.messager.alert("温馨提示", data.msg, "info", function () {
            });
        }
    })
}

//被保人用户确认
function offAffirm() {
    var idCard = $("#OffIdCard").val();
    $.post('/offInsurance_selectByIdCard', {'idCard': idCard}, function (data) {
        if (!data.success) {
            $.messager.alert("温馨提示", data.msg, "error");
        } else {
            $.messager.alert("温馨提示", data.msg, "info", function () {
            });
        }
    })
}

//查询保险信息
function openPro() {
    window.location.href = "/insuranceProduct";
}


//查询客户
function query_client() {
    window.location.href = "/customer";
}

//查询被投保人
function query_clientr() {
    window.location.href = "/customer";
}

//搜索按钮
function searchTemporary() {
    $("#tempoary_datagrid").datagrid("load", {
        //投保人
        applicant: $("#applicant").textbox("getText"),
        //保单编号
        policyNo: $("#policyNo").textbox("getText")
    });
}

function lookProductItem() {
    var row = $("#tempoary_datagrid").datagrid("getSelected");
    if (!row) {
        $.messager.alert('警告', '你好像没点准！', 'info');
        return;
    }
    var ids = [];
    $.each(row.products, function (index, data) {
        ids[index] = data.id;
    })
    //var id = window.JSON.stringify(ids)
    console.log(row)
    $("#productItem_datagrid").datagrid("load", {
        id: row.id
    })
    $("#choose_productItem_dialog").dialog("open");
}
function submitForm(index, row, changes) {
    var columns = $("#product_datagrid").datagrid("options").columns;
    var rows = $("#product_datagrid").datagrid("getRows");
    if (row.sn == "001") {
        var annuafee = 285 + changes.totalMoney * 0.0095 * 10000
        $("#product_datagrid").datagrid('updateRow', {
            index: index,
            row: {
                totalMoney: changes.totalMoney,
                annuafee: annuafee
            }
        });
    } else if (row.sn == "004") {
        var annuafee;
        if (row.totalMoney == 10) {
            annuafee = 962 + 144.3
        } else if (row.totalMoney == 20) {
            annuafee = 1191 + 144.3
        } else if (row.totalMoney == 30) {
            annuafee = 1346 + 201.9
        } else if (row.totalMoney == 50) {
            annuafee = 1615 + 242.25
        } else if (row.totalMoney == 100) {
            annuafee = 2103 + 315.44;
        }
        $("#product_datagrid").datagrid('updateRow', {
            index: index,
            row: {
                totalMoney: changes.totalMoney,
                annuafee: annuafee
            }
        });
    } else if (row.sn == "016") {
        var annuafee;
        if ($("carsize").val() > 1) {
            annuafee = changes.totalMoney * 10000 * 0.01
        } else {
            annuafee = changes.totalMoney * 10000 * 0.011
        }
        $("#product_datagrid").datagrid('updateRow', {
            index: index,
            row: {
                totalMoney: changes.totalMoney,
                annuafee: annuafee
            }
        });
    }
    //console.log(row)
    // console.log(changes)
}