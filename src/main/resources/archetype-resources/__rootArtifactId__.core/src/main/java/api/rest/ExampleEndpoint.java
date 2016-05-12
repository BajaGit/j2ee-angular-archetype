#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.rest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ${package}.data.accessors.ExampleAccessor;
import ${package}.data.filters.ExampleFilter;
import ${package}.data.models.Example;

@JsonIgnoreProperties(ignoreUnknown = true)
@Path("/example")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExampleEndpoint {
	
	@Path("")
	@GET
	public List<Example> get(@Context UriInfo info){
		MultivaluedMap<String, String> queryParams = info.getQueryParameters();
		Set<String> keys = queryParams.keySet();
		if ( keys != null && keys.size() > 0){
			//enable Filtered List queries
			return new ExampleFilter().filter(keys, queryParams);
		} else {
			// allow plan ALL queries
			System.out.println("has no queryParams");
			return new ExampleAccessor().getAll();
		}
		
	}
	
	@Path("/{name}")
	@GET
	public Example get(@PathParam("name") String name){
		Optional<Example> app = new ExampleAccessor().getAll().stream().filter( a -> a.getName().equals(name)).findFirst();
		if ( app.isPresent()){
			return app.get();
		}
		return null;
	}
	
	
	@POST
	public Example post(Example ex){
		// be sure to check submitted values first, the world is cruel :)
		ex.crup();
		return ex;
	}

}
