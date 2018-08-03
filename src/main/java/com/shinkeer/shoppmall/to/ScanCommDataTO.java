package com.shinkeer.shoppmall.to;

import com.shinkeer.shoppmall.common.aspect.ADD;

import javax.validation.constraints.NotBlank;

public class ScanCommDataTO {
    @NotBlank(groups = {ADD.class}, message = "编号不能为空")
    private String serialNum;
    @NotBlank(groups = {ADD.class}, message = "商品条码不能为空")
    private String commodityCode;
    @NotBlank(groups = {ADD.class}, message = "商品数量不能为空")
    private int commodityNum;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum = commodityNum;
    }

    public ScanCommDataTO() {
    }
}
