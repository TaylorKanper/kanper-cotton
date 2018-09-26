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
        }
    }
};
let backend = backendFn();