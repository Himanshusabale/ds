import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class CalculatorClient {
    public static void main(String[] args) throws Exception {
        // URL of the WSDL (Web Service Description Language)
        URL url = new URL("http://localhost:8080/ws/calculator?wsdl");

        // Service details: (Namespace URI, Service Name)
        QName qname = new QName("http://default_package/", "CalculatorImplService");

        Service service = Service.create(url, qname);
        Calculator calc = service.getPort(Calculator.class);

        System.out.println("Invoking Web Service...");
        System.out.println("Addition (10 + 5): " + calc.add(10, 5));
        System.out.println("Subtraction (10 - 5): " + calc.subtract(10, 5));
    }
}