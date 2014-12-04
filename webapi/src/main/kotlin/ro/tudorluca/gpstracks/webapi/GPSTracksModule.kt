package ro.tudorluca.gpstracks.webapi

import com.google.inject.AbstractModule
import org.skife.jdbi.v2.DBI
import ro.tudorluca.gpstracks.webapi.db.PositionRepository
import io.dropwizard.setup.Environment
import io.dropwizard.jdbi.DBIFactory
import com.google.inject.Provides
import javax.validation.Validator
import ro.tudorluca.gpstracks.webapi.service.PositionService
import ro.tudorluca.gpstracks.webapi.service.impl.PositionServiceImpl


/**
 * Created by Tudor Luca on 04/12/14.
 */
public class GPSTracksModule : AbstractModule() {

    override fun configure() {
        binder().bind(javaClass<PositionService>()).to(javaClass<PositionServiceImpl>())
    }

    Provides
    fun providesDBIFactory(conf: GPSTracksConfiguration, env: Environment): DBI = DBIFactory().build(env, conf.dataSourceFactory, "db")

    Provides
    fun providesPositionRepository(dbi: DBI): PositionRepository = dbi.open(javaClass<PositionRepository>())

    Provides
    fun providesDefaultValidator(env: Environment): Validator = env.getValidator()
}