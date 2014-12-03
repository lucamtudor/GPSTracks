package ro.tudorluca.gpstracks.webapi.resource

import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.core.Context
import javax.ws.rs.core.UriInfo
import javax.ws.rs.POST
import javax.ws.rs.GET
import ro.tudorluca.gpstracks.webapi.core.Position
import java.util.Date
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response
import ro.tudorluca.gpstracks.webapi.db.PositionRepository

/**
 * Created by Tudor Luca on 03/12/14.
 */
Path("/position")
Consumes(MediaType.APPLICATION_JSON)
Produces(MediaType.APPLICATION_JSON)
public class PositionResource(val repository: PositionRepository) {

    Context
    private var uriInfo: UriInfo? = null

    POST
    fun add(position: Position): Response {
        val id = repository.save(position)
        val location = uriInfo!!.getAbsolutePathBuilder().path(id.toString()).build()
        return Response.created(location).entity(repository.findById(id)).build()
    }

    GET
    fun getAllPositions(): Response {
        return Response.ok(repository.findAll()).build()
    }

    GET
    Path("/{id}")
    fun getPositionById(PathParam("id") id: Long): Response {
        val position = repository.findById(id)
        return Response.ok(position).build()
    }

    GET
    Path("/interval")
    fun getAllPositionsForUserInInterval(QueryParam("userId") userId: Long,
                                         QueryParam("start") start: Date,
                                         QueryParam("end") end: Date): Response {
        val positions = repository.findAllInIntervalByUserId(start, end, userId)
        return Response.ok(positions).build()
    }
}