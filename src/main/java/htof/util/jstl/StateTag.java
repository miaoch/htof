package htof.util.jstl;

import htof.util.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * 用于页面 jstl 转换状态
 * Created by miaoch
 */
public class StateTag extends TagSupport {

    private String state;
    private String mapName;
    private String defaultValue;
    private String redKeys;
    
    public void setState(String state) {
    	this.state = state;
    }
    public void setMapName(String mapName) {
    	this.mapName = mapName;
    }
    public void setDefaultValue(String defaultValue) {
    	this.defaultValue = defaultValue;
    }
    public void setRedKeys(String redKeys) {
    	this.redKeys = redKeys;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
        	String value = (defaultValue != null && !"".equals(defaultValue))
        			? defaultValue:"";
        	if (!"".equals(state)) {
        		Class c = Constants.class;
        		Field field = c.getDeclaredField(mapName);
        		Map map = (Map) field.get(null);
        		if (map.get(state) != null) {
        			value = map.get(state).toString();
        		}
        		if (redKeys != null && !redKeys.equals("")) {
        			String[] redKeyArray = redKeys.split(",");
        			if (Arrays.binarySearch(redKeyArray, state) >= 0) {
        				value = "<div style=\"color:#F00\">" + value + "</div>";
        			}
            	}
        	} else {
        		if (redKeys != null) {
    				value = "<div style=\"color:#F00\">" + value + "</div>";
            	}
        	}
        	pageContext.getOut().write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

}
