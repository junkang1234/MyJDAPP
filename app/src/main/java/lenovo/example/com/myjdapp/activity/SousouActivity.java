package lenovo.example.com.myjdapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.untils.IntentUntils;


public class SousouActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView zuo;
    private RecyclerView recyclerView;
    private TextView ti;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousou);

        getSupportActionBar().hide();

        initView();

    }

    private void initView() {

        zuo = (ImageView) findViewById(R.id.normal_code);
        zuo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyfive);
        ti = (TextView) findViewById(R.id.normal_message);
        ti.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.button_search);

    }

    @Override
    public void onClick(View view) {
        //switch判断
        switch (view.getId()) {
        		case R.id.normal_code:
        			closeThis();
        			break;
            case R.id.normal_message:
                tiaozou();
                break;
            case R.id.recyfive:
                break;
        		default:
        			break;
        		}
    }

    private void tiaozou() {
        IntentUntils.setIntent(this,SouSou1Activity.class,"keywords",editText.getText().toString());
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }

    private void closeThis() {
            finish();
        overridePendingTransition(R.anim.leftone, R.anim.rightone);
    }
}
