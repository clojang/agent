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
    [clojusc/trifl "0.1.0-SNAPSHOT"]]
  :manifest {"Premain-Class" "clojang.agent"}
  :codox {
    :project {:name "clojang-agent"}
    :themes [:clojang]
    :output-path "docs/current"
    :doc-paths ["resources/docs"]
    :namespaces [#"^clojang\.(?!test)"]
    :metadata {:doc/format :markdown}}
  :profiles {
    :uberjar {
      :aot :all}
    :test {
      :plugins [
        [lein-ancient "0.6.10"]
        [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
        [lein-bikeshed "0.4.1"]
        [lein-kibit "0.1.3" :exclusions [org.clojure/clojure]]
        [venantius/yagni "0.1.4"]]}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojang.agent.dev}}
    :docs {
      :aot :all
      :dependencies [[clojang/codox-theme "0.2.0-SNAPSHOT"]]
      :plugins [
        [lein-codox "0.10.3"]
        [lein-simpleton "1.3.0"]]}})
