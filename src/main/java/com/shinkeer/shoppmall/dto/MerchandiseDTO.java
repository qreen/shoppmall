/**
 * 用户查询数据传输
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.shinkeer.shoppmall.dto;

import com.shinkeer.shoppmall.base.dto.BaseDTO;

public class MerchandiseDTO extends BaseDTO {
    /**
     * 产品名称
     *
     */
    private String detailName;
    /**
     * 排序列名
     */
    private String sort;
    /**
     * 排序命令（desc asc）
     */
    private String sortOrder;


    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
