/*
 * T: same class that implements
 */
public interface UnambiguousPage<T> {
    /*
     * ensure(): should wait for an unique element of the page to ensure that
     * the current state of the driver is the unambiguous page itself.
     */
    T ensure();
}