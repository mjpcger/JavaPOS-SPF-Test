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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import jpos.CAT;
import jpos.CATConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.DirectIOEvent;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GUI control for CAT properties, methods and events.
 */
public class CATController extends CommonController {
    public TextField A_sequenceNumber;
    public TextField A_amount;
    public TextField A_taxOthers;
    public ComboBox<String> A_timeout;
    public TextField AccountNumber;
    public TextField Balance;
    public TextField SettledAmount;
    public TextField ApprovalCode;
    public TextField CardCompanyID;
    public TextField CenterResultCode;
    public TextField LogStatus;
    public TextField SequenceNumber;
    public TextField SlipNumber;
    public TextField TransactionType;
    public TextField TransactionNumber;
    public TextField PaymentCondition;
    public ComboBox<String> PaymentMedia;
    public TextArea PaymentDetail;
    public TextArea AdditionalSecurityInformation;
    public CheckBox TrainingMode;
    public TextField ADL_sequenceNumber;
    public ComboBox<String> ADL_type;
    public ComboBox<String> ADL_timeout;
    public TextField CD_sequenceNumber;
    public TextField CD_amount;
    public ComboBox<String> CD_timeout;
    public TextField CC_sequenceNumber;
    public ComboBox<String> CC_timeout;
    public TextArea B_DirectIOEvent;
    public TextArea A_DirectIOEvent;
    public TextArea C_DirectIOEvent;
    public AnchorPane AuthorizeAnchor;
    public AnchorPane AdministrativeAnchor;
    public AnchorPane MaintenanceAnchor;
    public TextArea DailyLog;
    private CAT TheCAT;
    private PropertyTableRow AdditionalSecurityInformationRow;
    private PropertyTableRow BalanceRow;
    private PropertyTableRow SettledAmountRow;
    private PropertyTableRow AccountNumberRow;
    private PropertyTableRow ApprovalCodeRow;
    private PropertyTableRow CardCompanyIDRow;
    private PropertyTableRow CenterResultCodeRow;
    private PropertyTableRow DailyLogRow;
    private PropertyTableRow LogStatusRow;
    private PropertyTableRow PaymentConditionRow;
    private PropertyTableRow PaymentDetailRow;
    private PropertyTableRow PaymentMediaRow;
    private PropertyTableRow SequenceNumberRow;
    private PropertyTableRow SlipNumberRow;
    private PropertyTableRow TrainingModeRow;
    private PropertyTableRow TransactionNumberRow;
    private PropertyTableRow TransactionTypeRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheCAT = (CAT) Control;
        TheCAT.addDirectIOListener(this);
        TheCAT.addStatusUpdateListener(this);
        TheCAT.addOutputCompleteListener(this);
        TheCAT.addErrorListener(this);
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(AccountNumberRow = new PropertyTableRow("AccountNumber", ""));
        Properties.getItems().add(AdditionalSecurityInformationRow = new PropertyTableRow("AdditionalSecurityInformation", ""));
        Properties.getItems().add(ApprovalCodeRow = new PropertyTableRow("ApprovalCode", ""));
        Properties.getItems().add(BalanceRow = new PropertyTableRow("Balance", "0.00"));
        Properties.getItems().add(new PropertyTableRow("CapAdditionalSecurityInformation", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeCompletion", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizePreSales", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeRefund", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeVoid", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeVoidPreSales", ""));
        Properties.getItems().add(new PropertyTableRow("CapCashDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CapCenterResultCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapCheckCard", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapDailyLog", "", new CapDailyLogValues()));
        Properties.getItems().add(new PropertyTableRow("CapInstallments", ""));
        Properties.getItems().add(new PropertyTableRow("CapLockTerminal", ""));
        Properties.getItems().add(new PropertyTableRow("CapLogStatus", ""));
        Properties.getItems().add(new PropertyTableRow("CapPaymentDetail", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapTaxOthers", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransactionNumber", ""));
        Properties.getItems().add(new PropertyTableRow("CapTrainingMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapUnlockTerminal", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Properties.getItems().add(CardCompanyIDRow = new PropertyTableRow("CardCompanyID", ""));
        Properties.getItems().add(CenterResultCodeRow = new PropertyTableRow("CenterResultCode", ""));
        Properties.getItems().add(DailyLogRow = new PropertyTableRow("DailyLog", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(LogStatusRow = new PropertyTableRow("LogStatus", "", new LogStatusValues()));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(PaymentConditionRow = new PropertyTableRow("PaymentCondition", "", new PaymentConditionValues()));
        Properties.getItems().add(PaymentDetailRow = new PropertyTableRow("PaymentDetail", ""));
        Properties.getItems().add(PaymentMediaRow = new PropertyTableRow("PaymentMedia", "", new PaymentMediaValues()));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(SequenceNumberRow = new PropertyTableRow("SequenceNumber", ""));
        Properties.getItems().add(SettledAmountRow = new PropertyTableRow("SettledAmount", ""));
        Properties.getItems().add(SlipNumberRow = new PropertyTableRow("SlipNumber", ""));
        Properties.getItems().add(TrainingModeRow = new PropertyTableRow("TrainingMode", ""));
        Properties.getItems().add(TransactionNumberRow = new PropertyTableRow("TransactionNumber", ""));
        Properties.getItems().add(TransactionTypeRow = new PropertyTableRow("TransactionType", "", new TransactionTypeValues()));
        CurrencyDigits.getItems().add(0);
        CurrencyDigits.getItems().add(1);
        CurrencyDigits.getItems().add(2);
        CurrencyDigits.getItems().add(3);
        CurrencyDigits.getItems().add(4);
        CurrencyDigits.setValue(2);
        A_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        ADL_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        CD_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        CC_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        ADL_type.getItems().add(new ADL_TypeValues().getSymbol(CATConst.CAT_DL_REPORTING));
        ADL_type.getItems().add(new ADL_TypeValues().getSymbol(CATConst.CAT_DL_SETTLEMENT));
        formatDecimalOnFocusLost(A_amount);
        formatDecimalOnFocusLost(A_taxOthers);
        formatDecimalOnFocusLost(CD_amount);
        ErrorCodeExtendedValueConverter = new ErrorCodeExtendedValues();
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            string2Decimal(BalanceRow);
            string2Decimal(SettledAmountRow);
            AccountNumber.setText(AccountNumberRow.getValue());
            Balance.setText(BalanceRow.getValue());
            SettledAmount.setText(SettledAmountRow.getValue());
            ApprovalCode.setText(ApprovalCodeRow.getValue());
            CardCompanyID.setText(CardCompanyIDRow.getValue());
            CenterResultCode.setText(CenterResultCodeRow.getValue());
            LogStatus.setText(LogStatusRow.getValue());
            SequenceNumber.setText(SequenceNumberRow.getValue());
            SlipNumber.setText(SlipNumberRow.getValue());
            TransactionType.setText(TransactionTypeRow.getValue());
            TransactionNumber.setText(TransactionNumberRow.getValue());
            PaymentCondition.setText(PaymentConditionRow.getValue());
            if (PaymentMedia.getItems().size() == 0) {
                Object[] pvals = new PaymentMediaValues().ValueList;
                for (int i = 1; i < pvals.length; i += 2)
                    PaymentMedia.getItems().add(pvals[i].toString());
            }
            PaymentMedia.setValue(PaymentMediaRow.getValue());
            PaymentDetail.setText(PaymentDetailRow.getValue());
            AdditionalSecurityInformation.setText(AdditionalSecurityInformationRow.getValue());
            DailyLog.setText(DailyLogRow.getValue());
            TrainingMode.setSelected("true".equals(TrainingModeRow.getValue()));
            formatDecimalTextField(A_amount, CurrencyDigits.getValue());
            formatDecimalTextField(A_taxOthers, CurrencyDigits.getValue());
            formatDecimalTextField(CD_amount, CurrencyDigits.getValue());
            InUpdateGui = false;
        }
    }

    class AuthorizeCompletionHandler extends CashDepositHandler {
        final long TaxOthers;

        AuthorizeCompletionHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, tio);
            setName(LogicalDeviceName + " " + "AuthorizeCompletionHandler");
            TaxOthers = taxothers;
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizeCompletion(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeCompletion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeCompletionHandler(seq, amount, taxother, tio).start();
    }

    class AuthorizePreSalesHandler extends AuthorizeCompletionHandler {
        AuthorizePreSalesHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, taxothers, tio);
            setName(LogicalDeviceName + " " + "AuthorizePreSalesHandler");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizePreSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizePreSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizePreSalesHandler(seq, amount, taxother, tio).start();
    }

    class AuthorizeRefundHandler extends AuthorizeCompletionHandler {
        AuthorizeRefundHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, taxothers, tio);
            setName(LogicalDeviceName + " " + "AuthorizeRefundHandler");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizeRefund(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeRefund(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeRefundHandler(seq, amount, taxother, tio).start();
    }

    class AuthorizeSalesHandler extends AuthorizeCompletionHandler {
        AuthorizeSalesHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, taxothers, tio);
            setName(LogicalDeviceName + " " + "AuthorizeSalesHandler");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizeSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeSalesHandler(seq, amount, taxother, tio).start();
    }

    class AuthorizeVoidHandler extends AuthorizeCompletionHandler {
        AuthorizeVoidHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, taxothers, tio);
            setName(LogicalDeviceName + " " + "AuthorizeVoidHandler");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizeVoid(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeVoid(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeVoidHandler(seq, amount, taxother, tio).start();
    }

    class AuthorizeVoidPreSalesHandler extends AuthorizeCompletionHandler {
        AuthorizeVoidPreSalesHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, taxothers, tio);
            setName(LogicalDeviceName + " " + "AuthorizeVoidPreSalesHandler");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.authorizeVoidPreSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeVoidPreSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimal(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimal(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeVoidPreSalesHandler(seq, amount, taxother, tio).start();
    }

    public void setTrainingMode(ActionEvent actionEvent) {
        try {
            TheCAT.setTrainingMode(TrainingMode.isSelected());
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGui();
    }

    public void setPaymentMedia(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            try {
                TheCAT.setPaymentMedia(new PaymentMediaValues().getInteger(PaymentMedia.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    class AccessDailyLogHandler extends MethodProcessor {
        private final int SequenceNo;
        private final int Type;
        private final int Timeout;

        AccessDailyLogHandler(int seq, int type, int tio) {
            super("LockTerminal");
            SequenceNo = seq;
            Type = type;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheCAT.cashDeposit(SequenceNo, Type, Timeout);
        }
    }

    public void accessDailyLog(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(ADL_sequenceNumber.getText());
        Integer type = (Integer) new ADL_TypeValues().getValue(ADL_type.getValue());
        Integer tio = new TimeoutValues().getInteger(ADL_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(type, "type") && !invalid(tio, "timeout"))
            new AccessDailyLogHandler(seq, type, tio).start();
    }

    class CashDepositHandler extends MethodProcessor {
        final int SequenceNo;
        final long Amount;
        final int Timeout;

        CashDepositHandler(int seq, long amount, int tio) {
            super("LockTerminal");
            SequenceNo = seq;
            Amount = amount;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheCAT.cashDeposit(SequenceNo, Amount, Timeout);
        }
    }

    public void cashDeposit(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Long amount = getDecimal(new PropertyTableRow("", CD_amount.getText()));
        Integer seq = new IntValues().getInteger(CD_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(CD_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(tio, "timeout"))
            new CashDepositHandler(seq, amount, tio).start();
    }

    class CheckCardHandler extends MethodProcessor {
        private final int SequenceNo;
        private final int Timeout;

        CheckCardHandler(int seq, int tio) {
            super("LockTerminal");
            SequenceNo = seq;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheCAT.checkCard(SequenceNo, Timeout);
        }
    }

    public void checkCard(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(CC_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(CC_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new CheckCardHandler(seq, tio).start();
    }

    class LockTerminalHandler extends MethodProcessor {
        LockTerminalHandler() {
            super("LockTerminal");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.lockTerminal();
        }
    }

    public void lockTerminal(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new LockTerminalHandler().start();
    }

    class UnlockTerminalHandler extends MethodProcessor {
        UnlockTerminalHandler() {
            super("UnlockTerminal");
        }

        @Override
        void runIt() throws JposException {
            TheCAT.unlockTerminal();
        }
    }

    public void unlockTerminal(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new UnlockTerminalHandler().start();
    }

    public void clearDirectIOText(ActionEvent actionEvent) {
        A_DirectIOEvent.setText("");
        B_DirectIOEvent.setText("");
        C_DirectIOEvent.setText("");
    }

    String DirectIOText = null;
    int DirectIOTextPosition = 0;
    double DirectIOHScroll = 0.0;
    double DirectIOVScroll = 0.0;
    Cursor DirectIOCursor = null;

    public void authorizationSelection(Event event) {
        new DirectIOTextUpdater(event, A_DirectIOEvent);
    }

    public void administrationSelection(Event event) {
        new DirectIOTextUpdater(event, B_DirectIOEvent);
    }

    public void maintenanceSelection(Event event) {
        new DirectIOTextUpdater(event, C_DirectIOEvent);
    }

    class DirectIOTextUpdater extends Thread {
        TextArea Area;
        DirectIOTextUpdater(Event event, TextArea area) {
            if (((Tab)event.getSource()).isSelected()) {
                (Area = area).setText(DirectIOText);
                runit();
                start();
            }
            else {
                DirectIOText = area.getText();
                DirectIOTextPosition = area.getCaretPosition();
                DirectIOHScroll = area.getScrollLeft();
                DirectIOVScroll = area.getScrollTop();
                DirectIOCursor = area.getCursor();
            }
        }

        @Override
        public void run() {
            try {
                sleep(100);
            } catch (InterruptedException e) {}
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    runit();
                }
            });
        }

        private void runit() {
            Area.positionCaret(DirectIOTextPosition);
            Area.setScrollLeft(DirectIOHScroll);
            Area.setScrollTop(DirectIOVScroll);
            Area.setCursor(DirectIOCursor);
        }
    }

    private void updateDirectIOTextArea(Event event, TextArea area) {
        if (((Tab)event.getSource()).isSelected()) {
            area.setText(DirectIOText);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    area.positionCaret(DirectIOTextPosition);
                    area.setScrollLeft(DirectIOHScroll);
                    area.setScrollTop(DirectIOVScroll);
                }
            });
        }
        else {
            DirectIOText = area.getText();
            DirectIOTextPosition = area.getCaretPosition();
            DirectIOHScroll = area.getScrollLeft();
            DirectIOVScroll = area.getScrollTop();
        }
    }

    private class CapDailyLogValues extends Values {
        CapDailyLogValues() {
            ValueList = new Object[]{
                    CATConst.CAT_DL_NONE, "DL_NONE",
                    CATConst.CAT_DL_REPORTING, "DL_REPORTING",
                    CATConst.CAT_DL_SETTLEMENT, "DL_SETTLEMENT",
                    CATConst.CAT_DL_REPORTING_SETTLEMENT, "DL_REPORTING_SETTLEMENT"
            };
        }
    }

    private class LogStatusValues extends Values {
        LogStatusValues() {
            ValueList = new Object[]{
                    CATConst.CAT_LOGSTATUS_OK, "LOGSTATUS_OK",
                    CATConst.CAT_LOGSTATUS_NEARFULL, "LOGSTATUS_NEARFULL",
                    CATConst.CAT_LOGSTATUS_FULL, "LOGSTATUS_FULL"
            };
        }
    }

    private class PaymentConditionValues extends Values {
        PaymentConditionValues() {
            ValueList = new Object[]{
                    CATConst.CAT_PAYMENT_LUMP, "PAYMENT_LUMP",
                    CATConst.CAT_PAYMENT_BONUS_1, "PAYMENT_BONUS_1",
                    CATConst.CAT_PAYMENT_BONUS_2, "PAYMENT_BONUS_2",
                    CATConst.CAT_PAYMENT_BONUS_3, "PAYMENT_BONUS_3",
                    CATConst.CAT_PAYMENT_BONUS_4, "PAYMENT_BONUS_4",
                    CATConst.CAT_PAYMENT_BONUS_5, "PAYMENT_BONUS_5",
                    CATConst.CAT_PAYMENT_INSTALLMENT_1, "PAYMENT_INSTALLMENT_1",
                    CATConst.CAT_PAYMENT_INSTALLMENT_2, "PAYMENT_INSTALLMENT_2",
                    CATConst.CAT_PAYMENT_INSTALLMENT_3, "PAYMENT_INSTALLMENT_3",
                    CATConst.CAT_PAYMENT_BONUS_COMBINATION_1, "PAYMENT_BONUS_COMBINATION_",
                    CATConst.CAT_PAYMENT_BONUS_COMBINATION_2, "PAYMENT_BONUS_COMBINATION_2",
                    CATConst.CAT_PAYMENT_BONUS_COMBINATION_3, "PAYMENT_BONUS_COMBINATION_3",
                    CATConst.CAT_PAYMENT_BONUS_COMBINATION_4, "PAYMENT_BONUS_COMBINATION_4",
                    CATConst.CAT_PAYMENT_REVOLVING, "PAYMENT_REVOLVING",
                    CATConst.CAT_PAYMENT_DEBIT, "PAYMENT_DEBIT",
                    CATConst.CAT_PAYMENT_ELECTRONIC_MONEY, "PAYMENT_ELECTRONIC_MONEY"
            };
        }
    }

    private class PaymentMediaValues extends Values {
        PaymentMediaValues() {
            ValueList = new Object[]{
                    CATConst.CAT_MEDIA_UNSPECIFIED, "MEDIA_UNSPECIFIED",
                    CATConst.CAT_MEDIA_CREDIT, "MEDIA_CREDIT",
                    CATConst.CAT_MEDIA_DEBIT, "MEDIA_DEBIT",
                    CATConst.CAT_MEDIA_ELECTRONIC_MONEY, "MEDIA_ELECTRONIC_MONEY"
            };
        }
    }

    private class TransactionTypeValues extends Values {
        TransactionTypeValues() {
            ValueList = new Object[]{
                    CATConst.CAT_TRANSACTION_SALES, "TRANSACTION_SALES",
                    CATConst.CAT_TRANSACTION_VOID, "TRANSACTION_VOID",
                    CATConst.CAT_TRANSACTION_REFUND, "TRANSACTION_REFUND",
                    CATConst.CAT_TRANSACTION_COMPLETION, "TRANSACTION_COMPLETION",
                    CATConst.CAT_TRANSACTION_PRESALES, "TRANSACTION_PRESALES",
                    CATConst.CAT_TRANSACTION_CHECKCARD, "TRANSACTION_CHECKCARD",
                    CATConst.CAT_TRANSACTION_VOIDPRESALES, "TRANSACTION_VOIDPRESALES",
                    CATConst.CAT_TRANSACTION_CASHDEPOSIT, "TRANSACTION_CASHDEPOSIT"
            };
        }
    }

    private class ADL_TypeValues extends Values {
        ADL_TypeValues() {
            ValueList = new Object[]{
                    CATConst.CAT_DL_REPORTING, "DL_REPORTING",
                    CATConst.CAT_DL_SETTLEMENT, "DL_SETTLEMENT"
            };
        }
    }

    @Override
    public void gotDirectIO(DirectIOEvent ev) {
        super.gotDirectIO(ev);
        String add = "\n" + ev.getEventNumber() + " - " + ev.getData();
        String[] object = ev.getObject().toString().split("\n");
        for (String line : object) {
            add += "\n  " + line;
        }
        String h = A_DirectIOEvent.getText() + add;
        A_DirectIOEvent.setText(h);
        B_DirectIOEvent.setText(h);
        C_DirectIOEvent.setText(h);
    }

    private class ErrorCodeExtendedValues extends Values {
        ErrorCodeExtendedValues() {
            ValueList = new Object[]{
                    CATConst.JPOS_ECAT_CENTERERROR, "ECAT_CENTERERROR",
                    CATConst.JPOS_ECAT_COMMANDERROR, "ECAT_COMMANDERROR",
                    CATConst.JPOS_ECAT_RESET, "ECAT_RESET",
                    CATConst.JPOS_ECAT_COMMUNICATIONERROR, "ECAT_COMMUNICATIONERROR",
                    CATConst.JPOS_ECAT_DAILYLOGOVERFLOW, "ECAT_DAILYLOGOVERFLOW",
                    CATConst.JPOS_ECAT_DEFICIENT, "ECAT_DEFICIENT",
                    CATConst.JPOS_ECAT_OVERDEPOSIT, "ECAT_OVERDEPOSIT"
            };
        }
    }
}
