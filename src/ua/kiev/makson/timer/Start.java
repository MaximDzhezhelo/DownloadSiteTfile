package ua.kiev.makson.timer;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;

import ua.kiev.makson.work_in_site.requests.GeneralHttpClient;
import ua.kiev.makson.work_in_site.requests.RequestHelper;

public class Start {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        GeneralHttpClient genClient = new GeneralHttpClient();
        RequestHelper requestHelper = new RequestHelper();
        Map<String, String> header = requestHelper.getInitialRequestHeader();
        File file = new File("delete");
        ;

        if (!file.exists()) {
            file.createNewFile();

        }
        GetTask getTask = new GetTask("http://tfile.me/", header, genClient,
                file);
        getTask.doGet();
        int statusLine = getTask.getStatusLine();
        Timer timer = new Timer();
        int a = 0;
        if (statusLine == 200) {
            System.out.println("");           
            timer.schedule(getTask, 5000);
            if(a==3){

                timer.cancel();
                timer.purge();
            }
            a++;
        }
        System.out.println(a);
    }
}
