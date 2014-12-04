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

        val TABLE_NAME = "TrackGPS.Position"
        val COL_ID = "id"
        val COL_DATE_CREATED = "dateCreated"
        val COL_USER_ID = "userId"
        val COL_LATITUDE = "latitude"
        val COL_LONGITUDE = "longitude"
        val COL_DATE = "date"

        val SQL_INSERT = """
                    INSERT INTO $TABLE_NAME (
                        $COL_USER_ID,
                        $COL_LATITUDE,
                        $COL_LONGITUDE,
                        $COL_DATE
                    )
                    VALUES (
                        :pos.userId, :pos.latitude, :pos.longitude, :pos.date
                    );"""

        val SQL_UPDATE = """
                    UPDATE $TABLE_NAME
                    SET $COL_DATE_CREATED = :pos.dateCreated, $COL_USER_ID = :pos.userId,
                        $COL_LATITUDE = :pos.latitude, $COL_LONGITUDE = :pos.longitude, $COL_DATE = :pos.date
                    WHERE $COL_ID = :pos.id
                    """
    }

    SqlQuery("SELECT * FROM $TABLE_NAME WHERE $COL_ID = :it")
    fun findById(Bind id: Long): Position?

    SqlQuery("SELECT * FROM $TABLE_NAME")
    fun findAll(): List<Position>

    SqlQuery("SELECT * FROM $TABLE_NAME WHERE $COL_DATE >= :start AND $COL_DATE <= :end and $COL_USER_ID = :userId")
    fun findAllInIntervalByUserId(start: Date, end: Date, Bind("userId") userId: Long): List<Position>

    SqlUpdate(SQL_INSERT) GetGeneratedKeys
    fun create(BindBean("pos") position: Position): Long

    SqlUpdate(SQL_UPDATE)
    fun save(BindBean("pos") position: Position): Long

    SqlUpdate("DELETE FROM $TABLE_NAME WHERE $COL_ID = :it")
    fun delete(Bind id: Long)

    SqlUpdate("DELETE FROM $TABLE_NAME WHERE $COL_DATE >= :start AND $COL_DATE <= :end and $COL_USER_ID = :userId")
    fun deletePositionsInIntervalByUserId(Bind("start") start: Date, Bind("end") end: Date, Bind("userId") userId: Long)
}