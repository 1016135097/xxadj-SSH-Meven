package cn.bubbletg.xxadj.entity;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/16 15:16
 * @description：提供页面显示相关操作，如分页查询
 * @modified By：
 * @version: 1.0.0
 */
public class Page<T> {

    /**
     * create by: BubbleTg
     * description: 当前页
     * create time: 2019/6/16 15:18
     */
    private Integer CurrentPage;

    /**
     * create by: BubbleTg
     * description: 总记录数
     * create time: 2019/6/16 15:18
     */
    private Integer totalCount;

    /**
     * create by: BubbleTg
     * description:  每页显示记录数
     * create time: 2019/6/16 15:18
     */
    private Integer pageSize;

    /**
     * create by: BubbleTg
     * description:  总页数
     * create time: 2019/6/16 15:18
     */
    private Integer totalPage;

    /**
     * create by: BubbleTg
     * description: 开始位置
     * create time: 2019/6/16 15:18
     */
    private Integer begin;

    /**
     * create by: BubbleTg
     * description: 每页记录的list集合
     * create time: 2019/6/16 15:18
     */
    private List<T> list;

    public Integer getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        CurrentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
