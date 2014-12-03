package ro.tudorluca.gpstracks.webapi

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid
import javax.validation.constraints.NotNull
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Tudor Luca on 03/12/14.
 */
public class GPSTracksConfiguration : Configuration() {

    Valid NotNull JsonProperty("database")
    public val dataSourceFactory: DataSourceFactory = DataSourceFactory()
}