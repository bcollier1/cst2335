package algonquin.cst2335.coll0513.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import algonquin.cst2335.coll0513.data.MainViewModel;
import algonquin.cst2335.coll0513.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView textview = variableBinding.textview;
        variableBinding.mybutton.setOnClickListener(vw -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            model.editString.observe(this, s ->{
                variableBinding.textview.setText(String.format("Your edit text has: %s", s));
            });
        });
    }
}