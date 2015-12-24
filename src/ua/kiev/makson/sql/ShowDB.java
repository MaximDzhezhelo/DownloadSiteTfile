package ua.kiev.makson.sql;

public class ShowDB {

	public static void main(String[] args) {
		JavaSQL javaSQL = new JavaSQL();
		javaSQL.showAll();
		// System.out.println(javaSQL.searchVideoByName("Преисподняя мертвых"));
		// javaSQL.deleteVideoByID(05);
		System.out.println(javaSQL.searchVideoByName("Жажда крови"));

	}

}
