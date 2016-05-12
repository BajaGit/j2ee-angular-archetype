#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.accessors;

import ${package}.data.DataAccessor;
import ${package}.data.models.Example;

public class ExampleAccessor extends DataAccessor<Example> {

	public ExampleAccessor() {
		super(Example.class);
	}

}
