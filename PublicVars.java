public class PublicVars {
    static volatile boolean THREAD_TERMINATE = false;

    public void TerminateThread(){
        THREAD_TERMINATE = true;
    }

}
