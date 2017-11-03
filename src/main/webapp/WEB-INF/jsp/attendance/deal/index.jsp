<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
    <title>日历</title>
    <link rel="stylesheet" href="${PATH}/css/h-calendar.css"/>
    <script src="${PATH}/js/jquery-1.11.0.min.js"></script>
    <script src="${PATH}/js/h-calendar.js"></script>
</head>
<body onload="initial();">
<form name="CLD" class="content">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="datetable">
    <thead>
    <tr>
		<td colSpan=7><span>公历</span>
		<select name="SY" onchange="changeCld();" style="font-SIZE: 9pt">
		<%	
			for (int i=1900; i<2050; i++) {
				out.write("<option>" + i);
        	}
		%>
		</select><span>年</span>
		<select name="SM" onchange="changeCld();" style="font-SIZE: 9pt">
		<%	
			for (int j=1; j<13; j++) {
        		out.write("<option>" + j);
        	}
		%>
        </select><span>月</span>
        </font><span id="GZ"></span>
      </td>
    </tr>
    </thead>
    <tbody>
    <tr style="background:#eee;">
      <td width="54">日</td>
      <td width="54">一</td>
      <td width="54">二</td>
      <td width="54">三</td>
      <td width="54">四</td>
      <td width="54">五</td>
      <td width="54">六</td>
    </tr>            
    <%	
    	int gNum;
    	for (int k=0; k<6; k++) {
			out.write("<tr align=\"center\">");
			for (int l=0;l<7;l++) {
				gNum = k * 7 + l;
				out.write("<td id=\"GD" + gNum +"\"><div style=\"text-align:right\"><font id=\"RQ" + gNum +"\" size=3 face=\"Arial Black\"");
				if (l == 0 || l == 6) {
					out.write("color=\"red\"");
				}
		        out.write("></font></div><br/><font id=\"SW" + gNum + "\" size=2 ></font>");
		        out.write("<br/><font id=\"XW" + gNum + "\" size=2 ></td>");
			}
			out.write("</tr>");
		}
	%>
   </tbody>
</table>
</form>
</body>
</html>
