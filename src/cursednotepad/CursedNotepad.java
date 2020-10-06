package cursednotepad;

import java.io.UnsupportedEncodingException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import security.SecureRandom;

public class CursedNotepad extends MIDlet implements CommandListener {

    private ChoiceGroup options;
    private static final String[] ALGOS = {"AES (256-bit)", "ChaCha20"};
    private String test = "It was the best of times, it was the worst of times,"
         + "it was the age of wisdom, it was the age of foolishness,"
         + "it was the epoch of belief, it was the epoch of incredulity,"
         + "it was the season of Light, it was the season of Darkness,"
         + "it was the spring of hope, it was the winter of despair,"
         + "we had everything before us, we had...";
    private Display display;
    private TextField timeField;
    private TextField iterationsField;
    private long elapsedTime;
    byte[] key = new byte[32];

    public CursedNotepad() {
        initKey(key);
        Form form = new Form("AES / ChaCha20 speed test");
        iterationsField = new TextField("Number of rounds: ", "100", 7, TextField.NUMERIC);
        form.append(iterationsField);
        options = new ChoiceGroup("Choose the encryption algorithm: ", Choice.EXCLUSIVE, ALGOS, null);
        options.setSelectedIndex(0, true);
        form.append(options);
        timeField = new TextField("Total elapsed time[ms]:", "", 75, TextField.UNEDITABLE);
        form.append(timeField);
        Command run = new Command("Run test", Command.OK, 0);
        form.addCommand(run);
        form.setCommandListener(this);
        display = Display.getDisplay(this);
        display.setCurrent(form);
    }

    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
        // TODO Auto-generated method stub
    }

    protected void pauseApp() {
        // TODO Auto-generated method stub
    }

    protected void startApp() throws MIDletStateChangeException {
        // TODO Auto-generated method stub
    }

    private void initKey(byte[] key)
    {
        SecureRandom sr = SecureRandom.getInstance("SHA256PRNG");
        sr.nextBytes(key);
    }

    public void commandAction(Command c, Displayable s) {
        if (c.getCommandType() == Command.OK) {
            try {
                int iterations = Integer.parseInt(iterationsField.getString());
                int index = options.getSelectedIndex();
                runTimes(iterations, index, key);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected long runTimes(int times, int cipherType, byte[] key) throws UnsupportedEncodingException
    {
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (i<times) {
            CipherManager cipher = new CipherManager(cipherType);
            byte[] encrypted = runEncryption(key, cipher);
            byte[] decrypted = runDecryption(key, cipher, encrypted);
            String decryptedString = new String(decrypted, "UTF-8");
//            System.out.println(decryptedString);
            i++;
            }
        long stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        return elapsedTime;
    }

    protected void updateTime(long elapsedTime) {
        timeField.setString(Long.toString(elapsedTime));
    }

    protected byte[] runEncryption(byte[] key, CipherManager cipher) {
        byte[] ibEnc;
        try {
            ibEnc = test.getBytes("UTF-8");
            byte[] obEnc = new byte[ibEnc.length];
            cipher.encrypt(key, ibEnc, obEnc);
            return obEnc;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "oops".getBytes();
        }
    }

    protected byte[] runDecryption(byte[] key, CipherManager cipher, byte[] ibEnc) {
        byte[] obEnc = new byte[ibEnc.length];
        cipher.decrypt(key, ibEnc, obEnc);
        return obEnc;
    }
}

