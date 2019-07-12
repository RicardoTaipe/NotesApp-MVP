package com.example.notesapp.activities.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.notesapp.Model.Note;
import com.example.notesapp.R;
import com.example.notesapp.activities.editor.EditorActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, MainAdapter.ItemClickListener {
    private static final int INTENT_EDIT =200 ;
    private static final int INTENT_ADD = 100;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;

    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;
    //private MainAdapter.ItemClickListener itemClickListener;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh= findViewById(R.id.swipe_refresh);
        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab =(FloatingActionButton) findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, EditorActivity.class),
                        INTENT_ADD);
            }
        });

        mainPresenter = new MainPresenter(this);
        mainPresenter.getNotes();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getNotes();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTENT_ADD && resultCode== RESULT_OK){
            mainPresenter.getNotes();
        }else if(requestCode == INTENT_EDIT && resultCode== RESULT_OK){
            mainPresenter.getNotes();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        mainAdapter = new MainAdapter(notes,this,this);
        mainAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mainAdapter);
        this.notes=notes;
    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void onItemClick(View view, int position) {
        String title = notes.get(position).getTitle();
        Intent intent = new Intent(this,EditorActivity.class);
        intent.putExtra("note",notes.get(position));
        startActivityForResult(intent,INTENT_EDIT);
    }
}
