package ua.kiev.makson.sql.crypter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

public class MyKey {
    private String nameKey = "AES";
    private File SEKRET_KEY;
    private Key key;

    public Key generateKey() {
        if (createKey()) {
            return key;
        } else {
            readKey();
            return key;
        }
    }

    private boolean createKey() {
        try {
            SEKRET_KEY = new File(String.format("sekret%s.key", nameKey));
            if (!SEKRET_KEY.exists()) {
                KeyGenerator keygen = KeyGenerator.getInstance(nameKey);
                SecureRandom random = new SecureRandom();
                keygen.init(random);
                key = keygen.generateKey();
                try (ObjectOutputStream ous = new ObjectOutputStream(
                        new FileOutputStream((SEKRET_KEY)))) {
                    ous.writeObject(key);
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return false;

            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void readKey() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                SEKRET_KEY))) {
            key = (Key) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
