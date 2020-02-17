// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    title: {
        text: '天津银行理财经理销售量'
    },
    tooltip: {},
    legend: {
        data: ["理财经理销量"]
    },
    xAxis: {
        data: ["张三","李四","王五","李柳","田七","周八"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 366, 100, 10, 20]
    }]
};




$.ajax({
    url:'/v1',
    type: "post",
    async: false,
    datatype:"json",
    data: {"str": "ffffffffff"},
    beforeSend:function(){},
    success: function(data) {
        if(data.code == 0 ){

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }else{
            alert(data.msg);
            return false;
        }

    },
    error : function() {
        layer.alert('服务器内部错误，请联系管理员');
        return false;
    }
});