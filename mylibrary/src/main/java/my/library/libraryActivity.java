package my.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class libraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Toast.makeText(getApplicationContext(), getIntent().getStringExtra("joke"), Toast.LENGTH_LONG).show();
    }
}
