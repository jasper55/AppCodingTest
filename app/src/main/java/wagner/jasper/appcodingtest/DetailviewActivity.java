package wagner.jasper.appcodingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailviewActivity extends AppCompatActivity {

    TextView tv_url, tv_name, tv_region, tv_words, tv_coatOfArms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);

        Bundle extras = getIntent().getExtras();

        String url = null;
        String name = null;
        String region = null;
        String words = null;
        String coatOfArms = null;

        if(extras != null){
            name = extras.getString("name");
            url = extras.getString("url");
            region = extras.getString("region");
            words = extras.getString("words");
            coatOfArms = extras.getString("coatOfArms");
        }

        tv_url = findViewById(R.id.tv_url);
        tv_url.setText(url);

        tv_name = findViewById(R.id.tv_name);
        tv_name.setText(name);

        tv_region = findViewById(R.id.tv_region);
        tv_region.setText(region);

        tv_words = findViewById(R.id.tv_words);
        tv_words.setText(words);

        tv_coatOfArms = findViewById(R.id.tv_coatOfArms);
        tv_coatOfArms.setText(coatOfArms);
    }
}