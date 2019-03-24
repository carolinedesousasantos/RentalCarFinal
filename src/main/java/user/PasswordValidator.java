package user;

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

}
