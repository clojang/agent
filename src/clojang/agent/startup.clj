(ns clojang.agent.startup
  "Clojang JVM Agent startup."
  (:require
    [clojang.agent.const :as const]
    [clojang.agent.errors :as errors]
    [dire.core :refer [with-handler!]]
    [jiface.otp.messaging :as messaging]
    [jiface.otp.nodes :as nodes]
    [taoensso.timbre :as log]
    [trifl.net :as net])
  (:import
    (java.awt HeadlessException SplashScreen)
    (java.io IOException)))

(defn perform-gui-tasks
  "Close the custom splash screen."
  []
  (if-let [screen (SplashScreen/getSplashScreen)]
    (.close screen)))

(defn start-default-node
  [node-name]
  (let [default-node (nodes/default-node node-name)
        default-mbox (messaging/default-mbox
                       default-node const/default-mbox-name)]
    (log/info "Registered nodes with message boxes:"
              (vec (nodes/get-names default-node)))
    {:default-node default-node
     :default-mbox default-mbox}))

(defn perform-node-tasks
  "This is the function that sets up a running node for a given JVM."
  [node-name]
  (log/infof "Bringing up OTP node on %s ..." node-name)
  (let [{:keys [default-node default-mbox]} (start-default-node node-name)]
    {:node {:name node-name
            :object default-node}
     :mbox {:name const/default-mbox-name
            :object default-mbox}}))

;; Running without a GUI
(with-handler! #'perform-gui-tasks
  HeadlessException
  (fn [e & args]
    (log/warn (.getMessage e))))

;; Running without an Erlang Port Mapper Daemon
(with-handler! #'start-default-node
  IOException
  (fn [e & args]
    (let [err (.getMessage e)]
      (if (= errors/nameserver-unresponsive err)
        (do
          (log/error err)
          (log/fatal errors/epmd-not-running?)
          (throw (ex-info errors/epmd-not-running?
                          {:upstream {
                             :error err
                             :error-type java.io.IOException
                             :exception e}})))
        (throw e)))))

