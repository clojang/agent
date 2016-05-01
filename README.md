# clojang-agent

[![Build Status][travis-badge]][travis][![Clojars Project][clojars-badge]][clojars]

[![Clojang logo][clojang-logo]][clojang-logo-large]

*Startup JVM Agent for Clojang*


#### Table of Contents

* [About](#about-)
* [Usage](#usage-)
* [License](#license-)


## About [&#x219F;](#table-of-contents)

The Clojang Agent is intended to perform one significant task and one minor one:

* Start up a JInterface node (in the same way that an Erlang shell or an LFE REPL when the BEAM is started in distributed mode, e.g., ``-sname mynode``)
* Close the splash image that Clojang displays


## Usage [&#x219F;](#table-of-contents)

To use the agent, update your ``project.clj`` (either top-level or one of your profiles) to include the following:

```clj
  ...
  :dependencies [
    [clojang/jiface "0.1.1"]
    [clojang/agent "0.1.0"]
    ...]
  :jvm-opts ["-Dnode.sname=clojang"]
  :java-agents [[clojang/agent "0.1.0"]]
  :aot [clojang.agent.startup]
  ...
```


## License [&#x219F;](#table-of-contents)

```
Copyright © 2016 Duncan McGreggor

Distributed under the Apache License Version 2.0.
```


<!-- Named page links below: /-->

[travis]: https://travis-ci.org/clojang/agent
[travis-badge]: https://travis-ci.org/clojang/agent.png?branch=master
[clojang-logo]: resources/images/clojang-logo-250x.png
[clojang-logo-large]: resources/images/clojang-logo-1000x.png
[clojars]: https://clojars.org/clojang/agent
[clojars-badge]: https://img.shields.io/clojars/v/clojang/agent.svg
