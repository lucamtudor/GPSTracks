package ro.tudorluca.gpstracks.webapi

import io.dropwizard.setup.Environment
import io.dropwizard.jdbi.DBIFactory
import ro.tudorluca.gpstracks.webapi.resource.PositionResource
import ro.tudorluca.gpstracks.webapi.db.PositionRepository
import io.dropwizard.Application

/**
 * Created by Tudor Luca on 03/12/14.
 */
public open class BaseApplication : Application<GPSTracksConfiguration>() {

    override fun run(conf: GPSTracksConfiguration, env: Environment) {
        val dbi = DBIFactory().build(env, conf.dataSourceFactory, "db")
        env.jersey().register(PositionResource(dbi.onDemand(javaClass<PositionRepository>())))
    }
}

