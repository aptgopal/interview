package me.gopal.interview.data.rest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import me.gopal.interview.data.model.Contact;

public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Contact>> getRepositories() {
        return repoService.getRepositories();
    }

    public Single<Contact> getRepo(String owner, String name) {
        return repoService.getRepo(owner, name);
    }
}
