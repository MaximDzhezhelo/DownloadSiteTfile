package ua.kiev.makson.work_in_site.page;

import java.io.File;
import ua.kiev.makson.sql.JavaSQL;
import ua.kiev.makson.work_in_site.requests.RequesAssistant;

public class GeneralWorkInSite {
    private static JavaSQL javaSQL;
    private static WorkWithPage workWithPage;

    public static JavaSQL getJavaSQL() {
        return javaSQL;
    }

    public GeneralWorkInSite() {
        javaSQL = new JavaSQL();
        workWithPage = new WorkWithPage();
        createDB();
    }

    private void createDB() {
        File db = new File("collection.db");
        if (!db.exists()) {
            javaSQL.createSQL();
        }
    }

    public void parsingPage(RequesAssistant assistant) {
        workWithPage.parsingPage(javaSQL, assistant);
    }
}
