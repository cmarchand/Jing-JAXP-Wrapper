package com.jimetevenard.xml.jingWrapper;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;


public class AutoSchemaFactory extends SchemaFactory{

	private LSResourceResolver resourceResolver;
	private ErrorHandler errorHandler;

	private static final String SCH_15_NS = "http://www.ascc.net/xml/schematron";
	private static final String SCH_ISO_NS = "http://purl.oclc.org/dsdl/schematron";
	private static final String NVDL_NS = "http://purl.oclc.org/dsdl/nvdl/ns/structure/1.0";
	
	private static final RuntimeException ONE_SCHEMA_ONLY = new UnsupportedOperationException("This implementation needs a (one) schema");

	@Override
	public boolean isSchemaLanguageSupported(String schemaLanguage) {
		return schemaLanguage.equals(SCH_15_NS)
				|| schemaLanguage.equals(SCH_ISO_NS)
				|| schemaLanguage.equals(NVDL_NS);
	}

	@Override
	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		
	}

	@Override
	public ErrorHandler getErrorHandler() {
		return this.errorHandler;
	}

	@Override
	public void setResourceResolver(LSResourceResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
		
	}

	@Override
	public LSResourceResolver getResourceResolver() {
		return this.resourceResolver;
	}

	@Override
	public Schema newSchema(Source[] schemas) throws SAXException {
		if(schemas != null && schemas.length == 1){
			return new AutoSchema(schemas[0], this);
		} else {
			throw ONE_SCHEMA_ONLY;
		}
	}

	@Override
	public Schema newSchema() throws SAXException {
		throw ONE_SCHEMA_ONLY;
	}
	
	

}
