package com.farmzop.application.FAQsAdapter;

/**
 * Created by Gaurav on 27-02-2016.
 */
public class FAQsCategoryItem implements FAQsItem {


    private static final int TYPE=0;
    String category;
    int id;

    public FAQsCategoryItem(int id,String category) {
        this.category = category;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public int getId() {
        return id;
    }
}
