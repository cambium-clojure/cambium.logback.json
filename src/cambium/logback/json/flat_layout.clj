;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns cambium.logback.json.flat-layout
  (:import
    [cambium.logback.json FlatJsonLayout MapTransformer ValueDecoder]))


(defn set-decoder!
  "Given a fn `(fn [^String encoded]) -> any` or cambium.logback.json.ValueDecoder instance, set the global decoder for
  the cambium.logback.json.FlatJsonLayout layout. The decoder is used for every MDC value ahead of the transformer."
  [decoder]
  (FlatJsonLayout/setGlobalDecoder
    ^ValueDecoder
    (if (instance? ValueDecoder decoder)
      decoder
      (reify ValueDecoder
        (decode [this encoded-value] (decoder encoded-value))))))


(defn set-transformer!
  "Given a fn `(map<String, Object> m) -> map<String, Object>` or cambium.logback.json.MapTransformer instance, set the
  global transformer for the cambium.logback.json.FlatJsonLayout layout. The map argument passed to the transformer is
  a mutable java.util.Map instance; however the transformer also must return a java.util.Map as result."
  [xformer]
  (FlatJsonLayout/setGlobalTransformer
    ^MapTransformer
    (if (instance? MapTransformer xformer)
      xformer
      (reify MapTransformer
        (transform [this mdc-map] (xformer mdc-map))))))
