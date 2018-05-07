package com.shinkeer.shoppmall.bo;

public class MerchandiseBO {
    private String id;
    private String detailName;
    private String pic;
    private String[] detailPic;
    private String[] detailPics;

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

    public String[] getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(String[] detailPic) {
        this.detailPic = detailPic;
    }
}
