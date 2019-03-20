package com.htc.delicates.Model;

import com.htc.delicates.Util.PinYin4jUtil;

public class Brand implements Comparable{
    public int brandId;
    public int brandImage;
    public boolean isWish;
    public String brandName;

    public Brand(int brandId, int brandImage, String brandName) {
        this.brandId = brandId;
        this.brandImage = brandImage;
        this.brandName = brandName;
        firstAlphabet = PinYin4jUtil.getFirstAlphabet(brandName);
    }
    public void setSelected(boolean isWish){
        this.isWish = isWish;
    }

    private String firstAlphabet;


    public String getFirstAlphabet() {
        return firstAlphabet;
    }

    @Override
    public int compareTo(Object another) {
        Brand compareContact = (Brand) another;
        if (compareContact.getFirstAlphabet().equals("#")){
            return 1;
        }else if (getFirstAlphabet().equals("#")){
            return -1;
        }else {
            return getFirstAlphabet().compareToIgnoreCase(((Brand) another).getFirstAlphabet());
        }
    }
}
