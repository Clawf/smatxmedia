package com.smatxmedia.nedi.smatxmedia.subsubcategories;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class Subsubategories {


    public Subsubategories(String id,String sub_id, String name){

        this.setId(id);
        this.setName(name);
        this.setSub_id(sub_id);

    }

    private String sub_id;
    private String id;
    private String name;

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
