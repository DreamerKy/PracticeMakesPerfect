// IPaymentService.aidl
package com.example.dreams;

import com.example.dreams.ITradeCallback;

// Declare any non-default types here with import statements

interface IPaymentService {

    void doBusiness(ITradeCallback listener);

//    void unregisterShotListener(IOnScreenShotListener listener);
}
