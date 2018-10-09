;$(function () {
    product.initDom();
    product.initEvent();
});
let productFn = function () {
    return {
        initDom: function () {
            $.ajax({
                url: '/firstCategory/getAllCategory',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.firstCategoryName + '</option>';
                        }
                        $('#first-category').html(options).selectpicker('refresh').selectpicker('val', data[0].id);
                    }
                }
            });
            $("#goods-table").bootstrapTable({
                url: '/goods/getAllGoodsBySecondCategoryId',
                classes: "table table-hover table-striped table-bordered templatemo-user-table",
                columns: [{
                    field: 'id',
                    title: '商品ID'
                }, {
                    field: 'secondCategory.firstCategory.firstCategoryName',
                    title: '商品分类',
                    sortable: true
                }, {
                    field: 'secondCategory.secondCategoryName',
                    title: '商品名称',
                    sortable: true
                }, {
                    field: 'supplier.supplierName',
                    title: '供应商名称',
                    sortable: true
                }, {
                    field: 'soldPrice',
                    title: '出售价格',
                    sortable: true
                }, {
                    field: 'number',
                    title: '库存',
                    sortable: true
                }, {
                    field: 'function',
                    title: '操作',
                    width: '140',
                    sortable: false,
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-primary' data-id='" + row.id + "'>购买</button> <button type='button' class='btn btn-xs btn-danger' data-id='" + row.id + "'>加入购物车</button>";
                    }
                }],
                search: true,
                showRefresh: true,
                showFullscreen: true,
                showToggle: true,
                showExport: true,
                showPaginationSwitch: true,
                showColumns: true,
                pagination: true,
                cache: false,
                onDblClickRow: function (row) {
                    $('#batch-buy-label').html("批量购买" + row.secondCategory.secondCategoryName);
                    $('#batch-buy-id').val(row.id);
                    $('#batch-buy-frame').modal('show');
                },
                onLoadSuccess: function () {
                    $("#goods-table button.btn-primary").on('click', function () {
                        let goodsId = $(this).attr('data-id');
                        $.ajax({
                            url: '/soldGoods/buyItem',
                            data: {goodsId: goodsId},
                            method: 'post',
                            success: function (data) {
                                if (data.code == 0) {
                                    $("#goods-table").bootstrapTable('refresh');
                                    toastr.success(data.msg);
                                }
                            },
                            error: function (e) {
                                toastr.error(e.responseJSON.msg);
                            }
                        });
                    });
                    $("#goods-table button.btn-danger").on('click', function () {
                        let goodsId = $(this).attr('data-id');
                        $.ajax({
                            url: '/soldGoods/addToShopCar',
                            data: {goodsId: goodsId},
                            success: function (data) {
                                if (data.code == 0) {
                                    $("#goods-table").bootstrapTable('refresh');
                                    toastr.success(data.msg);
                                }
                            },
                            error: function (e) {
                                toastr.error(e.responseJSON.msg);
                            }
                        });
                    })
                }
            });
        },
        initEvent: function () {
            $('#first-category').on("changed.bs.select", function () {
                let id = $(this).selectpicker('val');
                $.ajax({
                    url: '/secondCategory/getAllGoodsByFirstId',
                    data: {id: id},
                    success: function (data) {
                        let btns = '';
                        for (const datum of data) {
                            btns += "<button class='btn btn-success' type='button' data-id='" + datum.id + "'>" + datum.secondCategoryName + "</button>";
                        }
                        $('#secondCategory-list').html(btns);
                        $('#secondCategory-list button').on('click', function () {
                            let secondCategoryId = $(this).attr('data-id');
                            $("#goods-table").bootstrapTable('refresh', {query: {secondCategoryId: secondCategoryId}});
                        })
                    }
                })
            });
            $('#batch-buy-form').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    batchNumber: {
                        validators: {
                            notEmpty: {}
                        }
                    }
                }
            }).on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let $form = $(e.target);
                $.ajax({
                    url: '/soldGoods/batchBuy',
                    data: $form.serialize(),
                    method: 'post',
                    success: function (data) {
                        $('#batch-buy-frame').modal('hide');
                        $("#goods-table").bootstrapTable('refresh');
                        $('#batch-buy-form').bootstrapValidator('resetForm', true);
                        toastr.success(data.msg);
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
        }
    }
};
let product = productFn();