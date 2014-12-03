package ro.tudorluca.gpstracks.webapi.core

import kotlin.properties.Delegates
import java.util.Date

/**
 * Created by Tudor Luca on 03/12/14.
 */
public class Position : BaseEntity() {

    class object {

        fun init(id: Long, dateCreated: Date, userId: Long, latitude: String, longitude: String, date: Date): Position {
            val position = Position()
            position.id = id;
            position.dateCreated = dateCreated
            position.userId = userId
            position.latitude = latitude
            position.longitude = longitude
            position.date = date

            return position
        }
    }

    public var userId: Long by Delegates.notNull()
    public var latitude: String by Delegates.notNull()
    public var longitude: String by Delegates.notNull()
    public var date: Date by Delegates.notNull()
}