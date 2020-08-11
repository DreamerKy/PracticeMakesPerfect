package com.example.dreams.okhttp.chain;

/**
 * Created by likaiyu on 2020/1/11.
 */
public class FirstChain implements IBaseChain {
    @Override
    public void doChainAction(String needReturn, IBaseChain chain) {
        if("no".equals(needReturn)){
            System.out.println("拦截器已处理，不再往后面传递");
            return;
        }else{
            //Do Something...
            //继续下一个拦截器
//            chain.doChainAction(needReturn,c);
        }
    }
}
