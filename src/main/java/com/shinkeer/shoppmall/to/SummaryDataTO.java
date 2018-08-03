package com.shinkeer.shoppmall.to;

import com.shinkeer.shoppmall.base.to.BaseTO;
import com.shinkeer.shoppmall.common.aspect.ADD;

import javax.validation.constraints.NotBlank;

public class SummaryDataTO extends BaseTO {

    @NotBlank(groups = {ADD.class}, message = "编号")
    private String serialNum;
    @NotBlank(groups = {ADD.class}, message = "货位")
    private String goodAllocation;
    @NotBlank(groups = {ADD.class}, message = "商品编号")
    private String commodityCode;
    @NotBlank(groups = {ADD.class}, message = "预计数量")
    private int predictNum;
    @NotBlank(groups = {ADD.class}, message = "实际数量")
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
