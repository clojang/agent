(defproject clojang/agent "0.3.0-SNAPSHOT"
  :description "Clojang Node and REPL Start-up"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.3.0-SNAPSHOT"]
    [dire "0.5.4"]
    [clojusc/twig "0.3.0"]]
  :manifest {"Premain-Class" "clojang.agent.startup"}
  :aot :all
  :profiles {
    :testing {
       :plugins
         [[lein-ancient "0.6.10"]
          [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
          [lein-bikeshed "0.4.1"]
          [lein-kibit "0.1.2" :exclusions [org.clojure/clojure]]
          [venantius/yagni "0.1.4"]]}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojang.agent.dev}}})
