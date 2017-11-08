package htof.util.jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 用于页面 jstl时间格式化s
 * Created by miaoch
 */
public class DateTag extends TagSupport {

    private static final long serialVersionUID = 6464168398214506236L;

    private String value;
    private String format = "yyyy-MM-dd HH:mm:ss";

    @Override
    public int doStartTag() throws JspException {
        try {
        	if (value != null && !value.equals("") && !value.equals("0")) {
        		long time = Long.valueOf(value.trim());
        		Calendar c = Calendar.getInstance();
        		c.setTimeInMillis(time);
        		SimpleDateFormat dateformat = new SimpleDateFormat(format);
        		String s = dateformat.format(c.getTime());
        		pageContext.getOut().write(s);
        	} else {
        		pageContext.getOut().write("");
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

	public void setFormat(String format) {
		this.format = format;
	}

}
