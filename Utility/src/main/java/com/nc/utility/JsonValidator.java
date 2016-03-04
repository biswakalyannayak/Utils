package com.nc.utility;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.load.Dereferencing;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.nc.utility.model.ComplexAddressT2;

public class JsonValidator {

	public static final String JSON_V4_SCHEMA_IDENTIFIER = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_SCHEMA_IDENTIFIER_ELEMENT = "$schema";
    
	public static void main(String[] args) throws IOException, ProcessingException {
		
		
		
		ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(ComplexAddressT2.class, visitor);
        com.fasterxml.jackson.module.jsonSchema.JsonSchema schemaJ = visitor.finalSchema();
        JsonNode raddressSchemaNode = mapper.valueToTree(schemaJ);
        
        ComplexAddressT2 complex = new ComplexAddressT2();
        complex.setAddressLine1("line1");
        complex.setAddressLine2("line2");
        complex.setLandMark("landMark");
        complex.setPhone(new String[] {"fd","fdf"});
        System.err.println(mapper.writeValueAsString(complex));
        
		System.out.println(raddressSchemaNode.toString());
		
        final JsonNode good = Utils.loadResource("/address-good.json");
        final JsonNode bad = Utils.loadResource("/address-bad.json");

        
        final LoadingConfiguration cfg = LoadingConfiguration.newBuilder()
                .dereferencing(Dereferencing.INLINE).freeze();
            final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setLoadingConfiguration(cfg).freeze();

            final JsonSchema schema = factory.getJsonSchema(raddressSchemaNode);

        ProcessingReport report;

        report = schema.validate(good);
        System.out.println(report);

        report = schema.validate(bad);
        System.out.println(report);

	}

}
