package ua.kiev.makson.sql;

public class StartWork {

    public static void main(String[] args) {
        JavaSQL javaSQL = new JavaSQL();
//        javaSQL.showAll();
        javaSQL.deleteVideoByID(23);
        javaSQL.searchVideoByName("Макс ");

    }

}
