/*
 * Copyright 2016, OpenRemote Inc.
 *
 * See the CONTRIBUTORS.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.openremote.agent.protocol.simulator.element;

import org.openremote.model.attribute.AttributeType;
import org.openremote.model.value.Value;
import org.openremote.model.value.Values;

public class IntegerSimulatorElement extends SimulatorElement {

    public static final String ELEMENT_NAME_INTEGER = "integer";
    public static final String ELEMENT_NAME_RANGE = "range";

    final protected Integer min;
    final protected Integer max;

    public IntegerSimulatorElement() {
        this(null, null);
    }

    public IntegerSimulatorElement(Integer min, Integer max) {
        super(AttributeType.INTEGER);
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    @Override
    protected boolean isValid(Value value) {
        boolean valid = super.isValid(value);
        return valid && (
            value == null || (
                (getMin() == null || Values.getNumber(value).filter(v -> v >= getMin()).isPresent())
                    && (getMax() == null || Values.getNumber(value).filter(v -> v <= getMax()).isPresent())
            )
        );
    }

}