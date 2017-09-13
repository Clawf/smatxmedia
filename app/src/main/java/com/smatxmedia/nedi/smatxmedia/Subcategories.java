package com.smatxmedia.nedi.smatxmedia;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class Subcategories {

    private String subcategory_id;
    private String category_id;
    private String subcategory_name;
    private String cover;
    private String subsubcategories;


    public Subcategories(String subcategory_id,String category_id,String subcategory_name,String cover, String subsubcategories){

        setSubcategory_id(subcategory_id);
        setCategory_id(category_id);
        setSubcategory_name(subcategory_name);
        setCover(cover);
        setSubsubcategories(subsubcategories);


    }




    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSubsubcategories() {
        return subsubcategories;
    }

    public void setSubsubcategories(String subsubcategories) {
        this.subsubcategories = subsubcategories;
    }






}
