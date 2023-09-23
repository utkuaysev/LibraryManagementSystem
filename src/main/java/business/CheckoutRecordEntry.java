package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
    private static final long serialVersionUID = 111828999272875135L;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;

    private CheckoutRecord checkoutRecord;

    public CheckoutRecordEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate, CheckoutRecord checkoutRecord) {
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.checkoutRecord = checkoutRecord;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", bookCopy=" + bookCopy +
                ", checkoutRecord=" + checkoutRecord +
                '}';
    }
}

