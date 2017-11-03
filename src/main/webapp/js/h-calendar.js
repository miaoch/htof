var solarMonth = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Animals = new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");

//返回公历y年m+1月的天数
function solarDays(y,m){
	if ( m == 1 ) {
		return(((y%4==0)&&(y%100!=0)||(y%400==0))?29:28);
	} else {
	    return(solarMonth[m]);
	}
}

function calendar(y, m) {
    var sDObj = new Date(y, m, 1);	//当月第一天的日期
    this.length = solarDays(y,m);    //公历当月天数
    this.firstWeek = sDObj.getDay();    //公历当月1日星期几
}
//在表格中显示公历和农历的日期,以及相关节日
function drawCld(SY, SM) {
	var i, sD, sObj, SW, XW;
	var cld = new calendar(SY, SM);
    GZ.innerHTML = '【' + Animals[(SY - 4) % 12] + '】';	//生肖
	$.ajax({
        url:"getStatistics",
        data:{"year":SY, "month":SM},
        success: function(result){ 
        	var json = JSON.parse(result);  
        	for(i = 0; i < 42; i++) {
                sObj = eval('RQ'+ i);
                SW = eval('SW'+ i);
                XW = eval('XW'+ i);
                sD = i - cld.firstWeek;
                if (sD > -1 && sD < cld.length) { //日期内
                    sObj.innerHTML = sD + 1;
            		SW.innerHTML = json[sD + 1]?(json[sD + 1].sw?"上午:" + json[sD + 1].sw:""):"";
            		XW.innerHTML = json[sD + 1]?(json[sD + 1].xw?"下午:" + json[sD + 1].xw:""):"";
                } else {
                	sObj.innerHTML = "";
                    SW.innerHTML = "";
                    XW.innerHTML = "";
                }
        	}
        },
    });
}

//打开页时,在下拉列表中显示当前年月,并调用自定义函数drawCld(),显示公历和农历的相关信息
function initial() {
	var Today = new Date();
	var tY = Today.getFullYear();
	var tM = Today.getMonth();
	CLD.SY.selectedIndex=tY-1900;
	CLD.SM.selectedIndex=tM;
	drawCld(tY, tM);
}
//在下拉列表中选择年月时,调用自定义函数drawCld(),显示公历和农历的相关信息
function changeCld() {
	var y, m;
	y = CLD.SY.selectedIndex + 1900;
	m = CLD.SM.selectedIndex;
	drawCld(y, m);
}