package me.gopal.interview.ui.rooms;

import android.arch.lifecycle.LifecycleOwner;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.gopal.interview.R;
import me.gopal.interview.data.model.Contact;
import me.gopal.interview.data.model.Room;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomViewHolder>{

    private RoomSelectedListener roomSelectedListener;
    private final List<Room> data = new ArrayList<>();

    RoomListAdapter(RoomViewModel viewModel, LifecycleOwner lifecycleOwner, RoomSelectedListener roomSelectedListener) {
        this.roomSelectedListener = roomSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);

    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_room_list_item, parent, false);
        return new RoomViewHolder(view, roomSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RoomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_room_name) TextView roomNameTextView;
        @BindView(R.id.tv_capacity) TextView roomCapacity;
        @BindView(R.id.availability) TextView availability;

        private Room room;

        RoomViewHolder(View itemView, RoomSelectedListener roomSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           /* itemView.setOnClickListener(v -> {
                if(room != null) {
                    roomSelectedListener.onRepoSelected(room);
                }
            });*/
        }

        void bind(Room room) {
            this.room = room;
            roomNameTextView.setText(room.name);
            availability.setText(room.max_occupancy);
            if(room.is_occupied){
                roomCapacity.setText("Occupied");
                roomCapacity.setTextColor(Color.parseColor("#FF0000"));
            }else {
                roomCapacity.setText("Available");
                roomCapacity.setTextColor(Color.parseColor("#008000"));
            }
        }
    }
}
