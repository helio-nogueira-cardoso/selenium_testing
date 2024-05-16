package interfaces;
/*
 * T: same class that implements
 */


public interface UnambiguousPageInterface<T> {
    /*
     * ensure(): should wait for an unique element of the page to ensure that
     * the current state of the driver is the unambiguous page itself.
     */
    public abstract T ensure();
}