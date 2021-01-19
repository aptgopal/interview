package me.gopal.interview.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.gopal.interview.data.model.Contact;
import me.gopal.interview.data.rest.RepoRepository;

public class DetailsViewModel extends ViewModel {

    private final RepoRepository repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Contact> selectedRepo = new MutableLiveData<>();

    public LiveData<Contact> getSelectedRepo() {
        return selectedRepo;
    }

    @Inject
    public DetailsViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    public void setSelectedRepo(Contact contact) {
        selectedRepo.setValue(contact);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details", new String[] {
                    selectedRepo.getValue().lastName,
                    selectedRepo.getValue().firstName
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedRepo.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repo_details) {
        disposable.add(repoRepository.getRepo(repo_details[0], repo_details[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Contact>() {
            @Override
            public void onSuccess(Contact value) {
                selectedRepo.setValue(value);
            }

            @Override
            public void onError(Throwable e) {

            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
