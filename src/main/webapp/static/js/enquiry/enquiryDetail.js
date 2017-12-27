jQuery.ajaxSettings.traditional = true;

$(function () {
    var enquiryDetail = $("#enquiryDetail");
    var editIndex = undefined
    //关闭窗口刷新页面
    $("#p").dialog({
        onClose: function () {
            window.location.reload();
        }
    })

    $("#submit_search").click(function () {
        var number = $("#number").textbox("getText");
        //var plateNumber = $("#plateNumber").textbox("getText");
        var beginDate = $("#beginDate").datebox("getText");
        var endDate = $("#endDate").datebox("getText");
        enquiryDetail.datagrid("load", {
            number: number,
            beginDate: beginDate,
            endDate: endDate
        })
        //emp_datagrid.datagrid({queryParams: $("#emp_table").serializeObject()});   //点击搜索
    });


    //统一方法绑定
    var methodObj = {
        reload: function () {
            enquiryDetail.datagrid("reload")
        }, edit: function () {

        }, remove: function () {
            alert("22")
        }

    }
    //统一绑定事件
    $("[data-cmd]").click(function () {
        methodObj[$(this).data("cmd")]();
    });

    $("#p").dialog({
        title: '询价单明细',
        width: 1200,
        height: 600,
        closed: true,
    })
    enquiryDetail.datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        url: "/enquiry/query.do",
        pagination: true,
        rownumbers: true,
        toolbar: "#toolbar",
        ctrlSelect: true,
        columns: [[
            {
                field: 'offerNumber', title: '报价单号', width: 100
            },
            {
                field: 'safetymechanism', title: '机构名称', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'product', title: '保险名称', width: 100, formatter: function (value) {
                var i = "";
                $.each(value, function (index, data) {
                    if (data.name != undefined) {
                        i += data.name + ",";
                    }
                })
                return i.substr(0, i.length - 1);
            }
            },
            {
                field: 'car', title: '车牌号码', width: 100,
                formatter: function (value, row, index) {
                    return value ? value.plateNumber : '';
                }
            },
            {field: 'registerDate', title: '询价日期', width: 100},
            {field: 'totalAmount', title: '询价总价', width: 100},
            {
                field: 'state', title: '是否转投保单', width: 100, formatter: function (value, row, index) {
                return value ? "是" : "否";
            }

            },

            {
                field: 'detail',
                title: '查看询价单明细',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return "<a ondblclick='lookProductItem(this)' href='javascript:;' id='detail'>双击看明细</a>";
                }
            }
        ]]

    })
    //初始化机构,产品信息的数据表格
    $('#safe_product_dataGrid').datagrid({
        url: '/safe/list.do',
        view: detailview,
        checkbox: true,
        fitColumns: true,
        buttons: "#buttons",
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
                url: '/product/selectProductByItemId.do?id=' + row.id + "&enquiryId=" + enquiry.id,
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
                    {field: 'annuafee', title: '基本保费', width: 100, align: 'right'},
                ]],
                onResize: function () {
                    $('#safe_product_dataGrid').datagrid('fixDetailRowHeight', index);
                },
                onLoadSuccess: function (data) {
                    $.each(data.rows, function (index, data) {
                        if ($.inArray(data.id, arr1) != -1) {
                            //console.log("----",data.id)
                            ddv.datagrid("selectRow", index)
                            $.ajax({
                                url: "/product/selectTotalMoney.do",
                                data: {enquiryId: enquiry.id},
                                success: function (data2) {
                                    var columns = ddv.datagrid("options").columns
                                    var rows = ddv.datagrid("getRows");
                                    $.each(data2, function (index2, data) {
                                        // console.log(data.currentAnnuefee)
                                        ddv.datagrid('updateRow', {
                                            index: index2,
                                            row: {
                                                totalMoney: data.currentTotalmoney,
                                                annuafee: data.currentAnnuefee
                                            }
                                        });


                                    })
                                    //rows[index][columns[0][2].field] //=data.currentTotalmoney
                                    //onsole.log(rows[index])
                                }
                            })
                        }
                    })

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
            //$("#ddv").datagrid("getRowDetail",index)
            //console.log($("#safetyMechanismId").val())
            /*if ($("#safetyMechanismId").val()==row.id){
                console.log($("[filed]"))
                // $('#safe_product_dataGrid').datagrid("")
            }*/
            //console.log(ddv)
            //console.log(arr1);


        }
    });
})
var arr1 = [];
var enquiry;
var ddv;

