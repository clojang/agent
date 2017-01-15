(ns clojang.agent.const
  "Clojang agent constants")

(def short-name (System/getProperty "node.sname"))
(def long-name (System/getProperty "node.name"))
(def headless (System/getProperty "headless"))
(def default-mbox-name "default")
