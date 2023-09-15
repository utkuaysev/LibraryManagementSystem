package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = 111828999272875134L;

    List<CheckoutRecordEntry> checkoutRecordEntryList;


    public CheckoutRecord() {
        checkoutRecordEntryList = new ArrayList<>();
    }
    public void addEntry(CheckoutRecordEntry checkoutRecordEntry){
        checkoutRecordEntryList.add(checkoutRecordEntry);
        checkoutRecordEntry.setCheckoutRecord(this);
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntryList() {
        return checkoutRecordEntryList;
    }

}
