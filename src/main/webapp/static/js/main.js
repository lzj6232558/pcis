
// //初始化菜单树
$(function () {
    $("#myTree").tree({
        url:"/systemMenu/getMenu.do",
        pill:true,
        //点击左边的菜单就会触发这个函数,增加一个选项卡
        onClick:function(node){
            if(node.url){
                //如果选项卡已经存在,就设置选中的状态
                var tag = $("#myTabs").tabs("exists",node.text);
                if(tag){
                    $("#myTabs").tabs("select",node.text);
                }else{
                    //创建新的选项卡
                    $("#myTabs").tabs("add",{
                        title:node.text,
                        //href:node.url,//只引入body内容,并且可能出现id冲突的问题
                        content:'<iframe src='+node.url+' width=100% height=100% frameborder=0></iframe>',
                        closable:true
                    })
                }
            }else{
                $("#myTree").tree("toggle",node.target);
            }
        }
    })
//初始化选项卡
    $("#myTabs").tabs({
        fit:true
    })
});
