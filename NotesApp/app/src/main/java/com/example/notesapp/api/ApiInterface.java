package com.example.notesapp.api;

import com.example.notesapp.Model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("note")
    Call<Note> saveNote(@Body Note note);
    @GET("note")
    Call<List<Note>> getNotes();
    @PUT("note")
    Call<Note> updateNote(@Body Note note);
    @DELETE("note/{id}")
    Call<Note> deleteNote(@Path("id") int id);
}
