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
    var editIndex = undefined
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
                field: 'safetyMechanism', title: '保险机构', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'safeguardYear', title: '保障年限', width: 100},
            {field: 'totalMoney', title: '保额', width: 100},
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
        ]]
    });
    //初始化机构,产品信息的数据表格
    $('#safe_product_dataGrid').datagrid({
        url: '/safe/list.do',
        view: detailview,
        checkbox: true,
        fitColumns: true,
        striped: true,
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
        detailFormatter: function (index, row) {
            return '<div style="padding:2px" ><table class="ddv"></table></div>';
        },
        onExpandRow: function (index, row) {
            ddv = $(this).datagrid('getRowDetail', index).find('table.ddv');
            ddv.datagrid({
                url: '/product/selectProductByItemId2.do?id=' + row.id,
                checkbox: true,
                fitColumns: true,
                rownumbers: true,
                loadMsg: '',
                height: 'auto',
                columns: [[
                    {field: 'name', title: '保险名称', width: 200},
                    {
                        field: 'totalMoney',
                        title: '保额/车价(万)',
                        width: 100,
                        align: 'right',
                        editor: 'text',
                        type: 'numberbox'
                    },
                    /* {
                         field: 'undeduction',
                         title: '不计免赔',
                         width: 100,
                         align: 'right',
                         formatter: function (value, row, index) {
                             return value ? "<font color='blue'>是</font>" : "<font color='red'>否</font>"
                         }
                     },*/
                    {field: 'annuafee', title: '基本保费', width: 100, align: 'right'},
                ]],
                onResize: function () {
                    $('#safe_product_dataGrid').datagrid('fixDetailRowHeight', index);
                },
                onLoadSuccess: function () {
                    setTimeout(function () {
                        $('#safe_product_dataGrid').datagrid('fixDetailRowHeight', index);
                    }, 0);
                }, onClickCell: function (index, field, value) {
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
            $('#safe_product_dataGrid').datagrid('fixDetailRowHeight', index);
        }
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

            $("#safetyMechanismId").val($("#safe_select").combogrid("getValue"))
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
        cancel: function () {
            customer_dialog.dialog("close");
            customer_datagrid.dialog("refresh")
        },
        select_customer: function () {
            sure_customer();
        },
        select_car: function () {
            sure_car();
        },

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

});

var ddv;

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
    $("#form").form("submit", {
        url: '/policy/saveOrUpdate.do',
        onSubmit: function (param) {
            var oids = $.map($(".product-tr"), function (item, index) {
                return $(item).attr("data-oid");
            });
            var province = $("#province").combobox("getText");
            var city = $("#city").combobox("getText");
            var street = $("#street").textbox("getText");
            $("#province").combobox("setText", province + city + street);

            oids.shift();
            //产品的id
            param.pIds = oids;
            //总价
            param.totalAmount = $("#totalAmount").html();
        },
        success: function (data) {
            if (data != null && data != '' && data != undefined) {
                data = $.parseJSON(data)
                if (!data.success) {
                    $.messager.alert("温馨提示", data.message, "error");
                } else {
                    $.messager.alert("温馨提示", data.message, "info", function () {
                        $("#form").form('clear');
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
    $("#cusId").val(car.id)
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

//计算保费
function calculate2() {
    //拿到是车型
    //拿到选择产品的明细
    var totalAmount = 0;
    if ($(".ddv").datagrid("getSelections")!=undefined) {
        $.each($(".ddv").datagrid("getSelections"), function (index, data) {
            totalAmount += data.annuafee;
        })
    }
    if (totalAmount != 0) {
        $("#totalAmount").text(totalAmount)
        $.messager.alert('提醒', '计算成功！');
    } else {
        $.messager.alert('提醒', '请选择一款产品！');
    }


}

//修改编辑框,修改数据
function submitForm(index, row, changes) {
    //alert( row.totalMoney+"--"+changes.totalMoney);  //daliyResultRate
    var resultId = row.totalMoney;  // 当前行中修改的数据值
    var annuafee = row.annuafee;
    var valuation = $("#carvaluation").val()
    console.log(row)
    if (valuation == '') {
        $.messager.alert('提醒', '请输入车的价格！');
        return;
    }
    if (resultId == "" || annuafee == '') {
        $.messager.alert('提醒', '请录入数据！');
        $(this).datagrid('load');
        return;
    }
    var r = /^\+?[1-9][0-9]*$/;//判断输入的是正整数
    if (!r.test(resultId)) {
        $.messager.alert('提醒', '请输入正整数！');
        return;
    }
    $.ajax({
        type: "post",
        async: false,
        url: "/product/updateTotalMoney2.do",
        data: {
            "totalMoney": resultId, //将数据ajax到后台
            "id": row.id,
            "name": row.name,
            "size": $("#carsize").val(),
            "valuation": valuation,
            enquiryId: -2
        },
        success: function (data) {
            if (data.success) {
                //alert("保存成功");
                $("#safe_product_dataGrid").datagrid('load');
            }
        }
    })
}

//生成报价单,保存到数据库中
function generateBill() {
    var safetyMechanismId;
    console.log(ddv.datagrid("getSelections"))//
    $.each(ddv.datagrid("getSelections"), function (inex, data) {
        console.log(data)
        safetyMechanismId = data.safetyMechanism.id;
    })
    $("#safetyMechanismId").val(safetyMechanismId)
    var productIds = []
    var totalMoney=[];
    var annuafee=[];
    $.each(ddv.datagrid("getSelections"), function (index, data) {
        productIds[index] = data.id
        totalMoney[index]=data.totalMoney
        annuafee[index]=data.annuafee
    })
    JSON.stringify(productIds);
    $.ajaxSetup({
        contentType: "application/x-www-form-urlencoded; charset=utf-8"
    });
    $("#totalAmount2").val($("#totalAmount").text());
    $.post("/enquiry/saveOrUpdate.do?" + $("#form").serialize(), {productIds: productIds,totalMoney:totalMoney,annuafee:annuafee}, function (data) {
        if (data.success) {
            $.messager.alert('提醒', data.message, "info");
            $("#p").panel("refresh")

        } else {
            $.messager.alert('提醒', "请选择产品", "info");
        }
    }, "json")
}
