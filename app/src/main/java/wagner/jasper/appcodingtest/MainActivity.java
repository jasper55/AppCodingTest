package wagner.jasper.appcodingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;

import wagner.jasper.appcodingtest.model.ItemList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    RecyclerView.Adapter rvadapter1;
    RecyclerView.LayoutManager rvLayoutManager1;
    private final static String url = "https://anapioficeandfire.com/api/houses";

    static ArrayList<String> itemNames;
    static ItemList itemList;
    static JSONArray data;

    static TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ItemList();
        Log.i("jsonArray: ","before loading");
        itemNames = itemList.getNameList(this);
        data = itemList.getDetails(this);
        //itemNames = itemList.loadListFromUrl(this, url);
        Log.i("jsonArray: ","after loading");

        recyclerView1 = (RecyclerView) findViewById(R.id.rv_houseNameList);
        rvLayoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(rvLayoutManager1);

        rvadapter1 = new RecyclerViewAdapter(this);
        recyclerView1.setAdapter(rvadapter1);

        tv1 = (TextView) findViewById(R.id.tv);
    }
}