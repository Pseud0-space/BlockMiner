import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MinerThread implements Runnable {
    public static String Sha256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexDigest = new StringBuilder(2 * encodedHash.length);

            for (byte hash : encodedHash) {
                String hex = Integer.toHexString(0xff & hash);
                if (hex.length() == 1) {
                    hexDigest.append('0');
                }
                hexDigest.append(hex);
            }

            return hexDigest.toString();
        } catch (NoSuchAlgorithmException error) {
            System.out.println(error.toString());
        }
        return null;
    }

    public MinerThread(String transactions, int block_no, int zeros, String prev_hash, double[] limits) {
        double NONCE = limits[0];

        for (double i = limits[0]; i < limits[1]; i++) {
            PublicVars vars = new PublicVars();
            if(vars.THREAD_TERMINATE == true){
                break;
            }
            else{
                String temp_hash = Sha256(block_no + transactions + prev_hash + NONCE);

                assert temp_hash != null;
                if (temp_hash.substring(0, zeros).equals("0".repeat(zeros))) {
                    System.out.println("Nonce : " + String.format("%.0f", NONCE) + "    Hash : " + temp_hash);
                    vars.TerminateThread();
                    break;
                } else {
                    NONCE += 1;
                }
            }

        }
    }

    public void run() {
    }
}
