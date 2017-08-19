package org.ros.android.android_tutorial_pubsub;

/**
  Created by user on 2017-07-02.
 */

public class TableModel {
    private String tableNo;
    private String menu;
    private String size;
    private String count;

    public TableModel(String tableNo, String menu, String size, String count){
        this.tableNo = tableNo;
        this.menu = menu;
        this.size = size;
        this.count = count;
    }
    public String getTableNo(){
        return tableNo;
    }
    public String getMenu(){
        return menu;
    }
    public String getSize(){
        return size;
    }
    public  String getCount(){
        return count;
    }
}
