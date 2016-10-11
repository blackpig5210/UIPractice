package com.blackpig.www.dialog;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.blackpig.www.dialog.LoginDialogFragment.LoginInputListener;

public class MainActivity extends AppCompatActivity implements LoginInputListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EditNameDialogFragment dialogFragment = new EditNameDialogFragment();
//        dialogFragment.show(getFragmentManager(),"EidtNameDialog");

//        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
//        loginDialogFragment.show(getFragmentManager(), "loginDialog");

        EditNameDialogFragment dialogFragment = new EditNameDialogFragment();

        boolean mIsLagerLayout = getResources().getBoolean(R.bool.large_layout);
        Log.i("TAG", mIsLagerLayout + "");
        if (mIsLagerLayout) {
            dialogFragment.show(getFragmentManager(),"EidtNameDialog");
        } else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            // For a little polish, specify a transition animation
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
           // transaction.replace(R.id.id_ly, dialogFragment);
        }
    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this,username + password, Toast.LENGTH_SHORT).show();
    }
}
