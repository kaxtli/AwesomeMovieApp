package com.kaxtli.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kaxtli on 01/04/2017.
 */

// Implementará las tareas de un fragment
public class Main extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View es la vista ligada al fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
