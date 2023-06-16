package algonquin.cst2335.coll0513;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import algonquin.cst2335.coll0513.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        class MyRowHolder extends RecyclerView.ViewHolder {
            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        binding.recycleView.setAdapter(new RecyclerView.Adapter<MyRowHolder>() {
            @Override
            public MyRowHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }

        });
    }
}