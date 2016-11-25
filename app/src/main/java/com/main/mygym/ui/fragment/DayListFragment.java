package com.main.mygym.ui.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.main.mygym.Const;
import com.main.mygym.R;
import com.main.mygym.adapter.ExercisesCursorAdapter;
import com.main.mygym.provider.Contract;

/**
 * Created by barikos on 24.11.16.
 */
public class DayListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private ExercisesCursorAdapter mAdapter;
    private String mDay;

    public static DayListFragment getInstance(String day) {
        DayListFragment fragment = new DayListFragment();
        Bundle args = new Bundle();
       /* args.putLongArray(Const.FRAG_LIST_ARG_ID, listId);
        args.putStringArrayList(Const.FRAG_LIST_ARG_NAME, listName);*/
        args.putString(Const.DAY_ARG_NAME, day);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_day, null);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mDay = getArguments().getString(Const.DAY_ARG_NAME);
        }

        mListView = (ListView) view.findViewById(R.id.list_fragment_day);
        mAdapter = new ExercisesCursorAdapter(getContext(), null, 0);
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().initLoader(Contract.LOADER_F_ID, null, this);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExercisesCursorAdapter.ViewHolder holder = (ExercisesCursorAdapter.ViewHolder) view.getTag();
                if (holder != null) {
                    Toast.makeText(getContext(), holder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] selectionArgs = {mDay};
        return new CursorLoader(getContext(), Contract.CONTENT_URI, null, Contract.SELECTION, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
