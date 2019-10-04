package by.itacademy.jd2.validation;

public enum PasswordValidation {
    PASSWORD_VALIDATION;

    public boolean checkPassRepeatedPass(String pass, String repeatedPass){
        return pass.equals(repeatedPass);
    }
}
