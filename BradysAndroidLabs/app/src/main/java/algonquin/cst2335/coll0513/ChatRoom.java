package algonquin.cst2335.coll0513;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.coll0513.data.ChatRoomViewModel;
import algonquin.cst2335.coll0513.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.coll0513.databinding.ReceiveMessageBinding;
import algonquin.cst2335.coll0513.databinding.SentMessageBinding;
public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    protected RecyclerView.Adapter myAdapter;
    protected RecyclerView recyclerView;
    protected EditText textInput;
    protected Button btn;
    protected ArrayList<String> messages;
    ChatRoomViewModel chatModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        btn = binding.sendButton;
        textInput = binding.textMessage;
        recyclerView = binding.recycleView;

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateandTime = sdf.format(new Date());

        btn.setOnClickListener(ck -> {
            messages.add(textInput.getText().toString());
            myAdapter.notifyDataSetChanged();
            textInput.setText("");

        });

        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                if(viewType==1){
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                }
                else{
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                }
            }

            public int getItemViewType(int position){
                if (position % 2 == 0) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String obj = messages.get(position);
                holder.theMessage.setText(obj);
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected class MyRowHolder extends RecyclerView.ViewHolder {
        TextView theMessage;
        TextView timestamp;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            theMessage = itemView.findViewById(R.id.textMessageView);
            timestamp = itemView.findViewById(R.id.timestampView);
        }
    }
}