package com.ceiec.webguide.formal.page;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description: 保存分页对象
 **/
public class PagedItemsVO<T> implements Serializable{

    /** 序列号编号 */
    private static final long serialVersionUID = -4235716919149137121L;

    /** 总页数 */
    private Integer pageCount;

    /** 当前页码 */
    private Integer pageIndex;

    /** 每页记录数 */
    private Integer pageSize;

    /** 总记录数 */
    private Integer itemCount;

    /** 是否有上一页 */
    private Integer hasPre;

    /** 是否有下一页 */
    private Integer hasNext;

    /** 当前页面记录集合 */
    private List<T> items;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getHasPre() {
        return hasPre;
    }

    public void setHasPre(Integer hasPre) {
        this.hasPre = hasPre;
    }

    public Integer getHasNext() {
        return hasNext;
    }

    public void setHasNext(Integer hasNext) {
        this.hasNext = hasNext;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
