package br.com.exam.functional.component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import io.qameta.allure.Allure;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public interface LogBuffer {
    ByteArrayOutputStream byteArrayRequest = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayResponse = new ByteArrayOutputStream();
    PrintStream printStreamRequest = new PrintStream(byteArrayRequest, true);
    PrintStream printStreamResponse = new PrintStream(byteArrayResponse, true);
    RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(printStreamRequest);
    ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter(printStreamResponse);

    default void logRequest() {
        Allure.addAttachment("Request", byteArrayRequest.toString());
        printStreamRequest.flush();
        byteArrayRequest.reset();
    }
    default void logAll(){
        logRequest();
        logResponse();
    }
    default void logResponse() {
        Allure.addAttachment("Response", byteArrayResponse.toString());
        printStreamResponse.flush();
        byteArrayResponse.reset();
    }
}
