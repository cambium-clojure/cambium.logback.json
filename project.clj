(defproject cambium/cambium.logback.json "0.4.2"
  :description "JSON Logback backend for Cambium"
  :url "https://github.com/cambium-clojure/cambium.logback.json"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cambium/cambium.logback.core "0.4.2"]
                 [com.fasterxml.jackson.core/jackson-core     "2.9.0"]  ; in use by cheshire 5.8.0
                 [com.fasterxml.jackson.core/jackson-databind "2.9.0"]  ; in use by cheshire 5.8.0
                 [ch.qos.logback.contrib/logback-json-classic "0.1.5"]
                 [ch.qos.logback.contrib/logback-jackson      "0.1.5"]]
  :java-source-paths ["java-src"]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :global-vars {*warn-on-reflection* true
                *assert* true
                *unchecked-math* :warn-on-boxed}
  :profiles {:provided {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :dev {:dependencies [[cambium/cambium.core "0.9.2"]  ; pulls in [org.slf4j/slf4j-api "1.7.25"]
                                  [cambium/cambium.codec-simple "0.9.2"]]
                   :jvm-opts ["-Denable.dummy=true"]}
             :c15 {:dependencies [[org.clojure/clojure  "1.5.1"]]}
             :c16 {:dependencies [[org.clojure/clojure  "1.6.0"]]}
             :c17 {:dependencies [[org.clojure/clojure  "1.7.0"]]}
             :c18 {:dependencies [[org.clojure/clojure  "1.8.0"]]}
             :c19 {:dependencies [[org.clojure/clojure  "1.9.0"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

