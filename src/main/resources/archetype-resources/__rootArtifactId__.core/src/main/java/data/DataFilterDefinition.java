#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import javax.ws.rs.core.MultivaluedMap;

public interface DataFilterDefinition<T> extends BiFunction<Set<String>, MultivaluedMap<String, String>, List<T>>{
	
	
	default List<T> filter(Set<String> keys, MultivaluedMap<String, String> queryMap){
		return apply(keys, queryMap);
	}
}
