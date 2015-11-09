package ua.kiev.makson.work_in_site.page;

public class CreateString {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "Дом с призраками / House Hunting (Эрик Херт)";
        int x = str.indexOf('/');
        System.out.println(x);
        String strFirst = str.substring(0, x);
        System.out.println(strFirst);
        String strSecond = str.substring(x + 1, str.length());
        System.out.println(strSecond);

    }

}
