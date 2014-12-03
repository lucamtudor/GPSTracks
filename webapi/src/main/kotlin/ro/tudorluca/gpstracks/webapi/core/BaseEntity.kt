package ro.tudorluca.gpstracks.webapi.core

import java.util.Date

/**
 * Created by Tudor Luca on 03/12/14.
 */
data public abstract class BaseEntity {

    public var id: Long? = null
    public var dateCreated: Date? = null
}