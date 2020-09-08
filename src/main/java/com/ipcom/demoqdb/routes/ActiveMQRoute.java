package com.ipcom.demoqdb.routes;

import javax.sql.DataSource;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class ActiveMQRoute extends RouteBuilder{

    @Autowired
    Environment environment;

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;


    @Override
    public void configure() throws Exception {

        onException(PSQLException.class).log(LoggingLevel.ERROR,"PSQLException in the route ${body}")
                .maximumRedeliveries(3).redeliveryDelay(3000).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.ERROR);
        
//        onException(MySQLSyntaxErrorException.class).log(LoggingLevel.ERROR,"MySQLSyntaxErrorException in the route ${body}")
//        .maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.ERROR);


        from("{{fromNewQueue}}")
        .log("Read Message from sqlQueue ${body}")
		.log("Read headers from sqlQueue ${headers}")
	.choice()
        .when(simple("${header.nombre} == 'ADD'"))
            .log("Es agregar")
            .to("{{toMysql}}")
        .when(simple("${header.nombre} == 'UPDATE'"))
            .log("Es actualizar")
            .to("{{toMysql}}")
        .otherwise()
            .log("No se encontro operacion");

        }
}
