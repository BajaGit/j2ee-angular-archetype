#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

public abstract class DataFilter<T> {

	private Map<String, DataFilterDefinition<T>> registeredFilters;
	private DataFilterDefinition<T> defaultFilter;
	private List<T> result;
	
	public DataFilter(){
		this.registeredFilters = new HashMap<String, DataFilterDefinition<T>>();
	}
	
	public void registerFilter(String name, DataFilterDefinition<T> filter){
		this.registeredFilters.put(name, filter);
	}
	
	public boolean hasDefault(){
		return this.defaultFilter != null;
	}
	
	public boolean setRegisteredFilterAsDefault(String registeredName){
		Optional.ofNullable(this.registeredFilters.get(registeredName)).ifPresent(f -> this.defaultFilter = f);
		return hasDefault();
	}
	
	public List<T> filter(Set<String> paramKeys, MultivaluedMap<String, String> queryParams){
		result = null;
		if ( paramKeys.contains("filter") ){
			Optional.ofNullable(this.registeredFilters.get(queryParams.getFirst("filter")))
				.ifPresent( f -> result = f.filter(paramKeys, queryParams));
		} else {
			Optional.ofNullable(this.defaultFilter)
				.ifPresent( f -> result = f.filter(paramKeys, queryParams));
		}
		return result;
	}
	
}
