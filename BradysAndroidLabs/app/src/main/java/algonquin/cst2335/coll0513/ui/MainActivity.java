package algonquin.cst2335.coll0513.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
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
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.mybutton.setOnClickListener(vw -> {
            model.editString.observe(this, s -> {
                variableBinding.textview.setText(String.format("Your edit text has: %s", s));
            });
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        model.isSelected.observe(this, selected -> {
            CharSequence text = ("The value is now: " + selected);
            variableBinding.checkbox.setChecked(selected);
            variableBinding.radio.setChecked(selected);
            variableBinding.switchbutton.setChecked(selected);
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        variableBinding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.isSelected.postValue(isChecked);
            variableBinding.radio.setChecked(isChecked);
            variableBinding.switchbutton.setChecked(isChecked);
        });

        variableBinding.switchbutton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.isSelected.postValue(isChecked);
            variableBinding.checkbox.setChecked(isChecked);
            variableBinding.radio.setChecked(isChecked);
        });

        variableBinding.radio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.isSelected.postValue(isChecked);
            variableBinding.checkbox.setChecked(isChecked);
            variableBinding.switchbutton.setChecked(isChecked);
        });

        variableBinding.imageview.setOnClickListener(vw -> {
            model.editString.observe(this, s -> {
                        variableBinding.textview.setText(String.format("You've clicked the image: " + variableBinding.imageview.getTag()));
                    });
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        variableBinding.myimagebutton.setOnClickListener(vw -> {
            CharSequence text = ("The width = " + variableBinding.myimagebutton.getWidth() + "px and height = " + variableBinding.myimagebutton.getHeight() + "px");
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });
    }
}