package com.example.englishapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {

    private final List<Topic> topics;
    private final OnTopicClickListener onClickListener;

    interface OnTopicClickListener{
        void onTopicClick(Topic state, int position);
    }

    public class TopicHolder extends RecyclerView.ViewHolder {

        private TextView viewTitle;
        private TextView viewSerialNumber;

        public TopicHolder(@NonNull View itemView) {
            super(itemView);
            viewTitle = itemView.findViewById(R.id.topicName);
            viewSerialNumber = itemView.findViewById(R.id.topicNumber);
        }

        public TextView getTitileView(){
            return viewTitle;
        }
        public TextView getSerialNumberView(){
            return viewSerialNumber;
        }
    }

    public TopicAdapter(List<Topic> topics, OnTopicClickListener onClickListener) {
        this.topics = topics;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_topic;
    }

    @NonNull
    @Override
    public TopicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.getTitileView().setText(topic.getTitle());
        holder.getSerialNumberView().setText(topic.getSerialNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onTopicClick(topic, holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }
}
