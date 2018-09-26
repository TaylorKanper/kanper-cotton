;
$(function () {
    backend.initEvent();
});
let backendFn = function () {
    return {
        initDom: function () {

        },
        initEvent: function () {
            $('#submitSortForm').on('click', function () {
                let s = utils.serializeObject($('#addSort'));
                $.ajax({
                    url: '/firstCategory/add',
                    data: s,
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
            })
        }
    }
};
let backend = backendFn();