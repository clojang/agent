(defproject clojang/agent "0.4.0-SNAPSHOT"
  :description "Clojang Node and REPL Start-up"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.4.0-SNAPSHOT"]
    [dire "0.5.4"]
    [clojusc/trifl "0.1.0-SNAPSHOT"]
    [clojusc/twig "0.3.1"]]
  :manifest {"Premain-Class" "clojang.agent"}
  :aot :all
  :profiles {
    :testing {
       :plugins
         [[lein-ancient "0.6.10"]
          [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
          [lein-bikeshed "0.4.1"]
          [lein-kibit "0.1.3" :exclusions [org.clojure/clojure]]
          [venantius/yagni "0.1.4"]]}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojang.agent.dev}}})
