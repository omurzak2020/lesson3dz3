package com.example.lesson3dz3;

import android.content.Intent;
import android.icu.text.UFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class FormFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CONTENT = "content";
    private static final String ARG_USER = "user";
    private static final String ARG_GROUP = "group";
    private static final String ARG_ID = "id";

    private static final String UP_LOAD = "upload";

    private String mTitle;
    private String mContent;
    private Integer mUser;
    private Integer mGroup;
    private Integer mId;

    private boolean isUpdating;

    private EditText title,desc,user,group;
    private Button button;


    public static FormFragment newInstance(Post post) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();

        args.putString(ARG_TITLE, post.getTitle());
        args.putString(ARG_CONTENT, post.getContent());
        args.putInt(ARG_USER, post.getUser());
        args.putInt(ARG_GROUP,post.getGroup());
        args.putInt(ARG_ID,post.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            isUpdating = true;
            mTitle = getArguments().getString(ARG_TITLE);
            mContent = getArguments().getString(ARG_CONTENT);
            mUser = getArguments().getInt(ARG_USER);
            mGroup = getArguments().getInt(ARG_GROUP);
            mId = getArguments().getInt(ARG_ID);
            (( MainActivity ) requireActivity()).navView().setVisibility(View.GONE);
        }
    }

    public void setOnEditFields(){
        Toast.makeText(requireContext(), "ololo"+  mTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.title2);
        user = view.findViewById(R.id.title3);
        group = view.findViewById(R.id.title4);
        button = view.findViewById(R.id.save);
        if (isUpdating) setOnEditFields();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitBuilder.getInstance().createPost(new Post(title.getText().toString(),
                        desc.getText().toString(),
                        Integer.parseInt(user.getText().toString()),
                        Integer.parseInt(group.getText().toString()))).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(requireContext(), "ololol"+ response.body().getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(requireContext(), "ololol "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_activity,new HomeFragment());
                transaction.commit();

            }


        });

    }

}