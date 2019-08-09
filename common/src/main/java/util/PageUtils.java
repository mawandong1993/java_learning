package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对list，手动进行分页的工具
 *
 * @author mWX527225
 * @date 2018/5/10 15:10
 */
public class PageUtils<E> {

    //数据
    private List<E> list=new ArrayList<>();
    //分页大小
    private int pageSize = 10;
    //当前页
    private int pageNo = 1;
    //总数
    private int totalNum;
    //起始页
    private int startPage = 1;
    //终止页
    private int endPage;
    //起始条数
    private int startNum;
    //终止条数
    private int endNum;
    //上一页
    private int prePage;
    //下一页
    private int nextPage;

    public PageUtils(List<E> list) {
        this.initList(list);
        this.init(this.pageSize, this.pageNo);
    }

    public PageUtils(List<E> list, int pageSize, int pageNo) {
        this.initList(list);
        this.init(pageSize, pageNo);
    }

    //初始化数据
    private void initList(List<E> list){
        this.list.clear();
        if(list == null || list.isEmpty()){
            return;
        }
        this.list.addAll(list);
    }

    //初始化各项参数
    private void init(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalNum = this.list.size();
        this.startNum = initStartNum();
        this.endNum = initEndNum();
        this.endPage = initEndPage();
        this.prePage = (this.pageNo > 1 ? this.pageNo - 1 : -1);
        this.nextPage = (this.pageNo + 1 > endPage ? -1 : this.pageNo + 1);
    }


    //初始化起始值,最小值是0,代表第一条
    private int initStartNum() {
        int startNum = (this.pageNo - 1) * this.pageSize;;
        if (startNum < 0) {
            startNum = 0;
        }
        if (startNum > this.totalNum) {
            startNum = this.totalNum;
        }
        this.startNum = startNum;
        return startNum;
    }

    //初始化终止值,最大值是totalNum,代表最后一条
    private int initEndNum() {
        int endNum = this.startNum + this.pageSize;
        if (endNum < 0) {
            endNum = 0;
        }
        if (endNum > this.totalNum) {
            endNum = this.totalNum;
        }
        return endNum;
    }

    //初始化终止页
    private int initEndPage() {
        int endPage = 0;
        if (this.pageSize > 0 && this.totalNum > 0) {
            endPage = (this.totalNum - 1) / this.pageSize + 1;
        }
        return endPage;
    }

    /**
     * 获取分页结果
     */
    public List<E> getResults(int pageSize, int pageNo) {
        this.init(pageSize, pageNo);
        return this.getResults();
    }

    /**
     * 获取分页结果
     */
    public List<E> getResults() {
        //返回的结果
        List<E> returnList = new ArrayList<>();
        if (this.pageSize <= 0 || this.pageNo <= 0) {
            return returnList;
        }
        returnList.addAll(list.subList(this.startNum, this.endNum));
        return returnList;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
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
}
