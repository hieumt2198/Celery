package com.tmh.celery.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tmh.celery.R;
import com.tmh.celery.adapter.AllRecipesAdapter;
import com.tmh.celery.model.Recipe;

import java.io.Serializable;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Recipe> recipes;

    private OnFragmentInteractionListener mListener;

    private AllRecipesAdapter.OnItemPressedListener mOnAdapterItemPressedListener = new AllRecipesAdapter.OnItemPressedListener() {
        @Override
        public void onItemPressed(Recipe recipe) {
            mListener.onItemClicked(recipe);
        }
    };

    private View.OnClickListener onNewRecipeFabClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mListener.onFabClicked();
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(List<Recipe> recipes) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("RECIPES", (Serializable) recipes);
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance(List<Recipe> recipes, OnFragmentInteractionListener listener) {
        Log.d("Home", "NewInstance!");
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("RECIPES", (Serializable) recipes);
        fragment.setArguments(args);
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipes = (List<Recipe>) getArguments().getSerializable("RECIPES");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FloatingActionButton fabNewRecipe = view.findViewById(R.id.fabNewRecipe);
        fabNewRecipe.setOnClickListener(onNewRecipeFabClicked);

        RecyclerView recyclerAllRecipes = view.findViewById(R.id.recyclerAllRecipes);
        AllRecipesAdapter adapter = new AllRecipesAdapter(recipes, mOnAdapterItemPressedListener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerAllRecipes.setLayoutManager(layoutManager);
        recyclerAllRecipes.setAdapter(adapter);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onItemClicked(Recipe recipe);
        void onFabClicked();
    }
}
