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

import de.gmxhome.conrad.jpos.jpos_base.fiscalprinter.FiscalPrinterService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import jpos.*;
import jpos.events.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * GUI control for FiscalPrinter properties, methods and events.
 */
public class FiscalPrinterController extends CommonController {
    public CheckBox FlagWhenIdle;
    public CheckBox CheckTotal;
    public CheckBox DuplicateReceipt;
    public TextField AdditionalHeader;
    public TextField AdditionalTrailer;
    public TextField ChangeDue;
    public TextField PreLine;
    public TextField PostLine;
    public ComboBox<String> ContractorId;
    public ComboBox<String> FiscalReceiptType;
    public ComboBox<String> MessageType;
    public ComboBox<String> FiscalReceiptStation;
    public ComboBox<String> SlipSelection;
    public Label PrinterState;
    public ComboBox<String> PrintRec;
    public ComboBox<String> PrintRecPackage;
    public ComboBox<String> PrintRecSubtotal;
    public ComboBox<String> PrintRecVoid;
    public TextField PRMI_message;
    public CheckBox BFR_printHeader;
    public TextField PRV_description;
    public TextField PRV_amount;
    public TextField PRV_quantity;
    public ComboBox<String> PRV_adjustmentType;
    public TextField PRV_adjustment;
    public TextField PRV_vatInfo;
    public TextField PR_description;
    public TextField PR_price;
    public TextField PR_quantity;
    public TextField PR_vatInfo;
    public TextField PR_unitPrice;
    public TextField PR_unitName;
    public TextField PR_specialTax;
    public TextField PR_specialTaxName;
    public ComboBox<String> PR_adjustmentType;
    public TextField PR_amount;
    public ComboBox<String> PRP_adjustmentType;
    public TextField PRP_description;
    public TextField PRP_vatAdjustment;
    public TextField PRS_amount;
    public ComboBox<String> PRS_adjustmentType;
    public TextField PRS_description;
    public TextField PRS_adjustment;
    public TextField PRT_total;
    public TextField PRT_payment;
    public TextField PRT_description;
    public ComboBox<String> PRT_descriptionIndex;
    public TextField PRNP_description;
    public TextField PRNP_amount;
    public TextField PRC_amount;
    public TextField PRTID_description;
    public TextField PRMF_message;
    public CheckBox EFR_printHeader;
    public ComboBox<String> BI_timeout;
    public ComboBox<String> BR_timeout;
    public ComboBox<String> PN_station;
    public TextArea PN_data;
    public ComboBox<String> BFO_station;
    public TextField BFO_documentType;
    public TextField PFO_documentType;
    public TextField PFO_lineNumber;
    public TextField PFO_data;
    public TextField BFD_documentAmount;
    public TextField PFDL_documentLine;
    public ComboBox<String> BIL_vatID;
    public TextField VI_itemName;
    public ComboBox<String> VI_vatID;
    public ComboBox<String> PR_reportType;
    public TextField PR_startNum;
    public TextField PR_endNum;
    public TextField PPTR_date1;
    public TextField PPTR_date2;
    public ComboBox<String> DateType;
    public ComboBox<String> GD_dataItem;
    public ComboBox<String> GD_optArgs;
    public TextField GD_data;
    public TextField GD_date;
    public ComboBox<String> GT_vatID;
    public ComboBox<String> GT_optArgs;
    public TextField GT_data;
    public ComboBox<String> TotalizerType;
    public TextField RVV_start;
    public TextField RVV_stop;
    public TextField VatIDs;
    public ComboBox<String> GVE_vatID;
    public TextField GVE_optArgs;
    public TextField GVE_vatRate;
    public ComboBox<String> SVV_vatID;
    public TextField SVV_vatRate;
    public ComboBox<String> SC_newCurrency;
    public TextField SD_date;
    public TextField SSFID_ID;
    public ComboBox<String> SHL_lineNumber;
    public TextField SHL_text;
    public CheckBox SHL_doubleWidth;
    public ComboBox<String> STL_lineNumber;
    public TextField STL_text;
    public CheckBox STL_doubleWidth;
    public TextField SP_POSID;
    public TextField SP_cashierID;
    public CheckBox BFD_UseFourDigits;
    public CheckBox SVV_intRate;
    public Label ILlabel1;
    public Label ILlabel2;
    private FiscalPrinter ThePrinter;
    private PropertyTableRow FlagWhenIdleRow;
    private PropertyTableRow AdditionalHeaderRow;
    private PropertyTableRow AdditionalTrailerRow;
    private PropertyTableRow AmountDecimalPlacesRow;
    private PropertyTableRow CapAmountAdjustmentRow;
    private PropertyTableRow CapPackageAdjustmentRow;
    private PropertyTableRow CapPercentAdjustmentRow;
    private PropertyTableRow CapPositiveAdjustmentRow;
    private PropertyTableRow CapPositiveSubtotalAdjustmentRow;
    private PropertyTableRow CapPredefinedPaymentLinesRow;
    private PropertyTableRow CapSubAmountAdjustmentRow;
    private PropertyTableRow CapSubPercentAdjustmentRow;
    private PropertyTableRow ChangeDueRow;
    private PropertyTableRow CheckTotalRow;
    private PropertyTableRow ContractorIdRow;
    private PropertyTableRow DateTypeRow;
    private PropertyTableRow DuplicateReceiptRow;
    private PropertyTableRow FiscalReceiptStationRow;
    private PropertyTableRow FiscalReceiptTypeRow;
    private PropertyTableRow MessageTypeRow;
    private PropertyTableRow NumHeaderLinesRow;
    private PropertyTableRow NumTrailerLinesRow;
    private PropertyTableRow NumVatRatesRow;
    private PropertyTableRow PostLineRow;
    private PropertyTableRow PredefinedPaymentLinesRow;
    private PropertyTableRow PreLineRow;
    private PropertyTableRow PrinterStateRow;
    private PropertyTableRow QuantityDecimalPlacesRow;
    private PropertyTableRow SlipSelectionRow;
    private PropertyTableRow TotalizerTypeRow;
    private PropertyTableRow CapHasVatTableRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Hexadecimal;
        super.initialize(url, resourceBundle);
        ThePrinter = (FiscalPrinter) Control;
        ThePrinter.addDirectIOListener(this);

