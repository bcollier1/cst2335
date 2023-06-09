package algonquin.cst2335.coll0513;

import androidx.activity.result.*;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.*;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.*;

import algonquin.cst2335.coll0513.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        binding.textView.setText(String.format("Welcome back %s!", emailAddress));
        String filename = "Picture.png";
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        binding.editTextPhone.setText(
                prefs.getString("PhoneNumber", "")
        );

        binding.phoneButton.setOnClickListener(v -> {
            String phoneNumber = binding.editTextPhone.getText().toString();
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumber));
            editor.putString("PhoneNumber",phoneNumber);
            editor.apply();
            startActivity(call);
        });

        binding.pictureButton.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });

        binding.backButton.setOnClickListener(cl ->
            finish()
        );

        File file = new File( getFilesDir(), filename);

        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            binding.imageView.setImageBitmap(theImage);
        }
    }

    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (data==null){
                        return;
                    }
                    Bitmap thumbnail = data.getParcelableExtra("data");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        binding.imageView.setImageBitmap(thumbnail);

                        FileOutputStream fOut;
                        try {
                            String filename = "Picture.png";
                            fOut = openFileOutput(filename, Context.MODE_PRIVATE);

                            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                            fOut.flush();
                            fOut.close();
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
}