public class BlockMiner {
    static final String TRANSACTION = "TRANSACTIONS/TOKENS HERE";
    static final String PREV_HASH = "0".repeat(64);
    static final int BLOCK_NO = 1;
    static final int ZEROES = 8;

    public static void main(String[] args) throws InterruptedException {
        int HMILL = 0;

        long startTime = System.currentTimeMillis();

        for(;;){
            PublicVars vars = new PublicVars();
            if(vars.THREAD_TERMINATE){
                break;
            }
            else{
                System.out.println("Mining Block....");

                Runnable miner1 = new MinerThread(TRANSACTION, BLOCK_NO, ZEROES, PREV_HASH, new double[]{HMILL * 100000000, (HMILL + 1) * 100000000});
                Thread thread1 = new Thread(miner1);

                Runnable miner2 = new MinerThread(TRANSACTION, BLOCK_NO, ZEROES, PREV_HASH, new double[]{(HMILL + 1) * 100000000, (HMILL + 2) * 100000000});
                Thread thread2 = new Thread(miner2);

                Runnable miner3 = new MinerThread(TRANSACTION, BLOCK_NO, ZEROES, PREV_HASH, new double[]{(HMILL + 2) * 100000000, (HMILL + 3) * 100000000});
                Thread thread3 = new Thread(miner3);

                Runnable miner4 = new MinerThread(TRANSACTION, BLOCK_NO, ZEROES, PREV_HASH, new double[]{(HMILL + 3) * 100000000, (HMILL + 4) * 100000000});
                Thread thread4 = new Thread(miner4);

                Runnable miner5 = new MinerThread(TRANSACTION, BLOCK_NO, ZEROES, PREV_HASH, new double[]{(HMILL + 4) * 100000000, (HMILL + 5) * 100000000});
                Thread thread5 = new Thread(miner5);

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
                thread5.start();

                thread1.join();
                thread2.join();
                thread3.join();
                thread4.join();
                thread5.join();

                HMILL += 5;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Block Created in " + ((float) (endTime - startTime) / 1000.00) + " seconds");
    }
}
