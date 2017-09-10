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

import java.util.Map;

public interface MapTransformer {

    public static final MapTransformer NOP = new MapTransformer() {
        public Map<String, Object> transform(Map<String, Object> map) {
            return map;
        }
    };

    public Map<String, Object> transform(Map<String, Object> map);

}
