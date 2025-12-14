// 自定义应用JavaScript代码
$(document).ready(function() {
    // 可以在这里添加自定义的功能
    
    // 示例：添加一个全局的确认删除函数
    window.confirmDelete = function(url, title) {
        if (!title) title = "确定要删除这条记录吗？";
        alertMsg.confirm(title, {
            okCall: function() {
                DWZ.ajaxTodo(url, navTabAjaxDone);
            }
        });
    };
    
    // 示例：格式化日期显示
    window.formatDate = function(dateStr) {
        if (!dateStr) return '';
        var date = new Date(dateStr);
        var year = date.getFullYear();
        var month = (date.getMonth() + 1).toString().padStart(2, '0');
        var day = date.getDate().toString().padStart(2, '0');
        return year + '-' + month + '-' + day;
    };
});