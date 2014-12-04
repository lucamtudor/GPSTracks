package ro.tudorluca.gpstracks.webapi;

/**
 * Created by Tudor Luca on 03/12/14.
 */
public class GPSTracksApplication extends BaseApplication {

    public static void main(String[] args) throws Exception {
        new GPSTracksApplication().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }
}
