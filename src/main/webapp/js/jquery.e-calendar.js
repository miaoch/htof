/**
 * @license e-Calendar v0.9.3
 * (c) 2014-2016 - Jhonis de Souza
 * License: GNU
 */
Array.prototype.contains = function ( needle ) {
  for (i in this) {
    if (this[i] == needle) return true;
  }
  return false;
}
var adDay = new Date().getDate();
var adMonth = new Date().getMonth();
var adYear = new Date().getFullYear();
var dDay = adDay;
var dMonth = adMonth;
var dYear = adYear;
var clickDate;
var textData = dYear + "-" + (dMonth+1) + "-" + dDay;
var defaultdata = {morning: [{"nameList":""}], afternoon:[{"nameList":""}]};
(function ($) {
    var eCalendar = function (options, object) {
        var instance = object;
        var settings = $.extend({}, $.fn.eCalendar.defaults, options);
        var mouseOver = function () {
            $(this).addClass('c-nav-btn-over');
        };
        var mouseLeave = function () {
            $(this).removeClass('c-nav-btn-over');
        };
        var clickItem = function () {
        	clickDate = $(this);
            var d = $(this).attr('data-event-day');
            textData = dYear + "-" + (dMonth+1) + "-" + ($(this).text()==""?dDay:$(this).text());
            $(this).nextAll().removeClass('c-event-over');
            $(this).prevAll().removeClass('c-event-over');
            if($(this).hasClass('c-event-over')){
                $(this).removeClass('c-event-over');
                $('div.c-event-item[data-event-day="' + d + '"]').removeClass('c-event-over');
            }else{
                $(this).addClass('c-event-over');
                $('div.c-event-item[data-event-day="' + d + '"]').addClass('c-event-over');
            }
            $.ajax({
                url:"calendar/getInfo",
                data:{"dateStr":textData},
                success: function (result) {
            		settings.events = JSON.parse(result);
                    printEvent();
                }
            });
        }
        
        var dblClickItem = function () {
        	var textData = dYear + "-" + (dMonth+1) + "-" + ($(this).text()==""?dDay:$(this).text());
        	$("#detail").modal({
        	    remote: "systemset?date=" + textData
        	});
        }

        var nextMonth = function () {
            if (dMonth < 11) {
                dMonth++;
            } else {
                dMonth = 0;
                dYear++;
            }
            print();
        };
        var previousMonth = function () {
            if (dMonth > 0) {
                dMonth--;
            } else {
                dMonth = 11;
                dYear--;
            }
            print();
        };

        function printEvent(){
            $(".c-event-body").remove();
            $(".c-event-grid").remove();
            var cEvents = $('<div/>').addClass('c-event-grid');
            var cEventsBody = $('<div/>').addClass('c-event-body');
            var eventList = $('<div/>').addClass('c-event-list');
            cEvents.append($('<div/>').addClass('c-event-title c-pad-top').html(settings.eventTitle));
            cEvents.append(cEventsBody);
            //设置右边内容 eventList
            var item = $('<div/>').addClass('c-event-item');
            for(var j = 0;j< settings.events.morning.length;j++){
                var description = $('<div/>').addClass('description').html(settings.events.morning[j].nameList + '<br/>');
                item.append(description);
            }
            var itemnoon = $("<div/>").addClass('c-event-itemnoon');
            for(var k = 0;k< settings.events.afternoon.length;k++){
                var description = $('<div/>').addClass('description').html(settings.events.afternoon[k].nameList + '<br/>');
                itemnoon.append(description);
            }
            var $morning_add = $("<div style=\"float: right\"><a href=\"javascript:void(0)\" onclick=\"add(1, textData)\">设置</a></div>");
            var $afternoon_add = $("<div style=\"float: right\"><a href=\"javascript:void(0)\" onclick=\"add(2, textData)\">设置</a></div>");
            
            var morning = $("<div/>").addClass("morning").html(settings.events.morningtime);
            morning.append($morning_add);
            var afternoon = $("<div/>").addClass("afternoon").html(settings.events.afternoontime);
            afternoon.append($afternoon_add);
            eventList.append(morning);
            eventList.append(afternoon);
            eventList.append(item);
            eventList.append(itemnoon);
            //设置结束
            cEventsBody.append(eventList);
            $(instance).append(cEvents);
            return cEvents;
        }

        function print() {
            var dWeekDayOfMonthStart = new Date(dYear, dMonth, 1).getDay() - settings.firstDayOfWeek;
            if (dWeekDayOfMonthStart < 0) {
                dWeekDayOfMonthStart = 6 - ((dWeekDayOfMonthStart + 1) * -1);
            }
            var dLastDayOfMonth = new Date(dYear, dMonth + 1, 0).getDate();
            var dLastDayOfPreviousMonth = new Date(dYear, dMonth + 1, 0).getDate() - dWeekDayOfMonthStart + 1;
            var cBody = $('<div/>').addClass('c-grid');
            var cNext = $('<div/>').addClass('c-next c-grid-title c-pad-top');
            var cMonth = $('<div/>').addClass('c-month c-grid-title c-pad-top');
            var cPrevious = $('<div/>').addClass('c-previous c-grid-title c-pad-top');
            cPrevious.html(settings.textArrows.previous);
            cMonth.html(settings.months[dMonth] + ' ' + dYear);
            cNext.html(settings.textArrows.next);

            cPrevious.on('mouseover', mouseOver).on('mouseleave', mouseLeave).on('click', previousMonth);
            cNext.on('mouseover', mouseOver).on('mouseleave', mouseLeave).on('click', nextMonth);

            cBody.append(cPrevious);
            cBody.append(cMonth);
            cBody.append(cNext);
            var dayOfWeek = settings.firstDayOfWeek;
            for (var i = 0; i < 7; i++) {
                if (dayOfWeek > 6) {
                    dayOfWeek = 0;
                }
                var cWeekDay = $('<div/>').addClass('c-week-day c-pad-top');
                cWeekDay.html(settings.weekDays[dayOfWeek]);
                cBody.append(cWeekDay);
                dayOfWeek++;
            }
            var day = 1;
            var dayOfNextMonth = 1;
            var restDays = getRestDays();
            for (var i = 0; i < 42; i++) {
                var cDay = $('<div/>');
                if (i < dWeekDayOfMonthStart) {
                    cDay.addClass('c-day-previous-month c-pad-top');
                    cDay.html(dLastDayOfPreviousMonth++);
                } else if (day <= dLastDayOfMonth) {
            		cDay.addClass(restDays.contains(day)?'c-day c-pad-top c-red':'c-day c-pad-top');
                    cDay.on('click', clickItem);
                    cDay.on('dblclick', dblClickItem);
                    if (day == dDay && adMonth == dMonth && adYear == dYear) {
                        cDay.addClass('c-today');
                        cDay.click();
                    }
                    cDay.html(day++);
                } else {
                    cDay.addClass('c-day-next-month c-pad-top');
                    cDay.html(dayOfNextMonth++);
                }
                cBody.append(cDay);
            }
           printEvent();
           $(instance).addClass('calendar');
           $(instance).html(cBody);
        }

        function getRestDays() {
        	var days = new Array();
    		$.ajax({
                url:"calendar/getRestDays",
                async:false,
                data:{"year":dYear, "month":dMonth + 1},
                success: function (result) {
                	console.log(result);
                	days = result.split(",");
                }
            });
        	return days;
        }
        
        return print();
    }
    

    $.fn.eCalendar = function (oInit) {
        return this.each(function () {
            return eCalendar(oInit, $(this));
        });
    };

    $.fn.eCalendar.defaults = {
        weekDays: ['日', '一', '二', '三', '四', '五', '六'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        textArrows: {previous: '<', next: '>'},
        eventTitle: '排班人员列表',
        events: defaultdata,
        data: {"dateStr": dYear+"-"+(dMonth+1)+"-"+dDay},
        url: "calendar/getInfo",
        firstDayOfWeek: 0
    };

}(jQuery));