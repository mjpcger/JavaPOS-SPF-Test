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

/**
 * Converter for symbolic integer representations.
 */
public class Values {
    Object[] ValueList = new Object[0];

    /**
     * Retrieves symbolic representation of the specified value. If no symbolic representation exists, the value will
     * be simply converted to its string representation.
     * @param value Value to be converted.
     * @return String representation of the given value.
     */
    public String getSymbol(Object value) {
        for (int i = 0; i < ValueList.length - 1; i += 2) {
            if (value.equals(ValueList[i]))
                return (String)ValueList[i + 1];
        }
        return value.toString();
    }

    /**
     * Retrieves the integer value corresponding to the given symbol. If no integer representation exists, the symbol
     * will be converted with the Integer method parseInt(). If parseInt fails, <b>null</b> will be returned.
     * @param symbol Symbolic representation of an integer value.
     * @return The integer value or <b>null</b> if <b>symbol</b> was invalid.
     */
    public Integer getInteger(String symbol) {
        Object obj = getValue(symbol);

        if (obj == null && ValueList.length > 0) {
            if (ValueList[0].getClass() == Integer.class) {
                try {
                    return Integer.parseInt(symbol);
                } catch (Exception e) {
                }
            }
        }
        return (Integer) obj;
    }

    public Object getValue(String symbol) {
        for (int i = 0; i < ValueList.length - 1; i += 2) {
            if (((String)ValueList[i + 1]).equals(symbol))
                return ValueList[i];
        }
        return null;
    }
}
