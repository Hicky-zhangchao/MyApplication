package com.feicuiedu.androidhousekeeper;

/**
 * Created by 张超 on 2016/11/3.
 */

 class Person {

    private int per_ic;

    private String per_name;

    private String per_number;

    private int per_lic;

    public Person(){}

    public Person(int per_ic,String per_name,String per_number,int per_lic){
        this.per_ic=per_ic;
        this.per_name=per_name;
        this.per_number=per_number;
        this.per_lic=per_lic;
    }

    public void setPer_ic(int per_ic) {
        this.per_ic = per_ic;
    }

    public void setPer_lic(int per_lic) {
        this.per_lic = per_lic;
    }

    public void setPer_name(String per_name) {
        this.per_name = per_name;
    }

    public void setPer_number(String per_number) {
        this.per_number = per_number;
    }

    public int getPer_ic() {
        return per_ic;
    }

    public int getPer_lic() {
        return per_lic;
    }

    public String getPer_name() {
        return per_name;
    }

    public String getPer_number() {
        return per_number;
    }

}
