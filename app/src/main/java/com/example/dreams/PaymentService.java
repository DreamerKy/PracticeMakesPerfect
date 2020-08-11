package com.example.dreams;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by likaiyu on 2019/12/11.
 */
public class PaymentService extends Service {

    public class MyPaymentService extends IPaymentService.Stub{

        @Override
        public void doBusiness(ITradeCallback listener) throws RemoteException {
            Intent intent = new Intent(PaymentService.this,PracticeMakesPerfectActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            listener.onCallback("友刷返回");
        }
    }


//    private IPaymentService.Stub mBinder = new IPaymentService.Stub() {
//        @Override
//        public void doBusiness(ITradeCallback listener) throws RemoteException {
//            Intent intent = new Intent(PaymentService.this,PracticeMakesPerfectActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            listener.onCallback("友刷返回");
//        }
//    };

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("连接上服务");
        return new MyPaymentService();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
}
