package com.shinkeer.shoppmall.entity;

import com.shinkeer.shoppmall.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

    @Entity
    @Table(name = "scancommdata")
    public class ScanCommData extends BaseEntity {

        @Column(nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '编号'")
        private String serialNum;
        @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '商品条码'")
        private String commodityCode;
        @Column(nullable = false, columnDefinition = "TINYINT(11)   COMMENT '商品数量'")
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

        public ScanCommData() {
        }
    }
