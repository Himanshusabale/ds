import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Calculator {
    @WebMethod int add(int a, int b);
    @WebMethod int subtract(int a, int b);
}