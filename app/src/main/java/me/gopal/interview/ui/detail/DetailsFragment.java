package me.gopal.interview.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import me.gopal.interview.R;
import me.gopal.interview.base.BaseFragment;
import me.gopal.interview.util.ViewModelFactory;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.user_profile_name) TextView name;
    @BindView(R.id.user_profile_short_bio) TextView jobTitle;
    @BindView(R.id.phoneNumber) TextView phoneNumber;
    @BindView(R.id.emailId) TextView emailId;
    @BindView(R.id.user_profile_photo) ImageView avatar;
    @BindView(R.id.profile_layout) RelativeLayout background;

    @Inject ViewModelFactory viewModelFactory;
    private DetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        detailsViewModel.restoreFromBundle(savedInstanceState);
        displayRepo();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        detailsViewModel.saveToBundle(outState);
    }

    private void displayRepo() {
        detailsViewModel.getSelectedRepo().observe(this, repo -> {
            if (repo != null) {
                name.setText(repo.firstName+" "+repo.lastName);
                jobTitle.setText(repo.jobTitle);
                phoneNumber.setText(repo.phone);
                emailId.setText(repo.email);
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);
                Glide.with(avatar.getContext()).load(repo.avatar).apply(options).into(avatar);

                background.setBackgroundColor(Color.parseColor(repo.favouriteColor));
            }
        });
    }
}
