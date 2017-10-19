package com.flickerdemo.screen.util;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class PopupManager {
    private final Context mContext;

    private final Map<String, ProgressDialog> mProgressDialogs = new HashMap<>();

    public PopupManager(final Context pContext) {
        this.mContext = pContext;
    }

    public void showProgress(final String pTag, final String pMessage) {
        ProgressDialog progressDialog = new ProgressDialog(this.mContext);
        progressDialog.setMessage(pMessage);
        progressDialog.show();

        this.mProgressDialogs.put(pTag, progressDialog);
    }

    public boolean dismissProgress(final String pTag) {
        final ProgressDialog progressDialog = this.mProgressDialogs.remove(pTag);
        if (progressDialog == null) {
            return false;
        } else {
            progressDialog.dismiss();
            return true;
        }
    }
}