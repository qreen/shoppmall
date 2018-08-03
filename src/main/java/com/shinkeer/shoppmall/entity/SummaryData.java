package com.shinkeer.shoppmall.entity;

import com.shinkeer.shoppmall.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "summarydata")
public class SummaryData  extends BaseEntity {
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '编号'")
    private String serialNum;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '货位'")
    private String goodAllocation;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '商品编号'")
    private String commodityCode;
    @Column(nullable = false, columnDefinition = "TINYINT(11)   COMMENT '预计数量'")
    private int predictNum;
    @Column(nullable = false, columnDefinition = "TINYINT(11)   COMMENT '实际数量'")
    private int actualNum;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getGoodAllocation() {
        return goodAllocation;
    }

    public void setGoodAllocation(String goodAllocation) {
        this.goodAllocation = goodAllocation;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public int getPredictNum() {
        return predictNum;
    }

    public void setPredictNum(int predictNum) {
        this.predictNum = predictNum;
    }

    public int getActualNum() {
        return actualNum;
    }

    public void setActualNum(int actualNum) {
        this.actualNum = actualNum;
    }


}
