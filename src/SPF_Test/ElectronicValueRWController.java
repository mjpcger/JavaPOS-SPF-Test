/*
 * Copyright 2022 Martin Conrad
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
 *
 */

package SPF_Test;

import de.gmxhome.conrad.jpos.jpos_base.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import jpos.*;
import jpos.events.TransitionEvent;
import jpos.events.TransitionListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * GUI control for ElectronicValueRW properties, methods and events.
 */
public class ElectronicValueRWController extends CommonController implements TransitionListener {
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
    public ComboBox<String> TrainingModeState;
    public TextField ADL_sequenceNumber;
    public ComboBox<String> ADL_type;
    public ComboBox<String> ADL_timeout;
    public TextField CD_sequenceNumber;
    public TextField CD_amount;
    public ComboBox<String> CD_timeout;
    public TextField CC_sequenceNumber;
    public ComboBox<String> CC_timeout;
    public TextArea TransitionEvent;
    public AnchorPane CardDetection;
    public AnchorPane EVRWAuthorizeAnchor;
    public AnchorPane AuthorizeAnchor;
    public AnchorPane AdministrativeAnchor;
    public AnchorPane MaintenanceAnchor;
    public TextArea DailyLog;
    public ComboBox<String> CurrentService;
    public Label ServiceType;
    public ComboBox<String> PINEntry;
    public TextField MediumID;
    public ComboBox<String> ParameterControl;
    public ComboBox<String> PC_name;
    public ComboBox<String> PC_value;
    public TextField TransitionEventNumber;
    public TextField TransitionData;
    public TextArea TransitionString;
    public Button TransitionContinue;
    public ComboBox<String> BD_type;
    public ComboBox<String> BD_timeout;
    public ComboBox<String> BR_timeout;
    public CheckBox DetectionControl;
    public ComboBox<String> CardServiceList;
    public ComboBox<String> CO_help;
    public TextField CO_method;
    public TextField CO_classname;
    public TextField CO_parameter;
    public ComboBox<String> TypeDataObjectMethods;
    public ComboBox<String> TDOM_type;
    public TextField TDOM_data;
    public ComboBox<String> SeqTioMethods;
    public TextField ST_sequenceNumber;
    public ComboBox<String> ST_timeout;
    public ComboBox<String> SeqTioValMethods;
    public TextField STV_sequenceNumber;
    public ComboBox<String> STV_timeout;
    public ComboBox<String> TA_control;
    public TextField AL_sequenceNumber;
    public ComboBox<String> AL_type;
    public ComboBox<String> AL_timeout;
    public TextArea OAdditionalSecurityInformation;
    public TextField OApprovalCode;
    public TextField Amount;
    public TextField Point;
    public ComboBox<String> VoucherID;
    public TextField VoucherIDList;
    public Button PC_checkButton;
    private ElectronicValueRW TheElectronicValueRW;
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
    private PropertyTableRow TrainingModeStateRow;
    private PropertyTableRow TransactionNumberRow;
    private PropertyTableRow TransactionTypeRow;
    private PropertyTableRow AmountRow;
    private PropertyTableRow BalanceOfPointRow; //
    private PropertyTableRow CardServiceListRow;
    private PropertyTableRow CurrentServiceRow;
    private PropertyTableRow DetectionControlRow;
    private PropertyTableRow DetectionStatusRow;
    private PropertyTableRow ExpirationDateRow; //
    private PropertyTableRow LastUsedDateRow;   //
    private PropertyTableRow MediumIDRow;
    private PropertyTableRow PINEntryRow;
    private PropertyTableRow PointRow;
    private PropertyTableRow ReaderWriterServiceListRow;
    private PropertyTableRow ServiceTypeRow;
    private PropertyTableRow SettledPointRow;   //
    private PropertyTableRow TransactionLogRow;
    private PropertyTableRow VoucherIDRow;
    private PropertyTableRow VoucherIDListRow;
    private PropertyTableRow CapPINDeviceRow;

