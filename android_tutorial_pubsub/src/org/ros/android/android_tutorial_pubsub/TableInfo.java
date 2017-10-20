package org.ros.android.android_tutorial_pubsub;

/**
  Created by yeji on 2017. 9. 20..
 */

public class TableInfo {

    private int status;
    private String order;     //  private String order[] = {"0", "0", "0", "0"};
    private String time;
    private String tablenum;

    public String getOrder(){     // int
        return order;
    }
    public int getStatus(){
        return status;
    }
    public String getTime(){
        return time;
    }
    public String getTablenum(){return tablenum; }

    public void setOrder(String  order){      // int
        this.order = order;
    }
    public void setStatus(int status){
        if(status <= 4)
            this.status = status;
        if (status == 5) {
            setDefault();
        }
    }

    public void setDefault(){
        //배달이 끝나면 초기화
        // 아직 DB정송 -> 해야됨
        this.status = 0;
        this.time = "";
//        this.order[0] = "0"; //americano
//        this.order[1] = "0"; //caffe latte
//        this.order[2] = "0"; //Fra
//        this.order[3] = "0"; //Icetea
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setTablenum(String tablenum){this.tablenum = tablenum; }
}
