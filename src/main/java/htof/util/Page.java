package htof.util;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by miaoch on 2017/11/7.
 */
public class Page {
    private int curPage;
    private int pageSize;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private long totalRecord;
    private String requestURI;
    private String pageString;

    public Page(int curPage, int pageSize, int totalRecord, HttpServletRequest request) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        totalPage = totalRecord / pageSize + 1;
        this.prePage = curPage > 1 ?  curPage - 1 : 1;
        this.nextPage = curPage < totalPage ?  curPage + 1 : totalPage;
        this.totalRecord = totalRecord;
        setRequestURI(request);
        setPageString();
    }

    public Page(PageList pageList, HttpServletRequest request) {
        Paginator paginator = pageList.getPaginator();
        curPage = paginator.getPage();
        pageSize = paginator.getLimit();
        prePage = paginator.getPrePage();
        nextPage = paginator.getNextPage();
        totalPage = paginator.getTotalPages();
        totalRecord = paginator.getTotalCount();
        setRequestURI(request);
        setPageString();
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(HttpServletRequest request) {
        requestURI = request.getRequestURI() + "?";
        Map params = request.getParameterMap();
        String paramsStr = "";
        for (Object key : params.keySet()) {
            //只截取非当前页的请求字段
            if (!"curPage".equals(key)) {
                Object[] values = (Object[]) params.get(key);
                for (Object value : values) {
                    paramsStr += (key + "=" + value +"&");
                }
            }
        }
        if (paramsStr.length() > 0) requestURI += paramsStr;
    }

    public String getPageString() {
        return pageString;
    }

    //展示当前页的前后五页
    public void setPageString() {
        StringBuilder sb = new StringBuilder();
        int left = curPage - 2, right = curPage + 2;
        while (left < 1) {
            left++;
            right++;
        }
        while (right > totalPage) {
            if (left > 1) left--;
            right--;
        }
        sb.append(String.format("<li><a href='%scurPage=%d'>首页</a></li>",
                    requestURI, 1));
        sb.append(String.format("<li><a href='%scurPage=%d' aria-label='Previous'><span aria-hidden='true'>上一页</span></a></li>",
                    requestURI, prePage));
        for (int i = left; i < curPage; i++) {
            sb.append(String.format("<li><a href='%scurPage=%d'>%d</a></li>",
                    requestURI, i, i));
        }
        sb.append(String.format("<li class='active'><a href='%scurPage=%d'>%d</a></li>",
                requestURI, curPage, curPage));
        for (int i = curPage + 1; i <= right; i++) {
            sb.append(String.format("<li><a href='%scurPage=%d'>%d</a></li>",
                    requestURI, i, i));
        }
        sb.append(String.format("<li><a href='%scurPage=%d' aria-label='Next'><span aria-hidden='true'>下一页</span></a></li>",
                requestURI, nextPage));
        sb.append(String.format("<li><a href='%scurPage=%d'>末页</a></li>",
                requestURI, totalPage));
        pageString = sb.toString();
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
