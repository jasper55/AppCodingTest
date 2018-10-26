package wagner.jasper.appcodingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import java.util.ArrayList;
import wagner.jasper.appcodingtest.model.ItemList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvDisplayNameList;
    RecyclerView.Adapter rvNameListAdapter;
    RecyclerView.LayoutManager rvNameListLayoutManager;

    static ArrayList<String> itemNamesArray;
    static ItemList itemArrayList;
    static JSONArray JSONDataArray;

    static TextView tvBottomDisplaySelectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemArrayList = new ItemList();
        itemNamesArray = itemArrayList.getNameList(this);
        JSONDataArray = itemArrayList.getDetails(this);

        rvDisplayNameList = (RecyclerView) findViewById(R.id.rv_houseNameList);
        rvNameListLayoutManager = new LinearLayoutManager(this);
        rvDisplayNameList.setLayoutManager(rvNameListLayoutManager);

        rvNameListAdapter = new RecyclerViewAdapter(this);
        rvDisplayNameList.setAdapter(rvNameListAdapter);

        tvBottomDisplaySelectedName = (TextView) findViewById(R.id.tvBottomDisplaySelectedName);
    }
}