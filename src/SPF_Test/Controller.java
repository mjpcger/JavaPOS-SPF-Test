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

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Main JavaFX control. Initializes DeviceControl objects for all configured devices and manages device specific
 * GUI changes whenever a new logical name has been selected.
 */
public class Controller implements Initializable {
    @FXML
    public ComboBox<String> DeviceCategory;
    @FXML
    public ComboBox<String> LogicalName;
    @FXML
    public AnchorPane DevicePane;
    @FXML
    public Text Usage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeviceControl.loadDevices("jpos.xml", this);
        ArrayList<String> caterories = DeviceControl.getCategories();
        Collections.sort(caterories);
        DeviceCategory.setItems(FXCollections.observableList(caterories));
        DeviceCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> devices = DeviceControl.getNames(DeviceCategory.getValue());
                Collections.sort(devices);
                LogicalName.setItems(FXCollections.observableArrayList(devices));
                LogicalName.setValue(devices.get(0));
            }
        });
    }

    @FXML
    public void handleSetLogicalName(ActionEvent actionEvent) {
        DeviceControl current = DeviceControl.Devices.get(LogicalName.getValue());
        if (current != null) {
            DevicePane.getChildren().clear();
            Usage.setText(current.getUsage());
            if (current.Gui != null)
                DevicePane.getChildren().addAll(current.Gui);
        }
    }
}
