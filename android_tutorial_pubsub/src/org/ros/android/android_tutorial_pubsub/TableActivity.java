package org.ros.android.android_tutorial_pubsub;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

//import org.ros.node.NodeConfiguration;
//import org.ros.node.NodeMainExecutor;


public class TableActivity extends AppCompatActivity {

    private Talker talker;
    private TableInfo[] tables = new TableInfo[3]; //실제 테이블 정보
    private ImageButton[] tableImages = new ImageButton[3]; //xml에서 Image button

    private TextView t11, t12, t13, t14;
    private TextView t21, t22, t23, t24;
    private TextView t31, t32, t33, t34;
    private DrawerLayout DL;
    private ActionBarDrawerToggle DToggle;
    private String mActivityTitle;
    private TableLayout mDTable;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference table_ref[] = new DatabaseReference[3]; //table별 reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);    // activity_drawer

        mDTable = (TableLayout) findViewById(R.id.order_info_table);
        DL = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }catch (Exception ex){
            Log.e("TAG", ex.getMessage());
        }

        setupDrawer();

        tableImages[0] = (ImageButton) findViewById(R.id.table1);
        tableImages[1] = (ImageButton) findViewById(R.id.table2);
        tableImages[2] = (ImageButton) findViewById(R.id.table3);

        setupMessageButton();

        t11 = (TextView) findViewById(R.id.t11);
        t12 = (TextView) findViewById(R.id.t12);
        t13 = (TextView) findViewById(R.id.t13);
        t14 = (TextView) findViewById(R.id.t14);

        t21 = (TextView) findViewById(R.id.t21);
        t22 = (TextView) findViewById(R.id.t22);
        t23 = (TextView) findViewById(R.id.t23);
        t24 = (TextView) findViewById(R.id.t24);

        t31 = (TextView) findViewById(R.id.t31);
        t32 = (TextView) findViewById(R.id.t32);
        t33 = (TextView) findViewById(R.id.t33);
        t34 = (TextView) findViewById(R.id.t34);


        for (int i = 0; i < 3; i++) {
            tables[i] = new TableInfo();
        }

        table_ref[0] = databaseReference.child("table").child("table1");
        table_ref[1] = databaseReference.child("table").child("table2");
        table_ref[2] = databaseReference.child("table").child("table3");

        //image button을 통해서 table 별 status 바꾸기
        //app, DB 다 바꿈
        tableImages[0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (tables[0].getStatus() <= 4) {
                            tables[0].setStatus(tables[0].getStatus() + 1);
                            table_ref[0].child("status").setValue(tables[0].getStatus());
                        }
//                        if(tables[0].getStatus() == 3){     // status 이 3되면서 동시에 이미지는 delivery로 바꾸면서 1번 테이블로 로봇 호출됨
//                            talker.button_pressed[0] = true;    // 1번 테이블로 로봇 호출됨
//                        }
                    }
                }
        );

        tableImages[1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        if (tables[1].getStatus() <= 4) {
                            tables[1].setStatus(tables[1].getStatus() + 1);
                            table_ref[1].child("status").setValue(tables[1].getStatus());
                        }
//                        if(tables[1].getStatus() == 3){             // status 이 3되면서 동시에 이미지는 delivery로 바꾸면서 1번 테이블로 로봇 호출됨
//                           talker.button_pressed[1] = true;       // 2번 테이블로 로봇 호출됨
//                        }
                    }
                }
        );

        tableImages[2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (tables[2].getStatus() <= 4) {
                            tables[2].setStatus(tables[2].getStatus() + 1);
                            table_ref[2].child("status").setValue(tables[2].getStatus());
                        }
//                        if(tables[2].getStatus() == 3){         // status 이 3되면서 동시에 이미지는 delivery로 바꾸면서 1번 테이블로 로봇 호출됨
//                            talker.button_pressed[2] = true;        // 3번 테이블로 로봇 호출됨
//                        }
                    }
                }
        );


        //사용자가 주문을 했을 경우, DB changed
        //그때 데이터 읽어옴

        table_ref[0].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int status = dataSnapshot.child("status").getValue(Integer.class);
                if (status == 1) {
                    DL.openDrawer(GravityCompat.START);                                                 //주문 정보를 포함하고 있는 TableLayout (activity_order) 오른쪽 화면에 호출
                    CheckOrder(0, dataSnapshot, " ");
                    t11.setText("1");
                }
                changeTable(tableImages[0], status);
                t12.setText(tables[0].getTablenum());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        table_ref[1].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int status = dataSnapshot.child("status").getValue(Integer.class);
                if (status == 1) {
                    DL.openDrawer(GravityCompat.START);
                    CheckOrder(1, dataSnapshot, " ");
                    t21.setText("2");
                }
                changeTable(tableImages[1], status);
                t22.setText(tables[1].getTablenum());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        table_ref[2].addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int status = dataSnapshot.child("status").getValue(Integer.class);
                if (status == 1) {
                    DL.openDrawer(GravityCompat.START);
                    CheckOrder(2, dataSnapshot, " ");
                    t31.setText("3");
                }
                changeTable(tableImages[2], status);
                t32.setText(tables[2].getTablenum());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*       Talker Connection using a method to access Talker.java file
    *        Using Image Button to call on to Robot for Delivery        */
