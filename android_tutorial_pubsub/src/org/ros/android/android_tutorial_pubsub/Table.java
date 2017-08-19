package org.ros.android.android_tutorial_pubsub;

/**
 * Created by user on 2017-07-28.
 *
 */

public class Table {
    private String menu;
    private String count;
    private String table_num;

    public Table(){

    }
    public String getMenu(){
        return menu;
    }
    public String getCount(){
        return count;
    }
    public String getTable_num(){
        return table_num;
    }
    public void setMenu(String menu){
        this.menu = menu;
    }
    public void setCount(String count){
        this.count = count;
    }
    public void setTable_num(String table_num){
        this.table_num = table_num;
    }

}
