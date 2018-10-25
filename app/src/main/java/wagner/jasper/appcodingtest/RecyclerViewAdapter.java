package wagner.jasper.appcodingtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderKlasse> {

    int rowIndex = -1;
    Context context;

    public class ViewHolderKlasse extends RecyclerView.ViewHolder{

        TextView itemTextView;
        ImageView itemImageView;

        public ViewHolderKlasse(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.tv_houseName);
            itemImageView = (ImageView) itemView.findViewById(R.id.iv_twitter_ic);
        }
    }

    @Override
    public ViewHolderKlasse onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, null);
        return new ViewHolderKlasse(itemView1);
    }

    @Override
    public void onBindViewHolder(final ViewHolderKlasse viewHolderKlasse, final int i) {

        viewHolderKlasse.itemTextView.setText(MainActivity.itemNames.get(i));
        viewHolderKlasse.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    MainActivity.tv1.setText(MainActivity.itemNames.get(i));
                    rowIndex = i;
                    notifyDataSetChanged();
            }
        });

        if(rowIndex==i){
            viewHolderKlasse.itemImageView.setVisibility(View.VISIBLE);
            viewHolderKlasse.itemView.setBackgroundColor(Color.parseColor("#838487"));
            viewHolderKlasse.itemTextView.setTextColor(Color.parseColor("#FFFFFF"));

            viewHolderKlasse.itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("onClick: ",MainActivity.itemNames.get(i));
                    try {
                        Log.i("json: ",MainActivity.data.getJSONObject(i).toString());
                        JSONObject jsonObject = MainActivity.data.getJSONObject(i);
                        showDetailViewForEdit(jsonObject,context.getApplicationContext());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else
        {
            viewHolderKlasse.itemImageView.setVisibility(View.INVISIBLE);
            viewHolderKlasse.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolderKlasse.itemTextView.setTextColor(Color.parseColor("#9b9b9b"));
        }

    }

    @Override
    public int getItemCount() {
        return MainActivity.itemNames.size();
    }

    private void showDetailViewForEdit(JSONObject jsonObject, Context context) {
        Intent detailviewIntent = new Intent(context.getApplicationContext(),DetailviewActivity.class);
        detailviewIntent.setClass(context.getApplicationContext(), DetailviewActivity.class);
        try {
            detailviewIntent.putExtra("name", jsonObject.getString("name"));
            detailviewIntent.putExtra("region", jsonObject.getString("region"));
            detailviewIntent.putExtra("url", jsonObject.getString("url"));
            detailviewIntent.putExtra("words", jsonObject.getString("words"));
            detailviewIntent.putExtra("coatOfArms", jsonObject.getString("coatOfArms"));
            context.startActivity(detailviewIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerViewAdapter() {
    }
}