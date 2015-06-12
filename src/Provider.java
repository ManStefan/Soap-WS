import org.reficio.ws.SoapMultiValuesProvider;

import javax.xml.namespace.QName;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smanolache on 6/12/2015.
 */
public class Provider implements SoapMultiValuesProvider {
    @Override
    public Set<String> getMultiValues(QName qName) {


        Set<String> input = new HashSet<String>();

        if (qName.getLocalPart().equals("FromCurrency")){
            input.add("USD");
        }
        if (qName.getLocalPart().equals("ToCurrency")){
            input.add("EUR");
        }
        if (qName.getLocalPart().equals("blz")){
            input.add("10040061");
        }
        if (qName.getLocalPart().equals("ZIP")){
            input.add("10007");
        }

        return input;
    }
}
