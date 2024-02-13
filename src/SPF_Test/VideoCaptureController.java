/*
 * Copyright 2023 Martin Conrad
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

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jpos.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VideoCaptureController extends CommonController {
    public ComboBox<String> Storage;
    public ComboBox<String> VideoCaptureMode;
    public ComboBox<String> Brightness;
    public ComboBox<String> Contrast;
    public ComboBox<String> Exposure;
    public ComboBox<String> Gain;
    public ComboBox<String> Hue;
    public ComboBox<String> Saturation;
    public ComboBox<String> ColorSpace;
    public ComboBox<String> FrameRate;
    public ComboBox<String> Resolution;
    public ComboBox<String> Type;
    public ComboBox<String> SV_recordingTime;
    public ComboBox<String> TP_timeout;
    public CheckBox AutoExposure;
    public CheckBox AutoFocus;
    public CheckBox AutoGain;
    public CheckBox AutoWhiteBalance;
    public CheckBox HorizontalFlip;
    public CheckBox VerticalFlip;
    public CheckBox SV_overwrite;
    public CheckBox TP_overwrite;
    public TextField RemainingRecordingTimeInSec;
    public TextField SV_fileName;
    public TextField TP_fileName;
    private VideoCapture TheVideoCapture;
    private PropertyTableRow CapStorageRow;
    private PropertyTableRow StorageRow;
    private PropertyTableRow ClaimedRow;
    private PropertyTableRow AutoExposureRow;
    private PropertyTableRow AutoFocusRow;
    private PropertyTableRow AutoGainRow;
    private PropertyTableRow AutoWhiteBalanceRow;
    private PropertyTableRow BrightnessRow;
    private PropertyTableRow CapPhotoRow;
    private PropertyTableRow CapVideoRow;
    private PropertyTableRow ContrastRow;
    private PropertyTableRow ExposureRow;
    private PropertyTableRow GainRow;
    private PropertyTableRow HorizontalFlipRow;
    private PropertyTableRow HueRow;
    private PropertyTableRow PhotoColorSpaceRow;
    private PropertyTableRow PhotoColorSpaceListRow;
    private PropertyTableRow PhotoFrameRateRow;
    private PropertyTableRow PhotoMaxFrameRateRow;
    private PropertyTableRow PhotoResolutionRow;
    private PropertyTableRow PhotoResolutionListRow;
    private PropertyTableRow PhotoTypeRow;
    private PropertyTableRow PhotoTypeListRow;
    private PropertyTableRow SaturationRow;
    private PropertyTableRow VerticalFlipRow;
    private PropertyTableRow VideoCaptureModeRow;
    private PropertyTableRow VideoColorSpaceRow;
    private PropertyTableRow VideoColorSpaceListRow;
    private PropertyTableRow VideoFrameRateRow;
    private PropertyTableRow VideoMaxFrameRateRow;
    private PropertyTableRow VideoResolutionRow;
    private PropertyTableRow VideoResolutionListRow;
    private PropertyTableRow VideoTypeRow;
    private PropertyTableRow VideoTypeListRow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PropertyNameColumnWidth = 165;
        PropertyValueColumnWidth = 229;
        super.initialize(url, resourceBundle);
        TheVideoCapture = (VideoCapture) Control;
        TheVideoCapture.addDirectIOListener(this);
        TheVideoCapture.addStatusUpdateListener(this);
        TheVideoCapture.addErrorListener(this);
        Properties.getItems().add(ClaimedRow = new PropertyTableRow("Claimed", ""));
        Properties.getItems().add(new PropertyTableRow("CheckHealthText", ""));
        Properties.getItems().add(new PropertyTableRow("OutputID", ""));
        Properties.getItems().add(AutoExposureRow = new PropertyTableRow("AutoExposure", ""));
        Properties.getItems().add(AutoFocusRow = new PropertyTableRow("AutoFocus", ""));
        Properties.getItems().add(AutoGainRow = new PropertyTableRow("AutoGain", ""));
        Properties.getItems().add(AutoWhiteBalanceRow = new PropertyTableRow("AutoWhiteBalance", ""));
        Properties.getItems().add(BrightnessRow = new PropertyTableRow("Brightness", ""));
        Properties.getItems().add(new PropertyTableRow("CapAssociatedHardTotalsDevice", ""));
        Properties.getItems().add(new PropertyTableRow("CapAutoExposure", ""));
        Properties.getItems().add(new PropertyTableRow("CapAutoFocus", ""));
        Properties.getItems().add(new PropertyTableRow("CapAutoGain", ""));
        Properties.getItems().add(new PropertyTableRow("CapAutoWhiteBalance", ""));
        Properties.getItems().add(new PropertyTableRow("CapBrightness", ""));
        Properties.getItems().add(new PropertyTableRow("CapContrast", ""));
        Properties.getItems().add(new PropertyTableRow("CapExposure", ""));
        Properties.getItems().add(new PropertyTableRow("CapGain", ""));
        Properties.getItems().add(new PropertyTableRow("CapHorizontalFlip", ""));
        Properties.getItems().add(new PropertyTableRow("CapHue", ""));
        Properties.getItems().add(CapPhotoRow = new PropertyTableRow("CapPhoto", ""));
        Properties.getItems().add(new PropertyTableRow("CapPhotoColorSpace", ""));
        Properties.getItems().add(new PropertyTableRow("CapPhotoFrameRate", ""));
        Properties.getItems().add(new PropertyTableRow("CapPhotoResolution", ""));
        Properties.getItems().add(new PropertyTableRow("CapPhotoType", ""));
        Properties.getItems().add(new PropertyTableRow("CapSaturation", ""));
        Properties.getItems().add(CapStorageRow = new PropertyTableRow("CapStorage", "", new CapStorageValues()));
        Properties.getItems().add(new PropertyTableRow("CapVerticalFlip", ""));
        Properties.getItems().add(CapVideoRow = new PropertyTableRow("CapVideo", ""));
        Properties.getItems().add(new PropertyTableRow("CapVideoColorSpace", ""));
        Properties.getItems().add(new PropertyTableRow("CapVideoFrameRate", ""));
        Properties.getItems().add(new PropertyTableRow("CapVideoResolution", ""));
        Properties.getItems().add(new PropertyTableRow("CapVideoType", ""));
        Properties.getItems().add(ContrastRow = new PropertyTableRow("Contrast", ""));
        Properties.getItems().add(ExposureRow = new PropertyTableRow("Exposure", ""));
        Properties.getItems().add(GainRow = new PropertyTableRow("Gain", ""));
        Properties.getItems().add(HorizontalFlipRow = new PropertyTableRow("HorizontalFlip", ""));
        Properties.getItems().add(HueRow = new PropertyTableRow("Hue", ""));
        Properties.getItems().add(PhotoColorSpaceRow = new PropertyTableRow("PhotoColorSpace", ""));
        Properties.getItems().add(PhotoColorSpaceListRow = new PropertyTableRow("PhotoColorSpaceList", ""));
        Properties.getItems().add(PhotoFrameRateRow = new PropertyTableRow("PhotoFrameRate", ""));
        Properties.getItems().add(PhotoMaxFrameRateRow = new PropertyTableRow("PhotoMaxFrameRate", ""));
        Properties.getItems().add(PhotoResolutionRow = new PropertyTableRow("PhotoResolution", ""));
        Properties.getItems().add(PhotoResolutionListRow = new PropertyTableRow("PhotoResolutionList", ""));
        Properties.getItems().add(PhotoTypeRow = new PropertyTableRow("PhotoType", ""));
        Properties.getItems().add(PhotoTypeListRow = new PropertyTableRow("PhotoTypeList", ""));
        Properties.getItems().add(new PropertyTableRow("RemainingRecordingTimeInSec", ""));
        Properties.getItems().add(SaturationRow = new PropertyTableRow("Saturation", ""));
        Properties.getItems().add(StorageRow = new PropertyTableRow("Storage", "", new StorageValues()));
        Properties.getItems().add(VerticalFlipRow = new PropertyTableRow("VerticalFlip", ""));
        Properties.getItems().add(VideoCaptureModeRow = new PropertyTableRow("VideoCaptureMode", "", new VideoCaptureModeValues()));
        Properties.getItems().add(VideoColorSpaceRow = new PropertyTableRow("VideoColorSpace", ""));
        Properties.getItems().add(VideoColorSpaceListRow = new PropertyTableRow("VideoColorSpaceList", ""));
        Properties.getItems().add(VideoFrameRateRow = new PropertyTableRow("VideoFrameRate", ""));
        Properties.getItems().add(VideoMaxFrameRateRow = new PropertyTableRow("VideoMaxFrameRate", ""));
        Properties.getItems().add(VideoResolutionRow = new PropertyTableRow("VideoResolution", ""));
        Properties.getItems().add(VideoResolutionListRow = new PropertyTableRow("VideoResolutionList", ""));
        Properties.getItems().add(VideoTypeRow = new PropertyTableRow("VideoType", ""));
        Properties.getItems().add(VideoTypeListRow = new PropertyTableRow("VideoTypeList", ""));
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
        String forever = new TimeoutValues().getSymbol(JposConst.JPOS_FOREVER);
        SV_recordingTime.getItems().add(forever);
        TP_timeout.getItems().add(forever);
        updateGui();
    }

    @Override
    void updateGui() {
        String photo = CapPhotoRow.getValue();
        String video = CapVideoRow.getValue();
        String mode = VideoCaptureModeRow.getValue();
        super.updateGui();
        if (!InUpdateGui) {
            InUpdateGui = true;
            if (ClaimedRow.getValue().toLowerCase().equals("true")) {
                Integer cst = CapStorageRow.getValueConverter().getInteger(CapStorageRow.getValue());
                Storage.getItems().clear();
                if (cst != null) {
                    String value;
                    if (cst == SoundRecorderConst.SREC_CST_HOST_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundRecorderConst.SREC_ST_HOST));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundRecorderConst.SREC_CST_HARDTOTALS_ONLY) {
                        Storage.getItems().add(value = StorageRow.getValueConverter().getSymbol(SoundRecorderConst.SREC_ST_HARDTOTALS));
                        if (value.equals(StorageRow.getValue()))
                            Storage.setValue(value);
                    } else if (cst == SoundRecorderConst.SREC_CST_ALL) {
                        Object[] o = StorageRow.getValueConverter().ValueList;
                        for (int i = 1; i < o.length; i += 2) {
                            Storage.getItems().add(o[i].toString());
                            if (StorageRow.getValue().equals(o[i].toString()))
                                Storage.setValue(StorageRow.getValue());
                        }
                    }
                }
            } else {
                Storage.getItems().clear();
            }
            if (!StorageRow.getValue().equals(Storage.getValue()))
                Storage.setValue(StorageRow.getValue());
            if (AutoExposureRow.getValue().equals("true") != AutoExposure.isSelected())
                AutoExposure.setSelected(AutoExposureRow.getValue().equals("true"));
            if (AutoFocusRow.getValue().equals("true") != AutoFocus.isSelected())
                AutoFocus.setSelected(AutoFocusRow.getValue().equals("true"));
            if (AutoGainRow.getValue().equals("true") != AutoGain.isSelected())
                AutoGain.setSelected(AutoGainRow.getValue().equals("true"));
            if (AutoWhiteBalanceRow.getValue().equals("true") != AutoWhiteBalance.isSelected())
                AutoWhiteBalance.setSelected(AutoWhiteBalanceRow.getValue().equals("true"));
            if (HorizontalFlipRow.getValue().equals("true") != HorizontalFlip.isSelected())
                HorizontalFlip.setSelected(HorizontalFlipRow.getValue().equals("true"));
            if (VerticalFlipRow.getValue().equals("true") != VerticalFlip.isSelected())
                VerticalFlip.setSelected(VerticalFlipRow.getValue().equals("true"));
            if (!BrightnessRow.getValue().equals(Brightness.getValue()))
                Brightness.setValue(BrightnessRow.getValue());
            if (!ContrastRow.getValue().equals(Contrast.getValue()))
                Contrast.setValue(ContrastRow.getValue());
            if (!ExposureRow.getValue().equals(Exposure.getValue()))
                Exposure.setValue(ExposureRow.getValue());
            if (!GainRow.getValue().equals(Gain.getValue()))
                Gain.setValue(GainRow.getValue());
            if (!HueRow.getValue().equals(Hue.getValue()))
                Hue.setValue(HueRow.getValue());
            if (!SaturationRow.getValue().equals(Saturation.getValue()))
                Saturation.setValue(SaturationRow.getValue());
            if (!photo.equals(CapPhotoRow.getValue()) || !video.equals(CapVideoRow.getValue())) {
                VideoCaptureMode.getItems().clear();
                if (CapPhotoRow.getValue().equals("true")) {
                    String value = VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO);
                    VideoCaptureMode.getItems().add(value);
                    if (value.equals(VideoCaptureModeRow.getValue()))
                        VideoCaptureMode.setValue(value);
                }
                if (CapVideoRow.getValue().equals("true")) {
                    String value = VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO);
                    VideoCaptureMode.getItems().add(value);
                    if (value.equals(VideoCaptureModeRow.getValue()))
                        VideoCaptureMode.setValue(value);
                }
            }
            if (!VideoCaptureModeRow.getValue().equals(mode)) {
                VideoCaptureMode.setValue(VideoCaptureModeRow.getValue());
                ColorSpace.getItems().clear();
                FrameRate.getItems().clear();
                Resolution.getItems().clear();
                Type.getItems().clear();
                if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))) {
                    if (PhotoColorSpaceListRow.getValue().length() > 0) {
                        String[] values = PhotoColorSpaceListRow.getValue().split(",");
                        for (String value : values) {
                            ColorSpace.getItems().add(value);
                            if (value.equals(PhotoColorSpaceRow))
                                ColorSpace.setValue(value);
                        }
                    }
                    Integer max = new IntValues().getInteger(PhotoMaxFrameRateRow.getValue());
                    if (max != null && max > 0) {
                        for (Integer i = 1; i <= max; i++) {
                            FrameRate.getItems().add(i.toString());
                            if (i == new IntValues().getInteger(PhotoFrameRateRow.getValue()))
                                FrameRate.setValue(i.toString());
                        }
                    }
                    if (PhotoResolutionListRow.getValue().length() > 0) {
                        String[] values = PhotoResolutionListRow.getValue().split(",");
                        for (String value : values) {
                            Resolution.getItems().add(value);
                            if (value.equals(PhotoResolutionRow))
                                Resolution.setValue(value);
                        }
                    }
                    if (PhotoTypeListRow.getValue().length() > 0) {
                        String[] values = PhotoTypeListRow.getValue().split(",");
                        for (String value : values) {
                            Type.getItems().add(value);
                            if (value.equals(PhotoTypeRow))
                                Type.setValue(value);
                        }
                    }
                } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))) {
                    if (VideoColorSpaceListRow.getValue().length() > 0) {
                        String[] values = VideoColorSpaceListRow.getValue().split(",");
                        for (String value : values) {
                            ColorSpace.getItems().add(value);
                            if (value.equals(VideoColorSpaceRow))
                                ColorSpace.setValue(value);
                        }
                    }
                    Integer max = new IntValues().getInteger(VideoMaxFrameRateRow.getValue());
                    if (max != null && max > 0) {
                        for (Integer i = 1; i <= max; i++) {
                            FrameRate.getItems().add(i.toString());
                            if (i == new IntValues().getInteger(VideoFrameRateRow.getValue()))
                                FrameRate.setValue(i.toString());
                        }
                    }
                    if (VideoResolutionListRow.getValue().length() > 0) {
                        String[] values = VideoResolutionListRow.getValue().split(",");
                        for (String value : values) {
                            Resolution.getItems().add(value);
                            if (value.equals(VideoResolutionRow))
                                Resolution.setValue(value);
                        }
                    }
                    if (VideoTypeListRow.getValue().length() > 0) {
                        String[] values = VideoTypeListRow.getValue().split(",");
                        for (String value : values) {
                            Type.getItems().add(value);
                            if (value.equals(VideoTypeRow))
                                Type.setValue(value);
                        }
                    }
                }
            }
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))
                    && !PhotoColorSpaceRow.getValue().equals(ColorSpace.getValue()))
                ColorSpace.setValue(PhotoColorSpaceRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))
                    && !PhotoFrameRateRow.getValue().equals(FrameRate.getValue()))
                FrameRate.setValue(PhotoFrameRateRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))
                    && !PhotoResolutionRow.getValue().equals(Resolution.getValue()))
                Resolution.setValue(PhotoResolutionRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))
                    && !PhotoTypeRow.getValue().equals(Type.getValue()))
                Type.setValue(PhotoTypeRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))
                    && !VideoColorSpaceRow.getValue().equals(ColorSpace.getValue()))
                ColorSpace.setValue(VideoColorSpaceRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))
                    && !VideoFrameRateRow.getValue().equals(FrameRate.getValue()))
                FrameRate.setValue(VideoFrameRateRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))
                    && !VideoResolutionRow.getValue().equals(Resolution.getValue()))
                Resolution.setValue(VideoResolutionRow.getValue());
            if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))
                    && !VideoTypeRow.getValue().equals(Type.getValue()))
                Type.setValue(VideoTypeRow.getValue());
            InUpdateGui = false;
        }
    }

    public void setStorage(ActionEvent actionEvent) {
        if (!InUpdateGui && !StorageRow.getValue().equals(Storage.getValue())) {
            try {
                TheVideoCapture.setStorage(StorageRow.getValueConverter().getInteger(Storage.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVideoCaptureMode(ActionEvent actionEvent) {
        if (!InUpdateGui && !VideoCaptureModeRow.getValue().equals(VideoCaptureMode.getValue())) {
            try {
                TheVideoCapture.setVideoCaptureMode(VideoCaptureModeRow.getValueConverter().getInteger(VideoCaptureMode.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void getRemainingRecordingTimeInSec(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            updateGui();
        }
    }

    public void setAutoExposure(ActionEvent actionEvent) {
        if (!InUpdateGui && AutoExposureRow.getValue().toLowerCase().equals("true") != AutoExposure.isSelected()) {
            try {
                TheVideoCapture.setAutoExposure(AutoExposure.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAutoFocus(ActionEvent actionEvent) {
        if (!InUpdateGui && AutoFocusRow.getValue().toLowerCase().equals("true") != AutoFocus.isSelected()) {
            try {
                TheVideoCapture.setAutoFocus(AutoFocus.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAutoGain(ActionEvent actionEvent) {
        if (!InUpdateGui && AutoGainRow.getValue().toLowerCase().equals("true") != AutoGain.isSelected()) {
            try {
                TheVideoCapture.setAutoGain(AutoGain.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setAutoWhiteBalance(ActionEvent actionEvent) {
        if (!InUpdateGui && AutoWhiteBalanceRow.getValue().toLowerCase().equals("true") != AutoWhiteBalance.isSelected()) {
            try {
                TheVideoCapture.setAutoWhiteBalance(AutoWhiteBalance.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setHorizontalFlip(ActionEvent actionEvent) {
        if (!InUpdateGui && HorizontalFlipRow.getValue().toLowerCase().equals("true") != HorizontalFlip.isSelected()) {
            try {
                TheVideoCapture.setHorizontalFlip(HorizontalFlip.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setVerticalFlip(ActionEvent actionEvent) {
        if (!InUpdateGui && VerticalFlipRow.getValue().toLowerCase().equals("true") != VerticalFlip.isSelected()) {
            try {
                TheVideoCapture.setVerticalFlip(VerticalFlip.isSelected());
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setBrightness(ActionEvent actionEvent) {
        if (!InUpdateGui && !BrightnessRow.getValue().equals(Brightness.getValue())) {
            try {
                TheVideoCapture.setBrightness(Integer.parseInt(Brightness.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setContrast(ActionEvent actionEvent) {
        if (!InUpdateGui && !ContrastRow.getValue().equals(Contrast.getValue())) {
            try {
                TheVideoCapture.setContrast(Integer.parseInt(Contrast.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setExposure(ActionEvent actionEvent) {
        if (!InUpdateGui && !ExposureRow.getValue().equals(Exposure.getValue())) {
            try {
                TheVideoCapture.setExposure(Integer.parseInt(Exposure.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setGain(ActionEvent actionEvent) {
        if (!InUpdateGui && !GainRow.getValue().equals(Gain.getValue())) {
            try {
                TheVideoCapture.setGain(Integer.parseInt(Gain.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setHue(ActionEvent actionEvent) {
        if (!InUpdateGui && !HueRow.getValue().equals(Hue.getValue())) {
            try {
                TheVideoCapture.setHue(Integer.parseInt(Hue.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setSaturation(ActionEvent actionEvent) {
        if (!InUpdateGui && !SaturationRow.getValue().equals(Saturation.getValue())) {
            try {
                TheVideoCapture.setSaturation(Integer.parseInt(Saturation.getValue()));
            } catch (Exception e) {
                getFullErrorMessageAndPrintTrace(e);
            }
            updateGuiLater();
        }
    }

    public void setColorSpace(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (VideoCaptureModeRow.getValue().equals("")) {
                getFullErrorMessageAndPrintTrace(new JposException(JposConst.JPOS_E_NOSERVICE, "Device not opened"));
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))) {
                if (!PhotoColorSpaceRow.getValue().equals(ColorSpace.getValue())) {
                    try {
                        TheVideoCapture.setPhotoColorSpace(ColorSpace.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))) {
                if (!VideoColorSpaceRow.getValue().equals(ColorSpace.getValue())) {
                    try {
                        TheVideoCapture.setVideoColorSpace(ColorSpace.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            }
        }
    }

    public void setFrameRate(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (VideoCaptureModeRow.getValue().equals("")) {
                getFullErrorMessageAndPrintTrace(new JposException(JposConst.JPOS_E_NOSERVICE, "Device not opened"));
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))) {
                if (!PhotoFrameRateRow.getValue().equals(FrameRate.getValue())) {
                    try {
                        TheVideoCapture.setPhotoFrameRate(Integer.parseInt(FrameRate.getValue()));
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))) {
                if (!VideoFrameRateRow.getValue().equals(FrameRate.getValue())) {
                    try {
                        TheVideoCapture.setVideoFrameRate(Integer.parseInt(FrameRate.getValue()));
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            }
        }
    }

    public void setResolution(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (VideoCaptureModeRow.getValue().equals("")) {
                getFullErrorMessageAndPrintTrace(new JposException(JposConst.JPOS_E_NOSERVICE, "Device not opened"));
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))) {
                if (!PhotoResolutionRow.getValue().equals(Resolution.getValue())) {
                    try {
                        TheVideoCapture.setPhotoResolution(Resolution.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))) {
                if (!VideoResolutionRow.getValue().equals(Resolution.getValue())) {
                    try {
                        TheVideoCapture.setVideoResolution(Resolution.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            }
        }
    }

    public void setType(ActionEvent actionEvent) {
        if (!InUpdateGui) {
            if (VideoCaptureModeRow.getValue().equals("")) {
                getFullErrorMessageAndPrintTrace(new JposException(JposConst.JPOS_E_NOSERVICE, "Device not opened"));
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_PHOTO))) {
                if (!PhotoTypeRow.getValue().equals(Type.getValue())) {
                    try {
                        TheVideoCapture.setPhotoType(Type.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            } else if (VideoCaptureModeRow.getValue().equals(VideoCaptureModeRow.getValueConverter().getSymbol(VideoCaptureConst.VCAP_VCMODE_VIDEO))) {
                if (!VideoTypeRow.getValue().equals(Type.getValue())) {
                    try {
                        TheVideoCapture.setVideoType(Type.getValue());
                    } catch (Exception e) {
                        getFullErrorMessageAndPrintTrace(e);
                    }
                    updateGuiLater();
                }
            }
        }
    }

    class StartVideo extends MethodProcessor {
        private final String FileName;
        private final boolean Overwrite;
        private final int RecordingTime;

        StartVideo(String fileName, boolean overwrite, int recordingTime) {
            super("StartVideo");
            FileName = fileName;
            Overwrite = overwrite;
            RecordingTime = recordingTime;
        }

        @Override
        void runIt() throws JposException {
            TheVideoCapture.startVideo(FileName, Overwrite, RecordingTime);
        }
    }

    public void startVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer recordingTime = new IntValues().getInteger(SV_recordingTime.getValue());
        String filename = SV_fileName.getText();
        boolean overwrite = SV_overwrite.isSelected();
        if (!invalid(filename, "fileName") && !invalid(recordingTime, "recordingTime"))
            new StartVideo(filename, overwrite, recordingTime).start();
    }

    class StopVideo extends MethodProcessor {
        StopVideo() {
            super("StopVideo");
        }

        @Override
        void runIt() throws JposException {
            TheVideoCapture.stopVideo();
        }
    }

    public void stopVideo(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        new StopVideo().start();
    }

    class TakePhoto extends MethodProcessor {
        private final String FileName;
        private final boolean Overwrite;
        private final int Timeout;

        TakePhoto(String fileName, boolean overwrite, int timeout) {
            super("TakePhoto");
            FileName = fileName;
            Overwrite = overwrite;
            Timeout = timeout;
        }

        @Override
        void runIt() throws JposException {
            TheVideoCapture.takePhoto(FileName, Overwrite, Timeout);
        }
    }

    public void takePhoto(ActionEvent actionEvent) {
        if (isMethodRunning())
            return;
        Integer timeout = new TimeoutValues().getInteger(TP_timeout.getValue());
        String filename = TP_fileName.getText();
        boolean overwrite = TP_overwrite.isSelected();
        if (!invalid(filename, "fileName") && !invalid(timeout, "timeout"))
            new TakePhoto(filename, overwrite, timeout).start();
    }

    private class CapStorageValues extends IntValues {
        CapStorageValues() {
            ValueList = new Object[]{
                    VideoCaptureConst.VCAP_CST_HOST_ONLY, "CST_HOST_ONLY",
                    VideoCaptureConst.VCAP_CST_HARDTOTALS_ONLY, "CST_HARDTOTALS_ONLY",
                    VideoCaptureConst.VCAP_CST_ALL, "CST_ALL"
            };
        }
    }

    private class StorageValues extends IntValues {
        StorageValues() {
            ValueList = new Object[]{
                    VideoCaptureConst.VCAP_ST_HOST, "ST_HOST",
                    VideoCaptureConst.VCAP_ST_HARDTOTALS, "ST_HARDTOTALS",
                    VideoCaptureConst.VCAP_ST_HOST_HARDTOTALS, "ST_HOST_HARDTOTALS"
            };
        }
    }

    private class VideoCaptureModeValues extends IntValues {
        VideoCaptureModeValues() {
            ValueList = new Object[]{
                    VideoCaptureConst.VCAP_VCMODE_PHOTO, "VCMODE_PHOTO",
                    VideoCaptureConst.VCAP_VCMODE_VIDEO, "VCMODE_VIDEO"
            };
        }
    }
}
