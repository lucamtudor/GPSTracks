package ro.tudorluca.gpstracks.webapi

import io.dropwizard.setup.Environment
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import com.hubspot.dropwizard.guice.GuiceBundle
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.dropwizard.jersey.jackson.JacksonMessageBodyProvider

/**
 * Created by Tudor Luca on 03/12/14.
 */
public open class BaseApplication : Application<GPSTracksConfiguration>() {

    override fun initialize(bootstrap: Bootstrap<GPSTracksConfiguration>) {
        val guiceBundle = GuiceBundle.newBuilder<GPSTracksConfiguration>()
                .addModule(GPSTracksModule())
                .enableAutoConfig(javaClass<BaseApplication>().getPackage().getName())
                .setConfigClass(javaClass<GPSTracksConfiguration>())
                .build()

        bootstrap.addBundle(guiceBundle)
    }

    override fun run(conf: GPSTracksConfiguration, env: Environment) {
        val objectMapper = ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)

        env.jersey().register(JacksonMessageBodyProvider(objectMapper, env.getValidator()))
    }
}

