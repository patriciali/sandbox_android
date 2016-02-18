package com.formagrid.hellotest;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HelloActivity extends Activity {

    private static final int CONTACTS_LOADER_ID = 1;

    private static final String[] CONTACTS_PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Email.ADDRESS
    };

    private static final String CONTACTS_SELECTION =
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?"
            + " OR " + ContactsContract.CommonDataKinds.Email.ADDRESS + " LIKE ?";
    private String[] mSelectionArgs = {"", ""};

    private AutoCompleteTextView mAutoCompleteTextView;
    private SimpleCursorAdapter mCursorAdapter;

    private LoaderManager.LoaderCallbacks<Cursor> mContactsLoaderCallback;

    private String mSearchString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
                mSelectionArgs[0] = "%" + mSearchString + "%";
                mSelectionArgs[1] = "%" + mSearchString + "%";
                return new CursorLoader(
                        HelloActivity.this,
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        CONTACTS_PROJECTION,
                        CONTACTS_SELECTION,
                        mSelectionArgs,
                        null
                );
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                // Put the result Cursor in the adapter for the ListView
                mCursorAdapter.swapCursor(cursor);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                // Delete the reference to the existing Cursor
                mCursorAdapter.swapCursor(null);
            }
        };

        getLoaderManager().initLoader(CONTACTS_LOADER_ID, null, mContactsLoaderCallback);

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view);

        mCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY},
                new int[]{android.R.id.text1},
                0);
        mCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                int displayNamePrimaryIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
                int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);

                String result = "";
                if (displayNamePrimaryIndex >= 0) {
                    result += cursor.getString(displayNamePrimaryIndex);
                }
                if (emailIndex >= 0) {
                    result += " <" + cursor.getString(emailIndex) + ">";
                }

                ((TextView) view).setText(result);
                return true;
            }
        });

        mAutoCompleteTextView.setAdapter(mCursorAdapter);

        mAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSearchString = String.valueOf(s);
                if (!TextUtils.isEmpty(mSearchString)) {
                    getLoaderManager().restartLoader(0, null, mContactsLoaderCallback);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
