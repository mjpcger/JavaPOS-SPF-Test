package SPF_Test;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import jpos.ItemDispenser;
import jpos.ItemDispenserConst;
import jpos.JposException;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * GUI control for ItemDispenser properties, methods and events.
 */
public class ItemDispenserController extends CommonController {
    public TextField AIC_itemCount;
    public TextField DI_numItem;
    public TextField RIC_numItem;
    public TextField SlotNumber;
    private ItemDispenser TheItemDispenser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheItemDispenser = (ItemDispenser) Control;
        TheItemDispenser.addDirectIOListener(this);
        TheItemDispenser.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new DispenserStatusUpdateValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("DispenserStatus", "", new DispenserStatusValues()));
        Properties.getItems().add(new PropertyTableRow("CapEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapIndividualSlotStatus", ""));
        Properties.getItems().add(new PropertyTableRow("CapJamSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNearEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("MaxSlots", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        updateGui();
    }

    public void adjustItemCount(ActionEvent actionEvent) {
        Values val = new IntValues();
        Integer itemCount = val.getInteger(AIC_itemCount.getText());
        Integer slotNumber = val.getInteger(SlotNumber.getText());
        if (validate(new Object[]{itemCount, "itemCount", slotNumber, "slotNumber"})) {
            try {
                TheItemDispenser.adjustItemCount(itemCount, slotNumber);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    class DispenseItem extends MethodProcessor {
        private int[] NumItem;
        int SlotNumber;

        DispenseItem(int numItem, int slotNumber) {
            super("DispenseItem");
            NumItem = new int[]{numItem};
            SlotNumber = slotNumber;
        }

        @Override
        void runIt() throws JposException {
            TheItemDispenser.dispenseItem(NumItem, SlotNumber);
        }

        @Override
        void finish() {
            DI_numItem.setText("" + NumItem[0]);
            super.finish();
        }
    }

    public void dispenseItem(ActionEvent actionEvent) {
        Values val = new IntValues();
        Integer numItem = val.getInteger(DI_numItem.getText());
        Integer slotNumber = val.getInteger(SlotNumber.getText());
        if (validate(new Object[]{numItem, "numItem", slotNumber, "slotNumber"}))
            new DispenseItem(numItem, slotNumber).start();
    }

    public void readItemCount(ActionEvent actionEvent) {
        Values val = new IntValues();
        Integer numItem = val.getInteger(RIC_numItem.getText());
        Integer slotNumber = val.getInteger(SlotNumber.getText());
        int[] arg = {(numItem == null ? 0 : numItem)};
        if (validate(new Object[]{slotNumber, "slotNumber"})) {
            try {
                TheItemDispenser.readItemCount(arg, slotNumber);
                RIC_numItem.setText("" + arg[0]);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
        }
        updateGui();
    }

    private class DispenserStatusValues extends Values {
        DispenserStatusValues() {
            ValueList = new Object[]{
                    ItemDispenserConst.ITEM_DS_OK, "DS_OK",
                    ItemDispenserConst.ITEM_DS_EMPTY, "DS_EMPTY",
                    ItemDispenserConst.ITEM_DS_NEAREMPTY, "DS_NEAREMPTY",
                    ItemDispenserConst.ITEM_DS_JAM, "DS_JAM"
            };
        }
    }

    private class DispenserStatusUpdateValues extends StatusUpdateValues {
        DispenserStatusUpdateValues() {
            Object[] add = new Object[]{
                    ItemDispenserConst.ITEM_SUE_OK, "SUE_OK",
                    ItemDispenserConst.ITEM_SUE_EMPTY, "SUE_EMPTY",
                    ItemDispenserConst.ITEM_SUE_NEAREMPTY, "SUE_NEAREMPTY",
                    ItemDispenserConst.ITEM_SUE_JAM, "SUE_JAM"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + add.length);
            System.arraycopy(add, 0, ValueList, ValueList.length - add.length, add.length);
        }
    }
}
