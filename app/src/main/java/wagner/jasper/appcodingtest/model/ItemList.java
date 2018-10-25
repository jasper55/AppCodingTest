package wagner.jasper.appcodingtest.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

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

    public ArrayList<String> loadListFromUrl(Context context, String urlLink){
        new JSONTask().execute(urlLink);
        return nameList;
    }
    public class JSONTask extends AsyncTask<String, String, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... strings) {

            final String name = "name";
            nameList = new ArrayList<String>();
            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            URL url = null;

            try {
                url = new URL(strings[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                Log.i("StringBuffer: ",buffer.toString());
                String bufferString = buffer.toString();
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(bufferString);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.i("jsonObject: ",jsonObject.toString());
                    String mockName = jsonObject.getString("name");
                    Log.i("jsonString: ",mockName.toString());
                    nameList.add(mockName);
                }
                return nameList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {reader.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.i("onPost: ","wurde ausgeführt");
            super.onPostExecute(result);
            Log.i("onPost: ","wurde ausgeführt");
            Log.i("Post_Buffer: ",result.toString());
            //String bufferString = buffer.toString();
            ArrayList<String> bufferString = result;
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(bufferString);
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Log.i("jsonObject: ",jsonObject.toString());
                    String mockName = jsonObject.getString("name");
                    Log.i("jsonString: ",mockName.toString());
                    nameList.add(mockName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}