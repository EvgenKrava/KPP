package mybeans.table;

import java.util.EventListener;

public interface DataSheetChangeListener extends EventListener {
    void dataChanged(DataSheetChangeEvent e);
}
