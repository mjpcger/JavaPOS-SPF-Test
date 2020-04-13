/*
 * Copyright 2020 Martin Conrad
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package SPF_Test;

import de.gmxhome.conrad.jpos.jpos_base.scale.DoPriceCalculating;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import jpos.*;
import jpos.events.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for Scale properties, methods and events.
 */
public class ScaleController extends CommonController {
    public CheckBox StatusNotify;
    public CheckBox ZeroValid;
    public TextField TareWeight;
    public TextField UnitPrice;
    public Label SUE_state;
    public Label WeightUnit;
    public TextField DT_data;
    public TextField DPC_weightData;
    public TextField DPC_tare;
    public TextField DPC_unitPrice;
    public TextField DPC_unitPriceX;
    public TextField DPC_weightUnitX;
    public TextField DPC_weightNumeratorX;
    public TextField DPC_weightDenominatorX;
    public TextField DPC_price;
    public ComboBox<String> DPC_timeout;
    public CheckBox FV_itemManualTare;
    public CheckBox FV_itemWeightedTare;
    public CheckBox FV_itemPercentTare;
    public CheckBox FV_itemUnitPrince;
    public CheckBox FV_freeze;
    public TextField RLWWT_weightData;
    public TextField RLWWT_tare;
    public ComboBox<String> RLWWT_timeout;
    public TextField RW_weightData;
    public ComboBox<String> RW_timeout;
    public ComboBox<String> SPCM_mode;
    public ComboBox<String> SST_mode;
    public TextField SST_data;
    public ComboBox<String> STP_priority;
    public TextField SUPWWU_unitPrice;
    public ComboBox<String> SUPWWU_weightUnit;
    public TextField SUPWWU_weightNumerator;
    public TextField SUPWWU_weightDenominator;
    public Label SalesPrice;
    private Scale TheScale;
    private PropertyTableRow SalesPriceRow;
    private PropertyTableRow ScaleLiveWeightRow;
    private PropertyTableRow StatusNotifyRow;
    private PropertyTableRow TareWeightRow;
    private PropertyTableRow UnitPriceRow;
    private PropertyTableRow WeightUnitRow;
    private PropertyTableRow ZeroValidRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheScale = (Scale) Control;
        TheScale.addDirectIOListener(this);
        TheScale.addStatusUpdateListener(this);
        TheScale.addDataListener(this);
        TheScale.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("CapDisplay", ""));
        Properties.getItems().add(new PropertyTableRow("CapDisplayText", ""));
        Properties.getItems().add(new PropertyTableRow("CapFreezeValue", ""));
        Properties.getItems().add(new PropertyTableRow("CapPriceCalculating", ""));
        Properties.getItems().add(new PropertyTableRow("CapReadLiveWeightWithTare", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetPriceCalculationMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetUnitPriceWithWeightUnit", ""));
        Properties.getItems().add(new PropertyTableRow("CapSpecialTare", ""));
        Properties.getItems().add(new PropertyTableRow("CapStatusUpdate", ""));
        Properties.getItems().add(new PropertyTableRow("CapTarePriority", ""));
        Properties.getItems().add(new PropertyTableRow("CapTareWeight", ""));
        Properties.getItems().add(new PropertyTableRow("CapZeroScale", ""));
        Properties.getItems().add(new PropertyTableRow("MaxDisplayTextChars", ""));
        Properties.getItems().add(new PropertyTableRow("MaximumWeight", ""));
        Properties.getItems().add(new PropertyTableRow("MinimumWeight", ""));
        Properties.getItems().add(SalesPriceRow = new PropertyTableRow("SalesPrice", ""));
        Properties.getItems().add(ScaleLiveWeightRow = new PropertyTableRow("ScaleLiveWeight", ""));
        Properties.getItems().add(StatusNotifyRow = new PropertyTableRow("StatusNotify", "", new StatusNotifyValues()));
        Properties.getItems().add(TareWeightRow = new PropertyTableRow("TareWeight", ""));
        Properties.getItems().add(UnitPriceRow = new PropertyTableRow("UnitPrice", ""));
        Properties.getItems().add(WeightUnitRow = new PropertyTableRow("WeightUnit", "", new WeightUnitValues()));
        Properties.getItems().add(ZeroValidRow = new PropertyTableRow("ZeroValid", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        CurrencyDigits.getItems().add(0);
        CurrencyDigits.getItems().add(1);
        CurrencyDigits.getItems().add(2);
        CurrencyDigits.getItems().add(3);
        CurrencyDigits.getItems().add(4);
        CurrencyDigits.setValue(2);
        setPropertyOnFocusLost(UnitPrice, "UnitPrice");
        setPropertyOnFocusLost(TareWeight, "TareWeight");
        String forever = new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER);
        DPC_timeout.getItems().add(forever);
        RLWWT_timeout.getItems().add(forever);
        RW_timeout.getItems().add(forever);
        SPCM_modeValues spmval = new SPCM_modeValues();
        SPCM_mode.getItems().add(spmval.getSymbol(ScaleConst.SCAL_PCM_PRICE_LABELING));
        SPCM_mode.getItems().add(spmval.getSymbol(ScaleConst.SCAL_PCM_SELF_SERVICE));
        SPCM_mode.getItems().add(spmval.getSymbol(ScaleConst.SCAL_PCM_OPERATOR));
        SST_modeValues stmval = new SST_modeValues();
        SST_mode.getItems().add(stmval.getSymbol(ScaleConst.SCAL_SST_DEFAULT));
        SST_mode.getItems().add(stmval.getSymbol(ScaleConst.SCAL_SST_MANUAL));
        SST_mode.getItems().add(stmval.getSymbol(ScaleConst.SCAL_SST_PERCENT));
        SST_mode.getItems().add(stmval.getSymbol(ScaleConst.SCAL_SST_WEIGHTED));
        setPropertyOnFocusLost(SST_data, "SST_data");
        STP_priorityValues tpval = new STP_priorityValues();
        STP_priority.getItems().add(tpval.getSymbol(ScaleConst.SCAL_STP_FIRST));
        STP_priority.getItems().add(tpval.getSymbol(ScaleConst.SCAL_STP_NONE));
        setPropertyOnFocusLost(SUPWWU_unitPrice, "SUPWWU_unitPrice");
        WeightUnitValues wuval = new WeightUnitValues();
        SUPWWU_weightUnit.getItems().add(wuval.getSymbol(ScaleConst.SCAL_WU_GRAM));
        SUPWWU_weightUnit.getItems().add(wuval.getSymbol(ScaleConst.SCAL_WU_KILOGRAM));
        SUPWWU_weightUnit.getItems().add(wuval.getSymbol(ScaleConst.SCAL_WU_OUNCE));
        SUPWWU_weightUnit.getItems().add(wuval.getSymbol(ScaleConst.SCAL_WU_POUND));
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            StatusNotify.setSelected(new StatusNotifyValues().getSymbol(ScaleConst.SCAL_SN_ENABLED).equals(StatusNotifyRow.getValue()));
            ZeroValid.setSelected(Boolean.TRUE.toString().equals(ZeroValidRow.getValue()));
            WeightUnit.setText(WeightUnitRow.getValue());
            string2Decimal(SalesPriceRow);
            string2Decimal(UnitPriceRow);
            UnitPrice.setText(UnitPriceRow.getValue());
            string2Weight(TareWeightRow);
            TareWeight.setText(TareWeightRow.getValue());
            string2Weight(ScaleLiveWeightRow);
            setSUPWWU_unitPrice(null);
            SalesPrice.setText(SalesPriceRow.getValue());
            InUpdateGui = false;
        }
    }

    @Override
    public void gotStatusUpdate(StatusUpdateEvent event) {
        super.gotStatusUpdate(event);
        SUE_stateValues sval = new SUE_stateValues();
        if (sval.getValue(sval.getSymbol(event.getStatus())) != null) {
            SUE_state.setText(sval.getSymbol(event.getStatus()));
        }
    }

    @Override
    public void gotData(DataEvent event) {
        super.gotData(event);
        try {
            if (CurrentWeighingMethod == DataEventSource.ReadWeight) {
                setWeightField(RW_weightData, event.getStatus());
            } else if (CurrentWeighingMethod == DataEventSource.ReadLiveWeightWithTare) {
                setWeightField(RLWWT_weightData, TheScale.getScaleLiveWeight());
                setWeightField(RLWWT_tare, TheScale.getTareWeight());
            } else if (CurrentWeighingMethod == DataEventSource.DoPriceCalculating) {
                setWeightField(DPC_weightData, TheScale.getScaleLiveWeight());
                setWeightField(DPC_tare, TheScale.getTareWeight());
                setDecimalTextField(DPC_price, TheScale.getSalesPrice());
                setDecimalTextField(DPC_unitPrice, TheScale.getUnitPrice());
            }
        } catch (Exception e) {
            if (e instanceof JposException)
                getFullErrorMessageAndPrintTrace(e);
            else
                JOptionPane.showMessageDialog(null, "No valid TareWeight value\n" + getFullErrorMessageAndPrintTrace(false, e));
        }
        CurrentWeighingMethod = DataEventSource.Inactive;
    }

    void string2Weight(PropertyTableRow row) {
        try {
            row.setValue(new BigDecimal(Long.parseLong(row.getValue()))
                    .scaleByPowerOfTen(-3).setScale(3).toString());
        } catch (Exception e) {
            row.setValue("");
        }
    }

    private void setWeightField(TextField field, int weight) {
        try {
            field.setText(new BigDecimal(weight).scaleByPowerOfTen(-3).toString());
        } catch (Exception e) {
            field.setText("");
        }
    }

    public void setTareWeight(ActionEvent event) {
        formatDecimalTextField(TareWeight, 3);
        try {
            TheScale.setTareWeight(new BigDecimal(TareWeight.getText()).multiply(new BigDecimal(1000)).intValue());
        } catch (Exception e) {
            if (e instanceof JposException)
                getFullErrorMessageAndPrintTrace(e);
            else
                JOptionPane.showMessageDialog(null, "No valid TareWeight value\n" + getFullErrorMessageAndPrintTrace(false, e));
        }
        updateGui();
    }

    public void setUnitPrice(ActionEvent event) {
        formatDecimalTextField(UnitPrice, CurrencyDigits.getValue());
        try {
            TheScale.setUnitPrice(new BigDecimal(UnitPrice.getText()).multiply(new BigDecimal(10000)).longValue());
        } catch (Exception e) {
            if (e instanceof JposException)
                getFullErrorMessageAndPrintTrace(e);
            else
                JOptionPane.showMessageDialog(null, "No valid UnitPrice value\n" + getFullErrorMessageAndPrintTrace(false, e));
        }
        updateGui();
    }

    public void setSST_data(ActionEvent event) {
        SST_modeValues mval = new SST_modeValues();
        int len = SST_data.getText().length();
        if (mval.getSymbol(ScaleConst.SCAL_SST_DEFAULT).equals(SST_mode.getValue())) {
            formatDecimalTextField(SST_data, 3);
        } else if (mval.getSymbol(ScaleConst.SCAL_SST_MANUAL).equals(SST_mode.getValue())) {
            formatDecimalTextField(SST_data, 3);
        } else if (mval.getSymbol(ScaleConst.SCAL_SST_PERCENT).equals(SST_mode.getValue())) {
            formatDecimalTextField(SST_data, 2);
        }
    }

    public void setSUPWWU_unitPrice(ActionEvent event) {
        formatDecimalTextField(SUPWWU_unitPrice, CurrencyDigits.getValue());
    }

    public void setStatusNotify(javafx.event.ActionEvent actionEvent) {
        try {
            TheScale.setStatusNotify(StatusNotify.isSelected() ? ScaleConst.SCAL_SN_ENABLED : ScaleConst.SCAL_SN_DISABLED);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setZeroValid(javafx.event.ActionEvent actionEvent) {
        try {
            TheScale.setZeroValid(ZeroValid.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void displayText(ActionEvent actionEvent) {
        try {
            TheScale.displayText(DT_data.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void clearReturnedValues(ActionEvent actionEvent) {
        DPC_price.setText("");
        DPC_tare.setText("");
        DPC_unitPrice.setText("");
        DPC_weightData.setText("");
        DPC_unitPriceX.setText("");
        DPC_weightNumeratorX.setText("");
        DPC_weightDenominatorX.setText("");
        RLWWT_tare.setText("");
        RLWWT_weightData.setText("");
        RW_weightData.setText("");
    }

    enum DataEventSource {
        DoPriceCalculating,
        ReadLiveWeightWithTare,
        ReadWeight,
        Inactive
    }

    DataEventSource CurrentWeighingMethod = DataEventSource.Inactive;

    class DoPriceCalculatingHandler extends MethodProcessor {
        private final int Timeout;
        private int[] WeightData = new int[1];
        private int[] Tare = new int[1];
        private long[] UnitPrice = new long[1];
        private long[] UnitPriceX = new long[1];
        private int[] WeightUnitX = new int[1];
        private int[] WeightNumeratorX = new int[1];
        private int[] WeightDenominatorX = new int[1];
        private long[] Price = new long[1];

        DoPriceCalculatingHandler(int timeout) {
            super("DoPriceCalculating");
            Timeout = timeout;
            CurrentWeighingMethod = DataEventSource.DoPriceCalculating;
        }
        @Override
        void runIt() throws JposException {
            TheScale.doPriceCalculating(WeightData, Tare, UnitPrice, UnitPriceX, WeightUnitX, WeightNumeratorX, WeightDenominatorX, Price, Timeout);
        }

        @Override
        void finish() {
            super.finish();
            if (!AsyncMode.isSelected()) {
                setWeightField(DPC_weightData, WeightData[0]);
                setWeightField(DPC_tare, Tare[0]);
                setDecimalTextField(DPC_unitPrice, UnitPrice[0]);
                setDecimalTextField(DPC_price, Price[0]);
                CurrentWeighingMethod = DataEventSource.Inactive;
            }
            setDecimalTextField(DPC_unitPriceX, UnitPriceX[0]);
            DPC_weightUnitX.setText(new WeightUnitValues().getSymbol(WeightUnitX[0]));
            DPC_weightNumeratorX.setText(Integer.toString(WeightNumeratorX[0]));
            DPC_weightDenominatorX.setText(Integer.toString(WeightDenominatorX[0]));
        }
    }

    public void doPriceCalculating(ActionEvent actionEvent) {
        if (isMethodRunning()) {
            return;
        }
        Integer tio = new TimeoutValues().getInteger(DPC_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new DoPriceCalculatingHandler(tio).start();
    }

    public void freezeValue(ActionEvent actionEvent) {
        int item = FV_itemManualTare.isSelected() ? ScaleConst.SCAL_SFR_MANUAL_TARE : 0;
        item |= FV_itemPercentTare.isSelected() ? ScaleConst.SCAL_SFR_PERCENT_TARE : 0;
        item |= FV_itemUnitPrince.isSelected() ? ScaleConst.SCAL_SFR_UNITPRICE : 0;
        item |= FV_itemWeightedTare.isSelected() ? ScaleConst.SCAL_SFR_WEIGHTED_TARE : 0;
        try {
            TheScale.freezeValue(item, FV_freeze.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    class ReadLiveWeightWithTare extends MethodProcessor {
        private final int Timeout;
        private int[] WeightData = new int[1];
        private int[] Tare = new int[1];

        ReadLiveWeightWithTare(int timeout) {
            super("ReadLiveWeightWithTare");
            Timeout = timeout;
            CurrentWeighingMethod = DataEventSource.ReadLiveWeightWithTare;
        }
        @Override
        void runIt() throws JposException {
            TheScale.readLiveWeightWithTare(WeightData, Tare, Timeout);
        }

        @Override
        void finish() {
            super.finish();
            if (!AsyncMode.isSelected()) {
                setWeightField(RLWWT_weightData, WeightData[0]);
                setWeightField(RLWWT_tare, Tare[0]);
                CurrentWeighingMethod = DataEventSource.Inactive;
            }
        }
    }

    public void readLiveWeightWithTare(ActionEvent actionEvent) {
        if (isMethodRunning()) {
            return;
        }
        Integer tio = new TimeoutValues().getInteger(RLWWT_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new ReadLiveWeightWithTare(tio).start();
    }

    class ReadWeight extends MethodProcessor {
        private final int Timeout;
        private int[] WeightData = new int[1];

        ReadWeight(int timeout) {
            super("ReadWeight");
            Timeout = timeout;
            CurrentWeighingMethod = DataEventSource.ReadWeight;
        }
        @Override
        void runIt() throws JposException {
            TheScale.readWeight(WeightData, Timeout);
        }

        @Override
        void finish() {
            super.finish();
            if (!AsyncMode.isSelected()) {
                setWeightField(RW_weightData, WeightData[0]);
                CurrentWeighingMethod = DataEventSource.Inactive;
            }
        }
    }

    public void readWeight(ActionEvent actionEvent) {
        if (isMethodRunning()) {
            return;
        }
        Integer tio = new TimeoutValues().getInteger(RW_timeout.getValue());
        if (!invalid(tio, "timeout"))
            new ReadWeight(tio).start();
    }

    public void setPriceCalculationMode(ActionEvent actionEvent) {
        Integer mode = new SPCM_modeValues().getInteger(SPCM_mode.getValue());
        if (invalid(mode, "mode"))
            return;
        try {
            TheScale.setPriceCalculationMode(mode);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void setSpecialTare(ActionEvent actionEvent) {
        Integer mode = new SST_modeValues().getInteger(SST_mode.getValue());
        if (invalid(mode, "mode"))
            return;
        Integer data = null;
        try {
            BigDecimal bdata = new BigDecimal(SST_data.getText());
            if (mode == ScaleConst.SCAL_SST_DEFAULT || mode == ScaleConst.SCAL_SST_MANUAL)
                data = bdata.scaleByPowerOfTen(3).intValueExact();
            else if (mode == ScaleConst.SCAL_SST_PERCENT)
                data = bdata.scaleByPowerOfTen(2).intValueExact();
            else
                data = bdata.intValueExact();
        } catch (Exception e) {}
        if (invalid(data, "data"))
            return;
        try {
            TheScale.setSpecialTare(mode, data);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void setTarePriority(ActionEvent actionEvent) {
        Integer prio = new STP_priorityValues().getInteger(STP_priority.getValue());
        if (invalid(prio, "priority"))
            return;
        try {
            TheScale.setTarePrioity(prio);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void setUnitPriceWithWeightUnit(ActionEvent actionEvent) {
        Long unitPrice = null;
        Integer weightUnit = new WeightUnitValues().getInteger(SUPWWU_weightUnit.getValue());
        Integer weightNumerator = new IntValues().getInteger(SUPWWU_weightNumerator.getText());
        Integer weightDenominator = new IntValues().getInteger(SUPWWU_weightDenominator.getText());
        try {
            unitPrice = new BigDecimal(SUPWWU_unitPrice.getText()).scaleByPowerOfTen(4).longValueExact();
        } catch (Exception e) {}
        if (invalid(unitPrice, "unitPrice") || invalid(weightUnit, "weightUnit") || invalid(weightNumerator, "weightNumerator") || invalid(weightDenominator, "weightDenominator"))
            return;
        try {
            TheScale.setUnitPriceWithWeightUnit(unitPrice, weightUnit, weightNumerator, weightDenominator);
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void zeroScale(ActionEvent actionEvent) {
        try {
            TheScale.zeroScale();
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    private class StatusNotifyValues extends Values {
        StatusNotifyValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_SN_DISABLED, "SN_DISABLED",
                    ScaleConst.SCAL_SN_ENABLED, "SN_ENABLED"
            };
        }
    }

    private class WeightUnitValues extends Values {
        WeightUnitValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_WU_GRAM, "WU_GRAM",
                    ScaleConst.SCAL_WU_KILOGRAM, "WU_KILOGRAM",
                    ScaleConst.SCAL_WU_OUNCE, "WU_OUNCE",
                    ScaleConst.SCAL_WU_POUND, "WU_POUND"
            };
        }
    }

    private class SUE_stateValues extends Values {
        SUE_stateValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_SUE_STABLE_WEIGHT, "SUE_STABLE_WEIGHT",
                    ScaleConst.SCAL_SUE_WEIGHT_UNSTABLE, "SUE_WEIGHT_UNSTABLE",
                    ScaleConst.SCAL_SUE_WEIGHT_ZERO, "SUE_WEIGHT_ZERO",
                    ScaleConst.SCAL_SUE_WEIGHT_OVERWEIGHT, "SUE_WEIGHT_OVERWEIGHT",
                    ScaleConst.SCAL_SUE_NOT_READY, "SUE_NOT_READY",
                    ScaleConst.SCAL_SUE_WEIGHT_UNDER_ZERO, "SUE_WEIGHT_UNDER_ZERO"
            };
        }
    }

    private class SPCM_modeValues extends Values {
        SPCM_modeValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_PCM_PRICE_LABELING, "PCM_PRICE_LABELING",
                    ScaleConst.SCAL_PCM_SELF_SERVICE, "PCM_SELF_SERVICE",
                    ScaleConst.SCAL_PCM_OPERATOR, "PCM_OPERATOR"
            };
        }
    }

    private class SST_modeValues extends Values {
        SST_modeValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_SST_DEFAULT, "SST_DEFAULT",
                    ScaleConst.SCAL_SST_MANUAL, "SST_MANUAL",
                    ScaleConst.SCAL_SST_PERCENT, "SST_PERCENT",
                    ScaleConst.SCAL_SST_WEIGHTED, "SST_WEIGHTED"
            };
        }
    }

    private class STP_priorityValues extends Values {
        STP_priorityValues(){
            ValueList = new Object[]{
                    ScaleConst.SCAL_STP_FIRST, "STP_FIRST",
                    ScaleConst.SCAL_STP_NONE, "STP_NONE"
            };
        }
    }
}
