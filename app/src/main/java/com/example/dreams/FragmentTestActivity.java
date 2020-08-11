package com.example.dreams;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.dreams.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_test);
        if (fragment == null) {
//            bundle.putParcelable("bitmap", Utils.getBitmap(this,"world.jpg"));
            Bundle bundle = new Bundle();
            byte[] data = new byte[1024 * 1010];
            bundle.putByteArray("data", data);
            fragment = SimpleFragment.newInstance();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.fragment_test, fragment).commitNow();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("onSaveInstanceState1");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState2");

    }
}
