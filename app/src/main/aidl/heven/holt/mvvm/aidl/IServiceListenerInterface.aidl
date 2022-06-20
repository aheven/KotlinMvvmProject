// IServiceListenerInterface.aidl
package heven.holt.mvvm.aidl;

// Declare any non-default types here with import statements

interface IServiceListenerInterface {

    void onCompleted(String result, long totalMainThreadCostTime);

}
