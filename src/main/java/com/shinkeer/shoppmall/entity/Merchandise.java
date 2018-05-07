package com.shinkeer.shoppmall.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "merchandise")
public class Merchandise {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36, updatable = false, insertable = false)
    private String id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '商品名称'")
    private String detailName;
    @Column(nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '商品图片'")
    private String pic;
    @Column(nullable = true, columnDefinition = "VARCHAR(500)   COMMENT '详情图片'")
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
