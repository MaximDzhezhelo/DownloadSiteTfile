package ua.kiev.makson.controller;

public class ConvertPassword {

    public String getPassword(char[] pass) {
        if (pass == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pass.length; i++) {
            buf.append(pass[i]);
        }
        return buf.toString();
    }
}
