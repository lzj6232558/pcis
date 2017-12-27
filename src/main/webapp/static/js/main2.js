// //初始化菜单树
$(function () {

    $("#customer").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=11",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });

    $("#message").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=18",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


    $("#policy").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=22",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });

    $("#fee").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=30",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


    $("#claim").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=35",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


    $("#employee").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=15",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


    $("#data").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=39",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });

    $("#system").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=1",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


    $("#enquiry").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=46",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });
    $("#team").tree({
        url: "/systemMenu/getChildrenMenu.do?parent_id=36",
        pill: true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick: function (node) {
            if (node.url) {
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists", node.text);
                if (tag) {
                    $("#myTabs").tabs("select", node.text);
                } else {
                    //创建新的选项卡
                    $("#myTabs").tabs("add", {
                        title: node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content: '<iframe src=' + node.url + ' width=100% height=100% frameborder=0></iframe>',
                        closable: true
                    })
                }
            } else {
                $("#myTree").tree("toggle", node.target);
            }
        }
    });


//初始化选项卡
    $("#myTabs").tabs({
        fit: true,
        border: false
    })
});


function signIn() {
    $.get('/attendance/signIn.do', function (data) {
        if (data.success) {
            $.messager.alert('温馨提示', '签到成功', 'info', function () {
                //attendance_datagrid.datagrid('reload');
            });
        } else {
            $.messager.alert('温馨提示', data.message, 'error');
            //attendance_datagrid.datagrid('reload');
        }
    });
}
function signOut() {
    $.messager.confirm('签退确认', '是否要签退?', function (r) {
        if (r) {
            $.get('/attendance/signOut.do', function (data) {
                if (data.success) {
                    $.messager.alert('温馨提示', '签退成功！', 'info', function () {
                       // attendance_datagrid.datagrid('reload');
                    });
                } else {
                    $.messager.alert('温馨提示', data.message, 'error');
                   // attendance_datagrid.datagrid('reload');
                }
            });
        }
    });
}