        ThePrinter.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new PrtStatusUpdateValues();
        ThePrinter.addErrorListener(this);
        ThePrinter.addOutputCompleteListener(this);
        ErrorCodeExtendedValueConverter = new ExtendedErrorCodeValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("ActualCurrency", "", new ActualCurrencyValues()));
        Properties.getItems().add(AdditionalHeaderRow = new PropertyTableRow("AdditionalHeader", ""));
        Properties.getItems().add(AdditionalTrailerRow = new PropertyTableRow("AdditionalTrailer", ""));
        Properties.getItems().add(AmountDecimalPlacesRow = new PropertyTableRow("AmountDecimalPlaces", ""));
        Properties.getItems().add(new PropertyTableRow("CapAdditionalHeader", ""));
        Properties.getItems().add(new PropertyTableRow("CapAdditionalLines", ""));
        Properties.getItems().add(new PropertyTableRow("CapAdditionalTrailer", ""));
        Properties.getItems().add(CapAmountAdjustmentRow = new PropertyTableRow("CapAmountAdjustment", ""));
        Properties.getItems().add(new PropertyTableRow("CapAmountNotPaid", ""));
        Properties.getItems().add(new PropertyTableRow("CapChangeDue", ""));
        Properties.getItems().add(new PropertyTableRow("CapCheckTotal", ""));
        Properties.getItems().add(new PropertyTableRow("CapCoverSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapDoubleWidth", ""));
        Properties.getItems().add(new PropertyTableRow("CapDuplicateReceipt", ""));
        Properties.getItems().add(new PropertyTableRow("CapEmptyReceiptIsVoidable", ""));
        Properties.getItems().add(new PropertyTableRow("CapFiscalReceiptStation", ""));
        Properties.getItems().add(new PropertyTableRow("CapFiscalReceiptType", ""));
        Properties.getItems().add(new PropertyTableRow("CapFixedOutput", ""));
        Properties.getItems().add(CapHasVatTableRow = new PropertyTableRow("CapHasVatTable", ""));
        Properties.getItems().add(new PropertyTableRow("CapIndependentHeader", ""));
        Properties.getItems().add(new PropertyTableRow("CapItemList", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapJrnPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapMultiContractor", ""));
        Properties.getItems().add(new PropertyTableRow("CapNonFiscalMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapOnlyVoidLastItem", ""));
        Properties.getItems().add(new PropertyTableRow("CapOrderAdjustmentFirst", ""));
        Properties.getItems().add(CapPackageAdjustmentRow = new PropertyTableRow("CapPackageAdjustment", ""));
        Properties.getItems().add(CapPercentAdjustmentRow = new PropertyTableRow("CapPercentAdjustment", ""));
        Properties.getItems().add(CapPositiveAdjustmentRow = new PropertyTableRow("CapPositiveAdjustment", ""));
        Properties.getItems().add(CapPositiveSubtotalAdjustmentRow = new PropertyTableRow("CapPositiveSubtotalAdjustment", ""));
        Properties.getItems().add(new PropertyTableRow("CapPostPreLine", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerLossReport", ""));
        Properties.getItems().add(CapPredefinedPaymentLinesRow = new PropertyTableRow("CapPredefinedPaymentLines", ""));
        Properties.getItems().add(new PropertyTableRow("CapReceiptNotPaid", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapRecPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapRemainingFiscalMemory", ""));
        Properties.getItems().add(new PropertyTableRow("CapReservedWord", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetCurrency", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetHeader", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetPOSID", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetStoreFiscalID", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetTrailer", ""));
        Properties.getItems().add(new PropertyTableRow("CapSetVatTable", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpEmptySensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpFiscalDocument", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpFullSlip", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpNearEndSensor", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpPresent", ""));
        Properties.getItems().add(new PropertyTableRow("CapSlpValidation", ""));
        Properties.getItems().add(CapSubAmountAdjustmentRow = new PropertyTableRow("CapSubAmountAdjustment", ""));
        Properties.getItems().add(CapSubPercentAdjustmentRow = new PropertyTableRow("CapSubPercentAdjustment", ""));
        Properties.getItems().add(new PropertyTableRow("CapSubtotal", ""));
        Properties.getItems().add(new PropertyTableRow("CapTotalizerType", ""));
        Properties.getItems().add(new PropertyTableRow("CapTrainingMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapValidateJournal", ""));
        Properties.getItems().add(new PropertyTableRow("CapXReport", ""));
        Properties.getItems().add(ChangeDueRow = new PropertyTableRow("ChangeDue", ""));
        Properties.getItems().add(CheckTotalRow = new PropertyTableRow("CheckTotal", ""));
        Properties.getItems().add(ContractorIdRow = new PropertyTableRow("ContractorId", "", new ContractorIdValues()));
        Properties.getItems().add(new PropertyTableRow("CountryCode", "", new CountryCodeValues()));
        Properties.getItems().add(new PropertyTableRow("CoverOpen", ""));
        Properties.getItems().add(DateTypeRow = new PropertyTableRow("DateType", "", new DateTypeValues()));
        Properties.getItems().add(new PropertyTableRow("DayOpened", ""));
        Properties.getItems().add(new PropertyTableRow("DescriptionLength", ""));
        Properties.getItems().add(DuplicateReceiptRow = new PropertyTableRow("DuplicateReceipt", ""));
        Properties.getItems().add(new PropertyTableRow("ErrorLevel", "", new ErrorLevelValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorOutID", ""));
        Properties.getItems().add(new PropertyTableRow("ErrorState", "", new PrinterStateValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorStation", "", new ErrorStationValues()));
        Properties.getItems().add(new PropertyTableRow("ErrorString", ""));
        Properties.getItems().add(FiscalReceiptStationRow = new PropertyTableRow("FiscalReceiptStation", "", new FiscalReceiptStationValues()));
        Properties.getItems().add(FiscalReceiptTypeRow = new PropertyTableRow("FiscalReceiptType", "", new FiscalReceiptTypeValues()));
        Properties.getItems().add(FlagWhenIdleRow = new PropertyTableRow("FlagWhenIdle", ""));
        Properties.getItems().add(new PropertyTableRow("JrnEmpty", ""));
        Properties.getItems().add(new PropertyTableRow("JrnNearEnd", ""));
        Properties.getItems().add(new PropertyTableRow("MessageLength", ""));
        Properties.getItems().add(MessageTypeRow = new PropertyTableRow("MessageType", "", new MessageTypeValues()));
        Properties.getItems().add(NumHeaderLinesRow = new PropertyTableRow("NumHeaderLines", ""));
        Properties.getItems().add(NumTrailerLinesRow = new PropertyTableRow("NumTrailerLines", ""));
        Properties.getItems().add(NumVatRatesRow = new PropertyTableRow("NumVatRates", ""));
        Properties.getItems().add(PostLineRow = new PropertyTableRow("PostLine", ""));
        Properties.getItems().add(PredefinedPaymentLinesRow = new PropertyTableRow("PredefinedPaymentLines", ""));
        Properties.getItems().add(PreLineRow = new PropertyTableRow("PreLine", ""));
        Properties.getItems().add(PrinterStateRow = new PropertyTableRow("PrinterState", "", new PrinterStateValues()));
        Properties.getItems().add(QuantityDecimalPlacesRow = new PropertyTableRow("QuantityDecimalPlaces", ""));
        Properties.getItems().add(new PropertyTableRow("QuantityLength", ""));
        Properties.getItems().add(new PropertyTableRow("RecEmpty", ""));
        Properties.getItems().add(new PropertyTableRow("RecNearEnd", ""));
        Properties.getItems().add(new PropertyTableRow("RemainingFiscalMemory", ""));
        Properties.getItems().add(new PropertyTableRow("ReservedWord", ""));
        Properties.getItems().add(new PropertyTableRow("SlpEmpty", ""));
        Properties.getItems().add(new PropertyTableRow("SlpNearEnd", ""));
        Properties.getItems().add(SlipSelectionRow = new PropertyTableRow("SlipSelection", "", new SlipSelectionValues()));
        Properties.getItems().add(TotalizerTypeRow = new PropertyTableRow("TotalizerType", "", new TotalizerTypeValues()));
        Properties.getItems().add(new PropertyTableRow("TrainingModeActive", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        setPropertyOnFocusLost(AdditionalHeader, "AdditionalHeader");
        setPropertyOnFocusLost(AdditionalTrailer, "AdditionalTrailer");
        setPropertyOnFocusLost(ChangeDue, "ChangeDue");
        setPropertyOnFocusLost(PreLine, "PreLine");
        setPropertyOnFocusLost(PostLine, "PostLine");
        Values val = ContractorIdRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            ContractorId.getItems().add(val.ValueList[i].toString());
        val = FiscalReceiptTypeRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            FiscalReceiptType.getItems().add(val.ValueList[i].toString());
        val = MessageTypeRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            MessageType.getItems().add(val.ValueList[i].toString());
        val = FiscalReceiptStationRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            FiscalReceiptStation.getItems().add(val.ValueList[i].toString());
        val = SlipSelectionRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            SlipSelection.getItems().add(val.ValueList[i].toString());
        val = DateTypeRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            DateType.getItems().add(val.ValueList[i].toString());
        val = TotalizerTypeRow.getValueConverter();
        for (int i = 1; i < val.ValueList.length; i += 2)
            TotalizerType.getItems().add(val.ValueList[i].toString());
        val = new TimeoutValues();
        for (int i = 1; i < val.ValueList.length; i += 2) {
            BI_timeout.getItems().add(val.ValueList[i].toString());
            BR_timeout.getItems().add(val.ValueList[i].toString());
        }
        val = new PN_stationValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            PN_station.getItems().add(val.ValueList[i].toString());
        val = new BFO_stationValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            BFO_station.getItems().add(val.ValueList[i].toString());
        val = new PR_reportTypeValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            PR_reportType.getItems().add(val.ValueList[i].toString());
        val = new GD_dataItemValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            GD_dataItem.getItems().add(val.ValueList[i].toString());
        val = new GT_optArgsValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            GT_optArgs.getItems().add(val.ValueList[i].toString());
        val = new SC_newCurrencyValues();
        for (int i = 1; i < val.ValueList.length; i += 2)
            SC_newCurrency.getItems().add(val.ValueList[i].toString());
        PrintRec.getItems().addAll(
                "Item", "ItemVoid", "ItemAdjustment", "ItemAdjustment-\nVoid", "ItemFuel", "ItemFuelVoid", "ItemRefund",
                "ItemRefund-\nVoid", "Refund", "RefundVoid"
        );
        PrintRec.setButtonCell(new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setAlignment(Pos.CENTER_LEFT);
                    Insets old = getPadding();
                    setPadding(new Insets(old.getTop(), 2, old.getBottom(), 2));
                }
            }
        });
        PrintRec.getSelectionModel().select(0);
        PrintRecPackage.getItems().addAll("Package-\nAdjustment", "Package-\nAdjustVoid");
        PrintRecPackage.getSelectionModel().select(0);
        PrintRecSubtotal.getItems().addAll("Subtotal", "Subtotal-\nAdjustment", "Subtotal-\nAdjustVoid");
        PrintRecSubtotal.getSelectionModel().select(0);
        PrintRecVoid.getItems().addAll("Void", "VoidItem");
        PrintRecVoid.getSelectionModel().select(0);
        CurrencyDigits.getItems().add(0);
        CurrencyDigits.getItems().add(1);
        CurrencyDigits.getItems().add(2);
        CurrencyDigits.getItems().add(3);
        CurrencyDigits.getItems().add(4);
        CurrencyDigits.setValue(0);
        formatDecimalOnFocusLost(PRV_amount);
        formatDecimalOnFocusLost(PR_price);
        formatDecimalOnFocusLost(PR_unitPrice);
        formatDecimalOnFocusLost(PR_specialTax);
        formatDecimalOnFocusLost(PRS_amount);
        formatDecimalOnFocusLost(PRT_total);
        formatDecimalOnFocusLost(PRT_payment);
        formatDecimalOnFocusLost(PRNP_amount);
        formatDecimalOnFocusLost(PRC_amount);
        formatDecimalOnFocusLost(BFD_documentAmount);
        formatQuantityOnFocusLost(PRV_quantity);
        formatQuantityOnFocusLost(PR_quantity);
        formatVatIdOnFocusLost(PRV_vatInfo);
        formatVatIdOnFocusLost(PR_vatInfo);
        formatVatRateOnFocusLost(GVE_vatRate);
        formatVatRateOnFocusLost(SVV_vatRate);
        updateGui();
    }

    void formatQuantityOnFocusLost(TextField field) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                Integer val = new IntValues().getInteger(QuantityDecimalPlacesRow.getValue());
                formatDecimalTextField(field, val == null ? 0 : val);
            }
        });
    }

    void formatVatIdOnFocusLost(TextField field) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                Integer val = new IntValues().getInteger(AmountDecimalPlacesRow.getValue());
                boolean decimals = val != null && "false".equals(CapHasVatTableRow.getValue());
                formatDecimalTextField(field, decimals ? val : 0);
            }
        });
    }

    void formatVatRateOnFocusLost(TextField field) {
        field.focusedProperty().addListener((ov, oldv, newv) -> {
            if (!newv) {
                formatDecimalTextField(field, 4);
            }
        });
    }

    FiscalPrinterService TheService = null;

    @Override
    public void gotStatusUpdate(StatusUpdateEvent event) {
        if (TheService == null && event.getSource() instanceof FiscalPrinterService) {
            TheService = (FiscalPrinterService) event.getSource();
        }
        super.gotStatusUpdate(event);
    }

    @Override
    void updateGui() {
        String quantitydecimals = QuantityDecimalPlacesRow.getValue();
        String amountdecimals = AmountDecimalPlacesRow.getValue();
        String hasvatinfos = CapHasVatTableRow.getValue();
        String haspredefinedpaymentlines = CapPredefinedPaymentLinesRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            FlagWhenIdle.setSelected("true".equals(FlagWhenIdleRow.getValue()));
            CheckTotal.setSelected("true".equals(CheckTotalRow.getValue()));
            DuplicateReceipt.setSelected("true".equals(DuplicateReceiptRow.getValue()));
            AdditionalHeader.setText(AdditionalHeaderRow.getValue());
            AdditionalTrailer.setText(AdditionalTrailerRow.getValue());
            ChangeDue.setText(ChangeDueRow.getValue());
            PreLine.setText(PreLineRow.getValue());
            PostLine.setText(PostLineRow.getValue());
            ContractorId.setValue(ContractorIdRow.getValue());
            FiscalReceiptType.setValue(FiscalReceiptTypeRow.getValue());
            MessageType.setValue(MessageTypeRow.getValue());
            FiscalReceiptStation.setValue(FiscalReceiptStationRow.getValue());
            SlipSelection.setValue(SlipSelectionRow.getValue());
            PrinterState.setText(PrinterStateRow.getValue());
            if (CapPackageAdjustmentRow.getValue().equals("true")) {
                PackageAdjustmentTypeValues patvals = new PackageAdjustmentTypeValues();
                if (PRP_adjustmentType.getItems().size() != patvals.ValueList.length / 2) {
                    PRP_adjustmentType.getItems().clear();
                    for (int i = 1; i < patvals.ValueList.length; i += 2) {
                        PRP_adjustmentType.getItems().add(patvals.ValueList[i].toString());
                    }
                }
            }
            else
                PRP_adjustmentType.getItems().clear();
            if (!CapAmountAdjustmentRow.getValue().equals("") && !CapPercentAdjustmentRow.getValue().equals("") && !CapPositiveAdjustmentRow.getValue().equals("")) {
                if (PRV_adjustmentType.getItems().size() == 0) {
                    if (CapAmountAdjustmentRow.getValue().equals("true")) {
                        PRV_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT));
                        if (CapPositiveAdjustmentRow.getValue().equals("true"))
                            PRV_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_SURCHARGE));
                    }
                    if (CapPercentAdjustmentRow.getValue().equals("true")) {
                        PRV_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_DISCOUNT));
                        if (CapPositiveAdjustmentRow.getValue().equals("true"))
                            PRV_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_SURCHARGE));
                    }
                }
                if (PR_adjustmentType.getItems().size() == 0) {
                    if (CapAmountAdjustmentRow.getValue().equals("true")) {
                        PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT));
                        if (CapPositiveAdjustmentRow.getValue().equals("true"))
                            PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_SURCHARGE));
                        PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_COUPON_AMOUNT_DISCOUNT));
                    }
                    if (CapPercentAdjustmentRow.getValue().equals("true")) {
                        PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_DISCOUNT));
                        if (CapPositiveAdjustmentRow.getValue().equals("true"))
                            PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_SURCHARGE));
                        PR_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT));
                    }
                }
            }
            else
                PR_adjustmentType.getItems().clear();
            if (!CapSubAmountAdjustmentRow.getValue().equals("") && !CapSubPercentAdjustmentRow.getValue().equals("") && !CapPositiveSubtotalAdjustmentRow.getValue().equals("")) {
                if (PRS_adjustmentType.getItems().size() == 0) {
                    if (CapSubAmountAdjustmentRow.getValue().equals("true")) {
                        PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT));
                        if (CapPositiveSubtotalAdjustmentRow.getValue().equals("true"))
                            PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_AMOUNT_SURCHARGE));
                        PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_COUPON_AMOUNT_DISCOUNT));
                    }
                    if (CapSubPercentAdjustmentRow.getValue().equals("true")) {
                        PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_DISCOUNT));
                        if (CapPositiveSubtotalAdjustmentRow.getValue().equals("true"))
                            PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_SURCHARGE));
                        PRS_adjustmentType.getItems().add(new AdjustmentTypeValues().getSymbol(FiscalPrinterConst.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT));
                    }
                }
            }
            else
                PRS_adjustmentType.getItems().clear();
            Integer val = new IntValues().getInteger(AmountDecimalPlacesRow.getValue());
            if (val != null) {
                CurrencyDigits.setValue(val);
                if (!amountdecimals.equals(AmountDecimalPlacesRow.getValue())) {
                    formatDecimalTextField(PRV_amount, val);
                    formatDecimalTextField(PR_price, val);
                    formatDecimalTextField(PR_unitPrice, val);
                    formatDecimalTextField(PR_specialTax, val);
                    formatDecimalTextField(PRS_amount, val);
                    formatDecimalTextField(PRT_total, val);
                    formatDecimalTextField(PRT_payment, val);
                    formatDecimalTextField(PRNP_amount, val);
                    formatDecimalTextField(PRC_amount, val);
                    formatDecimalTextField(BFD_documentAmount, val);
                    if (!isPercentage(PRV_adjustmentType))
                        formatDecimalTextField(PRV_adjustment, val);
                    if (!isPercentage(PR_adjustmentType))
                        formatDecimalTextField(PR_amount, val);
                    if (!isPercentage(PRS_adjustmentType))
                        formatDecimalTextField(PRS_adjustment, val);
                }
            }
            val = new IntValues().getInteger(QuantityDecimalPlacesRow.getValue());
            if (!quantitydecimals.equals(QuantityDecimalPlacesRow.getValue()) && val != null) {
                formatDecimalTextField(PRV_quantity, val);
                formatDecimalTextField(PR_quantity, val);
            }
            val = "false".equals(CapHasVatTableRow.getValue()) ? 4 : 0;
            if (!hasvatinfos.equals(CapHasVatTableRow.getValue())) {
                formatDecimalTextField(PRV_vatInfo, val);
                formatDecimalTextField(PR_vatInfo, val);
            }
            if (!haspredefinedpaymentlines.equals(CapPredefinedPaymentLinesRow.getValue())) {
                if ("true".equals(CapPredefinedPaymentLinesRow.getValue())) {
                    String current = PRT_descriptionIndex.getValue();
                    PRT_descriptionIndex.getItems().clear();
                    for (String ppl : PredefinedPaymentLinesRow.getValue().split(",")) {
                        PRT_descriptionIndex.getItems().add(ppl);
                        if (ppl.equals(current))
                            PRT_descriptionIndex.setValue(current);
                    }
                    PRT_description.setVisible(false);
                    PRT_descriptionIndex.setVisible(true);
                } else {
                    PRT_descriptionIndex.setVisible(false);
                    PRT_description.setVisible(true);
                }
            }
            val = new IntValues().getInteger(NumHeaderLinesRow.getValue());
            if (val != null && val != SHL_lineNumber.getItems().size()) {
                String current = SHL_lineNumber.getValue();
                for (int i = 1; i <= val; i++) {
                    String valstr = Integer.toString(i);
                    SHL_lineNumber.getItems().add(valstr);
                    if (valstr.equals(current))
                        SHL_lineNumber.setValue(current);
                }
            }
            val = new IntValues().getInteger(NumTrailerLinesRow.getValue());
            if (val != null && val != STL_lineNumber.getItems().size()) {
                String current = STL_lineNumber.getValue();
                for (int i = 1; i <= val; i++) {
                    String valstr = Integer.toString(i);
                    STL_lineNumber.getItems().add(valstr);
                    if (valstr.equals(current))
                        STL_lineNumber.setValue(current);
                }
            }
            InUpdateGui = false;
        }
    }

    private boolean isPercentage(ComboBox<String> box) {
        Values vals = new AdjustmentTypeValues();
        for (String symbol : new String[]{
                vals.getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_DISCOUNT),
                vals.getSymbol(FiscalPrinterConst.FPTR_AT_PERCENTAGE_SURCHARGE),
                vals.getSymbol(FiscalPrinterConst.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT)
        }) {
            if (symbol.equals(box.getValue()))
                return true;
        }
        return false;
    }

    public void setFlagWhenIdle(ActionEvent actionEvent) {
        try {
            ThePrinter.setFlagWhenIdle(FlagWhenIdle.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setCheckTotal(ActionEvent actionEvent) {
        try {
            ThePrinter.setCheckTotal(CheckTotal.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setDuplicateReceipt(ActionEvent actionEvent) {
        try {
            ThePrinter.setDuplicateReceipt(DuplicateReceipt.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setAdditionalHeader(ActionEvent event) {
        try {
            ThePrinter.setAdditionalHeader(AdditionalHeader.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setAdditionalTrailer(ActionEvent event) {
        try {
            ThePrinter.setAdditionalTrailer(AdditionalTrailer.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setChangeDue(ActionEvent event) {
        try {
            ThePrinter.setChangeDue(ChangeDue.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPreLine(ActionEvent event) {
        try {
            ThePrinter.setPreLine(PreLine.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPostLine(ActionEvent event) {
        try {
            ThePrinter.setPostLine(PostLine.getText());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setContractorId(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setContractorId(ContractorIdRow.getValueConverter().getInteger(ContractorId.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setFiscalReceiptType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setFiscalReceiptType(FiscalReceiptTypeRow.getValueConverter().getInteger(FiscalReceiptType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMessageType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setMessageType(MessageTypeRow.getValueConverter().getInteger(MessageType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setFiscalReceiptStation(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setFiscalReceiptStation(FiscalReceiptStationRow.getValueConverter().getInteger(FiscalReceiptStation.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setSlipSelection(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setSlipSelection(SlipSelectionRow.getValueConverter().getInteger(SlipSelection.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setDateType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setDateType(DateTypeRow.getValueConverter().getInteger(DateType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setTotalizerType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                ThePrinter.setTotalizerType(TotalizerTypeRow.getValueConverter().getInteger(TotalizerType.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class BeginFiscalReceipt extends MethodProcessor {
        final boolean PrintHeader;

        BeginFiscalReceipt(boolean printHeader) {
            super(null);
            PrintHeader = printHeader;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginFiscalReceipt(PrintHeader);
        }
    }

    public void beginFiscalReceipt(ActionEvent actionEvent) {
        new BeginFiscalReceipt(BFR_printHeader.isSelected()).start();
    }

    Long fromDecimal(String value, String decimals) {
        try {
            return new BigDecimal(value).scaleByPowerOfTen(Integer.parseInt(decimals)).longValueExact();
        } catch (Exception e) {
            return null;
        }
    }

    Integer fromVatInfo(String value) {
        try {
            if ("false".equals(CapHasVatTableRow.getValue())) {
                return new BigDecimal(value).scaleByPowerOfTen(4).intValueExact();
            } else {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
            return null;
        }
    }

    class PrintRecItem extends MethodProcessor {
        final String Description;
        final long Price;
        final int Quantity;
        final int VatInfo;
        final long UnitPrice;
        final String UnitName;

        PrintRecItem(String description, long price, int quantity, int vatInfo, long unitPrice, String unitName) {
            super(null);
            Description = description;
            Price = price;
            Quantity = quantity;
            VatInfo = vatInfo;
            UnitPrice = unitPrice;
            UnitName = unitName;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItem(Description, Price, Quantity, VatInfo, UnitPrice, UnitName);
        }
    }

    class PrintRecItemVoid extends PrintRecItem {
        PrintRecItemVoid(String description, long price, int quantity, int vatInfo, long unitPrice, String unitName) {
            super(description, price, quantity, vatInfo, unitPrice, unitName);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemVoid(Description, Price, Quantity, VatInfo, UnitPrice, UnitName);
        }
    }

    class PrintRecItemAdjustment extends PrintRecItem {
        final int AdjustmentType;

        PrintRecItemAdjustment(int adjustmentType, String description, long price, int vatInfo) {
            super(description, price, 0, vatInfo, 0, null);
            AdjustmentType = adjustmentType;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemAdjustment(AdjustmentType, Description, Price, VatInfo);
        }
    }

    class PrintRecItemAdjustmentVoid extends PrintRecItemAdjustment {
        PrintRecItemAdjustmentVoid(int adjustmentType, String description, long price, int vatInfo) {
            super(adjustmentType, description, price, vatInfo);
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemAdjustmentVoid(AdjustmentType, Description, Price, VatInfo);
        }
    }

    class PrintRecItemRefund extends PrintRecItem {
        PrintRecItemRefund(String description, long price, int quantity, int vatInfo, long unitPrice, String unitName) {
            super(description, price, quantity, vatInfo, unitPrice, unitName);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemRefund(Description, Price, Quantity, VatInfo, UnitPrice, UnitName);
        }
    }

    class PrintRecItemRefundVoid extends PrintRecItem {
        PrintRecItemRefundVoid(String description, long price, int quantity, int vatInfo, long unitPrice, String unitName) {
            super(description, price, quantity, vatInfo, unitPrice, unitName);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemRefundVoid(Description, Price, Quantity, VatInfo, UnitPrice, UnitName);
        }
    }

    class PrintRecItemFuel extends PrintRecItem {
        final long SpecialTax;
        private final String SpecialTaxName;

        PrintRecItemFuel(String description, long price, int quantity, int vatInfo, long unitPrice, String unitName, long specialTax, String specialTaxName) {
            super(description, price, quantity, vatInfo, unitPrice, unitName);
            SpecialTax = specialTax;
            SpecialTaxName = specialTaxName;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemFuel(Description, Price, Quantity, VatInfo, UnitPrice, UnitName, SpecialTax, SpecialTaxName);
        }
    }

    class PrintRecItemFuelVoid extends PrintRecItemFuel {
        PrintRecItemFuelVoid(String description, long price, int vatInfo, long specialTax) {
            super(description, price, 0, vatInfo, 0, null, specialTax, null);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecItemFuelVoid(Description, Price, VatInfo, SpecialTax);
        }
    }

    class PrintRecRefund extends PrintRecItem {
        PrintRecRefund(String description, long price, int vatInfo) {
            super(description, price, 0, vatInfo, 0, null);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecRefund(Description, Price, VatInfo);
        }
    }

    class PrintRecRefundVoid extends PrintRecItem {
        PrintRecRefundVoid(String description, long price, int vatInfo) {
            super(description, price, 0, vatInfo, 0, null);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecRefundVoid(Description, Price, VatInfo);
        }
    }

    boolean validInt(Long val, String valstr, String decimals) {
        if ("".equals(decimals)) {
            myMessageDialog("Number of decimals unknown for parameter " + valstr + ".");
        } else if (!invalid(val, valstr)) {
            if (val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE)
                return true;
            myMessageDialog("Value " + val + " out of int range for parameter " + valstr + ".");
        }
        return false;
    }

    public void printRec(ActionEvent actionEvent) {
        String quantitydecimals = QuantityDecimalPlacesRow.getValue();
        if (PrintRec.getItems().get(0).equals(PrintRec.getValue())) {   // "Item"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Long quantity = fromDecimal(PR_quantity.getText(), quantitydecimals);
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long unitPrice = fromDecimal(PR_unitPrice.getText(), "4");
            String unitName = PR_unitName.getText();
            if (!invalid(price, "price") && validInt(quantity, "quantity", quantitydecimals) && !invalid(vatInfo, "vatInfo") && !invalid(unitPrice, "unitPrice"))
                new PrintRecItem(description, price, quantity.intValue(), vatInfo, unitPrice, unitName).start();
        } else if (PrintRec.getItems().get(1).equals(PrintRec.getValue())) {    // "ItemVoid"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Long quantity = fromDecimal(PR_quantity.getText(), QuantityDecimalPlacesRow.getValue());
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long unitPrice = fromDecimal(PR_unitPrice.getText(), "4");
            String unitName = PR_unitName.getText();
            if (!invalid(price, "price") && validInt(quantity, "quantity", quantitydecimals) && !invalid(vatInfo, "vatInfo") && !invalid(unitPrice, "unitPrice"))
                new PrintRecItemVoid(description, price, quantity.intValue(), vatInfo, unitPrice, unitName).start();
        } else if (PrintRec.getItems().get(2).equals(PrintRec.getValue())) {      // "ItemAdjustment"
            Integer adjustmentType = new AdjustmentTypeValues().getInteger(PR_adjustmentType.getValue());
            String description = PR_description.getText();
            Long amount = fromDecimal(PR_amount.getText(), "4");
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            if (!invalid(adjustmentType, "adjustmentType") && !invalid(amount, "amount") && !invalid(vatInfo, "vatInfo"))
                new PrintRecItemAdjustment(adjustmentType, description, amount, vatInfo).start();
        } else if (PrintRec.getItems().get(3).equals(PrintRec.getValue())) {        // "ItemAdjustmentVoid"
            Integer adjustmentType = new AdjustmentTypeValues().getInteger(PR_adjustmentType.getValue());
            String description = PR_description.getText();
            Long amount = fromDecimal(PR_amount.getText(), "4");
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            if (!invalid(adjustmentType, "adjustmentType") && !invalid(amount, "amount") && !invalid(vatInfo, "vatInfo"))
                new PrintRecItemAdjustment(adjustmentType, description, amount, vatInfo).start();
        } else if (PrintRec.getItems().get(4).equals(PrintRec.getValue())) {        // "ItemFuel"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Long quantity = fromDecimal(PR_quantity.getText(), QuantityDecimalPlacesRow.getValue());
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long unitPrice = fromDecimal(PR_unitPrice.getText(), "4");
            String unitName = PR_unitName.getText();
            Long specialTax = fromDecimal(PR_specialTax.getText(), "4");
            String specialTaxName = PR_specialTaxName.getText();
            if (!invalid(price, "price") && validInt(quantity, "quantity", quantitydecimals) && !invalid(vatInfo, "vatInfo") && !invalid(unitPrice, "unitPrice") && !invalid(specialTax, "specialTax"))
                new PrintRecItemFuel(description, price, quantity.intValue(), vatInfo, unitPrice, unitName, specialTax, specialTaxName).start();
        } else if (PrintRec.getItems().get(5).equals(PrintRec.getValue())) {        // "ItemFuelVoid"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long specialTax = fromDecimal(PR_specialTax.getText(), "4");
            if (!invalid(price, "price") && !invalid(vatInfo, "vatInfo") && !invalid(specialTax, "specialTax"))
                new PrintRecItemFuelVoid(description, price, vatInfo, specialTax).start();
        } else if (PrintRec.getItems().get(6).equals(PrintRec.getValue())) {      // "ItemRefund"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Long quantity = fromDecimal(PR_quantity.getText(), QuantityDecimalPlacesRow.getValue());
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long unitPrice = fromDecimal(PR_unitPrice.getText(), "4");
            String unitName = PR_unitName.getText();
            if (!invalid(price, "amount") && validInt(quantity, "quantity", quantitydecimals) && !invalid(vatInfo, "vatInfo") && !invalid(unitPrice, "unitPrice"))
                new PrintRecItemRefund(description, price, quantity.intValue(), vatInfo, unitPrice, unitName).start();
        } else if (PrintRec.getItems().get(6).equals(PrintRec.getValue())) {     // "ItemRefundVoid"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Long quantity = fromDecimal(PR_quantity.getText(), QuantityDecimalPlacesRow.getValue());
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            Long unitPrice = fromDecimal(PR_unitPrice.getText(), "4");
            String unitName = PR_unitName.getText();
            if (!invalid(price, "amount") && validInt(quantity, "quantity", quantitydecimals) && !invalid(vatInfo, "vatInfo") && !invalid(unitPrice, "unitPrice"))
                new PrintRecItemRefundVoid(description, price, quantity.intValue(), vatInfo, unitPrice, unitName).start();
        } else if (PrintRec.getItems().get(7).equals(PrintRec.getValue())) {      // "Refund"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            if (!invalid(price, "amount") && !invalid(vatInfo, "vatInfo"))
                new PrintRecRefund(description, price, vatInfo).start();
        } else if (PrintRec.getItems().get(8).equals(PrintRec.getValue())) {  // "RefundVoid"
            String description = PR_description.getText();
            Long price = fromDecimal(PR_price.getText(), "4");
            Integer vatInfo = fromVatInfo(PR_vatInfo.getText());
            if (!invalid(price, "amount") && !invalid(vatInfo, "vatInfo"))
                new PrintRecRefundVoid(description, price, vatInfo).start();
        } else {
            myMessageDialog("Select PrintRec method first");
        }
    }

    class PrintRecPackageAdjustment extends PrintRecItem {
        final int AdjustmentType;
        final String VatAdjustment;

        PrintRecPackageAdjustment(int adjustmentType, String description, String vatAdjustment) {
            super(description, 0, 0, 0, 0, null);
            AdjustmentType = adjustmentType;
            VatAdjustment = vatAdjustment;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecPackageAdjustment(AdjustmentType, Description, VatAdjustment);
        }
    }

    class PrintRecPackageAdjustVoid extends PrintRecPackageAdjustment {
        PrintRecPackageAdjustVoid(int adjustmentType, String vatAdjustment) {
            super(adjustmentType, null, vatAdjustment);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecPackageAdjustVoid(AdjustmentType, VatAdjustment);
        }
    }

    public void printRecPackage(ActionEvent actionEvent) {
        if (PrintRecPackage.getItems().get(0).equals(PrintRecPackage.getValue())) {      // "PackageAdjustment"
            Integer adjustmentType = new PackageAdjustmentTypeValues().getInteger(PRP_adjustmentType.getValue());
            String description = PRP_description.getText();
            String vatAdjustment = PRP_vatAdjustment.getText();
            if (!invalid(adjustmentType, "adjustmentType"))
                new PrintRecPackageAdjustment(adjustmentType, description, vatAdjustment).start();
        } else if (PrintRecPackage.getItems().get(1).equals(PrintRecPackage.getValue())) {  // "PackageAdjustVoid"
            Integer adjustmentType = new PackageAdjustmentTypeValues().getInteger(PRP_adjustmentType.getValue());
            String vatAdjustment = PRP_vatAdjustment.getText();
            if (!invalid(adjustmentType, "adjustmentType"))
                new PrintRecPackageAdjustVoid(adjustmentType, vatAdjustment).start();
        } else {
            myMessageDialog("Select PrintRecPackage method first");
        }
    }

    class PrintRecSubtotal extends MethodProcessor {
        final long Amount;

        PrintRecSubtotal(long amount) {
            super(null);
            Amount = amount;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecSubtotal(Amount);
        }
    }

    class PrintRecSubtotalAdjustment extends PrintRecSubtotal {
        final int AdjustmentType;
        final String Description;

        PrintRecSubtotalAdjustment(int adjustmentType, String description, long amount) {
            super(amount);
            AdjustmentType = adjustmentType;
            Description = description;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecSubtotalAdjustment(AdjustmentType, Description, Amount);
        }
    }

    class PrintRecSubtotalAdjustVoid extends PrintRecSubtotalAdjustment {
        PrintRecSubtotalAdjustVoid(int adjustmentType, long amount) {
            super(adjustmentType, null, amount);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecSubtotalAdjustVoid(AdjustmentType, Amount);
        }
    }

    public void printRecSubtotal(ActionEvent actionEvent) {
        if (PrintRecSubtotal.getItems().get(0).equals(PrintRecSubtotal.getValue())) {      // "Subtotal"
            Long amount = fromDecimal(PRS_amount.getText(), "4");
            if (!invalid(amount, "amount"))
                new PrintRecSubtotal(amount).start();
        } else if (PrintRecSubtotal.getItems().get(1).equals(PrintRecSubtotal.getValue())) {  // "SubtotalAdjustment"
            Integer adjustmentType = new AdjustmentTypeValues().getInteger(PRS_adjustmentType.getValue());
            String description = PRS_description.getText();
            Long amount = fromDecimal(PRS_adjustment.getText(), "4");
            if (!invalid(adjustmentType, "adjustmentType") && !invalid(amount, "amount"))
                new PrintRecSubtotalAdjustment(adjustmentType, description, amount).start();
        } else if (PrintRecSubtotal.getItems().get(2).equals(PrintRecSubtotal.getValue())) {  // "SubtotalAdjustVoid"
            Integer adjustmentType = new AdjustmentTypeValues().getInteger(PRS_adjustmentType.getValue());
            Long amount = fromDecimal(PRS_adjustment.getText(), "4");
            if (!invalid(adjustmentType, "adjustmentType") && !invalid(amount, "amount"))
                new PrintRecSubtotalAdjustVoid(adjustmentType, amount).start();
        } else {
            myMessageDialog("Select PrintRecSubtotal method first");
        }
    }

    class PrintRecMessage extends MethodProcessor {
        private final String Message;

        PrintRecMessage(String message) {
            super("PrintRecMessage");
            Message = message;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecMessage(Message);
        }
    }

    public void printRecMessageF(ActionEvent actionEvent) {
        printRecMessage(PRMF_message);
    }

    public void printRecMessageI(ActionEvent actionEvent) {
        printRecMessage(PRMI_message);
    }

    public void printRecMessage(TextField message) {
        new PrintRecMessage(message.getText()).start();
    }

    class PrintRecVoid extends MethodProcessor {
        final String Description;

        PrintRecVoid(String description) {
            super(null);
            Description = description;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecVoid(Description);
        }
    }

    class PrintRecVoidItem extends PrintRecVoid {
        private final long Amount;
        private final int Quantity;
        private final int AdjustmentType;
        private final long Adjustment;
        private final int VatInfo;

        PrintRecVoidItem(String description, long amount, int quantity, int adjustmentType, long adjustment, int vatInfo) {
            super(description);
            Amount = amount;
            Quantity = quantity;
            AdjustmentType = adjustmentType;
            Adjustment = adjustment;
            VatInfo = vatInfo;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecVoidItem(Description, Amount, Quantity, AdjustmentType, Adjustment, VatInfo);
        }
    }

    public void printRecVoid(ActionEvent actionEvent) {
        String quantitydecimals = QuantityDecimalPlacesRow.getValue();
        if (PrintRecVoid.getItems().get(0).equals(PrintRecVoid.getValue())) {      // "Void"
            new PrintRecVoid(PRV_description.getText()).start();
        } else if (PrintRecVoid.getItems().get(1).equals(PrintRecVoid.getValue())) {  // "VoidItem"
            String description = PRV_description.getText();
            Long amount = fromDecimal(PRV_amount.getText(), "4");
            Long quantity = fromDecimal(PRV_quantity.getText(), quantitydecimals);
            Integer adjustmentType = new AdjustmentTypeValues().getInteger(PRV_adjustmentType.getValue());
            Long adjustment = fromDecimal(PRV_adjustment.getText(), "4");
            Integer vatInfo = fromVatInfo(PRV_vatInfo.getText());
            if (invalid(amount, "amount") || !validInt(quantity, "quantity", quantitydecimals))
                return;
            if (!invalid(adjustmentType, "adjustmentType") && !invalid(adjustment, "adjustment") && !invalid(vatInfo, "vatInfo"))
                new PrintRecVoidItem(description, amount, quantity.intValue(), adjustmentType, adjustment, vatInfo).start();
        } else {
            myMessageDialog("Select PrintRecVoid method first");
        }
    }

    class PrintRecTotal extends MethodProcessor {
        private final long Total;
        private final long Payment;
        private final String Description;

        PrintRecTotal(long total, long payment, String description) {
            super("PrintRecTotal");
            Total = total;
            Payment = payment;
            Description = description;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printRecTotal(Total, Payment, Description);
        }
    }

    public void printRecTotal(ActionEvent actionEvent) {
        Long total = fromDecimal(PRT_total.getText(), "4");
        Long payment = fromDecimal(PRT_payment.getText(), "4");
        String description;
        if ("true".equals(CapPredefinedPaymentLinesRow.getValue())) {
            description = PRT_descriptionIndex.getValue();
        } else {
            description = PRT_description.getText();
        }
        if (!invalid(total, "total") && !invalid(payment, "payment"))
            new PrintRecTotal(total, payment, description).start();
    }

    class PrintRecNotPaid extends MethodProcessor {
        private String Description;
        private long Amount;

        PrintRecNotPaid(String description, long amount){
            super("PrintRecNotPaid");
            Description = description;
            Amount = amount;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printRecNotPaid(Description, Amount);
        }
    }

    public void printRecNotPaid(ActionEvent actionEvent) {
        String description = PRNP_description.getText();
        Long amount = fromDecimal(PRNP_amount.getText(), "4");
        if (!invalid(amount, "amount"))
            new PrintRecNotPaid(description, amount).start();
    }

    class PrintRecCash extends MethodProcessor {
        private final long Amount;

        PrintRecCash(long amount) {
            super("PrintRecCash");
            Amount = amount;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printRecCash(Amount);
        }
    }

    public void printRecCash(ActionEvent actionEvent) {
        Long amount = fromDecimal(PRC_amount.getText(), "4");
        if (!invalid(amount, "amount"))
            new PrintRecCash(amount).start();
    }

    class PrintRecTaxID extends MethodProcessor {
        PrintRecTaxID(String taxID) {
            super("PrintRecTaxID");
            TaxID = taxID;
        }

        private String TaxID;

        @Override
        void runIt() throws JposException {
            ThePrinter.printRecTaxID(TaxID);
        }
    }

    public void printRecTaxID(ActionEvent actionEvent) {
        new PrintRecTaxID(PRTID_description.getText()).start();
    }

    class EndFiscalReceipt extends BeginFiscalReceipt {
        EndFiscalReceipt(boolean printHeader) {
            super(printHeader);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.endFiscalReceipt(PrintHeader);
        }
    }

    public void endFiscalReceipt(ActionEvent actionEvent) {
        new EndFiscalReceipt(EFR_printHeader.isSelected()).start();
    }

    class BeginInsertion extends EndInsertion {
        final int Timeout;

        BeginInsertion(int timeout) {
            super();
            Timeout = timeout;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginInsertion(Timeout);
        }
    }

    public void beginInsertion(ActionEvent actionEvent) {
        Integer timeout = new TimeoutValues().getInteger(BI_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginInsertion(timeout).start();
    }

    class EndInsertion extends MethodProcessor {
        EndInsertion() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.endInsertion();
        }
    }

    public void endInsertion(ActionEvent actionEvent) {
        new EndInsertion().start();
    }

    class BeginRemoval extends BeginInsertion {
        BeginRemoval(int timeout) {
            super(timeout);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        Integer timeout = new TimeoutValues().getInteger(BR_timeout.getValue());
        if (!invalid(timeout, "timeout"))
            new BeginRemoval(timeout).start();
    }

    class EndRemoval extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endRemoval();
        }
    }

    public void endRemoval(ActionEvent actionEvent) {
        new EndInsertion().start();
    }

    class BeginFixedOutput extends EndInsertion {
        final int Station;
        private final int DocumentType;

        BeginFixedOutput(int station, int documentType) {
            super();
            Station = station;
            DocumentType = documentType;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginFixedOutput(Station, DocumentType);
        }
    }

    public void beginFixedOutput(ActionEvent actionEvent) {
        Integer station = new BFO_stationValues().getInteger(BFO_station.getValue());
        Integer documentType = new IntValues().getInteger(BFO_documentType.getText());
        if (!invalid(station, "station") && !invalid(documentType, "documentType"))
            new BeginFixedOutput(station, documentType).start();
    }

    class EndFixedOutput extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endFixedOutput();
        }
    }

    public void endFixedOutput(ActionEvent actionEvent) {
        new EndFixedOutput().start();
    }

    class PrintFixedOutput extends MethodProcessor {
        private int DocumentType;
        private int LineNumber;
        private String Data;

        PrintFixedOutput(int documentType, int lineNumber, String data) {
            super("PrintFixedOutput");
            DocumentType = documentType;
            LineNumber = lineNumber;
            Data = data;
        }

        @Override
        void runIt() throws JposException {
            ThePrinter.printFixedOutput(DocumentType, LineNumber, Data);
        }
    }

    public void printFixedOutput(ActionEvent actionEvent) {
        Integer lineNumber = new IntValues().getInteger(PFO_lineNumber.getText());
        Integer documentType = new IntValues().getInteger(PFO_documentType.getText());
        String data = PFO_data.getText();
        if (!invalid(documentType, "documentType") && !invalid(lineNumber, "lineNumber"))
            new PrintFixedOutput(documentType, lineNumber, data).start();
    }

    class BeginItemList extends EndInsertion {
        int VatID;

        BeginItemList(int vatID) {
            super();
            VatID = vatID;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginItemList(VatID);
        }
    }

    public void beginItemList(ActionEvent actionEvent) {
        Integer vatID = new IntValues().getInteger(BIL_vatID.getValue());
        if (!invalid(vatID, "vatID"))
            new BeginItemList(vatID).start();
    }

    class EndItemList extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endItemList();
        }
    }

    public void endItemList(ActionEvent actionEvent) {
        new EndItemList().start();
    }

    class VerifyItem extends BeginItemList {
        private String ItemName;

        VerifyItem(String itemName, int vatID) {
            super(vatID);
            ItemName = itemName;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.verifyItem(ItemName, VatID);
        }
    }

    public void verifyItem(ActionEvent actionEvent) {
        Integer vatID = new IntValues().getInteger(VI_vatID.getValue());
        if (!invalid(vatID, "vatID"))
            new VerifyItem(VI_itemName.getText(), vatID).start();
    }

    class BeginNonFiscal extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.beginNonFiscal();
        }
    }

    public void beginNonFiscal(ActionEvent actionEvent) {
        new BeginNonFiscal().start();
    }

    class EndNonFiscal extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endNonFiscal();
        }
    }

    public void endNonFiscal(ActionEvent actionEvent) {
        new EndNonFiscal().start();
    }

    class PrintNormal extends BeginFixedOutput {
        private String Data;

        PrintNormal(int station, String data) {
            super(station, 0);
            Data = data;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printNormal(Station, Data);
        }
    }

    public void printNormal(ActionEvent actionEvent) {
        Integer station = new PN_stationValues().getInteger(PN_station.getValue());
        if (!invalid(station, "station"))
            new PrintNormal(station, getData(PN_data)).start();
    }

    private String getData(TextArea text) {
        try {
            char[] data = text.getText().toCharArray();
            int i = 0, j = 0;
            boolean hexmode = false;
            while (i < data.length) {
                if (data[i] == '\\' && i + 1 < data.length) {
                    if (hexmode && data[i + 1] == 'c') {    // \c stops hex data
                        hexmode = false;
                    }
                    else if (!hexmode && data[i + 1] == 'x') {
                        hexmode = true;
                    }
                    else {
                        int index = "nre\\".indexOf(data[i + 1]);
                        if (index < 0 || hexmode) {
                            if (hexmode)
                                myMessageDialog("Invalid control character specification in data: \\" + data[i + 1]);
                            else
                                myMessageDialog("Invalid control character specification in hex-data: \\" + data[i + 1]);
                            break;
                        }
                        data[j++] = new char[]{10, 13, 27, '\\'}[index];
                    }
                    i += 2;
                }
                else {
                    if (hexmode) {
                        int h = "0123456789ABCDEF".indexOf(new String(new char[]{data[i]}).toUpperCase());
                        int l = i + 1 < data.length ? "0123456789ABCDEF".indexOf(new String(new char[]{data[i + 1]}).toUpperCase()) : -999;
                        if (h < 0 || l < 0) {
                            if (l == -999)
                                myMessageDialog("Unexpected hex-data end");
                            else
                                myMessageDialog("Invalid hex-data: " + data[i] + data[i + 1]);
                            break;
                        }
                        data[j++] = (char)((h << 4) + l);
                        i += 2;
                    }
                    else {
                        data[j++] = data[i++];
                    }
                }
            }
            return new String(Arrays.copyOf(data, j));
        } catch (NullPointerException e) {
            return null;
        }
    }

    class BeginFiscalDocument extends EndFiscalDocument {
        private final int DocumentAmount;

        BeginFiscalDocument(int documentAmount) {
            super();
            DocumentAmount = documentAmount;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.beginFiscalDocument(DocumentAmount);
        }
    }

    public void beginFiscalDocument(ActionEvent actionEvent) {
        String digits = BFD_UseFourDigits.isSelected() ? "4" : AmountDecimalPlacesRow.getValue();
        Long documentAmount = fromDecimal(BFD_documentAmount.getText(), digits);
        if (validInt(documentAmount, "documentAmount", digits))
            new BeginFiscalDocument(documentAmount.intValue()).start();
    }

    class EndFiscalDocument extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endFiscalDocument();
        }
    }

    public void endFiscalDocument(ActionEvent actionEvent) {
        new EndFiscalDocument().start();
    }

    class PrintFiscalDocumentLine extends EndInsertion {
        private final String DocumentLine;

        PrintFiscalDocumentLine(String documentLine) {
            super();
            DocumentLine = documentLine;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printFiscalDocumentLine(DocumentLine);
        }
    }

    public void printFiscalDocumentLine(ActionEvent actionEvent) {
        new PrintFiscalDocumentLine(PFDL_documentLine.getText()).start();
    }

    class PrintPeriodicTotalsReport extends EndInsertion {
        String Start;
        String End;

        PrintPeriodicTotalsReport(String start, String end) {
            super();
            Start = start;
            End = end;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printPeriodicTotalsReport(Start, End);
        }
    }

    public void printPeriodicTotalsReport(ActionEvent actionEvent) {
        new PrintPeriodicTotalsReport(PPTR_date1.getText(), PPTR_date2.getText()).start();
    }

    class PrintReport extends PrintPeriodicTotalsReport {
        private final int ReportType;

        PrintReport(int reportType, String startNum, String endNum) {
            super(startNum, endNum);
            ReportType = reportType;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.printReport(ReportType, Start, End);
        }
    }

    public void printReport(ActionEvent actionEvent) {
        Integer reportType = new PR_reportTypeValues().getInteger(PR_reportType.getValue());
        if (!invalid(reportType, "reportType"))
            new PrintReport(reportType, PR_startNum.getText(), PR_endNum.getText()).start();
    }

    class PrintXReport extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.printXReport();
        }
    }

    public void printXReport(ActionEvent actionEvent) {
        new PrintXReport().start();
    }

    class PrintZReport extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.printZReport();
        }
    }

    public void printZReport(ActionEvent actionEvent) {
        new PrintZReport().start();
    }

    class PrintPowerLossReport extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.printPowerLossReport();
        }
    }

    public void printPowerLossReport(ActionEvent actionEvent) {
        new PrintPowerLossReport().start();
    }

    class PrintDuplicateReceipt extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.printDuplicateReceipt();
        }
    }

    public void printDuplicateReceipt(ActionEvent actionEvent) {
        new PrintDuplicateReceipt().start();
    }

    class ClearError extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.clearError();
        }
    }

    public void clearError(ActionEvent actionEvent) {
        new ClearError().start();
    }

    class ResetPrinter extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.resetPrinter();
        }
    }

    public void resetPrinter(ActionEvent actionEvent) {
        new ResetPrinter().start();
    }

    class BeginTraining extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.beginTraining();
        }
    }

    public void beginTraining(ActionEvent actionEvent) {
        new BeginTraining().start();
    }

    class EndTraining extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.endTraining();
        }
    }

    public void endTraining(ActionEvent actionEvent) {
        new EndTraining().start();
    }

    class GetData extends MethodProcessor {
        private final int DataItem;
        int[] OptArgs = new int[1];
        String[] Data = new String[1];
        GetData(int dataItem, int optArgs, String data) {
            super("GetData");
            DataItem = dataItem;
            OptArgs[0] = optArgs;
            Data[0] = data;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.getData(DataItem, OptArgs, Data);
        }
        @Override
        void finish() {
            updateGui();
            Values val;
            if (DataItem == FiscalPrinterConst.FPTR_GD_TENDER)
                val = new GDTenderValues();
            else if (DataItem == FiscalPrinterConst.FPTR_GD_DESCRIPTION_LENGTH)
                val = new GDDescriptionLengthValues();
            else if (DataItem == FiscalPrinterConst.FPTR_GD_LINECOUNT)
                val = new GDLinecountValues();
            else
                val = new IntValues();
            GD_optArgs.setValue(val.getSymbol(OptArgs[0]));
            GD_data.setText(Data[0]);
        }
    }

    public void getData(ActionEvent actionEvent) {
        Integer dataItem = new GD_dataItemValues().getInteger(GD_dataItem.getValue());
        Values val;
        if (dataItem == FiscalPrinterConst.FPTR_GD_TENDER)
            val = new GDTenderValues();
        else if (dataItem == FiscalPrinterConst.FPTR_GD_DESCRIPTION_LENGTH)
            val = new GDDescriptionLengthValues();
        else if (dataItem == FiscalPrinterConst.FPTR_GD_LINECOUNT)
            val = new GDLinecountValues();
        else
            val = new IntValues();
        Integer optArgs = val.getInteger(GD_optArgs.getValue());
        if (!invalid(dataItem, "dataItem") && !invalid(optArgs, "optArgs"))
            new GetData(dataItem, optArgs, GD_data.getText()).start();
    }

    class GetDate extends MethodProcessor {
        String[] Date = new String[1];
        GetDate(String date) {
            super("GetDate");
            Date[0] = date;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.getDate(Date);
        }
        @Override
        void finish() {
            updateGui();
            GD_date.setText(Date[0]);
        }
    }

    public void getDate(ActionEvent actionEvent) {
        new GetDate(GD_date.getText()).start();
    }

    class GetTotalizer extends MethodProcessor {
        private final int VatID;
        int OptArgs;
        String[] Data = new String[1];
        GetTotalizer(int vatID, int optArgs, String data) {
            super("GetTotalizer");
            VatID = vatID;
            OptArgs = optArgs;
            Data[0] = data;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.getTotalizer(VatID, OptArgs, Data);
        }
        @Override
        void finish() {
            updateGui();
            GT_data.setText(Data[0]);
        }
    }

    public void getTotalizer(ActionEvent actionEvent) {
        Integer vatID = new IntValues().getInteger(GT_vatID.getValue());
        Integer optArgs = new GT_optArgsValues().getInteger(GT_optArgs.getValue());
        if (!invalid(vatID, "vatID") && !invalid(optArgs, "optArgs"))
            new GetTotalizer(vatID, optArgs, GT_data.getText()).start();
    }

    class GetVatEntry extends MethodProcessor {
        private final int VatID;
        int OptArgs;
        int[] VatRate = new int[1];
        GetVatEntry(int vatID, int optArgs, int vatRate) {
            super("GetVatEntry");
            VatID = vatID;
            OptArgs = optArgs;
            VatRate[0] = vatRate;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.getVatEntry(VatID, OptArgs, VatRate);
        }
        @Override
        void finish() {
            updateGui();
            GVE_vatRate.setText(new BigDecimal(VatRate[0]).scaleByPowerOfTen(-4).toString());
        }
    }

    public void getVatEntry(ActionEvent actionEvent) {
        Integer vatID = new IntValues().getInteger(GVE_vatID.getValue());
        Integer optArgs = new IntValues().getInteger(GVE_optArgs.getText());
        Long vatRate = fromDecimal(GVE_vatRate.getText(), "4");
        if (!invalid(vatID, "vatID") && !invalid(optArgs, "optArgs") && validInt(vatRate, "vatRate", "4"))
            new GetVatEntry(vatID, optArgs, vatRate.intValue()).start();
    }

    class SetVatValue extends EndInsertion {
        private int VatID;
        private String VatRate;
        SetVatValue(int vatID, String vatRate) {
            super();
            VatID = vatID;
            VatRate = vatRate;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setVatValue(VatID, VatRate);
        }
    }

    public void setVatValue(ActionEvent actionEvent) {
        Integer vatID = new IntValues().getInteger(SVV_vatID.getValue());
        String vatRate = SVV_vatRate.getText();
        Long lrate = fromDecimal(vatRate, "4");
        if (SVV_intRate.isSelected() && lrate != null)
            vatRate = Long.toString(lrate);
        if (!invalid(vatID, "vatID"))
            new SetVatValue(vatID, vatRate).start();
    }

    class SetVatTable extends EndInsertion {
        @Override
        void runIt() throws JposException {
            ThePrinter.setVatTable();
        }
    }

    public void setVatTable(ActionEvent actionEvent) {
        new SetVatTable().start();
    }

    class SetCurrency extends EndInsertion {
        private int NewCurrency;

        SetCurrency(int newCurrency) {
            super();
            NewCurrency = newCurrency;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setCurrency(NewCurrency);
        }
    }

    public void setCurrency(ActionEvent actionEvent) {
        Integer newCurrency = new SC_newCurrencyValues().getInteger(SC_newCurrency.getValue());
        if (!invalid(newCurrency, "newCurrency"))
            new SetCurrency(newCurrency).start();
    }

    class SetDate extends EndInsertion {
        String Date;

        SetDate(String date) {
            super();
            Date = date;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setDate(Date);
        }
    }

    public void setDate(ActionEvent actionEvent) {
        new SetDate(SD_date.getText()).start();
    }

    class SetStoreFiscalID extends SetDate {
        SetStoreFiscalID(String id) {
            super(id);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setStoreFiscalID(Date);
        }
    }

    public void setStoreFiscalID(ActionEvent actionEvent) {
        new SetStoreFiscalID(SSFID_ID.getText()).start();
    }

    class SetHeaderLine extends SetDate {
        final int LineNumber;
        final boolean DoubleWidth;

        SetHeaderLine(int lineNumber, String text, boolean doubleWidth) {
            super(text);
            LineNumber = lineNumber;
            DoubleWidth = doubleWidth;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setHeaderLine(LineNumber, Date, DoubleWidth);
        }
    }

    public void setHeaderLine(ActionEvent actionEvent) {
        Integer lineNumber = new IntValues().getInteger(SHL_lineNumber.getValue());
        if (!invalid(lineNumber, "lineNumber"))
            new SetHeaderLine(lineNumber, SHL_text.getText(), SHL_doubleWidth.isSelected()).start();
    }

    class SetTrailerLine extends SetHeaderLine {
        SetTrailerLine(int lineNumber, String text, boolean doubleWidth) {
            super(lineNumber, text, doubleWidth);
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setTrailerLine(LineNumber, Date, DoubleWidth);
        }
    }

    public void setTrailerLine(ActionEvent actionEvent) {
        Integer lineNumber = new IntValues().getInteger(STL_lineNumber.getValue());
        if (!invalid(lineNumber, "lineNumber"))
            new SetTrailerLine(lineNumber, STL_text.getText(), STL_doubleWidth.isSelected()).start();
    }

    class SetPOSID extends SetDate {
        private String CashierID;

        SetPOSID(String posID, String cashierID) {
            super(posID);
            CashierID = cashierID;
        }
        @Override
        void runIt() throws JposException {
            ThePrinter.setPOSID(Date, CashierID);
        }
    }

    public void setPOSID(ActionEvent actionEvent) {
        new SetPOSID(SP_POSID.getText(), SP_cashierID.getText()).start();
    }

    public void setupPrintRec(ActionEvent actionEvent) {
        int recIndex;
        for (recIndex = 0; recIndex < PrintRec.getItems().size(); recIndex++) {
            if (PrintRec.getValue().equals(PrintRec.getItems().get(recIndex)))
                break;
        }
        if (recIndex == 0 || recIndex == 1 || recIndex == 6 || recIndex == 7) {
            // PrintRecItem, PrintRecItemVoid, PrintRecItemRefund, PrintRecItemRefundVoid
            setVisible(new Control[]{PR_specialTax, PR_specialTaxName, PR_adjustmentType, PR_amount}, false);
            PR_description.setLayoutY(65);
            PR_price.setPromptText(recIndex < 2 ? "price" : "amount");
            PR_vatInfo.setLayoutY(140);
            setVisible(new Control[]{PR_description, PR_price, PR_quantity, PR_vatInfo, PR_unitPrice, PR_unitName}, true);
        } else if (recIndex == 8 || recIndex == 9) {
            // PrintRecRefund, PrintRecRefundVoid
            setVisible(new Control[]{PR_specialTax, PR_specialTaxName, PR_adjustmentType, PR_amount, PR_quantity, PR_unitPrice, PR_unitName}, false);
            PR_description.setLayoutY(65);
            PR_price.setPromptText("amount");
            PR_vatInfo.setLayoutY(115);
            setVisible(new Control[]{PR_description, PR_price, PR_vatInfo}, true);
        } else if (recIndex == 2 || recIndex == 3) {
            // PrintRecItemAdjustment, PrintRecItemAdjustmentVoid
            setVisible(new Control[]{PR_specialTax, PR_specialTaxName, PR_quantity, PR_unitPrice, PR_unitName, PR_price}, false);
            PR_description.setLayoutY(90);
            PR_vatInfo.setLayoutY(140);
            setVisible(new Control[]{PR_description, PR_adjustmentType, PR_amount, PR_vatInfo}, true);
        } else if (recIndex == 4) {
            // PrintRecItemFuel
            setVisible(new Control[]{PR_adjustmentType, PR_amount}, false);
            PR_description.setLayoutY(65);
            PR_price.setPromptText(recIndex < 2 ? "price" : "amount");
            PR_vatInfo.setLayoutY(140);
            PR_specialTax.setLayoutY(215);
            PR_specialTaxName.setLayoutY(240);
            setVisible(new Control[]{PR_specialTax, PR_specialTaxName, PR_description, PR_price, PR_quantity, PR_vatInfo, PR_unitPrice, PR_unitName}, true);
        } else if (recIndex == 5) {
            // PrintRecItemFuelVoid
            setVisible(new Control[]{PR_adjustmentType, PR_amount, PR_specialTaxName, PR_quantity, PR_unitPrice, PR_unitName}, false);
            PR_description.setLayoutY(65);
            PR_price.setPromptText(recIndex < 2 ? "price" : "amount");
            PR_vatInfo.setLayoutY(115);
            PR_specialTax.setLayoutY(140);
            setVisible(new Control[]{PR_specialTax, PR_description, PR_price, PR_vatInfo}, true);
        }
    }

    void setVisible(Control[] controls, boolean visible) {
        for (Control control : controls)
            control.setVisible(visible);
    }

    public void setupPrintRecPackage(ActionEvent actionEvent) {
        if (PrintRecPackage.getValue().equals(PrintRecPackage.getItems().get(0))) {
            PRP_description.setVisible(true);
            PRP_vatAdjustment.setLayoutY(115);
        } else {
            PRP_description.setVisible(false);
            PRP_vatAdjustment.setLayoutY(90);
        }
    }

    public void setupPrintRecSubtotal(ActionEvent actionEvent) {
        if (PrintRecSubtotal.getValue().equals(PrintRecSubtotal.getItems().get(0))) {
            // PrintRecSubtotal
            setVisible(new Control[]{PRS_adjustmentType, PRS_description, PRS_adjustment}, false);
            PRS_amount.setVisible(true);
        } else if (PrintRecSubtotal.getValue().equals(PrintRecSubtotal.getItems().get(1))) {
            // PrintRecSubtotalAdjustment
            PRS_amount.setVisible(false);
            PRS_adjustment.setLayoutY(115);
            setVisible(new Control[]{PRS_adjustmentType, PRS_description, PRS_adjustment}, true);
        } else {
            // PrintRecSubtotalAdjustVoid
            setVisible(new Control[]{PRS_description, PRS_amount},false);
            PRS_adjustment.setLayoutY(90);
            setVisible(new Control[]{PRS_adjustmentType, PRS_adjustment},true);
        }
    }

    public void setupPrintRecVoid(ActionEvent actionEvent) {
        boolean enable = PrintRecVoid.getValue().equals(PrintRecVoid.getItems().get(1));
        PRV_amount.setVisible(enable);
        PRV_quantity.setVisible(enable);
        PRV_adjustmentType.setVisible(enable);
        PRV_adjustment.setVisible(enable);
        PRV_vatInfo.setVisible(enable);
    }

    ChangeListener<Boolean> PRV_adjustmentListener = null;

    public void setPRVAdjustmentType(ActionEvent actionEvent) {
        setNewListener(PRV_adjustmentListener, PRV_adjustmentType, PRV_adjustment);
    }

    ChangeListener<Boolean> PR_amountListener = null;

    public void setPRAdjustmentType(ActionEvent actionEvent) {
        setNewListener(PR_amountListener, PR_adjustmentType, PR_amount);
    }

    ChangeListener<Boolean> PRS_amountListener = null;

    public void setPRSAdjustmentType(ActionEvent actionEvent) {
        setNewListener(PRS_amountListener, PRS_adjustmentType, PRS_adjustment);
    }

    ChangeListener<Boolean> setNewListener(ChangeListener listener, ComboBox<String> type, TextField adjustment) {
        if (listener != null)
            adjustment.focusedProperty().removeListener(listener);
        if (isPercentage(type)) {
            adjustment.focusedProperty().addListener(listener = new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue ov, Boolean oldv, Boolean newv) {
                    if (!newv) {
                        formatDecimalTextField(adjustment, 4);
                    }
                }
            });
            formatDecimalTextField(adjustment, 4);
        } else {
            adjustment.focusedProperty().addListener(listener = new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue ov, Boolean oldv, Boolean newv) {
                    if (!newv) {
                        Integer val = new IntValues().getInteger(AmountDecimalPlacesRow.getValue());
                        formatDecimalTextField(adjustment, val == null ? 0 : val);
                    }
                }
            });
            Integer val = new IntValues().getInteger(AmountDecimalPlacesRow.getValue());
            formatDecimalTextField(adjustment, val == null ? 0 : val);
        }
        return listener;
    }

    public void verifyGDOptArgs(ActionEvent actionEvent) {
        String dataItem = GD_dataItem.getValue();
        String current = GD_optArgs.getValue();
        Values val = new GD_dataItemValues();
        GD_optArgs.getItems().clear();
        if (val.getSymbol(FiscalPrinterConst.FPTR_GD_TENDER).equals(dataItem)) {
            val = new GDTenderValues();
        } else if (val.getSymbol(FiscalPrinterConst.FPTR_GD_LINECOUNT).equals(dataItem)) {
            val = new GDLinecountValues();
        } else  if (val.getSymbol(FiscalPrinterConst.FPTR_GD_DESCRIPTION_LENGTH).equals(dataItem)) {
            val = new GDDescriptionLengthValues();
        } else
            return;
        for (int i = 1; i < val.ValueList.length; i += 2) {
            GD_optArgs.getItems().add(val.ValueList[i].toString());
            if (val.ValueList[i].equals(current))
                GD_optArgs.setValue(current);
        }
    }

    public void setVatIDs(ActionEvent actionEvent) {
        Integer start = new IntValues().getInteger(RVV_start.getText());
        Integer end = new IntValues().getInteger(RVV_stop.getText());
        String[] optArgValues = (VatIDs.getText() == null || VatIDs.getText().equals("") ? "0" : VatIDs.getText()).split(",");
        int[] optArgs = new int[optArgValues.length];
        for (int i = 0; i < optArgs.length; i++) {
            try {
                optArgs[i] = Integer.parseInt(optArgValues[i]);
            } catch (Exception e) {
                optArgs = null;
            }
        }
        if (invalid(start, "start") || invalid(end, "stop") || invalid(optArgs, "'list of optArg'"))
            return;
        Integer count = new IntValues().getInteger(NumVatRatesRow.getValue());
        if ("true".equals(CapHasVatTableRow.getValue()) && count != null) {
            String vatids = "";
            for (int i = start; i <= end && count > 0; i++) {
                int j;
                for (j = 0; j < optArgs.length; j++) {
                    try {
                        int[] rate = new int[1];
                        ThePrinter.getVatEntry(i, optArgs[j], rate);
                        break;
                    } catch (JposException e) {
                        if (e.getErrorCode() != JposConst.JPOS_E_ILLEGAL) {
                            getFullErrorMessageAndPrintTrace(e);
                            return;
                        }
                    }
                }
                if (j < optArgs.length) {
                    --count;
                    vatids += "," + i;
                }
            }
            if (vatids.length() == 0) {
                myMessageDialog("No VAT IDs available in interval [" + start + "," + end + "].");
                VatIDs.setText("");
            } else {
                VatIDs.setText(vatids.substring(1));
                String[] ids = vatids.substring(1).split(",");
                ComboBox<String>[] boxes = new ComboBox[]{GT_vatID, GVE_vatID, SVV_vatID, BIL_vatID, VI_vatID};
                for (ComboBox<String> box : boxes) {
                    String current = box.getValue();
                    box.getItems().clear();
                    for (String id : ids) {
                        box.getItems().add(id);
                        if (id.equals(current))
                            box.setValue(current);
                    }
                }
                for (Label hint : new Label[]{ILlabel1, ILlabel2})
                    hint.setVisible(false);
            }
        } else {
            myMessageDialog("No VAT table available.");
            VatIDs.setText("");
        }
    }

    private class ActualCurrencyValues extends Values {
        ActualCurrencyValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_AC_BRC, "AC_BRC",
                    FiscalPrinterConst.FPTR_AC_BGL, "AC_BGL",
                    FiscalPrinterConst.FPTR_AC_EUR, "AC_EUR",
                    FiscalPrinterConst.FPTR_AC_GRD, "AC_GRD",
                    FiscalPrinterConst.FPTR_AC_HUF, "AC_HUF",
                    FiscalPrinterConst.FPTR_AC_ITL, "AC_ITL",
                    FiscalPrinterConst.FPTR_AC_PLZ, "AC_PLZ",
                    FiscalPrinterConst.FPTR_AC_ROL, "AC_ROL",
                    FiscalPrinterConst.FPTR_AC_RUR, "AC_RUR",
                    FiscalPrinterConst.FPTR_AC_TRL, "AC_TRL",
                    FiscalPrinterConst.FPTR_AC_CZK, "AC_CZK",
                    FiscalPrinterConst.FPTR_AC_UAH, "AC_UAH",
                    FiscalPrinterConst.FPTR_AC_SEK, "AC_SEK",
                    FiscalPrinterConst.FPTR_AC_OTHER, "AC_OTHER"
            };
        }
    }

    private class ContractorIdValues extends Values {
        ContractorIdValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_CID_FIRST, "CID_FIRST",
                    FiscalPrinterConst.FPTR_CID_SECOND, "CID_SECOND",
                    FiscalPrinterConst.FPTR_CID_SINGLE, "CID_SINGLE"
            };
        }
    }

    private class CountryCodeValues extends Values {
        CountryCodeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_CC_BRAZIL, "CC_BRASIL",
                    FiscalPrinterConst.FPTR_CC_GREECE, "CC_GREECE",
                    FiscalPrinterConst.FPTR_CC_HUNGARY, "CC_HUNGARY",
                    FiscalPrinterConst.FPTR_CC_ITALY, "CC_ITALY",
                    FiscalPrinterConst.FPTR_CC_POLAND, "CC_POLAND",
                    FiscalPrinterConst.FPTR_CC_TURKEY, "CC_TURKEY",
                    FiscalPrinterConst.FPTR_CC_RUSSIA, "CC_RUSSIA",
                    FiscalPrinterConst.FPTR_CC_BULGARIA, "CC_BULGARIA",
                    FiscalPrinterConst.FPTR_CC_ROMANIA, "CC_ROMANIA",
                    FiscalPrinterConst.FPTR_CC_CZECH_REPUBLIC, "CC_CZECH_REPUBLIC",
                    FiscalPrinterConst.FPTR_CC_UKRAINE, "CC_UKRAINE",
                    FiscalPrinterConst.FPTR_CC_SWEDEN, "CC_SWEDEN",
                    FiscalPrinterConst.FPTR_CC_GERMANY, "CC_GERMANY",
                    FiscalPrinterConst.FPTR_CC_OTHER, "CC_OTHER"
            };
        }
    }

    private class DateTypeValues extends Values {
        DateTypeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_DT_CONF, "DT_CONF",
                    FiscalPrinterConst.FPTR_DT_EOD, "DT_EOD",
                    FiscalPrinterConst.FPTR_DT_RESET, "DT_RESET",
                    FiscalPrinterConst.FPTR_DT_RTC, "DT_RTC",
                    FiscalPrinterConst.FPTR_DT_VAT, "DT_VAT",
                    FiscalPrinterConst.FPTR_DT_START, "DT_START",
                    FiscalPrinterConst.FPTR_DT_TICKET_START, "DT_TICKET_START",
                    FiscalPrinterConst.FPTR_DT_TICKET_END, "DT_TICKET_END"
            };
        }
    }

    private class ErrorLevelValues extends Values {
        ErrorLevelValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_EL_NONE, "EL_NONE",
                    FiscalPrinterConst.FPTR_EL_RECOVERABLE, "EL_RECOVERABLE",
                    FiscalPrinterConst.FPTR_EL_FATAL, "EL_FATAL",
                    FiscalPrinterConst.FPTR_EL_BLOCKED, "EL_BLOCKED"
            };
        }
    }

    private class PrinterStateValues extends Values {
        PrinterStateValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_PS_MONITOR, "PS_MONITOR",
                    FiscalPrinterConst.FPTR_PS_FISCAL_RECEIPT, "PS_FISCAL_RECEIPT",
                    FiscalPrinterConst.FPTR_PS_FISCAL_RECEIPT_TOTAL, "PS_FISCAL_RECEIPT_TOTAL",
                    FiscalPrinterConst.FPTR_PS_FISCAL_RECEIPT_ENDING, "PS_FISCAL_RECEIPT_ENDING",
                    FiscalPrinterConst.FPTR_PS_FISCAL_DOCUMENT, "PS_FISCAL_DOCUMENT",
                    FiscalPrinterConst.FPTR_PS_FIXED_OUTPUT, "PS_FIXED_OUTPUT",
                    FiscalPrinterConst.FPTR_PS_ITEM_LIST, "PS_ITEM_LIST",
                    FiscalPrinterConst.FPTR_PS_LOCKED, "PS_LOCKED",
                    FiscalPrinterConst.FPTR_PS_NONFISCAL, "PS_NONFISCAL",
                    FiscalPrinterConst.FPTR_PS_REPORT, "PS_REPORT"
            };
        }
    }

    private class ErrorStationValues extends Values {
        ErrorStationValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_S_JOURNAL, "S_JOURNAL",
                    FiscalPrinterConst.FPTR_S_RECEIPT, "S_RECEIPT",
                    FiscalPrinterConst.FPTR_S_SLIP, "S_SLIP",
                    FiscalPrinterConst.FPTR_S_JOURNAL_RECEIPT, "S_JOURNAL_RECEIPT",
                    FiscalPrinterConst.FPTR_S_JOURNAL_SLIP, "S_JOURNAL_SLIP",
                    FiscalPrinterConst.FPTR_S_RECEIPT_SLIP, "S_RECEIPT_SLIP"
            };
        }
    }

    private class FiscalReceiptStationValues extends Values {
        FiscalReceiptStationValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_RS_RECEIPT, "RS_RECEIPT",
                    FiscalPrinterConst.FPTR_RS_SLIP, "RS_SLIP"
            };
        }
    }

    private class FiscalReceiptTypeValues extends Values {
        FiscalReceiptTypeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_RT_CASH_IN, "RT_CASH_IN",
                    FiscalPrinterConst.FPTR_RT_CASH_OUT, "RT_CASH_OUT",
                    FiscalPrinterConst.FPTR_RT_GENERIC, "RT_GENERIC",
                    FiscalPrinterConst.FPTR_RT_SALES, "RT_SALES",
                    FiscalPrinterConst.FPTR_RT_SERVICE, "RT_SERVICE",
                    FiscalPrinterConst.FPTR_RT_SIMPLE_INVOICE, "RT_SIMPLE_INVOICE",
                    FiscalPrinterConst.FPTR_RT_REFUND, "RT_REFUND"
            };
        }
    }

    private class MessageTypeValues extends Values {
        MessageTypeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_MT_ADVANCE, "MT_ADVANCE",
                    FiscalPrinterConst.FPTR_MT_ADVANCE_PAID, "MT_ADVANCE_PAID",
                    FiscalPrinterConst.FPTR_MT_AMOUNT_TO_BE_PAID, "MT_AMOUNT_TO_BE_PAID",
                    FiscalPrinterConst.FPTR_MT_AMOUNT_TO_BE_PAID_BACK, "MT_AMOUNT_TO_BE_PAID_BACK",
                    FiscalPrinterConst.FPTR_MT_CARD, "MT_CARD",
                    FiscalPrinterConst.FPTR_MT_CARD_NUMBER, "MT_CARD_NUMBER",
                    FiscalPrinterConst.FPTR_MT_CARD_TYPE, "MT_CARD_TYPE",
                    FiscalPrinterConst.FPTR_MT_CASH, "MT_CASH",
                    FiscalPrinterConst.FPTR_MT_CASHIER, "MT_CASHIER",
                    FiscalPrinterConst.FPTR_MT_CASH_REGISTER_NUMBER, "MT_CASH_REGISTER_NUMBER",
                    FiscalPrinterConst.FPTR_MT_CHANGE, "MT_CHANGE",
                    FiscalPrinterConst.FPTR_MT_CHEQUE, "MT_CHEQUE",
                    FiscalPrinterConst.FPTR_MT_CLIENT_NUMBER, "MT_CLIENT_NUMBER",
                    FiscalPrinterConst.FPTR_MT_CLIENT_SIGNATURE, "MT_CLIENT_SIGNATURE",
                    FiscalPrinterConst.FPTR_MT_COUNTER_STATE, "MT_COUNTER_STATE",
                    FiscalPrinterConst.FPTR_MT_CREDIT_CARD, "MT_CREDIT_CARD",
                    FiscalPrinterConst.FPTR_MT_CURRENCY, "MT_CURRENCY",
                    FiscalPrinterConst.FPTR_MT_CURRENCY_VALUE, "MT_CURRENCY_VALUE",
                    FiscalPrinterConst.FPTR_MT_DEPOSIT, "MT_DEPOSIT",
                    FiscalPrinterConst.FPTR_MT_DEPOSIT_RETURNED, "MT_DEPOSIT_RETURNED",
                    FiscalPrinterConst.FPTR_MT_DOT_LINE, "MT_DOT_LINE",
                    FiscalPrinterConst.FPTR_MT_DRIVER_NUMB, "MT_DRIVER_NUMB",
                    FiscalPrinterConst.FPTR_MT_EMPTY_LINE, "MT_EMPTY_LINE",
                    FiscalPrinterConst.FPTR_MT_FREE_TEXT, "MT_FREE_TEXT",
                    FiscalPrinterConst.FPTR_MT_FREE_TEXT_WITH_DAY_LIMIT, "MT_FREE_TEXT_WITH_DAY_LIMIT",
                    FiscalPrinterConst.FPTR_MT_GIVEN_DISCOUNT, "MT_GIVEN_DISCOUNT",
                    FiscalPrinterConst.FPTR_MT_LOCAL_CREDIT, "MT_LOCAL_CREDIT",
                    FiscalPrinterConst.FPTR_MT_MILEAGE_KM, "MT_MILEAGE_KM",
                    FiscalPrinterConst.FPTR_MT_NOTE, "MT_NOTE",
                    FiscalPrinterConst.FPTR_MT_PAID, "MT_PAID",
                    FiscalPrinterConst.FPTR_MT_PAY_IN, "MT_PAY_IN",
                    FiscalPrinterConst.FPTR_MT_POINT_GRANTED, "MT_POINT_GRANTED",
                    FiscalPrinterConst.FPTR_MT_POINTS_BONUS, "MT_POINTS_BONUS",
                    FiscalPrinterConst.FPTR_MT_POINTS_RECEIPT, "MT_POINTS_RECEIPT",
                    FiscalPrinterConst.FPTR_MT_POINTS_TOTAL, "MT_POINTS_TOTAL",
                    FiscalPrinterConst.FPTR_MT_PROFITED, "MT_PROFITED",
                    FiscalPrinterConst.FPTR_MT_RATE, "MT_RATE",
                    FiscalPrinterConst.FPTR_MT_REGISTER_NUMB, "MT_REGISTER_NUMB",
                    FiscalPrinterConst.FPTR_MT_SHIFT_NUMBER, "MT_SHIFT_NUMBER",
                    FiscalPrinterConst.FPTR_MT_STATE_OF_AN_ACCOUNT, "MT_STATE_OF_AN_ACCOUNT",
                    FiscalPrinterConst.FPTR_MT_SUBSCRIPTION, "MT_SUBSCRIPTION",
                    FiscalPrinterConst.FPTR_MT_TABLE, "MT_TABLE",
                    FiscalPrinterConst.FPTR_MT_THANK_YOU_FOR_LOYALTY, "MT_THANK_YOU_FOR_LOYALTY",
                    FiscalPrinterConst.FPTR_MT_TRANSACTION_NUMB, "MT_TRANSACTION_NUMB",
                    FiscalPrinterConst.FPTR_MT_VALID_TO, "MT_VALID_TO",
                    FiscalPrinterConst.FPTR_MT_VOUCHER, "MT_VOUCHER",
                    FiscalPrinterConst.FPTR_MT_VOUCHER_PAID, "MT_VOUCHER_PAID",
                    FiscalPrinterConst.FPTR_MT_VOUCHER_VALUE, "MT_VOUCHER_VALUE",
                    FiscalPrinterConst.FPTR_MT_WITH_DISCOUNT, "MT_WITH_DISCOUNT",
                    FiscalPrinterConst.FPTR_MT_WITHOUT_UPLIFT, "MT_WITHOUT_UPLIFT"
            };
        }
    }

    private class SlipSelectionValues extends Values {
        SlipSelectionValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_SS_FULL_LENGTH, "SS_FULL_LENGTH",
                    FiscalPrinterConst.FPTR_SS_VALIDATION, "SS_VALIDATION"
            };
        }
    }

    private class TotalizerTypeValues extends Values {
        TotalizerTypeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_TT_DOCUMENT, "TT_DOCUMENT",
                    FiscalPrinterConst.FPTR_TT_DAY, "TT_DAY",
                    FiscalPrinterConst.FPTR_TT_RECEIPT, "TT_RECEIPT",
                    FiscalPrinterConst.FPTR_TT_GRAND, "TT_GRAND"
            };
        }
    }

    private class PrtStatusUpdateValues extends StatusUpdateValues {
        PrtStatusUpdateValues() {
            super();
            Object[] prtvalues = new Object[]{
                    FiscalPrinterConst.FPTR_SUE_IDLE, "SUE_IDLE",
                    FiscalPrinterConst.FPTR_SUE_COVER_OPEN, "SUE_COVER_OPEN",
                    FiscalPrinterConst.FPTR_SUE_COVER_OK, "SUE_COVER_OK",
                    FiscalPrinterConst.FPTR_SUE_JRN_EMPTY, "SUE_JRN_EMPTY",
                    FiscalPrinterConst.FPTR_SUE_JRN_NEAREMPTY, "SUE_JRN_NEAREMPTY",
                    FiscalPrinterConst.FPTR_SUE_JRN_PAPEROK, "SUE_JRN_PAPEROK",
                    FiscalPrinterConst.FPTR_SUE_REC_EMPTY, "SUE_REC_EMPTY",
                    FiscalPrinterConst.FPTR_SUE_REC_NEAREMPTY, "SUE_REC_NEAREMPTY",
                    FiscalPrinterConst.FPTR_SUE_REC_PAPEROK, "SUE_REC_PAPEROK",
                    FiscalPrinterConst.FPTR_SUE_SLP_EMPTY, "SUE_SLP_EMPTY",
                    FiscalPrinterConst.FPTR_SUE_SLP_NEAREMPTY, "SUE_SLP_NEAREMPTY",
                    FiscalPrinterConst.FPTR_SUE_SLP_PAPEROK, "SUE_SLP_PAPEROK",
                    FiscalPrinterConst.FPTR_SUE_JRN_COVER_OPEN, "SUE_JRN_COVER_OPEN",
                    FiscalPrinterConst.FPTR_SUE_JRN_COVER_OK, "SUE_JRN_COVER_OK",
                    FiscalPrinterConst.FPTR_SUE_REC_COVER_OPEN, "SUE_REC_COVER_OPEN",
                    FiscalPrinterConst.FPTR_SUE_REC_COVER_OK, "SUE_REC_COVER_OK",
                    FiscalPrinterConst.FPTR_SUE_SLP_COVER_OPEN, "SUE_SLP_COVER_OPEN",
                    FiscalPrinterConst.FPTR_SUE_SLP_COVER_OK, "SUE_SLP_COVER_OK"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + prtvalues.length);
            System.arraycopy(prtvalues, 0, ValueList, ValueList.length - prtvalues.length, prtvalues.length);
        }
    }

    private class ExtendedErrorCodeValues extends Values {
        ExtendedErrorCodeValues() {
            ValueList = new Object[] {
                    FiscalPrinterConst.JPOS_EFPTR_COVER_OPEN, "EFPTR_COVER_OPEN",
                    FiscalPrinterConst.JPOS_EFPTR_JRN_EMPTY, "EFPTR_JRN_EMPTY",
                    FiscalPrinterConst.JPOS_EFPTR_REC_EMPTY, "EFPTR_REC_EMPTY",
                    FiscalPrinterConst.JPOS_EFPTR_SLP_EMPTY, "EFPTR_SLP_EMPTY",
                    FiscalPrinterConst.JPOS_EFPTR_SLP_FORM, "EFPTR_SLP_FORM",
                    FiscalPrinterConst.JPOS_EFPTR_MISSING_DEVICES, "EFPTR_MISSING_DEVICES",
                    FiscalPrinterConst.JPOS_EFPTR_WRONG_STATE, "EFPTR_WRONG_STATE",
                    FiscalPrinterConst.JPOS_EFPTR_TECHNICAL_ASSISTANCE, "EFPTR_TECHNICAL_ASSISTANCE",
                    FiscalPrinterConst.JPOS_EFPTR_CLOCK_ERROR, "EFPTR_CLOCK_ERROR",
                    FiscalPrinterConst.JPOS_EFPTR_FISCAL_MEMORY_FULL, "EFPTR_FISCAL_MEMORY_FULL",
                    FiscalPrinterConst.JPOS_EFPTR_FISCAL_MEMORY_DISCONNECTED, "EFPTR_FISCAL_MEMORY_DISCONNECTED",
                    FiscalPrinterConst.JPOS_EFPTR_FISCAL_TOTALS_ERROR, "EFPTR_FISCAL_TOTALS_ERROR",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_ITEM_QUANTITY, "EFPTR_BAD_ITEM_QUANTITY",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_ITEM_AMOUNT, "EFPTR_BAD_ITEM_AMOUNT",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_ITEM_DESCRIPTION, "EFPTR_BAD_ITEM_DESCRIPTION",
                    FiscalPrinterConst.JPOS_EFPTR_RECEIPT_TOTAL_OVERFLOW, "EFPTR_RECEIPT_TOTAL_OVERFLOW",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_VAT, "EFPTR_BAD_VAT",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_PRICE, "EFPTR_BAD_PRICE",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_DATE, "EFPTR_BAD_DATE",
                    FiscalPrinterConst.JPOS_EFPTR_NEGATIVE_TOTAL, "EFPTR_NEGATIVE_TOTAL",
                    FiscalPrinterConst.JPOS_EFPTR_WORD_NOT_ALLOWED, "EFPTR_WORD_NOT_ALLOWED",
                    FiscalPrinterConst.JPOS_EFPTR_BAD_LENGTH, "EFPTR_BAD_LENGTH",
                    FiscalPrinterConst.JPOS_EFPTR_MISSING_SET_CURRENCY, "EFPTR_MISSING_SET_CURRENCY",
                    FiscalPrinterConst.JPOS_EFPTR_DAY_END_REQUIRED, "EFPTR_DAY_END_REQUIRED"
            };
        }
    }

    private class AdjustmentTypeValues extends Values {
        AdjustmentTypeValues() {
            ValueList = new Object[] {
                    FiscalPrinterConst.FPTR_AT_AMOUNT_DISCOUNT, "AT_AMOUNT_DISCOUNT",
                    FiscalPrinterConst.FPTR_AT_AMOUNT_SURCHARGE, "AT_AMOUNT_SURCHARGE",
                    FiscalPrinterConst.FPTR_AT_PERCENTAGE_DISCOUNT, "AT_PERCENTAGE_DISCOUNT",
                    FiscalPrinterConst.FPTR_AT_PERCENTAGE_SURCHARGE, "AT_PERCENTAGE_SURCHARGE",
                    FiscalPrinterConst.FPTR_AT_COUPON_AMOUNT_DISCOUNT, "AT_COUPON_AMOUNT_DISCOUNT",
                    FiscalPrinterConst.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT, "AT_COUPON_PERCENTAGE_DISCOUNT"
            };
        }
    }

    private class PackageAdjustmentTypeValues extends Values {
        PackageAdjustmentTypeValues() {
            if (TheService != null && TheService.AllowItemAdjustmentTypesInPackageAdjustment)
                ValueList = new AdjustmentTypeValues().ValueList;
            else {
                ValueList = new Object[]{
                        FiscalPrinterConst.FPTR_AT_DISCOUNT, "AT_DISCOUNT",
                        FiscalPrinterConst.FPTR_AT_SURCHARGE, "AT_SURCHARGE"
                };
            }
        }
    }

    private class PN_stationValues extends BFO_stationValues {
        PN_stationValues() {
            super();
            int index = ValueList.length;
            ValueList = Arrays.copyOf(ValueList, index + 2);
            ValueList[index++] = FiscalPrinterConst.FPTR_S_JOURNAL;
            ValueList[index++] ="S_JOURNAL";
        }
    }

    private class BFO_stationValues extends Values {
        BFO_stationValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_S_RECEIPT, "S_RECEIPT",
                    FiscalPrinterConst.FPTR_S_SLIP, "S_SLIP"
            };
        }
    }

    private class PR_reportTypeValues extends Values {
        PR_reportTypeValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_RT_ORDINAL, "RT_ORDINAL",
                    FiscalPrinterConst.FPTR_RT_DATE, "RT_DATE",
                    FiscalPrinterConst.FPTR_RT_EOD_ORDINAL, "RT_EOD_ORDINAL"
            };
        }
    }

    private class GD_dataItemValues extends Values {
        GD_dataItemValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_GD_FIRMWARE, "GD_FIRMWARE",
                    FiscalPrinterConst.FPTR_GD_PRINTER_ID, "GD_PRINTER_ID",
                    FiscalPrinterConst.FPTR_GD_CURRENT_TOTAL, "GD_CURRENT_TOTAL",
                    FiscalPrinterConst.FPTR_GD_DAILY_TOTAL, "GD_DAILY_TOTAL",
                    FiscalPrinterConst.FPTR_GD_GRAND_TOTAL, "GD_GRAND_TOTAL",
                    FiscalPrinterConst.FPTR_GD_MID_VOID, "GD_MID_VOID",
                    FiscalPrinterConst.FPTR_GD_NOT_PAID, "GD_NOT_PAID",
                    FiscalPrinterConst.FPTR_GD_RECEIPT_NUMBER, "GD_RECEIPT_NUMBER",
                    FiscalPrinterConst.FPTR_GD_REFUND, "GD_REFUND",
                    FiscalPrinterConst.FPTR_GD_REFUND_VOID, "GD_REFUND_VOID",
                    FiscalPrinterConst.FPTR_GD_NUMB_CONFIG_BLOCK, "GD_NUMB_CONFIG_BLOCK",
                    FiscalPrinterConst.FPTR_GD_NUMB_CURRENCY_BLOCK, "GD_NUMB_CURRENCY_BLOCK",
                    FiscalPrinterConst.FPTR_GD_NUMB_HDR_BLOCK, "GD_NUMB_HDR_BLOCK",
                    FiscalPrinterConst.FPTR_GD_NUMB_RESET_BLOCK, "GD_NUMB_RESET_BLOCK",
                    FiscalPrinterConst.FPTR_GD_NUMB_VAT_BLOCK, "GD_NUMB_VAT_BLOCK",
                    FiscalPrinterConst.FPTR_GD_FISCAL_DOC, "GD_FISCAL_DOC",
                    FiscalPrinterConst.FPTR_GD_FISCAL_DOC_VOID, "GD_FISCAL_DOC_VOID",
                    FiscalPrinterConst.FPTR_GD_FISCAL_REC, "GD_FISCAL_REC",
                    FiscalPrinterConst.FPTR_GD_FISCAL_REC_VOID, "GD_FISCAL_REC_VOID",
                    FiscalPrinterConst.FPTR_GD_NONFISCAL_DOC, "GD_NONFISCAL_DOC",
                    FiscalPrinterConst.FPTR_GD_NONFISCAL_DOC_VOID, "GD_NONFISCAL_DOC_VOID",
                    FiscalPrinterConst.FPTR_GD_NONFISCAL_REC, "GD_NONFISCAL_REC",
                    FiscalPrinterConst.FPTR_GD_RESTART, "GD_RESTART",
                    FiscalPrinterConst.FPTR_GD_SIMP_INVOICE, "GD_SIMP_INVOICE",
                    FiscalPrinterConst.FPTR_GD_Z_REPORT, "GD_Z_REPORT",
                    FiscalPrinterConst.FPTR_GD_TENDER, "GD_TENDER",
                    FiscalPrinterConst.FPTR_GD_LINECOUNT, "GD_LINECOUNT",
                    FiscalPrinterConst.FPTR_GD_DESCRIPTION_LENGTH, "GD_DESCRIPTION_LENGTH"
            };
        }
    }

    private class GDTenderValues extends Values {
        GDTenderValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_PDL_CASH, "PDL_CASH",
                    FiscalPrinterConst.FPTR_PDL_CHEQUE, "PDL_CHEQUE",
                    FiscalPrinterConst.FPTR_PDL_CHITTY, "PDL_CHITTY",
                    FiscalPrinterConst.FPTR_PDL_COUPON, "PDL_COUPON",
                    FiscalPrinterConst.FPTR_PDL_CURRENCY, "PDL_CURRENCY",
                    FiscalPrinterConst.FPTR_PDL_DRIVEN_OFF, "PDL_DRIVEN_OFF",
                    FiscalPrinterConst.FPTR_PDL_EFT_IMPRINTER, "PDL_EFT_IMPRINTER",
                    FiscalPrinterConst.FPTR_PDL_EFT_TERMINAL, "PDL_EFT_TERMINAL",
                    FiscalPrinterConst.FPTR_PDL_TERMINAL_IMPRINTER, "PDL_TERMINAL_IMPRINTER",
                    FiscalPrinterConst.FPTR_PDL_FREE_GIFT, "PDL_FREE_GIFT",
                    FiscalPrinterConst.FPTR_PDL_GIRO, "PDL_GIRO",
                    FiscalPrinterConst.FPTR_PDL_HOME, "PDL_HOME",
                    FiscalPrinterConst.FPTR_PDL_IMPRINTER_WITH_ISSUER, "PDL_IMPRINTER_WITH_ISSUER",
                    FiscalPrinterConst.FPTR_PDL_LOCAL_ACCOUNT, "PDL_LOCAL_ACCOUNT",
                    FiscalPrinterConst.FPTR_PDL_LOCAL_ACCOUNT_CARD, "PDL_LOCAL_ACCOUNT_CARD",
                    FiscalPrinterConst.FPTR_PDL_PAY_CARD, "PDL_PAY_CARD",
                    FiscalPrinterConst.FPTR_PDL_PAY_CARD_MANUAL, "PDL_PAY_CARD_MANUAL",
                    FiscalPrinterConst.FPTR_PDL_PREPAY, "PDL_PREPAY",
                    FiscalPrinterConst.FPTR_PDL_PUMP_TEST, "PDL_PUMP_TEST",
                    FiscalPrinterConst.FPTR_PDL_SHORT_CREDIT, "PDL_SHORT_CREDIT",
                    FiscalPrinterConst.FPTR_PDL_STAFF, "PDL_STAFF",
                    FiscalPrinterConst.FPTR_PDL_VOUCHER, "PDL_VOUCHER"
            };
        }
    }

    private class GDLinecountValues extends Values {
        GDLinecountValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_LC_ITEM, "LC_ITEM",
                    FiscalPrinterConst.FPTR_LC_ITEM_VOID, "LC_ITEM_VOID",
                    FiscalPrinterConst.FPTR_LC_DISCOUNT, "LC_DISCOUNT",
                    FiscalPrinterConst.FPTR_LC_DISCOUNT_VOID, "LC_DISCOUNT_VOID",
                    FiscalPrinterConst.FPTR_LC_SURCHARGE, "LC_SURCHARGE",
                    FiscalPrinterConst.FPTR_LC_SURCHARGE_VOID, "LC_SURCHARGE_VOID",
                    FiscalPrinterConst.FPTR_LC_REFUND, "LC_REFUND",
                    FiscalPrinterConst.FPTR_LC_REFUND_VOID, "LC_REFUND_VOID",
                    FiscalPrinterConst.FPTR_LC_SUBTOTAL_DISCOUNT, "LC_SUBTOTAL_DISCOUNT",
                    FiscalPrinterConst.FPTR_LC_SUBTOTAL_DISCOUNT_VOID, "LC_SUBTOTAL_DISCOUNT_VOID",
                    FiscalPrinterConst.FPTR_LC_SUBTOTAL_SURCHARGE, "LC_SUBTOTAL_SURCHARGE",
                    FiscalPrinterConst.FPTR_LC_SUBTOTAL_SURCHARGE_VOID, "LC_SUBTOTAL_SURCHARGE_VOID",
                    FiscalPrinterConst.FPTR_LC_COMMENT, "LC_COMMENT",
                    FiscalPrinterConst.FPTR_LC_SUBTOTAL, "LC_SUBTOTAL",
                    FiscalPrinterConst.FPTR_LC_TOTAL, "LC_TOTAL"
            };
        }
    }

    private class GDDescriptionLengthValues extends Values {
        GDDescriptionLengthValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_DL_ITEM, "DL_ITEM",
                    FiscalPrinterConst.FPTR_DL_ITEM_ADJUSTMENT, "DL_ITEM_ADJUSTMENT",
                    FiscalPrinterConst.FPTR_DL_ITEM_FUEL, "DL_ITEM_FUEL",
                    FiscalPrinterConst.FPTR_DL_ITEM_FUEL_VOID, "DL_ITEM_FUEL_VOID",
                    FiscalPrinterConst.FPTR_DL_NOT_PAID, "DL_NOT_PAID",
                    FiscalPrinterConst.FPTR_DL_PACKAGE_ADJUSTMENT, "DL_PACKAGE_ADJUSTMENT",
                    FiscalPrinterConst.FPTR_DL_REFUND, "DL_REFUND",
                    FiscalPrinterConst.FPTR_DL_REFUND_VOID, "DL_REFUND_VOID",
                    FiscalPrinterConst.FPTR_DL_SUBTOTAL_ADJUSTMENT, "DL_SUBTOTAL_ADJUSTMENT",
                    FiscalPrinterConst.FPTR_DL_TOTAL, "DL_TOTAL",
                    FiscalPrinterConst.FPTR_DL_VOID, "DL_VOID",
                    FiscalPrinterConst.FPTR_DL_VOID_ITEM, "DL_VOID_ITEM"
            };
        }
    }

    private class GT_optArgsValues extends Values {
        GT_optArgsValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_GT_GROSS, "GT_GROSS",
                    FiscalPrinterConst.FPTR_GT_NET, "GT_NET",
                    FiscalPrinterConst.FPTR_GT_DISCOUNT, "GT_DISCOUNT",
                    FiscalPrinterConst.FPTR_GT_DISCOUNT_VOID, "GT_DISCOUNT_VOID",
                    FiscalPrinterConst.FPTR_GT_ITEM, "GT_ITEM",
                    FiscalPrinterConst.FPTR_GT_ITEM_VOID, "GT_ITEM_VOID",
                    FiscalPrinterConst.FPTR_GT_NOT_PAID, "GT_NOT_PAID",
                    FiscalPrinterConst.FPTR_GT_REFUND, "GT_REFUND",
                    FiscalPrinterConst.FPTR_GT_REFUND_VOID, "GT_REFUND_VOID",
                    FiscalPrinterConst.FPTR_GT_SUBTOTAL_DISCOUNT, "GT_SUBTOTAL_DISCOUNT",
                    FiscalPrinterConst.FPTR_GT_SUBTOTAL_DISCOUNT_VOID, "GT_SUBTOTAL_DISCOUNT_VOID",
                    FiscalPrinterConst.FPTR_GT_SUBTOTAL_SURCHARGES, "GT_SUBTOTAL_SURCHARGES",
                    FiscalPrinterConst.FPTR_GT_SUBTOTAL_SURCHARGES_VOID, "GT_SUBTOTAL_SURCHARGES_VOID",
                    FiscalPrinterConst.FPTR_GT_SURCHARGE, "GT_SURCHARGE",
                    FiscalPrinterConst.FPTR_GT_SURCHARGE_VOID, "GT_SURCHARGE_VOID",
                    FiscalPrinterConst.FPTR_GT_VAT, "GT_VAT",
                    FiscalPrinterConst.FPTR_GT_VAT_CATEGORY, "GT_VAT_CATEGORY"
            };
        }
    }

    private class SC_newCurrencyValues extends Values {
        SC_newCurrencyValues() {
            ValueList = new Object[]{
                    FiscalPrinterConst.FPTR_SC_EURO, "SC_EURO"
            };
        }
    }
}
