import javax.xml.ws.Endpoint;

public class CalculatorPublisher {
    public static void main(String[] args) {
        // Publish the service at this URL
        String url = "http://localhost:8080/ws/calculator";
        Endpoint.publish(url, new CalculatorImpl());
        
        System.out.println("Calculator Web Service is running at: " + url);
    }
}