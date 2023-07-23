package algonquin.cst2335.coll0513.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import algonquin.cst2335.coll0513.ChatRoom;

public class ChatRoomViewModel extends ViewModel{
    public MutableLiveData<ArrayList<ChatRoom.ChatMessage>> messages = new MutableLiveData<>();
    public MutableLiveData<ChatRoom.ChatMessage> selectedMessage = new MutableLiveData<>(null);
}
