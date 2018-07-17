# agent

[![Build Status][travis-badge]][travis]
[![Dependencies Status][deps-badge]][deps]
[![Clojars Project][clojars-badge]][clojars]

*Clojang Node and REPL Start-up*

[![Clojang logo][logo]][logo-large]


#### Contents

* [About](#about-)
* [Usage](#usage-)
* [Documentation](#documentation-)
* [Donating](#donating-)
* [License](#license-)


## About [&#x219F;](#contents)

The Clojang Agent is intended to perform one significant task and one minor one:

* Start up a JInterface node (in the same way that an Erlang shell or an LFE
  REPL when the BEAM is started in distributed mode, e.g., `-sname mynode`)
* Close the splash image that Clojang displays


## Usage [&#x219F;](#contents)

To use the agent, update your `project.clj` (either top-level or one of your
profiles) to include the following:

```clj
  ...
  :dependencies [
    [clojang/jiface "0.5.0"]
    [clojang/agent "0.4.0"]
    ...]
  :jvm-opts ["-Dnode.sname=clojang"]
  :java-agents [[clojang/agent "0.4.0"]]
  :aot [clojang.agent.startup]
  ...
```


## Documentation [&#x219F;](#contents)

The clojang-agent API reference is available here:

 * http://clojang.github.io/agent


## Donating [&#x219F;](#contents)

A donation account for supporting development on this project has been set up
on Liberapay here:

* [https://liberapay.com/clojang/donate](https://liberapay.com/clojang/donate)

You can learn more about Liberapay on its [Wikipedia entry][libera-wiki] or on the
service's ["About" page][libera-about].

[libera-wiki]: https://en.wikipedia.org/wiki/Liberapay
[libera-about]: https://liberapay.com/about/


## License [&#x219F;](#contents)

```
Copyright © 2018 The Clojang Project

Copyright © 2016-2017 Duncan McGreggor

Distributed under the Apache License Version 2.0.
```


<!-- Named page links below: /-->

[travis]: https://travis-ci.org/clojang/agent
[travis-badge]: https://travis-ci.org/clojang/agent.png?branch=master
[deps]: http://jarkeeper.com/clojang/agent
[deps-badge]: http://jarkeeper.com/clojang/agent/status.svg
[clojars]: https://clojars.org/clojang/agent
[clojars-badge]: https://img.shields.io/clojars/v/clojang/agent.svg
[logo]: https://github.com/clojang/resources/blob/master/images/logo-5-250x.png
[logo-large]: https://github.com/clojang/resources/blob/master/images/logo-5-1000x.png
