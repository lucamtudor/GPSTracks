package ro.tudorluca.gpstracks.webapi.resource

import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.core.UriInfo
import javax.ws.rs.POST
import javax.ws.rs.GET
import ro.tudorluca.gpstracks.webapi.core.Position
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Response
import java.util.Date
import javax.inject.Inject
import javax.validation.Validator
import javax.ws.rs.PUT
import ro.tudorluca.gpstracks.webapi.service.PositionService
import javax.ws.rs.core.Response.Status

/**
 * Created by Tudor Luca on 03/12/14.
 */
Path("/position")
Consumes(MediaType.APPLICATION_JSON)
Produces(MediaType.APPLICATION_JSON)
public class PositionResource [Inject](val positionService: PositionService) {

    Inject var uriInfo: UriInfo? = null

    Inject var validator: Validator? = null

    POST
    fun add(position: Position): Response {
        val savedPosition = positionService.savePosition(position)
        val location = uriInfo!!.getAbsolutePathBuilder().path(savedPosition.id.toString()).build()
        return Response.created(location).entity(savedPosition).build()
    }

    PUT
    Path("/{id}")
    fun update(PathParam("id") id: Long, position: Position): Response {
        val originalPosition = positionService.getOne(id)
        if (originalPosition == null || !id.equals(position.id)) {
            return Response.status(Status.BAD_REQUEST).build()
        }

        positionService.savePosition(position)
        return Response.status(Status.NO_CONTENT).build()
    }

    GET
    fun getAllPositions(): Response = Response.ok(positionService.getAll()).build()

    GET
    Path("/{id}")
    fun getPositionById(PathParam("id") id: Long): Response {
        val position = positionService.getOne(id)
        if (position != null) {
            return Response.ok(position).build()
        }

        return Response.status(Status.NOT_FOUND).build()
    }

    GET
    Path("/interval")
    fun getAllPositionsForUserInInterval(QueryParam("userId") userId: Long,
                                         QueryParam("start") start: Date,
                                         QueryParam("end") end: Date): Response =
            Response.ok(positionService.getAllInIntervalByUserId(start, end, userId)).build()
}