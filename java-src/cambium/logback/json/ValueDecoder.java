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

public interface ValueDecoder {

    /**
     * No-Operation decoder that simply returns the encoded value.
     */
    public static final ValueDecoder NOP = new ValueDecoder() {
        public Object decode(String encodedValue) {
            return encodedValue;
        }
    };

    public Object decode(String encodedValue);

}
