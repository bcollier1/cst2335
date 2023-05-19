package algonquin.cst2335.coll0513;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import algonquin.cst2335.coll0513.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext =   variableBinding.textview;
        final Button btn =   variableBinding.mybutton;
        EditText myedit =   variableBinding.myedittext;
        String editString = myedit.getText().toString();
        btn.setOnClickListener(
                vw ->   mytext.setText(String.format("Your edit text has: %s", editString)) );
    }
}