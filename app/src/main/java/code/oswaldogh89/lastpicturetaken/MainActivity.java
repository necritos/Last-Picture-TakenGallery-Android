package code.oswaldogh89.lastpicturetaken;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Image> imagesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        assert my_recycler_view != null;
        my_recycler_view.setHasFixedSize(true);

        imagesArray = Utils.getFilePaths(MainActivity.this);

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, imagesArray);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        my_recycler_view.setAdapter(adapter);

    }
}
