package com.example.prmmusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmmusic.R;
import com.example.prmmusic.activity.AlbumListActivity;
import com.example.prmmusic.activity.AllTopicActivity;
import com.example.prmmusic.activity.PlaylistActivity;
import com.example.prmmusic.adapter.RecyclerAlbumOverviewAdapter;
import com.example.prmmusic.model.Album;
import com.example.prmmusic.model.Playlist;
import com.example.prmmusic.model.Song;
import com.example.prmmusic.service.APIService;
import com.example.prmmusic.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumOverviewFragment extends Fragment implements RecyclerAlbumOverviewAdapter.OnItemClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView moreAlbum = view.findViewById(R.id.text_view_more_album);
        moreAlbum.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AlbumListActivity.class);
            startActivity(intent);
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_album_overview);
        DataService dataService = APIService.getService();
        Call<List<Album>> callBack = dataService.get4Albums();
        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call,
                                   @NonNull Response<List<Album>> response) {
                recyclerView.setAdapter(
                        new RecyclerAlbumOverviewAdapter(getContext(), response.body(),
                                AlbumOverviewFragment.this));
            }

            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
                Log.d("fail", "fail to load data");
            }
        });
    }

    @Override
    public void onItemClick(Album album) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callBack = dataService.getSongsFromAlbum(album.getId());
        callBack.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Intent intent = new Intent(getContext(), PlaylistActivity.class);
                Playlist playlist = new Playlist();
                playlist.setImageIcon(album.getImage());
                playlist.setImageBackgroud(album.getImage());
                playlist.setName(album.getName());
                intent.putExtra("playlist", playlist);
                intent.putParcelableArrayListExtra("songs",
                        (ArrayList<? extends Parcelable>) response.body());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
