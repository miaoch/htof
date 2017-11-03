$(function(){
	//点击nav
	$('.title a').click(function(){
		$('.title a').removeClass('active');
		$(this).addClass('active');
	});
	
	//点击向左隐藏按钮
	var onOff = false;
	$('.asideA').click(function(){
		if(!onOff){
			$('.aside').children().hide();
			$('.aside').css('padding','0px');
			$(this).show().css('background-image','url(images/analysisPic7.png)');
			$('.aside').animate({
				width:'20px'
			},500);
			$('.section2').animate({
				width:'1250px'
			},500);
		}else{
			$('.aside').css('padding','0px 16px');
			$(this).show().css('background-image','url(images/analysisPic6.png)');
			$('.aside').animate({
				width:'500px'
			},50);
			$('.section2').animate({
				width:'770px'
			},50);
			$('.aside').children().show();
		}
		onOff = !onOff;
		return false;
	});
	
	//点击下拉箭头
	$('.timerA3').click(function(){
		$('.aside2Ul1').hide();
		$(this).parent().children(3).show();
		return false;
	});
	
	$('.aside2Ul1 li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.aside2Ul1 li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.aside2Ul1 li').click(function(){
		$('.aside2Ul1').hide();
		$(this).parent().parent().find('.timerA4').html($(this).html());
	});
	
	

	$(document).click(function(){
	$('.aside2Ul1').hide();
	
	});
});