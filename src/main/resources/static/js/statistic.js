;$(function () {
    statistic.initDom();
});
let statisticFn = function () {
    return {
        initDom() {
            let options = {
                chart: {
                    type: 'column'
                },
                title: {
                    text: '月度销售情况统计'
                },
                subtitle: {
                    text: '制作: 爱王姑娘的康老师'
                },
                xAxis: {
                    categories: [
                        '一月',
                        '二月',
                        '三月',
                        '四月',
                        '五月',
                        '六月',
                        '七月',
                        '八月',
                        '九月',
                        '十月',
                        '十一月',
                        '十二月'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '销量 (件)'
                    }
                },
                loading: {
                    showDuration: 500
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y} 件</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: []
            };
            let chart = Highcharts.chart('month-sale-chart', options);
            chart.showLoading("数据正在加载中...");
            $.ajax({
                url: '/statistic/findSoldNumberByCategory',
                success: function (data) {
                    for (const datum of data) {
                        chart.addSeries(datum, true, true);
                    }
                    chart.hideLoading()
                }
            });
        }
    }
};
let statistic = statisticFn();