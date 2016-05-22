(defproject clojang/agent "0.2.0-SNAPSHOT"
  :description "Startup JVM Agent for Clojang"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.2.0-SNAPSHOT"]
    [dire "0.5.4"]
    [clojusc/twig "0.2.1"]]
  :manifest {"Premain-Class" "clojang.agent.startup"}
  :profiles {
    :uber {
      :aot :all}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]
        [clojusc/twig "0.2.1"]]
      :source-paths ["dev-resources/src"]
      :aot [clojure.tools.logging.impl]
      :repl-options {:init-ns clojang.agent.dev}}})
