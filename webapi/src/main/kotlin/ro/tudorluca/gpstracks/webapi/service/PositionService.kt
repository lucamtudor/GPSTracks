package ro.tudorluca.gpstracks.webapi.service

import ro.tudorluca.gpstracks.webapi.core.Position
import java.util.Date

/**
 * Created by Tudor Luca on 04/12/14.
 */
public trait PositionService {

    fun getOne(id: Long): Position?

    fun getAll(): List<Position>

    fun getAllInIntervalByUserId(start: Date, end: Date, userId: Long): List<Position>

    fun savePosition(position: Position): Position

    fun deletePosition(id: Long)

    fun deletePositionsInIntervalByUserId(start: Date, end: Date, userId: Long)
}