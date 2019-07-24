package com.cmall.commons.pojo;

import com.cmall.pojo.TbItem;

public class TbItemChild extends TbItem{
    private String[] images;
    
    /*
     * 库存是否足
     */
    private Boolean enough;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public Boolean getEnough() {
        return enough;
    }

    public void setEnough(Boolean enough) {
        this.enough = enough;
    }
    
}
