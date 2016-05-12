#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.filters;

import java.util.stream.Collectors;

import ${package}.data.DataFilter;
import ${package}.data.DataFilterDefinition;
import ${package}.data.accessors.ExampleAccessor;
import ${package}.data.models.Example;

public class ExampleFilter extends DataFilter<Example> {

	private DataFilterDefinition<Example> defaultFilter = (k, qm) -> {
		String name = qm.getFirst("name");
		return new ExampleAccessor().getAll().stream()
					.filter( a -> a.getName().indexOf(name) > -1)
					.collect(Collectors.toList());
	};
	
	public ExampleFilter(){
		super.registerFilter("NameContains", this.defaultFilter);
		super.setRegisteredFilterAsDefault("NameContains");
	}
}
