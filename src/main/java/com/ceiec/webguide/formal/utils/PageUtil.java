package com.ceiec.webguide.formal.utils;

import com.ceiec.webguide.formal.page.PagedItemsVO;

import java.util.List;

/**
 * CreateDate: 2018/4/24 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class PageUtil {

    /** 第一页的页码 */
    private static final int FIRST_PAGE_INDEX = 1;

    /**
     * 生成分页所需信息
     *
     * @param  totalCount 所有item的数量
     *         pageIndex 当前页码
     *         size 每页显示条数
     * @return JSONObject 包含分页信息的json对象
     */
    public static PagedItemsVO createPageMsg(int totalCount, int pageIndex, int size, List items){

        PagedItemsVO pagedItemsVO = new PagedItemsVO();
        int pageCount = (int) Math.ceil((double) totalCount / size);
        pagedItemsVO.setPageCount(pageCount);
        pagedItemsVO.setPageIndex(pageIndex);
        pagedItemsVO.setPageSize(size);
        pagedItemsVO.setItemCount(totalCount);
        pagedItemsVO.setHasPre(pageIndex == FIRST_PAGE_INDEX ? 0 : 1);
        pagedItemsVO.setHasNext(pageIndex >= pageCount ? 0 : 1);
        pagedItemsVO.setItems(items);
        return pagedItemsVO;
    }
}
