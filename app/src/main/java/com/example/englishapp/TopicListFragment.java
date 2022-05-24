package com.example.englishapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TopicListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Topic> topics;
    private OnFragmentSendDataListener fragmentSendDataListener;

    //Fields for work with database
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private Cursor query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        // Creating RecyclerView in this fragment
        recyclerView = view.findViewById(R.id.topicRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TopicAdapter.OnTopicClickListener topicClickListener = new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onTopicClick(Topic topic, int position) {

                /*Toast.makeText(view.getContext(), "Был выбран пункт " + topic.getSerialNumber(),
                        Toast.LENGTH_SHORT).show();*/

                Fragment bodyFragment = TopicBodyFragment.newInstance(topic.getBody(), topic.getSerialNumber());

                getParentFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frameLayoutTopics, bodyFragment)
                        .commit();
            }
        };

        recyclerView.setAdapter(new TopicAdapter(GenerateTopics(), topicClickListener));

        return view;
    }

    private List<Topic> GenerateTopics() {

        topics = new ArrayList<>();

        db = databaseHelper.getReadableDatabase();
        query =  db.rawQuery("select * from "+ DatabaseHelper.TABLE_TOPICS, null);

        while (query.moveToNext()){
            topics.add(new Topic(
                    query.getString(1),
                    query.getString(2),
                    query.getString(3)));

        }

        //topics.add(new Topic("Построение предложений в английском языке I/We/You/They", 1));
        /*topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", "1", "1"));
        topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", "1", "1"));
        topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", "1", "1"));
        topics.add(new Topic("Построение предложений в английском языке I/We/You/They/1/1/1/1/1", "1", "1"));*/

        db.close();
        query.close();
        return topics;
    }

    //Interface for exchange some data between activity and this fragment
    interface OnFragmentSendDataListener {
        void onSendData(String topicId);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentSendDataListener");
        }
    }
}