package cursednotepad;

import bc.crypto.others.DataLengthException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import security.SecureRandom;

public class CursedNotepad extends MIDlet implements CommandListener {

    private ChoiceGroup options;
    private static final String[] ALGOS = {"AES", "ChaCha20"};
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
            int iterations = Integer.parseInt(iterationsField.getString());
            int index = options.getSelectedIndex();
            switch (index) {
                case 0:
                    elapsedTime = runTimes(iterations, 0, key);
                    updateTime(elapsedTime);
                    break;
                case 1:
                    runTimes(iterations, 1, key);
                    updateTime(elapsedTime);
                    break;
            }
        }
    }

    protected long runTimes(int times, int method, byte[] key)
    {
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (i<times) {
            if (method == 0) {
                runAES(key);
            }
            if (method == 1) {
                runChaCha(key);
            }
            i++;
        }
        long stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        return elapsedTime;
    }

    protected void updateTime(long elapsedTime) {
        timeField.setString(Long.toString(elapsedTime));
    }

    protected void runAES(byte[] key) {
        try {
            AESManager aes = new AESManager();
            byte[] ibEnc = test.getBytes("UTF-8");
            byte[] obEnc = new byte[ibEnc.length];
            SecureRandom sr = SecureRandom.getInstance("SHA256PRNG");
            byte[] iv = new byte[16];
            sr.nextBytes(key);
            sr.nextBytes(iv);
            aes.encrypt(key, ibEnc, obEnc, iv, test);
            byte[] ibDec = new byte[obEnc.length];
            System.arraycopy(obEnc, 0, ibDec, 0, obEnc.length);
            byte[] obDec = new byte[ibEnc.length];
            aes.decrypt(key, ibDec, obDec, iv, test);
            String actual = new String(obDec, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (DataLengthException ex) {
            ex.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    protected void runChaCha(byte[] key) {
        try {
            ChaChaManager chaCha = new ChaChaManager();
            ByteArrayInputStream isEnc = new ByteArrayInputStream(test.getBytes("UTF-8"));
            ByteArrayOutputStream osEnc = new ByteArrayOutputStream();
            SecureRandom sr = SecureRandom.getInstance("SHA256PRNG");
            byte[] iv = new byte[8];
            sr.nextBytes(iv);
            try {
                chaCha.encrypt(isEnc, osEnc, key, iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            byte[] encrypted = osEnc.toByteArray();

            InputStream isDec = new ByteArrayInputStream(encrypted);
            ByteArrayOutputStream osDec = new ByteArrayOutputStream();
            try {
                chaCha.decrypt(isDec, osDec, key, iv);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            byte[] decrypted = osDec.toByteArray();
            String actual = new String(decrypted, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
}

