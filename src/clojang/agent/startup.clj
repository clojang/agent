(ns clojang.agent.startup
  "Clojang JVM Agent startup."
  (:require
    [clojang.agent.const :as const]
    [dire.core :refer [with-handler!]]
    [jiface.otp.messaging :as messaging]
    [jiface.otp.nodes :as nodes]
    [taoensso.timbre :as log]
    [trifl.net :as net])
  (:import
    (java.awt HeadlessException SplashScreen)))

(defn perform-gui-tasks
  "Close the custom splash screen."
  []
  (if-let [screen (SplashScreen/getSplashScreen)]
    (.close screen)))

(defn perform-node-tasks
  "This is the function that sets up a running node for a given JVM."
  [node-name]
  (log/infof "Bringing up OTP node on %s ..." node-name)
  (let [default-node (nodes/default-node node-name)
        default-mbox (messaging/default-mbox
                       default-node const/default-mbox-name)]
    (log/info "Registered nodes with message boxes:"
              (vec (nodes/get-names default-node)))))

(with-handler! #'perform-gui-tasks
  HeadlessException
  (fn [e & args]
    (log/warn (.getMessage e))))
