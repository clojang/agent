(ns clojang.agent.startup
  ""
  (:require [clojure.tools.logging :as log]
            [jiface.nodes :as nodes]
            [jiface.util :as util]
            [twig.core :as twig])
  (:import [java.lang.instrument])
  (:gen-class
    :methods [^:static [premain [String java.lang.instrument.Instrumentation] void]]))

(defn get-node-name
  "Get the node name."
  []
  (format "%s@%s"
          (if (System/getProperty "node.sname")
              (System/getProperty "node.name"))
          (util/get-hostname)))

(defn perform-gui-tasks
  "Close the custom splash screen."
  []
  (-> (java.awt.SplashScreen/getSplashScreen)
      (.close)))

(defn perform-node-tasks
  "This is the function that sets up a running node for a given JVM."
  []
  (twig/set-level! 'clojang :info)
  (let [node-name (get-node-name)]
    (log/infof "Bringing up OTP node on %s ..." node-name)
    (nodes/node node-name)))

(defn -premain
  [args instrument]
  (perform-node-tasks)
  (perform-gui-tasks))
