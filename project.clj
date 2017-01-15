(defproject clojang/agent "0.2.0"
  :description "Clojang Node and REPL Start-up"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.2.0"]
    [dire "0.5.4"]
    [clojusc/twig "0.3.0"]]
  :manifest {"Premain-Class" "clojang.agent.startup"}
  :aot :all
  :profiles {
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojang.agent.dev}}})
