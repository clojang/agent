PROJ := $(strip $(word 2, $(shell grep defproject project.clj )))
PROJ_VERSION := $(strip $(subst \", , $(word 3, $(shell grep defproject project.clj ))))
ERL_VERSION := $(shell erl -eval "io:format(erlang:system_info(system_version)),halt()" -noshell)
ERL_LIBS := $(shell erl -eval "io:format(code:root_dir()),halt()" -noshell)
CLOJURE_DEP := $(strip $(shell grep "org.clojure/clojure" project.clj))
CLOJURE_VER := $(subst ], , $(word 3, $(CLOJURE_DEP)))

include dev-resources/make/code.mk
include dev-resources/make/docs.mk
include dev-resources/make/test.mk
