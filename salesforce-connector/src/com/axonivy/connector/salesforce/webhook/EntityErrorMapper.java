package com.axonivy.connector.salesforce.webhook;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class EntityErrorMapper
     implements ExceptionMapper<Exception> {

   public Response toResponse(Exception e) {
      return Response.status(Response.Status.EXPECTATION_FAILED).build();
   }
}