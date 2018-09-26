;
$(function () {
    backend.initEvent();
});
let backendFn = function () {
    return {
        initDom: function () {

        },
        initEvent: function () {
            $('#addSort')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        firstCategoryName: {
                            validators: {
                                notEmpty: {}
                            }
                        }
                    }
                })
                .on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    // Get the form instance
                    let $form = $(e.target);
                    $.ajax({
                        url: '/firstCategory/add',
                        data: $form.serialize(),
                        method: 'post',
                        success: function (data) {
                            $('#add-category-frame').modal('hide');
                            $('#addSort')[0].reset();
                            toastr.success('添加分类' + data.data.firstCategoryName + '成功');
                        },
                        error: function () {
                            toastr.error('添加失败');
                        }
                    });
                });
            $('#add-goods-frame').on('show.bs.modal', function () {
                $.ajax({
                    url: '/firstCategory/getAllCategory',
                    success: function (data) {
                        let options='';
                        for (const datum of data) {
                            options += '<option value=\'' + datum.id + '\'>' + datum.firstCategoryName + '</option>';
                        }
                        $('#category-select').html(options).selectpicker('refresh');
                    }
                })
            });
            $('#all-category-frame').on('show.bs.modal', function () {
                $('#all-category-table').bootstrapTable({
                    classes: "table table-hover table-striped table-bordered templatemo-user-table",
                    columns: [{
                        field: 'id',
                        title: '分类ID'
                    }, {
                        field: 'firstCategoryName',
                        title: '分类名称'
                    }],
                    url: '/firstCategory/getAllCategory'
                });
            });
        }
    }
};
let backend = backendFn();