package com.example.dreams.networkframework;

import java.io.InputStream;

/**
 * Created by likaiyu on 2019/10/28.
 */
interface CallBackListener {

    void onSuccess(InputStream inputStream);

    void onFailure();


}
