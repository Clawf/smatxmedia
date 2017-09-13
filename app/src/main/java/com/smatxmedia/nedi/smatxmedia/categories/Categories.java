package com.smatxmedia.nedi.smatxmedia.categories;

/**
 * Created by Nedi on 13-Sep-17.
 */

public class Categories {


    public Categories(String id, String name){

        this.setId(id);
        this.setName(name);

    }

    private String id;
    private String name;


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
