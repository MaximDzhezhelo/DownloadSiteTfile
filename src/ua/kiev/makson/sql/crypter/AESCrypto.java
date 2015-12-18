package ua.kiev.makson.sql.crypter;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCrypto {

	private Cipher ecipher;
	private Cipher dcipher;
	private MyKey myKey;
	private Key key;

	public AESCrypto() {
		myKey = new MyKey();
		key = (Key) myKey.generateKey();
		try {
			this.ecipher = Cipher.getInstance("AES");
			this.dcipher = Cipher.getInstance("AES");
			this.ecipher.init(Cipher.ENCRYPT_MODE, key);
			this.dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	public String encrypt(String plaintext) {
		try {
			return new BASE64Encoder().encode(ecipher.doFinal(plaintext.getBytes("UTF8")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("restriction")
	public String decrypt(String ciphertext) {
		try {
			return new String(dcipher.doFinal(new BASE64Decoder().decodeBuffer(ciphertext)), "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}