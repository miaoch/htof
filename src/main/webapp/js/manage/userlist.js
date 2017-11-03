var id;
$(function(){
	$('.table tbody tr').click(function(){
		id=$(this).children(1).val();
		$('.table tbody tr').removeClass('active1');
		$(this).addClass('active1');
	});
});
