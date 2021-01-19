package me.gopal.interview.data.rest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import me.gopal.interview.data.model.Contact;
import me.gopal.interview.data.model.Room;

public class RoomRepository {

    private final RepoService repoService;

    @Inject
    public RoomRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Room>> getRoomDetails() {
        return repoService.getRooms();
    }


}
