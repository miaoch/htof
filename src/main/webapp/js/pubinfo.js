var id;
$(function(){
	$('.tableList2 li').click(function(){
		if($(this).attr("class") == "citytitle")
			return;
		$('.tableList2 li').removeClass('active');
		$(this).addClass('active');
		id=$(this).children(1).val();
	});
});
