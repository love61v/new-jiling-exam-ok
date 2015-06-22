/**
 * 解决IE下文本框placeholder属性无效问题
 */
$(function(){
	function placeholderSupport() {
	     return 'placeholder' in document.createElement('input');
	 }
	
	 if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
	     $('[placeholder]').focus(function() {
	         var input = $(this);
	         if (input.val() == input.attr('placeholder')) {
	             input.val('');
	             input.removeClass('placeholder');
	         }
	     }).blur(function() {
	         var input = $(this);
	         if (input.val() == '' || input.val() == input.attr('placeholder')) {
	             input.addClass('placeholder');
	             input.val(input.attr('placeholder'));
	         }
	     }).blur();
	 };
});

/**
 * 禁止密码复制
 */
$(function(){
    $("input:password").bind("copy cut paste",function(e){
       return false;
    });
});

	 