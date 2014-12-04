package ro.tudorluca.gpstracks.webapi.db

import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys
import org.skife.jdbi.v2.sqlobject.BindBean
import ro.tudorluca.gpstracks.webapi.core.Position
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.Bind
import java.util.Date
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

RegisterMapper(javaClass<PositionMapper>())
public trait PositionRepository {

    class object {

        val SQL_INSERT = """
                    INSERT INTO `TrackGPS`.`Position` (
                        `userId`,
                        `latitude`,
                        `longitude`,
                        `date`
                    )
                    VALUES (
                        :pos.userId, :pos.latitude, :pos.longitude, :pos.date
                    );"""

        val COL_ID = "id"
        val COL_DATE_CREATED = "dateCreated"
        val COL_USER_ID = "userId"
        val COL_LATITUDE = "latitude"
        val COL_LONGITUDE = "longitude"
        val COL_DATE = "date"
    }

    SqlQuery("SELECT * FROM Position WHERE id = :it")
    fun findById(Bind id: Long): Position?

    SqlQuery("SELECT * FROM Position")
    fun findAll(): List<Position>

    SqlQuery("SELECT * FROM Position WHERE date >= :start AND date <= :end and userId = :userId")
    fun findAllInIntervalByUserId(start: Date, end: Date, Bind("userId") userId: Long): List<Position>

    SqlUpdate(SQL_INSERT) GetGeneratedKeys
    fun save(BindBean("pos") position: Position): Long

    SqlUpdate("DELETE FROM Position WHERE id = :it")
    fun delete(Bind id: Long)

    SqlUpdate("DELETE FROM Position WHERE date >= :start AND date <= :end and userId = :userId")
    fun deletePositionsInIntervalByUserId(Bind("start") start: Date, Bind("end") end: Date, Bind("userId") userId: Long)
}