//    private void setupMessageButton(){
//
//        tableImages[0].setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(tables[0].getStatus() == 3){
//                    talker.button_pressed[0] = true;
//                }
//            }
//        });
//
//        tableImages[1].setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(tables[1].getStatus() == 3){
//                    talker.button_pressed[1] = true;
//                }
//            }
//        });
//
//        tableImages[2].setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(tables[2].getStatus() == 3){
//                    talker.button_pressed[2] = true;
//                }
//            }
//        });
//    }

    /*      Initiate Navigation Layout via Drawer Layout        */
    private void setupDrawer()  {
        DToggle = new ActionBarDrawerToggle(this,DL, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("주문 정보!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        DToggle.setDrawerIndicatorEnabled(true);
        DL.setDrawerListener(DToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            // Sync the toggle state after onRestoreInstanceState has occurred.
            DToggle.syncState();
        }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_table_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (DToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //status 별 ImageButton의 resource change

    private void changeTable(ImageButton btn, int status){
        Resources resources = getResources();
        switch(status) {
            case 1:
                btn.setImageDrawable(resources.getDrawable(R.drawable.table_order));
                break;
            case 2:
                btn.setImageDrawable(resources.getDrawable(R.drawable.table_progress));
                break;
            case 3:
                btn.setImageDrawable(resources.getDrawable(R.drawable.table_delivery));
                break;
            case 4:
                btn.setImageDrawable(resources.getDrawable(R.drawable.table_orderend));
                break;
            default:
                btn.setImageDrawable(resources.getDrawable(R.drawable.table_oval));
                break;
        }
        btn.setBackgroundColor(Color.TRANSPARENT);
    }

    // TableLayout 수정
    private void CheckOrder(int table, DataSnapshot dataSnapshot, String keyVal){
        String [] tablenum = new String[3];
        /*         Column two for ordered table number               */

        tables[0].setTablenum(dataSnapshot.child("table1").getKey());
        tables[1].setTablenum(dataSnapshot.child("table2").getKey());
        tables[2].setTablenum(dataSnapshot.child("table3").getKey());

        /*         Column three for ordered drinks               */

        tables[table].setOrder(dataSnapshot.child(" ").getKey()+ dataSnapshot.child("order").getValue());
        t13.setText(tables[0].getOrder());      // table
        t23.setText(tables[1].getOrder());
        t33.setText(tables[2].getOrder());

        /*         Column four for ordered time               */

        tables[table].setTime(dataSnapshot.child("time").getValue(String.class));
        t14.setText(tables[0].getTime());
        t24.setText(tables[1].getTime());
        t34.setText(tables[2].getTime());

        /*        For status of ordered drinks               */

        tables[table].setStatus(dataSnapshot.child("status").getValue(Integer.class));

        //Table Layout에 row 추가하는 자바 코드
        // 첫번째 주문 번호, 두번째 table(table), 세번째는 주문 내용, 마지막은 주문 시간
        // 첫번째는 ordernumber++ 이걸 넣어
        // 두번째는 table+1
        // 세번째는
        //        String order = "Americano: " +  order[0] + ", Caffe Latte: " + order[1] + ", Frappuccino: " + order[2] + ", Icetea: " + order[3];
        // 마지막은 tables[table].getTime()
//        tables[table] = inst_table;

    }
    private void setupMessageButton() {
        tableImages[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(tables[0].getStatus()==3){
                        talker.button_pressed[0] = true;
                    }
            }
        });
        tableImages[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tables[1].getStatus()==3){
                    talker.button_pressed[1] = true;
                }

            }
        });
        tableImages[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tables[2].getStatus()==3){
                    talker.button_pressed[2] = true;
                }
            }
        });
    }

}
