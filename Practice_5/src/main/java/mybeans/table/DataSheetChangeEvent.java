package mybeans.table;

import java.util.EventObject;

public class DataSheetChangeEvent extends EventObject {

    public static final long serialVersionUID = 1L;

    public DataSheetChangeEvent(Object source) {
        super(source);
    }
}
