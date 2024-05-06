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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jpos.*;

import java.util.Objects;

/**
 * Main application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SPF-Test.fxml")));
        primaryStage.setTitle("JavaPOS-SPF Test");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.show();
    }

    /**
     * Main method. Final operation is closing all possibly open JPOS control objects.
     * @param args  Ignored.
     */
    public static void main(String[] args) {
        try {
            test();
            launch(args);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            for (DeviceControl dev : DeviceControl.Devices.values()) {
                if (dev.getControl() != null && dev.getControl().getState() != JposConst.JPOS_S_CLOSED) {
                    try {
                        dev.Controller.close(null);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Insert stuff for later use, to test how it works
     */
    static private void test() {
    }
}
