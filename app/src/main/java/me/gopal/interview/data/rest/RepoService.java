package me.gopal.interview.data.rest;

import java.util.List;

import io.reactivex.Single;
import me.gopal.interview.data.model.Contact;
import me.gopal.interview.data.model.Room;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

    @GET("people")
    Single<List<Contact>> getRepositories();

    @GET("people")
    Single<Contact> getRepo(@Path("owner") String owner, @Path("name") String name);

    @GET("rooms")
    Single<List<Room>> getRooms();
}
