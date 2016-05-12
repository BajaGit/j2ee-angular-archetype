#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import ${package}.data.DataSource;

@Entity
public class Example implements DataSource {

	@Id
	private String unid;
	
	private String name;

	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// create or update
	public void crup(){
		if ( this.unid.equals("@Id")){
			this.unid = UUID.randomUUID().toString();
		}
		DataSource.super.crup();
	}
	
}
