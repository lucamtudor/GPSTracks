package ro.tudorluca.gpstracks.webapi.db

import org.skife.jdbi.v2.tweak.ResultSetMapper
import ro.tudorluca.gpstracks.webapi.core.Position
import java.sql.ResultSet
import org.skife.jdbi.v2.StatementContext


/**
 * Created by Tudor Luca on 03/12/14.
 */
public class PositionMapper : ResultSetMapper<Position> {

    override fun map(index: Int, r: ResultSet, ctx: StatementContext): Position {
        return Position.init(
                id = r.getLong(PositionRepository.COL_ID),
                dateCreated = r.getDate(PositionRepository.COL_DATE_CREATED),
                userId = r.getLong(PositionRepository.COL_USER_ID),
                latitude = r.getString(PositionRepository.COL_LATITUDE),
                longitude = r.getString(PositionRepository.COL_LONGITUDE),
                date = r.getDate(PositionRepository.COL_DATE))
    }
}