package com.axonivy.connector.salesforce;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.oas.annotations.Hidden;

@Path("salesforceMock")
@PermitAll
@Hidden
public class SalesforceMock {

	@GET
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOpps(@QueryParam("q") String q) {
		return Response.status(200).entity(load("json/oppLists.json")).build();
	}

	@POST
	@Path("sobjects/Opportunity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewOpp(JsonNode jsonNode) {
		return Response.status(201).entity(load("json/createNewOppResponse.json")).build();
	}

	@PATCH
	@Path("sobjects/Opportunity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateOpp(@PathParam("id") String id, JsonNode jsonNode) {
		return Response.status(204).entity("").build();
	}

	@GET
	@Path("sobjects/Account/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id") String id) {
		return Response.status(200).entity(load("json/account.json")).build();
	}

	@GET
	@Path("sobjects/Opportunity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpportunity(@PathParam("id") String id) {
		return Response.status(200).entity(load("json/opportunity.json")).build();
	}

	@DELETE
	@Path("sobjects/Opportunity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOpportunity(@PathParam("id") String id) {
		return Response.status(204).entity("").build();
	}

	private static String load(String path) {
		try (InputStream is = SalesforceMock.class.getResourceAsStream(path)) {
			return IOUtils.toString(is, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			throw new RuntimeException("Failed to read resource: " + path);
		}
	}

}
