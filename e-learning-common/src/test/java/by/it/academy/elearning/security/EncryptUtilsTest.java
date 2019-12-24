package by.it.academy.elearning.security;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EncryptUtilsTest {

    private static final String PASSWORD = "password";
    private static final String PASSWORD_HASH_256 = "57584e23bae1d08c3a8fde541b2fed8be563058831d930cb3a99b30753d067c8";
    private static final String PASSWORD_HASH_512 = "879d2b653902dcf5a2bb8863cafa30761b77073b91e049135b875b7d84800556c9eef2d567094ef0c8a18f81ea2d33b1a28d7969690e3d1542a404a2834a9173";
    private static final String SALT = "salt";
    private static final byte[] BYTES = new byte[]{-79, -87, 109};


    @Test
    public void getPass() {
        String pass = "jerry001";
        String salt = EncryptUtils.generateSaltString();
        System.out.println("salt = " + salt);
        System.out.println("pass = " + pass);

        String hash = EncryptUtils.getSHA256(pass, salt);
        System.out.println("hash " + hash);
    }

    @Test
    public void getSHA256() {
        String sha256 = EncryptUtils.getSHA256(PASSWORD, SALT);
        assertThat(sha256).isEqualTo(PASSWORD_HASH_256);
    }

    @Test
    public void getSHA512() {
        String sha512 = EncryptUtils.getSHA512(PASSWORD, SALT);
        assertThat(sha512).isEqualTo(PASSWORD_HASH_512);
    }

    @Test
    public void generateSalt() {
        byte[] bytes = EncryptUtils.generateSalt();
        assertThat(bytes).isNotEmpty().hasSize(EncryptUtils.SALT_SIZE);
    }

    @Test
    public void generateSaltStringRandom() {
        String salt1 = EncryptUtils.generateSaltString();
        String salt2 = EncryptUtils.generateSaltString();
        assertThat(salt1).isNotEqualTo(salt2);
    }

    @Test
    public void generateSaltRandom() {
        byte[] bytes1 = EncryptUtils.generateSalt();
        byte[] bytes2 = EncryptUtils.generateSalt();
        assertThat(bytes1).isNotEqualTo(bytes2);
    }


    @Test
    public void byteToString() {
        String byteToString = EncryptUtils.byteToString(BYTES);
        assertThat(byteToString).isEqualTo(SALT);
    }

    @Test
    public void stringToBytes() {
        byte[] bytes = EncryptUtils.stringToBytes(SALT);
        assertThat(bytes).isEqualTo(BYTES);
    }

    @Test
    public void stringToBytesAndBack() {
        byte[] bytes = EncryptUtils.stringToBytes(SALT);
        String result = EncryptUtils.byteToString(bytes);
        assertThat(result).isEqualTo(SALT);
    }


}
