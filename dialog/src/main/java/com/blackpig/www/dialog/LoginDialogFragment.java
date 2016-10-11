package com.blackpig.www.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by black on 2016/10/10/0010.
 */

public class LoginDialogFragment extends DialogFragment {
    private EditText username;
    private EditText password;
    public interface LoginInputListener{
        void onLoginInputComplete(String username, String password);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_login,null);
        username = (EditText) view.findViewById(R.id.id_txt_username);
        password = (EditText) view.findViewById(R.id.id_txt_password);
        builder.setView(view)
                .setPositiveButton("Sign in",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginInputListener listener = (LoginInputListener) getActivity();
                                listener.onLoginInputComplete(username.getText().toString(),
                                        password.getText().toString());
                            }
                        })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }
}
