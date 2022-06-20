// IMultipleProcessServiceInterface.aidl
package heven.holt.mvvm.aidl;

// Declare any non-default types here with import statements
import heven.holt.mvvm.aidl.IServiceListenerInterface;

interface IMultipleProcessServiceInterface {

    void addServiceListener(IServiceListenerInterface serviceListener);

    void initStartup();

    void clear();
}
