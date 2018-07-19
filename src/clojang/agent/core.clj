(ns clojang.agent.core
  "Clojang JVM agent."
  (:require
    [clojang.agent.const :as const]
    [clojang.agent.startup :as startup]
    [clojusc.twig :as logger]
    [jiface.otp.messaging :as messaging]
    [jiface.otp.nodes :as nodes]
    [taoensso.timbre :as log]
    [trifl.net :as net])
  (:import
    (java.lang.instrument Instrumentation))
  (:gen-class
    :methods [^:static [agentmain [String java.lang.instrument.Instrumentation] void]
              ^:static [premain [String java.lang.instrument.Instrumentation] void]]))

(defn get-node-name
  "Get the node name."
  []
  (format "%s@%s"
    (if-not (nil? const/short-name)
      const/short-name
      const/long-name)
    (net/get-short-local-hostname)))

(defn headless?
  "Check to see if this JVM is declared as being headless."
  []
  (if (nil? const/headless)
    false
    true))

(defn -main
  [& args]
  (logger/set-level! 'clojang :info)
  (startup/perform-node-tasks (get-node-name))
  (if-not (headless?)
    (startup/perform-gui-tasks)))

(defn -agentmain
  [^String args ^Instrumentation instrumentation]
  (-main))

(defn -premain
  [^String args ^Instrumentation instrumentation]
  (-agentmain args instrumentation))
