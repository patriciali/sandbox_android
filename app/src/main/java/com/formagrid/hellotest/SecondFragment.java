package com.formagrid.hellotest;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

public class SecondFragment extends Fragment {

    private static final String EXTRA_ITEM_INDEX = "extra_item_index";

    private int mItemIndex;
    private FirstFragment mFirstFragment;
    private StringWrapper mItem;

    public SecondFragment() {
        super();
        setArguments(new Bundle());
    }

    public void safeSetArguments(int itemPosition) {
        getArguments().putInt(EXTRA_ITEM_INDEX, itemPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FrameLayout topLevelView = (FrameLayout) inflater.inflate(R.layout.fragment_second, container, false);

        mItemIndex = getArguments().getInt(EXTRA_ITEM_INDEX);
        mFirstFragment = (FirstFragment) getActivity().getFragmentManager().findFragmentByTag(HelloActivity.FIRST_TRANSACTION);
        mItem = mFirstFragment.getItemAtIndex(mItemIndex);

        EditText editText = (EditText) topLevelView.findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.string = String.valueOf(s);
                mFirstFragment.notifyItemAtIndexChanged(mItemIndex);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return topLevelView;
    }

}
