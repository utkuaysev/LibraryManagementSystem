package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = 111828999272875134L;

    List<CheckoutRecordEntry> checkoutRecordEntryList;


    public CheckoutRecord() {
        checkoutRecordEntryList = new ArrayList<>();
    }
    public void addEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate){
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(bookCopy, checkoutDate, dueDate, this);
        checkoutRecordEntryList.add(checkoutRecordEntry);
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntryList() {
        return checkoutRecordEntryList;
    }

}
