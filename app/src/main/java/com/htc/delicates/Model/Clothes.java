package com.htc.delicates.Model;

public class Clothes {
    public int clothesId;
    public String clothesName;
    public String clothesBrand;
    public String clothesSize;
    public String clothesType;
    public String clothesTag;

    public Clothes(int clothesId, String clothesName, String clothesBrand, String clothesSize, String clothesType, String clothesTag) {
        this.clothesId = clothesId;
        this.clothesName = clothesName;
        this.clothesBrand = clothesBrand;
        this.clothesSize = clothesSize;
        this.clothesType = clothesType;
        this.clothesTag = clothesTag;
    }
}
