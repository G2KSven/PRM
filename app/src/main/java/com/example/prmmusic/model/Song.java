package com.example.prmmusic.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("albumID")
    @Expose
    private String albumID;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("like")
    @Expose
    private int like;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playlistID")
    @Expose
    private String playlistID;
    @SerializedName("singer")
    @Expose
    private String singer;
    @SerializedName("typeID")
    @Expose
    private String typeID;

    public Song(int id, String albumID, String image, int like, String link, String name, String playlistID, String singer, String typeID) {
        this.id = id;
        this.albumID = albumID;
        this.image = image;
        this.like = like;
        this.link = link;
        this.name = name;
        this.playlistID = playlistID;
        this.singer = singer;
        this.typeID = typeID;
    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public Song(Parcel parcel) {
        id = parcel.readInt();
        albumID = parcel.readString();
        image = parcel.readString();
        like = parcel.readInt();
        link = parcel.readString();
        name = parcel.readString();
        playlistID = parcel.readString();
        singer = parcel.readString();
        typeID = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(albumID);
        dest.writeString(image);
        dest.writeInt(like);
        dest.writeString(link);
        dest.writeString(name);
        dest.writeString(playlistID);
        dest.writeString(singer);
        dest.writeString(typeID);
    }

    public static Creator<Song> CREATOR = new Creator<Song>() {

        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