    private Object[] TagValues = {
            "AccessLogLastDateTime", LocalDateTime.class,
            "AccountNumber", null,
            "Amount", Long.class,
            "AmountForPoint", Long.class,
            "AuthenticationStatus", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_AS_AUTHENTICATED, "AS_AUTHENTICATED",
                    ElectronicValueRWConst.EVRW_TAG_AS_UNAUTHENTICATED, "AS_UNAUTHENTICATED"
            },
            "AutoCharge", new String[]{"True", "False"},
            "Balance", Long.class,
            "BalanceOfPoint", Long.class,
            "BusinessUnitID", null,
            "CancelTransactionType",  new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_CTT_CANCEL, "CTT_CANCEL",
                    ElectronicValueRWConst.EVRW_TAG_CTT_CHARGE, "CTT_CHARGE",
                    ElectronicValueRWConst.EVRW_TAG_CTT_RETURN, "CTT_RETURN",
                    ElectronicValueRWConst.EVRW_TAG_CTT_SALES, "CTT_SALES"
            },
            "CardCompanyName", null,
            "CardTransactionLogID", null,
            "CardTransactionNumber", Integer.class,
            "ChargeableAmount", Long.class,
            "ChargeableCount", Integer.class,
            "ChargeMethod", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_CM_CASH, "CM_CASH",
                    ElectronicValueRWConst.EVRW_TAG_CM_CREDIT, "CM_CREDIT",
                    ElectronicValueRWConst.EVRW_TAG_CM_POINT, "CM_POINT"
            },
            "DateTime", LocalDateTime.class,
            "EffectiveDaysOfKey", Integer.class,
            "EndAccountID", null,
            "EndDateTime", LocalDateTime.class,
            "EndEVRWTransactionNumber", Integer.class,
            "EndPOSTransactionNumber", Integer.class,
            "EVRWApprovalCode", null,
            "EVRWDataUpdateDateTime", LocalDateTime.class,
            "EVRWDateTime", LocalDateTime.class,
            "EVRWID", Integer.class,
            "EVRWTransactionLogID", null,
            "EVRWTransactionNumber", Integer.class,
            "ExpirationDate", LocalDateTime.class,
            "ExpiredAccountID", null,
            "ForceOnlineCheck", new String[]{"True", "False"},
            "InsufficientAmount", Long.class,
            "ItemCode", null,
            "KeyExpirationDateTime", LocalDateTime.class,
            "KeyUpdateDateTime", LocalDateTime.class,
            "LastTimeBalance", LocalDateTime.class,
            "LastTimeCardTransactionLogID", null,
            "LastTimeEVRWTransactionLogID", null,
            "LastUsedDateTime", LocalDateTime.class,
            "LogCheck", new String[]{"True", "False"},
            "MediaData", null,
            "MediumID", Integer.class,
            "MediumIssuerInformation", null,
            "MemberInformation", null,
            "MerchantID", null,
            "ModuleID", Integer.class,
            "NegativeInformationType",new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_NIT_ALL, "NIT_ALL",
                    ElectronicValueRWConst.EVRW_TAG_NIT_UPDATED, "NIT_UPDATED"
            },
            "NegativeInformationUpdateDateTime", LocalDateTime.class,
            "NumberOfAddition", Integer.class,
            "NumberOfEVRWTransactionLog", Integer.class,
            "NumberOfFreeEVRWTransactionLog", Integer.class,
            "NumberOfRecord", Integer.class,
            "NumberOfSentEVRWTransactionLog", Integer.class,
            "NumberOfSubtraction", Integer.class,
            "NumberOfTransaction", Integer.class,
            "NumberOfUncompletedAddition", Integer.class,
            "NumberOfUncompletedSubtraction", Integer.class,
            "NumberOfUncompletedVoid", Integer.class,
            "NumberOfVoid", Integer.class,
            "OtherAmount", LocalDateTime.class,
            "PaymentCondition", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_PC_INSTALLMENT_1, "PC_INSTALLMENT_1",
                    ElectronicValueRWConst.EVRW_TAG_PC_INSTALLMENT_2, "PC_INSTALLMENT_2",
                    ElectronicValueRWConst.EVRW_TAG_PC_INSTALLMENT_3, "PC_INSTALLMENT_3",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_1, "PC_BONUS_1",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_2, "PC_BONUS_2",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_3, "PC_BONUS_3",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_4, "PC_BONUS_4",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_5, "PC_BONUS_5",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_COMBINATION_1, "PC_BONUS_COMBINATION_1",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_COMBINATION_2, "PC_BONUS_COMBINATION_2",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_COMBINATION_3, "PC_BONUS_COMBINATION_3",
                    ElectronicValueRWConst.EVRW_TAG_PC_BONUS_COMBINATION_4, "PC_BONUS_COMBINATION_4",
                    ElectronicValueRWConst.EVRW_TAG_PC_LUMP, "PC_LUMP",
                    ElectronicValueRWConst.EVRW_TAG_PC_REVOLVING, "PC_REVOLVING"
            },
            "PaymentDetail", null,
            "PaymentMethod", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_PM_COMBINED, "PM_COMBINED",
                    ElectronicValueRWConst.EVRW_TAG_PM_FULL_SETTLEMENT, "PM_FULL_SETTLEMENT"
            },
            "PaymentMethodForPoint", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_PMFP_CASH, "PMFP_CASH",
                    ElectronicValueRWConst.EVRW_TAG_PMFP_CREDIT, "PMFP_CREDIT",
                    ElectronicValueRWConst.EVRW_TAG_PMFP_EM, "PMFP_EM",
                    ElectronicValueRWConst.EVRW_TAG_PMFP_OTHER, "PMFP_OTHER"
            },
            "Point", Integer.class,
            "POSDateTime", LocalDateTime.class,
            "POSTransactionNumber", Integer.class,
            "RegistrableServiceCapacity", Integer.class,
            "RequestedAutoChargeAmount", Long.class,
            "ResponseCode1", Integer.class,
            "ResponseCode2", Integer.class,
            "ResultOnSettlement", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_ROS_NG, "ROS_NG",
                    ElectronicValueRWConst.EVRW_TAG_ROS_OK, "ROS_OK",
                    ElectronicValueRWConst.EVRW_TAG_ROS_UNKNOWN, "ROS_UNKNOWN"
            },
            "RetryTimeout", Integer.class,
            "SettledAmount", Long.class,
            "SettledAutoChargeAmount", Long.class,
            "SettledMemberInformation", null,
            "SettledOther-Amount", Long.class,
            "SettledPoint", Integer.class,
            "SetttledVoucherID", null,
            "SettlementNumber", Integer.class,
            "SignatureFlag", new String[]{"True", "False"},
            "SoundAssistFlag", new String[]{"True", "False"},
            "StartAccountID", null,
            "StartDateTime", LocalDateTime.class,
            "StartEVRWTransactionNumber", Integer.class,
            "StartPOSTransactionNumber", Integer.class,
            "SummaryTermType", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_STT_1, "STT_1",
                    ElectronicValueRWConst.EVRW_TAG_STT_2, "STT_2",
                    ElectronicValueRWConst.EVRW_TAG_STT_3, "STT_3"
            },
            "TargetService", null,
            "TaxOthers", Long.class,
            "TotalAmountOfAddition", Long.class,
            "TotalAmountOfSubtraction", Long.class,
            "TotalAmountOfTransaction", Long.class,
            "TotalAmountOfUncompletedAddition", Long.class,
            "TotalAmountOfUncompletedSubtraction", Long.class,
            "TotalAmountOfUncompletedVoid", Long.class,
            "TotalAmountOfVoid", Long.class,
            "TouchTimeout", Integer.class,
            "TransactionType", new Object[]{
                    ElectronicValueRWConst.EVRW_TAG_TT_ADD, "TT_ADD",
                    ElectronicValueRWConst.EVRW_TAG_TT_CANCEL_CHARGE, "TT_CANCEL_CHARGE",
                    ElectronicValueRWConst.EVRW_TAG_TT_CANCEL_RETURN, "TT_CANCEL_RETURN",
                    ElectronicValueRWConst.EVRW_TAG_TT_CANCEL_SALES, "TT_CANCEL_SALES",
                    ElectronicValueRWConst.EVRW_TAG_TT_GET_LOG, "TT_GET_LOG",
                    ElectronicValueRWConst.EVRW_TAG_TT_READ, "TT_READ",
                    ElectronicValueRWConst.EVRW_TAG_TT_RETURN, "TT_RETURN",
                    ElectronicValueRWConst.EVRW_TAG_TT_SUBTRACT, "TT_SUBTRACT",
                    ElectronicValueRWConst.EVRW_TAG_TT_WRITE, "TT_WRITE",
                    ElectronicValueRWConst.EVRW_TAG_TT_COMPLETION, "TT_COMPLETION",
                    ElectronicValueRWConst.EVRW_TAG_TT_PRE_SALES, "TT_PRE_SALES"
            },
            "UILCDControl", new String[]{"True", "False"},
            "UILEDControl", new String[]{"True", "False"},
            "UISOUNDControl", new String[]{"True", "False"},
            "VOIDorRETURN", new Object[]{1, "Void", 2, "Return"},
            "VoidTransactionType", new Object[]{1, "Cash", 2, "Exchanging points"},
            "VoucherID", null,
            "VoucherIDList", null,
            "WorkstationID", null,
            "WorkstationMaker", null,
            "WorkstationSerialNumber", null
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        Conversion = ByteConversion.Ascii;
        super.initialize(url, resourceBundle);
        TheElectronicValueRW = (ElectronicValueRW) Control;
        TheElectronicValueRW.addDirectIOListener(this);
        TheElectronicValueRW.addTransitionListener(this);
        TheElectronicValueRW.addStatusUpdateListener(this);
        StatusUpdateEventStatusValueConverter = new ElectronicValueRWStatusUpdateValues();
        TheElectronicValueRW.addOutputCompleteListener(this);
        TheElectronicValueRW.addErrorListener(this);
        ErrorCodeExtendedValueConverter = new ErrorCodeExtendedValues();
        Properties.getItems().add(new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(AccountNumberRow = new PropertyTableRow("AccountNumber", ""));
        Properties.getItems().add(AdditionalSecurityInformationRow = new PropertyTableRow("AdditionalSecurityInformation", ""));
        Properties.getItems().add(AmountRow = new PropertyTableRow("Amount", "0.00"));
        Properties.getItems().add(ApprovalCodeRow = new PropertyTableRow("ApprovalCode", ""));
        Properties.getItems().add(BalanceRow = new PropertyTableRow("Balance", "0.00"));
        Properties.getItems().add(BalanceOfPointRow = new PropertyTableRow("BalanceOfPoint", "0.00"));
        Properties.getItems().add(new PropertyTableRow("CapActivateService", ""));
        Properties.getItems().add(new PropertyTableRow("CapAdditionalSecurityInformation", ""));
        Properties.getItems().add(new PropertyTableRow("CapAddValue", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeCompletion", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizePreSales", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeRefund", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeVoid", ""));
        Properties.getItems().add(new PropertyTableRow("CapAuthorizeVoidPreSales", ""));
        Properties.getItems().add(new PropertyTableRow("CapCancelValue", ""));
        Properties.getItems().add(new PropertyTableRow("CapCardSensor", "", new CapCardSensorValues()));
        Properties.getItems().add(new PropertyTableRow("CapCashDeposit", ""));
        Properties.getItems().add(new PropertyTableRow("CapCenterResultCode", ""));
        Properties.getItems().add(new PropertyTableRow("CapCheckCard", ""));
        Properties.getItems().add(new PropertyTableRow("CapCompareFirmwareVersion", ""));
        Properties.getItems().add(new PropertyTableRow("CapDailyLog", "", new CapDailyLogValues()));
        Properties.getItems().add(new PropertyTableRow("CapDetectionControl", "", new CapDetectionControlValues()));
        Properties.getItems().add(new PropertyTableRow("CapElectronicMoney", ""));
        Properties.getItems().add(new PropertyTableRow("CapEnumerateCardServices", ""));
        Properties.getItems().add(new PropertyTableRow("CapIndirectTransactionLog", ""));
        Properties.getItems().add(new PropertyTableRow("CapInstallments", ""));
        Properties.getItems().add(new PropertyTableRow("CapLockTerminal", ""));
        Properties.getItems().add(new PropertyTableRow("CapLogStatus", ""));
        Properties.getItems().add(new PropertyTableRow("CapMediumID", ""));
        Properties.getItems().add(new PropertyTableRow("CapMembershipCertificate", ""));
        Properties.getItems().add(new PropertyTableRow("CapPaymentDetail", ""));
        Properties.getItems().add(CapPINDeviceRow = new PropertyTableRow("CapPINDevice", ""));
        Properties.getItems().add(new PropertyTableRow("CapPoint", ""));
        Properties.getItems().add(new PropertyTableRow("CapPowerReporting", "", new CapPowerReportingValues()));
        Properties.getItems().add(new PropertyTableRow("CapStatisticsReporting", ""));
        Properties.getItems().add(new PropertyTableRow("CapSubtractValue", ""));
        Properties.getItems().add(new PropertyTableRow("CapTaxOthers", ""));
        Properties.getItems().add(new PropertyTableRow("CapTrainingMode", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransaction", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransactionLog", ""));
        Properties.getItems().add(new PropertyTableRow("CapTransactionNumber", ""));
        Properties.getItems().add(new PropertyTableRow("CapUnlockTerminal", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateFirmware", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateKey", ""));
        Properties.getItems().add(new PropertyTableRow("CapUpdateStatistics", ""));
        Properties.getItems().add(new PropertyTableRow("CapVoucher", ""));
        Properties.getItems().add(new PropertyTableRow("CapWriteValue", ""));
        Properties.getItems().add(CardCompanyIDRow = new PropertyTableRow("CardCompanyID", ""));
        Properties.getItems().add(CardServiceListRow = new PropertyTableRow("CardServiceList", ""));
        Properties.getItems().add(CenterResultCodeRow = new PropertyTableRow("CenterResultCode", ""));
        Properties.getItems().add(CurrentServiceRow = new PropertyTableRow("CurrentService", ""));
        Properties.getItems().add(DailyLogRow = new PropertyTableRow("DailyLog", ""));
        Properties.getItems().add(DetectionControlRow = new PropertyTableRow("DetectionControl", ""));
        Properties.getItems().add(DetectionStatusRow = new PropertyTableRow("DetectionStatus", "", new DetectionStatusValues()));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("DeviceServiceVersion", ""));
        Properties.getItems().add(ExpirationDateRow = new PropertyTableRow("ExpirationDate", ""));
        Properties.getItems().add(LastUsedDateRow = new PropertyTableRow("LastUsedDate", ""));
        Properties.getItems().add(LogStatusRow = new PropertyTableRow("LogStatus", "", new LogStatusValues()));
        Properties.getItems().add(MediumIDRow = new PropertyTableRow("MediumID", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(PaymentConditionRow = new PropertyTableRow("PaymentCondition", "", new PaymentConditionValues()));
        Properties.getItems().add(PaymentDetailRow = new PropertyTableRow("PaymentDetail", ""));
        Properties.getItems().add(PaymentMediaRow = new PropertyTableRow("PaymentMedia", "", new PaymentMediaValues()));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceDescription", ""));
        Properties.getItems().add(new PropertyTableRow("PhysicalDeviceName", ""));
        Properties.getItems().add(PINEntryRow = new PropertyTableRow("PINEntry", "", new PINEntryValues()));
        Properties.getItems().add(PointRow = new PropertyTableRow("Point", ""));
        Properties.getItems().add(ReaderWriterServiceListRow = new PropertyTableRow("ReaderWriterServiceList", ""));
        Properties.getItems().add(SequenceNumberRow = new PropertyTableRow("SequenceNumber", ""));
        Properties.getItems().add(ServiceTypeRow = new PropertyTableRow("ServiceType", "", new ServiceTypeValues()));
        Properties.getItems().add(SettledAmountRow = new PropertyTableRow("SettledAmount", ""));
        Properties.getItems().add(SettledPointRow = new PropertyTableRow("SettledPoint", ""));
        Properties.getItems().add(SlipNumberRow = new PropertyTableRow("SlipNumber", ""));
        Properties.getItems().add(TrainingModeStateRow = new PropertyTableRow("TrainingModeState", "", new TrainingModeStateValues()));
        Properties.getItems().add(TransactionLogRow = new PropertyTableRow("TransactionLog", ""));
        Properties.getItems().add(TransactionNumberRow = new PropertyTableRow("TransactionNumber", ""));
        Properties.getItems().add(TransactionTypeRow = new PropertyTableRow("TransactionType", "", new TransactionTypeValues()));
        Properties.getItems().add(VoucherIDRow = new PropertyTableRow("VoucherID", ""));
        Properties.getItems().add(VoucherIDListRow = new PropertyTableRow("VoucherIDList", ""));
        CurrencyDigits.getItems().add(0);
        CurrencyDigits.getItems().add(1);
        CurrencyDigits.getItems().add(2);
        CurrencyDigits.getItems().add(3);
        CurrencyDigits.getItems().add(4);
        CurrencyDigits.setValue(2);
        A_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        ADL_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        AL_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        BD_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        BR_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        CD_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        CC_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        ST_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        STV_timeout.getItems().add(new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER));
        ADL_type.getItems().add(new ADL_TypeValues().getSymbol(ElectronicValueRWConst.EVRW_DL_REPORTING));
        ADL_type.getItems().add(new ADL_TypeValues().getSymbol(ElectronicValueRWConst.EVRW_DL_SETTLEMENT));
        BD_type.getItems().add(new BD_typeValues().getSymbol(ElectronicValueRWConst.EVRW_BD_ANY));
        BD_type.getItems().add(new BD_typeValues().getSymbol(ElectronicValueRWConst.EVRW_BD_SPECIFIC));
        CO_help.getItems().add("Help");
        CO_help.getItems().add("This method can be used to create the Object parameter of EVRW service methods.");
        CO_help.getItems().add("");
        CO_help.getItems().add("To create a String object, simply set parameter to the necessary string and let classname and method empty.");
        CO_help.getItems().add("");
        CO_help.getItems().add("To create an object using a constructor with one String parameter, simply set parameter to the necessary");
        CO_help.getItems().add("string, classname to the name of the class of the object to be generated and let method empty.");
        CO_help.getItems().add("");
        CO_help.getItems().add("If you use a factory class with a static factory method consuming one String parameter, set parameter to the");
        CO_help.getItems().add("necessary string, classname to the name of the factory class and method to the factory method name.");
        CO_help.getItems().add("After pressing the CreateObject button, JavaPOS-SPF-Test uses classname, method and parameter to create");
        CO_help.getItems().add("the object for later use in any of the EVRW methods that need an Object parameter.");
        TypeDataObjectMethods.getItems().add("ActivateService");
        TypeDataObjectMethods.getItems().add("ActivateEVService");
        TypeDataObjectMethods.getItems().add("OpenDailyEVService");
        TypeDataObjectMethods.getItems().add("CloseDailyEVService");
        TypeDataObjectMethods.getItems().add("DeactivateEVService");
        TypeDataObjectMethods.getItems().add("AccessData");
        TypeDataObjectMethods.getItems().add("UpdateData");
        TypeDataObjectMethods.getItems().add("UpdateKey");
        SeqTioMethods.getItems().add("CheckServiceRegistrationToMedium");
        SeqTioMethods.getItems().add("RegisterServiceToMedium");
        SeqTioMethods.getItems().add("UnregisterServiceToMedium");
        SeqTioValMethods.getItems().add("ReadValue");
        SeqTioValMethods.getItems().add("AddValue");
        SeqTioValMethods.getItems().add("SubtractValue");
        SeqTioValMethods.getItems().add("WriteValue");
        SeqTioValMethods.getItems().add("CancelValue");
        TA_control.getItems().add(new TA_controlValues().getSymbol(ElectronicValueRWConst.EVRW_TA_NORMAL));
        TA_control.getItems().add(new TA_controlValues().getSymbol(ElectronicValueRWConst.EVRW_TA_TRANSACTION));
        AL_type.getItems().add(new AL_typeValues().getSymbol(ElectronicValueRWConst.EVRW_AL_REPORTING));
        AL_type.getItems().add(new AL_typeValues().getSymbol(ElectronicValueRWConst.EVRW_AL_SETTLEMENT));
        TDOM_type.getItems().add(new AD_typeValues().getSymbol(ElectronicValueRWConst.EVRW_AD_KEY));
        TDOM_type.getItems().add(new AD_typeValues().getSymbol(ElectronicValueRWConst.EVRW_AD_NEGATIVE_LIST));
        TDOM_type.getItems().add(new AD_typeValues().getSymbol(ElectronicValueRWConst.EVRW_AD_OTHERS));
        formatDecimalOnFocusLost(A_amount);
        formatDecimalOnFocusLost(A_taxOthers);
        formatDecimalOnFocusLost(CD_amount);
        formatDecimalOnFocusLost(Amount);
        ErrorCodeExtendedValueConverter = new ErrorCodeExtendedValues();
        CurrentService.getItems().add("");
        setPropertyOnFocusLost(MediumID, "MediumID");
        setPropertyOnFocusLost(ApprovalCode, "ApprovalCode");
        setPropertyOnFocusLost(OApprovalCode, "ApprovalCode");
        ParameterControl.getItems().addAll("SetParameterInformation","RetrieveResultInformation","ClearParameterInformation");
        ParameterControl.setValue("SetParameterInformation");
        setPropertyOnFocusLost(AdditionalSecurityInformation, "AdditionalSecurityInformation");
        setPropertyOnFocusLost(OAdditionalSecurityInformation, "AdditionalSecurityInformation");
        setPropertyOnFocusLost(Amount, "Amount");
        setPropertyOnFocusLost(Point, "Point");
        setPropertyOnFocusLost(VoucherIDList, "VoucherIDList");
        for (int i = 0; i < TagValues.length; i += 2) {
            PC_name.getItems().add(TagValues[i].toString());
        }
        updateGui();
    }

    @Override
    void updateGui() {
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            rowValue2Decimal(BalanceRow);
            rowValue2Decimal(SettledAmountRow);
            AccountNumber.setText(AccountNumberRow.getValue());
            Balance.setText(BalanceRow.getValue());
            SettledAmount.setText(SettledAmountRow.getValue());
            PropertyTableRow dummy = new PropertyTableRow("", AmountRow.getValue());
            rowValue2Decimal(dummy);
            Amount.setText(dummy.getValue());
            dummy.setValue(PointRow.getValue());
            rowValue2Decimal(dummy);
            Point.setText(dummy.getValue());
            VoucherIDList.setText(VoucherIDListRow.getValue());
            ApprovalCode.setText(ApprovalCodeRow.getValue());
            OApprovalCode.setText(ApprovalCodeRow.getValue());
            CardCompanyID.setText(CardCompanyIDRow.getValue());
            CenterResultCode.setText(CenterResultCodeRow.getValue());
            LogStatus.setText(LogStatusRow.getValue());
            MediumID.setText(MediumIDRow.getValue());
            SequenceNumber.setText(SequenceNumberRow.getValue());
            ServiceType.setText(ServiceTypeRow.getValue());
            SlipNumber.setText(SlipNumberRow.getValue());
            TransactionType.setText(TransactionTypeRow.getValue());
            TransactionNumber.setText(TransactionNumberRow.getValue());
            PaymentCondition.setText(PaymentConditionRow.getValue());
            if ((PINEntry.getItems().size() == 0) ^ (PINEntryRow.getValue().length() == 0)) {
                if (PINEntry.getItems().size() == 0) {
                    Object[] values = new PINEntryValues().ValueList;
                    for (int i = 0; i < values.length; i += 2) {
                        if ((Integer)values[i] == ElectronicValueRWConst.EVRW_PIN_ENTRY_INTERNAL) {
                            if (CapPINDeviceRow.getValue().toLowerCase().equals("true"))
                                PINEntry.getItems().add(values[i + 1].toString());

                        } else
                            PINEntry.getItems().add(values[i + 1].toString());
                    }
                } else {
                    PINEntry.getItems().clear();
                }
            }
            PINEntry.setValue(PINEntryRow.getValue());
            if (PaymentMedia.getItems().size() == 0) {
                Object[] pvals = new PaymentMediaValues().ValueList;
                for (int i = 1; i < pvals.length; i += 2)
                    PaymentMedia.getItems().add(pvals[i].toString());
            }
            PaymentMedia.setValue(PaymentMediaRow.getValue());
            PaymentDetail.setText(PaymentDetailRow.getValue());
            AdditionalSecurityInformation.setText(AdditionalSecurityInformationRow.getValue());
            OAdditionalSecurityInformation.setText(AdditionalSecurityInformationRow.getValue());
            DailyLog.setText(DailyLogRow.getValue());
            if (TrainingModeState.getItems().size() == 0) {
                Object[] pvals = new TrainingModeStateValues().ValueList;
                for (int i = 1; i < pvals.length; i += 2)
                    TrainingModeState.getItems().add(pvals[i].toString());
            }
            String[] vids = VoucherIDListRow.getValue().split(",");
            VoucherID.getItems().clear();
            if (vids.length > 1 || (vids.length == 1 && vids[0].length() > 0)) {
                for (String vid : vids)
                    VoucherID.getItems().add(vid);
            }
            VoucherID.setValue(VoucherIDRow.getValue());
            DetectionControl.setSelected("true".equals(DetectionControlRow.getValue().toLowerCase()));
            TrainingModeState.setValue(TrainingModeStateRow.getValue());
            formatDecimalTextField(A_amount, CurrencyDigits.getValue());
            formatDecimalTextField(A_taxOthers, CurrencyDigits.getValue());
            formatDecimalTextField(CD_amount, CurrencyDigits.getValue());
            InUpdateGui = false;
        }
    }

    @Override
    public void open(ActionEvent ev) {
        super.open(ev);
        if (TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            String[] services = ReaderWriterServiceListRow.getValue().split(",");
            CurrentService.getItems().clear();
            for (String service : services) {
                CurrentService.getItems().add(service);
            }
            CurrentService.getItems().add("");
            CurrentService.setValue("");
        }
    }

    @Override
    public void close(ActionEvent ev) {
        super.close(ev);
        if (TheElectronicValueRW.getState() == JposConst.JPOS_S_CLOSED) {
            CurrentService.getItems().clear();
            CurrentService.getItems().add("");
            CurrentService.setValue("");
        }
    }

    public void parameterControl(ActionEvent actionEvent) {
        try {
            if ("RetrieveResultInformation".equals(ParameterControl.getValue())) {
                String[] value = {""};
                TheElectronicValueRW.retrieveResultInformation(PC_name.getValue(), value);
                if (PC_valueConverter != null) {
                    PC_value.setValue(PC_valueConverter.getSymbol(value[0]));
                } else
                    PC_value.setValue(value[0]);
            } else if ("SetParameterInformation".equals(ParameterControl.getValue())) {
                String val = PC_value.getValue();
                if (PC_valueConverter != null) {
                    Integer ival = PC_valueConverter.getInteger(val);
                    if (ival != null)
                        val = ival.toString();
                }
                TheElectronicValueRW.setParameterInformation(PC_name.getValue(), val);
            } else {
                TheElectronicValueRW.clearParameterInformation();
                PC_value.setValue("");
                PC_name.setValue("");
            }
        } catch (JposException e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGuiLater();
    }

    public void setParameterControl(ActionEvent actionEvent) {
        PC_value.setDisable(!"SetParameterInformation".equals(ParameterControl.getValue()));
        PC_checkButton.setDefaultButton(!"SetParameterInformation".equals(ParameterControl.getValue()));
        PC_name.setDisable("ClearParameterInformation".equals(ParameterControl.getValue()));
        updateGuiLater();
    }

    private Object PC_condition = null;
    private Values PC_valueConverter = null;

    public void setPC_name(ActionEvent actionEvent) {
        String name = PC_name.getValue();
        String[] value = {PC_value.getValue(), null};
        Integer intvalue = initializeValueObjects(value);
        PC_value.getItems().clear();
        PC_valueConverter = null;
        for (int i = 0; i < TagValues.length; i += 2) {
            if (TagValues[i].toString().equals(name)) {
                PC_condition = TagValues[i + 1];
                if (PC_condition instanceof String[]) {
                    convertValueForBoolean();
                }
                else if (PC_condition instanceof Object[]) {
                    convertValueForEnumeration(value, intvalue);
                }
                else if (PC_condition == Long.class) {
                    convertValueForCurrency(value);
                }
                // No specific handling for the following PC_condition values:
                // LocalDateTime.class (Datetime), Integer.class (Number) and null (String)
                break;
            }
        }
        PC_value.setValue(value[0]);
        updateGuiLater();
    }

    private Integer initializeValueObjects(String[] value) {
        Integer intvalue = null;
        if (PC_valueConverter != null) {
            intvalue = PC_valueConverter.getInteger(value[0]);
            if (intvalue != null)
                value[0] = "" + intvalue;
        } else if (PC_condition == Long.class && value[0] != null && value[0].indexOf('.') >= 0) {
            String s = decimalStringToCurrencyString(value[0]);
            if (s.length() > 0) {
                value[1] = value[0];
                value[0] = s;
            }
        }
        return intvalue;
    }

    private void convertValueForBoolean() {
        // Predefined strings (Boolean)
        for (String s : (String[]) PC_condition)
            PC_value.getItems().add(s);
    }

    private void convertValueForEnumeration(String[] value, Integer intvalue) {
        // PC_condition is array of int - name pairs
        PC_valueConverter = new Values();
        PC_valueConverter.ValueList = (Object[]) PC_condition;
        if (intvalue == null)
            intvalue = PC_valueConverter.getInteger(value[0]);
        for (int j = 0; j < PC_valueConverter.ValueList.length; j += 2) {
            String s = PC_valueConverter.ValueList[j + 1].toString();
            PC_value.getItems().add(s);
            if (s.equals(value[0]) || (intvalue != null && intvalue == PC_valueConverter.getInteger(s)))
                value[0] = s;
        }
    }

    private void convertValueForCurrency(String[] value) {
        if (value[1] == null) {
            String s = currencyStringToDecimalString(value[0], 4);
            if (s.length() > 0) {
                for (int i = 4; i > CurrencyDigits.getValue(); --i) {
                    if (s.charAt(s.length() - 1) == '0')
                        s = s.substring(0, s.length() - 1);
                    else
                        break;
                }
                value[0] = s;
            }
        }
        else
            value[0] = value[1];
    }

    public void checkPCvalue(ActionEvent actionEvent) {
        Object s = null;
        try {
            if (PC_condition == Long.class) {
                s = decimalStringToCurrencyString(PC_value.getValue());
                if (s.toString().length() == 0)
                    throw new Exception("Invalid Currency: " + PC_value.getValue());
            }
            else if (PC_condition == Integer.class) {
                s = Integer.parseInt(PC_value.getValue());
            }
            else if (PC_condition == null) {
                s = PC_value.getValue();
            }
            else if (PC_condition == LocalDateTime.class) {
                s = LocalDateTime.parse(PC_value.getValue(), DateTimeFormatter.ISO_DATE_TIME);
            }
            else if (PC_condition instanceof String[]) {
                for (String h : (String[]) PC_condition) {
                    if (h.equals(PC_value.getValue()))
                        s = h;
                }
            }
            else if (PC_valueConverter != null) {
                s = PC_valueConverter.getValue(PC_value.getValue());
            }
            s.toString();
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
        updateGuiLater();
    }

    public void setPC_value(ActionEvent actionEvent) {
        if (PC_condition == Long.class) {
            String s = PC_value.getValue();
            if (s != null && s.length() > 0 && s.indexOf('.') < 0) {
                s = currencyStringToDecimalString(s, 4);
                if (s.length() > 0) {
                    for (int i = 4; i > CurrencyDigits.getValue(); --i) {
                        if (s.charAt(s.length() - 1) == '0')
                            s = s.substring(0, s.length() - 1);
                        else
                            break;
                    }
                    PC_value.setValue(s);
                }
            }
        }
    }

    Object TheObject = null;

    public void createObject(ActionEvent actionEvent) {
        try {
            String identifier = CO_parameter.getText();
            String classname = CO_classname.getText();
            String factoryname = CO_method.getText();
            if (classname != null && classname.length() > 0) {
                Class<?> creatorclass = Class.forName(classname);
                if (factoryname == null || factoryname.length() == 0) {
                    // Factory for default object
                    Constructor<?> getObject = creatorclass.getConstructor(String.class);
                    TheObject = getObject.newInstance(identifier);
                } else {
                    Method getObject = creatorclass.getMethod(factoryname, String.class);
                    TheObject = getObject.invoke(null, identifier);
                }
            }
            else
                TheObject = identifier;
        } catch (Exception e) {
            getFullErrorMessageAndPrintTrace(e);
        }
    }

    public void setCO_help(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            CO_help.setValue("Help");

        });
    }

    private String TypeValue;

    public void setTypeDataObjectMethod(ActionEvent actionEvent) {
        String method = TypeDataObjectMethods.getValue();
        if ("AccessData".equals(method) || "UpdateData".equals(method)) {
            if (TDOM_type.isDisabled()) {
                TDOM_type.setDisable(false);
                if (TypeValue != null)
                    TDOM_type.setValue(TypeValue);
            }
        } else {
            if (!TDOM_type.isDisabled()) {
                TypeValue = TDOM_type.getValue();
                TDOM_type.setValue("");
                TDOM_type.setDisable(true);
            }
        }
    }

    public void callTypeDataObjectMethod(ActionEvent actionEvent) {
        String method = TypeDataObjectMethods.getValue();
        if ("ActivateService".equals(method))
            activateService(actionEvent);
        else if ("ActivateEVService".equals(method))
            activateEVService(actionEvent);
        else if ("OpenDailyEVService".equals(method))
            openDailyEVService(actionEvent);
        else if ("CloseDailyEVService".equals(method))
            closeDailyEVService(actionEvent);
        else if ("DeactivateEVService".equals(method))
            deactivateEVService(actionEvent);
        else if ("AccessData".equals(method))
            accessData(actionEvent);
        else if ("UpdateData".equals(method))
            updateData(actionEvent);
        else if ("UpdateKey".equals(method))
            updateKey(actionEvent);
    }

    public void callSeqTioMethod(ActionEvent actionEvent) {
        String method = SeqTioMethods.getValue();
        if ("CheckServiceRegistrationToMedium".equals(method))
            checkServiceRegistrationToMedium(actionEvent);
        else if ("RegisterServiceToMedium".equals(method))
            registerServiceToMedium(actionEvent);
        else if ("UnregisterServiceToMedium".equals(method))
            unregisterServiceToMedium(actionEvent);
    }

    public void callSeqTioValMethod(ActionEvent actionEvent) {
        String method = SeqTioMethods.getValue();
        if ("ReadValue".equals(method))
            readValue(actionEvent);
        else if ("AddValue".equals(method))
            addValue(actionEvent);
        else if ("SubtractValue".equals(method))
            subtractValue(actionEvent);
        else if ("WriteValue".equals(method))
            writeValue(actionEvent);
        else if ("CancelValue".equals(method))
            cancelValue(actionEvent);
    }

    abstract private class DataObjectMethod extends MethodProcessor {
        int[] Data = new int[1];
        Object[] Obj = { TheObject };
        DataObjectMethod(int data) {
            super(null);
            Data[0] = data;
        }

        @Override
        void finish() {
            super.finish();
            TheObject = Obj[0];
            TDOM_data.setText("" + Data[0]);
        }
    }

    private class AccessDataHandler extends DataObjectMethod {
        int Type;

        AccessDataHandler(int type, int data) {
            super(data);
            Type = type;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.accessData(Type, Data, Obj);
        }
    }

    public void accessData(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer type = new AD_typeValues().getInteger(TDOM_type.getValue());
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(type, "dataType") && !invalid(data, "data"))
            new AccessDataHandler(type, data).start();
    }

    private abstract class SequenceTimeoutMethod extends MethodProcessor {
        int SequenceNumber;
        int Timeout;
        SequenceTimeoutMethod(int sequenceNumber, int timeout) {
            super(null);
            SequenceNumber = sequenceNumber;
            Timeout = timeout;
        }
    }

    private class AccessLogHandler extends SequenceTimeoutMethod {
        int Type;
        AccessLogHandler(int sequenceNumber, int type, int timeout) {
            super(sequenceNumber, timeout);
            Type = type;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.accessLog(SequenceNumber, Type, Timeout);
        }
    }

    public void accessLog(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(AL_sequenceNumber.getText());
        Integer typ = new AL_typeValues().getInteger(AL_type.getValue());
        Integer tio = new TimeoutValues().getInteger(AL_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(typ, "type") && !invalid(tio, "timeout"))
            new AccessLogHandler(seq, typ, tio).start();
    }

    private class ActivateEVServiceHandler extends DataObjectMethod {
        ActivateEVServiceHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.activateEVService(Data, Obj);
        }
    }

    public void activateEVService(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new ActivateEVServiceHandler(data).start();
    }

    private class ActivateServiceHandler extends DataObjectMethod {
        ActivateServiceHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.activateService(Data, Obj);
        }
    }

    public void activateService(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new ActivateServiceHandler(data).start();
    }

    private class AddValueHandler extends SequenceTimeoutMethod {
        AddValueHandler(int seq, int tio) {
            super(seq, tio);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.addValue(SequenceNumber, Timeout);
        }
    }

    public void addValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(STV_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(STV_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new AddValueHandler(seq, tio).start();
    }

    private class BeginDetectionHandler extends SequenceTimeoutMethod {
        // Store type in SequenceNumber component.
        BeginDetectionHandler(int type, int timeout) {
            super(type, timeout);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.beginDetection(SequenceNumber, Timeout);
        }
    }

    public void beginDetection(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer type = new BD_typeValues().getInteger(BD_type.getValue());
        Integer tio = new TimeoutValues().getInteger(BD_timeout.getValue());
        if (!invalid(type, "type") && !invalid(tio, "timeout"))
            new BeginDetectionHandler(type, tio).start();
    }

    private class BeginRemovalHandler extends SequenceTimeoutMethod {
        BeginRemovalHandler(int timeout) {
            super(0, timeout);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.beginRemoval(Timeout);
        }
    }

    public void beginRemoval(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer tio = new TimeoutValues().getInteger(BR_timeout.getValue());
        if (!invalid(tio, "Timeout"))
            new BeginRemovalHandler(tio).start();
    }

    private class CancelValueHandler extends SequenceTimeoutMethod {
        CancelValueHandler(int seqenceNumber, int timeout) {
            super(seqenceNumber, timeout);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.cancelValue(SequenceNumber, Timeout);
        }
    }

    public void cancelValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(STV_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(STV_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new CancelValueHandler(seq, tio).start();
    }

    private class CaptureCardHandler extends MethodProcessor {
        CaptureCardHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.captureCard();
        }
    }

    public void captureCard(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new CaptureCardHandler().start();
    }

    private class CheckServiceRegistrationToMediumHandler extends SequenceTimeoutMethod {
        CheckServiceRegistrationToMediumHandler(int sequenceNumber, int timeout) {
            super(sequenceNumber, timeout);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.checkServiceRegistrationToMedium(SequenceNumber, Timeout);
        }
    }

    public void checkServiceRegistrationToMedium(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(ST_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(ST_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new CheckServiceRegistrationToMediumHandler(seq, tio).start();
    }

    private class CloseDailyEVServiceHandler extends DataObjectMethod {
        CloseDailyEVServiceHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.closeDailyEVService(Data, Obj);
        }
    }

    public void closeDailyEVService(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new CloseDailyEVServiceHandler(data).start();
    }

    private class DeactivateEVServiceHandler extends DataObjectMethod {
        DeactivateEVServiceHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.deactivateEVService(Data, Obj);
        }
    }

    public void deactivateEVService(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new DeactivateEVServiceHandler(data).start();
    }

    private class EndDetectionHandler extends MethodProcessor {
        EndDetectionHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.endDetection();
        }
    }

    public void endDetection(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EndDetectionHandler().start();
    }

    private class EndRemovalHandler extends MethodProcessor {
        EndRemovalHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.endRemoval();
        }
    }

    public void endRemoval(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EndRemovalHandler().start();
    }

    private class EnumerateCardServicesHandler extends MethodProcessor {
        EnumerateCardServicesHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.enumerateCardServices();
        }
    }

    public void enumerateCardServices(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new EnumerateCardServicesHandler().start();
    }

    private class OpenDailyEVServiceHandler extends DataObjectMethod {
        OpenDailyEVServiceHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.openDailyEVService(Data, Obj);
        }
    }

    public void openDailyEVService(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new OpenDailyEVServiceHandler(data).start();
    }

    private class QueryLastSuccessfulTransactionResultHandler extends MethodProcessor {
        QueryLastSuccessfulTransactionResultHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.queryLastSuccessfulTransactionResult();
        }
    }

    public void queryLastSuccessfulTransactionResult(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new QueryLastSuccessfulTransactionResultHandler().start();
    }

    private class ReadValueHandler extends SequenceTimeoutMethod {
        ReadValueHandler(int sequenceNumber, int timeout) {
            super(sequenceNumber, timeout);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.readValue(SequenceNumber, Timeout);
        }
    }

    public void readValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(STV_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(STV_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new ReadValueHandler(seq, tio).start();
    }

    private class RegisterServiceToMediumHandler extends SequenceTimeoutMethod {
        RegisterServiceToMediumHandler(int seq, int tio) {
            super(seq, tio);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.registerServiceToMedium(SequenceNumber, Timeout);
        }
    }

    public void registerServiceToMedium(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(ST_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(ST_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new RegisterServiceToMediumHandler(seq,tio).start();
    }

    private class SubtractValueHandler extends SequenceTimeoutMethod {
        SubtractValueHandler(int s, int t) {
            super(s, t);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.subtractValue(SequenceNumber, Timeout);
        }
    }

    public void subtractValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(STV_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(STV_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new SubtractValueHandler(seq, tio).start();
    }

    private class TransactionAccessHandler extends SequenceTimeoutMethod {
        // Uses sequenceNumber for parameter control
        TransactionAccessHandler(int control) {
            super(control, 0);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.transactionAccess(SequenceNumber);
        }
    }

    public void transactionAccess(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer ctrl = new TA_controlValues().getInteger(TA_control.getValue());
        if (!invalid(ctrl, "control"))
            new TransactionAccessHandler(ctrl).start();
    }

    private class UnregisterServiceToMediumHandler extends SequenceTimeoutMethod {
        UnregisterServiceToMediumHandler(int s, int t) {
            super(s, t);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.unregisterServiceToMedium(SequenceNumber, Timeout);
        }
    }

    public void unregisterServiceToMedium(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(ST_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(ST_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new UnregisterServiceToMediumHandler(seq, tio).start();
    }

    private class UpdateDataHandler extends AccessDataHandler {
        UpdateDataHandler(int type, int data) {
            super(type, data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.updateData(Type, Data, Obj);
        }
    }

    public void updateData(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer type = new AD_typeValues().getInteger(TDOM_type.getValue());
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(type, "type") && !invalid(data, "data"))
            new UpdateDataHandler(type, data).start();
    }

    private class UpdateKeyHandler extends DataObjectMethod {
        UpdateKeyHandler(int data) {
            super(data);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.updateKey(Data, Obj);
        }
    }

    public void updateKey(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer data = new IntValues().getInteger(TDOM_data.getText());
        if (!invalid(data, "data"))
            new UpdateKeyHandler(data).start();
    }

    private class WriteValueHandler extends SequenceTimeoutMethod {
        WriteValueHandler(int s, int t) {
            super(s, t);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.writeValue(SequenceNumber, Timeout);
        }
    }

    public void writeValue(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(STV_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(STV_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(tio, "timeout"))
            new WriteValueHandler(seq, tio).start();
    }

    private class TransitionEventData {
        TransitionEvent Event;
        SyncObject Waiter;
        boolean JustDisplayed;
        TransitionEventData(TransitionEvent ev) {
            Event = ev;
            Waiter = new SyncObject();
            JustDisplayed = false;
        }
    }

    List<TransitionEventData> Transitions = new ArrayList<>();

    @Override
    public void transitionOccurred(TransitionEvent transitionEvent) {
        TransitionEventData tr = new TransitionEventData(transitionEvent);
        synchronized (this) {
            Transitions.add(tr);
        }
        Platform.runLater(() -> {
            gotTransition(tr.Event);
        });
        tr.Waiter.suspend(SyncObject.INFINITE);
    }

    private void gotTransition(TransitionEvent ev) {
        if (ev != null) {
            updateGui();
            output("TE: " + getLogString(ev));
        }
        TransitionEventData tr;
        while (true) {
            synchronized (this) {
                if (Transitions.size() == 0) {
                    TransitionData.setDisable(true);
                    TransitionString.setDisable(true);
                    TransitionContinue.setVisible(false);
                    return;
                }
                tr = Transitions.get(0);
            }
            if (!tr.JustDisplayed) {
                TransitionEventNumber.setText(new TransitionEventNumberValues().getSymbol(tr.Event.getEventNumber()));
                TransitionData.setText("" + tr.Event.getData());
                TransitionString.setText(tr.Event.getString());
                tr.JustDisplayed = true;
            }
            switch (tr.Event.getEventNumber()) {
                default:
                    synchronized (this) {
                        Transitions.remove(tr);
                    }
                    tr.Waiter.signal();
                    continue;
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_AUTHORIZE:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_AUTO_CHARGE:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_CANCEL:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_CENTER_CHECK:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_CENTER_CHECK_COMPLETE:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_DEVICE_DATA:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_INVALID_OPERATION:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_PAYMENT_CONDITION:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_PIN_ENTRY_BY_OUTER_PINPAD:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_REMAINDER_SUBTRACTION:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_SEARCH_TABLE:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_SELECT:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_TOUCH_RETRY:
                case ElectronicValueRWConst.EVRW_TE_CONFIRM_TOUCH_TIMEOUT:
                    if (ev != null) {
                        TransitionData.setDisable(false);
                        TransitionString.setDisable(false);
                        TransitionContinue.setVisible(true);
                    }
                    return;
            }
        }
    }

    public void transitionContinue(ActionEvent actionEvent) {
        updateGui();
        synchronized (this) {
            if (Transitions.size() > 0) {
                TransitionEventData tr = Transitions.get(0);
                try {
                    tr.Event.setData(Integer.parseInt(TransitionData.getText()));
                    Transitions.remove(tr);
                    tr.Event.setString(TransitionString.getText());
                    tr.Waiter.signal();
                    gotTransition(null);
                } catch (Exception e) {
                    getFullErrorMessageAndPrintTrace(e);
                }
            }
        }
    }

    String getLogString(TransitionEvent event) {
        if (event instanceof JposTransitionEvent)
            return ((JposTransitionEvent) event).toLogString();
        String add = new TransitionEventNumberValues().getSymbol(event.getEventNumber()) +
                " - " + event.getData() + ":";
        String[] object = event.getString().split("\n");
        for (String line : object) {
            add += "\n  " + line;
        }
        return add;
    }

    class AuthorizeCompletionHandler extends CashDepositHandler {
        final long TaxOthers;

        AuthorizeCompletionHandler(int seq, long amount, long taxothers, int tio) {
            super(seq, amount, tio);
            TaxOthers = taxothers;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.authorizeCompletion(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeCompletion(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
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
            TheElectronicValueRW.authorizePreSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizePreSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
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
            TheElectronicValueRW.authorizeRefund(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeRefund(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
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
            TheElectronicValueRW.authorizeSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
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
            TheElectronicValueRW.authorizeVoid(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeVoid(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
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
            TheElectronicValueRW.authorizeVoidPreSales(SequenceNo, Amount, TaxOthers, Timeout);
        }
    }

    public void authorizeVoidPreSales(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer seq = new IntValues().getInteger(A_sequenceNumber.getText());
        Long amount = getDecimalRowValue(new PropertyTableRow("", A_amount.getText()));
        Long taxother = getDecimalRowValue(new PropertyTableRow("", A_taxOthers.getText()));
        Integer tio = new TimeoutValues().getInteger(A_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(taxother, "taxOther") && !invalid(tio, "timeout"))
            new AuthorizeVoidPreSalesHandler(seq, amount, taxother, tio).start();
    }

    public void setTrainingModeState(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setTrainingModeState(new TrainingModeStateValues().getInteger(TrainingModeState.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setPINEntry(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setPINEntry(new PINEntryValues().getInteger(PINEntry.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setApprovalCode(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TextField field = (TextField) actionEvent.getTarget();
                TheElectronicValueRW.setApprovalCode(field.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setMediumID(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setMediumID(MediumID.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setCurrentService(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setCurrentService(CurrentService.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setPaymentMedia(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setPaymentMedia(new PaymentMediaValues().getInteger(PaymentMedia.getValue()));
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAdditionalSecurityInformation(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TextArea target = (TextArea) actionEvent.getTarget();
                TheElectronicValueRW.setAdditionalSecurityInformation(target.getText());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setDetectionControl(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setDetectionControl(DetectionControl.isSelected());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVoucherID(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setVoucherID(VoucherID.getValue());
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAmount(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                long amount = getDecimalRowValue(new PropertyTableRow("", Amount.getText()));
                TheElectronicValueRW.setAmount(amount);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setPoint(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                long points = getDecimalRowValue(new PropertyTableRow("", Point.getText()));
                TheElectronicValueRW.setPoint(points);
            } catch (JposException e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVoucherIDList(ActionEvent actionEvent) {
        if (!InUpdateGui && TheElectronicValueRW.getState() != JposConst.JPOS_S_CLOSED) {
            try {
                TheElectronicValueRW.setVoucherIDList(VoucherIDList.getText());
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
            super(null);
            SequenceNo = seq;
            Type = type;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.accessDailyLog(SequenceNo, Type, Timeout);
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
            super(null);
            SequenceNo = seq;
            Amount = amount;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.cashDeposit(SequenceNo, Amount, Timeout);
        }
    }

    public void cashDeposit(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Long amount = getDecimalRowValue(new PropertyTableRow("", CD_amount.getText()));
        Integer seq = new IntValues().getInteger(CD_sequenceNumber.getText());
        Integer tio = new TimeoutValues().getInteger(CD_timeout.getValue());
        if (!invalid(seq, "sequenceNumber") && !invalid(amount, "amount") && !invalid(tio, "timeout"))
            new CashDepositHandler(seq, amount, tio).start();
    }

    class CheckCardHandler extends MethodProcessor {
        private final int SequenceNo;
        private final int Timeout;

        CheckCardHandler(int seq, int tio) {
            super(null);
            SequenceNo = seq;
            Timeout = tio;
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.checkCard(SequenceNo, Timeout);
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
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.lockTerminal();
        }
    }

    public void lockTerminal(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new LockTerminalHandler().start();
    }

    class UnlockTerminalHandler extends MethodProcessor {
        UnlockTerminalHandler() {
            super(null);
        }

        @Override
        void runIt() throws JposException {
            TheElectronicValueRW.unlockTerminal();
        }
    }

    public void unlockTerminal(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new UnlockTerminalHandler().start();
    }

    public void operationSelection(Event event) {
    }

    public void cardDetectionSelection(Event event) {
    }

    public void authorizationSelection(Event event) {
    }

    public void administrationSelection(Event event) {
    }

    public void maintenanceSelection(Event event) {
    }

    private class CapDailyLogValues extends Values {
        CapDailyLogValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_DL_NONE, "DL_NONE",
                    ElectronicValueRWConst.EVRW_DL_REPORTING, "DL_REPORTING",
                    ElectronicValueRWConst.EVRW_DL_SETTLEMENT, "DL_SETTLEMENT",
                    ElectronicValueRWConst.EVRW_DL_REPORTING_SETTLEMENT, "DL_REPORTING_SETTLEMENT"
            };
        }
    }

    private class LogStatusValues extends Values {
        LogStatusValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_LS_OK, "LS_OK",
                    ElectronicValueRWConst.EVRW_LS_NEARFULL, "LS_NEARFULL",
                    ElectronicValueRWConst.EVRW_LS_FULL, "LS_FULL"
            };
        }
    }

    private class PaymentConditionValues extends Values {
        PaymentConditionValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_PAYMENT_LUMP, "PAYMENT_LUMP",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_1, "PAYMENT_BONUS_1",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_2, "PAYMENT_BONUS_2",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_3, "PAYMENT_BONUS_3",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_4, "PAYMENT_BONUS_4",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_5, "PAYMENT_BONUS_5",
                    ElectronicValueRWConst.EVRW_PAYMENT_INSTALLMENT_1, "PAYMENT_INSTALLMENT_1",
                    ElectronicValueRWConst.EVRW_PAYMENT_INSTALLMENT_2, "PAYMENT_INSTALLMENT_2",
                    ElectronicValueRWConst.EVRW_PAYMENT_INSTALLMENT_3, "PAYMENT_INSTALLMENT_3",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_COMBINATION_1, "PAYMENT_BONUS_COMBINATION_",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_COMBINATION_2, "PAYMENT_BONUS_COMBINATION_2",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_COMBINATION_3, "PAYMENT_BONUS_COMBINATION_3",
                    ElectronicValueRWConst.EVRW_PAYMENT_BONUS_COMBINATION_4, "PAYMENT_BONUS_COMBINATION_4",
                    ElectronicValueRWConst.EVRW_PAYMENT_REVOLVING, "PAYMENT_REVOLVING",
                    ElectronicValueRWConst.EVRW_PAYMENT_DEBIT, "PAYMENT_DEBIT",
                    ElectronicValueRWConst.EVRW_PAYMENT_ELECTRONIC_MONEY, "PAYMENT_ELECTRONIC_MONEY"
            };
        }
    }

    private class PaymentMediaValues extends Values {
        PaymentMediaValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_MEDIA_UNSPECIFIED, "MEDIA_UNSPECIFIED",
                    ElectronicValueRWConst.EVRW_MEDIA_CREDIT, "MEDIA_CREDIT",
                    ElectronicValueRWConst.EVRW_MEDIA_DEBIT, "MEDIA_DEBIT",
                    ElectronicValueRWConst.EVRW_MEDIA_ELECTRONIC_MONEY, "MEDIA_ELECTRONIC_MONEY"
            };
        }
    }

    private class TransactionTypeValues extends Values {
        TransactionTypeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_TRANSACTION_SALES, "TRANSACTION_SALES",
                    ElectronicValueRWConst.EVRW_TRANSACTION_VOID, "TRANSACTION_VOID",
                    ElectronicValueRWConst.EVRW_TRANSACTION_REFUND, "TRANSACTION_REFUND",
                    ElectronicValueRWConst.EVRW_TRANSACTION_COMPLETION, "TRANSACTION_COMPLETION",
                    ElectronicValueRWConst.EVRW_TRANSACTION_PRESALES, "TRANSACTION_PRESALES",
                    ElectronicValueRWConst.EVRW_TRANSACTION_CHECKCARD, "TRANSACTION_CHECKCARD",
                    ElectronicValueRWConst.EVRW_TRANSACTION_VOIDPRESALES, "TRANSACTION_VOIDPRESALES",
                    ElectronicValueRWConst.EVRW_TRANSACTION_CASHDEPOSIT, "TRANSACTION_CASHDEPOSIT"
            };
        }
    }

    private class ADL_TypeValues extends Values {
        ADL_TypeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_DL_REPORTING, "DL_REPORTING",
                    ElectronicValueRWConst.EVRW_DL_SETTLEMENT, "DL_SETTLEMENT"
            };
        }
    }

    private class ErrorCodeExtendedValues extends Values {
        ErrorCodeExtendedValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.JPOS_EEVRW_CENTERERROR, "EEVRW_CENTERERROR",
                    ElectronicValueRWConst.JPOS_EEVRW_COMMANDERROR, "EEVRW_COMMANDERROR",
                    ElectronicValueRWConst.JPOS_EEVRW_RESET, "EEVRW_RESET",
                    ElectronicValueRWConst.JPOS_EEVRW_COMMUNICATIONERROR, "EEVRW_COMMUNICATIONERROR",
                    ElectronicValueRWConst.JPOS_EEVRW_LOGOVERFLOW, "EEVRW_LOGOVERFLOW",
                    ElectronicValueRWConst.JPOS_EEVRW_DAILYLOGOVERFLOW, "EEVRW_DAILYLOGOVERFLOW",
                    ElectronicValueRWConst.JPOS_EEVRW_DEFICIENT, "EEVRW_DEFICIENT",
                    ElectronicValueRWConst.JPOS_EEVRW_OVERDEPOSIT, "EEVRW_OVERDEPOSIT"
            };
        }
    }

    private class ElectronicValueRWStatusUpdateValues extends StatusUpdateValues {
        ElectronicValueRWStatusUpdateValues() {
            super();
            Object[] emvalues = new LogStatusValues().ValueList;
            Object[] evrwvalues = new Object[]{
                    ElectronicValueRWConst.EVRW_SUE_LS_OK, "LS_OK",
                    ElectronicValueRWConst.EVRW_SUE_LS_NEARFULL, "LS_NEARFULL",
                    ElectronicValueRWConst.EVRW_SUE_LS_FULL, "LS_FULL",
                    ElectronicValueRWConst.EVRW_SUE_DS_NOCARD, "DS_NOCARD",
                    ElectronicValueRWConst.EVRW_SUE_DS_DETECTED, "DS_DETECTED",
                    ElectronicValueRWConst.EVRW_SUE_DS_ENTERED, "DS_ENTERED",
                    ElectronicValueRWConst.EVRW_SUE_DS_CAPTURED, "DS_CAPTURED"
            };
            ValueList = Arrays.copyOf(ValueList, ValueList.length + evrwvalues.length + emvalues.length);
            System.arraycopy(emvalues, 0, ValueList, ValueList.length - evrwvalues.length - emvalues.length, emvalues.length);
            System.arraycopy(evrwvalues, 0, ValueList, ValueList.length - evrwvalues.length, evrwvalues.length);
        }
    }

    private class TrainingModeStateValues extends Values {
        TrainingModeStateValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_TM_FALSE, "TM_FALSE",
                    ElectronicValueRWConst.EVRW_TM_TRUE, "TM_TRUE",
                    ElectronicValueRWConst.EVRW_TM_UNKNOWN, "TM_UNKNOWN"
            };
        }
    }

    private class CapCardSensorValues extends Values {
        CapCardSensorValues() {
            ValueList = new Object[]{
                    0, "0",
                    ElectronicValueRWConst.EVRW_CCS_ENTRY, "CCS_ENTRY",
                    ElectronicValueRWConst.EVRW_CCS_DETECT, "CCS_DETECT",
                    ElectronicValueRWConst.EVRW_CCS_DETECT|ElectronicValueRWConst.EVRW_CCS_ENTRY, "CCS_DETECT|CCS_ENTRY",
                    ElectronicValueRWConst.EVRW_CCS_CAPTURE, "CCS_CAPTURE",
                    ElectronicValueRWConst.EVRW_CCS_CAPTURE|ElectronicValueRWConst.EVRW_CCS_ENTRY, "CCS_CAPTURE|CCS_ENTRY",
                    ElectronicValueRWConst.EVRW_CCS_CAPTURE|ElectronicValueRWConst.EVRW_CCS_DETECT, "CCS_CAPTURE|CCS_DETECT",
                    ElectronicValueRWConst.EVRW_CCS_CAPTURE|ElectronicValueRWConst.EVRW_CCS_DETECT|ElectronicValueRWConst.EVRW_CCS_ENTRY, "CCS_CAPTURE|CCS_DETECT|CCS_ENTRY"
            };
        }
    }

    private class CapDetectionControlValues extends Values {
        CapDetectionControlValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_CDC_RWCONTROL, "CDC_RWCONTROL",
                    ElectronicValueRWConst.EVRW_CDC_APPLICATIONCONTROL, "CDC_APPLICATIONCONTROL"
            };
        }
    }

    private class DetectionStatusValues extends Values {
        DetectionStatusValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_DS_NOCARD, "DS_NOCARD",
                    ElectronicValueRWConst.EVRW_DS_DETECTED, "DS_DETECTED",
                    ElectronicValueRWConst.EVRW_DS_ENTERED, "DS_ENTERED",
                    ElectronicValueRWConst.EVRW_DS_CAPTURED, "DS_CAPTURED"
            };
        }
    }

    private class PINEntryValues extends Values {
        PINEntryValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_PIN_ENTRY_NONE, "PIN_ENTRY_NONE",
                    ElectronicValueRWConst.EVRW_PIN_ENTRY_EXTERNAL, "PIN_ENTRY_EXTERNAL",
                    ElectronicValueRWConst.EVRW_PIN_ENTRY_INTERNAL, "PIN_ENTRY_INTERNAL",
                    ElectronicValueRWConst.EVRW_PIN_ENTRY_UNKNOWN, "PIN_ENTRY_UNKNOWN"
            };
        }
    }

    private class ServiceTypeValues extends Values {
        ServiceTypeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_ST_UNSPECIFIED, "ST_UNSPECIFIED",
                    ElectronicValueRWConst.EVRW_ST_ELECTRONIC_MONEY, "ST_ELECTRONIC_MONEY",
                    ElectronicValueRWConst.EVRW_ST_POINT, "ST_POINT",
                    ElectronicValueRWConst.EVRW_ST_VOUCHER, "ST_VOUCHER",
                    ElectronicValueRWConst.EVRW_ST_MEMBERSHIP, "ST_MEMBERSHIP",
                    ElectronicValueRWConst.EVRW_ST_CAT, "ST_CAT"
            };
        }
    }

    private class TransitionEventNumberValues extends Values {
        TransitionEventNumberValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_AUTHORIZE, "TE_CONFIRM_AUTHORIZE",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_AUTO_CHARGE, "TE_CONFIRM_AUTO_CHARGE",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_CANCEL, "TE_CONFIRM_CANCEL",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_CENTER_CHECK, "TE_CONFIRM_CENTER_CHECK",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_CENTER_CHECK_COMPLETE, "TE_CONFIRM_CENTER_CHECK_COMPLETE",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_DEVICE_DATA, "TE_CONFIRM_DEVICE_DATA",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_INVALID_OPERATION, "TE_CONFIRM_INVALID_OPERATION",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_PAYMENT_CONDITION, "TE_CONFIRM_PAYMENT_CONDITION",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_PIN_ENTRY_BY_OUTER_PINPAD, "TE_CONFIRM_PIN_ENTRY_BY_OUTER_PINPAD",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_REMAINDER_SUBTRACTION, "TE_CONFIRM_REMAINDER_SUBTRACTION",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_SEARCH_TABLE, "TE_CONFIRM_SEARCH_TABLE",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_SELECT, "TE_CONFIRM_SELECT",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_TOUCH_RETRY, "TE_CONFIRM_TOUCH_RETRY",
                    ElectronicValueRWConst.EVRW_TE_CONFIRM_TOUCH_TIMEOUT, "TE_CONFIRM_TOUCH_TIMEOUT",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_BUSY, "TE_NOTIFY_BUSY",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_CAPTURE_CARD, "TE_NOTIFY_CAPTURE_CARD",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_CENTER_CHECK, "TE_NOTIFY_CENTER_CHECK",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_CENTER_CHECK_COMPLETE, "TE_NOTIFY_CENTER_CHECK_COMPLETE",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_CHECK_CARD, "TE_NOTIFY_CHECK_CARD",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_COMPLETE, "TE_NOTIFY_COMPLETE",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_INVALID_OPERATION, "TE_NOTIFY_INVALID_OPERATION",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_LOCK, "TE_NOTIFY_LOCK",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_PIN, "TE_NOTIFY_PIN",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_PROGRESS_1_TO_100, "TE_NOTIFY_PROGRESS_1_TO_100",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_SELECT_PAYMENT_CONDITION, "TE_NOTIFY_SELECT_PAYMENT_CONDITION",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_TOUCH, "TE_NOTIFY_TOUCH",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_TOUCH_RETRY, "TE_NOTIFY_TOUCH_RETRY",
                    ElectronicValueRWConst.EVRW_TE_NOTIFY_TOUCH_RETRY_CANCELABLE, "TE_NOTIFY_TOUCH_RETRY_CANCELABLE"
            };
        }
    }

    private class AL_typeValues extends Values {
        AL_typeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_AL_REPORTING, "AL_REPORTING",
                    ElectronicValueRWConst.EVRW_AL_SETTLEMENT, "AL_SETTLEMENT"
            };
        }
    }

    private class BD_typeValues extends Values {
        BD_typeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_BD_ANY, "BD_ANY",
                    ElectronicValueRWConst.EVRW_BD_SPECIFIC, "BD_SPECIFIC"
            };
        }
    }

    private class TA_controlValues extends Values {
        TA_controlValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_TA_TRANSACTION, "TA_TRANSACTION",
                    ElectronicValueRWConst.EVRW_TA_NORMAL, "TA_NORMAL"
            };
        }
    }

    private class AD_typeValues extends Values {
        AD_typeValues() {
            ValueList = new Object[]{
                    ElectronicValueRWConst.EVRW_AD_KEY, "AD_KEY",
                    ElectronicValueRWConst.EVRW_AD_NEGATIVE_LIST, "AD_NEGATIVE_LIST",
                    ElectronicValueRWConst.EVRW_AD_OTHERS, "AD_OTHERS"
            };
        }
    }
}
