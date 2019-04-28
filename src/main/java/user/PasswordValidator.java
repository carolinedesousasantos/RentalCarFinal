package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import servlets.CreateAccount;

public class PasswordValidator {

    static final String PASSWORD_FORMAT = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    public static String message;

    public static boolean validate(String password) {
        return (""+password).matches(PASSWORD_FORMAT);
    }

    public static String checkPasswords(String password1, String password2) {
        message = "";
        if ("".equals(password1) || "".equals(password2)) {
            return message = "Debes rellenar todos los campos obligatorios.";
        }
        if (!password1.equals(password2)) {
            return message = "Las contraseñas deben coincidir.";
        }
        return message;
    }
    
     public static String encryptPassword(String passwordToHash, byte[] salt)
    {
        String encryptedPassword = null;
        try {
            //SHA-256, SHA-384, SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedPassword;
    }
    
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[]{1,1};
       // sr.nextBytes(salt);
        return salt;
    }
    
       public static String encryptPassword( String password) {
        String passwordToHash = password;
        byte[] salt = null;
        try {
            salt = PasswordValidator.getSalt();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        String securePassword = PasswordValidator.encryptPassword(passwordToHash, salt);
        return securePassword;
    }

}
