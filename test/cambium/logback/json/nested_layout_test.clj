;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns cambium.logback.json.nested-layout-test
  (:require
    [clojure.test :refer :all]
    [cambium.codec :as codec]
    [cambium.core  :as log]
    [cambium.logback.json.flat-layout :as flat])
  (:import
    [java.util Map]))


(defn flat-decode
  [f]
  (flat/set-decoder! codec/destringify-val)
  (flat/set-transformer! (fn [^Map m] (doto m (.put "inserted" "entry"))))
  (f))


(use-fixtures :once flat-decode)


(deftest a-test
  (log/info {:foo 10
             :bar true} "sample1")
  (log/info {:foo {:bar 10
                   :baz 20}} "sample2"))
