(ns clojang.agent.startup
  ""
  (:require [clojang.agent.const :as const]
            [clojure.tools.logging :as log]
            [dire.core :refer [with-handler!]]
            [clojusc.twig :as logger]
            [jiface.otp.nodes :as nodes]
            [jiface.util :as util])
  (:import [java.lang.instrument]
           [java.awt HeadlessException SplashScreen])
  (:gen-class
    :methods [^:static [premain [String java.lang.instrument.Instrumentation] void]]))

(defn get-node-name
  "Get the node name."
  []
  (format "%s@%s"
    (if-not (nil? const/short-name)
      const/short-name
      const/long-name)
    (util/get-hostname)))

(defn perform-gui-tasks
  "Close the custom splash screen."
  []
  (if-let [screen (SplashScreen/getSplashScreen)]
    (.close screen)))

(defn perform-node-tasks
  "This is the function that sets up a running node for a given JVM."
  []
  (logger/set-level! 'clojang :info)
  (let [default-node-name (get-node-name)
        default-node (nodes/node default-node-name)]
    (log/infof "Bringing up OTP node on %s ..." default-node-name)
    (-> default-node
        (nodes/create-mbox)
        (messaging/register-name const/default-mbox-name))))

(defn headless?
  "Check to see if this JVM is declared as being headless."
  []
  (if (nil? const/headless)
    false
    true))

(defn -premain
  [args instrument]
  (perform-node-tasks)
  (if-not (headless?)
    (perform-gui-tasks)))

(with-handler! #'perform-gui-tasks
  HeadlessException
  (fn [e & args]
    (log/warn (.getMessage e))))
