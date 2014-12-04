package ro.tudorluca.gpstracks.webapi.service.impl

import ro.tudorluca.gpstracks.webapi.service.PositionService
import ro.tudorluca.gpstracks.webapi.core.Position
import java.util.Date
import ro.tudorluca.gpstracks.webapi.db.PositionRepository
import javax.inject.Inject

/**
 * Created by Tudor Luca on 04/12/14.
 */
public class PositionServiceImpl [Inject](val positionRepository: PositionRepository) : PositionService {

    override fun getOne(id: Long): Position? = positionRepository.findById(id)

    override fun getAll(): List<Position> = positionRepository.findAll()

    override fun getAllInIntervalByUserId(start: Date, end: Date, userId: Long) =
            positionRepository.findAllInIntervalByUserId(start, end, userId)

    override fun savePosition(position: Position): Position {
        val id = positionRepository.save(position)
        val savedPosition = getOne(id)
        if (savedPosition == null) {
            throw RuntimeException("Position $position could not be saved")
        } else {
            return savedPosition
        }
    }

    override fun deletePosition(id: Long) = positionRepository.delete(id)

    override fun deletePositionsInIntervalByUserId(start: Date, end: Date, userId: Long) =
            positionRepository.deletePositionsInIntervalByUserId(start, end, userId)
}