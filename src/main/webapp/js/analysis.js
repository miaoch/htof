$(function(){
	//初始化查询时间及地点
	$('.timerA1').html('2016 - 03');
	$('.timerA2').html('2016 - 04');
	$('.timerA4').html('全省');
	$('.sectionP span').html($('.timerA1').html());
	$('.sectionP em').html($('.timerA2').html());
	$('.sectionP strong').html($('.timerA4').html());
	
	//点击查询时间(左边)
	$('.timerA1').click(function(ev){
		$('.timerA1Ul').show();
		$('.timerA1U2').hide();
		$('.timerA1U3').hide();
		return false;
	});
	
	$('.timerA1Ul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.timerA1Ul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.timerA1Ul li').click(function(){
		$('.timerA1Ul').hide();
		$('.timerA1').html($(this).html());
	});
	//点击查询时间(右边)
	$('.timerA2').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').show();
		$('.timerA1U3').hide();
		return false;
	});
	
	$('.timerA1U2 li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.timerA1U2 li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.timerA1U2 li').click(function(){
		$('.timerA1U2').hide();
		$('.timerA2').html($(this).html());
	});
	//点击查询地点
	$('.timerA3').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.timerA1U3').show();
		return false;
	});
	//事部件排名查询
	$('.eventtypechoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.eventtypeul').show();
		return false;
	});
	//问题来源区域查询
	$('.srcareachoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.srcareaul').show();
		return false;
	});
	//问题来源区域查询
	$('.srcareaquchoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.srcareaquul').show();
		return false;
	});
	$('.srccodechoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.srccodeul').show();
		return false;
	});
	//问题类型区域查询
	$('.typecodechoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.typecodeul').show();
		return false;
	});
	$('.typeareachoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.typeareaul').show();
		return false;
	});
	$('.maintypechoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.maintypeul').show();
		return false;
	});
	$('.partcodechoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.partcodeul').show();
		return false;
	});
	$('.partareachoose').click(function(ev){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.partareaul').show();
		return false;
	});
	
	$('.timerA1U3 li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.timerA1U3 li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.timerA1U3 li').click(function(){
		$('.timerA1U3').hide();
		$('.timerA4').html($(this).html());
	});
	
	$('.eventtypeul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.eventtypeul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.eventtypeul li').click(function(){
		$('.eventtypeul').hide();
		$('.eventtype').html($(this).html());
	});
	
	$('.srcareaul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.srcareaul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.srcareaul li').click(function(){
		$('.srcareaul').hide();
		$('.srcarea').html($(this).html());
	});
	
	$('.srcareaquul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.srcareaquul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.srcareaquul li').click(function(){
		$('.srcareaquul').hide();
		$('.srcareaqu').html($(this).val());
	});

	$('.srccodeul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.srccodeul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.srccodeul li').click(function(){
		$('.srccodeul').hide();
		$('.srccode').html($(this).html());
	});
	
	$('.typecodeul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.typecodeul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.typecodeul li').click(function(){
		$('.typecodeul').hide();
		$('.typecode').html($(this).html());
	});
	
	$('.typeareaul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.typeareaul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.typeareaul li').click(function(){
		$('.typeareaul').hide();
		$('.typearea').html($(this).html());
	});
	
	//部件统计 部件大类
	$('.partcodeul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.partcodeul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.partcodeul li').click(function(){
		$('.partcodeul').hide();
		$('.partcode').html($(this).html());
	});
	//部件统计 地市选择
	$('.partareaul li').mouseover(function(){
		$(this).css("background",'#f2f4f5');
	});
	$('.partareaul li').mouseout(function(){
		$(this).css("background",'#fff');
	});
	$('.partareaul li').click(function(){
		$('.partareaul').hide();
		$('.partarea').html($(this).html());
	});
	
	//点击查询按钮
	$('.btn').click(function(){
		$('.sectionP span').html($('.timerA1').html());
		$('.sectionP em').html($('.timerA2').html());
		$('.sectionP strong').html($('.timerA4').html());
		
	});
	
	//点击nav
	$('.title a').click(function(){
		$('.title a').removeClass('active');
		$(this).addClass('active');
	});
	
	//点击表格行
	/*
	$('.tableList2 li').click(function(){
		$('.tableList2 li').removeClass('active');
		$(this).addClass('active');
		$('.sectionSpan').html($(this).children(1).html());
	});
	*/
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
			$('.section').animate({
				width:'1250px'
			},500);
		}else{
			$('.aside').css('padding','0px 16px');
			$(this).show().css('background-image','url(images/analysisPic6.png)');
			$('.aside').animate({
				width:'290px'
			},50);
			$('.section').animate({
				width:'980px'
			},50);
			$('.aside').children().show();
		}
		onOff = !onOff;
		return false;
	});
	
	
	$(document).click(function(){
		$('.timerA1Ul').hide();
		$('.timerA1U2').hide();
		$('.timerA1U3').hide();
	});
	
	//市区联动
	/*
	$(".srcareaul li").click(function(){
		var citycode = getCityCode(this.innerText);
		$.ajax({
		   type: "POST",
		   url: "getChildrenArea",
		   data: "citycode="+citycode,
		   dataType: "json",
		   success: function(data){
			   if(data){
				   $(".srcareaquul").empty();
				   $(".srcareaquul").append("<li value='all'>全部</li>");
				   for(var x in data){
					   $(".srcareaquul").append("<li value="+x+">"+data[x]+"</li>");
				   }
				   $(".srcareaquul li").on({
					   click: function() {
						   $('.srcareaquul').hide();
						   $('.srcareaqu').html($(this).val());
					   },
					   mouseover: function() {
						   $(this).css("background",'#f2f4f5');
					   },
					   mouseout: function() {
						   $(this).css("background",'#fff');
					   }
				   }); 
			   }
		   }
		});
	});
	*/
	
	//事部件大类联动
	$(".typecodeul li").click(function(){
		var typecode = getTypeCode(this.innerText);
		$.ajax({
		   type: "POST",
		   url: "getChildrenMainType",
		   data: "eventtype="+typecode,
		   dataType: "json",
		   success: function(data){
			   if(data){
				   $(".maintypeul").empty();
				   $(".maintypeul").append("<li value='all'>全部</li>");
				   for(var x in data){
					   $(".maintypeul").append("<li value="+x+">"+data[x]+"</li>");
				   }
				   $(".maintypeul li").on({
					   click: function() {
						   $('.maintypeul').hide();
						   $('.maintype').html($(this).html());
					   },
					   mouseover: function() {
						   $(this).css("background",'#f2f4f5');
					   },
					   mouseout: function() {
						   $(this).css("background",'#fff');
					   }
				   }); 
			   }
		   }
		});
	});
});

