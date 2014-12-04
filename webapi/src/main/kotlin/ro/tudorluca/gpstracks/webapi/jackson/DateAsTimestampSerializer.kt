package ro.tudorluca.gpstracks.webapi.jackson

import com.fasterxml.jackson.databind.JsonSerializer
import java.util.Date
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

/**
 * Created by Tudor Luca on 04/12/14.
 */
public class DateAsTimestampSerializer : JsonSerializer<Date>() {

    override fun serialize(value: Date?, jgen: JsonGenerator?, provider: SerializerProvider?) {
        jgen?.writeNumber(value?.getTime() ?: 0)
    }
}
