(ns clojang.agent.const
  "Clojang agent constants")

(def short-name (or (System/getProperty "node.sname") "nonode"))
(def long-name (or (System/getProperty "node.name") "nonode@nohost"))
(def headless (System/getProperty "headless"))
(def default-mbox-name "default")
