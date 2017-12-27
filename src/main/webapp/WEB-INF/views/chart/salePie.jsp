<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 2017/12/8
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售报表饼状图</title>

    <script type="text/javascript" src="/static/plugins/echarts/dist/echarts.js"></script>
</head>
<body>
<div id="pie" style="height:500px;width:700px ;"></div>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: '/static/plugins/echarts/dist'
        }
    });
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/pie', // 使用饼状图就加载pei模块，按需加载
            'echarts/chart/funnel' // 漏斗
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('pie'));

            var option ={
                title : {
                    text: '销售总额',
                    subtext: '${groupType}',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: ${max}
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                series : [
                    {
                        name:'销售总额',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '50%'],
                        itemStyle : {
                            normal : {
                                label : {
                                    show : true
                                },
                                labelLine : {
                                    show : true,
                                    length:40
                                }
                            },
                            emphasis : {
                                label : {
                                    show : true,
                                    position : 'center',
                                    textStyle : {
                                        fontSize : '30',
                                        fontWeight : 'bold'
                                    }
                                }
                            }
                        },
                        data:${groupList}
                    }
                ]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );
</script>
</body>
</html>
