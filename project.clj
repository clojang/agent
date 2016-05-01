(defproject clojang/agent "0.1.4-SNAPSHOT"
  :description "Startup JVM Agent for Clojang"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.1.2-SNAPSHOT"]
    [dire "0.5.4"]
    [twig "0.1.6"]]
  :manifest {"Premain-Class" "clojang.agent.startup"}
  :aot [clojang.agent.startup]
  :profiles {
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]
        [twig "0.1.6"]]
      :source-paths ["dev-resources/src"]
      :aot [clojure.tools.logging.impl]
      :repl-options {:init-ns clojang.agent.dev}}})
