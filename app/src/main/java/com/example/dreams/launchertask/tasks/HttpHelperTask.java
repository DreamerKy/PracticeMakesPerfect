package com.example.dreams.launchertask.tasks;

import com.example.dreams.launchertask.Task;
import com.example.dreams.net.HttpHelper;
import com.example.dreams.net.VolleyProcesser;

/**
 * Created by likaiyu on 2020/2/17.
 */
public class HttpHelperTask extends Task {

    @Override
    public void run() {
        HttpHelper.init(new VolleyProcesser(mContext));
    }
}
