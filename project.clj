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
    :ubercompile {
      :aot :all}
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.2.8"]
        [lein-ancient "0.6.15"]
        [lein-bikeshed "0.5.1"]
        [lein-kibit "0.1.6"]
        [venantius/yagni "0.1.4"]]}
    :test {
      :aot :all
      :plugins [
        [lein-ltest "0.3.0"]]
      :source-paths ["test"]
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojang.agent.dev}}
    :docs {
      :aot :all
      :dependencies [
        [clojang/codox-theme "0.2.0-SNAPSHOT"]]
      :plugins [
        [lein-codox "0.10.3"]
        [lein-simpleton "1.3.0"]]}}
  :aliases {
    ;; Dev Aliases
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "ubercompile" ["do"
      ["clean"]
      ["with-profile" "+ubercompile" "compile"]]
    "check-vers" ["with-profile" "+lint" "ancient" "check" ":all"]
    "check-jars" ["with-profile" "+lint" "do"
      ["deps" ":tree"]
      ["deps" ":plugin-tree"]]
    "check-deps" ["do"
      ["check-jars"]
      ["check-vers"]]
    "kibit" ["with-profile" "+lint" "kibit"]
    "eastwood" ["with-profile" "+lint" "eastwood" "{:namespaces [:source-paths]}"]
    "lint" ["do"
      ["kibit"]
      ;["eastwood"]
      ]
    "ltest" ["with-profile" "+test" "ltest"]})
