package com.shinkeer.shoppmall.to;

import com.shinkeer.shoppmall.base.entity.BaseEntity;
import com.shinkeer.shoppmall.common.aspect.ADD;
import com.shinkeer.shoppmall.common.aspect.EDIT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

public class MerchandiseTO{

    @NotBlank(groups = {EDIT.class}, message = "id不能为空")
    private String id;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "商品名称不能为空")
    private String detailName;
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "图片不能为空")
    private String pic;
    private String detailPic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(String detailPic) {
        this.detailPic = detailPic;
    }
}
