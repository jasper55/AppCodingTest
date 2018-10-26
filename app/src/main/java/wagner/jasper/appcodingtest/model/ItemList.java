package wagner.jasper.appcodingtest.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ItemList extends ArrayList<String>{

    private ArrayList<String> nameList;
    private JSONArray datailList;

    public ItemList() {
        this.nameList = nameList;
        this.datailList = datailList;
    }

    public ArrayList<String> getNameList(Context context) {
        AssetManager assetManager = context.getAssets();
        final String name = "name";
        nameList = new ArrayList<String>();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("GOT_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String mockName = jsonObject.getString(name);
                nameList.add(mockName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("nameList: ", nameList.toString());
        return nameList;
    }

    public JSONArray getDetails(Context context) {
        AssetManager assetManager = context.getAssets();
        datailList = new JSONArray();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open("GOT_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer,"UTF-8");
            datailList = new JSONArray(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datailList;
    }
}