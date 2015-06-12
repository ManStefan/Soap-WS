import org.reficio.ws.SoapContext;
import org.reficio.ws.builder.SoapBuilder;
import org.reficio.ws.builder.SoapOperation;
import org.reficio.ws.builder.core.Wsdl;
import org.reficio.ws.client.core.SoapClient;

import java.io.*;
import java.util.*;
import javax.xml.namespace.QName;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerException;


import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        //weatherWSDL();
        //localWSDL();
        //currency();
        //bank();
    }

    public static void weatherWSDL(){
        Wsdl wsdl = Wsdl.parse("http://wsf.cdyne.com/WeatherWS/Weather.asmx?WSDL");

        SoapBuilder builder = wsdl.binding()
                .localPart("WeatherSoap12")
                .find();
        SoapOperation operation = builder.operation()
                .name("GetCityForecastByZIP")
                .find();

        SoapContext context = new SoapContext(false, false, false, true, true, new HashSet<QName>(), new Provider());

        String request = builder.buildInputMessage(operation, context);

        SoapClient client = SoapClient.builder()
                .endpointUri("http://wsf.cdyne.com/WeatherWS/Weather.asmx")
                .build();
        String response = client.post(request);

        System.out.println(request);
        System.out.println(response);
    }

    public static void localWSDL(){
        Wsdl wsdl = Wsdl.parse("http://localhost:8080/services/HelloWorld?wsdl");

        SoapBuilder builder = wsdl.binding()
                .localPart("HelloWorldPortBinding")
                .find();
        SoapOperation operation = builder.operation()
                .name("sayHelloWorldFrom")
                .find();

        SoapContext context = new SoapContext(true, false, false, true, true, new HashSet<QName>(), new Provider());
        String request = builder.buildInputMessage(operation, context);

        SoapClient client = SoapClient.builder()
                .endpointUri("http://localhost:8080/services/HelloWorld")
                .build();
        String response = client.post(request);

        System.out.println(request);
        System.out.println(response);
    }

    public static void currency(){
        Wsdl wsdl = Wsdl.parse("http://www.webservicex.net/CurrencyConvertor.asmx?WSDL");

        SoapBuilder builder = wsdl.binding()
                .localPart("CurrencyConvertorSoap12")
                .find();
        SoapOperation operation = builder.operation()
                .name("ConversionRate")
                .find();

        SoapContext context = new SoapContext(true, false, false, true, true, new HashSet<QName>(), new Provider());
        String request = builder.buildInputMessage(operation, context);

        SoapClient client = SoapClient.builder()
                .endpointUri("http://www.webservicex.net/CurrencyConvertor.asmx")
                .build();
        String response = client.post(request);

        System.out.println(request);
        System.out.println(response);
    }

    public static void bank(){
        Wsdl wsdl = Wsdl.parse("http://www.thomas-bayer.com/axis2/services/BLZService?wsdl");

        SoapBuilder builder = wsdl.binding()
                .localPart("BLZServiceSOAP12Binding")
                .find();
        SoapOperation operation = builder.operation()
                .name("getBank")
                .find();

        SoapContext context = new SoapContext(true, false, false, true, true, new HashSet<QName>(), new Provider());
        String request = builder.buildInputMessage(operation, context);

        SoapClient client = SoapClient.builder()
                .endpointUri("http://www.thomas-bayer.com/axis2/services/BLZService")
                .build();
        String response = client.post(request);

        System.out.println(request);
        System.out.println(response);
    }

    public static List<String> getBindings(String wsdlUrl){
        Wsdl wsdl = Wsdl.parse(wsdlUrl);

        List<String> bindings = new ArrayList<String>();
        for (QName binding : wsdl.getBindings()){
            bindings.add(binding.getLocalPart());
        }

        return bindings;
    }

    public static List<String> getOperationsForBinding(String wsdlUrl, String binding){
        Wsdl wsdl = Wsdl.parse(wsdlUrl);

        SoapBuilder builder = wsdl.binding().localPart(binding).find();

        List<String> operations = new ArrayList<String>();
        for (SoapOperation soapOperation : builder.getOperations()){
            operations.add(soapOperation.getOperationName());
        }

        return operations;
    }

}
