(defn get-banner
  []
  (try
    (str
      (slurp "resources/text/banner.txt")
      ;(slurp "resources/text/loading.txt")
      )
    ;; If another project can't find the banner, just skip it.
    (catch Exception _ "")))

(defn get-prompt
  [ns]
  (str "\u001B[35m[\u001B[34m"
       ns
       "\u001B[35m]\u001B[33m λ\u001B[m=> "))

(defproject clojang/agent "0.7.0-SNAPSHOT"
  :description "Clojang Node and REPL Start-up"
  :url "https://github.com/clojang/agent"
  :scm {
    :name "git"
    :url  "https://github.com/clojang/agent"}
  :license {
    :name "Apache License, Version 2.0"
    :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [clojang/jiface "0.7.0-SNAPSHOT"]
    [clojusc/trifl "0.3.0"]
    [clojusc/twig "0.3.3"]]
  :manifest {"Agent-Class" "clojang.agent.core"
             "Premain-Class" "clojang.agent.core"
             "Can-Redefine-Classes" "true"}
  :main clojang.agent.core
  :aot [clojang.agent.core]
  :codox {
    :project {:name "clojang-agent"}
    :themes [:clojang]
    :output-path "docs/current"
    :doc-paths ["resources/docs"]
    :namespaces [#"^clojang\.(?!test)"]
    :metadata {:doc/format :markdown}}
  :profiles {
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.2.9"]
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
    :docs {
      :aot :all
      :dependencies [
        [clojang/codox-theme "0.2.0-SNAPSHOT"]]
      :plugins [
        [lein-codox "0.10.4"]
        [lein-simpleton "1.3.0"]]}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :plugins [
        [bansd/deploy-uberjar "0.1.2"]]
      :source-paths ["dev-resources/src"]
      :repl-options {
        :init-ns clojang.agent.repl
        :prompt ~get-prompt
        :init ~(println (get-banner))}}}
  :aliases {
    ;; Dev Aliases
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "compile" ["do"
      ["clean"]
      ["compile"]]
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
    "ltest" ["with-profile" "+test" "ltest"]
    "build" ["do"
      ["clean"]
      ["check-vers"]
      ["lint"]
      ["ltest" ":all"]
      ["uberjar"]]})
