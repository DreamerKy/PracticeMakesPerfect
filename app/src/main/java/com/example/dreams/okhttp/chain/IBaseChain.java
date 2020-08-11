package com.example.dreams.okhttp.chain;

/**
 * Created by likaiyu on 2020/1/11.
 */
public interface IBaseChain {

    void doChainAction(String needReturn,IBaseChain chain);

}
