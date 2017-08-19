package org.ros.android.android_tutorial_pubsub;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import static org.ros.android.android_tutorial_pubsub.R.id.table_screen_return;


//import android.support.v7.app.AppCompatActivity;

//import org.ros.cafe_Boss_Emp.app.R;

//import static com.example.user.ca_simulation.R.id.table_screen_return;
//import android.widget.ActionMenuView.*;

/**
  Created by user on 2017-07-02.
 */

public class OrderedContentActivity extends TableActivity{  // extends AppCompactActivity

    private TextView textViewMenu, textViewCount, textViewtable_num;
    private ArrayList<TableModel> menuList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordered_content);
     //   Firebase.setAndroidContext(this);
        textViewMenu = (TextView) findViewById(R.id.menu);
        textViewCount = (TextView) findViewById(R.id.count);
        textViewtable_num = (TextView) findViewById(R.id.tableNo);

        DatabaseReference mDatabase  = FirebaseDatabase.getInstance().getReference("Table1");
        mDatabase.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Table table = dataSnapshot.getValue(Table.class);
                // adding values to a string
                String string = "table_num: "+table.getTable_num();
                String s2 = "\nmenu: "+table.getMenu();
                String s3 = "\ncount: "+table.getCount();
                // display values in various TextViews
                textViewtable_num.setText(string);
                textViewMenu.setText(s2);
                textViewCount.setText(s3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        menuList = new ArrayList<>();
        ListView view = (ListView) findViewById(R.id.listView);
        tablelistviewAdapter adapter = new tablelistviewAdapter(this, menuList);
        view.setAdapter(adapter);

        populateList();
        adapter.notifyDataSetChanged();
        view.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String tableNo = ((TextView) view.findViewById(R.id.tableNo)).getText().toString();
                String menu = ((TextView) view.findViewById(R.id.menu)).getText().toString();
                String size = ((TextView) view.findViewById(R.id.size)).getText().toString();
                String count = ((TextView) view.findViewById(R.id.count)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "Table_Number : " + tableNo + "\n"
                                + "Menu : " + menu + "\n"
                                + "Size : " + size + "\n"
                                + "Count : " + count, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateList() {

        TableModel item1, item2, item3;

        item1 = new TableModel("\t\t\t\t1 ", "    Esspresso", "\t\t\t\t\t\t\tX", "\t\t\t\t2");
        menuList.add(item1);
        item2 = new TableModel("\t\t\t\t5 ", "    Americano", "\t\t\t\t\t\t\tL", "\t\t\t\t1");
        menuList.add(item2);
        item3 = new TableModel("\t\t\t\t2 ", "    Hot Chocolate", "\t\t\t\t\t\t\tXL", "\t\t\t\t3");
        menuList.add(item3);
    }
    // Inflate Menu icons

    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu, this adds items to the actionbar
        getMenuInflater().inflate(R.menu.menu_ordered_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == table_screen_return){
            Intent intent = new Intent(getApplication(), TableActivity.class);
            count++;
            startActivity(intent);
            count=1;
        }
        return super.onOptionsItemSelected(item);
    }
}