function lookProductItem() {
    //回显数据到表格中
    enquiry = $("#enquiryDetail").datagrid("getSelected");
    $("#customerId").val(enquiry.customer.id)
    $("#carId").val(enquiry.car.id)
    $("#customerId").val(enquiry.customer.id)
    $("#safetyMechanismId").val(enquiry.safetymechanism.id)
    $("#name").textbox("setText", enquiry.customer.name)
    $("#customergender").combobox("setValue", enquiry.customer.gender);
    $("#idno").textbox("setText", enquiry.customer.idno)
    $("#tel").textbox("setText", enquiry.customer.tel)
    $("#origin").textbox("setText", enquiry.origin)
    $("#employeeId").combobox("setValue", enquiry.employee.id);
    $("#customerName").textbox("setText", enquiry.customer.name)
    $("#carbrand").textbox("setText", enquiry.car.brand)
    $("#carmodel").textbox("setText", enquiry.car.model)
    $("#carplateNumber").textbox("setText", enquiry.car.plateNumber)
    $("#cargasDisplacement").textbox("setText", enquiry.car.gasDisplacement)
    $("#carcategory").combobox("setValue", enquiry.car.category)
    $("#carpurchaseDate").datebox("setValue", enquiry.car.purchaseDate)
    $("#carvaluation").textbox("setText", enquiry.car.valuation)
    $("#carsize").combobox("setValue", enquiry.car.size)
    $("#VIN").textbox("setText", enquiry.car.VIN)
    $("#engineNumber").textbox("setText", enquiry.car.engineNumber)
    $("#totalAmount").text(enquiry.totalAmount);
    $.each(enquiry.product, function (index, data) {
        arr1[index] = data.id
    })
    $.each($("#safe_product_dataGrid").datagrid("getData").rows, function (index, data) {
        if (enquiry.safetymechanism.id == data.id) {
            $("#safe_product_dataGrid").datagrid("selectRow", index)

        }
    })
    $("#p").dialog("open");
}

//修改编辑框,修改数据
function submitForm(index, row, changes) {
    //alert( row.totalMoney+"--"+changes.totalMoney);  //daliyResultRate
    var resultId = row.totalMoney;  // 当前行中修改的数据值
    var annuafee = row.annuafee;
    var valuation = $("#carvaluation").val()
    //console.log(row)
    //console.log(changes)
    /*if (valuation == '') {
        $.messager.alert('提醒', '请输入车的价格！');
        return;
    }*/

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
        url: "/product/updateTotalMoney.do",
        data: {
            "totalMoney": resultId, //将数据ajax到后台
            "id": row.id,
            "name": row.name,
            "size": $("#carsize").val(),
            enquiryId: enquiry.id

        }, /* $.ajax({
                 url: "/product/selectTotalMoney.do",
                 data: {enquiryId: enquiry.id},
                 success:function (data2) {
                     var columns=ddv.datagrid("options").columns
                     var rows=ddv.datagrid("getRows");
                     rows[index][columns[0][2].field]=data2.currentTotalmoney//=data.currentTotalmoney
                     console.log(rows[index][columns[0][2].field])
                 }
             })*/

        success: function (data) {
            //给当前的表格设置值
            if (data.success) {
                //alert("保存成功");
                $("#safe_product_dataGrid").datagrid('load');
                //ddv.datagrid("refreshRow", index)
            }
        }
    })
}

//计算保费
function calculate2() {
    $("#idno").textbox("setText", enquiry.customer.idno)
    $("#tel").textbox("setText", enquiry.customer.tel)
    $("#carbrand").textbox("setText", enquiry.car.brand)
    $("#carmodel").textbox("setText", enquiry.car.model)
    $("#carplateNumber").textbox("setText", enquiry.car.plateNumber)
    $("#cargasDisplacement").textbox("setText", enquiry.car.gasDisplacement)
    //拿到是车型
    //拿到选择产品的明细
    var totalAmount = 0;
    console.log($(".ddv").datagrid("getSelections"))
    if ($(".ddv").datagrid("getSelections") != undefined && $(".ddv").datagrid("getSelections") != NaN) {
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

//更新询价单
function updateEnquiry() {
    $("#enquiryId").val(enquiry.id)
    var safetyMechanismId;
    $.each(ddv.datagrid("getSelections"), function (inex, data) {
        safetyMechanismId = data.safetyMechanism.id;
    })
    $("#safetyMechanismId").val(safetyMechanismId)
    var productIds = []
    var totalMoney = [];
    var annuafee = [];
    $.each(ddv.datagrid("getSelections"), function (index, data) {
        productIds[index] = data.id
        totalMoney[index] = data.totalMoney
        annuafee[index] = data.annuafee
    })
    // console.log($("#form").serialize())
    JSON.stringify(productIds);
    // ;
    var totalAmount2 = 0;
    $.each(annuafee, function (index, data) {
        totalAmount2 += data;
    })
    $("#totalAmount2").val(totalAmount2)
    //console.log(ddv.datagrid("getSelections"))
    $.post("/enquiry/saveOrUpdate.do?" + $("#form").serialize(), {
        productIds: productIds,
        totalMoney: totalMoney,
        annuafee: annuafee
    }, function (data) {
        if (data.success) {
            $.messager.alert('提醒', data.message, "info");

        } else {
            $.messager.alert('提醒', data.message, "erro");
        }
    }, "json")
}

//转存保单
function saveBill() {
    $.post("/enquiry/saveBill.do", {id: enquiry.id}, function (data) {
        if (data.success) {
            $.messager.alert('提醒', data.message, "info");
            window.location.reload();
        } else {
            $.messager.alert('提醒', data.message, "erro");
            $("#safe_product_dataGrid").datagrid('load');
        }
    }, "json")
}