function getCityCode(cityname) {
	switch (cityname) {
	case '杭州市':
		return '3301';
	case '宁波市':
		return '3302';
	case '温州市':
		return '3303';
	case '绍兴市':
		return '3306';
	case '嘉兴市':
		return '3304';
	case '湖州市':
		return '3305';
	case '衢州市':
		return '3308';
	case '舟山市':
		return '3309';
	case '金华市':
		return '3307';
	case '台州市':
		return '3310';
	case '丽水市':
		return '3311';
	default:
		return 'all';
	}
}

function getSrcCode(srccode) {
	switch (srccode) {
	case '公众举报':
		return '01';
	case '采集员上报':
		return '02';
	case '视频抓拍':
		return '03';
	case '微信上报':
		return '04';
	case 'APP上报':
		return '05';
	case '网上投诉':
		return '06';
	case '其他':
		return '07';
	default:
		return 'all';
	}
}

function getTypeCode(srccode) {
	switch (srccode) {
	case '事件':
		return '01';
	case '部件':
		return '02';
	case '其他':
		return '03';
	default:
		return 'all';
	}
}

function getPartCode(partname) {
	switch (partname) {
	case '公用设施':
		return '08';
	case '道路交通':
		return '09';
	case '市容环境':
		return '10';
	case '园林绿化':
		return '11';
	case '房屋土地':
		return '12';
	case '其他设施':
		return '13';
	case '扩展部件':
		return '14';
	case '其他部件':
		return '15';
	default:
		return 'all';
	}
}
