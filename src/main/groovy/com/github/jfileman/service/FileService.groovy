package com.github.jfileman.service

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

import com.github.jfileman.service.data.SingleFile

@Path('/')
class FileService
{
   @GET @Path('listFiles/{dir}')
   List<SingleFile> listFiles(@PathParam('dir') String dir)
   {
      return []
   }
}
