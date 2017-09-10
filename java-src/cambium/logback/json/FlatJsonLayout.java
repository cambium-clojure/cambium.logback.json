/**
 *   Copyright (c) Shantanu Kumar. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file LICENSE at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 *   the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

package cambium.logback.json;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.contrib.json.classic.JsonLayout;

/**
 * This class renders a "flat" JSON layout, in which the MDC (Mapped Diagnostic Context) map is merged into the
 * top-level attribute map. Rest all remains the same as the base class {@link JsonLayout}.
 *
 */
public class FlatJsonLayout extends JsonLayout {

    private static volatile ValueDecoder decoder = ValueDecoder.NOP;
    private static volatile MapTransformer xformer = MapTransformer.NOP;

    public static void setGlobalDecoder(ValueDecoder valueDecoder) {
        decoder = valueDecoder;
    }

    public static void setGlobalTransformer(MapTransformer mapTransformer) {
        xformer = mapTransformer;
    }

    @Override
    protected Map<?, ?> toJsonMap(ILoggingEvent event) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if (this.includeTimestamp) {
            long timestamp = event.getTimeStamp();
            String formatted = formatTimestamp(timestamp);
            if (formatted != null) {
                map.put(TIMESTAMP_ATTR_NAME, formatted);
            }
        }

        if (this.includeLevel) {
            Level level = event.getLevel();
            if (level != null) {
                String lvlString = String.valueOf(level);
                map.put(LEVEL_ATTR_NAME, lvlString);
            }
        }

        if (this.includeThreadName) {
            String threadName = event.getThreadName();
            if (threadName != null) {
                map.put(THREAD_ATTR_NAME, threadName);
            }
        }

        if (this.includeMDC) {
            Map<String, String> mdc = event.getMDCPropertyMap();
            if ((mdc != null) && !mdc.isEmpty()) {
                // unlike the default JsonLayout, here we merge the MDC map into the top-level map
                final ValueDecoder valueDecoder = decoder; // 'decoder' is volatile - avoid repetitive read by caching
                for (final Entry<String, String> pair: mdc.entrySet()) {
                    map.put(pair.getKey(), valueDecoder.decode(pair.getValue()));
                }
                //map.put(MDC_ATTR_NAME, mdc);  // this is what JsonLayout does with MDC
            }
        }

        if (this.includeLoggerName) {
            String loggerName = event.getLoggerName();
            if (loggerName != null) {
                map.put(LOGGER_ATTR_NAME, loggerName);
            }
        }

        if (this.includeFormattedMessage) {
            String msg = event.getFormattedMessage();
            if (msg != null) {
                map.put(FORMATTED_MESSAGE_ATTR_NAME, msg);
            }
        }

        if (this.includeMessage) {
            String msg = event.getMessage();
            if (msg != null) {
                map.put(MESSAGE_ATTR_NAME, msg);
            }
        }

        if (this.includeContextName) {
            String msg = event.getLoggerContextVO().getName();
            if (msg != null) {
                map.put(CONTEXT_ATTR_NAME, msg);
            }
        }

        if (this.includeException) {
            IThrowableProxy throwableProxy = event.getThrowableProxy();
            if (throwableProxy != null) {
                String ex = getThrowableProxyConverter().convert(event);
                if (ex != null && !ex.equals("")) {
                    map.put(EXCEPTION_ATTR_NAME, ex);
                }
            }
        }

        return xformer.transform(map);
    }

}
