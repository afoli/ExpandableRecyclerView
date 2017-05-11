package ir.thebigbang.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.thebigbang.expandablerecyclerviewlibrary.ConstDefault;
import ir.thebigbang.expandablerecyclerviewlibrary.RecyclerViewCustom;
import ir.thebigbang.expandablerecyclerviewlibrary.models.ChildItem;
import ir.thebigbang.expandablerecyclerviewlibrary.models.TitleItem;
import ir.thebigbang.expandablerecyclerviewlibrary.processes.OnRecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
