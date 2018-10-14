;$(function () {
    product.initDom();
    product.initEvent();
});
let productFn = function () {
    return {
        refreshTotal: function (number) {
            $('#total-price').html(number);
        },
        calculateTotal: function () {
            let total = 0;
            $('#shopping-car-info tr').each(function () {
                let soldPrice = $(this).find('input[name=soldPrice]').val();
                let buyNumber = $(this).find('input[name=buyNumber]').val();
                let discount = $(this).find('input[name=discount]').val();
                total += soldPrice * buyNumber * discount;
            });

            return total.toFixed(1);
        },
        initDom: function () {
            $.ajax({
                url: '/member/getAllMember',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.memberName + ' ' + datum.phone + '</option>';
                        }
                        $('#choose-member').html(options).selectpicker('refresh').selectpicker('val', '');
                    }
                }
            });
            $.ajax({
                url: '/firstCategory/getAllCategory',
                success: function (data) {
                    let options = '';
                    if (data && data.length != 0) {
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.firstCategoryName + '</option>';
                        }
                        $('#first-category').html(options).selectpicker('refresh').selectpicker('val', data[0].id).on('changed.bs.select', function () {
                            let firstCategoryId = $(this).selectpicker('val');
                            $("#goods-table").bootstrapTable('refresh', {query: {firstCategoryId: firstCategoryId}});
                        });
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
                    field: 'buyDate',
                    title: '进货时间',
                    sortable: true
                }, {
                    field: 'function',
                    title: '直接购买',
                    width: '70',
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-primary' data-id='" + row.id + "'>购买</button>";
                    }
                }, {
                    field: 'shoppingCar',
                    title: '加入购物车',
                    width: '70',
                    formatter: function (value, row) {
                        return "<button type='button' class='btn btn-xs btn-danger' data-id='" + row.id + "'>加入购物车</button>";

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
                onClickRow: function (row, $el, field) {
                    if (field != 'shoppingCar') {
                        return;
                    }
                    let $trs = $('#shopping-car-info tr');
                    let s = true;
                    $.each($trs, function (i, n) {
                        let $tt = $(n);
                        if ($tt.find('td').eq(0).find('input').val() == row.id) {
                            toastr.info("已经添加了商品id为" + row.id + "的商品");
                            s = false;
                        }
                    });
                    if (s) {
                        let addToTotal = row.soldPrice;
                        let origin = parseInt($('#total-price').html());
                        product.refreshTotal(origin + addToTotal);
                        let tr = "<tr>\n" +
                            "<td class=\"col-xs-1\"><input title=\"商品ID\" name=\"goodsId\" type=\"hidden\" class=\"form-control input-sm\" disabled value='" + row.id + "'>" + row.id + "</td>\n" +
                            "<td class=\"col-xs-1\"><input title=\"商品分类\" name=\"firstCategoryId\" type=\"hidden\" class=\"form-control input-sm\" disabled value='" + row.secondCategory.firstCategory.id + "'>" + row.secondCategory.firstCategory.firstCategoryName + "</td>\n" +
                            "<td class=\"col-xs-1\"><input title=\"商品名称\" name=\"secondCategoryId\" type=\"hidden\" class=\"form-control input-sm\" disabled value='" + row.secondCategory.id + "'>" + row.secondCategory.secondCategoryName + "</td>\n" +
                            "<td class=\"col-xs-1\"><input title=\"商品售价\" name=\"soldPrice\" type=\"number\" class=\"form-control input-sm\" value='" + row.soldPrice + "'></td>\n" +
                            "<td class=\"col-xs-1\"><input title=\"购买数量\" name=\"buyNumber\" type=\"number\" class=\"form-control input-sm\" value='1' max='" + row.number + "' min='1'></td>\n" +
                            "<td class=\"col-xs-1\">" + row.number + "</td>\n" +
                            "<td class=\"col-xs-1\"><input title=\"商品折扣\" name=\"discount\" type=\"number\" class=\"form-control input-sm\" step=\"0.01\" max=\"1\" min=\"0\" value='1.00'></td>\n" +
                            "<td class=\"col-xs-1\"><button type=\"button\" class=\"btn btn-danger\">删除</button></td>\n" +
                            "</tr>";
                        $('#shopping-car-info').append(tr)
                            .on('click', 'button', function () {
                                $(this).closest('tr').remove();
                                product.refreshTotal(product.calculateTotal());
                            })
                            .on('change', 'input[name=soldPrice]', function () {
                                product.refreshTotal(product.calculateTotal());
                            })
                            .on('change', 'input[name=buyNumber]', function () {
                                product.refreshTotal(product.calculateTotal());
                            })
                            .on('change', 'input[name=discount]', function () {
                                product.refreshTotal(product.calculateTotal());
                            });
                        toastr.success("成功添加了商品id为" + row.id + "的商品");
                    }

                },
                onDblClickRow: function (row) {
                    $('#batch-buy-label').html("批量购买" + row.secondCategory.secondCategoryName);
                    $('#batch-buy-id').val(row.id);
                    $('#batch-buy-frame').modal('show');
                }
            });
        },
        initEvent: function () {
            $("#goods-table").on('click', 'button.btn-primary', function () {
                let goodsId = $(this).attr('data-id');
                $.ajax({
                    url: '/soldGoods/buyItem',
                    data: {goodsId: goodsId},
                    method: 'post',
                    success: function (data) {
                        if (data.code == 0) {
                            $("#goods-table").bootstrapTable('refresh');
                            toastr.success(data.msg);
                        } else {
                            toastr.error(data.msg);
                        }
                    },
                    error: function (e) {
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
            $('#shopping-cat-form').bootstrapValidator().on('success.form.bv', function (e) {
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                let form = utils.serializeTable($('#shopping-car-info'));
                let memberId = $('#choose-member').val();
                let postObj = {
                    goodsItemList: form,
                    memberId: memberId
                };
                $.ajax({
                    url: '/soldGoods/shoppingCarBuy',
                    data: JSON.stringify(postObj),
                    dataType: "json",
                    contentType: 'application/json',
                    method: 'post',
                    success: function (data) {
                        $('#shopping-car').modal('hide');
                        $('#shopping-cat-form')[0].reset();
                        if (data.code == 0) {
                            toastr.success(data.msg);
                            $('#shopping-car-info').empty();
                            $('#goods-table').bootstrapTable('refresh');
                            $('#choose-member').selectpicker('val', '');
                        } else {
                            toastr.error(data.msg);
                        }
                        $('#shopping-cat-form').bootstrapValidator('resetForm', true);
                    },
                    error: function (e) {
                        $('#shopping-cat-form').bootstrapValidator('resetForm', true);
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
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
                        $('#batch-buy-form').bootstrapValidator('resetForm', true);
                        toastr.error(e.responseJSON.msg);
                    }
                });
            });
        }
    }
};
let product = productFn();