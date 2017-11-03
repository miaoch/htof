$(function(){
	var content = document.getElementsByClassName('content')[0];
	var aLi = content.getElementsByTagName('li');
	var arr = [];
	var zIndex = 1;
	//存储每个li的left,top,width,height.
	for (var i=0; i<aLi.length; i++) {
		arr.push( {left: aLi[i].offsetLeft, top: aLi[i].offsetTop, width: aLi[i].offsetWidth,height:aLi[i].offsetHeight} );
	}
	
	for (var i=0; i<aLi.length; i++) {
		aLi[i].index = i;
		
		//鼠标滑入效果
		aLi[i].onmouseover = function() {
			this.style.zIndex = zIndex++;
			startMove( this, {
				width : arr[this.index].width *1.5,
				height : arr[this.index].height *1.5,
				left : arr[this.index].left - Math.round(arr[this.index].width*0.5/2),
				top : arr[this.index].top - Math.round(arr[this.index].height*0.5/2)
			});
			
		};
		//鼠标移开效果
		aLi[i].onmouseout = function() {
			startMove( this, {
				width : arr[this.index].width,
				height : arr[this.index].height,
				left : arr[this.index].left,
				top : arr[this.index].top
			});
		}
		
	};
	
	//点击"登录"
	$('.mask a').click(function(){
			$('.mask').hide();
		
	});
	
});